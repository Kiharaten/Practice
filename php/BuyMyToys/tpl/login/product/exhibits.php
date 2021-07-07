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
    <!-- 出品用スタイル -->
    <link rel="stylesheet" href="./css/exhibits_style.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:100,500,900&amp;subset=japanese" rel="stylesheet">
    <link href="./images/materials/toys_boy_white.png">
    <link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
    <title>Buy By Toys | 出品</title>
</head>

<body>
    <header>
        <h1> 出品</h1>
        <p id="back_button"><a href="#" onclick="history.back(); return false;"><img src="./images/materials/back_arrow.png" alt="戻る"></a></p>
    </header>
    <div id="exhibits">
        <form action="./index.php" method="post" enctype="multipart/form-data">
            <div id="select_image">
                <label for="image1">
                    <img id="image1_preview" src="./images/materials/camera_icon_exhibits.png">
                    <input type="file" name="image1" class="product_image" id="image1">
                </label>
            </div>

            <select class="classic" name="enter_exhibits[category]" size="1">
                <option value="0">カテゴリ選択</option>
                <?php foreach ($categorys as $reco) : ?>
                    <option value="<?php echo $reco['category_id']; ?>"><?php echo $reco['category_name']; ?></option>
                <?php endforeach; ?>
            </select>
            <p id="product_name"><input type="text" name="enter_exhibits[name]" placeholder="商品名"></p>

            <textarea rows="10" cols="20" name="enter_exhibits[description]" placeholder="商品の説明"></textarea>

            <p id="details">商品の詳細</p>

            <p>配送方法：まさる堂らくらくパック</p>

            <p id="price"><input type="text" name="enter_exhibits[price]" placeholder="商品価格">&nbsp;円</p>
            <p id="comment">※ 手数料 (10%) + 配送料 (500円) が 加算されます</p>

            <div id="button">
                <button type="submit" name="lineup">やめておく</button>
                <button type="submit" id="confirm">かくにんする</button>
            </div>
        </form>
    </div>
    <script src="./js/jquery-3.4.1.min.js"></script>
    <!-- その他の手書きjs -->
    <script src="./js/others.js"></script>
</body>

</html>