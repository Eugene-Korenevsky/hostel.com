<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
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
    <link href="resources/css/sm-core-css.css" rel="stylesheet">
    <link href="resources/css/sm-clean.css" rel="stylesheet">
    <link href="resources/css/main1.css" type="text/css" rel="stylesheet" />
    <link href="resources/css/skeleton.css" rel="stylesheet" />
    <link href="resources/css/help.css" rel="stylesheet" />
    <script src="resources/js/jquery-3.5.1.min.js"></script>
    <script src="resources/js/jquery.smartmenus.min.js"></script>
    <script src="resources/js/jquery.validate.js">
    </script>
</head>
<script>
    $(document).ready(function() {
        $("#form").validate({
            rules: {
                j_password: {
                    required: true,
                    minlength: 3
                },
                j_username: {
                    required: true,
                    email: true
                }
            },
            messages: {
                j_password: {
                    required: "Write your password",
                    minlength: "min 3 letters"
                },
                j_username: {
                    required: "Write your email",
                    email: "Wrong email adress"
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
                             <a class="current-page" href="loginForm">log In</a>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                              <a class="current-page" href="logout">log Out</a>
                        </security:authorize>
                        <security:authorize access="!isAuthenticated()">
                              <a href="registration.html">registratinion</a>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                               <a href="profile">cabinet</a>
                        </security:authorize>


                        <a href="#">About Us</a>
                        <a  href="rooms">rooms</a>
                        <a class="home" href="home">Home</a>
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

                <form id="form" class="form" name="f" method="POST" action="/com.company-1.0-SNAPSHOT/j_spring_security_check">
                    <p>
                        <label class="label" for="j_username">Email : </label>
                        <input type="email" name="j_username" required />
                        <label for="j_username" class="error"></label>
                    </p>
                    <p>
                        <label class="label" for="j_password"> Password : </label>
                        <input type="password" name="j_password" required minlength="3" />
                        <label for="j_password" class="error"></label>
                    </p>
                    <p><input id="submit" class="submit" name="submit" type="submit" value="submit" /></p>
                </form>
                <div class="registration">
                    <a class="button" href="registration">Registration</a>
                </div>
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