package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Inscriptions;
import util.Utilitaire;

public class InscriptionDAO {

    Utilitaire u = new Utilitaire();

    public List<Inscriptions> listeInscription() {
        List<Inscriptions> inscriptions = new ArrayList<>();
        String sql = "SELECT * FROM inscriptions WHERE inscription_id = 2 ";
        System.out.println("Début de l'exécution de la méthode listeInscription");

        try { 
            Connection connexion = u.getConnection();
            System.out.println("Connexion à la base de données réussie");

            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            System.out.println("Préparation de la requête réussie");

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Exécution de la requête réussie");

            while (resultSet.next()) {
                Inscriptions inscription = new Inscriptions();
                inscription.setInscription_id(resultSet.getInt("inscription_id"));
                inscription.setAdherent_id(resultSet.getString("adherent_id"));
                inscription.setEvent_id(resultSet.getInt("event_id"));
                inscriptions.add(inscription);
                System.out.println("Inscription ajoutée : " + inscription);
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Fin de l'exécution de la méthode listeInscription");
        return inscriptions;
    }
}
