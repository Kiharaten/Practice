<?php
//------------------------------------------------ 商品購入 ------------------------------------------------//
function lineup()
{
    // " 販売中の商品を一覧表示 ";
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "SELECT i.product_id AS id, i.product_name AS title, i.product_price AS price
            FROM product_information AS i
            WHERE del_flg = 0 AND trade_flg = 0
            ORDER BY i.product_regist_date DESC
            LIMIT 60;";
    $result = mysqli_query($cn, $sql);
    mysqli_close($cn);

    while ($db_data = mysqli_fetch_assoc($result)) {
        $products[] = [
            'id' => $db_data['id'],
            'title' => $db_data['title'],
            'price' => $db_data['price'],
            'img' => "./images/upload/" . $db_data["id"] . "/image1.jpg"
        ];
    }
    return $products;
}

function search_contents($product_name, $category_id, $under_price, $top_price, $member_name)
{
    if($product_name == ''){
        $product_name = NULL;
    }
    if($category_id == ''){
        $category_id = NULL;
    }
    if($member_name == ''){
        $member_name = NULL;
    }
    // sql文を実行して結果を配列型にします
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql =  sql_line_generator($product_name, $category_id, $under_price, $top_price, $member_name);
    $result = mysqli_query($cn, $sql);
    mysqli_close($cn);

    while ($db_data = mysqli_fetch_assoc($result)) {
        $products[] = [
            'id' => $db_data['id'],
            'title' => $db_data['title'],
            'price' => $db_data['price'],
            'img' => "./images/upload/" . $db_data["id"] . "/image1.jpg"
        ];
    }
    return $products;
}

function sql_line_generator($product_name, $category_id, $under_price, $top_price, $member_name)
{
    // この関数は５つの引数を元にsql文を生成します
    $sql_head = "SELECT  i.product_id AS id,
                    i.product_name AS title,
                    i.product_price AS price
            FROM product_information AS i, members AS m
            WHERE   i.del_flg = 0 AND
                    i.trade_flg = 0 ";


    $sql_bottom =   "ORDER BY i.product_regist_date DESC
                    LIMIT 60;";

    if(isset($product_name)){
        $sql_head .= "AND i.product_name LIKE '%".$product_name."%'";
    }

    if(isset($category_id)){
        $sql_head .="AND product_category = '".$category_id."'";
    }

    if(isset($under_price)){
        $sql_head .= "AND " . $under_price . " <= i.product_price ";
    }

    if(isset($top_price)){
        $sql_head .= "AND i.product_price <= " . $top_price . "";
    }

    if(isset($member_name)){
        $sql_head .= "AND
        i.member_id = (
            SELECT m.member_id
            FROM members AS m
            WHERE m.member_name LIKE '%" . $member_name . "%') AND
        m.member_id = (
            SELECT m.member_id
            FROM members AS m
            WHERE m.member_name LIKE '%" . $member_name . "%')";
    }

    return $sql_head .= $sql_bottom ;
}

function get_category_name($category_id)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "SELECT category_name
            FROM product_category
            WHERE category_id = '$category_id'";
    $result = mysqli_query($cn, $sql);
    $category_name = mysqli_fetch_assoc($result);
    mysqli_close($cn);

    return $category_name['category_name'];
}
function get_member_name($member_id)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "SELECT member_name FROM members WHERE member_id = '$member_id'";
    $result = mysqli_query($cn, $sql);
    $member_name = mysqli_fetch_assoc($result);
    mysqli_close($cn);

    return $member_name['member_name'];
}

function get_new_exhibits_product_id()
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "SELECT max(product_id)+1 AS num
            FROM product_information;";
    $result = mysqli_query($cn, $sql);
    $insert_num = mysqli_fetch_assoc($result);
    mysqli_close($cn);

    return $insert_num;
}
// 商品カテゴリ情報取得
function get_product_category()
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "SELECT category_id,category_name FROM product_category";
    $result = mysqli_query($cn, $sql);
    mysqli_close($cn);
    while ($db_data = mysqli_fetch_assoc($result)) {
        $categorys[] =  $db_data;
    }

    return $categorys;
}

function select_product_detail($product_id)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "SELECT i.product_name, m.member_nickname, c.category_name, i.product_description, i.product_price
            FROM product_information AS i, members AS m, product_category AS c
            WHERE i.product_id = " . $product_id . "
            AND m.member_id = (
                SELECT member_id
                FROM product_information
                WHERE product_id = " . $product_id . "
            ) AND c.category_id = (
                SELECT product_category
                FROM product_information
                WHERE product_id = " . $product_id . "
            );";
    $result = mysqli_query($cn, $sql);
    $row = mysqli_fetch_assoc($result);
    mysqli_close($cn);

    return $row;
}

function verification_buying($product_id)
{
    // " 商品確認画面でのmysql接続 ";

    $row = select_product_detail($product_id);
    $file = "./images/upload/" . $product_id . "/";
    $main_image = $file . "image1.jpg";

    $product = [
        'title' => $row['product_name'],
        'image' => $main_image,
        'member_nickname' => $row['member_nickname'],
        'category' =>  $row["category_name"],
        'description' => $row["product_description"],
        'price' => $row["product_price"],
    ];
    return $product;
}

function get_product_details($product_id)
{
    // " 商品詳細画面でのmysql接続 ";

    $row = select_product_detail($product_id);
    $file = "./images/upload/" . $product_id . "/";
    $main_image = $file . "image1.jpg";
    $product = [
        'title' => $row['product_name'],
        'image' => $main_image,
        'member_nickname' => $row['member_nickname'],
        'category' =>  $row["category_name"],
        'description' => $row["product_description"],
        'price' => $row["product_price"],
    ];
    return $product;
}

function unlink_if_isnot_uploaded($filename1, $filename2, $filename3)
{
    if ($filename1["error"]) {
        unlink('./images/upload/tpl/image1.jpg');
    }
}

function add_product($product_id, $member_id, $name, $price, $category_id, $description)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "INSERT INTO product_information VALUES(
            '" . $product_id . "',
            '" . $member_id . "',
            '" . $name . "',
            " . $price . ",
            '" . $category_id . "',
            '" . $description . "',
            CURRENT_TIMESTAMP,
            0,
            0);";
    $result = mysqli_query($cn, $sql);
    mysqli_fetch_assoc($result);
    mysqli_close($cn);
    return;
}
function cheng_trade_flg($product_id)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "UPDATE product_information SET trade_flg  = 1 WHERE product_id = " . $product_id . ";";
    mysqli_query($cn, $sql);
    mysqli_close($cn);

    return;
}
function add_favotite($product_id, $member_id)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "INSERT INTO favorites(member_id, product_id, del_flg) VALUES ('$member_id','$product_id',0)";
    mysqli_query($cn, $sql);
    mysqli_close($cn);

    return;
}
function del_favotite($product_id)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "UPDATE favorites SET del_flg  = 1 WHERE product_id = '$product_id'";
    mysqli_query($cn, $sql);
    mysqli_close($cn);

    return;
}

// おねだりリスト取得
function get_favorite_list($member_id)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "SELECT product_id FROM favorites WHERE member_id = '$member_id'";
    $result = mysqli_query($cn, $sql);
    while ($db_data = mysqli_fetch_assoc($result)) {
        $favorites_id[] =  $db_data;
    }
    mysqli_close($cn);
    return $favorites_id;
}
// おねだり商品名
function get_favo_product_id($product_name)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "SELECT f.product_id from product_information as p,favorites as f WHERE product_name = '$product_name'";
    $result = mysqli_query($cn, $sql);
    $product_id = mysqli_fetch_assoc($result);
    mysqli_close($cn);
    
    return $product_id;
}
function get_favo_product($product_id){
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "SELECT product_id,product_name FROM product_information WHERE product_id='$product_id'";
    $result = mysqli_query($cn, $sql);
    while ($db_data = mysqli_fetch_assoc($result)) {
        $paremts =  $db_data;
    }
    mysqli_close($cn);
    
    return $paremts;
}
// 親リスト取得
function get_paremts_list($member_id)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "SELECT parent_name,parentemail FROM parents WHERE member_id = '$member_id'";
    $result = mysqli_query($cn, $sql);
    while ($db_data = mysqli_fetch_assoc($result)) {
        $paremts[] =  $db_data;
    }
    mysqli_close($cn);
    return $paremts;
}
// 購入
function buy_product($product_id)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "UPDATE product_information SET del_flg = 1 WHERE product_id = '$product_id'";
    mysqli_query($cn, $sql);
    mysqli_close($cn);
    return ;
}
// 見送り
function nobuy($product_id)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "UPDATE product_information SET trade_flg  = 0 WHERE product_id = " . $product_id . ";";
    mysqli_query($cn, $sql);
    mysqli_close($cn);
    return ;
}
//------------------------------------------------ 会員機能 ------------------------------------------------//

// すでに登録済みの会員か
function alredy_member_check($cn, $mail)
{
    $sql = "SELECT COUNT(*) as count FROM members WHERE member_mail = '$mail'";
    $result = mysqli_query($cn, $sql);
    $db_data = mysqli_fetch_assoc($result);

    return $db_data['count'];
}
// 仮会員DB登録
function add_pre_member($cn, $urltoken, $mail)
{
    $sql = "INSERT INTO pre_member (urltoken,mail,date) VALUES ('$urltoken','$mail',now())";
    mysqli_query($cn, $sql);

    return;
}
// メールの有効期限チェック
function check_token($cn, $urltoken)
{
    //flagが0の未登録者・仮登録日から24時間以内
    //24時間以内に仮登録され、本登録されていないトークンの場合
    $sql = "SELECT mail FROM pre_member WHERE urltoken = '$urltoken' AND flag = 0 AND date > now() - interval 24 hour";
    $result = mysqli_query($cn, $sql);
    $db_data = mysqli_fetch_assoc($result);

    return $db_data;
}
//前後にある半角全角スペースを削除する関数
function spaceTrim($str)
{
    // 行頭
    $str = preg_replace('/^[　]+/u', '', $str);
    // 末尾
    $str = preg_replace('/[　]+$/u', '', $str);
    return $str;
}
// すでに使用中のID出ないか
function alredy_member_id_check($cn, $member_id)
{
    $sql = "SELECT COUNT(*) as count FROM members WHERE member_id = '$member_id'";
    $result = mysqli_query($cn, $sql);
    $db_data = mysqli_fetch_assoc($result);

    return $db_data['count'];
}
// 会員DB追加
function add_member($cn, $member_id, $member_name, $member_nickname, $member_gender, $member_mail, $password_hash, $member_tel, $member_birthday)
{
    $sql = "INSERT INTO members(member_id,member_name,member_nickname,member_gender,member_mail,member_password,member_tel,member_birthday)
    VALUES('$member_id','$member_name','$member_nickname','$member_gender','$member_mail','$password_hash','$member_tel','$member_birthday')";
    mysqli_query($cn, $sql);
    return;
}
// 仮会員状態無効化
function update_pre_member($cn, $mail)
{
    $sql = "UPDATE pre_member SET flag=1 WHERE mail='$mail'";
    mysqli_query($cn, $sql);
    return;
}
// プロフィール画像アップロード
function upload_profile_icon($member_key)
{
    mkdir(PROFILE_UPLOAD_PATH . 'no_' . $member_key);
    chmod(PROFILE_UPLOAD_PATH . 'no_' . $member_key . '/', 0777);
    rename(PRE_PROFILE_UPLOAD_NAME, PROFILE_UPLOAD_PATH . 'no_' . $member_key . '/user_profile.jpg');
    return;
}
// ログイン認証用のパスワードハッシュ取得
function get_hash_user($id_mail)
{
    //データベース接続
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    // IDかメールか
    if (strpos($id_mail, '@') === false) {
        // ID
        $sql = "SELECT member_id,member_password FROM members WHERE member_id = '$id_mail' AND del_flg=0";
    } else {
        // mail
        $sql = "SELECT member_id,member_password FROM members WHERE member_mail = '$id_mail' AND del_flg=0";
    }
    $result = mysqli_query($cn, $sql);
    mysqli_close($cn);
    $db_data = mysqli_fetch_assoc($result);
    return $db_data;
}

// メンバーキー取得
function get_member_key($member_id)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "SELECT member_key FROM members WHERE member_id = '$member_id'";
    $result = mysqli_query($cn, $sql);
    mysqli_close($cn);
    $db_data = mysqli_fetch_assoc($result);
    return $db_data['member_key'];
}

// マイページ用表示情報取得
function get_member_info($member_id)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "SELECT member_key,member_name,member_nickname FROM members WHERE member_id = '$member_id'";
    $result = mysqli_query($cn, $sql);
    mysqli_close($cn);
    $db_data = mysqli_fetch_assoc($result);

    return $db_data;
}
// 会員の住所取得
function get_address($member_id)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "SELECT zipcode,address,name FROM addresses WHERE member_id = '$member_id'";
    $result = mysqli_query($cn, $sql);
    mysqli_close($cn);
    while ($db_data = mysqli_fetch_assoc($result)) {
        $address[] =  $db_data;
    }
    return $address;
}
// 会員の住所登録
function add_address($member_id, $zipcode, $address, $name)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "INSERT INTO addresses(member_id,zipcode,address,name) VALUES('$member_id','$zipcode','$address','$name');";
    mysqli_query($cn, $sql);
    mysqli_close($cn);

    return;
}

// 会員の口座取得
function get_bank($member_id)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "SELECT bank_name,bank_branch,bank_number,bank_holder FROM bank_accounts WHERE member_id = '$member_id'";
    $result = mysqli_query($cn, $sql);
    mysqli_close($cn);
    while ($db_data = mysqli_fetch_assoc($result)) {
        $banks[] =  $db_data;
    }
    return $banks;
}
// 会員に口座登録
function add_bank($member_id, $bank_name, $branch_number, $branch_name, $bank_number, $bank_holder)
{
    $cn = mysqli_connect(HOST, DB_USER, DB_PASS, DB_NAME);
    mysqli_set_charset($cn, 'utf8');
    $sql = "INSERT INTO bank_accounts(member_id,bank_name,branch_number,bank_branch,bank_number,bank_holder) VALUES('$member_id','$bank_name',$branch_number,'$branch_name',$bank_number,'$bank_holder');";
    mysqli_query($cn, $sql);
    mysqli_close($cn);

    return;
}
