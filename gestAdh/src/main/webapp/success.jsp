<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Adherent, models.Evenement" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscription Réussie</title>
    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="assets/css/style.css">
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
            padding: 30px;
            max-width: 600px;
            width: 100%;
            text-align: center;
            border: 3px solid black;
        }
        h1 {
            color: red;
        }
        h2 {
            color: green;
        }
        p {
            color: black;
        }
        .btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            color: white;
            background-color: black;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-size: 16px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #333;
        }
        .btn-print {
            margin-top: 10px;
            background-color: green;
        }
        .btn-print:hover {
            background-color: darkgreen;
        }
    </style>
    <script>
        function printPage() {
            window.print();
        }
    </script>
</head>

<body>
    <div class="container">
        <h1>Inscription Réussie</h1>
        <h2>Informations de l'Adhérent</h2>
        <p><strong>CNI:</strong> ${adherent.cni}</p>
        <p><strong>Nom:</strong> ${adherent.nom}</p>
        <p><strong>Prénom:</strong> ${adherent.prenom}</p>
        <p><strong>Email:</strong> ${adherent.email}</p>
        <p><strong>Téléphone:</strong> ${adherent.tel}</p>
        <p><strong>Profession:</strong> ${adherent.profession}</p>
        
        <h2>Informations de l'Événement</h2>
        <p><strong>ID:</strong> ${event.id}</p>
        <p><strong>Localisation:</strong> ${event.localisationE}</p>
        <p><strong>Date:</strong> ${event.dateE}</p>
        <p><strong>Heure:</strong> ${event.heure}</p>
        <p><strong>Type:</strong> ${event.type}</p>
        
        <a href="adherent.jsp" class="btn">Retour aux événements</a>
        <button class="btn btn-print" onclick="printPage()">Imprimer</button>
    </div>
</body>

</html>
