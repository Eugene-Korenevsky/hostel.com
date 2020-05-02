
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>

<fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/>

<u:html>

    <div id="body">
	<u:choseroom/>
        <div id="inputtext">
            <form method="POST" action="confirmOrder">
                        <p><input id="loginButton" type="submit" value="<fmt:message key="make.order.button" bundle="${rs}" />"></p>
            </form>
			<td><c:out value="${ elem.user.name }" /></td>
			<p><c:out value ="${ order.dateIn }" /></p>
			<p><c:out value ="${ order.dateOut }" /></p>
			<p><c:out value ="${ order.user.name }" /></p>
			<p><c:out value ="${ order.room.number }" /></p>
           <p><fmt:message key="message.order.cost" bundle="${rs}" /><c:out value="${ price }" /></p>
        </div>
    </div>


</u:html>