<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un compte sur ENI-Enchères</title>
<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/style/form.css" rel="stylesheet">
</head>

<jsp:include page="/WEB-INF/JSP/fragments/header.jsp"></jsp:include>
<body>


<form action="<%=request.getContextPath()%>/Inscription" method="post">
		<label for=username> Nom d'utilisateur : </label><input type="text" pattern="^[a-zA-Z0-9]+$" maxLength="20" id="username" name="username" value="" required/>
		<br/>
		<label for="email"> Adresse Email : </label><input type="email" maxLength="50" id="email" name="email" value="" required/>
		<br/>
		<label for="mot_de_passe">Mot de passe : </label><input type="password" maxLength="30" id="mot_de_passe" name="password" value="" required/>
		<br/>
		<label for="confirm_password">Confirmez le mot de passe : </label><input type="password" maxLength="30" id="confirm_password" name="confirm_password" value="" required/>
		<br/>
		<label for="last_name">Nom : </label><input type="text" maxLength="50" id="last_name" name="last_name" value="" required/>
		<br/>
		<label for="first_name">Prénom : </label><input type="text" maxLength="50" id="first_name" name="first_name" value="" required/>
		<br/>
		<label for="phone">Téléphone : </label><input type="text" pattern="^[0-9]+$" maxLength="10" id="phone" name="phone" value="" required/>
		<br/>
		<label for="street">Rue : </label><input type="text" maxLength="50" id="street" name="street" value="" required/>
		<br/>
		<label for="postcode">Code postal : </label><input type="text" pattern="^[0-9]+$" maxLength="5" id="postcode" name="postcode" value="" required/>
		<br/>
		<label for="city">Ville : </label><input type="text" maxLength="50" id="city" name="city" value="" required/>
		<br/>
		
		
		<input type="submit" value="Créer"/>
	</form>
	<button type="button" onclick="window.location.href ='';">Annuler</button>
	
</body>
</html>