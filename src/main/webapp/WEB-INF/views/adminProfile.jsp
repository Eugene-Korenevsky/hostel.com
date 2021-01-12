<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
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
                             <a href="../loginForm">log In</a>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                              <a href="../logout">log Out</a>
                        </security:authorize>
                        <security:authorize access="!isAuthenticated()">
                              <a href="../registration">registratinion</a>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                               <a class="current-page" href="profile">cabinet</a>
                        </security:authorize>


                        <a href="#">About Us</a>
                        <a  href="../rooms">rooms</a>
                        <a  class="home" href="../home">Home</a>
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
                    <p>Your role : <c:out value="${ user.role.role }"  /></p>
                </div>
                <div class="data profile1">
                    <p> Name : <c:out value="${ user.name }"  /></p>
                    <p class="more"><a href="#">Change</a></p>
                </div>
                <div class="data profile1">
                    <p>Surname : <c:out value="${ user.surname }"  /></p>
                    <p class="more"><a href="#">Change</a></p>
                </div>
                <div class="data profile1">
                    <p>Email : <c:out value="${ user.email }"  /></p>
                    <p class="more"><a href="#">Change</a></p>
                </div>
                <div class="data profile1">
                    <p>Here you can change your password</p>
                    <p class="more"><a href="#">Change</a></p>
                </div>

            </div>
            <div class="five columns">
                <nav class="pagebutton">
                    <p><a href="rooms">Rooms</a></p>
                    <p><a href="reserves">Reserves</a></p>
                    <p><a href="orders">Orders</a></p>
                    <p><a href="users">Users</a></p>
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