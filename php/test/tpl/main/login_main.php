<section id="main">

<p class='show'>
<h1 class='show'>ログイン画面的な？</h1>
<form action="" method="post" class='show' name="form1">
    <ul>
        <li><input type="text" name="text1" placeholder="name" class='show' onBlur="checkInput();" onFocus="rmClass();"></li>
        <li><input type="password" name="text2" placeholder="password" class='show' onBlur="checkInput();" onFocus="rmClass();"></li>
        <li><button type="submit" value="" id='button' class='show' onclick="return checkForm();">SIGN IN</button></li>
    </ul>
</form>
<script>
    $(".show").hide();
    $(".show").delay(300).fadeIn();
</script>

</section>

