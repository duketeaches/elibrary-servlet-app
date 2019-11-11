<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Gallery</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {
	box-sizing: border-box;
}

body {
	background-color: #f1f1f1;
	padding: 20px;
	font-family: Arial;
}

/* Center website */
.main {
	max-width: 1000px;
	margin: auto;
}

h1 {
	font-size: 50px;
	word-break: break-all;
}

.row {
	margin: 10px -16px;
}

/* Add padding BETWEEN each column */
.row, .row>.column {
	padding: 8px;
}

/* Create three equal columns that floats next to each other */
.column {
	float: left;
	width: 33.33%; /* Hide all elements by default */
}

/* Clear floats after rows */
.row:after {
	content: "";
	display: table;
	clear: both;
}

/* Content */
.content {
	background-color: #c3c2e0;
	padding: 10px;
	height: 25em;
}

/* The "show" class is added to the filtered elements */
.show {
	display: block;
}

/* Style the buttons */
.btn {
	border: none;
	outline: none;
	padding: 12px 16px;
	background-color: white;
	cursor: pointer;
}

.btn:hover {
	background-color: #ddd;
}

.btn.active {
	background-color: #666;
	color: white;
}

/* Popup */
body {
	font-family: Arial, sans-serif;
	background:
		url(http://www.shukatsu-note.com/wp-content/uploads/2014/12/computer-564136_1280.jpg)
		no-repeat;
	background-size: cover;
	height: 100vh;
}

h1 {
	text-align: center;
	font-family: Tahoma, Arial, sans-serif;
	color: #06D85F;
	margin: 80px 0;
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

.overlay {
	position: fixed;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	background: rgba(0, 0, 0, 0.7);
	transition: opacity 500ms;
	visibility: hidden;
	opacity: 0;
}

.overlay:target {
	visibility: visible;
	opacity: 1;
}

.popup {
	margin: 70px auto;
	padding: 20px;
	background: #fff;
	border-radius: 5px;
	width: 30%;
	position: relative;
	transition: all 5s ease-in-out;
}

.popup h2 {
	margin-top: 0;
	color: #333;
	font-family: Tahoma, Arial, sans-serif;
}

.popup .close {
	position: absolute;
	top: 20px;
	right: 30px;
	transition: all 200ms;
	font-size: 30px;
	font-weight: bold;
	text-decoration: none;
	color: #333;
}

.popup .close:hover {
	color: #06D85F;
}

.popup .content {
	max-height: 30%;
	overflow: auto;
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
</head>
<body>

	<!-- MAIN (Center website) -->
	<div class="main">

		<hr>

		<h2>Our Books</h2>
		<c:if test="${user.admin==true }">
			<div class="box">
				<a class="button" href="#popup1">Add Book </a>
			</div>
		</c:if>



		<!-- Portfolio Gallery Grid -->
		<div class="row">
			<c:forEach var="book" items="${books }">
				<div class="column nature">
					<div class="content">
						<img src="images?id=${book.bookId }"
							style="width: 100%; height: 68%; object-fit: contain;">
						<h4>${book.name}</h4>
						<p>${book.author}</p>
						<p>${book.year}</p>
						<a href="images?id=${book.bookId }&content=true" target="_blank">Download Book</a>
					</div>
				</div>

			</c:forEach>
			<!-- END GRID -->
		</div>

		<!-- END MAIN -->
	</div>

	<!-- 	Add Book Modal -->
	<div id="popup1" class="overlay">
		<div class="popup">
			<h2>Here i am</h2>
			<a class="close" href="#">&times;</a>
			<div class="content">
				<form action="addBook" method="POST" enctype="multipart/form-data">
					<table>
						<tr>
							<td>Book Name</td>
							<td><input type="text" name="name"></td>
						</tr>
						<tr>
							<td>Author</td>
							<td><input type="text" name="author"></td>
						</tr>
						<tr>
							<td>Year</td>
							<td><input type="text" name="year"></td>
						</tr>
						<tr>
							<td>ISBN</td>
							<td><input type="text" name="isbn"></td>
						</tr>
						<tr>
							<td>Add Cover Image</td>
							<td><input type="file" name="image"></td>
						</tr>
						<tr>
							<td>Add Content File</td>
							<td><input type="file" name="content"></td>
						</tr>
						<tr>
							<td colspan="2"><button type="submit">Add Book</button></td>
						</tr>
						<tr>
							<td colspan="2"><label style="color: red">${message }</label></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function openBookModal() {
		document.getElementById("popup1").style.display = "block";
	}
</script>
</html>