<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/reset.css">
    <link rel="stylesheet" href="./css/login_form_style.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:100,500,900&amp;subset=japanese" rel="stylesheet">
    <link href="./images/materials/toys_boy_white.png">
    <link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
    <title>Buy My Toys | ログイン</title>
</head>

<body>
    <div id="login_form">
        <form id="close_icon" action="./index.php" method="get">
            <button type="submit"><img src="./images/materials/close.png" alt="閉じる"></button>
        </form>
        <p id="logo">
            <img src="./images/materials/toys_boy.png" alt="ロゴ">
            <span>Buy My Toys</span>
        </p>
        <form action="./index.php" method="post">
            <ul>
                <li><input type="text" name="login[id_mail]" placeholder="メンバーID&nbsp;or&nbsp;メールアドレス"></li>
                <li><input type="password" name="login[password]" placeholder="パスワード"></li>
                <li><button type="submit">ログイン</button></li>
            </ul>
            <p id="signin">アカウントを持っていない場合&nbsp;<a href="<?php echo BASE_URL; ?>index.php?singin=''">登録はこちら</a></p>
        </form>
    </div>
</body>

</html>