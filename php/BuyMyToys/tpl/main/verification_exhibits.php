<?php
// // DB
// $name = $_POST['name'];
// $category = $_POST['category'];
// // DB接続して名前で表示
// $image1 = $_FILES['image1'];
// $image2 = $_FILES['image2'];
// $image3 = $_FILES['image3'];
// $price = $_POST['price'];
// $description = $_POST['description'];

// // 表示価格の変更
// $price *= 1.1;
// $price += 500;

// move_uploaded_file($image1['tmp_name'], './images/upload/'.'tpl/'.'image1.jpg');
// move_uploaded_file($image2['tmp_name'], './images/upload/'.'tpl/'.'image2.jpg');
// move_uploaded_file($image3['tmp_name'], './images/upload/'.'tpl/'.'image3.jpg');
?>
<div id="verification_exhibits">
    <h1>出品の確認</h1>
    <form action="./index.php" method="post">
        <input type="hidden" name="name" value="<?php echo $name; ?>">
        <input type="hidden" name="category" value="<?php echo $category; ?>">
        <input type="hidden" name="image1" value="<?php echo $image1; ?>">
        <input type="hidden" name="image2" value="<?php echo $image2; ?>">
        <input type="hidden" name="image3" value="<?php echo $image3; ?>">
        <input type="hidden" name="description" value="<?php echo $description; ?>">
        <input type="hidden" name="price" value="<?php echo $price; ?>">

        <h2>商品名：<?php echo $name; ?></h2>
        <h2>商品画像</h2>
        <ul>
            <li><img src="./images/upload/tpl/image1.jpg"></li>
            <li><img src="./images/upload/tpl/image2.jpg"></li>
            <li><img src="./images/upload/tpl/image3.jpg"></li>
        </ul>

        <h2>商品説明</h2>
            <pre><?php echo $description; ?></pre>
        <h2>出品価格：<?php echo $price; ?>円</h2>

        <button type="submit" value="" name="verification_to_done">出品する</button>
        <button type="submit" value="" name="lineup">やめておく</button>
    </form>
</div>
<!-- <script> print("510"); </script> -->