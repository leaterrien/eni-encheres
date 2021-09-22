<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:if test="${! empty title}">${title}</</c:if> <c:if
		test="${empty title}">ENI-Encheres</c:if></title>
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
		<h3 class="white-color mx-3">ENI-Enchères</h3>
	</nav>
</body>
</html>