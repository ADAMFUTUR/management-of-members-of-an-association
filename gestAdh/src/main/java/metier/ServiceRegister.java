package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Adherent;
import util.Utilitaire;

public class ServiceRegister {
    Utilitaire u = new Utilitaire();
	public void insertFormData(Adherent adherent) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    Utilitaire u = new Utilitaire();
        connection = u.getConnection();


	    
	    try {
	        connection = u.getConnection();
	        
	        // Check if CNI, email, or telephone number already exists
	        String checkQuery = "SELECT COUNT(*) FROM adherent WHERE cni = ? OR email = ? OR tel = ?";
	        preparedStatement = connection.prepareStatement(checkQuery);
	        preparedStatement.setString(1, adherent.getCni());
	        preparedStatement.setString(2, adherent.getEmail());
	        preparedStatement.setInt(3, adherent.getTel());
	        ResultSet resultSet = preparedStatement.executeQuery();
	            // Insert the form data if CNI, email, and telephone number are unique
	            String insertQuery = "INSERT INTO adherent (cni, email, tel, password, nom, prenom, age, profession) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	            preparedStatement = connection.prepareStatement(insertQuery);
	            preparedStatement.setString(1, adherent.getCni());
	            preparedStatement.setString(2, adherent.getEmail());
	            preparedStatement.setInt(3, adherent.getTel());
	            preparedStatement.setString(4, adherent.getPassword());
	            preparedStatement.setString(5, adherent.getNom());
	            preparedStatement.setString(6, adherent.getPrenom());
	            preparedStatement.setInt(7, adherent.getAge());
	            preparedStatement.setString(8, adherent.getProfession());
	            preparedStatement.executeUpdate();
	            System.out.println("Form data inserted successfully!");
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Failed to insert form data into the database!");
	    }
	}

    public boolean checkCniExists(String cni) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean exists = false;
        
        try {
            connection = u.getConnection();
            String query = "SELECT COUNT(*) FROM adherent WHERE cni = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cni);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public boolean checkEmailExists(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean exists = false;
        
        try {
            connection = u.getConnection();
            String query = "SELECT COUNT(*) FROM adherent WHERE email = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        return exists;
    }

    public boolean checkTelExists(int tel) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean exists = false;
        
        try {
            connection = u.getConnection();
            String query = "SELECT COUNT(*) FROM adherent WHERE tel = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tel);
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


