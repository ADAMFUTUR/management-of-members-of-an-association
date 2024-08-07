package controller;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.ServiceLogin;
import metier.ServiceLogin.AuthStatus;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServiceLogin serviceLogin = new ServiceLogin();

    @FunctionalInterface
    interface AuthAction {
        void execute(HttpServletRequest request, HttpServletResponse response, String cni) throws IOException, ServletException;
    }

    private Map<AuthStatus, AuthAction> authActions = new EnumMap<>(AuthStatus.class);

    public ServletLogin() {
        authActions.put(AuthStatus.ADMIN, (request, response, cni) -> {
            HttpSession session = request.getSession(true);
            session.setAttribute("userRole", "ADMIN");
            session.setAttribute("userCNI", cni);
            response.sendRedirect("index.jsp");
        });
        authActions.put(AuthStatus.INVALID, (request, response, cni) -> {
            HttpSession session = request.getSession(true);
            session.setAttribute("errorMessage", "Invalid login credentials");
            response.sendRedirect("error.jsp");
        });
        // Add other statuses as needed
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cni = request.getParameter("cni");
        String password = request.getParameter("password");
        AuthStatus authStatus = serviceLogin.authenticate(cni, password);

        AuthAction action = authActions.getOrDefault(authStatus, (req, res, userCni) -> {
            HttpSession session = req.getSession();
            session.setAttribute("errorMessage", "Invalid login credentials");
            res.sendRedirect("error.jsp");
        });
        
        action.execute(request, response, cni);

        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(24*60*60);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
