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
    <link href="../resources/css/help.css" type="text/css" rel="stylesheet" />
    <script src="../resources/js/jquery-3.5.1.min.js"></script>
    <script src="../resources/js/jquery.smartmenus.min.js">
    </script>
    <script>
      $(document).ready(function() {
          var order1;
          var reserve = {};
          var dates = {};
          $(".confirm").on("click",function(){
               var value1 = $(this).attr('href');
               $("#confirmOrder").show();
               $.getJSON("orders/"+value1,function( order ){
                   console.log(order);
                   reserve.id = order.id;
                   reserve.user = order.user;
                   reserve.room = order.room;
                   reserve.totalPrice = order.totalPrice;
                   reserve.dateIn = order.dateIn;
                   reserve.dateOut = order.dateOut;
                   dates.dateIn = order.dateIn;
                   dates.dateOut = order.dateOut;
                   dates.roomId = order.room.id;
                   dates.orderId = order.id;
                   console.log(dates.dateIn);
                   console.log(dates.dateOut);
                   console.log(dates.roomId);
                   result_json = JSON.stringify(reserve);
                   console.log(result_json);
                   $.post("reserves/isFree",dates,function(data){
                      console.log(data);
                      if(data == "notFree"){
                         $("#message1").show();
                      }else{
                         $("#message").show();
                         $("#res").show();
                         $("#res").text(data);
                      }
                   });

               });
             return false;
          });

          $("#cancelCO").click(function() {
            $("#confirmOrder").hide();
            $("#message1").hide();
            $("#message").hide();
            $("#res").hide();
            return false;
          });


           $("#confirmOrder1").click(function() {
                      $("#message1").hide();
                      $("#message").hide();
                      $("#res").hide();
                      result_json = JSON.stringify(reserve);
                           $.ajax({
                              type: 'POST',
                              url: 'reserves',
                              contentType: 'application/json',
                              data: result_json,
                              success: function(data) {
                                   console.log("done");
                                   document.location.href = "reserves";
                              },
                              error:  function(){
                                 alert('Ошибка!');
                              }
                           });
                      return false;
           });


          $(".deleteR").on("click", function() {
                 var value1 = $(this).attr('href');
                 console.log(value1);
                 $("#delete").show();
                 url1 = value1;
                 console.log("url = "+url1);
                 return false;
          });

          $("#deleteForm").click(function(event) {
                 event.preventDefault();
                 var req = new XMLHttpRequest();
                 req.open("DELETE", "orders/" + url1, false);
                 req.send(null);
                 document.location.href = "orders";
          });
          $("#cancelD").click(function() {
                  $("#delete").hide();
                  return false;
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
                             <a href="loginForm">log In</a>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                              <a href="logout">log Out</a>
                        </security:authorize>
                        <security:authorize access="!isAuthenticated()">
                              <a href="registration.html">registratinion</a>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                               <a class="current-page" href="profile">cabinet</a>
                        </security:authorize>


                        <a href="#">About Us</a>
                        <a href="../rooms">rooms</a>
                        <a class="home" href="../">Home</a>
                    </nav>
                </div>
            </div>

        </div>
    </header>
    <div class="container">
      <c:choose>
           <c:when test="${ not empty error}">
               <div class="row">
                  <div class="twelve columns">
                      <p class="text" ><fmt:message key="${error}" bundle="${rs}" /></p>
                  </div>
               </div>
           </c:when>
           <c:otherwise>
        <div class="row rooms">
            <div class="four columns">
                Client
            </div>
            <div class="seven columns">
                Room
            </div>
            <div class="two columns">
                total price
            </div>
        </div>
            <c:if test="${ not empty datesNotFreeError}">
			<p><fmt:message key="${message}" bundle="${rs}" /></p>
			</c:if>

        <c:forEach var="order" items="${orders}" varStatus="status">
        <div class="row rowdata">
            <div class="four columns data">
                <div class="profile">

                </div>
                <div>
                    <p> Name : <c:out value="${order.user.name}"/></p>
                    <p>Surname : <c:out value="${order.user.surname}"/></p>
                    <p>Email : <c:out value="${order.user.email}"/></p>
                </div>
            </div>
            <div class="seven columns data ">
                <div class=" imgd">
                    <img class="img" src="../resources/images/img1/1.jpg" width="250" height="150">
                </div>
                <div>
                    <p class="price">Number : <c:out value="${order.room.number}"/></p>
                    <p class="sits">Arrive date : <c:out value="${order.dateIn}"/></p>
                    <p>Leave date : <c:out value="${order.dateOut}"/></p>
                </div>
            </div>
            <div class="two columns data ">
                <div>
                    <p> <c:out value="${order.totalPrice}"/></p>
                </div>
                <p class="more"><a class="deleteR" href="${order.id}">Cancel</a></p>
                <p class="more more1"><a class="confirm" href="${order.id}">Confirm</a></p>
            </div>
        </div>
        </c:forEach>
       </c:otherwise>
      </c:choose>
    </div>

     <div class=" container chooseDateDialog1" id="delete" title="Chose dates" hidden>
           <div id="hide" class="row">
                <p>This dioloq window.You can moove it in the window</p>
                       delete this reserve?
                       <div>
                          <a id="cancelD" class="previous" href="#">Previous</a>
                          <a id="deleteForm" class="makeorder" href="75" >Delete</a>
                       </div>
           </div>
     </div>

     <div class=" container chooseDateDialog1" id="confirmOrder" title="Chose dates" hidden>
                <div id="hide" class="row">
                                    <p id="message" hidden>It will be cost for user
                                    <p id="res" class="desc1" hidden> </p>
                                    </p>
                                    <p id="message1" hidden>not free
                                    </p>
                            <div>
                               <a id="cancelCO" class="previous" href="#">Previous</a>
                               <a id="confirmOrder1" class="makeorder" href="75" >Confirm</a>
                            </div>
                </div>
     </div>


    <footer>
        <div class="container ">
            <div class="row ">
                <div class="twelve columns ">
                    <p id="text ">Foot</p>
                </div>
            </div>
        </div>
    </footer>

</body>

</html>