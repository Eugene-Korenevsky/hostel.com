<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>

 <fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/> 
 <fmt:setBundle basename = "access" var="access" scope="request"/>
 <fmt:message key="access.first" var="access1" bundle="${access}" />
<u:html>

    <div id="body">
        <u:choseroom/>
        <table id="table">
            <tbody id="tbody">
               <tr>
                    
                    <th><fmt:message key="room.number" bundle="${rs}" /></th>
                    <th><fmt:message key="room.class.message" bundle="${rs}" /></th>
                    <th><fmt:message key="room.price" bundle="${rs}" /></th>
                    <th><fmt:message key="room.sits" bundle="${rs}" /></th>
                    <th></th>
                </tr>
<tr>

<td><c:out value="${ room.number }"  /></td>
<td><c:out value="${ room.roomClass }" /></td>
<td><c:out value="${ room.price }" /></td>
<td><c:out value="${ room.sits }" /></td>
<td>
<c:if test="${user.role.role eq access1}">
          <p> <form  action="changeRoomForm" method="POST" >
					<button id="button" value="${ room.id }" name="roomId" type="submit"><fmt:message key="change.room.button" bundle="${rs}" /></button>
                </form></p>
			<p>	<form  action="changeDescriptions" method="POST" >
					<button id="button" value="${ room.id }" name="roomId" type="submit">Change description</button>
                </form></p>
				</c:if>
</td>
<td>
<c:choose>
<c:when test="${room.id eq '28'}">
   <img src="resources/images/1" >
</c:when>
<c:when test="${room.id eq '18'}">
   <img src="resources/images/2.jpg" >
</c:when>
<c:when test="${room.id eq '19'}">
   <img src="resources/images/3.jpg" >
</c:when>
<c:when test="${room.id eq '29'}">
   <img src="resources/images/4.jpg" >
</c:when>
<c:when test="${room.id eq '31'}">
   <img src="resources/images/5.jpg" >
</c:when>
<c:otherwise>
<img src="resources/images/6.jpg" >
</c:otherwise>
</c:choose>
</td>
<td>
<c:forEach var="elem" items="${room.descriptions}" varStatus="status">
<p><c:out value="${ elem.description }"  /></p>
</c:forEach>
</td>
</tr>
                
            </tbody>



        </table>
    </div>

</u:html>