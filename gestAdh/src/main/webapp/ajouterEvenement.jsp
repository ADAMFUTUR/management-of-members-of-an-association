<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%
HttpSession sess = request.getSession(false);
if(sess == null || sess.getAttribute("userRole") == null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter Événement</title>
    <!-- ======= Styles ====== -->
    <style>
        body {
            background-color: beige; /* Couleur de fond */
            font-family: Arial, sans-serif;
        }
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .main {
            background-color: white; /* Couleur de fond du formulaire */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
        }
        .cardHeader {
            text-align: center;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 10px;
        }
        input[type="number"],
        input[type="text"],
        input[type="date"] {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-bottom: 10px;
        }
        button[type="submit"] {
            padding: 10px 20px;
            border: none;
            background-color: blue; /* Couleur du bouton */
            color: white;
            border-radius: 5px;
            cursor: pointer;
        }
        button[type="submit"]:hover {
            background-color: darkblue; /* Couleur du bouton au survol */
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="main">
            <div class="cardHeader">
                <h2 style="color: blue;">Ajouter un Nouvel Événement</h2>
            </div>
            <form action="ServletEvenementAjouter" method="post">
                              <label for="heure">heure :</label>
                <input type="number" id="heure" name="heure" min="0" max="23" title = "taper une heure valide" required>
                <label for="localisation">Localisation:</label>
                <input type="text" id="localisation" name="localisation" required>
                
                <label for="date">Date:</label>
                <input type="date" id="date" name="date" required>
                
                <label for="type">Type:</label>
                <input type="text" id="type" name="type" required>
                
                <button type="submit">Ajouter</button>
            </form>
        </div>
    </div>

    <!-- =========== Scripts =========  -->
    <script src="assets/js/main.js"></script>
    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>
