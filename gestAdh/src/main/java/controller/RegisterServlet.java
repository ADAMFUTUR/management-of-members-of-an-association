package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.ServiceRegister;
import models.Adherent;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String cni = request.getParameter("cni");
        String email = request.getParameter("email");
        int tel = Integer.parseInt(request.getParameter("tel"));
        String password = request.getParameter("password");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        int age = Integer.parseInt(request.getParameter("age"));
        String profession = request.getParameter("profession");

        // Create an instance of ServiceRegister
        ServiceRegister service = new ServiceRegister();

        // Check if CNI, email, or telephone already exist
        boolean cniExists = service.checkCniExists(cni);
        boolean emailExists = service.checkEmailExists(email);
        boolean telExists = service.checkTelExists(tel);

        if (cniExists || emailExists || telExists) {
            out.println("<html><body>");
            out.println("<script>alert('CNI, email, or telephone number already exists!'); window.location.href='register.jsp';</script>");
            out.println("</body></html>");
            return;
        }

        // Create an instance of Adherent and set the properties
        Adherent adherent = new Adherent();
        adherent.setCni(cni);
        adherent.setEmail(email);
        adherent.setTel(tel);
        adherent.setPassword(password);
        adherent.setNom(nom);
        adherent.setPrenom(prenom);
        adherent.setAge(age);
        adherent.setProfession(profession);
        
        // Insert form data
        service.insertFormData(adherent);

        // Print registration information
        out.println("<html><head><title>Registration Information</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; }");
        out.println("h2 { color: #007bff; text-shadow: 0 0 10px #87ceeb; }");
        out.println("p { color: #333; }");
        out.println("</style>");
        out.println("<script>");
        out.println("setTimeout(function() { window.location.href = 'adherent.jsp'; }, 5000);"); // Redirection après 10 secondes
        out.println("</script>");
        out.println("</head><body>");
        out.println("<h2>Registration Information</h2>");
        out.println("<p><strong>CNI:</strong> " + adherent.getCni() + "</p>");
        out.println("<p><strong>Email:</strong> " + adherent.getEmail() + "</p>");
        out.println("<p><strong>Téléphone:</strong> " + adherent.getTel() + "</p>");
        out.println("<p><strong>Password:</strong> " + adherent.getPassword() + "</p>");
        out.println("<p><strong>Nom:</strong> " + adherent.getNom() + "</p>");
        out.println("<p><strong>Prénom:</strong> " + adherent.getPrenom() + "</p>");
        out.println("<p><strong>Age:</strong> " + adherent.getAge() + "</p>");
        out.println("<p><strong>Profession:</strong> " + adherent.getProfession() + "</p>");
        out.println("<p>Successfully registered! You will be redirected to login page in 5 seconds.</p>");
        out.println("</body></html>");

    }
}
