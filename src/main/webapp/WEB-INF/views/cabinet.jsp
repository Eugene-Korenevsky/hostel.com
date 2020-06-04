<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>

<fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/> 
<fmt:setBundle basename = "resources.access" var="access" scope="request"/>
 <fmt:message key="access.first" var="access1" bundle="${access}" />
 <fmt:message key="access.second" var="access2" bundle="${access}" />

<u:html>



    <div id="body">
	<u:choseroom/>
        <table id="table">
            <tbody id="tbody">
              <tr>
<td><c:choose>
 <c:when test="${user.role.role eq access1}">
    <form method="GET" action="findUserOrders">
            <button id="button" type="submit"><fmt:message key="order.button" bundle="${rs}" /></button>
        </form>
		<br/>
		<form action="findUserReserves" method="GET">
            <button id="button" type="submit"><fmt:message key="reserve.button" bundle="${rs}" /></button>
			</form>
			<br/>
		<form action="roomsList" method"GET">
            <button id="button" type="submit"><fmt:message key="room.button" bundle="${rs}" /></button>
        </form>
		<br/>
		<form action="usersList" method"GET">
            <button id="button" type="submit">Users</button>
        </form>
    </c:when>
	<c:when test="${user.role.role eq access2}">
    <form method="GET" action="findUserOrders">
            <button id="button" type="submit"><fmt:message key="my.orders.button" bundle="${rs}" /></button>
        </form>
		<br/>
		<form action="findUserReserves" method="GET">
            <button id="button" type="submit"><fmt:message key="my.reserve.button" bundle="${rs}" /></button>
			</form>
			<br/>
		<form action="roomsList" method"GET">
            <button id="button" type="submit"><fmt:message key="room.button" bundle="${rs}" /></button>
        </form>
    </c:when>
	<c:otherwise>
	    <form  action="login>
                    <button id="button" type="submit"><fmt:message key="login.button" bundle="${rs}" /></button>
                </form>
	</c:otherwise>
	</c:choose>
	</td>
<td><c:out value="${ user.name }"  /></td>
<td><c:out value="${ user.surname }" /></td>
<td><c:out value="${ user.role.role }" /></td>
<td></td>
</tr>  
            </tbody>
        </table>
    </div>
 </td>
    </div>

</u:html>