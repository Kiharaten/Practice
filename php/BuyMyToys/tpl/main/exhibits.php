<div id="exhibits">
  <h1>商品の出品</h1>
  <form action="./index.php" method="post" enctype="multipart/form-data">

    <h2>商品画像を選択</h2>
      <input type="file" name="image1">
      <input type="file" name="image2">
      <input type="file" name="image3">

    <h2>商品名</h2>
    <input type="text" value="プラレール" name="name">

    <h2>商品の説明</h2>
      <textarea rows="4" cols="40" name="description">
息子が幼稚園の時遊んでいたおもちゃです。
キズ、ヨゴレ等あります。
      </textarea>

      <h2>商品の詳細</h2>
        <p>カテゴリー</p>
        <select name="category" size="1">
          <option value="B01">男児キャラクター</option>
          <option value="B01">ミニカー</option>
          <option value="B01">レールトイ</option>
          <option value="B01">トイホビー</option>
          <option value="B01">その他男児玩具関連</option>
          <option value="D31">ぬいぐるみ</option>
          <option value="D32">人形</option>
          <option value="D41">スケールプラモデル</option>
          <option value="D42">キャラクタープラモデル</option>
          <option value="D43">ホビーラジコン</option>
          <option value="D44">鉄道模型</option>
          <option value="D45">ガン</option>
          <option value="D46">クラフトホビー</option>
          <option value="D47">ホビー関連用品</option>
          <option value="D48">その他ホビー</option>
          <option value="G11">きせかえ</option>
          <option value="G12">おままごと</option>
          <option value="G13">女児ホビー</option>
          <option value="G14">女児コレクション</option>
          <option value="G15">女児キャラクター</option>
          <option value="G16">ライフ・ファッション</option>
          <option value="G17">トイドール</option>
          <option value="G18">その他女児玩具関連</option>
          <option value="K21">アクショントイ</option>
          <option value="K22">プレスクール</option>
          <option value="K23">ベビー</option>
          <option value="K24">乗用</option>
          <option value="K25">音楽</option>
          <option value="K26">幼児キャラクター</option>
          <option value="K27">ブロック</option>
          <option value="K28">木製玩具及び積木</option>
          <option value="K29">書籍</option>
          <option value="K30">遊具</option>
          <option value="S51">花火</option>
          <option value="S52">節句</option>
          <option value="S53">夏物</option>
          <option value="S54">小物玩具</option>
          <option value="S55">スポーツ</option>
        </select>

      <h2>配送方法：まさる堂らくらくパック</h2>

      <h2>販売価格</h2>
        <p>商品価格<input type="text" value="100" name="price">円</p>
        <p>※ 手数料 (10%) + 配送料 (500円) が 加算されます</p>

        <button type="submit" value="" name="exhibits_to_verification">かくにんする</button>
        <button type="submit" value="" name="lineup">やめておく</button>
  </form>
  </div>
