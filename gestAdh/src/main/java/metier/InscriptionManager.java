package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import metier.ServiceLogin.AuthStatus;
import util.Utilitaire; 
import models.Adherent;

public class InscriptionManager {
    public static AuthStatus authenticateAdherent(String cni, String password) {
    	Utilitaire u = new Utilitaire();

        try {
            Connection connection = u.getConnection();
            if (connection != null) {
                // Vérifier les informations d'identification dans la table adherent
                String sql = "SELECT COUNT(*) FROM adherent WHERE cni = ? AND password = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, cni);
                ps.setString(2, password); 
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count > 0) {
                        return AuthStatus.USER;
                    }
                }

                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AuthStatus.INVALID;
    }
    
    public static boolean inscrireAdherentEvenement(String cni, String password, int eventId) {
        AuthStatus authStatus = authenticateAdherent(cni, password);
    	Utilitaire u = new Utilitaire();

        if (authStatus == AuthStatus.USER) {
            try {
                Connection connection = u.getConnection();
                if (connection != null) {
                    String sql = "INSERT INTO inscriptions (adherent_id, event_id) VALUES (?, ?)";
                    System.out.println("fffffffffffffffff");
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setString(1, cni);
                    ps.setInt(2, eventId);
                    
                    int rowsAffected = ps.executeUpdate();
                    ps.close();
                    
                    // Vérifier si l'inscription a été ajoutée avec succès
                    if (rowsAffected > 0) {
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
