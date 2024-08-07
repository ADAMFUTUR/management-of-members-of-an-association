<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Adherent" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>

<%
HttpSession sess = request.getSession(false);
if (sess == null || sess.getAttribute("userRole") == null) {
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
    <title>Gestion des Adhérents</title>
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

        function confirmEventDeletion(eventId) {
            if (confirm("Voulez-vous vraiment supprimer cet événement et toutes les inscriptions associées ?")) {
                window.location.href = 'ServletEventSupprimer?id=' + eventId;
            }
        }

        function checkSession() {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "SessionCheckServlet", true);
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    if (xhr.responseText === "invalid") {
                        window.location.href = "login.jsp";
                    }
                }
            };
            xhr.send();
        }

        // Check session every 5 minutes
        setInterval(checkSession, 300000);

        // Initial session check on page load
        window.onload = checkSession;
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
                        <div class="numbers"><c:out value="${fn:length(adherents)}"/></div>
                        <div class="cardName">Adhérents</div>
                    </div>
                    <div class="iconBx">
                        <ion-icon name="people-outline"></ion-icon>
                    </div>
                </div>
            </div>

            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Liste des Adhérents</h2>
                        <form action="CSVExporter" method="get">
                            <button type="submit" class="blue-button">Exporter liste adherent en CSV</button>
                        </form>
                        <button onclick="window.print()">Print List</button>
                    </div>

                    <form action="ServletAdherentAfficher" method="get">
                        <table border="1" id="adherentTable">
                            <thead>
                                <tr>
                                    <th>CNI</th>
                                    <th>Nom</th>
                                    <th>Prénom</th>
                                    <th>Âge</th>
                                    <th>Email</th>
                                    <th>Telephone</th>
                                    <th>Profession</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${adherents}" var="a">
                                    <tr>
                                        <td>${a.cni}</td>
                                        <td>${a.nom}</td>
                                        <td>${a.prenom}</td>
                                        <td>${a.age}</td>
                                        <td>${a.email}</td>
                                        <td>${a.tel}</td>
                                        <td>${a.profession}</td>
                                        <td>
                                            <a href="#" onclick="confirmDeletion('${a.cni}')" class="btn">Supprimer</a>
                                        </td>
                                        <td>
                                            <a href="ModifierAdherentServlet?cni=${a.cni}" class="btn">Modifier</a>
                                        </td>
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
