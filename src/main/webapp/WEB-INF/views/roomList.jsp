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
    <link href="resources/css/main1.css" type="text/css" rel="stylesheet" />
    <link href="resources/css/skeleton.css" rel="stylesheet" />
    <link href="resources/css/help.css" rel="stylesheet" />
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
                              <a href="registration.html">registratinion</a>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                               <a href="profile">cabinet</a>
                        </security:authorize>


                        <a href="#">About Us</a>
                        <a class="current-page" href="roomsList">rooms</a>
                        <a class="home" href="home">Home</a>
                    </nav>
                </div>
            </div>
        </div>
    </header>




    <div class="container">
        <div class="row ">
            <div class="twelve columns">
                search
            </div>
        </div>
        <div class="row rooms">
            <div class="four columns">
                Photo
            </div>
            <div class="one columns">
                Number
            </div>
            <div class="one columns">
                Sits
            </div>
            <div class="one columns">
                class
            </div>
            <div class="two columns">
                price
            </div>
            <div class="three columns">
                descriptions
            </div>

        </div>


        <c:forEach var="elem" items="${roomList}" varStatus="status">
        <div class="row rowdata">

            <div class="four columns imgd">
                <img class="img" src="resources/images/img1/1.jpg" width="250" height="150">
            </div>
            <div class="one columns data">
                <p class="preinfo">Number : </p>
                <p class="info"><c:out value="${ elem.number }"  /></p>
            </div>
            <div class="one columns data">
                <p class="preinfo">Sits : </p>
                <p class="info"><c:out value="${ elem.sits }" /></p>
            </div>
            <div class="one columns data">
                <p class="preinfo">Class : </p>
                <p class="info"><c:out value="${ elem.roomClass }" /></p>
            </div>
            <div class="two columns data">
                <p class="preinfo">Price : </p>
                <p class="info"><c:out value="${ elem.price }" /> $</p>
            </div>



            <div class="three columns data">
                <c:forEach var="desc" items="${elem.descriptions}" varStatus="status">
                <p class="desc"><c:out value="${ desc.description }"  /></p>
                </c:forEach>
                <p class="more"><a href="room/${elem.id}">More</a></p>
            </div>



        </div>
        </c:forEach>
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