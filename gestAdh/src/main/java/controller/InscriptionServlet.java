package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.InscriptionManager;
import metier.ServiceLogin;
import metier.ServiceLogin.AuthStatus;
import models.Adherent;
import models.Evenement;
import util.Utilitaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ServiceLogin s = new ServiceLogin();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Récupérer les données du formulaire
        String cni = request.getParameter("cni");
        String password = request.getParameter("password");
        int eventId = Integer.parseInt(request.getParameter("id"));

        // Vérifier les identifiants de l'adhérent
        AuthStatus authStatus = s.authenticate(cni, password);
        if (authStatus == null) {
            session.setAttribute("errorMessage", "Erreur d'authentification. Veuillez vérifier vos informations.");
            response.sendRedirect("error.jsp");
            return;
        }

        // Inscrire l'adhérent à l'événement
        boolean success = InscriptionManager.inscrireAdherentEvenement(cni, password, eventId);
        if (success) {
            try {
                // Récupérer les informations de l'adhérent et de l'événement
                Adherent adherent = getAdherentDetails(cni);
                Evenement event = getEventDetails(eventId);

                // Stocker les informations dans la session
                session.setAttribute("adherent", adherent);
                session.setAttribute("event", event);
                response.sendRedirect("success.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("errorMessage", "Erreur lors de la récupération des informations.");
                response.sendRedirect("error.jsp");
            }
        } else {
            session.setAttribute("errorMessage", "Erreur lors de l'inscription à l'événement.");
            response.sendRedirect("error.jsp");
        }
    }

    private Adherent getAdherentDetails(String cni) throws Exception {
    	Utilitaire u = new Utilitaire();
        Connection connection = u.getConnection();
        String sql = "SELECT * FROM adherent WHERE cni = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, cni);
        ResultSet rs = ps.executeQuery();

        Adherent adherent = new Adherent();
        if (rs.next()) {
            adherent.setCni(rs.getString("cni"));
            adherent.setEmail(rs.getString("email"));
            adherent.setTel(rs.getInt("tel"));
            adherent.setPassword(rs.getString("password"));
            adherent.setNom(rs.getString("nom"));
            adherent.setPrenom(rs.getString("prenom"));
            adherent.setAge(rs.getInt("age"));
            adherent.setProfession(rs.getString("profession"));
        }
        rs.close();
        ps.close();
        return adherent;
    }

    private Evenement getEventDetails(int eventId) throws Exception {
    	Utilitaire u = new Utilitaire();

        Connection connection = u.getConnection();
        String sql = "SELECT * FROM evenement WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, eventId);
        ResultSet rs = ps.executeQuery();

        Evenement event = new Evenement();
        if (rs.next()) {
            event.setId(rs.getInt("id"));
            event.setLocalisationE(rs.getString("localisationE"));
            event.setDateE(rs.getDate("dateE"));
            event.setHeure(rs.getInt("heure"));
            event.setType(rs.getString("type"));
        }
        rs.close();
        ps.close();
        return event;
    }
}
