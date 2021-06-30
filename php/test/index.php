<?php
session_start();
// ################### 前処理ファイル呼び出し ##########################
//関数ファイル呼び出し
require_once "./functions/function.php";
//configファイル呼び出し
// require_once "../config.php";

// $header, $body, $footerの初期値設定

$header = './tpl/header/header.php';
$main = './tpl/main/toppage.php';
$footer = './tpl/footer/footer.php';

// $header = './tpl/header/login_header.php';
// $main = './tpl/main/login_main.php';
// $footer = './tpl/footer/login_footer.php';

// var_dump($_POST);

// ################### ボタンアクションの例 ##########################
if (isset($_POST['button_action'])) {
    // POST の値があるときこの分岐に入ります

    $main = function_name($_POST['button_action']);
    // POST の値を処理の関数に渡し、その戻り値を受け取ります
    // データベースアクセスなどは関数の中で行ってください

    $main = './tpl/main/button_action.php';
    // 処理後表示したいphpファイルを指定します
}

if (isset($_POST['to_login'])) {
    // echo("// ----- you call to_login ----- //");
    // POST の値があるときこの分岐に入ります

    // $main = function_name($_POST['button_action']);
    // この処理の場合は関数は必要ないです。

    $header = './tpl/header/login_header.php';
    $main = './tpl/main/login_main.php';
    $footer = './tpl/footer/login_footer.php';
    // 処理後表示したいphpファイルを指定します
}


if (isset($_POST['hogehoge1'])) {
    // echo("// ----- you call hogehoge1 ----- //");

    $header = './tpl/header/header.php';
    $main = './tpl/main/hogehoge1.php';
    $footer = './tpl/footer/footer.php';
}

if (isset($_POST['hogehoge2'])) {
    // echo("// ----- you call hogehoge2 ----- //");

    $header = './tpl/header/header.php';
    $main = './tpl/main/hogehoge2.php';
    $footer = './tpl/footer/footer.php';
}

if (isset($_POST['hogehoge3'])) {
    // echo("// ----- you call hogehoge3 ----- //");

    $header = './tpl/header/header.php';
    $main = './tpl/main/hogehoge3.php';
    $footer = './tpl/footer/footer.php';
}

if (isset($_POST['hogehoge4'])) {
    // echo("// ----- you call hogehoge4 ----- //");

    $header = './tpl/header/header.php';
    $main = './tpl/main/hogehoge4.php';
    $footer = './tpl/footer/footer.php';
}

if (isset($_POST['hogehoge5'])) {
    // echo("// ----- you call hogehoge5 ----- //");

    $header = './tpl/header/header.php';
    $main = './tpl/main/hogehoge5.php';
    $footer = './tpl/footer/footer.php';
}

if (isset($_POST['hogehoge6'])) {
    // echo("// ----- you call hogehoge6 ----- //");

    $header = './tpl/header/header.php';
    $main = './tpl/main/hogehoge6.php';
    $footer = './tpl/footer/footer.php';
}


if (isset($_POST['hogehoge7'])) {
    // echo("// ----- you call hogehoge7 ----- //");

    $header = './tpl/header/header.php';
    $main = './tpl/main/hogehoge7.php';
    $footer = './tpl/footer/footer.php';
}


if (isset($_POST['hogehoge8'])) {
    // echo("// ----- you call hogehoge8 ----- //");

    $header = './tpl/header/header.php';
    $main = './tpl/main/hogehoge8.php';
    $footer = './tpl/footer/footer.php';
}

// ################### 後処理ファイル呼び出し ##########################
require_once $header;
require_once $main;
require_once $footer;
// ここで変数内のページを読み込みます