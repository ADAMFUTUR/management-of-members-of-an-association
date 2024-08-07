<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Adherent" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
HttpSession sess = request.getSession(false);
if(sess == null || sess.getAttribute("userRole") == null){
    response.sendRedirect("login.jsp");
    return;
}
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Responsive Admin Dashboard | Gestion des Adhérents</title>
    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
    <!-- =============== Navigation ================ -->
    <div class="container">
        <div class="navigation">
            <!-- Navigation code here -->
        </div>

        <!-- ========================= Main ==================== -->
        <div class="main">
            <div class="topbar">
                <!-- Topbar code here -->
            </div>

            <!-- ======================= Cards ================== -->
            <div class="cardBox">
                <!-- Cards code here -->
            </div>

            <!-- ================ Adherent Details Form ================= -->
            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Modifier les informations de l'evenement</h2>
                    </div>
                    <form action="ModifierEvenementServlet" method="post">
                        <input type="hidden" name="id" value="${evenement.id}" />
                        
                        <label for="localisationE">Localisation :</label>
                        <input type="text" id="localisationE" name="localisationE" value="${evenement.localisationE}" required  />

                        <label for="heure">Heure :</label>
                        <input type="number" id="heure" name="heure" value="${evenement.heure}" min="0" max="23" required />

                        <button type="submit">Enregistrer</button>
                    </form>
                </div>
            </div>

            <!-- =========== Scripts =========  -->
            <script src="assets/js/main.js"></script>
            <!-- ====== ionicons ======= -->
            <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
            <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        </div>
    </div>
</body>

</html>