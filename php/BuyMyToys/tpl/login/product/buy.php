<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="./css/reset.css">
    <!-- 左上に戻るがあるヘッダー用スタイル -->
    <link rel="stylesheet" href="./css/back_header_style.css">
    <!-- 購入系スタイル -->
    <link rel="stylesheet" href="./css/buy_style.css">
    <title>Buy My Toys | 購入</title>
</head>
<body>
<header>
        <h1> 購入</h1>
        <p id="back_button"><a href="#" onclick="history.back(); return false;"><img src="./images/materials/back_arrow.png" alt="戻る"></a></p>
    </header>
    <div id="details">

        <p id="product_name"><?php echo $product["title"]; ?></p>
        <p id="product_img"><img src="<?php echo $product["image"]; ?>"></p>

        <ul>
            <li>出品者名：<?php echo $product["member_nickname"]; ?></li>
            <li>カテゴリ：<?php echo $product["category"]; ?></li>
            <li>配送方法：まさる堂らくらくパック</li>
            <li>商品説明
                <pre><?php echo $product["description"]; ?><pre></li>
            <li id="price">&yen;<?php echo $product["price"]; ?>(税込)</li>
        </ul>
        <form action="./index.php" method="post">
            <button type="submit" value="<?php echo $product_id ?>" name="no" id="confirm_butotn">見送る</button>
            <button type="submit" value="<?php echo $product_id ?>" name="buy" id="confirm_butotn">購入する</button>
        </form>
    </div>
</body>
</html>