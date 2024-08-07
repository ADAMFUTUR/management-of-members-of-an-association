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

@WebServlet("/ServletAdherentModifier")
public class ServletAdherentModifier extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdherentDAO dao;

    public void init() {
        dao = new AdherentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession sess = request.getSession(false);
    	if(sess == null || sess.getAttribute("userRole") == null){
    		response.sendRedirect("login.jsp");
    		return;
    	}
        Adherent adherent = new Adherent();
        adherent.setCni(request.getParameter("cni"));
        adherent.setNom(request.getParameter("nom"));
        adherent.setPrenom(request.getParameter("prenom"));
        adherent.setEmail(request.getParameter("email")); 
        adherent.setTel(Integer.parseInt(request.getParameter("tel")));
        adherent.setAge(Integer.parseInt(request.getParameter("age")));
        adherent.setProfession(request.getParameter("profession"));

        dao.updateAdherent(adherent);

        response.sendRedirect(request.getContextPath() + "/ServletAdherentAfficher");
    }
}
