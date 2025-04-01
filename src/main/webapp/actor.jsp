<%@ page language="java" errorPage="error.jsp"%>
<%@ page import="java.sql.*"%>
<html>
<title>Actor</title>
<body>

<a style="padding:8px 16px;margin:32px;border:2px;border-radius: 4px;text-decoration: none;background-color: #CA4F10;" href="index.jsp"> BACK </a>
<h2>MY ACTOR</h2>
<%
    if(session.getAttribute("user") == null){
        System.out.println("@Actor.jsp: USER IS NULL: EXPIRED");
         response.sendRedirect("login.jsp");
    }
%>

<%
    try {
        // Secure Driver Loading
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Database Connection with Try-with-Resources
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sakila", "root", "14999");
             PreparedStatement pstmt = conn.prepareStatement(
                "SELECT * FROM actor WHERE actor_id = ?")) {

            // Safely handle potential null or invalid input
            String actorIdParam = request.getParameter("actorID");
            if (actorIdParam != null && !actorIdParam.isEmpty()) {
                // Convert and set parameter safely
                pstmt.setInt(1, Integer.parseInt(actorIdParam));

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
%>
                    Actor ID: <%= rs.getLong("actor_id") %> </br>
                    Actor Name: <%= rs.getString("first_name") %> <%= rs.getString("last_name") %>
<%
                    } else {

                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Actor not found");
%>

<%
                    }
                }
            } else {
%>
                Invalid or missing Actor ID.
<%
            }
        }
    } catch (NumberFormatException e) {
%>
        Invalid Actor ID format.
<%
    } catch (SQLException e) {
%>
        Database error: <%= e.getMessage() %>
<%
    } catch (ClassNotFoundException e) {
%>
        Database driver not found.
<%
    }
%>


<form action="logout" method="post">
    <input type="submit" value="Logout" >
 </form>


</body>
</html>
