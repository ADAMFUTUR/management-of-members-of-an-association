package controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.EvenementDAO;
import models.Evenement;

import java.io.IOException;
@WebServlet("/ServletEvenementEditer")
public class ServletEvenementEditer extends HttpServlet {
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
        int id = Integer.parseInt(request.getParameter("id"));
    	System.out.println("ssssssssssssssssssss");

        Evenement evenement = dao.getEvenement(id);
        request.setAttribute("evenement", evenement);
		response.sendRedirect("EditEvenement.jsp");// corrected forwarding method
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Remove the redundant redirection
        doGet(request, response);
    }
}
