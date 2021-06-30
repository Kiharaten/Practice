<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- ホーム画面に追加時アドレスバー非表示 -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <!-- ホーム画面に表示されるアプリ名 -->
    <meta name="apple-mobile-web-app-title" content="Buy My Toys">
    <!-- ホーム画面に表示されるアプリアイコン -->
    <link rel="apple-touch-icon" sizes="180x180" href="./images/materials/toys_boy_icon.png">
    <link rel="stylesheet" href="./css/reset.css">
    <!-- 未ログイン時のヘッダーCSS -->
    <link rel="stylesheet" href="./css/nologin_header_style.css">
    <!-- 商品一覧表示用のCSS -->
    <link rel="stylesheet" href="./css/lineup_style.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:100,500,900&amp;subset=japanese" rel="stylesheet">
    <link href="./images/materials/toys_boy_white.png">
    <link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
    <title><?php echo $title; ?></title>
</head>

<body>
    <header>
        <nav>
            <button id="login_icon" type="submit"><img src="./images/materials/login.png"></button>
            <h1 id="page_top">
                <a href="">
                    <img src="./images/materials/toys_boy_white.png" alt="Buy My Toys">
                </a>
            </h1>
            <p id="search_icon"><img src="./images/materials/search.png" alt="商品検索"></p>
        </nav>
        <div id="login_form">
            <p id="close_icon"><img src="./images/materials/close.png" alt="閉じる"></p>
            <form id="login_form_content" action="./index.php" method="post">
                <p id="loginform_logo">
                    <img src="./images/materials/toys_boy.png" alt="ロゴ">
                    <span>Buy My Toys</span>
                </p>
                <ul>
                    <li><input type="text" name="login[id_mail]" placeholder="メールアドレス"></li>
                    <li><input type="password" name="login[password]" placeholder="パスワード"></li>
                    <input type="hidden" name="login[token]" value="<?php echo $token; ?>">
                    <li><button type="submit">ログイン</button></li>
                </ul>
                <p id="sing_in">アカウントを持っていない場合&nbsp;<a href="<?php echo BASE_URL; ?>index.php?singin=''">登録はこちら</a></p>
            </form>
        </div>
    </header>