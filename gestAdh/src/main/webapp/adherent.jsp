<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Adherent" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des événements</title>
    <!-- ======= Styles ====== -->
    <style>
        /* Custom CSS styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #34495e; /* Dark blue background */
            color: #fff; /* White text color */
        }

        .main {
            padding: 20px;
        }

        .topbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .toggle {
            cursor: pointer;
        }

        .search input[type="text"] {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            width: 200px;
            background-color: #fff; /* Beige input background */
            color: #34495e; /* Dark blue text color */
        }

        .cardBox {
            display: flex;
            justify-content: space-around;
            margin-bottom: 20px;
        }

        .card {
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            background-color: #f5f5f5;
            color: #34495e; /* Dark blue text color */
        }

        .cardName {
            font-weight: bold;
            margin-top: 10px;
        }

        .details {
            margin-top: 20px;
        }

        .recentOrders {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 20px;
            background-color: #f9f9f9;
            color: #34495e; /* Dark blue text color */
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 8px;
            border: 1px solid #ccc;
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #fff; /* White label text color */
        }

        select, input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-bottom: 10px;
            background-color: #fff; /* Beige input background */
            color: #34495e; /* Dark blue text color */
        }

        .btn {
            width: auto;
            padding: 10px 20px;
            margin-top: 10px;
        }
    </style>
</head>

<body>
   
        <!-- ========================= Main ==================== -->
        <div class="main">
            <div class="topbar">
                <div class="toggle">
                    <ion-icon name="menu-outline"></ion-icon>
                </div>

                <div class="search">
                    <label>
                        <input type="text" id="searchInput" placeholder="Search here" oninput="filterTable()"> <!-- Ajout de l'ID et de l'événement oninput -->
                        <ion-icon name="search-outline"></ion-icon>
                    </label>
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

            <!-- ================ Adherent Details List ================= -->
            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Liste des Événements</h2>
                    </div>
                    <!-- Formulaire pour afficher les événements -->
                    <form action="ServletEvenementAfficherAdh" method="get">
                        <table id="eventTable"> <!-- Ajout de l'ID pour le tableau -->
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
                                <c:forEach  var="a" items="${evenements}">
                                    <tr>
                                        <td>${a.id}</td>
                                        <td>${a.localisationE}</td>
                                        <td>${a.dateE}</td>
                                        <td>${a.heure}</td>
                                    	<td>${a.type}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <button type="submit">Afficher</button>
                    </form>
                </div>
                <!-- Form for event registration -->
                <form action="InscriptionServlet" method="post">
                    <label for="id">Événement :</label>
                    <select id="id" name="id" required>
                        <option value="">Sélectionner un événement</option>
                        <c:forEach var="event" items="${evenements}">
                            <option value="${event.id}">${event.id}</option>
                        </c:forEach>
                    </select><br/>
                    
                    <label for="cni">CNI :</label>
                    <input type="text" id="cni" name="cni" required><br/>
                    
                    <label for="password">Password :</label>
                    <input type="password" id="password" name="password" required>

                    <!-- Other form fields if necessary -->

                    <button type="submit" class="btn">S'inscrire</button>
                </form>
            </div>

            <!-- =========== Scripts =========  -->
            <script src="assets/js/main.js"></script>
            <!-- ====== ionicons ======= -->
            <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
            <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

            <!-- JavaScript function for table filtering -->
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
                    // Function to prevent back navigation
                    function preventBackNavigation() {
                        window.history.forward();
                    }

                    // Attach event listener to the "back" button
                    window.addEventListener('popstate', preventBackNavigation, false);
                }
            </script>
        </div>
    </div>
</body>

</html>
                        
