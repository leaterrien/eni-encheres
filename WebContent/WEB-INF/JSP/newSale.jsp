<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>newSale</title>
<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/style/form.css" rel="stylesheet">
</head>
<body>
	<!-- header -->
	<jsp:include page="/WEB-INF/JSP/fragments/header.jsp">
		<jsp:param value="newSale" name="pageTitle" />
	</jsp:include>
	
	<h1>Nouvelle Vente</h1>
	
	
	
	<form action="<%=request.getContextPath()%>/ServletNewSale" method="post">
		<div class = "form-group">
			<label for="name">Article :</label>
			<input type="text" id="name" name="name" autofocus required/>
		</div>
		<div class = "form-group">
			<label for="description">Description :</label>
			<textarea id="description" name="description" autofocus required></textarea>
		</div>
		<div class = "form-group">
			<label for="category">Catégorie :</label>
			<select id="category" name="category" required>
				<c:forEach var="categorie" items="${categories}">
					<option value="${categorie.noCategorie}">${categorie.libelle}</option>
				</c:forEach>
				
			</select>
		</div>
		<div class = "form-group">
			<label for="photoDeLArticle">Photo de L'article :</label>
			<input type="file" class="form-control-file" id="photoDeLArticle" name="photoDeLArticle">
		</div>
		<div>
			<label for="price">Mise à prix :</label>
			<input type="number" id="price" name="price" min="0">
		</div>
			<label for="start_auction">Début de l'enchère :</label>
			<input type="date" id="start_auction" name="start_auction"/>
			<br>
			<label for="end_auction">Fin de l'enchère :</label>
			<input type="date" id="end_auction" name="end_auction"/>
		<br>
		<br>
		<div>
			<fieldset class="form-group border p-3">
				<legend class="w-auto px-2">Retrait</legend>	
					<div class="form-row">
						<div class="col-md-6 mb-3">
							<label for="street">Rue :</label>
							<input type="text" class="form-control" id="street" name="street" value="${utilisateur.rue}"/>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6 mb-3">
							<label for="postcode">Code Postal :</label>
							<input type="text" class="form-control" id="postcode" name="postcode" value="${utilisateur.codePostal}"/>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6 mb-3">
							<label for="city">Ville :</label>
							<input type="text" class="form-control" id="city" name="city" value="${utilisateur.ville}"/>
						</div>
					</div>
				</fieldset>
		</div>
			
		<a href="${pageContext.request.contextPath}" class="btn button-navigation">Annuler</a>
		<input type="submit" class="btn button-navigation" value="Valider"/>
	</form>
	
	<c:forEach var="error" items="${errors}">
		<p >
			<fmt:message key="${error}" bundle="${errorMessages}"></fmt:message>
		</p>
	</c:forEach>
			
	<!-- footer -->
	<jsp:include page="/WEB-INF/JSP/fragments/footer.jsp">
			<jsp:param value="" name=""/>
	</jsp:include>

</body>
</html>