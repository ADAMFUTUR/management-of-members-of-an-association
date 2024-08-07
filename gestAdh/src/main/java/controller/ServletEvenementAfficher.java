package controller;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import metier.EvenementDAO;
import models.Evenement;

import java.util.List;


	@WebServlet("/ServletEvenementAfficher")
	public class ServletEvenementAfficher extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private EvenementDAO dao;

	    public void init() {
	        dao = new EvenementDAO();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	    	HttpSession sess = request.getSession(false);
	    	if(sess == null || sess.getAttribute("userRole") == null){
	    		response.sendRedirect("login.jsp");
	    		return;
	    	}
	    	List<Evenement> evenements = dao.listeEvenement();
	        request.setAttribute("evenements", evenements);
	        int nombreEvenements = dao.getNombreTotalEvenements();
	        request.setAttribute("nombreEvenements", nombreEvenements);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("events.jsp");
	        dispatcher.forward(request, response);
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    
	}

