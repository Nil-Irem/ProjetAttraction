<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!----------------------CSS------------------------------>
<style media="screen">
body {
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
}

header {
	height: 8%;
}

main {
	height: 88%;
	display: flex;
	justify-content: space-around;
	align-items: center;
}

footer {
	background-color: #ec8809b3;
	height: 4%;
	justify-content: space-between;
}

nav {
	background-color: #ec8809b3;
}

#asideRight, #asideLeft {
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	align-items: space-around;
	color: black;
	font-size: 30px;
	text-align: center;
	background-color: #ec8809b3;
	border-radius: 25%;
}

#section {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}


#fLeft, #fRight {
	font-size: 20px;
	/* color: #2c1bd7; */
	text-align: center;
}

#logo {
	background-color: #ec8809b3;
	border-radius: 5%;
}

#btnPlay {
	font-size: 3vw;
	height: 50%;
	width: 50%;
}

#menu {
	display: flex;
	justify-content: space-around;
	background-color: #ec8809b3;
}


.formulaire
{
	background-color: #ec8809b3;
	text-align: center;
    font-size: 20px;
    font-weight: bold;
}


</style>


<!DOCTYPE html>
<html lang=fr>
<head>
<!----------------------BOOTSTRAP------------------------------>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
	crossorigin="anonymous"></script>

<meta charset="utf-8">
<title>Yolocoaster Tycoon - Le Jeu</title>
</head>

<!-------------------HTML------------------------------>

<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<c:url var="imgPath" value="img" />

<body style="background-image: url(${imgPath}/parcBackground.webp);">
	<header>
		<nav class="navbar navbar-dark">
			<div class="container-fluid">
				<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarToggleExternalContent" aria-controls="#navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
         			<span class="navbar-toggler-icon"></span>
        		</button>
				<img id="menulogo" src="${imgPath}/logoYoloSmall.png" alt="YoloLogo">
			</div>
		</nav>

		<div class="collapse" id="navbarToggleExternalContent">
			<div id="menu" class="p-4">
				<a href="${ctx}/connexion" type="button" class="btn btn-primary btn-lg">Connexion</a> 
				<a href="${ctx}/inscription" type="button" class="btn btn-secondary btn-lg">Inscription</a>
				<a href="PageInfo.html" type="button" class="btn btn-info btn-lg">A propos</a>
			</div>
		</div>
	</header>

	<main>
	<div class="container-fluid">
		<div class="row h-100 justify-content-center align-items-center" style="display: ${display_accueil}">
			<div class="col-3" id="asideLeft">
				<br>
				<h1>
					<em><strong>"Un jeu génial!"<br>Tufu Mag
					</strong></em>
				</h1>
				<br>
				<h1>
					<em><strong>"Une CLAQUE visuelle"<br>Geek Mag
					</strong></em>
				</h1>
				<br>
			</div>
			<div class="col-6" id="section">
				<div id="logo">
					<img src="${imgPath}/logoBlanc.png" alt="YoloLogo" width="100%">
					<%-- <img src="${imgPath}/YoloLogoSmall.png" alt="YoloLogo"> --%>
				</div>
				<br> <a id="btnPlay" href="${ctx}/connexion" type="button"
					class="btn btn-success">Jouer !</a>
				<!--<button type="button" class="btn btn-danger" style="font-size:40px;">Quitter</button>-->
			</div>

			<div class="col-3" id="asideRight">
				<br>
				<h1>
					<em><strong>"Une immersion totale!"<br>Tufu Mag
					</strong></em>
				</h1>
				<br>
				<h1>
					<em><strong>"Un jeu de génie"<br>Geek Mag
					</strong></em>
				</h1>
				<br>
			</div>
		</div>


		<div class="row h-100 justify-content-center align-items-center" style="display: ${display_connexion}">
			<div class="col-sm-2 col-md-6 col-lg-4">
				<form:form action="${ctx}/compte/connexion" method="post" modelAttribute="compte" class="formulaire">
					<br>
					Pseudo* <form:input required="true" path="login" placeholder="pseudo" type="text"/>
					<br><br>
					Mot de passe* <form:input required="true" path="password" placeholder="mot de passe" type="password"/>
					<br><br>
					<button type="submit" class="btn btn-success">Se Connecter</button>
					<br><br>
					<a href="${ctx}/inscription">Créer un compte</a> <br>
					<br>
				</form:form>
			</div>
		</div>


		<div class="row h-100 justify-content-center align-items-center" style="display: ${display_inscription}">
			<div class="col-sm-2 col-md-6 col-lg-4">
				<form:form action="${ctx}/compte/inscription" method="post" modelAttribute="compte" class="formulaire">
					<br> 
					Pseudo* <form:input required="true" path="login" placeholder="pseudo" type="text"/>
						<form:errors path="login" style="color:red"/><br><br>
					<!-- Email *<input id="Email" name="email" type="email" placeholder="lorem@email.com"><br><br>
					Date de naissance <input id="Date de naissance" name="naissance" type="date"><br><br> -->
					Mot de passe* <form:input required="true" path="password" placeholder="mot de passe" type="password"/>
						<form:errors path="password" style="color:red"/>
						<br><br>
					<button type="submit" class="btn btn-success">S'inscrire</button>
					<br><br>
					<a href="${ctx}/connexion">Déjà inscrit ?</a> <br>
					<br>
				</form:form>
			</div>
		</div>

</div>

	</main>


	<footer>
		<!-- <div id="fLeft">
			<a href="">Accueil</a>
			<a href="">Plan</a>
			<a href="">Contact</a>
			<a href="">CGU</a>
		</div> -->
		<div id="fRight">
			<span>YoloCompany - Copyrigth &copy; 2021</span>
		</div>
	</footer>
</body>
</html>