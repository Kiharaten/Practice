<?php

$json = file_get_contents("php://input");
$data = json_decode($json);

$file = "../csv/json2-3.csv";
$current = file_get_contents($file);
foreach($data as $key => $value){
	$current .= $key ."=" . $value .",";
}
$current = rtrim($current, ",");
$current .= "\n";
file_put_contents($file, $current);

echo json_encode($data);

?>