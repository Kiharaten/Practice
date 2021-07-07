<div id="verification_buying">
  <h1>タイトル：<?php echo $product["title"]; ?></h1>
  <h2><img src=<?php echo $product["image"]; ?>></h2>

  <ul>
    <li>出品者名：<?php echo $product["member_name"] ?></li>
    <li>&yen;<?php echo $product["price"]; ?>(税込)</li>
  </ul>

  <form action="./index.php" method="post">
    <button type="submit" value="<?php echo $product_id ?>" name="verification_to_done_buying">おねだりする！</button>
    <button type="submit" name="lineup">やめておく</button>
  <form>
</div>
