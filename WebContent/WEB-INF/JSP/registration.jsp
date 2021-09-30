<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="fr.eni.encheres.messages.business_exception_messages" var="errorMessages"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un compte sur ENI-Enchères</title>
<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/style/form.css" rel="stylesheet">
</head>

<jsp:include page="/WEB-INF/JSP/fragments/header.jsp"></jsp:include>


	<h1 class="d-flex justify-content-center my-5">Inscription sur le site ENI Enchères</h1>
	<br/>

<form action="<%=request.getContextPath()%>/Inscription" method="post">
	<div class="row">
		<div class="col-12 col-md-6">
			<label class="form-label" for=username>Pseudo : </label> 
			<input class="form-control" value="${creationUtilisateur.pseudo}"
				type="text" pattern="^[a-zA-Z0-9]+$" maxLength="20" id="username"
				name="username" required /> 
				
			<label class="form-label" for="password">Mot de passe : </label>
			<input class="form-control" type="password"
				maxLength="30" id="password" name="password" required /> 
				
			<label class="form-label" for="confirm_password">Confirmez le mot
				de passe : </label> 
			<input class="form-control" type="password"
				maxLength="30" id="confirm_password" name="confirm_password"
				required /> 
				
			<label class="form-label" for="last_name">Nom : </label> 
			<input class="form-control" value="${creationUtilisateur.nom}"
				type="text" maxLength="50" id="last_name" name="last_name" required />

			<label class="form-label" for="first_name">Prénom : </label> 
			<input class="form-control" value="${creationUtilisateur.prenom}"
				type="text" maxLength="50" id="first_name" name="first_name"
				required />
		</div>
		<div class="col-12 col-md-6">
		
			<label class="form-label" for="email"> Email : </label>
			<input class="form-control" value="${creationUtilisateur.email}"
				type="email" maxLength="50" id="email" name="email" required /> 
			<label class="form-label" for="phone">Téléphone : </label> 
			<input class="form-control" value="${creationUtilisateur.telephone}"
				type="text" pattern="^[0-9]+$" maxLength="10" id="phone"
				name="phone" required /> 
			<label class="form-label" for="street">Rue : </label> 
			<input class="form-control" value="${creationUtilisateur.rue}"
				type="text" maxLength="50" id="street" name="street" required /> 
			<label class="form-label" for="postcode">Code postal : </label> 
			<input class="form-control" value="${creationUtilisateur.codePostal}"
				type="text" pattern="^[0-9]+$" maxLength="5" id="postcode"
				name="postcode" required /> 
			<label class="form-label" for="city">Ville : </label> 
			<input class="form-control" value="${creationUtilisateur.ville}"
				type="text" maxLength="50" id="city" name="city" required />

		</div>
	</div>
	<div class="d-flex justify-content-center align-items-center">
		<a href="${pageContext.request.contextPath}" class="btn button-navigation">Annuler</a> 
		<input type="submit" class="h-100 btn button-navigation" value="Valider" />
	</div>
</form>

<c:forEach var="error" items="${errors}">
		<p >
			<fmt:message key="${error}" bundle="${errorMessages}"></fmt:message>
		</p>
	</c:forEach>
	
	<jsp:include page="/WEB-INF/JSP/fragments/footer.jsp"></jsp:include>