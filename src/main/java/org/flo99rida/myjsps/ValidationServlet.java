package org.flo99rida.myjsps;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/validate")
public class ValidationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();

        if (req.getParameter("user").equals("ali") && req.getParameter("pass").equals("123")) {

            // invalidating the current session to prevent session hijacking
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            session = req.getSession(true);

            pw.println("SESSION: " + session.getId());
            session.setAttribute("user", req.getParameter("user"));
            pw.println(req.getParameter("user"));
            resp.sendRedirect("index.jsp");
        } else {
            resp.sendRedirect("login.jsp");
        }

    }
}
