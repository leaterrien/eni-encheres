<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- header -->
<jsp:include page="/WEB-INF/JSP/fragments/header.jsp">
	<jsp:param value="encheres-list" name="pageTitle" />
</jsp:include>

<h1 class="my-5 d-flex justify-content-center">Liste des enchères</h1>

<c:forEach var="article" items="${listeArticles}">
	<p>${article}</p>
</c:forEach>

<!-- footer -->
<jsp:include page="/WEB-INF/JSP/fragments/footer.jsp">
		<jsp:param value="" name=""/>
</jsp:include>