<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- header -->
<jsp:include page="/WEB-INF/JSP/fragments/header.jsp">
	<jsp:param value="encheres-list" name="pageTitle" />
</jsp:include>

<h1 class="my-5 d-flex justify-content-center">Liste des enchères</h1>

<form method="get">
	<div class="row">
		<div class="col-12 col-sm-6 col-md-4">
			<!-- barre de recherche -->
			<div class="input-group mb-3">
 				<span class="input-group-text"><span class="material-icons">search</span></span>
 				<input type="text" id="barreRecherche" name="barreRecherche" class="form-control" placeholder="Le nom de l'article contient..." value="${nomRecherche}">
			</div>
		
			<!-- choix de catégorie -->
			<div class="mb-4">
				<label for="category" class="font-weight-bold">Catégorie</label> 
				<select
					id="category" name="category" class="dropdown">
					<option class="dropdown-item" value="0">Toutes</option>
					<c:forEach var="category" items="${listeCategories}">
						<option class="dropdown-item" 
							value="${category.noCategorie}" ${(!empty selectedCategory && selectedCategory == category.noCategorie) ? 'selected' : ''}>
							${category.libelle}
						</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<!-- bouton rechercher -->
		<div class="col-12 col-sm-6 col-md-6 mb-3 mb-sm-0">
			<button type="submit" class="btn button-navigation">Rechercher</button>
		</div>
	</div>
</form>


<div class="row">
	<!-- affichage des articles sous forme de cards -->
	<c:forEach var="article" items="${listeArticles}">
		<div class="col-12 col-md-6 mb-3">
			<div class="card p-3">
				<h3 class="font-weight-bold">${article.nom}</h3>
				<p>
					<span class="font-weight-bold">Prix : </span>${article.miseAPrix}</p>
				<p>
					<span class="font-weight-bold">Fin de l'enchère : </span>${article.dateFinEncheres.format(dateFormat)}</p>
				<p>
					<span class="font-weight-bold">Vendeur : </span>${article.vendeur.pseudo}</p>
			</div>
		</div>
	</c:forEach>
</div>

<!-- footer -->
<jsp:include page="/WEB-INF/JSP/fragments/footer.jsp">
	<jsp:param value="" name="" />
</jsp:include>