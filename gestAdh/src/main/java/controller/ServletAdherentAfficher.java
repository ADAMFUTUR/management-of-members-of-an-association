package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.AdherentDAO;
import models.Adherent;

import java.io.IOException;
import java.util.List;

@WebServlet("/ServletAdherentAfficher")
public class ServletAdherentAfficher extends HttpServlet {
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
        HttpSession session = request.getSession(false); // Get existing session if it exists
    	List<Adherent> adherents = dao.listeAdherent();
        int totalAdherents = dao.countAdherents();
        request.setAttribute("adherents", adherents);
        request.setAttribute("totalAdherents", totalAdherents);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    
}
