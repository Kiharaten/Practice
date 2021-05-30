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
    <title>Buy My Toys | トップページ</title>
</head>

<body>
    <header>
        <nav>
            <form action="./index.php" method="get">
                <button id="login_icon" type="submit" name="login_form"><img src="./images/materials/login.png"></button>
            </form>
            <h1 id="page_top">
                <a href="./index.php">
                    <img src="./images/materials/toys_boy_white.png" alt="Buy My Toys">
                </a>
            </h1>
            <p id="search_icon"><a href="<?php echo BASE_URL; ?>index.php?search_form=''"><img src="./images/materials/search.png" alt="商品検索"></a></p>
        </nav>
    </header>
    <div id="lineup">
        <div id="products">
            <!-- データ内容表示 -->
            <?php foreach ($products as $reco) : ?>
                <div class="product">
                    <a href="<?php echo BASE_URL; ?>index.php?product_detail=<?php echo $reco['id']; ?>">
                        <img src="<?php echo $reco['img']; ?>" alt="商品">
                        <span class="price">&yen;&nbsp;<?php echo $reco['price']; ?></span>
                    </a>
                </div>
            <?php endforeach; ?>
        </div>
    </div>
    <script src="./js/jquery-3.4.1.min.js"></script>
    <!-- その他の手書きjs -->
    <script src="./js/others.js"></script>
</body>

</html>