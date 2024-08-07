package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import models.Inscriptions;
import metier.InscriptionDAO;

@WebServlet("/ServletInscriptionAfficher")
public class ServletInscriptionAfficher extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InscriptionDAO dao;

    public void init() {
        dao = new InscriptionDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession(false);
        if (sess == null || sess.getAttribute("userRole") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Inscriptions> inscriptions = dao.listeInscription();
        request.setAttribute("inscriptions", inscriptions);

        RequestDispatcher dispatcher = request.getRequestDispatcher("inscriptions.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
