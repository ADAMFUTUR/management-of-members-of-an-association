<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Envoyer email à l'administrateur</title>
</head>
<body>

    <%
        String email = request.getParameter("email");
        String cni = request.getParameter("cni");
        String tel = request.getParameter("tel");

        // Message préétabli dans le corps de l'e-mail
        String message = "J'ai oublié mon mot de passe. Voici mes informations :\n"
                         + "Email : " + email + "\n"
                         + "Numéro de CNI : " + cni + "\n"
                         + "Téléphone : " + tel + "\n\n"
                         + "Merci de m'aider à réinitialiser mon mot de passe.\n\n"
                         + "Cordialement,\n\n"
                         + "Nom Prénom";

        // Encode le message pour être utilisé dans l'URL
        String encodedMessage = URLEncoder.encode(message, "UTF-8");

        // Création du lien mailto
        String mailtoLink = "mailto:admin@gmail.com"
                            + "?subject=" + URLEncoder.encode("Demande de réinitialisation de mot de passe", "UTF-8")
                            + "&body=" + encodedMessage;
    %>

    <a href="<%= mailtoLink %>">Envoyer email à l'administrateur</a>

</body>
</html>
