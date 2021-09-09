<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>

<head>
	<meta charset="UTF-8">
	<title>Yolocoaster Tycoon Joueur</title>
</head>

<body>

<c:set var="ctx" value = "${pageContext.servletContext.contextPath}"/>

<h1>Liste des parties</h1>
&emsp; &emsp; 
<input id="btnAddParc" type="button" class="btn btn-success" value="Créer une nouvelle partie">
&emsp;
<input id="filterPartie" placeholder="filtre">
        
<div id="addFormParc" style="display: none">
	<h3>Nouvelle partie</h3>
	<form:form action="${ctx}/parcs/addparc" method="post" modelAttribute="parc" class="formulaire">
		<form:input required="true" path="joueur.id" type="hidden" value="${sessionScope.joueur.id}"/><br>
		Nom* <form:input required="true" path="nomParc" placeholder="nom" type="text"/><br>
			<form:errors path="nomParc" style="color:red"/><br><br>
		Difficulte* <form:select required="true" path="typeDifficulte" items="${typesDifficultes}"/><br><br>
		<button type="submit" class="btn btn-success">Ajouter</button><br><br>
	</form:form>
</div> 
	
     <table class="table table-striped">
       <thead>
         <tr>
           <th>Nom</th>
           <th>Taille</th>
           <th>Argent</th>
           <th>Nombre jours joués</th>
           <th>Difficulté</th>
           </tr>
       </thead>
        <tbody id="contentTable">
			<c:forEach items="${parcs}" var="parc">
				<tr>
					<td>${parc.nomParc}</td>
					<td>${parc.taille}</td>
					<td>${parc.argent}</td>
					<td>${parc.nbjour}</td>
					<td>${parc.typeDifficulte}</td>
					<td>
                   		<a href="${ctx}/parcs/play?id=${parc.id}" type="button" class ="btn btn-warning">Jouer</a>
                   		<a href="${ctx}/parcs/delete?id=${parc.id}" type="button" class ="btn btn-danger">Supprimer</a>
                	 </td>
				</tr>
			</c:forEach>  
		</tbody>
     </table>
     
     
     
<script>

	btnAddParc.onclick=function()
	{
		addFormParc.style.display="block";
	}

</script>
	

</html>