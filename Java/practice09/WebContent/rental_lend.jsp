<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,beans.Friends,beans.Disks" %>
<%
    String target = (String)request.getAttribute("TARGET");
    String action = (String)request.getAttribute("ACTION");
    List<Friends> friendlist = (List <Friends>)request.getAttribute("FRIENDLIST");
    List<Disks> disklist = (List <Disks>)request.getAttribute("DISKLIST");
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
        <form action="./RentalServlet" method="post">
            <input type="hidden" name="method" value="lend">
            <select name="friend">
                <option value="unselected">- 貸出相手を選択してください</option>
                <% for(Friends friends : friendlist){ %>
                <option value=<%='"' + friends.getName() + '"' %>><%= friends.getName() %></option>
                <%} %>                
            </select>
            <br><br>
            <select name="disk1">
                <option value="unselected">- 選択してください</option>
                <% for(Disks disks : disklist){ %>
                <option value=<%='"' + disks.getName() + '"'%>><%= disks.getName() %></option>
                <%} %>                
            </select>
            <br>
            <select name="disk2">
                <option value="unselected">- 選択してください</option>
                <% for(Disks disks : disklist){ %>
                <option value=<%='"' + disks.getName() + '"'%>><%= disks.getName() %></option>
                <%} %>                
            </select>
            <br>
            <select name="disk3">
                <option value="unselected">- 選択してください</option>
                <% for(Disks disks : disklist){ %>
                <option value=<%='"' + disks.getName() + '"'%>><%= disks.getName() %></option>
                <%} %>                
            </select>
            <br>
            <select name="disk4">
                <option value="unselected">- 選択してください</option>
                <% for(Disks disks : disklist){ %>
                <option value=<%='"' + disks.getName() + '"'%>><%= disks.getName() %></option>
                <%} %>                
            </select>
            <br>
            <select name="disk5">
                <option value="unselected">- 選択してください</option>
                <% for(Disks disks : disklist){ %>
                <option value=<%='"' + disks.getName() + '"'%>><%= disks.getName() %></option>
                <%} %>                
            </select>
            <br>
            <button type="submit"><%= action %></button>
        </form>
    </article><!--main-->

    <article class="footer">
        <p>HAL.inc All rights reserved</p>
    </article><!--footer-->
</body>
</html>