package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.Utilitaire;

public class ServiceLogin {
    Utilitaire u = new Utilitaire();

    public enum AuthStatus {
        ADMIN, USER, INVALID
    }

    public AuthStatus authenticate(String cni, String password) {
        Connection connection = u.getConnection();

        try {

                String sqlAdmin = "SELECT COUNT(*) FROM adminsup WHERE cni = ? AND password = ?";
                System.out.print("aaaaaaaaaaaaaaaaaaa");
                PreparedStatement psAdminAuth = connection.prepareStatement(sqlAdmin);
                psAdminAuth.setString(1, cni);
                psAdminAuth.setString(2, password);
                ResultSet rsAdminAuth = psAdminAuth.executeQuery();

                if (rsAdminAuth.next()) {
                    int countAdminAuth = rsAdminAuth.getInt(1);
                    if (countAdminAuth > 0) {
                        return AuthStatus.ADMIN;
                    }
                }

                psAdminAuth.close();            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return AuthStatus.INVALID;
    }
}
