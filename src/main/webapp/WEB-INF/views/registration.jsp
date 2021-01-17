<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/>
<fmt:message key="message.wrong.email" var="wrongEmail" bundle="${rs}" />


<!DOCTYPE html>
<html>

<head>
    <title>the best hostel in the word</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="resources/css/normalize.css" rel="stylesheet" />
    <link href="resources/css/main1.css" type="text/css" rel="stylesheet" />
    <link href="resources/css/skeleton.css" rel="stylesheet" />
    <link href="resources/css/help.css" rel="stylesheet" />
    <script src="resources/js/jquery-3.5.1.min.js"></script>
    <script src="resources/js/jquery.validate.js">
    </script>
</head>
<script>
    $(document).ready(function() {

        $("#form").validate({
            rules: {
                password: {
                    required: true,
                    minlength: 3
                },
                j_username: {
                    required: true,
                    email: true
                },
                name: {
                    required: true,
                    minlength: 3
                },
                surname: {
                    required: true,
                    minlength: 3
                }
            },
            messages: {
                password: {
                    required: "Write your password",
                    minlength: "min 3 letters"
                },
                j_username: {
                    required: "Write your email",
                    email: "Wrong email adress"
                },
                name: {
                    required: "Write your name",
                    minlength: "min 3 letters"
                },
                surname: {
                    required: "Write your surname",
                    minlength: "min 3 letters"
                }
            },
            success: function(label) {
                label.addClass("valid").text("-")
            }
        });
    });
</script>

<body>
    <header>
        <div class="container">
            <div class="row">
                <div class="three columns">
                    <a href="home.html">
                        <img src="resources/images/img/hotel_1_1.png" class="logo">
                    </a>
                    <p class="text">.com</p>
                </div>



                <div class="nine columns">
                    <nav class="nav">
                           <security:authorize access="!isAuthenticated()">
                                <a href="loginForm"><fmt:message key="login.button" bundle="${rs}" /></a>
                           </security:authorize>
                           <security:authorize access="isAuthenticated()">
                                 <a href="logout"><fmt:message key="logout.button" bundle="${rs}" /></a>
                           </security:authorize>
                           <security:authorize access="!isAuthenticated()">
                                 <a class="current-page" href="registration.html"><fmt:message key="register.button" bundle="${rs}" /></a>
                           </security:authorize>
                           <security:authorize access="isAuthenticated()">
                                  <a href="profile"><fmt:message key="cabinet.button" bundle="${rs}" /></a>
                           </security:authorize>


                           <a href="#"><fmt:message key="about" bundle="${rs}" /></a>
                           <a  href="rooms"><fmt:message key="room.button" bundle="${rs}" /></a>
                           <a class="home" href="home.html"><fmt:message key="main.page" bundle="${rs}" /></a>
                    </nav>
                </div>
            </div>

        </div>
    </header>
    <div class="container">
    <c:if test="${ not empty message}">
               <div class="row">
                 <div class="twelve columns">
                    <p class="text" ><fmt:message key="${message}" bundle="${rs}" /></p>
                 </div>
        	   </div>
        </c:if>
        <div class="row">
            <div class="twelve columns">
                <s:form id="form" class="form" name="f" method="POST" action="registration"
                    modelAttribute="user">
                    <p><fmt:message key="register.button" bundle="${rs}" /></p>
                    <p>
                        <label class="label" for="j_username"><fmt:message key="login.form.email" bundle="${rs}" /> : </label>
                        <s:input path="email" type="email" name="j_username"/>
                        <label for="j_username" class="error"></label>
                        <label for="j_username" class="success" hidden></label>
                        <s:errors path="email"/>
                    </p>
                    <p>
                        <label class="label" for="j_password"><fmt:message key="login.form.password" bundle="${rs}" /> : </label>
                        <s:input path="password" type="password" name="password"/>
                        <label for="password" class="error"></label>
                        <label for="password" class="success" hidden></label>
                        <s:errors path="password"/>
                    </p>
                    <p>
                        <label class="label" for="name"><fmt:message key="login.form.name" bundle="${rs}" /> : </label>
                        <s:input path="name" type="text" name="name"  />
                        <label for="name" class="error"></label>
                        <label for="name" class="success" hidden></label>
                        <s:errors path="name"/>
                    </p>
                    <p>
                        <label class="label" for="surname"><fmt:message key="login.form.surname" bundle="${rs}" /> : </label>
                        <s:input path="surname" type="text" name="surname"/>
                        <label for="surname" class="error"></label>
                        <label for="surname" class="success" hidden></label>
                        <s:errors path="surname"/>
                    </p>
                    <p><input class="submit" name="submit" type="submit" value="<fmt:message key="confirm" bundle="${rs}" />" /></p>
                </s:form>


            </div>
        </div>

    </div>
    <footer>
        <div class="container">
            <div class="row">
                <div class="twelve columns">
                    <p id="text">Foot</p>
                </div>
            </div>
        </div>
    </footer>
</body>

</html>