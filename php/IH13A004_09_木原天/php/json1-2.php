<?php 
echo $_GET['my_text'];
echo '<hr>';
$value = $_GET['value'];
$json = json_decode($value);
echo $value;
echo '<hr>';
echo var_dump($json);
?>