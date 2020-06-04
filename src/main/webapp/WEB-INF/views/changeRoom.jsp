<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>

<fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/> 

<u:html>


    
    <div id="body">
	<u:choseroom />
	<c:if test="${ not empty message}"> 
			<p><fmt:message key="${message}" bundle="${rs}" /></p>
			</c:if>
        <div id="inputtext">
            <form method="POST" action="changeRoom">
			<fmt:message key="change.room.parameters" bundle="${rs}" />
                <p> <fmt:message key="room.class.message" bundle="${rs}" /><input type="text" name="class"></p>
                <p><fmt:message key="room.price" bundle="${rs}" /><input type="text" name="price"></p>
                <p><fmt:message key="room.number" bundle="${rs}" /><input type="number" name="number"></p>
                <p><fmt:message key="room.sits" bundle="${rs}" /><input type="number" name="sits"></p>
                <p><button value="${ roomId }" name="roomId" type="submit"><fmt:message key="change.room.button" bundle="${rs}" /></button></p>
            </form>
        </div>
    </div>


</u:html>