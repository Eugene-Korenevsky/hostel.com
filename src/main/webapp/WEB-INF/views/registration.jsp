<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


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
                                <a href="loginForm">log In</a>
                           </security:authorize>
                           <security:authorize access="isAuthenticated()">
                                 <a href="logout">log Out</a>
                           </security:authorize>
                           <security:authorize access="!isAuthenticated()">
                                 <a href="registration.html">registratinion</a>
                           </security:authorize>
                           <security:authorize access="isAuthenticated()">
                                  <a href="profile">cabinet</a>
                           </security:authorize>


                           <a href="#">About Us</a>
                           <a class="current-page" href="roomsList">rooms</a>
                           <a class="home" href="home.html">Home</a>
                    </nav>
                </div>
            </div>

        </div>
    </header>
    <div class="container">
        <div class="row">
            <div class="twelve columns">
                <s:form id="form" class="form" name="f" method="POST" action="registration"
                    modelAttribute="user">
                    <p>Registration</p>
                    <p>
                        <label class="label" for="j_username">Email : </label>
                        <s:input path="email" type="email" name="j_username"/>
                        <label for="j_username" class="error"></label>
                        <label for="j_username" class="success" hidden></label>
                        <s:errors path="email"/>
                    </p>
                    <p>
                        <label class="label" for="j_password"> Password : </label>
                        <s:input path="password" type="password" name="password"/>
                        <label for="password" class="error"></label>
                        <label for="password" class="success" hidden></label>
                        <s:errors path="password"/>
                    </p>
                    <p>
                        <label class="label" for="name"> Name : </label>
                        <s:input path="name" type="text" name="name"  />
                        <label for="name" class="error"></label>
                        <label for="name" class="success" hidden></label>
                        <s:errors path="name"/>
                    </p>
                    <p>
                        <label class="label" for="surname"> Surname : </label>
                        <s:input path="surname" type="text" name="surname"/>
                        <label for="surname" class="error"></label>
                        <label for="surname" class="success" hidden></label>
                        <s:errors path="surname"/>
                    </p>
                    <p><input class="submit" name="submit" type="submit" value="submit" /></p>
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