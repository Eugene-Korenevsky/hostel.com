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
    <link href="resources/css/help.css" type="text/css" rel="stylesheet" />
    <link href="resources/css/skeleton.css" rel="stylesheet" />
     <script src="resources/js/jquery-3.5.1.min.js"></script>
    <script src="resources/js/jquery.smartmenus.min.js">
    </script>
    <script>
        $(document).ready(function() {
            $(".sm").smartmenus();
            $("#orders1").hide();
            $("#reserves1").hide();
            $("#orders").hide();
            $("#reserves").hide();

            $("#showReserves").click(function(evt) {
                $("#reserves2").hide();
                $("#reserves1").show();
                $("#reserves").fadeIn(1000);

            });

            $("#hideReserves").click(function(evt) {
                $("#reserves1").hide();
                $("#reserves2").show();
                $("#reserves").hide();

            });
            $("#showOrders").click(function(evt) {
                $("#orders2").hide();
                $("#orders1").show();
                $("#orders").fadeIn(1000);

            });

            $("#hideOrders").click(function(evt) {
                $("#orders1").hide();
                $("#orders2").show();
                $("#orders").hide();

            })


        });
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
                               <a class="current-page" href="profile">cabinet</a>
                        </security:authorize>


                        <a href="#">About Us</a>
                        <a href="room">rooms</a>
                        <a class = "home"  href="/">Home</a>
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

                <div id="reserves2" class="data profile1">
                    <p>Your reserves</p>
                    <a id="showReserves" class="prof" href="#"><img src="resources/images/img/show_1_25X25.png"></a>
                </div>


                <div id="reserves1" class="data profile1">
                    <p>Your reserves</p>
                    <a id="hideReserves" class="prof" href="#"><img src="resources/images/img/hide_1_25X25.png"></a>
                    <div id="reserves">
                        <c:forEach var="reserve" items="${reserves}" varStatus="status">
                        <div>
                            <img class="img" src="resources/images/img1/2.jpg" width="250" height="150">
                        </div>
                        <p class="data">Number : <c:out value="${ reserve.room.number }"  /></p>
                        <p class="data">Price : <c:out value="${ reserve.totalPrice }"  /> $</p>
                        <p class="data">arrive date : <c:out value="${ reserve.dateIn }"  /></p>
                        <p class="data">leave date : <c:out value="${ reserve.dateOut }"  /></p>
                        </c:forEach>
                    </div>
                </div>


                <div id="orders2" class="data profile1">
                    <p>Your orders</p>
                    <a id="showOrders" class="prof" href="#"><img src="resources/images/img/show_1_25X25.png"></a>
                </div>



                <div id="orders1" class="data profile1">
                    <p>Your orders</p>
                    <a id="hideOrders" class="prof" href="#"><img src="resources/images/img/hide_1_25X25.png"></a>
                    <div id="orders">
                        <c:forEach var="order" items="${orders}" varStatus="status">
                        <div>
                            <img class="img" src="resources/images/img1/2.jpg" width="250" height="150">
                        </div>
                        <p class="data">Number : <c:out value="${ order.room.number }"  /></p>
                        <p class="data">Price : <c:out value="${ order.totalPrice }"  /> $</p>
                        <p class="data">arrive date : <c:out value="${ order.dateIn }"  /></p>
                        <p class="data">leave date : <c:out value="${ order.dateOut }"  /></p>
                        </c:forEach>

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

</html>