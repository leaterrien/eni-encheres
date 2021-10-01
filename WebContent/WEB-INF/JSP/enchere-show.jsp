<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
	<jsp:include page="/WEB-INF/JSP/fragments/header.jsp">
		<jsp:param value="enchere-show" name="pageTitle" />
	</jsp:include>
<body>

	<c:if test="${!empty article}">
		<h1 class="my-5 d-flex justify-content-center">${article.nom}</h1>
		<div>	
			<p><span class="user-show-label">Description : </span><span class="user-show-value">${article.description}</span></p>	
			<p><span class="user-show-label">Catégorie : </span><span class="user-show-value">${categorie.libelle}</span></p>	
			<p><span class="user-show-label">Début de l'enchère : </span><span class="user-show-value">${article.dateDebutEncheres}</span></p>	
			<c:if test="${!empty enchere}">
	 			<p><span class="user-show-label">Meilleure offre : </span><span class="user-show-value">${enchere.montant} points par </span>
	 			 	<span class="user-show-value">${enchere.encherisseur.getPseudo()}</span> </p>
	 		</c:if>
			<p><span class="user-show-label">Mise à prix : </span><span class="user-show-value">${article.miseAPrix} points</span></p>	
			<p><span class="user-show-label">Fin de l'enchère : </span><span class="user-show-value">${article.dateFinEncheres}</span></p>
			<c:if test="${!empty retrait}">	
				<p><span class="user-show-label">Retrait : </span></p>
				<p><span class="user-show-value">${retrait.rue}</span></p>
				<p><span class="user-show-value">${retrait.codePostal} </span><span class="user-show-value">${retrait.ville}</span></p>
			</c:if>
			<c:if test="${empty retrait}">
				<p><span class="user-show-label">Retrait : </span><span class="user-show-value">${vendeur.rue}</span></p>
				<p><span class="user-show-value">${vendeur.codePostal} </span><span class="user-show-value">${vendeur.ville}</span></p>
			</c:if>
 	
			<p><span class="user-show-label">Vendeur : </span><span class="user-show-value">${vendeur.pseudo}</span></p> 
		</div>
	</c:if>
	<c:if test="${vendeur.pseudo.equals(utilisateur.pseudo) && debutEncheres == false}">
		<p><a style = "margin: 5 px" href=" " class="col-3 btn button-navigation"> Modifier votre annonce</a></p> 
	</c:if>
	<c:if test="${!vendeur.pseudo.equals(utilisateur.pseudo) && debutEncheres == true && finEncheres == false}">
		<p><a style = "margin: 5 px" href=" " class="col-3 btn button-navigation"> Enchérir </a></p> 
	</c:if>
	<c:if test="${finEncheres == true && empty article.prixVente}">
		<p><span class="user-show-label">L'article n'a pas été vendu</span></p>
	</c:if>
	<c:if test="${finEncheres == true &&  enchere.encherisseur.getPseudo().equals(utilisateur.pseudo)}">
		<p><span class="user-show-label">Vous avez gagné l'enchère</span></p>
	</c:if>
<%-- 	<c:if test="${finEncheres == true && !empty article.prixVente}">
		<p><span class="user-show-value">L'article a été vendu</span></p>
	</c:if> --%>
	<!-- TODO : vérifier si l'utilisateur en session est le vendeur -->


</body>
</html>