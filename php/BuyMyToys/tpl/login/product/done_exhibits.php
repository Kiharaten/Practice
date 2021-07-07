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
    <!-- 左上に戻るがあるヘッダー用スタイル -->
    <link rel="stylesheet" href="./css/back_header_style.css">
    <!-- 商品詳細画面での画像のサイズを横幅いっぱいに -->
    <link rel="stylesheet" href="./css/product_style.css">
    <!-- 出品用スタイル -->
    <link rel="stylesheet" href="./css/exhibits_style.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:100,500,900&amp;subset=japanese" rel="stylesheet">
    <link href="./images/materials/toys_boy_white.png">
    <link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
    <title>Buy My Toys | 出品完了</title>
</head>

<body>
<header>
        <h1> 出品完了</h1>
        <p id="back_button"><a href="#" onclick="history.back(); return false;"><img src="./images/materials/back_arrow.png" alt="戻る"></a></p>
    </header>
    <div id="done_exhibits">
        <div>
            <ul>
                <li>商品名：<?php echo $name; ?></li>
                <li>商品ID：<?php echo $product_id; ?></li>
                <li>出品者名：<?php echo $member_name ?></li>
                <li>商品カテゴリ：<?php echo $category; ?></li>
                <li  id="product_image">商品画像<img src="./images/upload/<?php echo $product_id; ?>/image1.jpg"></li>
                <li>価格：&yen;<?php echo $price; ?>円</li>
            </ul>
            <form action="./index.php" method="get">
                <button name="lineup">TOPページへ</button>
                <button name="mypage">出品内容の確認</button>
            </form>
        </di>
    </div>
    <script src="./js/jquery-3.4.1.min.js"></script>
    <!-- その他の手書きjs -->
    <script src="./js/others.js"></script>
</body>

</html>