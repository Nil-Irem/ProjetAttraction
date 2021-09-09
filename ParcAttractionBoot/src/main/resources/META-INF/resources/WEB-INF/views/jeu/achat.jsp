<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
	<c:set var="ctx" value = "${pageContext.servletContext.contextPath}"/>
<head>
<meta charset="UTF-8">
<title>Yolocoaster Tycoon Achat</title>
</head>
<body>
<c:set var="ctx" value = "${pageContext.servletContext.contextPath}"/>

<div class="container">
	<table class="table">
		<thead>
			<tr>
        		<th>Type de Construction</th>
				<th>Nom</th>
				<th>Prix d'Acquisition (€)</th>
				<th>Coût par tour (€)</th>
				<th>Nombre d'Améliorations Possibles</th>
                <th>Taux d'Incidents</th>
                <th>Taille (m²)</th>
                <th>Affluence</th>
                <th>Revenu Journalier par Client</th>
			</tr>
       	</thead>
		<c:forEach items="${achatsA}" var="att">
			<tr>
            	<td>Attraction</td>
				<td>${att.nom}</td>
				<td>${att.prixAcquisition}</td>
				<td>${att.prixFonctionnement}</td>
				<td>${att.nbAmelioration}</td>
                <td>${att.tauxIncident}</td>
                <td>${att.taille}</td>
                <td>${att.affluence}</td>
                <td>0</td>
				<td><a class="btn btn-link" href="${ctx}/achat/save">acheter</a></td>		
			</tr>
		</c:forEach>
        <c:forEach items="${achatsR}" var="r">
			<tr>
				<td>Restaurant</td>
				<td>${r.nom}</td>
				<td>${r.prixAcquisition}</td>
				<td>${r.prixFonctionnement}</td>
				<td>${r.nbAmelioration}</td>
            	<td>${r.tauxIncident}</td>
            	<td>${r.taille}</td>
            	<td>${r.affluence}</td>
            	<td>${r.revenuJourPersonne}</td>
				<td><a class="btn btn-link" href="${ctx}/achat/save">acheter</a></td>
			</tr>
		</c:forEach>
        <c:forEach items="${achatsB}" var="b">
			<tr>
                <td>Boutique</td>
				<td>${b.nom}</td>
				<td>${b.prixAcquisition}</td>
				<td>${b.prixFonctionnement}</td>
				<td>${b.nbAmelioration}</td>
                <td>${b.tauxIncident}</td>
                <td>${b.taille}</td>
                <td>${b.affluence}</td>
                <td>${b.revenuJourPersonne}</td>
				<td><a class="btn btn-link" href="${ctx}/achat/save">acheter</a></td>
			</tr>
		</c:forEach>
        <c:forEach items="${achatsC}" var="c">
			<tr>
                <td>Commodité</td>
				<td>${c.nom}</td>
				<td>${c.prixAcquisition}</td>
				<td>0</td>
				<td>0</td>
                <td>0</td>
                <td>${c.taille}</td>
                <td>Augmente l'affluence</td>
                <td>0</td>
				<td><a class="btn btn-link" href="${ctx}/save">acheter</a></td>
			</tr>
		</c:forEach>
        <c:forEach items="${achatsE}" var="e">
				<tr>
                         <td>Employé</td>
					<td>${e.metier}</td>
					<td>0</td>
					<td>${e.salaire}</td>
					<td>0</td>
                         <td><c:if test="${e.salaire}>50"><p>Diminue le taux d'incidents de ${e.salaire} %</p></c:if>
                              </td>
                         <td>0</td>
                         <td><c:if test="${e.salaire}<50"><p>Augmente l'affluence de ${e.salaire}%</p></c:if></td>
                         <td>0</td>
					<td><a class="btn btn-link" href="${ctx}/save">acheter</a></td>
					
				</tr>
			</c:forEach>
		</table>
		
	</div>
</body>
</html>