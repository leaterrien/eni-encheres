<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="fr.eni.encheres.messages.business_exception_messages" var="errorMessages"/>


<!-- header -->
<jsp:include page="/WEB-INF/header.jsp">
	<jsp:param value="user-show" name="pageTitle" />
</jsp:include>

<h1 class="my-5">Erreur 500</h1>

<p class="error-page-principal-message">Nous avons rencontré un problème :</p>

<!-- affichage de la liste des erreurs rencontrées -->
<c:forEach var="error" items="${errors}">
	<p>
		<fmt:message key="${error}" bundle="${errorMessages}"></fmt:message>
	</p>
</c:forEach>

<!-- bouton de navigation vers l'accueil -->
<jsp:include page="button-home.jsp">
	<jsp:param value="" name=""/>
</jsp:include>

<!-- footer -->
<jsp:include page="/WEB-INF/footer.jsp">
		<jsp:param value="" name=""/>
</jsp:include>