<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- ホーム画面に追加時アドレスバー非表示 -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link rel="stylesheet" href="./css/reset.css">
    <!-- 会員登録時CSS -->
    <link rel="stylesheet" href="./css/regist_style.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:100,500,900&amp;subset=japanese" rel="stylesheet">
    <link href="./images/materials/toys_boy_white.png">
    <title>入力確認画面</title>
</head>

<body>

    <header>
        <h1>会員登録</h1>
        <p id="back_button"><a href="#" onclick="history.back(); return false;"><img src="./images/materials/back_arrow.png" alt="戻る"></a></p>
    </header>

    <?php if (count($errors) === 0) : ?>


        <form id="confirm_member_form" action="./regist.php" method="post">

            <p>氏名：<?php echo  $member_name; ?></p>
            <p>ニックネーム：<?php echo  $member_nickname; ?></p>
            <p>性別：<?php echo  $member_gender; ?></p>
            <p>メールアドレス：<?php echo  $_SESSION['mail']; ?></p>
            <p>メンバーID：<?php echo  $member_id; ?></p>
            <p>パスワード：<?php echo  $password_hide; ?></p>
            <p>電話番号：<?php echo  $member_tel; ?></p>
            <p>生年月日：<?php echo  $member_birthday; ?></p>
            <p id="last">画像ファイル名：<?php echo  $profile_icon['name']; ?></p>
            <button type="submit" name="add_member[regist]">登録する</button>

        </form>

    <?php elseif (count($errors) > 0) : ?>

        <?php foreach ($errors as $value) : ?>
            <p><?php echo $value; ?></p>
        <?php endforeach; ?>

        <input type="button" value="戻る" onClick="history.back()">

    <?php endif; ?>

</body>

</html>