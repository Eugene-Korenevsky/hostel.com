<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>

 <fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/>
 <fmt:message key="message.wrong.email" var="wrongEmail" bundle="${rs}" />


<u:html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js">
        </script>
        <script src="resources/js/jquery.validate.js">
        </script>
        <script>
        $(document).ready(function() {
            $("#form").validate();
        });
        </script>
    <div id="body">
	<u:choseroom/>
	<c:if test="${ not empty message}"> 
			<p><fmt:message key="${message}" bundle="${rs}" /></p>
			</c:if>
        <div id="login">
               <form name="f" method="POST" action="/com.company-1.0-SNAPSHOT/j_spring_security_check">
               <input type="text" name="j_username"/>
               <input type="password" name="j_password"/>
               <input name="submit" type="submit"/>
               </form>

          <!-- <s:form id="form" method="POST" action="/com.company-1.0-SNAPSHOT/j_spring_security_check" modelAttribute ="user">
                <p><fmt:message key="login.form.email" bundle="${rs}" />
                <s:input path="email" type="email" name="j_username"/>
                <s:errors path="email"/>
                <p><fmt:message key="login.form.password" bundle="${rs}" />
                <s:input id="password" path="password" type="password" class="required"
                title="${ wrongEmail }" name="j_password"/>
                <s:errors path="password"/>


                <s:input path="name" type="hidden" value="default"/>
                <s:input path="surname" type="hidden" value="default"/>
                <p><input id="button" type="submit" value="<fmt:message key="login.button" bundle="${rs}" />"></p>
           </s:form>-->
			<form action="registerForm">
            <button id="button" type="submit"><fmt:message key="register.button" bundle="${rs}" /></button>
        </form>
        </div>
        


    </div>

</u:html>