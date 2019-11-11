<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLibrary</title>
</head>
<body>

	<div class="box">
		<a class="button" href="books">Go To Library</a>
	</div>
</body>
<style>
body {
	font-family: Arial, sans-serif;
	background:
		url(http://www.shukatsu-note.com/wp-content/uploads/2014/12/computer-564136_1280.jpg)
		no-repeat;
	background-size: cover;
	height: 100vh;
}
.box {
	width: 40%;
	margin: 0 auto;
	background: rgba(255, 255, 255, 0.2);
	padding: 35px;
	border: 2px solid #fff;
	border-radius: 20px/50px;
	background-clip: padding-box;
	text-align: center;
}

.button {
	font-size: 1em;
	padding: 10px;
	color: #fff;
	border: 2px solid #06D85F;
	border-radius: 20px/50px;
	text-decoration: none;
	cursor: pointer;
	transition: all 0.3s ease-out;
}

.button:hover {
	background: #06D85F;
}


@media screen and (max-width: 700px) {
	.box {
		width: 70%;
	}
	.popup {
		width: 70%;
	}
}
</style>
</html>