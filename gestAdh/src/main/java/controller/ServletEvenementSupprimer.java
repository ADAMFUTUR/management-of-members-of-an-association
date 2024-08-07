package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.EvenementDAO;

import java.io.IOException;

/**
 * Servlet implementation class ServletEvenementSupprimer
 */@WebServlet("/ServletEvenementSupprimer")
 public class ServletEvenementSupprimer extends HttpServlet {
	 
	    private static final long serialVersionUID = 1L;
	    private EvenementDAO dao;

	    public void init() {
	        dao = new EvenementDAO();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	HttpSession sess = request.getSession(false);
	    	if(sess == null || sess.getAttribute("userRole") == null){
	    		response.sendRedirect("login.jsp");
	    		return;
	    	}
	        int id = Integer.parseInt(request.getParameter("id"));
	        dao.supprimerEvenement(id);
	        response.sendRedirect(request.getContextPath() + "/ServletEvenementAfficher");
	    }
	}
