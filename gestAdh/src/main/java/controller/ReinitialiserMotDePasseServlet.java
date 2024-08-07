package controller;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import models.Adherent;
import metier.AdherentDAO;

public class ReinitialiserMotDePasseServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        String cni = request.getParameter("cni");
        String nouveauMotDePasse = request.getParameter("password");

        // Mettre à jour le mot de passe de l'adhérent dans la base de données
        AdherentDAO adherentDAO = new AdherentDAO();
        Adherent adherent = adherentDAO.getAdherent(cni);

        if (adherent != null) {
            adherent.setPassword(nouveauMotDePasse); // Supposons que vous avez une méthode set pour le mot de passe
            adherentDAO.updateAdherent(adherent);

            // Rediriger vers une page de confirmation ou de connexion
            response.sendRedirect("connexion.jsp");
        } else {
            // Gérer le cas où l'adhérent n'est pas trouvé (optionnel)
            response.getWriter().println("Erreur : Adhérent non trouvé.");
        }
    }
}
