package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.AdherentDAO;
import models.Adherent;

@WebServlet("/CSVExporter")
public class CSVExporter extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession sess = request.getSession(false);
    	if(sess == null || sess.getAttribute("userRole") == null){
    		response.sendRedirect("login.jsp");
    		return;
    	}
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=adherents.csv");

        List<Adherent> adherents = new AdherentDAO().listeAdherent();

        PrintWriter out = response.getWriter();
        for (Adherent adherent : adherents) {
            out.print(adherent.getCni());
            out.print(",");
            out.print(adherent.getNom());
            out.print(",");
            out.print(adherent.getPrenom());
            out.print(",");
            out.print(adherent.getAge());
            out.print(",");
            out.print(adherent.getEmail());
            out.print(",");
            out.print(adherent.getTel());
            out.print(",");
            out.print(adherent.getProfession());
            out.print("\n");
        }
        out.close();
    }
}