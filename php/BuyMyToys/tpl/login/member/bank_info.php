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
    <title>マイページ｜口座情報</title>
</head>

<body>
    <header>
        <h1> 口座情報</h1>
        <p id="back_button"><a href="#" onclick="history.back(); return false;"><img src="./images/materials/back_arrow.png" alt="戻る"></a></p>
    </header>
    <div id="bank_info">
        <?php if (is_array($bank_info)) : ?>
            <?php foreach ($bank_info as $reco) : ?>
                <ul class="bank_list">
                    <?php if (is_array($reco)) : ?>
                        <?php foreach ($reco as $value) : ?>
                            <li><?php echo $value; ?></li>
                        <?php endforeach; ?>
                    <?php else : ?>
                        <li><?php echo $roco; ?></li>
                    <?php endif; ?>
                </ul>
            <?php endforeach; ?>
        <?php else : ?>
            <ul id="bank_message">
                <li><?php echo $bank_info; ?></li>
            </ul>
        <?php endif; ?>
        <form action="./index.php" method="post">
            <button type="submit" value="<?php echo $member_id; ?>" name="add_bank">口座を追加する</button>
        </form>
    </div>
    <a href="./index.php">TOP（仮）</a>
    <script src="./js/jquery-3.4.1.min.js"></script>
    <!-- その他の手書きjs -->
    <script src="./js/others.js"></script>
</body>

</html>