<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire d'inscription</title>
    <style>
    
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
           background-image: url('bg.png');
            
        }

form {
    background-color: #f9f9f9; /* Light gray background */
    padding: 20px;
    border-radius: 5px;
    width: 400px; /* Adjusted width */
    margin: 0 auto;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

        label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
        

        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="number"] {
            width: calc(100% - 12px);
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        
        input[type="submit"] {
    background-color: #007bff; /* Blue button */
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 3px;
    cursor: pointer;
}

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error {
            color: red;
        }
        
        h2 {
    color: #007bff; /* Blue color for the text */
    text-shadow: 0 0 10px #87ceeb; /* Sky blue glow effect */
}
        
    </style>
    <script>
        function validateForm() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var age = document.getElementById("age").value;

            if (password !== confirmPassword) {
                alert("Les mots de passe ne correspondent pas.");
                return false;
            }
            
            if (age < 18) {
                alert("Vous devez avoir au moins 18 ans pour vous inscrire.");
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
    <h2 align = "center">Inscription Adherent</h2>
    <form action="RegisterServlet" method="post" onsubmit="return validateForm();">
        <label for="cni">CNI:</label>
        <input type="text" id="cni" name="cni" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        
        <label for="tel">Téléphone:</label>
        <input type="text" id="tel" name="tel" required><br><br>

        <label for="password">Mot de passe:</label>
        <input type="password" id="password" name="password" required><br><br>

        <label for="confirmPassword">Confirmer le mot de passe:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>

        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" required><br><br>

        <label for="prenom">Prénom:</label>
        <input type="text" id="prenom" name="prenom" required><br><br>

        <label for="age">Âge:</label>
        <input type="number" id="age" name="age" required><br><br>

        <label for="profession">Profession:</label>
        <input type="text" id="profession" name="profession" required><br><br>

        <input type="submit" value="S'inscrire">
    </form>
    
</body>
</html>
