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
               <c:forEach var="elem" items="${users}" varStatus="status">
               <tr>
                    <th><fmt:message key="room.number" bundle="${rs}" /></th>
                    <th><fmt:message key="room.class.message" bundle="${rs}" /></th>
                    <th><fmt:message key="room.price" bundle="${rs}" /></th>
                    <th><fmt:message key="room.sits" bundle="${rs}" /></th>
					<th></th>
                    <th></th>
					<th></th>
                </tr>
<tr id="row">
<td ><c:out value="${ elem.name }"  /></td>
<td ><c:out value="${ elem.surname }" /></td>
<td ><c:out value="${ elem.role.role }" /></td>
<td ><c:out value="${ elem.email }" /></td>
<td><form method="POST" action="changeRole">
		<select name="roleId" size="1">
        <c:forEach var="elem1" items="${roles}" varStatus="status">
                           <option value="${ elem1.id }"><c:out value="${ elem1.role }"  /></option> 
						   </c:forEach>
        </select>
		<p>	
					<button id="button" name="userId" value="${elem.id}" type="submit">Change role</button>
					</p>
					</form></td>
</tr>
</c:forEach> 
                
            </tbody>



        </table>
           
    
    </div>

</u:html>