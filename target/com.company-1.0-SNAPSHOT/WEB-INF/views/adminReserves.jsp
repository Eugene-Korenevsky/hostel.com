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
                req.open("DELETE", "reserves/" + url1, false);
                req.send(null);
                document.location.href = "reserves";
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
               <p class="text" ><fmt:message key="${error}" bundle="${rs}" /></p>
             </div>
          </div>
      </c:when>
      <c:otherwise>
        <div class="row rooms">
            <div class="four columns">
                <fmt:message key="client" bundle="${rs}" />
            </div>
            <div class="seven columns">
                <fmt:message key="room" bundle="${rs}" />
            </div>
            <div class="two columns">
                <fmt:message key="total.price" bundle="${rs}" />
            </div>
        </div>

        <c:forEach var="reserve" items="${reserves}" varStatus="status">
        <div class="row rowdata">
            <div class="four columns data">
                <div class="profile">

                </div>
                <div>
                    <p><fmt:message key="login.form.name" bundle="${rs}" /> : <c:out value="${reserve.user.name}"/></p>
                    <p><fmt:message key="login.form.surname" bundle="${rs}" /> : <c:out value="${reserve.user.surname}"/></p>
                    <p><fmt:message key="login.form.email" bundle="${rs}" /> : <c:out value="${reserve.user.email}"/></p>
                </div>
            </div>
            <div class="seven columns data ">
                <div class=" imgd">
                    <img class="img" src="../resources/images/img1/1.jpg" width="250" height="150">
                </div>
                <div>
                    <p><fmt:message key="room.number" bundle="${rs}" /> : <c:out value="${reserve.room.number}"/></p>
                    <p><fmt:message key="date.arrive" bundle="${rs}" /> : <c:out value="${reserve.dateIn}"/></p>
                    <p><fmt:message key="date.leave" bundle="${rs}" />: <c:out value="${reserve.dateOut}"/></p>
                </div>
            </div>
            <div class="two columns data ">
                <div>
                    <p> <c:out value="${reserve.totalPrice}"/></p>
                </div>
                <p class="more"><a class="deleteR" href="${reserve.id}"><fmt:message key="cancel" bundle="${rs}" /></a></p>
            </div>
        </div>
        </c:forEach>
   </c:otherwise>
  </c:choose>
 </div>



     <div class=" container chooseDateDialog1" id="delete" title="Chose dates" hidden>
                    <div id="hide" class="row">

                        <fmt:message key="delete.reserve" bundle="${rs}" />

                        <div>
                           <a id="cancelD" class="previous" href="#"><fmt:message key="cancel" bundle="${rs}" /></a>
                           <a id="deleteForm" class="makeorder" href="75" data-method="delete"><fmt:message key="delete" bundle="${rs}" /></a>
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