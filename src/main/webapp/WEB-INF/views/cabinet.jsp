<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/> 
<fmt:setBundle basename = "access" var="access" scope="request"/>


<u:html>



    <div id="body">
	<u:choseroom/>
        <table id="table">
            <tbody id="tbody">
              <tr>
<td>
<td><security:authentication property="principal.username" /></td>
<security:authentication property="principal.username" var="name" />
<td><c:out value="${ name }" /></td>
</tr>  
            </tbody>
        </table>
    </div>
 </td>
    </div>

</u:html>