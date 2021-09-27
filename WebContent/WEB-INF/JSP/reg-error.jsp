<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="fr.eni.encheres.messages.business_exception_messages" var="errorMessages"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Erreur formulaire</title>
</head>
<body>

<c:forEach var="error" items="${errors}">
	<p>
		<fmt:message key="${error}" bundle="${errorMessages}"></fmt:message>
	</p>
</c:forEach>
	

</body>
</html>