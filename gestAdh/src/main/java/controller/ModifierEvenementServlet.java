package controller;

import models.Adherent;
import models.Evenement;
import metier.AdherentDAO;
import metier.EvenementDAO;
import util.Utilitaire;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/ModifierEvenementServlet")
public class ModifierEvenementServlet extends HttpServlet {

    /**
	 *
	 */
	Utilitaire u = new Utilitaire();
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		        int id = Integer.parseInt(request.getParameter("id"));
		        String localisationE = request.getParameter("localisationE");
		        int heure = Integer.parseInt(request.getParameter("heure"));

		        Connection connection = u.getConnection();
		        EvenementDAO eventDAO = new EvenementDAO();

		        Evenement event = new Evenement();
		        event.setId(id);
		        event.setLocalisationE(localisationE);
		        event.setHeure(heure);

		        eventDAO.updateEvenement(event);
		        response.sendRedirect("events.jsp");
		    }
		
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Connection connection = u.getConnection();
        EvenementDAO dao = new EvenementDAO();
        Evenement evenement = dao.getEvenement(id);


        request.setAttribute("evenement", evenement);
        request.getRequestDispatcher("/EditEvenement.jsp").forward(request, response);
    }
}
