<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
</head>
<body>
	<!-- header -->
	<jsp:include page="/WEB-INF/JSP/fragments/header.jsp">
		<jsp:param value="connexion" name="pageTitle" />
	</jsp:include>
	
	<h1 class="my-5 d-flex justify-content-center">Identifiez-vous</h1>
	
	<c:if test="${!empty listErrors }">
		<p style="color:red;">Votre e-mail ou votre mot de passe n'est pas correct.</p>
	</c:if>
	
	<form method="post">
		<label for="login">Identifiant:</label>
		<input type="text" value="${cookieEnchInitL}" name="login" required/>
		<br>
		<label for="password">Mot de passe:</label>
		<input type="password" value="${cookieHcneTiniP}" name="password" required/>
		<br>
		<br>
		<button type="submit" value="Connexion" name="connexion">Connexion</button>
		<input type="checkbox" id="seSouvenirDeMoi" name="seSouvenirDeMoi" ${seSouvenirDeMoi ? 'checked':''} />
		<label for="seSouvenirDeMoi">Se souvenir de moi </label>
		<br>
		<a href="https://www.google.fr/">Mot de passe oublié</a>
		<br>
		<br>
		<input type="submit" value="Créer un compte" name="créer un compte" />
		<br>
	</form>
	
	<!-- footer -->
	<jsp:include page="/WEB-INF/JSP/fragments/footer.jsp">
			<jsp:param value="" name=""/>
	</jsp:include>

</body>
</html>




