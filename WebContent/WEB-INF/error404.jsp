<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<jsp:include page="/WEB-INF/header.jsp">
	<jsp:param value="user-show" name="pageTitle" />
</jsp:include>

<h1 class="my-5">Erreur 404</h1>

<p class="error-page-principal-message">La page que vous avez demandée n'existe pas</p>

<!-- bouton de navigation vers l'accueil -->
<jsp:include page="button-home.jsp">
	<jsp:param value="" name=""/>
</jsp:include>

<!-- footer -->
<jsp:include page="/WEB-INF/footer.jsp">
		<jsp:param value="" name=""/>
</jsp:include>