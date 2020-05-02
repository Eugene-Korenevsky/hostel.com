<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>

 <fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/> 
<u:html>

    <div id="body">
	<u:choseroom/>
	<fmt:message key="${ message }" bundle="${rs}" />
<form action="rooms.html" method"GET">
            <button type="submit"><fmt:message key="room.button" bundle="${rs}" /></button>
        </form>
    </div>
</u:html>
