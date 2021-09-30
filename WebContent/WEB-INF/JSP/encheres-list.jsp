<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	session.setAttribute("achatSelected", true);
%>
<!-- header -->
<jsp:include page="/WEB-INF/JSP/fragments/header.jsp">
	<jsp:param value="encheres-list" name="pageTitle" />
</jsp:include>

<h1 class="my-5 d-flex justify-content-center">Liste des enchères</h1>

<form method="get">
	<div class="row">
		<div class="col-12 col-sm-8">
			<!-- barre de recherche -->
			<div class="input-group mb-3">
				<span class="input-group-text"><span class="material-icons">search</span></span>
				<input type="text" id="barreRecherche" name="barreRecherche"
					class="form-control" placeholder="Le nom de l'article contient..."
					value="${nomRecherche}">
			</div>

			<!-- choix de catégorie -->
			<div class="mb-4">
				<label for="category" class="font-weight-bold">Catégorie</label> 
				<select id="category" name="category" class="dropdown">
					<option class="dropdown-item" value="0">Toutes</option>
					<c:forEach var="category" items="${listeCategories}">
						<option class="dropdown-item" value="${category.noCategorie}"
							${(!empty selectedCategory && selectedCategory == category.noCategorie) ? 'selected' : ''}>
							${category.libelle}
						</option>
					</c:forEach>
				</select>
			</div>

			<c:if test="${! empty utilisateur }">
				<!-- filtres achats / ventes -->
				<div class="row">
					<!-- achats -->
					<div class="col-sm-12 col-md-6 mb-3">
						<input type="radio" id="achat" name="achatVente" value="achat"
							onchange="inverseDisabledCheckbox()" ${achatSelected ? 'checked' : ''}>
							<label for="achat" class="font-weight-bold">Achats</label>
						<!-- checkbox achats -->
						<div>
							<div>
								<input type="checkbox" id="encheresOuvertes" name="achats"
									value="encheresOuvertes" ${achatSelected ? '' : 'disabled'} 
									${checkboxConditions.contains("encheresOuvertes") || checkboxConditions.size() == 0 ? 'checked' : ''}> 
								<label for="encheresOuvertes">enchères ouvertes</label>
							</div>
							<div>
								<input type="checkbox" id="encheresEnCours" name="achats"
									value="encheresEnCours" ${achatSelected ? '' : 'disabled'}
									${checkboxConditions.contains("encheresEnCours") ? 'checked' : ''}> 
								<label for="encheresEnCours">mes enchères en cours</label>
							</div>
							<div>
								<input type="checkbox" id="encheresRemportees" name="achats"
									value="encheresRemportees" ${achatSelected ? '' : 'disabled'}
									${checkboxConditions.contains("encheresRemportees") ? 'checked' : ''}> 
									<label for="encheresRemportees">mes enchères remportées</label>
							</div>
						</div>
					</div>
					<!-- ventes -->
					<div class="col-sm-12 col-md-6 mb-3">
						<input type="radio" id="vente" name="achatVente" value="vente"
							onchange="inverseDisabledCheckbox()" ${!achatSelected ? 'checked' : ''}> 
							<label for="achat" class="font-weight-bold">Mes ventes</label>
						<!-- checkbox ventes -->
						<div>
							<div>
								<input type="checkbox" id="ventesEnCours" name="ventes"
									value="ventesEnCours" ${!achatSelected ? '' : 'disabled'}
									${checkboxConditions.contains("ventesEnCours") ? 'checked' : ''}> 
									<label for="ventesEnCours">mes ventes en cours</label>
							</div>
							<div>
								<input type="checkbox" id="ventesNonDebutees" name="ventes"
									value="ventesNonDebutees" ${!achatSelected ? '' : 'disabled'}
									${checkboxConditions.contains("ventesNonDebutees") ? 'checked' : ''}> 
									<label for="ventesNonDebutees">ventes non débutées</label>
							</div>
							<div>
								<input type="checkbox" id="ventesTerminees" name="ventes"
									value="ventesTerminees" ${!achatSelected ? '' : 'disabled'}
									${checkboxConditions.contains("ventesTerminees") ? 'checked' : ''}> 
									<label for="ventesTerminees">ventes terminées</label>
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</div>

		<!-- bouton rechercher -->
		<div class="col-12 col-sm-4 mb-3 mb-sm-0">
			<button type="submit" class="btn button-navigation">Rechercher</button>
		</div>
	</div>
</form>

<div class="row">
	<!-- affichage des articles sous forme de cards -->
	<c:if test="${empty listeArticles}">
		<p>Aucun article ne correspond à votre recherche</p>
	</c:if>
	<c:if test="${!empty listeArticles}">
		<c:forEach var="article" items="${listeArticles}">
			<div class="col-12 col-md-6 mb-3">
				<div class="card p-3">
					<h3 class="font-weight-bold">${article.nom}</h3>
					<p>
						<span class="font-weight-bold">Prix : </span>${article.miseAPrix}</p>
					<p>
						<span class="font-weight-bold">Fin de l'enchère : </span>${article.dateFinEncheres.format(dateFormat)}</p>
					<p>
						<span class="font-weight-bold">Vendeur : </span>
						<c:choose>
							<c:when test="${!(article.vendeur.noUtilisateur == sessionScope.utilisateur.noUtilisateur)}">
								<a class="black-color" 
									href="${pageContext.request.contextPath}/Utilisateur/${article.vendeur.noUtilisateur}">
									${article.vendeur.pseudo}
								</a>
							</c:when>
							<c:otherwise>
								<a class="black-color" 
									href="${pageContext.request.contextPath}/ModificationUtilisateur">
									${article.vendeur.pseudo}
								</a>
							</c:otherwise>
						</c:choose>
					</p>
					
				</div>
			</div>
		</c:forEach>
	</c:if>
</div>
<script>
	// Fonction d'activation / désactivation des checkbox du formulaire en fonction du bouton radio sélectionné
	function inverseDisabledCheckbox() {
		document.getElementById("ventesEnCours").disabled = !document.getElementById("ventesEnCours").disabled;
		document.getElementById("ventesNonDebutees").disabled = !document.getElementById("ventesNonDebutees").disabled;
		document.getElementById("ventesTerminees").disabled = !document.getElementById("ventesTerminees").disabled;
		document.getElementById("encheresOuvertes").disabled = !document.getElementById("encheresOuvertes").disabled;
		document.getElementById("encheresEnCours").disabled = !document.getElementById("encheresEnCours").disabled;
		document.getElementById("encheresRemportees").disabled = !document.getElementById("encheresRemportees").disabled;
		if(document.getElementById("vente").checked == false) {
			document.getElementById("ventesEnCours").checked = false;
			document.getElementById("ventesNonDebutees").checked = false;
			document.getElementById("ventesTerminees").checked = false;
		}
		if(document.getElementById("achat").checked == false) {
			document.getElementById("encheresOuvertes").checked = false;
			document.getElementById("encheresEnCours").checked = false;
			document.getElementById("encheresRemportees").checked = false;
		}
	}
</script>

<!-- footer -->
<jsp:include page="/WEB-INF/JSP/fragments/footer.jsp">
	<jsp:param value="" name="" />
</jsp:include>