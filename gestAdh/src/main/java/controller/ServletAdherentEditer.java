package controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.AdherentDAO;
import models.Adherent;

import java.io.IOException;

@WebServlet("/ServletAdherentEditer")
public class ServletAdherentEditer extends HttpServlet {
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
        response.sendRedirect("ModifierAdherent.jsp");

    	System.out.println("ssssssssssssssssssss");
        String cni = request.getParameter("cni");
        Adherent adherent = dao.getAdherent(cni);
        request.setAttribute("adherent", adherent);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("ModifierAdherent.jsp");

        doGet(request, response);
    }
}
