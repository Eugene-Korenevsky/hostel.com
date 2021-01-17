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
               $.getJSON("orders/"+value1,function(){

               }).done(function(order){
                   reserve = order;
                   console.log(reserve);
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

                   }).done(function(data){
                   if(data == "notFree"){
                       $("#message1").show();
                   }else{
                       $("#message").show();
                       $("#res").show();
                       $("#res").text(data);
                   }
                   }).fail(function(response){
                      if(response.status === 404){
                         $("#orderNotExist").show();
                      }else if(response.status === 500){
                        $("#error").show();
                      }else{
                        $("#notfound").show();
                      }
                   });

               }).fail(function(response){
                   console.log(response);
                   if(response.status === 404){
                      $("#orderNotExist").show();
                   }else{
                     $("#error").show();
                   }
               });
             return false;
          });

          $(".cancelCO").on("click",function(){
            $("#confirmOrder").hide();
            $("#message1").hide();
            $("#message").hide();
            $("#res").hide();
            $("#orderNotExist").hide();
            $("#error").hide();
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
                              error:  function(response){
                                if(response.status === 404){
                                   $("#orderNotExist").show();
                                }else if(response.status === 500){
                                   $("#error").show();
                                }else{
                                   $("#valid").show();
                                }
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
      <c:choose>
           <c:when test="${ not empty error}">
               <div class="row">
                  <div class="twelve columns">
                      <p class="text" ><c:out value="${ error }"  /></p>
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
        <div class="row rooms">
            <div class="four columns">
                <fmt:message key="client" bundle="${rs}" />
            </div>
            <div class="seven columns">
                <fmt:message key="room.number" bundle="${rs}" />
            </div>
            <div class="two columns">
                <fmt:message key="total.price" bundle="${rs}" />
            </div>
        </div>
            <!--<c:if test="${ not empty datesNotFreeError}">
			<p><fmt:message key="client" bundle="${rs}" /></p>
			</c:if>-->

        <c:forEach var="order" items="${orders}" varStatus="status">
        <div class="row rowdata">
            <div class="four columns data">
                <div class="profile">

                </div>
                <div>
                    <p><fmt:message key="login.form.name" bundle="${rs}" /> : <c:out value="${order.user.name}"/></p>
                    <p><fmt:message key="login.form.surname" bundle="${rs}" /> : <c:out value="${order.user.surname}"/></p>
                    <p><fmt:message key="login.form.email" bundle="${rs}" /> : <c:out value="${order.user.email}"/></p>
                </div>
            </div>
            <div class="seven columns data ">
                <div class=" imgd">
                    <img class="img" src="../resources/images/img1/1.jpg" width="250" height="150">
                </div>
                <div>
                    <p class="price"><fmt:message key="room.number" bundle="${rs}" /> : <c:out value="${order.room.number}"/></p>
                    <p class="sits"><fmt:message key="date.arrive" bundle="${rs}" /> : <c:out value="${order.dateIn}"/></p>
                    <p><fmt:message key="date.leave" bundle="${rs}" />: <c:out value="${order.dateOut}"/></p>
                </div>
            </div>
            <div class="two columns data ">
                <div>
                    <p> <c:out value="${order.totalPrice}"/></p>
                </div>
                <p class="more"><a class="deleteR" href="${order.id}"><fmt:message key="delete" bundle="${rs}" /></a></p>
                <p class="more more1"><a class="confirm" href="${order.id}"><fmt:message key="confirm" bundle="${rs}" /></a></p>
            </div>
        </div>
        </c:forEach>
       </c:otherwise>
      </c:choose>
    </div>

     <div class="chooseDateDialog1" id="delete" title="Chose dates" hidden>
           <div id="hide" class="row">
                       <fmt:message key="delete.order" bundle="${rs}" />
                       <div>
                          <a id="cancelD" class="previous" href="#"><fmt:message key="cancel" bundle="${rs}" /></a>
                          <a id="deleteForm" class="makeorder" href="75" ><fmt:message key="delete" bundle="${rs}" /></a>
                       </div>
           </div>
     </div>

     <div class=" container chooseDateDialog1" id="confirmOrder" title="Chose dates" hidden>
                <div id="hide" class="row">
                <div id="message" hidden>
                   <p><fmt:message key="message.order.cost" bundle="${rs}" />
                   <div >
                      <a class="cancelCO previous" href="#"><fmt:message key="cancel" bundle="${rs}" /></a>
                      <a id="confirmOrder1" class="makeorder" href="75" ><fmt:message key="confirm" bundle="${rs}" /></a>
                   </div>
                </div>
                   </p>
                   <p id="res" hidden></p>
                   <p id="orderNotExist"  hidden>
                   <fmt:message key="order.is.not.exist" bundle="${rs}" />
                      <a class="cancelCO previous" href="#"><fmt:message key="cancel" bundle="${rs}" /></a>
                   </p>
                   <p id="error"  hidden>
                         <fmt:message key="error.message" bundle="${rs}" />
                         <a class="cancelCO previous" href="#"><fmt:message key="cancel" bundle="${rs}" /></a>
                   </p>
                   <p id="message1" hidden>
                       <fmt:message key="message.is.already.reserved" bundle="${rs}" />
                       <a class="cancelCO previous" href="#"><fmt:message key="cancel" bundle="${rs}" /></a>
                   </p>
                   <p id="notfound" hidden>
                        <fmt:message key="order.room.not.found" bundle="${rs}" />
                        <a class="cancelCO previous" href="#"><fmt:message key="cancel" bundle="${rs}" /></a>
                   </p>
                   <p id="valid" hidden>
                            <fmt:message key="valid.error" bundle="${rs}" />
                            <a class="cancelCO previous" href="#"><fmt:message key="cancel" bundle="${rs}" /></a>
                   </p>

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