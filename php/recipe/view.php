<?php
    // echo $name;
    // echo $menu;
    // echo $comment;
    // echo $image1;
    // echo $image2;
    // echo $image3;

?>



<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1><?= $menu ?></h1>
    <p><?= $comment ?></p>
    <p><?= $user_name ?></p>
    <p><img src = "./image/image1.jpg", height = "250", width = "250"></p>
    <p><img src = "./image/image2.jpg", height = "250", width = "250"></p>
    <p><img src = "./image/image3.jpg", height = "250", width = "250"></p>

    
    <form action = "mission_6-2_upload.php" method = "post">
        <button type = "submit" name = "return">入力に戻る</button>
    </form>
</body>
</html>

<?php

$dsn = 'mysql:dbname=***;host=localhost';
	$user = '***';
	$password = 'PASSWORD';
    $pdo = new PDO($dsn, $user, $password, array(PDO::ATTR_ERRMODE => PDO::ERRMODE_WARNING));

    $sql = "CREATE TABLE IF NOT EXISTS dinner1"
	    ." ("
	    . "contribution_id INT AUTO_INCREMENT PRIMARY KEY,"
        . "user_name char(32),"
        . "menu TEXT," 
        . "comment TEXT,"
        . "image1 TEXT,"
        . "image2 TEXT,"
        . "image3 TEXT,"
        . "date TEXT"
	    .");";
    $stmt = $pdo->query($sql);

//データの表示
    $sql = 'SELECT * FROM dinner1';
	$stmt = $pdo->query($sql);
	$results = $stmt->fetchAll();
	foreach ($results as $row){
		echo $row['contribution_id'].',';
        echo $row['user_name'].',';
        echo $row['menu'].',';
        echo $row['comment'].'<br>';
        echo $row['image1'].',';
        echo $row['image2'].',';
        echo $row['image3'].',';
        echo $row['date'].',';
	echo "<hr>";
    }
?>