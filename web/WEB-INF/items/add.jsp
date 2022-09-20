<%@ page import="model.User" %>
<%@ page import="model.Category" %><%--
  Created by IntelliJ IDEA.
  User: SmartS
  Date: 18.09.2022
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add items</title>
</head>
<%Category category = new Category();%>
<body>
please add your item
<form action="/items/add" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" >
    <input type="text" name="title" placeholder="please input title"/><br>
    <input type="number" name="price" placeholder="please input price"/><br>
    <select name="<%=category.getName()%>">

    </select><br>
    <input type="file" name="pic_url" placeholder="please add photo"/>
    <input type="text" name="title" placeholder="please input title"/>

</form>
</body>
</html>
