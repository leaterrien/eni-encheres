<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Affichage d'une enchère</title>
</head>
<jsp:include page="/WEB-INF/JSP/fragments/header.jsp">
<body>

	<c:if test="${!empty article}">
		<h1 class="my-5 d-flex justify-content-center">Détail vente</h1>
		<div>
			<p><span class="user-show-value">${article.nom}</span></p>		
			<p><span class="user-show-label">Description : </span><span class="user-show-value">${article.description}</span></p>	
			<p><span class="user-show-label">Catégorie : </span><span class="user-show-value">${article.categorie}</span></p>	
			<p><span class="user-show-label">Meilleure offre : </span><span class="user-show-value">${enchere.montant}</span></p>	
			<p><span class="user-show-label">Mise à prix : </span><span class="user-show-value">${article.miseAPrix}</span></p>	
			<p><span class="user-show-label">Fin de l'enchère : </span><span class="user-show-value">${article.dateDebutEncheres}</span></p>	
			<p><span class="user-show-label">Retrait : </span><span class="user-show-value">${retrait.rue}</span></p>	
			<p><span class="user-show-value">${retrait.codePostal}</span><span class="user-show-value">${retrait.ville}</span></p>
			<p><span class="user-show-label">Vendeur : </span><span class="user-show-value">${retrait.pseudo}</span></p>
			
		</div>
	</c:if>

</body>
</html>