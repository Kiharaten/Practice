<?php
    //echo "OK";

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


    if(!isset($_POST["user_name"])){
        echo "post not found";
    }
    else{
        $user_name = $_POST["user_name"];
        $menu = $_POST["menu"];
        $comment = $_POST["comment"];
        // $image1 = $_POST["image1"];
        // $image2 = $_POST["image2"];
        // $image3 = $_POST["image3"];

        // echo $name;
        // echo $menu;
        // echo $comment;
        // echo $image1;
        // echo $image2;
        // echo $image3;

    }

    if(!isset($_FILES["image1"])){
        echo "image1 not found";
        require_once("./mission_6-2_view.php");
    }
    if(!isset($_FILES["image2"])){
        echo "image2 not found";
        require_once("./mission_6-2_view.php");
    }
    if(!isset($_FILES["image3"])){
        echo "image3 not found";
        require_once("./mission_6-2_view.php");
    }
    
    echo "image OK";

    $image1 = $_FILES["image1"];
    $image2 = $_FILES["image2"];       
    $image3 = $_FILES["image3"];

    $date = date('Y/m/d h:i:s');
    
    $sql = $pdo -> prepare("INSERT INTO dinner1 (user_name, menu, comment, image1, image2, image3, date) VALUES (:user_name, :menu, :comment, :image1, :image2, :image3, date)");//データ入力
    $sql -> bindParam(':user_name', $user_name2, PDO::PARAM_STR);
    $sql -> bindParam(':menu', $menu2, PDO::PARAM_STR);
    $sql -> bindParam(':comment', $comment2, PDO::PARAM_STR);
    $sql -> bindParam(':image1', $image12, PDO::PARAM_STR);
    $sql -> bindParam(':image2', $image22, PDO::PARAM_STR);
    $sql -> bindParam(':image3', $image32, PDO::PARAM_STR);
    $sql -> bindParam(':date', $date2, PDO::PARAM_STR);
    $user_name2 = $user_name;
    $menu2 = $menu;
    $comment2 = $comment;
    $image12 = $image1;
    $image22 = $image2;
    $image32 = $image3;
    $date2 = $date;
    $sql -> execute();
    echo "<hr>";

       // mkdir("./image/", 0777);//フォルダの作成 

        move_uploaded_file($image1["tmp_name"], "./image/image1.jpg");
        move_uploaded_file($image2["tmp_name"], "./image/image2.jpg");
        move_uploaded_file($image3["tmp_name"], "./image/image3.jpg");
        
        require_once("./mission_6-2_view.php");

    