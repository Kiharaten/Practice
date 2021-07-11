<?php 
echo $_POST['my_text'];
echo '<hr>';
$value = $_POST['value'];
$json = json_decode($value);
echo $value;
echo '<hr>';
echo var_dump($json);
?>