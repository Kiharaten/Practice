<div id="mypage">
  <div id="profile_area">
    <p id="profile_image"><img src="./images/user_icon/no_<?php echo $user_data['member_key']; ?>/user_profile.jpg"></p>
    <p id="user_name"><?php echo $user_data['member_name']; ?></p>
    <form action="./index.php" method="post">
      <a href="<?php echo BASE_URL; ?>index.php?address_info=<?php echo $member_id; ?>" value="<?php echo $member_id; ?>">住所情報</a>
      <a href="<?php echo BASE_URL; ?>index.php?bank_info=<?php echo $member_id; ?>">銀行口座情報</a>
      <button type="submit" name="logout">ログアウト</button>
    </form>
  </div>
</div>