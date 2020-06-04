<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>

 <fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/> 
 
<u:html>

    <div id="body">
        <u:choseroom/>
        <table id="table">
            <tbody id="tbody">
               <c:forEach var="elem" items="${reserves}" varStatus="status">
              <tr>
                    <th></th>
                    <th></th>
                    <th><fmt:message key="date.arrive" bundle="${rs}" /></th>
                    <th><fmt:message key="date.leave" bundle="${rs}" /></th>
                    <th><fmt:message key="room.sits" bundle="${rs}" /></th>
                    <th><fmt:message key="room.number" bundle="${rs}" /></th>
                </tr>
<tr id="row">
<td><c:out value="${ elem.user.name }" /></td>
<td><c:out value="${ elem.user.surname }"  /></td>
<td><fmt:formatDate value="${elem.dateIn}" />
<fmt:formatDate value="${elem.dateIn}" type="time" timeStyle="short" /></td>
<td><fmt:formatDate value="${elem.dateOut}" />
<fmt:formatDate value="${elem.dateOut}" type="time" timeStyle="short" /></td>
<td><c:out value="${ elem.room.sits }" /></td>
<td><c:out value="${ elem.room.number }" /></td>
<td><form   action="deleteReserve" method="POST" >
					<button id="button" value="${ elem.id }" name="id" type="submit"><fmt:message key="cancel.reserve.button" bundle="${rs}" /></button>
                </form></td>
</tr>
</c:forEach>
                
            </tbody>



        </table>
    </div>

</u:html>