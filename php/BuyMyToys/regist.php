<?php
session_start();
require_once '../config.php';
require_once './func/function.php';

//データベース接続
$cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
mysqli_set_charset($cn, 'utf8');
if (isset($_POST['regist_mail'])) {
    // エラーメッセージ初期化
    $errors = [];


    //POSTデータ変数格納
    $mail = isset($_POST['regist_mail']['mail']) ? htmlspecialchars($_POST['regist_mail']['mail'], ENT_QUOTES)  : '';

    //メール未入力判定
    if (empty($mail)) {
        $errors['mail'] = "メールが入力されていません。";
    } else {
        // メール形式チェック
        if (!preg_match("/^([a-zA-Z0-9])+([a-zA-Z0-9\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\._-]+)+$/", $mail)) {
            $errors['mail_check'] = "メールアドレスの形式が正しくありません。";
        }
        // すでに登録されているmailかどうか
        $alredy = alredy_member_check($cn, $mail);
        if ((int) $alredy != 0) {
            $errors['member_check'] = "このメールアドレスはすでに利用されております。";
        }
    }
    if (count($errors) === 0) {
        $urltoken = hash('sha256', uniqid(rand(), 1));
        $url = BASE_URL . "regist.php?urltoken=" . $urltoken;

        //仮会員用テーブルに登録
        add_pre_member($cn, $urltoken, $mail);
        //データベース接続切断
        mysqli_close($cn);

        // メール送信
        mb_language('ja');
        mb_internal_encoding('UTF-8');
        //メールの宛先
        $mailTo = $mail;

        //Return-Pathに指定するメールアドレス
        $returnMail = 'web@example.com';

        $name = "BuyMyToys";
        $mail = 'web@example.com';
        $subject = "【BuyMyToys】新規会員登録用URLのお知らせ";

        $body = <<< EOM
        24時間以内に下記のURLからご登録下さい。
        {$url}
        EOM;
        //Fromヘッダーを作成
        $header = 'From: ' . mb_encode_mimeheader($name) . ' <' . $mail . '>';

        if (mb_send_mail($mailTo, $subject, $body, $header, '-f' . $returnMail)) {

            //セッション変数を全て解除
            $_SESSION = array();

            //クッキーの削除
            if (isset($_COOKIE["PHPSESSID"])) {
                setcookie("PHPSESSID", '', time() - 1800, '/');
            }

            //セッションを破棄する
            session_destroy();

            $message = "<span>$mailTo</span><br>認証メールが転送されました。<br> メールをチェックしてください。";
        } else {
            $errors['mail_error'] = "メールの送信に失敗しました。";
        }
    }
    require_once './tpl/nologin/regist/send_mail.php';
    exit;
}
// メールリンククリック
if (isset($_GET['urltoken'])) {
    //GETデータを変数に入れる
    $urltoken = isset($_GET['urltoken']) ? htmlspecialchars($_GET['urltoken'], ENT_QUOTES) : '';

    $valid_token_mail = check_token($cn, $urltoken);
    //レコード件数
    if (count($valid_token_mail) == 1) {
        $mail  = $valid_token_mail['mail'];
        $_SESSION['mail'] = $mail;
    } else {
        $errors['urltoken_timeover'] = "このURLはご利用できません。<br>有効期限が過ぎた等の問題があります。<br>もう一度登録をやりなおして下さい。";
    }
    //データベース接続切断
    mysqli_close($cn);

    require_once './tpl/nologin/regist/enter_member.php';
    exit;
}
if (isset($_POST['regist_member'])) {

    // セッション初期化
    $_SESSION['member_id'] = '';
    $_SESSION['password'] = '';
    // エラーメッセージ初期化
    $errors = [];

    //POSTされたデータを各変数に入れる

    $member_name = isset($_POST['regist_member']['member_name']) ? htmlspecialchars($_POST['regist_member']['member_name'], ENT_QUOTES) : '';
    $member_nickname = isset($_POST['regist_member']['member_nickname']) ? htmlspecialchars($_POST['regist_member']['member_nickname'], ENT_QUOTES) : '';
    $member_gender = isset($_POST['regist_member']['member_gender']) ? $_POST['regist_member']['member_gender'] : '';
    $member_id = isset($_POST['regist_member']['member_id']) ? htmlspecialchars($_POST['regist_member']['member_id'], ENT_QUOTES) : '';
    $password = isset($_POST['regist_member']['password']) ? htmlspecialchars($_POST['regist_member']['password'], ENT_QUOTES) : '';
    $member_tel = isset($_POST['regist_member']['member_tel']) ? htmlspecialchars($_POST['regist_member']['member_tel'], ENT_QUOTES) : '';
    $birthday_year = isset($_POST['regist_member']['year']) ? $_POST['regist_member']['year'] : '';
    $birthday_month = isset($_POST['regist_member']['month']) ? $_POST['regist_member']['month'] : '';
    $birthday_day = isset($_POST['regist_member']['day']) ? $_POST['regist_member']['day'] : '';
    $member_birthday = $birthday_year . '-' . $birthday_month . '-' . $birthday_day;
    // プロフィール画像格納
    $profile_icon = $_FILES['profile_icon'];

    $member_name = spaceTrim($member_name);
    $member_nickname = spaceTrim($member_nickname);
    $member_id = spaceTrim($member_id);
    $password = spaceTrim($password);
    $member_tel = spaceTrim($member_tel);
    //入力判定
    if (empty($member_name)) {
        $errors['member_name'] = "氏名が入力されていません。";
    }
    if (empty($member_nickname)) {
        $errors['member_nickname'] = "ニックネームが入力されていません。";
    }
    if ($member_gender === 0) {
        $errors['member_nickname'] = "性別が選択されていません。";
    }
    if (empty($member_tel)) {
        $errors['member_tel'] = "電話番号が入力されていません。";
    }
    if (empty($birthday_year)) {
        $errors['birthday_year'] = "年が選択されていません";
    }
    if (empty($birthday_month)) {
        $errors['birthday_month'] = "月が選択されていません";
    }
    if (empty($birthday_day)) {
        $errors['birthday_day'] = "日が選択されていません";
    }

    if (empty($member_id)) {
        $errors['member_id'] = "メンバーIDが入力されていません。";
    } elseif (mb_strlen($member_id) > 10) {
        $errors['member_id_length'] = "メンバーIDは10文字以内で入力して下さい。";
    }

    // すでに登録されているmember_idかどうか
    $alredy_id = alredy_member_id_check($cn, $member_id);
    if ((int) $alredy_id != 0) {
        $errors['member_id_check'] = "このメンバーIDはすでに利用されております。";
    }

    //パスワード入力判定
    if (empty($password)) {
        $errors['password'] = "パスワードが入力されていません。";
    } elseif (!preg_match('/^[0-9a-zA-Z]{5,30}$/', $password)) {
        $errors['password_length'] = "パスワードは半角英数字の5文字以上30文字以下で入力して下さい。";
    } else {
        $password_hide = str_repeat('*', strlen($password));
    }
    //エラーが無ければセッションに登録
    if (count($errors) === 0) {
        $_SESSION['member_name'] = $member_name;
        $_SESSION['member_nickname'] = $member_nickname;
        $_SESSION['member_gender'] = $member_gender;
        $_SESSION['member_id'] = $member_id;
        $_SESSION['password'] = $password;
        $_SESSION['member_tel'] = $member_tel;
        $_SESSION['member_birthday'] = $member_birthday;
        $_SESSION['profile_icon'] = $profile_icon;
    }
    // ページ遷移の際に削除されるのでここで移動
    move_uploaded_file($profile_icon['tmp_name'], PRE_PROFILE_UPLOAD_PATH . 'user_profile.jpg');
    require_once './tpl/nologin/regist/confirm_member.php';
    exit;
}

// 登録
if (isset($_POST['add_member'])) {
    // エラーメッセージ初期化
    $errors = [];

    $member_name = $_SESSION['member_name'];
    $member_nickname = $_SESSION['member_nickname'];
    $member_gender = $_SESSION['member_gender'];
    $member_mail = $_SESSION['mail'];
    $member_id = $_SESSION['member_id'];
    //パスワードのハッシュ化
    $password_hash =  password_hash($_SESSION['password'], PASSWORD_DEFAULT);
    $member_tel = $_SESSION['member_tel'];
    $member_birthday = $_SESSION['member_birthday'];
    $profile_icon = $_SESSION['profile_icon'];
    add_member($cn, $member_id, $member_name, $member_nickname, $member_gender, $member_mail, $password_hash, $member_tel, $member_birthday);
    update_pre_member($cn, $member_mail);
    // メンバーキー取得
    $member_key = get_member_key($member_id);
    // プロフィール画像フォルダ生成+画像保存
    upload_profile_icon($member_key['member_key']);
    //データベース接続切断
    mysqli_close($cn);

    //セッションクッキーの削除・sessionidとの関係を探れ。つまりはじめのsesssionidを名前でやる
    if (isset($_COOKIE["PHPSESSID"])) {
        setcookie("PHPSESSID", '', time() - 1800, '/');
    }
    //セッションを破棄する
    session_destroy();
    require_once './tpl/nologin/regist/complete.php';
    exit;
}
// 会員登録最初に表示
require_once './tpl/nologin/regist/enter_mail.php';
exit;
