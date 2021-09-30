<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="fr.eni.encheres.messages.business_exception_messages" var="errorMessages"/>

<jsp:include page="/WEB-INF/JSP/fragments/header.jsp"></jsp:include>

<h2>Supprimer votre profil ? </h2>
<form action="<%=request.getContextPath()%>/SuppressionUtilisateur" method="post">
	<div class="col-sm-12"><label for="password">Veuillez renseigner votre mot de passe avant confirmation de la suppression de votre compte </label><br/>
	<input type="password" maxLength="30" id="password" name="password" value="" /></div><br/>
	<a href="${pageContext.request.contextPath}/ModificationUtilisateur" class="btn button-navigation">Revenir en arrière</a>
	<input type="submit" class="btn button-navigation" value="Supprimer mon compte"/>
</form>
	
<c:forEach var="error" items="${errors}">
	<p >
		<fmt:message key="${error}" bundle="${errorMessages}"></fmt:message>
	</p>
</c:forEach>

<jsp:include page="/WEB-INF/JSP/fragments/footer.jsp">
		<jsp:param value="" name=""/>
</jsp:include>
