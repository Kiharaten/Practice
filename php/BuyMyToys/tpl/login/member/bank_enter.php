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
    <title>口座情報入力</title>
</head>

<body>
    <header>
        <h1> 口座追加</h1>
        <p id="back_button"><a href="#" onclick="history.back(); return false;"><img src="./images/materials/back_arrow.png" alt="戻る"></a></p>
    </header>
    <div id="bank_add">
        <form action="./index.php" method="post">
            <!-- 金融機関名(主要) -->
            <select name="enter_bank[bank_name]">
                <option value="0">三菱UFJ銀行</option>
                <option value="1">みずほ銀行</option>
                <option value="2">りそな銀行</option>
                <option value="3">埼玉りそな銀行</option>
                <option value="4">三井住友銀行</option>
                <option value="5">ジャパネット銀行</option>
                <option value="6">楽天銀行</option>
                <option value="7">ゆうちょ銀行</option>
            </select>
            <input type="text" name="enter_bank[branch_number]" placeholder="支店コード">
            <input type="text" name="enter_bank[branch_name]" placeholder="支店名">
            <input type="text" name="enter_bank[bank_number]" placeholder="口座番号">
            <input type="text" name="enter_bank[bank_holder_last]" placeholder="口座名義(セイ)">
            <input type="text" name="enter_bank[bank_holder_first]" placeholder="口座名義(メイ)">
            <input type="hidden" name="enter_bank[member_id]" value="<?php echo $member_id; ?>">
            <div id="confirm_button_area">
                <button type="reset">リセット</button>
                <button id="confirm" type="submit">確認する</button>
            </div>
        </form>
    </div>
    <script src="./js/jquery-3.4.1.min.js"></script>
    <!-- その他の手書きjs -->
    <script src="./js/others.js"></script>
</body>

</html>