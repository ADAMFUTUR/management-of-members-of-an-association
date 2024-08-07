<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscriptions</title>
    <link rel="stylesheet" href="assets/css/style.css">
    <style type="text/css">
        @media print {
            .navigation,
            .topbar,
            .search,
            .user {
                display: none;
            }
            body {
                font-size: 12pt;
            }
            button,
            a.btn {
                display: none;
            }
        }
    </style>
    <script>
        function confirmDeletion(cni) {
            if (confirm("Voulez-vous vraiment supprimer cet adhérent ?")) {
                window.location.href = 'ServletAdherentSupprimer?cni=' + cni;
            }
        }

        window.onhashchange = function() {
            if (window.location.pathname !== '/login.jsp') {
                window.location.href = 'login.jsp';
            }
        };
    </script>
</head>
<body>
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
                    <a href="inscriptions.jsp">
                        <span class="icon">
                            <ion-icon name="buble-outline"></ion-icon>
                        </span>
                        <span class="title">Inscriptions</span>
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
            <div class="cardBox">
                <div class="card">
                    <div>
                        <div class="numbers"><c:out value="${fn:length(inscriptions)}"/></div>
                        <div class="cardName">Inscriptions</div>
                    </div>
                    <div class="iconBx">
                        <ion-icon name="people-outline"></ion-icon>
                    </div>
                </div>
            </div>
            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Liste des inscriptions</h2>
                    </div>
                    <form action="ServletInscriptionAfficher" method="get">
                        <table border="1" id="adherentTable">
                            <thead>
                                <tr>
                                    <th>num d'inscription</th>
                                    <th>CNI Adherent</th>
                                    <th>id Evenement</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${inscriptions}" var="a">
                                    <tr>
                                        <td>${a.inscription_id}</td>
                                        <td>${a.adherent_id}</td>
                                        <td>${a.event_id}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <button type="submit" class="blue-button">Afficher</button>
                    </form>
                </div>
            </div>
            <script src="assets/js/main.js"></script>
            <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
            <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
            <script>
                function filterTable() {
                    var input, filter, table, tr, td, i, j, txtValue;
                    input = document.getElementById("searchInput");
                    filter = input.value.toLowerCase();
                    table = document.getElementById("adherentTable");
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
            </script>
        </div>
    </div>
</body>
</html>
