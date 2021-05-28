<?php

// $dsn = 'mysql:dbname=***;host=localhost';
// $user = '***';
// $password = 'PASSWORD';
// $pdo = new PDO($dsn, $user, $password, array(PDO::ATTR_ERRMODE => PDO::ERRMODE_WARNING));

    // -------------------- SQL処理 -------------------- //
    // //SQL文組み立て
    // $sql = $pdo -> prepare("INSERT INTO dinner1 (user_name, menu, comment, image1, image2, image3, date) VALUES (:user_name, :menu, :comment, :image1, :image2, :image3, :date)");

    // // 配列に置き換え文字列を格納
    // $param = array();
    // array_push($param, $user_name);
    // array_push($param, $menu);
    // array_push($param, $comment);
    // array_push($param, $image_path[0]);
    // array_push($param, $image_path[1]);
    // array_push($param, $image_path[2]);
    // array_push($param, $date);

    // // 文字列置き換え
    // $sql -> bindParam(':user_name', $param[0], PDO::PARAM_STR);
    // $sql -> bindParam(':menu', $param[1], PDO::PARAM_STR);
    // $sql -> bindParam(':comment', $param[2], PDO::PARAM_STR);
    // $sql -> bindParam(':image1', $param[3], PDO::PARAM_STR);
    // $sql -> bindParam(':image2', $param[4], PDO::PARAM_STR);
    // $sql -> bindParam(':image3', $param[5], PDO::PARAM_STR);
    // $sql -> bindParam(':date', $param[6], PDO::PARAM_STR);

    // // SQL文実行
    // $sql -> execute();
    // -------------------- SQL処理 -------------------- //

// //データの表示
//     $sql = 'SELECT * FROM dinner1';
// 	$stmt = $pdo->query($sql);
// 	$results = $stmt->fetchAll();
// 	foreach ($results as $row){
// 		echo $row['contribution_id'].',';
//         echo $row['user_name'].',';
//         echo $row['menu'].',';
//         echo $row['comment'].'<br>';
//         echo $row['image1'].',';
//         echo $row['image2'].',';
//         echo $row['image3'].',';
//         echo $row['date'].',';
// 	echo "<hr>";
//     }
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
    <p><img src = "<?= $image_path[0] ?>", height = "250", width = "250"></p>
    <p><img src = "<?= $image_path[1] ?>", height = "250", width = "250"></p>
    <p><img src = "<?= $image_path[2] ?>", height = "250", width = "250"></p>

    
    <form action = "mission_6-2_upload.php" method = "post">
        <button type = "submit" name = "return">入力に戻る</button>
    </form>
</body>
</html>