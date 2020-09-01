<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "java.util.List,beans.Friends" %>
<%
    String target = (String)request.getAttribute("TARGET");
    String action = (String)request.getAttribute("ACTION");
    List <Friends> list = (List <Friends>)request.getAttribute("LIST");
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
        <% if(!(list.isEmpty())){ %>
            <table>
                <tr>
                    <th><%= target + "ID" %></th>
                    <th><%= target + "名" %></th>
                    <th><%= target + "メールアドレス" %></th>
                </tr>
            <% for(Friends friends : list){ %>
                <tr>
                    <td><%= friends.getId() %></td>
                    <td><%= friends.getName() %></td>
                    <td><%= friends.getMail() %></td>
                </tr>
            <%} %>
        <% }else{ %>
            <p><%= target + "データが存在しません" %></p>
        <% } %>
            </table>
    </article><!--main-->

    <article class="footer">
        <p>HAL.inc All rights reserved</p>
    </article><!--footer-->
</body>
</html>