package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Evenement;
import util.Utilitaire;

public class EvenementDAO {

    Utilitaire u = new Utilitaire();
    public List<Evenement> listeEvenement() {
        List<Evenement> evenements = new ArrayList<>();
        String sql = "SELECT * FROM evenement WHERE dateE > CURDATE()";
        System.out.println("ssssssssssssssss");
        try {
        	Connection connexion = u.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Evenement evenement = new Evenement();
                evenement.setId(resultSet.getInt("id"));
                evenement.setDateE(resultSet.getDate("dateE"));
                evenement.setLocalisationE(resultSet.getString("localisationE"));
                evenement.setType(resultSet.getString("type"));
                evenement.setHeure(resultSet.getInt("heure"));
                evenements.add(evenement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evenements;
    }

    public void supprimerEvenement(int id) {
        String sql = "DELETE FROM evenement WHERE id = ?";
        try { 
        Connection connexion = u.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEvenement(Evenement evenement) {
        String sql = "UPDATE evenement SET localisationE = ?, heure = ?  WHERE id = ?";
        try {
        	Connection connexion = u.getConnection();
        	System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuu");
             PreparedStatement preparedStatement = connexion.prepareStatement(sql);

            preparedStatement.setString(1, evenement.getLocalisationE());
            preparedStatement.setInt(2, evenement.getHeure());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Evenement getEvenement(int id) {
        Evenement evenement = null;
        String sql = "SELECT * FROM evenement WHERE id = ?";
        try {
        	Connection connexion = u.getConnection();
        
             PreparedStatement preparedStatement = connexion.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    evenement = new Evenement();
                    evenement.setId(resultSet.getInt("id"));
                    evenement.setDateE(resultSet.getDate("dateE"));
                    evenement.setLocalisationE(resultSet.getString("localisationE"));
                    evenement.setType(resultSet.getString("type"));
                    evenement.setHeure(resultSet.getInt("heure"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evenement;
    }

    public void ajouterEvenement(Evenement evenement) {
        String sql = "INSERT INTO evenement (localisationE, dateE, type , heure ) VALUES ( ?, ?, ?,?)";
        try {
        	Connection connection = u.getConnection();
        	System.out.println("aaaaaaaaaaaaaaaaaaaaa");
             PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, evenement.getLocalisationE());
            statement.setDate(2, new java.sql.Date(evenement.getDateE().getTime()));
            statement.setString(3, evenement.getType());
            statement.setInt(4,evenement.getHeure());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getNombreTotalEvenements() {
        int nombreEvenements = 0;
        try {
        		Connection connection = u.getConnection();// obtenir une connexion à votre base de données ;
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS total FROM evenement WHERE dateE > CURDATE()");
             ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                nombreEvenements = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        }
        return nombreEvenements;
    }
    
    public boolean checkIdExists(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean exists = false;
        
        try {
            connection = u.getConnection();
            String query = "SELECT COUNT(*) FROM evenement WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        return exists;
    }

}
