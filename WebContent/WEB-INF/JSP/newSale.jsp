<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
	<div class="row">
		<div class="row">
			<div class="card"> <img src="" alt="objet en vente"/> </div>
		</div>
		<div class="card"> <img src="" alt="objet en vente"/> </div>
		<div class="card"> <img src="" alt="objet en vente"/> </div>
		<div class="card"> <img src="" alt="objet en vente"/> </div>
		<div class="card"> <img src="" alt="objet en vente"/> </div>
		<div class="card"> <img src="" alt="objet en vente"/> </div>
		<div class="card"> <img src="" alt="objet en vente"/> </div>
		<div class="card"> <img src="" alt="objet en vente"/> </div>
		<div class="row">
			<div class="card"> <img src="" alt="objet en vente"/> </div>
		</div>
		<div class="card"> <img src="" alt="objet en vente"/> </div>
	
	</div>
	
	<form action="<%=request.getContextPath()%>/ServletNewSale" method="post">
		<div class = "form-group">
			<label for="item">Article :</label>
			<input type="text" name="Article" autofocus required/>
		</div>
		<div class = "form-group">
			<label for="description">Description :</label>
			<input type="text" name="Article" autofocus required/>
		</div>
			
			
			
			
		
		<label for="description">Description :</label>
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