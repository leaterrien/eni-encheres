<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Title : récupération du titre si défini, sinon utiliation du titre par défaut -->
<title>
	<c:choose>
		<c:when test="${! empty param.pageTitle}">
		<fmt:bundle basename="fr.eni.encheres.messages.titles">
			<fmt:message key="title.${param.pageTitle}"></fmt:message>
		</fmt:bundle>
		</c:when>
		<c:otherwise>
		<fmt:bundle basename="fr.eni.encheres.messages.titles">
			<fmt:message key="title.default"></fmt:message>
		</fmt:bundle>
		</c:otherwise>
	</c:choose>
</title>
<!-- material icon -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- google font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Montserrat" />
<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<!-- style.css -->
<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-principal">
		<a class="white-color mx-3 header-navbar-logo" href="${pageContext.request.contextPath}">ENI-Enchères</a>
	</nav>
	<div class="container">