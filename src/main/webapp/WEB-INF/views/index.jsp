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
    <link href="resources/css/normalize.css" rel="stylesheet" />
    <link href="resources/css/sm-core-css.css" rel="stylesheet">
    <link href="resources/css/sm-clean.css" rel="stylesheet">
    <link href="resources/css/slider.css" rel="stylesheet" />
    <link href="resources/css/main1.css" type="text/css" rel="stylesheet" />
    <link href="resources/css/skeleton.css" rel="stylesheet" />
    <link href="resources/css/logoAction.css" rel="stylesheet" />
    <script src="resources/js/jquery-3.5.1.min.js"></script>
    <script src="resources/js/jquery.smartmenus.min.js">
    </script>

</head>


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
                              <a href="registration">registratinion</a>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                               <a href="profile">cabinet</a>
                        </security:authorize>


                        <a href="#">About Us</a>
                        <a href="room">rooms</a>
                        <a class="home current-page" href="">Home</a>
                    </nav>
                </div>
            </div>




        </div>
    </header>
    <div class="container">
        <div class="row">
            <div class="six columns">
                <div class="news">
                    <p>
                        Hello
                    </p>
                </div>
            </div>
            <div class="six columns">
                <div class="slider" id="main-slider">
                    <!-- outermost container element -->
                    <div class="slider-wrapper">
                        <!-- innermost wrapper element -->
                        <div class="slide"> <img src="resources/images/img1/2.jpg" width="550" height="365"></div>
                        <!-- slides -->
                        <div class="slide"> <img src="resources/images/img1/5.jpg" width="550" height="365"></div>
                        <div class="slide"> <img src="resources/images/img1/3.jpg" width="550" height="365"></div>
                    </div>
                    <div class="slider-nav">
                        <!-- "Previous" and "Next" actions -->
                        <button class="slider-previous">Previous</button>
                        <button class="slider-next">Next</button>
                    </div>
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
<script src="resources/js/slideShow.js"></script>


</html>