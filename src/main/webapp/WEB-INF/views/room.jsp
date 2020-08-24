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
    <link href="../resources/css/skeleton.css" rel="stylesheet" />
    <link href="../resources/css/help.css" rel="stylesheet" />
    <link href="../resources/css/slider.css" rel="stylesheet" />
    <link href="../resources/css/jquery-ui.css" rel="stylesheet">
    <script src="../resources/js/jquery-3.5.1.min.js"></script>
    <script src="../resources/js/jquery.smartmenus.min.js">
    </script>
    <script src="../resources/js/jquery-ui.min.js">
    </script>
    <script>
        $(document).ready(function() {
            var dates = {};
            $("#hello").hide();
            $("#shouldLogin").hide();
             $("#confirm1").click(function(evt) {
             $.post("../order",dates,function(data){
                document.location.href = "../profile";
             });

                 return false;
             });


            $("#confirm").click(function(evt) {
                   dates.dateIn = $("#dateIn").val(),
                   dates.dateOut = $("#dateOut").val(),
                   dates.roomId = $("#roomId").val()


                $.post("../order/isFree",dates,function(data){
                  if(data == "notFree"){
                     $("#res").hide();
                     $("#message1").hide();
                     $("#message").hide();
                     $("#message2").show();
                  }else if(data == "wrongDates"){
                     $("#message").hide();
                     $("#message2").hide();
                     $("#res").hide();
                     $("#message1").show();
                  }else{
                  $("#message").show();
                  $("#message1").hide();
                  $("#message2").hide();
                  $("#res").show();
                  $("#res").text(data);
                  }
                });
                $("#hide").hide();
                $("#hide1").show();

            });
            $("#makeOrder").click(function(evt) {
                $("#hello").show();
                $("#hide1").hide();
            });
            $("#cancel").click(function(evt) {
                $("#hello").hide();
            });

            $("#cancel1").click(function(evt) {
                $("#hide").show();
                $("#hello").hide();
            });
        });
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
                               <a href="../profile">cabinet</a>
                        </security:authorize>

                        <a href="#">About Us</a>
                        <a class="current-page" href="../room">rooms</a>
                        <a class="home" href="../">Home</a>
                    </nav>
                </div>
            </div>

        </div>
    </header>
    <div class="container">


        <div class="row">
            <div class="six columns">
                <div class="slider" id="main-slider">
                    <!-- outermost container element -->
                    <div class="slider-wrapper">
                        <!-- innermost wrapper element -->
                        <div class="slide"> <img src="../resources/images/img1/2.jpg" width="550" height="365"></div>
                        <!-- slides -->
                        <div class="slide"> <img src="../resources/images/img1/5.jpg" width="550" height="365"></div>
                        <div class="slide"> <img src="../resources/images/img1/3.jpg" width="550" height="365"></div>
                    </div>
                    <div class="slider-nav">
                        <button class="slider-previous">Previous</button>
                        <button class="slider-next">Next</button>
                    </div>
                </div>
            </div>
            <div class="six columns rowdesc">
                <div class="data">
                    <p>Number : <c:out value="${ room.number }"  /></p>
                </div>
                <div class="data">
                    <p>Sits : <c:out value="${ room.sits }"  /></p>
                </div>
                <div class="data">
                    <p>Class : <c:out value="${ room.roomClass }"  /></p>
                </div>
                <div class="data">
                    <p>Price : <c:out value="${ room.price }"  /> $</p>
                </div>
                <div class="data1">
                    <p>Descriptions :
                        <c:forEach var="desc" items="${room.descriptions}" varStatus="status">
                        <p class="desc1"><c:out value="${ desc.description }"  /></p>
                        </c:forEach>
                    </p>
                </div>
                <div>
                    <a class="previous" href="room/${elem.id}">Previous</a>
                    <a id="makeOrder" class="makeorder" href="#">Make Order</a>
                </div>
            </div>

        </div>


        <div class="container chooseDateDialog" id="hello" title="Chose dates">
            <div id="hide" class="row">
                <p>This dioloq window.You can moove it in the window</p>
                <label class="label date" for="dateIn">Date In </label>
                <input id="dateIn" name="dateIn" type="date">
                <label class="label date" for="dateOut">Date Out </label>
                <input id="dateOut" name="dateOut" type="date">
                <input id="roomId" value="<c:out value="${ room.id }"  />" type="hidden">
                <a id="confirm" class="makeorder dialog" href="#">Make Order</a>
                <a id="cancel" class="previous dialog" href="#">Previous</a>
            </div>

            <div id="hide1" class="row">
                <p id="message">It will be cost for you
                <p id="res" class="desc1"> </p>
                </p>
                <p id="message1">Wrong dates
                </p>
                <p id="message2">not free</p>
                <security:authorize access="!isAuthenticated()">
                   <p>You should login</p>
                </security:authorize>

                <security:authorize access="hasRole('user')">
                <a id="confirm1" class="makeorder dialog" href="orderMaker">Make Order</a>
                </security:authorize>
                <a id="cancel1" class="previous dialog" href="#">Previous</a>
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
<script src="../resources/js/slideShow.js"></script>


</html>