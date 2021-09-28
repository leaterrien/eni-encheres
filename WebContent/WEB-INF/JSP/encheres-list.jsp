<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- header -->
<jsp:include page="/WEB-INF/JSP/fragments/header.jsp">
	<jsp:param value="encheres-list" name="pageTitle" />
</jsp:include>

<h1 class="my-5 d-flex justify-content-center">Liste des enchères</h1>

<div class="row">
	<!-- affichage des articles sous forme de cards -->
	<c:forEach var="article" items="${listeArticles}">
		<div class="col-12 col-md-6 mb-3">
			<div class="card p-3">
				<h3 class="font-weight-bold">${article.nom}</h3>
				<p><span class="font-weight-bold">Prix : </span>${article.miseAPrix}</p>
				<p><span class="font-weight-bold">Fin de l'enchère : </span>${article.dateFinEncheres.format(dateFormat)}</p>
				<p><span class="font-weight-bold">Vendeur : </span>${article.vendeur.pseudo}</p>
			</div>
		</div>
	</c:forEach>
</div>

<!-- footer -->
<jsp:include page="/WEB-INF/JSP/fragments/footer.jsp">
		<jsp:param value="" name=""/>
</jsp:include>