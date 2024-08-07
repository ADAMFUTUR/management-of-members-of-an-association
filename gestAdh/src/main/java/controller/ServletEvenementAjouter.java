package controller;

import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.Evenement;
import metier.EvenementDAO;

/**
 * Servlet implementation class ServletEvenementAjouter
 */
@WebServlet("/ServletEvenementAjouter")
public class ServletEvenementAjouter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private EvenementDAO dao;

    public void init() {
        dao = new EvenementDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String localisation = request.getParameter("localisation");
        String dateString = request.getParameter("date");
        String type = request.getParameter("type");
        int heure = Integer.parseInt(request.getParameter("heure"));

        Evenement evenement = new Evenement();
        evenement.setLocalisationE(localisation);
        evenement.setType(type);
        evenement.setHeure(heure);
        try {
            // Parse the date string into a Date object
            Date dateE = dateFormat.parse(dateString);
            // Set the date in the Evenement object
            evenement.setDateE(dateE);
        } catch (ParseException e) {
            // Handle parsing error
            e.printStackTrace();
            // Set error response or forward to an error page
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
            return;
        }
       
 	   response.setContentType("text/html");
        // Check if CNI, email, or telephone already exist
       
        dao.ajouterEvenement(evenement);

        response.sendRedirect("events.jsp"); // Redirect to the list of events after adding
    }
}
