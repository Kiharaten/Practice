<?php

$filepath = "../csv/json2-4.csv";

switch ($_SERVER['REQUEST_METHOD']){
	case 'GET':
		$num = $_GET['number'];
		$parameter = $_GET;

		if($num == ""){
			$num = 1;
		}

		if($num < 1 || $num >= 10){
			http_response_code(400);
			echo "範囲外です";
			break;
		}

		$i = 0;
		if(($handle = fopen($filepath,"r")) !== false){
			while(($line = fgetcsv($handle, 0, ",")) !== false){
				if($i < $num){
					foreach ($line as $key => $data) {
						$data_substance = explode("=", $data);
						if($data_substance[0] == 'hobby' || $data_substance[0] == 'food'){
							$data_substance[1] = str_split($data_substance[1]);
						}
						$array[$data_substance[0]] = $data_substance[1];
					}
					$records[] = $array;
					$i++;
				}else{
					break;
				}
			}
			fclose($handle);
		}

		echo json_encode($records);

		break;

	case 'POST':
		$json = file_get_contents("php://input");
		$data = json_decode($json);
		$parameter = $_POST;
		$current = file_get_contents($filepath);
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
		file_put_contents($filepath, $current);

		// $csv = file('./json2-4.csv');
		// $csv_body = array_splice($csv, count($csv)-1);
		// $rec = explode(',',$csv_body[0]);

		// $ary_list = [];
		// $i = 0;
		// while(isset($rec[$i])){
		// 	$rec2 = explode('=',$rec[$i]);
		// 	$ary_list[$rec2[0]] = rtrim($rec2[1]);
		// 	$i++;
		// }

		echo json_encode($data);
		break;

}









?>