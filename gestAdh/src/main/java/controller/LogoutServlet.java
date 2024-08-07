package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get existing session if it exists

        if (session != null) {
            // Log before invalidating the session
            System.out.println("Logging out user with session ID: " + session.getId());

            session.invalidate(); // Invalidate the session

            // Log after invalidating the session
            System.out.println("Session invalidated successfully.");
        } else {
            // Session doesn't exist
            System.out.println("No session found. Nothing to invalidate.");
        }

        // Redirect to the login page
        response.sendRedirect("login.jsp");
    }
}
