<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "java.util.List,beans.Rentals" %>
<%
    String target = (String)request.getAttribute("TARGET");
    String action = (String)request.getAttribute("ACTION");
    List <Rentals> list = (List <Rentals>)request.getAttribute("LIST");
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
       
        <% if(!(list.isEmpty())) { %>
            <table>
                <tr>
                    <th><%= target + "番号" %></th>
                    <th><%= target + "日時" %></th>
                    <th><%= target + "DVD" %></th>
                    <th><%= target + "状態" %></th>
                </tr>
            <% for(Rentals rentals : list){ %>
                <form action="./RentalServlet" method="post">
                    <tr>
                        <input type="hidden" name="id" value=<%= rentals.getId() %>>
                        <input type="hidden" name="method" value="return">
                        <td><%= rentals.getNumber() %></td>
                        <td><%= rentals.getDate() %></td>
                        <td><%= rentals.getDiskName() %></td>
                        <td><%= rentals.getStatusName() %></td>
                        <% if(rentals.getStatusName().equals("貸出中")){ %>
                            <td><button type="submit">返却する</button></td>
                        <%} else {%>
                            <td>返却済</td>
                        <% } %>
                    </tr>
                </form>
            <%} %>
        <% }else{ %>
            <p><%= target + "データが存在しません" %></p>
        <% } %>
            </table>
        </form>
    </article><!--main-->

    <article class="footer">
        <p>HAL.inc All rights reserved</p>
    </article><!--footer-->
</body>
</html>