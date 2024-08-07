package metier;


import jakarta.servlet.http.HttpSession;

public class ServiceLogout {

    public void logout(HttpSession session) {
        if (session != null) {
            session.invalidate(); // Invalidate the session to log out the user
        }
    }
}