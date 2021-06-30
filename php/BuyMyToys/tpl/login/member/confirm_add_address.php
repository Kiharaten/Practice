<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- ホーム画面に追加時アドレスバー非表示 -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link rel="stylesheet" href="./css/reset.css">
    <!-- 左上に戻るがあるヘッダー用スタイル -->
    <link rel="stylesheet" href="./css/back_header_style.css">
    <!-- マイページCSS -->
    <link rel="stylesheet" href="./css/info_style.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:100,500,900&amp;subset=japanese" rel="stylesheet">
    <link href="./images/materials/toys_boy_white.png">
    <title>住所入力</title>
</head>

<body>
    <header>
        <h1> 住所追加</h1>
        <p id="back_button"><a href="#" onclick="history.back(); return false;"><img src="./images/materials/back_arrow.png" alt="戻る"></a></p>
    </header>
    <div id="confirm_address">
        <!-- 郵便番号 -->
        <p>郵便番号:<?php echo $zipcode; ?></p>
        <!-- 住所 -->
        <p>住所:<?php echo $address; ?></p>
        <!-- 名義 -->
        <p>名義:<?php echo $name; ?></p>
        <form action="./index.php" method="post" id="add_address_button">
            <button id="confirm" type="submit" name="regist_address">登録</button>
        </form>
    </div>
    <script src="./js/jquery-3.4.1.min.js"></script>
    <script src="./js/ajaxzip3.js"></script>
    <!-- その他の手書きjs -->
    <script src="./js/others.js"></script>
</body>

</html>