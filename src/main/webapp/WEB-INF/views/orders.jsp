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
			<c:if test="${ not empty message}"> 
			<p><fmt:message key="${message}" bundle="${rs}" /></p>
			</c:if>
               <c:forEach var="elem" items="${orders}" varStatus="status">
   <tr>
                    <th></th>
                    <th></th>
                    <th><fmt:message key="date.arrive" bundle="${rs}" /></th>
                    <th><fmt:message key="date.leave" bundle="${rs}" /></th>
					<th><fmt:message key="room.number" bundle="${rs}" /></th>
                </tr>          
<tr id="row">
<td><c:out value="${ elem.user.name }" /></td>
<td><c:out value="${ elem.user.surname }"  /></td>
<td><fmt:formatDate value="${elem.dateIn}" />
	<fmt:formatDate value="${elem.dateIn}" type="time" timeStyle="short" /></td>
<td><fmt:formatDate value="${elem.dateOut}" />
	<fmt:formatDate value="${elem.dateOut}" type="time" timeStyle="short" /></td>
<td><c:out value="${ elem.room.number }" /></td>

<td><c:choose>
<c:when test="${user.role.role eq access2}">
 <form  action="deleteOrder" method="POST" >
					<button id="button" value="${ elem.id }" name="id" type="submit"><fmt:message key="cancel.order.button" bundle="${rs}" /></button>
                </form>
</c:when>
<c:when test="${user.role.role eq access1}">
<td><form  action="confirmReserve" method="POST" >
					<button id="button" value="${ elem.id }" name="id" type="submit"><fmt:message key="confirm.order.button" bundle="${rs}" /></button>
                </form></td>
<td><form  action="deleteOrder" method="POST" >
					<button id="button" value="${ elem.id }" name="id" type="submit"><fmt:message key="cancel.order.button" bundle="${rs}" /></button>
                </form></td>
</c:when>
<c:otherwise>
	    <form  action="loginForm.html">
                    <button id="button" type="submit"><fmt:message key="login.button" bundle="${rs}" /></button>
                </form>
	</c:otherwise>
</c:choose>

</c:forEach></td>
</tr>

                
            </tbody>



        </table>
    </div>


</u:html>