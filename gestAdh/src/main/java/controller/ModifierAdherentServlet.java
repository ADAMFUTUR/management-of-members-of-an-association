package controller;

import models.Adherent;
import metier.AdherentDAO;
import util.Utilitaire;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/ModifierAdherentServlet")
public class ModifierAdherentServlet extends HttpServlet {

    /**
	 * 
	 */
	Utilitaire u = new Utilitaire();
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession(false);
		if(sess == null || sess.getAttribute("userRole") == null){
			response.sendRedirect("login.jsp");
			return;
		}
        String cni = request.getParameter("cni");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        int tel = Integer.parseInt(request.getParameter("tel"));
        int age = Integer.parseInt(request.getParameter("age"));
        String profession = request.getParameter("profession");

        Connection connection = u.getConnection();
        AdherentDAO adherentDAO = new AdherentDAO();
        Adherent adherent = new Adherent();

        adherent.setCni(cni);
        adherent.setNom(nom);
        adherent.setPrenom(prenom);
        adherent.setEmail(email);
        adherent.setTel(tel);
        adherent.setAge(age);
        adherent.setProfession(profession);

        adherentDAO.updateAdherent(adherent);

        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cni = request.getParameter("cni");

        Connection connection = u.getConnection();
        AdherentDAO adherentDAO = new AdherentDAO();
        Adherent adherent = adherentDAO.getAdherent(cni);

        request.setAttribute("adherent", adherent);
        request.getRequestDispatcher("/EditAdherent.jsp").forward(request, response);
    }
}
