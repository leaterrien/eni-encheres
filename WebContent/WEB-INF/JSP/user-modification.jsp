<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="fr.eni.encheres.messages.business_exception_messages" var="errorMessages"/>

<!-- header -->
	<jsp:include page="/WEB-INF/JSP/fragments/header.jsp">
		<jsp:param value="modification" name="pageTitle" />
	</jsp:include>

<h1 class="my-5 d-flex ">Mon profil</h1>

<form action="<%=request.getContextPath()%>/ModificationUtilisateur" method="post">
	<div class="row">
		<p class="font-weight-bold">Modification des informations du compte : </p>
		<div class="col-md-6"><label class="form-label" for=username>Pseudo : </label><input class="form-control" type="text" pattern="^[a-zA-Z0-9]+$" maxLength="20" id="username" name="username" value="${utilisateur.pseudo}" required/></div>
		<div class="col-md-6"><label class="form-label" for="email"> Email : </label><input class="form-control" type="email" maxLength="50" id="email" name="email" value="${utilisateur.email}" required/></div>
		<div class="col-md-6"><label class="form-label" for="last_name">Nom : </label><input class="form-control" type="text" maxLength="50" id="last_name" name="last_name" value="${utilisateur.nom}" required/></div>
		<div class="col-md-6"><label class="form-label" for="first_name">Prénom : </label><input class="form-control" type="text" maxLength="50" id="first_name" name="first_name" value="${utilisateur.prenom}" required/></div>	
		<div class="col-md-6"><label class="form-label" for="phone">Téléphone : </label><input class="form-control" type="text" pattern="^[0-9]+$" maxLength="10" id="phone" name="phone" value="${utilisateur.telephone}"required/></div>	
		<div class="col-md-6"><label class="form-label" for="street">Rue : </label><input class="form-control" type="text" maxLength="50" id="street" name="street" value="${utilisateur.rue}" required/></div>	
		<div class="col-md-6"><label class="form-label" for="postcode">Code postal : </label><input class="form-control" type="text" pattern="^[0-9]+$" maxLength="5" id="postcode" name="postcode" value="${utilisateur.codePostal}" required/></div>	
		<div class="col-md-6"><label class="form-label" for="city">Ville : </label><input class="form-control" type="text" maxLength="50" id="city" name="city" value="${utilisateur.ville}" required/></div>	<br/>
	</div>
		<br/>
		<p class="font-weight-bold mb-3 mt-4">Modification du mot de passe : </p>
	<div class="row">
		<div class="col-md-6"><label class="form-label" for="password">Mot de passe actuel : </label><input class="form-control" type="password" maxLength="30" id="password" name="password" value="" /></div>
	</div>
	<div class="row">
		<div class="col-md-6"><label class="form-label" for="new_password">Nouveau mot de passe : </label><input class="form-control" type="password" maxLength="30" id="new_password" name="new_password" value="" /></div>	
		<div class="col-md-6"><label class="form-label" for="confirm_password">Confirmation : </label><input class="form-control" type="password" maxLength="50" id="confirm_password" name="confirm_password" value="" /></div>
	</div>
	<br/>
	<br/>
	<br/>
	<p><span class="user-show-label">Crédit : </span><span class="user-show-value">${utilisateur.credit}</span></p>
	<c:forEach var="error" items="${errors}">
		<p >
			<fmt:message key="${error}" bundle="${errorMessages}"></fmt:message>
		</p>
	</c:forEach>
	<div class="d-flex justify-content-center align-items-center">
		<a style = "display: block; margin: 5px" href="${pageContext.request.contextPath}/SuppressionUtilisateur" class="btn button-navigation">Supprimer mon compte</a>
		<input style = "margin: 5 px" type="submit" class="h-100 btn button-navigation" value="Enregistrer"/>
	</div>
</form>

<br/>


<jsp:include page="/WEB-INF/JSP/fragments/footer.jsp">
		<jsp:param value="" name=""/>
</jsp:include>