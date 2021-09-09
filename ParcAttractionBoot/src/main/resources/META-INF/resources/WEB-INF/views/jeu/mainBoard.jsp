<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">

body{
	 
	display: flex;
	flex-direction: column;
	background-color:  #ec8809b3;
	flex-grow: 1;
	//border: black 2px, solid;

}

header{
	text-align: : center;
	//border: blue 2px, solid;
}

footer{
	text-align: : center;
	//border: black 2px, solid;
}


#corp{

	display: flex;
	flex-direction: row;
//	border: red 2px solid;

	flex-grow: 1;
}

#possession{

	min-width: 80%;
	display: flex;
	flex-wrap: wrap;
	background-color:  #ec520933;
//	border: yellow 2px solid;
	flex-grow: 1;

}

#boutique, #restaurant, #attraction, #employe, #commodite, #empty{

	max-width: 30%;
//	border: solid green 2px;
	flex-grow: 1;

}


</style>


<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>Modification</title>
<link rel="shortcut icon" href="img/logoYoloIcon.png">
</head>
<body>

	<header>
		<h1> Nom du parc </h1>

	</header>

	<div class="container" id="corp">
		
		<div class="container">
			
			<div class="btn-group-vertical">
  				<a type="button" class="btn btn-primary">Retour Map</a>
  				<a type="button" class="btn btn-primary">Sauvegarder</a>
  				<a type="button" class="btn btn-primary">Quitter</a>
			</div>

			
		</div>	
		
		
		<div class="container" id="possession" >
			
			<div class="container" id="attraction">
				<h1>Attractions<a type="button" class="btn btn-info" href="${ctx}/possessions/attraction">Détail</a></h1>
				<c:if test="${delete!=null}">
					<div class="alert alert-primary">produit ${delete} supprimé</div>
				</c:if>
				<table class="table">
					<c:forEach items="${attractions}" var="a">
						<tr>
							<td>${a.id}</td>
							<td>${a.nom}</td>
							<td>${a.prixFonctionnement}</td>
							<td>${a.affluence}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="btn-group" role="group" aria-label="Basic mixed styles example">
				  <a type="button" class="btn btn-danger" href="${ctx}/achat?type=attraction">Acheter</a>
				  <a type="button" class="btn btn-warning" href="${ctx}/amelioration?type=attraction">Améliorer</a>
				  <a type="button" class="btn btn-success" href="${ctx}/vente?type=attraction">Vendre</a>
				</div>
			</div>

			<div class="container" id="restaurant">
				<h1>Restaurants<a type="button" class="btn btn-info">Détail</a></h1>
				<c:if test="${delete!=null}">
					<div class="alert alert-primary">produit ${delete} supprimé</div>
				</c:if>
				<table class="table">
					<c:forEach items="${restaurants}" var="r">
						<tr>
							<td>${r.id}</td>
							<td>${r.nom}</td>
							<td>${r.prixFonctionnement}</td>
							<td>${r.affluence}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="btn-group" role="group" aria-label="Basic mixed styles example">
				  <a type="button" class="btn btn-danger" href="${ctx}/achat?type=restaurant">Acheter</a>
				  <a type="button" class="btn btn-warning" href="${ctx}/amelioration?type=restaurant">Améliorer</a>
				  <a type="button" class="btn btn-success" href="${ctx}/vente?type=restaurant">Vendre</a>
				</div>
			</div>	

			<div class="container" id="employe">
				<h1>Employés<a type="button" class="btn btn-info" href="${ctx}/possessions/employe">Detail</a></h1>
				<c:if test="${delete!=null}">	
					<div class="alert alert-primary">produit ${delete} supprimé</div>
				</c:if>
				<table class="table">
					<c:forEach items="${employes}" var="e">
						<tr>
							<td>${e.id}</td>
							<td>${e.nom}</td>
							<td>${e.prixFonctionnement}</td>
							<td>${e.affluence}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="btn-group" role="group" aria-label="Basic mixed styles example">
				  <a type="button" class="btn btn-danger" href="${ctx}/achat?type=employe">Embaucher</a>
				  <a type="button" class="btn btn-warning" href="${ctx}/amelioration?type=employe">Ameliorer</a>
				  <a type="button" class="btn btn-success" href="${ctx}/vente?type=restaurant">Vendre</a>
				</div>
			</div>	

			<div class="container" id="boutique">
				<h1>Boutiques<a type="button" class="btn btn-info" href="${ctx}/possessions/boutique">Détail</a></h1>
				<c:if test="${delete!=null}">
					<div class="alert alert-primary">produit ${delete} supprimé</div>
				</c:if>
				<table class="table">
					<c:forEach items="${boutiques}" var="b">
						<tr>
							<td>${b.id}</td>
							<td>${b.nom}</td>
							<td>${b.prixFonctionnement}</td>
							<td>${b.affluence}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="btn-group" role="group" aria-label="Basic mixed styles example">
				  <a type="button" class="btn btn-danger" href="${ctx}/achat?type=boutique">Acheter</a>
				  <a type="button" class="btn btn-warning" href="${ctx}/amelioration?type=boutique">Ameliorer</a>
				  <a type="button" class="btn btn-success" href="${ctx}/vente?type=boutique">Vendre</a>
				</div>
			</div>	

			<div class="container" id="empty">
				<h1></h1>
				
			</div>	


			<div class="container" id="commodite">
				<h1>Commodités<a type="button" class="btn btn-info" href="${ctx}/possessions/commodite">Détail</a></h1>
				<c:if test="${delete!=null}">
					<div class="alert alert-primary">produit ${delete} supprimé</div>
				</c:if>
				<table class="table">
					<c:forEach items="${commodites}" var="c">
						<tr>
							<td>${c.id}</td>
							<td>${c.nom}</td>
							<td>${c.prixFonctionnement}</td>
							<td>${c.affluence}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="btn-group" role="group" aria-label="Basic mixed styles example">
				  <a type="button" class="btn btn-danger" href="${ctx}/achat?type=commodite">Acheter</a>
				  <a type="button" class="btn btn-warning" href="${ctx}/amelioration?type=commodite">Ameliorer</a>
				  <a type="button" class="btn btn-success" href="${ctx}/vente?type=commodite">Vendre</a>
				</div>
			</div>	
		</div>

		<div class="container">
			
		</div>	
	</div>

	<footer>
		Le footer

	</footer>
</body>
</html>

