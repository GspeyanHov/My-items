<%@ page import="model.Item" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: SmartS
  Date: 16.09.2022
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Items page</title>
</head>
<%
    List<Item> items = (List<Item>) request.getAttribute("item");
%>
<body>
<table border="1">
    <tr>
        <th>id</th>
        <th>title</th>
        <th>price</th>
        <th>category_id</th>
        <th>pic_url</th>
        <th>user_id</th>
    </tr>

    <%
        for (Item item : items) {%>
    <tr>
        <td><%=item.getId()%></td>
         <td><%=item.getTitle()%></td>
         <td><%=item.getPrice()%></td>
         <td><%=item.getCategory().getId()%></td>
         <td><%=item.getPicUrl()%></td>
         <td><%=item.getUser().getId()%></td>
    </tr>

       <% }%>



</table>
</body>
</html>
