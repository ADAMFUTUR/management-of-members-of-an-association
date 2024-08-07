
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="style.css">
    <link rel="image" href="logo1.png" type="image/png">
    <title>B.A</title>
</head>
<body>
<header>
    <img class="logo" src="logo1.png" alt="logo" width="50px" height="50px">
    <a href="#" class="logo">BeAct</a>
    <nav class="navigation">
        <a href="#services">Our values</a>
        <a href="#projects">Login</a>
        <a href="#contact">Contact</a>
    </nav>
</header>
<section class="main">
    <div>
        <h2>Hello, we are BeAct<br><span>We Make the World a Better Place</span></h2>
        <h3 style="color: black;">You are not alone</h3>
        <a href="#projects" class="main-btn">Connect Now</a>
        <div class="social-icons">
            <a href="#"><i class="fab fa-linkedin"></i></a>
            <a href="#"><i class="fab fa-twitter"></i></a>
            <a href="#"><i class="fab fa-instagram"></i></a>
            <a href="#"><i class="fab fa-youtube"></i></a>
        </div>
    </div>
</section>

<section class="cards" id="services">
    <h2 class="title">Our values</h2>
    <div class="content">
        <div class="card">
            <div class="icon">
                <img src="football.png" alt="football" width="100px" height="100px">
            </div>
            <div class="info">
                <h3>Be Curious</h3>
                <p>Always learning and exploring new ideas</p>
            </div>
        </div>
        <div class="card">
            <div class="icon">
                <img src="ball-of-basketball.png" width="100px" height="100px">
            </div>
            <div class="info">
                <h3>Be Courage</h3>
                <p>Courage is not in absence of fear, but acting despite being afraid</p>
            </div>
        </div>
    </div>
</section>
<br/>
<section class="projects" id="projects">
    <div class="content">
        <div class="wrapper">
            <div class="card-switch">
                <label class="switch">
                    <div class="flip-card__inner">
                        <div class="flip-card__front">
                            <div class="title">Log in</div>
                            <form class="flip-card__form" action="ServletLogin" method="post">
                                <input class="flip-card__input" name="cni" placeholder="CNI" type="text">
                                <input class="flip-card__input" name="password" placeholder="Password" type="password">
                                <button class="flip-card__btn">Let's go!</button>
                            </form>
                        </div>
                      </div>
                </label> 
            </div>   
        </div>
    </div>
</section>
<br><br>

<section class="cards contact" id="contact">
    <br/><h2 class="title">Let's work together</h2>
    <div class="content">
        <div class="card">
            <div class="icon">
                <i class="fas fa-phone"></i>
            </div>
            <div class="info">
                <h3>Phone</h3>
                <p>+212612345678</p>
            </div>
        </div>
        <div class="card">
            <div class="icon">
                <i class="fas fa-envelope"></i>
            </div>
            <div class="info">
                <h3>Email</h3>
                <p>adam@gmail.com</p>
            </div>
        </div>
    </div>
</section>

<footer class="footer">
    <p class="footer-title">Copyrights @ <span>Imloul adam</span></p>
    <p class="footer-title">Email <span>iml@gmail.com</span></p>
    <p class="footer-title">Phone <span>+212612345678</span></p>
    <div class="social-icons">
        <a href="#"><i class="fab fa-linkedin"></i></a>
        <a href="#"><i class="fab fa-twitter"></i></a>
        <a href="#"><i class="fab fa-instagram"></i></a>
        <a href="#"><i class="fab fa-youtube"></i></a>
    </div>
</footer>

<script>

function validateForm() {
    var age = document.getElementById('age').value;
    var password = document.getElementById('password').value;
    var passwordCnf = document.getElementById('passwordCnf').value;

    if (age < 18) {
        alert('You must be at least 18 years old to sign up.');
        return false;
    }

    if (password !== passwordCnf) {
        alert('Passwords do not match.');
        return false;
    }
    return true;
}

// Function to stop the loader and hide it
function stopLoader() {
    const loader = document.querySelector('.loader');
    loader.style.display = 'none';
}

// Add an event listener to the window load event
window.addEventListener('load', stopLoader);
window.onhashchange = function() {
	  if (window.location.pathname!== '/login.jsp') {
	    window.location.href = 'login.jsp';
	  }
	};

}</script>

</body>
</html>
