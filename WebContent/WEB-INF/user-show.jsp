<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<jsp:include page="/WEB-INF/header.jsp">
	<jsp:param value="user-show" name="pageTitle" />
</jsp:include>

	<!--  Affichage de l'utilisateur si celui-ci a été trouvé -->
	<c:if test="${!empty utilisateur}">
	<h1 class="my-5 d-flex justify-content-center">${utilisateur.pseudo}</h1>
	<div>
		<p><span class="user-show-label">Pseudo : </span><span class="user-show-value">${utilisateur.pseudo}</span></p>	
		<p><span class="user-show-label">Nom : </span><span class="user-show-value">${utilisateur.nom}</span></p>	
		<p><span class="user-show-label">Prénom : </span><span class="user-show-value">${utilisateur.prenom}</span></p>	
		<p><span class="user-show-label">Email : </span><span class="user-show-value">${utilisateur.email}</span></p>	
		<p><span class="user-show-label">Téléphone : </span><span class="user-show-value">${utilisateur.telephone}</span></p>	
		<p><span class="user-show-label">Rue : </span><span class="user-show-value">${utilisateur.rue}</span></p>	
		<p><span class="user-show-label">Code postal : </span><span class="user-show-value">${utilisateur.codePostal}</span></p>	
		<p><span class="user-show-label">Ville : </span><span class="user-show-value">${utilisateur.ville}</span></p>	
	</div>
	</c:if>
	<!-- Affichage d'un message d'erreur si l'utilisateur n'a pas été trouvé -->
	<c:if test="${empty utilisateur}">
		<h1 class="my-5 d-flex justify-content-center">Profil utilisateur</h1>
		<p>L'utilisateur demandé n'existe pas</p>
	</c:if>
	<div class="mt-5 d-flex justify-content-center">
		<div class="btn">
			<a href="" class="">Retourner à l'accueil</a>
		</div>
	</div>

<!-- footer -->
<jsp:include page="/WEB-INF/footer.jsp">
		<jsp:param value="" name=""/>
</jsp:include>
