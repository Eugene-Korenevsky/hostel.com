<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>

 <fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/> 
 <fmt:setBundle basename = "access" var="access" scope="request"/>
 <fmt:message key="access.first" var="access1" bundle="${access}" />
 <fmt:message key="access.second" var="access2" bundle="${access}" />
 
<u:html>
    <div id="body">
        <u:choseroom/>
        <table id="table">
            <tbody id="tbody">
               <c:forEach var="elem" items="${roomList}" varStatus="status">
               <tr>
                    <th><fmt:message key="room.number" bundle="${rs}" /></th>
                    <th><fmt:message key="room.class" bundle="${rs}" /></th>
                    <th><fmt:message key="room.price" bundle="${rs}" /></th>
                    <th><fmt:message key="room.sits" bundle="${rs}" /></th>
					<th></th>
                    <th></th>
					<th></th>
                </tr>
<tr>
<td><c:out value="${ elem.number }"  /></td>
<td><c:out value="${ elem.roomClass }" /></td>
<td><c:out value="${ elem.price }" /></td>
<td><c:out value="${ elem.sits }" /></td>
<c:choose>
 <c:when test="${user.role.role eq access1}">
    <td><form  action="findRoom" method="GET" >
					<button value="${ elem.id }" name="id" type="submit"><fmt:message key="more.button" bundle="${rs}" /></button>
                </form></td>
<td><form  action="addRoomForm">
					<button  type="submit"><fmt:message key="add.room.button" bundle="${rs}" /></button>
                </form></td>
<td><form  action="deleteRoom" method="POST" >
					<button value="${ elem.id }" name="id" type="submit"><fmt:message key="delete.room.button" bundle="${rs}" /></button>
                </form></td>
				 </c:when>
 <c:when test="${user.role.role eq access2}">
<td><form  action="findRoom" method="GET" >
					<button value="${ elem.id }" name="id" type="submit"><fmt:message key="more.button" bundle="${rs}" /></button>
                </form></td>			
				
<td><form  action="makeOrder" method="POST" >
                    <p>date in  : <input type="date" name="dateIn" </p>
					<p>time in : <input type="time" name="timeIn" </p>
                    <p> date out : <input type="date" name="dateOut" </p>
					<p>time out : <input type="time" name="timeOut" </p>
					<button value="${ elem.id }" name="roomId" type="submit"><fmt:message key="make.order.button" bundle="${rs}" /></button>
					
                </form></td>
 </c:when>
	<c:otherwise>
	   <td><form  action="findRoom" method="GET" >
					<button value="${ elem.id }" name="id" type="submit"><fmt:message key="more.button" bundle="${rs}" /></button>
                </form></td>
	</c:otherwise>
	</c:choose></td>
</tr>
</c:forEach> 
                
            </tbody>



        </table>
		
    </div>

</body>
</u:html>