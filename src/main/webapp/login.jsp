<%@ page language="java" errorPage="error.jsp"%>
<%@ page import="java.sql.*"%>
<html>
<title>Login</title>
<body>
<h2>Login</h2>
<form action="validate" method="post">
    <label style="display:block; margin:5px;" for="userIN"> username:<input name="user" type="text" id="userIN" value="ali" required> </label>
    <label style="display:block; margin:5px;" for="passIN"> password:<input name="pass" type="password" id="passIN" value="123" required> </label>
    <input style="display:block; margin:5px;" type="submit" value="login">
</form>
</body>
</html>
