<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/reset.css">
    <!-- 検索フォーム用スタイル -->
    <link rel="stylesheet" href="./css/search_form_style.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:100,500,900&amp;subset=japanese" rel="stylesheet">
    <link href="./images/materials/toys_boy_white.png">
    <link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
    <title>Buy My Toys | 検索</title>
</head>

<body>
    <div id="search_form">
        <form id="close_icon" action="./index.php" method="get">
            <button type="submit"><img src="./images/materials/close.png" alt="閉じる"></button>
        </form>
        <form id="enter_keyword_form" action="./index.php" method="post">
            <ul>
                <li><input type="text" name="serch" placeholder="キーワード"></li>
                <li><button type="submit">検索</button></li>
            </ul>
        </form>
    </div>
</body>

</html>