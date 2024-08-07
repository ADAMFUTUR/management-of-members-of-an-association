package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.AdherentDAO;

import java.io.IOException;

@WebServlet("/ServletAdherentSupprimer")
public class ServletAdherentSupprimer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdherentDAO dao;

    public void init() {
    	 dao = new AdherentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession sess = request.getSession(false);
    	if(sess == null || sess.getAttribute("userRole") == null){
    		response.sendRedirect("login.jsp");
    		return;
    	}
        String cni = request.getParameter("cni");
        dao.supprimerAdherent(cni);
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
