<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>

 <fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/> 
<u:html>
    <div id="body">
        <u:choseroom/>
        <table >
		<th>Delet description</th>
		<th>Add description</th>
		<th>Room Descriptions</th>
		
		
		<tr>
		<td>
		<form method="POST" action="removeDescription">
		<select name="descriptionId" size="1">
        <c:forEach var="elem" items="${room.descriptions}" varStatus="status">
                           <option value="${ elem.id }"><c:out value="${ elem.description}"  /></option> 
						   </c:forEach>
        </select>
		<p>	
					<button id="button"  name="descriptionId" type="submit">Delete Description</button>
					</p>
        </form>
		
		</td>
		<td>
		<form method="POST" action="addDescription">
		<select name="descriptionId" size="1">
        <c:forEach var="elem" items="${descriptions}" varStatus="status">
                           <option value="${ elem.id }"><c:out value="${ elem.description }"  /></option> 
						   </c:forEach>
        </select>
		<p>	
					<button id="button"   type="submit">Add Description</button>
					</p>
        </form>
		 
		
		
		
		</td>
		<td>
		<c:forEach var="elem" items="${room.descriptions}" varStatus="status">
                        <p><c:out value="${ elem.description }"  /></p>
						   </c:forEach>
		
		
		
		</td>
		<td>
		<form method="POST" action="createDescription">
		<input id="input" type="text" name="description">
		<p><button id="button"   type="submit">Add Description</button></p>
		</form>
		
		</td>
		<td>
		<form method="POST" action="deleteDescription">
		<select name="descriptionId" size="1">
        <c:forEach var="elem" items="${descriptions}" varStatus="status">
                           <option value="${ elem.id }"><c:out value="${ elem.description }"  /></option> 
						   </c:forEach>
        </select>
		<p>	
					<button id="button"   type="submit">Delete Description from database</button>
					</p>
        </form>
		</td>
		</tr>
		
		</table>
		
    </div>

</body>
</u:html>