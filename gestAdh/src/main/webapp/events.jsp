<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Adherent" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                    <a href="index.jsp">
                        <span class="icon">
                            <ion-icon name="people-outline"></ion-icon>
                        </span>
                        <span class="title">Adhérents</span>
                    </a>
                </li>

                <li>
                    <a href="events.jsp">
                        <span class="icon">
                            <ion-icon name="calendar-outline"></ion-icon>
                        </span>
                        <span class="title">Événements</span>
                    </a>
                </li>

                <li>
                    <a href="ServletLogout">
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
                        <input type="text" id="searchInput" placeholder="Search here" onkeyup="filterTable()">
                        <ion-icon name="search-outline"></ion-icon>
                    </label>
                </div>
            </div>

            <!-- ======================= Cards ================== -->
            <div class="cardBox">
                <div class="card">
                    <div>
                        <div class="numbers"><c:out value="${fn:length(evenements)}"/></div>
                        <div class="cardName">Événements</div>
                    </div>
                    <div class="iconBx">
                        <ion-icon name="calendar-outline"></ion-icon>
                    </div>
                </div>
            </div>

            <!-- ================ Adherent Details List ================= -->
            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Liste des Événements</h2>
                    </div>
                    <!-- Ajouter le bouton "Ajouter événement" -->
                    <div class="cardHeader">
                        <a href="ajouterEvenement.jsp" class="btn" style="background-color: blue; color: white;">Ajouter événement</a>
                    </div>

                    <!-- Formulaire pour afficher les événements -->
                    <form action="ServletEvenementAfficher" method="get">
                        <table border="1" id="eventTable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Localisation</th>
                                    <th>Date</th>
                                    <th>Heure</th>
                                    <th>Type</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="evenement" items="${evenements}">
                                    <tr>
                                        <td>${evenement.id}</td>
                                        <td>${evenement.localisationE}</td>
                                        <td>${evenement.dateE}</td>
                                        <td>${evenement.heure}</td>
                                        <td>${evenement.type}</td>
                                        <td>
                                            <form action="ServletEvenementSupprimer" method="get" onsubmit="return confirm('Voulez-vous vraiment supprimer cet événement ?');" style="display:inline;">
                                                <input type="hidden" name="id" value="${evenement.id}">
                                                <button type="submit">Supprimer</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <button type="submit">Afficher</button>
                    </form>
                </div>
            </div>

            <!-- =========== Scripts =========  -->
            <script src="assets/js/main.js"></script>
            <!-- ====== ionicons ======= -->
            <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
            <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
            <script>
                function filterTable() {
                    var input, filter, table, tr, td, i, j, txtValue;
                    input = document.getElementById("searchInput");
                    filter = input.value.toLowerCase();
                    table = document.getElementById("eventTable");
                    tr = table.getElementsByTagName("tr");

                    for (i = 1; i < tr.length; i++) {
                        tr[i].style.display = "none";
                        td = tr[i].getElementsByTagName("td");
                        for (j = 0; j < td.length; j++) {
                            if (td[j]) {
                                txtValue = td[j].textContent || td[j].innerText;
                                if (txtValue.toLowerCase().indexOf(filter) > -1) {
                                    tr[i].style.display = "";
                                    break;
                                }
                            }
                        }
                    }
                }
                window.onload = function() {
                    // Fonction pour empêcher la navigation en arrière
                    function preventBackNavigation() {
                        window.history.forward();
                    }

                    // Attacher un écouteur d'événement sur le bouton "arrière"
                    window.addEventListener('popstate', preventBackNavigation, false);
                }
            </script>
        </div>
    </div>
</body>

</html>
