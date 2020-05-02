<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <div id="inputtext">
            <form method="POST" action="register">
                <p><fmt:message key="login.form.name" bundle="${rs}" /><input type="text" name="name"></p>
                <p><fmt:message key="login.form.surname" bundle="${rs}" /><input type="text" name="surname"></p>
                <p><fmt:message key="login.form.email" bundle="${rs}" /><input type="email" name="email"></p>
                <p><fmt:message key="login.form.password" bundle="${rs}" /><input type="password" name="password"></p>
                <p><input id="button" type="submit" value="<fmt:message key="register.button" bundle="${rs}" />"></p>
            </form>
        </div>
    </div>

</u:html>