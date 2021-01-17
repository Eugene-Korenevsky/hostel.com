<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/>

<!DOCTYPE html>
<html>

<head>
    <title>the best hostel in the word</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../resources/css/normalize.css" rel="stylesheet" />
    <link href="../resources/css/sm-core-css.css" rel="stylesheet">
    <link href="../resources/css/sm-clean.css" rel="stylesheet">
    <link href="../resources/css/main1.css" type="text/css" rel="stylesheet" />
    <link href="../resources/css/help.css" type="text/css" rel="stylesheet" />
    <link href="../resources/css/skeleton.css" rel="stylesheet" />
    <script src="../resources/js/jquery-3.5.1.min.js"></script>
    <script src="../resources/js/jquery.smartmenus.min.js">
    </script>
</head>


<body>
    <header>
        <div class="container">
            <div class="row">
                <div class="three columns">
                    <a href="home.html">
                        <img src="../resources/images/img/hotel_1_1.png" class="logo">
                    </a>
                    <p class="text">.com</p>
                </div>



                <div class="nine columns">
                    <nav class="nav">
                        <security:authorize access="!isAuthenticated()">
                             <a href="loginForm"><fmt:message key="login.button" bundle="${rs}" /></a>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                              <a href="../logout"><fmt:message key="logout.button" bundle="${rs}" /></a>
                        </security:authorize>
                        <security:authorize access="!isAuthenticated()">
                              <a href="registration.html"><fmt:message key="registration.button" bundle="${rs}" /></a>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                               <a class="current-page" href="profile"><fmt:message key="cabinet.button" bundle="${rs}" /></a>
                        </security:authorize>


                        <a href="#"><fmt:message key="about" bundle="${rs}" /></a>
                        <a href="../rooms"><fmt:message key="room.button" bundle="${rs}" /></a>
                        <a class="home" href="../"><fmt:message key="main.page" bundle="${rs}" /></a>
                    </nav>
                </div>
            </div>

        </div>
    </header>
    <div class="container">
        <div class="row">
            <div class="two columns profile">

            </div>
            <div class="five columns">
                <div class="data profile1">
                    <p><fmt:message key="user.role" bundle="${rs}" /> : <c:out value="${ user.role.role }"  /></p>
                </div>
                <div class="data profile1">
                    <p><fmt:message key="login.form.name" bundle="${rs}" /> : <c:out value="${ user.name }"  /></p>
                </div>
                <div class="data profile1">
                    <p><fmt:message key="login.form.surname" bundle="${rs}" /> : <c:out value="${ user.surname }"  /></p>
                </div>
                <div class="data profile1">
                    <p><fmt:message key="login.form.email" bundle="${rs}" /> : <c:out value="${ user.email }"  /></p>
                </div>

            </div>
            <div class="five columns">
                <nav class="pagebutton">
                    <p><a href="rooms"><fmt:message key="room.button" bundle="${rs}" /></a></p>
                    <p><a href="reserves"><fmt:message key="reserves.button" bundle="${rs}" /></a></p>
                    <p><a href="orders"><fmt:message key="order.button" bundle="${rs}" /></a></p>
                    <p><a href="users"><fmt:message key="user.button" bundle="${rs}" /></a></p>
                </nav>
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