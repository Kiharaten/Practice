<?php

$json = file_get_contents("php://input");
$data = json_decode($json);

$file = "./json3-1.csv";
$current = file_get_contents($file);
foreach ($data as $key => $value) {
	if(is_array($value)){
		$string = "";
		foreach ($value as $details) {
			$string .= $details;
		}
		$current .= $key ."=". $string .",";
	}else{
		$current .= $key ."=". $value .",";
	}
}
$current = rtrim($current, ",");
$current .= "\n";
file_put_contents($file, $current);

/*csvファイルを読み込んで連想配列化*/
$csv = file('./json3-1.csv');
$csv_body = array_splice($csv, count($csv)-1);
$rec = explode(',',$csv_body[0]);

$ary_list = [];
$i = 0;
while(isset($rec[$i])){
	$rec2 = explode('=',$rec[$i]);
	$ary_list[$rec2[0]] = rtrim($rec2[1]);
	$i++;
}

echo json_encode($ary_list);

?>