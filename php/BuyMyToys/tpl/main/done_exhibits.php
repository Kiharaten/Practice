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

// // | product_id          | varchar(7)   | NO   | PRI | NULL    |       |
// // | member_id           | varchar(255) | NO   |     | NULL    |       |
// // | product_name        | varchar(255) | NO   |     | NULL    |       |
// // | product_price       | int(7)       | NO   |     | NULL    |       |
// // | product_category    | varchar(3)   | NO   |     | NULL    |       |
// // | product_description | varchar(255) | NO   |     | NULL    |       |
// // | product_regist_date | date         | NO   |     | NULL    |       |
// // | trade_flg           | int(1)       | NO   |     | NULL    |       |
// // | del_flg             | int(1)       | NO   |     | NULL    |       |

?>
<div id="done_exhibits">
    <h1>出品完了星</h1>
    <ul>
        <li>商品名：<?php echo $name; ?></li>
        <li>商品ID：<?php echo $product_id; ?></li>
        <li>商品カテゴリ：<?php echo $category; ?></li>
        <li>hoge:<img src="./images/upload/<?php echo $product_id; ?>/image1.jpg"></li>
        <li><?php echo $image2; ?></li>
        <li><?php echo $image3; ?></li>
        <li>価格：&yen;<?php echo $price; ?>円</li>
    </ul>
    <form action="./index.php" method="post">
        <button name="lineup">TOPページへ</button>
        <button name="mypage">出品内容の確認</button>
    </form>
</div>