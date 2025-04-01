<%@ page language="java" errorPage="error.jsp"%>
<html>
<title>Index</title>
<body>

<%
    if(session.getAttribute("user") == null){
        System.out.println("'user' attribute IS NULL: Session EXPIRED");
         response.sendRedirect("login.jsp");
    }
%>

<h2>Hello, <%= session.getAttribute("user") %> </h2>

<form action="actor.jsp" method="get">
  <label for="actorID"> Actor ID
   <input id="actorID" name="actorID" type="number">
  </label>
    <input type="submit" value="Click me" >
 </form>

<h2>Upload File(s)</h2>
 <form action="fileupload" method="post" enctype="multipart/form-data">
   <label for="fuid"> File
    <input id="fuid" name="file" type="file" multiple>
   </label>
     <input type="submit" value="Upload" >
  </form>

<form action="logout" method="post">
    <input type="submit" value="Logout" >
 </form>



</body>
</html>
