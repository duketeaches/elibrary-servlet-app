<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%-- <base href="${pageContext.request.contextPath}/" /> --%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/style3.css" />
<link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.header {
	overflow: hidden;
	background-color: #f1f1f1;
	padding: 20px 10px;
}

.header a {
	float: left;
	color: black;
	text-align: center;
	padding: 12px;
	text-decoration: none;
	font-size: 18px;
	line-height: 25px;
	border-radius: 4px;
}

.header a.logo {
	font-size: 25px;
	font-weight: bold;
}

.header a:hover {
	background-color: #ddd;
	color: black;
}

.header a.active {
	background-color: dodgerblue;
	color: white;
}

#loginButton {
	font-size: 25px;
	font-weight: bold;
}

#loginButton:hover {
	background-color: #ddd;
	color: black;
}

#loginButton.active {
	background-color: dodgerblue;
	color: white;
}

.header-right {
	float: right;
}

@media screen and (max-width: 500px) {
	.header a {
		float: none;
		display: block;
		text-align: left;
	}
	.header-right {
		float: none;
	}
}
/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
	background-color: #F7F7F7;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	/* 	width: 45%; */
	height: 100%;
}

/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>
</head>
<body>

	<div class="header">
		<a href="#default" class="logo">eLibrary</a>
		<div class="header-right">
			<a class="active" href="home">Home</a>
			<c:choose>
				<c:when test="${user !=null}">
					<a href="#">${user.person.name }</a>
					<a class="active" href="logout">Logout</a>
				</c:when>
				<c:when test="${user ==null}">
					<button class="loginButton" id="loginButton">login</button>
				</c:when>
			</c:choose>
			<!-- 			<a href="#contact">Contact</a> -->
		</div>
	</div>


	<!-- Login Modal -->
	<div id="loginModal" class="modal">
		<!-- Modal content -->
		<div class="modal-content">
			<span class="close">&times;</span><br>

			<div class="container">
				<!-- Codrops top bar -->

				<!--/ Codrops top bar -->
				<header>
					<h1>
						Login or Join <span>with eLibrary</span>
					</h1>
				</header>
				<section>
					<div id="container_demo">
						<!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
						<a class="hiddenanchor" id="toregister"></a> <a
							class="hiddenanchor" id="tologin"></a>
						<div id="wrapper">
							<div id="login" class="animate form">
								<form action="login" method="POST"
									enctype="application/x-www-form-urlencoded">
									<h1>Log in</h1>
									<p>
										<label for="username" class="uname" data-icon="u">
											Your email or username </label> <input id="username" name="username"
											required="required" type="text"
											placeholder="myusername or mymail@mail.com" />
									</p>
									<p>
										<label for="password" class="youpasswd" data-icon="p">
											Your password </label> <input id="password" name="password"
											required="required" type="password"
											placeholder="eg. X8df!90EO" />
									</p>

									<p class="login button">
										<input type="submit" value="Login" />
									</p>
									<p class="change_link">
										Not a member yet ? <a href="#toregister" class="to_register">Join
											us</a>
									</p>
								</form>
							</div>

							<div id="register" class="animate form">
								<form action="addUser" method="POST"
									enctype="application/x-www-form-urlencoded">
									<h1>Sign up</h1>
									<p>
										<label for="usernamesignup" class="uname" data-icon="u">Your
											Full Name</label> <input id="name" name="name"
											required="required" type="text"
											placeholder="Kazi Abid" />
									</p>
									<p>
										<label for="usernamesignup" class="uname" data-icon="u">Your
											Contact Number</label> <input id="contactNumber" name="contactNumber"
											required="required" type="text"
											placeholder="9889955478" />
									</p>
									<p>
										<label for="usernamesignup" class="uname" data-icon="u">Your
											Age</label> <input id="age" name="age"
											required="required" type="text"
											placeholder="25" />
									</p>
									<p>
										<label for="usernamesignup" class="uname" data-icon="u">Your
											username</label> <input id="username" name="username"
											required="required" type="text"
											placeholder="mysuperusername690" />
									</p>
									<p>
										<label for="emailsignup" class="youmail" data-icon="e">
											Your email</label> <input id="email" name="email"
											required="required" type="email"
											placeholder="mysupermail@mail.com" />
									</p>
									<p>
										<label for="passwordsignup" class="youpasswd" data-icon="p">Your
											password </label> <input id="password" name="password"
											required="required" type="password"
											placeholder="eg. X8df!90EO" />
									</p>
									<p>
										<label for="passwordsignup_confirm" class="youpasswd"
											data-icon="p">Please confirm your password </label> <input
											id="passwordsignup_confirm" name="passwordsignup_confirm"
											required="required" type="password"
											placeholder="eg. X8df!90EO" />
									</p>
									<p class="signin button">
										<input type="submit" value="Sign up" />
									</p>
									<p class="change_link">
										Already a member ? <a href="#tologin" class="to_register">
											Go and log in </a>
									</p>
								</form>
							</div>

						</div>
					</div>
				</section>
			</div>
		</div>
	</div>
</body>

<script>


	// Get the modal
	var modal = document.getElementById("loginModal");

	// Get the button that opens the modal
	var btn = document.getElementById("loginButton");

	var logoutBtn = document.getElementById("logoutButton");
	
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks the button, open the modal 
	btn.onclick = function() {
		modal.style.display = "block";
	}

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
	
	//on load
// 	(function() {
<%-- 		<%String message = (String) request.getAttribute("message");%> --%>
<%-- 		var message = "<%=message%>"; --%>
// 		if (message) {
// 			btn.click();
// 		}
// 	})();
	document.addEventListener('DOMContentLoaded', function(){ 
		<%String message = (String) session.getAttribute("message");%>
		var message = "<%=message%>";
		if (message) {
			if (message != "null") {
				btn.click();
			}
			
		};
	}, false);
</script>
</html>
