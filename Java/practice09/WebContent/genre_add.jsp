<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
    String target = (String)request.getAttribute("TARGET"); 
    String action = (String)request.getAttribute("ACTION");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <title>DVD貸出管理 - <%= target + "の" + action %></title>
</head>
<body>
    <article class="header">
        <h1>DVD貸出管理 - <%= target + "の" + action %></h1>
    </article><!--header-->

    <article class="main">
        <form action="./GenreServlet" method="post">
            <input type="text" name="name" value="" placeholder="ジャンル名">
            <button type="submit"><%= action %></button>
        </form>
    </article><!--main-->

    <article class="footer">
        <p>HAL.inc All rights reserved</p>
    </article><!--footer-->
</body>
</html>