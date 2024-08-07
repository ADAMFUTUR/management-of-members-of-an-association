package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import models.Adherent;
import util.Utilitaire;

public class AdherentDAO{
    Utilitaire u = new Utilitaire();

    public List<Adherent> listeAdherent() {
        List<Adherent> adherents = new ArrayList<>();
        String sql = "SELECT * FROM adherent";
        try {Connection connexion = u.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Adherent adherent = new Adherent();
                adherent.setCni(resultSet.getString("cni"));
                adherent.setNom(resultSet.getString("nom"));
                adherent.setPrenom(resultSet.getString("prenom"));
                adherent.setEmail(resultSet.getString("email"));
                adherent.setTel(resultSet.getInt("tel"));
                adherent.setAge(resultSet.getInt("age"));
                adherent.setProfession(resultSet.getString("profession"));
                adherents.add(adherent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return adherents;
    }

    public void supprimerAdherent(String cni) {
        String sql = "DELETE FROM adherent WHERE `adherent`.`cni` = ?";
        try { 
        	Connection connexion = u.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql);

            preparedStatement.setString(1, cni);
            int rowsDeleted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateAdherent(Adherent adherent) {
        String sql = "UPDATE adherent SET nom = ?, prenom = ?, email = ?, tel = ?, age = ?, profession = ? WHERE cni = ?";
        try {Connection connexion = u.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql);

            preparedStatement.setString(1, adherent.getNom());
            preparedStatement.setString(2, adherent.getPrenom());
            preparedStatement.setString(3, adherent.getEmail());
            preparedStatement.setInt(4, adherent.getTel());
            preparedStatement.setInt(5, adherent.getAge());
            preparedStatement.setString(6, adherent.getProfession());
            preparedStatement.setString(7, adherent.getCni());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public Adherent getAdherent(String cni) {
        Adherent adherent = null;
        String sql = "SELECT * FROM adherent WHERE cni = ?";
        try {
        	
        	Connection connexion = u.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(sql);

            preparedStatement.setString(1, cni);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    adherent = new Adherent();
                    adherent.setCni(resultSet.getString("cni"));
                    adherent.setNom(resultSet.getString("nom"));
                    adherent.setPrenom(resultSet.getString("prenom"));
                    adherent.setEmail(resultSet.getString("email"));
                    adherent.setTel(resultSet.getInt("tel"));
                    adherent.setAge(resultSet.getInt("age"));
                    adherent.setProfession(resultSet.getString("profession"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adherent;
    }
    
    public int countAdherents() {
        int count = 0;
        try {
        		Connection connection = u.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM adherent");
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
