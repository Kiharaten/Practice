<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- ホーム画面に追加時アドレスバー非表示 -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link rel="stylesheet" href="./css/reset.css">
    <!-- 会員登録時CSS -->
    <link rel="stylesheet" href="./css/regist_style.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:100,500,900&amp;subset=japanese" rel="stylesheet">
    <link href="./images/materials/toys_boy_white.png">
    <title>会員登録完了画面</title>
</head>

<body>

    <header>
        <h1>会員登録</h1>
    </header>
    <div id="message">
        <?php if (count($errors) === 0) : ?>

            <p id="send_mail_message">登録完了いたしました。<br><a href="<?php echo BASE_URL; ?>index.php">トップページへ</a></p>

        <?php elseif (count($errors) > 0) : ?>

            <?php foreach ($errors as $value) : ?>
                <p id="send_mail_message"><?php echo $value; ?></p>
            <?php endforeach; ?>

        <?php endif; ?>
    </div>
</body>

