<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Réinitialisation de Mot de Passe</title>
</head>
<body>
    <h2>Réinitialisation de Mot de Passe</h2>
    
    <% 
    String cni = request.getParameter("cni");
    if (cni != null) { %>
        <form action="ReinitialiserMotDePasseServlet" method="post">
            <input type="hidden" name="cni" value="<%= cni %>">
            
            <label for="password">Nouveau Mot de Passe :</label>
            <input type="password" id="password" name="password" required><br><br>
            
            <input type="submit" value="Réinitialiser Mot de Passe">
        </form>
    <% } else { %>
        <p>Paramètre CNI manquant.</p>
    <% } %>
</body>
</html>
