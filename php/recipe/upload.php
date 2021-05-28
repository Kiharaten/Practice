<?php
    //$tytle =""

    $dsn = 'mysql:dbname=recipe;;host=localhost';
	$user = 'root';
	$password = '';
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

    // $sql = "CREATE TABLE IF NOT EXISTS user_table"
    // ." ("
    // . "user_id INT AUTO_INCREMENT PRIMARY KEY,"
    // . "user_name char(32),"
    // . "login_password char(32)"
    // .");";
    // $stmt = $pdo->query($sql);

    // $sql = 'SELECT user_id FROM dinner1 ORDER BY user_id DESC LIMIT 1';// 最後の行を取得
    // // $sql->execute();
    // // $stmt = $pdo->prepare($sql);
    // //         $stmt->bindParam(':user_id', $user_id2, PDO::PARAM_INT);
    // //         $stmt->execute();
    // // $sql ->execute();
    // $results = $sql->fetchAll();
    //         foreach ($results as $result) {
	// 	        $image_id = $result["user_id"] + 1;
	//         }

?>




<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

【 投稿 】<br>
    <form action="mission_6-2_function.php" method="post" enctype = "multipart/form-data">
        <ul>
            <li>ユーザー名： 　<input type="text" value = "あやか" name="user_name"></li>
            <li>メニュー： 　<input type="text" value = "オムライス" name="menu"></li>
            <li>コメント：　<input type="text" value = "美味しく作れます" name="comment"></li>
            <li>写真1：　<input type="file" value = "写真1" name="image1"></li>
            <li>写真2：　<input type="file" value = "写真2" name="image2"></li>
            <li>写真3：　<input type="file" value = "写真3" name="image3"></li>
        </ul>
        <input type="submit" name="submit" value = "送信">
    </form>
    <br>
    
</body>
</html>