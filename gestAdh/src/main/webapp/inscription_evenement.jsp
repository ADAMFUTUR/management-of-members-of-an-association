<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscription à un événement</title>
</head>
<body>
    <h2>Inscription à un événement</h2>
    <form action="servlet_gestion_inscriptions" method="post">
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" required><br><br>

        <label for="prenom">Prénom :</label>
        <input type="text" id="prenom" name="prenom" required><br><br>

        <label for="email">Email :</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="evenement">Choisir un événement :</label>
        <select id="evenement" name="evenement">
            <c:forEach items="${evenements}" var="evenement">
                <option value="${evenement.id}">${evenement.nom}</option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="S'inscrire">
    </form>
</body>
</html>
