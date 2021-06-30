<div id="details">

  <h1>タイトル：<?php echo $product["title"]; ?></h1>
  <h2><img src=<?php echo $product["image"]; ?>></h2>

  <ul>
    <li>出品者名：<?php echo $product["member_name"] ?></li>
    <li>カテゴリ：<?php echo $product["category"]; ?></li>
    <li>配送方法：まさる堂らくらくパック</li>
    <li>商品説明：<pre><?php echo $product["description"]; ?><pre></li>
    <li>&yen;<?php echo $product["price"]; ?>(税込)</li>
  </ul>

  <form action="./index.php" method="post">
    <button type="submit" value="<?php echo $product_id ?>" name="product_to_verification">こうにゅう画面へ！</button>
    <button type="submit" id="submit" name="lineup">ちがうおもちゃをみる</button>
  </form>


    <?php foreach ($row as $reco) : ?>
        <article>
          <form action="./index.php" method="post">
          <button name="product_detail">
            <h2><img src="<?php echo $reco['img']; ?>" alt="商品"></h2>
            <p class="price">&yen;<?php echo $reco['price']; ?></p>
            <p class="about">
              <?php echo $reco['intro']; ?>
            </p>
          </button>
        </form>
        </article>
    <?php endforeach; ?>
</div>