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
    <div id="address_add">
        <form action="./index.php" method="post">
            <input type="text" name="enter_address[zipcode]" size="10" maxlength="8" onKeyUp="AjaxZip3.zip2addr(this,'','enter_address[addr01]','enter_address[addr02]');" placeholder="郵便番号　入力例:5300001">
            <!-- ▼住所入力フィールド(都道府県) -->
            <input type="text" name="enter_address[addr01]" size="20" placeholder="都道府県">
            <!-- ▼住所入力フィールド(都道府県以降の住所) -->
            <input type="text" name="enter_address[addr02]" size="60" placeholder="市区町村">
            <!-- 番地 -->
            <input type="text" name="enter_address[house_number]" placeholder="番地">
            <!-- マンション名等 -->
            <input type="text" name="enter_address[building_name]" placeholder="マンション名等＋号室">
            <!-- 名義 -->
            <input type="text" name="enter_address[name]" placeholder="名義">
            <input type="hidden" name="enter_address[member_id]" value="<?php echo $member_id; ?>">
            <div id="add_address_button">
                <button type="reset">リセット</button>
                <button id="confirm" type="submit">確認する</button>
            </div>
        </form>
    </div>
    <script src="./js/jquery-3.4.1.min.js"></script>
    <script src="./js/ajaxzip3.js"></script>
    <!-- その他の手書きjs -->
    <script src="./js/others.js"></script>
</body>

</html>