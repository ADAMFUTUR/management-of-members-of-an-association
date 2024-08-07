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
            <ul>
                <li>
                    <a href="#">
                        <span class="icon">
                            <ion-icon name="hand-left-outline"></ion-icon>
                        </span>
                        <span class="title">BeAct</span>
                    </a>
                </li>

                <li>
                    <a href="EditAdherent.jsp">
                        <span class="icon">
                            <ion-icon name="chatbubble-outline"></ion-icon>
                        </span>
                        <span class="title">Informations</span>
                    </a>
                </li>

                <li>
                    <a href="adherent.jsp"> 
                        <span class="icon">
                            <ion-icon name="calendar-outline"></ion-icon>
                        </span>
                        <span class="title">Événements</span>
                    </a>
                </li>

                <li>
                    <a href="logout.jsp">
                        <span class="icon">
                            <ion-icon name="log-out-outline"></ion-icon>
                        </span>
                        <span class="title">Sign Out</span>
                    </a>
                </li>
            </ul>
        </div>

        <!-- ========================= Main ==================== -->
        <div class="main">
            <div class="topbar">
                <div class="toggle">
                    <ion-icon name="menu-outline"></ion-icon>
                </div>

                <div class="search">
                    <label>
                        <input type="text" placeholder="Search here">
                        <ion-icon name="search-outline"></ion-icon>
                    </label>
                </div>

                <div class="user">
                    <img src="assets/imgs/customer01.jpg" alt="">
                </div>
            </div>

            <!-- ======================= Cards ================== -->
            <div class="cardBox">
                <div class="card">
                    <div>
                        <div class="numbers">${nombreEvenements}</div>
                        <div class="cardName">Événements</div>
                    </div>
                    <div class="iconBx">
                        <ion-icon name="calendar-outline"></ion-icon>
                    </div>
                </div>
            </div>

            <!-- ================ Adherent Details Form ================= -->
            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Modifier les informations de l'adhérent</h2>
                    </div>
                    <form action="ModifierAdherentServlet" method="post">
                        <input type="hidden" name="cni" value="${adherent.cni}" />
                        
                        <label for="nom">Nom :</label>
                        <input type="text" id="nom" name="nom" value="${adherent.nom}" required pattern="[A-Za-zÀ-ÖØ-öø-ÿ\s]+" title="Le nom doit uniquement contenir des lettres et des espaces." />

                        <label for="prenom">Prénom :</label>
                        <input type="text" id="prenom" name="prenom" value="${adherent.prenom}" required pattern="[A-Za-zÀ-ÖØ-öø-ÿ\s]+" title="Le prénom doit uniquement contenir des lettres et des espaces." />

                        <label for="email">Email :</label>
                        <input type="email" id="email" name="email" value="${adherent.email}" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Veuillez entrer une adresse email valide." />

                        <label for="tel">Téléphone :</label>
                        <input type="text" id="tel" name="tel" value="${adherent.tel}" required pattern="0[67]\d{8}" title="Le numéro de téléphone doit commencer par 06 ou 07 et contenir exactement 10 chiffres." />

                        <label for="age">Âge :</label>
                        <input type="number" id="age" name="age" value="${adherent.age}" required min="18" max="120" title="Veuillez entrer un âge valide." />

                        <label for="profession">Profession :</label>
                        <input type="text" id="profession" name="profession" value="${adherent.profession}" required pattern="[A-Za-zÀ-ÖØ-öø-ÿ\s]+" title="La profession doit uniquement contenir des lettres et des espaces." />

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
