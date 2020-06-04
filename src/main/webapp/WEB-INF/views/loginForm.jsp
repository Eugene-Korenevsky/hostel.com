<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>

 <fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/> 
<u:html>

    <div id="body">
	<u:choseroom/>
	<c:if test="${ not empty message}"> 
			<p><fmt:message key="${message}" bundle="${rs}" /></p>
			</c:if>
        <div id="login">
            <form method="POST" action="login">
                <p><fmt:message key="login.form.email" bundle="${rs}" /><input id="button" type="email" name="email"></p>
                <p><fmt:message key="login.form.password" bundle="${rs}" /><input id="button" type="password" name="password"></p>
                <p><input id="button" type="submit" value="<fmt:message key="login.button" bundle="${rs}" />"></p>
            </form>
			<form action="registerForm">
            <button id="button" type="submit"><fmt:message key="register.button" bundle="${rs}" /></button>
        </form>
        </div>
        


    </div>

</u:html>