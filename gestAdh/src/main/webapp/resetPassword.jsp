<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Réinitialisation de mot de passe</title>
</head>
<body>

    <h2>Formulaire de réinitialisation de mot de passe</h2>

    <form action="envoyer-email-admin.jsp" method="post">
        <label for="email">Email :</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="cni">Numéro de CNI :</label>
        <input type="text" id="cni" name="cni" required><br><br>

        <label for="tel">Téléphone :</label>
        <input type="text" id="tel" name="tel" required><br><br>

        <input type="submit" value="Envoyer email à l'administrateur">
    </form>

</body>
</html>
