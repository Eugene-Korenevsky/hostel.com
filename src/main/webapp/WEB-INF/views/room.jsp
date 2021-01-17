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
             $.post("../orders",dates,function(data){

             }).done(function(response){
                document.location.href = "../profile";
             }).fail(function(response){

             });

                 return false;
             });


            $("#confirm").click(function(evt) {
                   dates.dateIn = $("#dateIn").val(),
                   dates.dateOut = $("#dateOut").val(),
                   dates.roomId = $("#roomId").val()
                   console.log(dates);


                $.post("../orders/isFree",dates,function(data){

                }).done(function(data){
                       if(data == "notFree"){
                          // $("#res").hide();
                           //$("#message1").hide();
                           //$("#message").hide();
                           $("#make").hide();
                           $("#message2").show();
                       }else if(data == "wrongDates"){
                           //$("#message").hide();
                           //$("#message2").hide();
                           //$("#res").hide();
                           $("#make").hide();
                           $("#message1").show();
                       }else{
                           $("#message").show();
                          // $("#message1").hide();
                          // $("#message2").hide();
                           $("#res").show();
                           $("#res").text(data);
                       }
                }).fail(function(response){
                    console.log(response);
                       if(response.status === 500){
                          // $("#message").hide();
                          // $("#message2").hide();
                           //$("#res").hide();
                           $("#message3").show();
                       }else if(response.status === 400){
                           //$("#message").hide();
                           //$("#message2").hide();
                           //$("#res").hide();
                           $("#message1").show();
                       }else{
                           $("#message4").show();
                           //$("#message1").hide();
                           //$("#message2").hide();
                           //$("#res").show();
                           //$("#res").text(data);
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
                $("#message2").hide();
                $("#message1").hide();
                $("#hide").show();
                $("#hello").hide();
                $("#message4").hide();
                $("#message3").hide();
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
                             <a href="../loginForm"><fmt:message key="login.button" bundle="${rs}" /></a>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                              <a href="../logout"><fmt:message key="logout.button" bundle="${rs}" /></a>
                        </security:authorize>
                        <security:authorize access="!isAuthenticated()">
                              <a href="../registration"><fmt:message key="register.button" bundle="${rs}" /></a>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                               <a href="../profile"><fmt:message key="cabinet.button" bundle="${rs}" /></a>
                        </security:authorize>

                        <a href="#"><fmt:message key="about" bundle="${rs}" /></a>
                        <a class="current-page" href="../rooms"><fmt:message key="room.button" bundle="${rs}" /></a>
                        <a class="home" href="../"><fmt:message key="main.page" bundle="${rs}" /></a>
                    </nav>
                </div>
            </div>

        </div>
    </header>
    <div class="container">


        <div class="row">
        <c:choose>
          <c:when test="${ not empty error}">
                 <div class="row">
                   <div class="twelve columns">
                      <p class="text" ><fmt:message key="${error}" bundle="${rs}" /></p>
                   </div>
          	   </div>
          	   <div class="row">
                          <div class="four columns">
                                       <p class="text" ></p>
                                      </div>
                           <div class="four columns">
                            <div> <img src="/com.company-1.0-SNAPSHOT/resources/images/error.jpg"></div>
                           </div>
                           <div class="four columns">
                                       <p class="text" ></p>
                           </div>
                        </div>
                        <div class="row">
                                   <div class="twelve columns">
                                     <p class="text" ></p>
                                   </div>
                   	   </div>
                   	   <div class="row">
                              <div class="twelve columns">
                                    <p class="text" ></p>
                              </div>
                   	   </div>
          </c:when>
          <c:otherwise>
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
                              <p><fmt:message key="room.number" bundle="${rs}" /> : <c:out value="${ room.number }"  /></p>
                          </div>
                          <div class="data">
                              <p><fmt:message key="room.sits" bundle="${rs}" /> : <c:out value="${ room.sits }"  /></p>
                          </div>
                          <div class="data">
                              <p><fmt:message key="room.class.message" bundle="${rs}" /> : <c:out value="${ room.roomClass }"  /></p>
                          </div>
                          <div class="data">
                              <p><fmt:message key="room.price" bundle="${rs}" /> : <c:out value="${ room.price }"  /> $</p>
                          </div>
                          <div class="data1">
                              <p><fmt:message key="create.room.parameters" bundle="${rs}" /> :
                                  <c:forEach var="desc" items="${room.descriptions}" varStatus="status">
                                  <p class="desc1"><c:out value="${ desc.description }"  /></p>
                                  </c:forEach>
                              </p>
                          </div>
                          <div>
                              <a class="previous" href="../rooms"><fmt:message key="cancel" bundle="${rs}" /></a>
                              <a id="makeOrder" class="makeorder" href="#"><fmt:message key="make.order" bundle="${rs}" /></a>
                          </div>
                      </div>

          </c:otherwise>
        </c:choose>





        </div>


        <div class="container chooseDateDialog" id="hello" title="Chose dates">
            <div id="hide" class="row">
                <label class="label date" for="dateIn"><fmt:message key="date.arrive" bundle="${rs}" /></label>
                <input id="dateIn" name="dateIn" type="date">
                <label class="label date" for="dateOut"><fmt:message key="date.leave" bundle="${rs}" /></label>
                <input id="dateOut" name="dateOut" type="date">
                <input id="roomId" value="<c:out value="${ room.id }"  />" type="hidden">
                <a id="confirm" class="makeorder dialog" href="#"><fmt:message key="make.order" bundle="${rs}" /></a>
                <a id="cancel" class="previous dialog" href="#"><fmt:message key="cancel" bundle="${rs}" /></a>
            </div>

            <div id="hide1" class="row">
                <p id="message" hidden><fmt:message key="total.price" bundle="${rs}" />
                <p id="res" class="desc1"> </p>
                </p>
                <p id="message1" hidden><fmt:message key="message.un.correct.dates" bundle="${rs}" />
                </p>

                <p id="message3" hidden><fmt:message key="error.message" bundle="${rs}" />
                </p>
                <p id="message4" hidden><fmt:message key="entity.not.exist" bundle="${rs}" />
                </p>



                <p id="message2" hidden><fmt:message key="message.is.already.reserved" bundle="${rs}" /></p>
                <security:authorize access="!isAuthenticated()">
                   <p><fmt:message key="login.first" bundle="${rs}" /></p>
                </security:authorize>

                <div id="make"><security:authorize access="hasRole('user')">
                <a id="confirm1" class="makeorder dialog" href="orderMaker" hidden><fmt:message key="make.order" bundle="${rs}" /></a>
                </security:authorize>
                </div>
                <a id="cancel1" class="previous dialog" href="#"><fmt:message key="cancel" bundle="${rs}" /></a>
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