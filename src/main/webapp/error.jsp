<%@ page isErrorPage="true"%>
<html>
<body bgColor="red">
<h2>Error!</h2>
    <%= exception.getMessage() %> </br>
    <%= exception.getCause() %> </br>
    <%
    System.out.println("--------------------------------");
    exception.printStackTrace();
     %>

</body>
</html>
