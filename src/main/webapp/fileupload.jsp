<%@ page language="java" errorPage="error.jsp"%>
<%@ page import="java.util.List"%>

<html>
<title>Login</title>
<body>
<h2>File Upload</h2>

<%-- Show results --%>
<% if (request.getAttribute("success") != null) { %>
    <div style="color: green">
        <h3>Successful Uploads:</h3>
        <ul>
            <% for (String msg : (List<String>) request.getAttribute("success")) { %>
                <li><%= msg %></li>
            <% } %>
        </ul>
    </div>
<% } %>

<% if (request.getAttribute("error") != null) { %>
    <div style="color: red">
        <h3>Errors:</h3>
        <ul>
            <% for (String err : (List<String>) request.getAttribute("error")) { %>
                <li><%= err %></li>
            <% } %>
        </ul>
    </div>
<% } %>
</body>
</html>
