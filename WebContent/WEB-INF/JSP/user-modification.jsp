<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="fr.eni.encheres.messages.business_exception_messages" var="errorMessages"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier votre compte</title>
<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/style/form.css" rel="stylesheet">

</head>
<jsp:include page="/WEB-INF/JSP/fragments/header.jsp"></jsp:include>
<body>

<h1 class="my-5 d-flex justify-content-center">${utilisateur.noUtilisateur}</h1>

<form class="row" action="<%=request.getContextPath()%>/ModificationUtilisateur" method="post">
	<div class="row">
		<div class="col-md-6"><label for=username>Pseudo : </label><input class="col-md-6" type="text" pattern="^[a-zA-Z0-9]+$" maxLength="20" id="username" name="username" value="${utilisateur.pseudo}" required/></div>
		<div class="col-md-6"><label for="email"> Email : </label><input class="col-md-6" type="email" maxLength="50" id="email" name="email" value="${utilisateur.email}" required/></div>
		<div class="col-md-6"><label for="last_name">Nom : </label><input class="col-md-6" type="text" maxLength="50" id="last_name" name="last_name" value="${utilisateur.nom}" required/></div>
		<div class="col-md-6"><label for="first_name">Prénom : </label><input class="col-md-6" type="text" maxLength="50" id="first_name" name="first_name" value="${utilisateur.prenom}" required/></div>	
		<div class="col-md-6"><label for="phone">Téléphone : </label><input class="col-md-6" type="text" pattern="^[0-9]+$" maxLength="10" id="phone" name="phone" value="${utilisateur.telephone}"required/></div>	
		<div class="col-md-6"><label for="street">Rue : </label><input class="col-md-6" type="text" maxLength="50" id="street" name="street" value="${utilisateur.rue}" required/></div>	
		<div class="col-md-6"><label for="postcode">Code postal : </label><input class="col-md-6" type="text" pattern="^[0-9]+$" maxLength="5" id="postcode" name="postcode" value="${utilisateur.codePostal}" required/></div>	
		<div class="col-md-6"><label for="city">Ville : </label><input class="col-md-6" type="text" maxLength="50" id="city" name="city" value="${utilisateur.ville}" required/></div>	
		<div class="col-sm-12"><label for="password">Mot de passe actuel : </label><input type="password" maxLength="30" id="password" name="password" value="" /></div>
		<div class="col-md-6"><label for="new_password">Nouveau mot de passe : </label><input class="col-md-6" type="password" maxLength="30" id="new_password" name="new_password" value="" /></div>	
		<div class="col-md-6"><label for="confirm_password">Confirmation : </label><input class="col-md-6" type="password" maxLength="50" id="confirm_password" name="confirm_password" value="" /></div>
			
	</div>
	<input type="submit" class="btn button-navigation" value="Enregistrer"/>
</form>

<br/>
<a href="${pageContext.request.contextPath}/SuppressionUtilisateur" class="btn button-navigation">Supprimer mon compte</a>

<p><span class="user-show-label">Crédit : </span><span class="user-show-value">${utilisateur.credit}</span></p>
		
<c:forEach var="error" items="${errors}">
	<p >
		<fmt:message key="${error}" bundle="${errorMessages}"></fmt:message>
	</p>
</c:forEach>



</body>
</html>