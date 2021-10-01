<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="fr.eni.encheres.messages.business_exception_messages" var="errorMessages"/>


	<!-- header -->
	<jsp:include page="/WEB-INF/JSP/fragments/header.jsp">
		<jsp:param value="newSale" name="pageTitle" />
	</jsp:include>
	
	<h1 class="d-flex justify-content-center my-5">Nouvelle Vente</h1>
	<br>
	
	<c:forEach var="error" items="${errors}">
		<p >
			<fmt:message key="${error}" bundle="${errorMessages}"></fmt:message>
		</p>
	</c:forEach>
	
	<form method="post">
		<div class="d-flex">
		<div class="mr-auto p-2 frame border p-3" style="float:left; width: 20%; height: 20%;"><img src="" alt="Image article"></div>
		<div class="row justify-content-center my-5">
			<div class="col-12 col-md-6">
				
					<label class="form-label" for="name">Article :</label>
					<input class="form-control" type="text" id="name" name="name" autofocus required/>
				
				
					<label class="form-label" for="description">Description :</label>
					<textarea class="form-control" id="description" name="description" autofocus required></textarea>
				
				
					<label class="form-label" for="category">Catégorie :</label>
					<select class="form-control"  id="category" name="category" required>
						<c:forEach var="categorie" items="${categories}">
							<option value="${categorie.noCategorie}">${categorie.libelle}</option>
						</c:forEach>
						
					</select>
				
					<label class="form-label" for="photoDeLArticle">Photo de L'article :</label>
					<input class="form-control" type="file" class="form-control-file" id="photoDeLArticle" name="photoDeLArticle">
				
					<label class="form-label" for="price">Mise à prix :</label>
					<input class="form-control" type="number" id="price" name="price" min="0" required>
				
					<label class="form-label" for="start_auction">Début de l'enchère :</label>
					<input class="form-control" type="date" id="start_auction" name="start_auction" required/>
					<br>
					<label class="form-label" for="end_auction">Fin de l'enchère :</label>
					<input class="form-control" type="date" id="end_auction" name="end_auction" required/>
				<br>
				<br>
			</div>
				<fieldset class="form-group border p-3">
					<legend class="w-auto px-2">Retrait</legend>	
						<div class="form-group row">
							<label for="street" class="col-sm-2 col-form-label">Rue :</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="street" name="street" value="${utilisateur.rue}"/>
							</div>
						</div>
						
						<div class="form-group row">
							<label for="postcode" class="col-sm-2 col-form-label">Code Postal :</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="postcode" name="postcode" value="${utilisateur.codePostal}"/>
							</div>
						</div>
						
						<div class="form-group row">
							<label for="city" class="col-sm-2 col-form-label">Ville :</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="city" name="city" value="${utilisateur.ville}"/>
							</div>
						</div>
						
					</fieldset>
		</div>
		</div>
	<div class="d-flex justify-content-center">	
		<a href="${pageContext.request.contextPath}" class="btn button-navigation">Annuler</a>
		<div class="p-2 bd-highlight"></div>
		<div class="p-2 bd-highlight"></div>
		<input type="submit" class="btn button-navigation" value="Valider"/>
	</div>	
	</form>
	
	
	
			
	<jsp:include page="/WEB-INF/JSP/fragments/footer.jsp"></jsp:include>