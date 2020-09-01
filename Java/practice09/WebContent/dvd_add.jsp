<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,beans.Actors,beans.Genres" %>
<%
    String target = (String)request.getAttribute("TARGET");
    String action = (String)request.getAttribute("ACTION");
    List<Actors> actorlist = (List <Actors>)request.getAttribute("ACTORLIST");
    List<Genres> genrelist = (List <Genres>)request.getAttribute("GENRELIST");
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
        <form action="./DiskServlet" method="post">
            <input type="text" name="name" value="" placeholder="追加するdvdタイトル">
            <select name="genre">
                <option value="unselected">- 選択してください</option>
                <% for(Genres genres : genrelist){ %>
                <option value=<%= genres.getName() %>><%= genres.getName() %></option>
                <%} %>                
            </select>
            <!-- <select name="actor" size="1" multiple> -->
            <select name="actor">
                <option value="unselected">- 選択してください</option>
                <% for(Actors actors : actorlist){ %>
                <option value=<%= actors.getName() %>><%= actors.getName() %></option>
                <%} %>                
            </select>
            <button type="submit"><%= action %></button>
        </form>
    </article><!--main-->

    <article class="footer">
        <p>HAL.inc All rights reserved</p>
    </article><!--footer-->
</body>
</html>