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
                req.open("DELETE", "reserve/" + url1, false);
                req.send(null);
                document.location.href = "reserve";
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
                        <a href="../room">rooms</a>
                        <a class="home" href="../home">Home</a>
                    </nav>
                </div>
            </div>

        </div>
    </header>
    <div class="container">
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

        <c:forEach var="reserve" items="${reserves}" varStatus="status">
        <div class="row rowdata">
            <div class="four columns data">
                <div class="profile">

                </div>
                <div>
                    <p> Name : <c:out value="${reserve.user.name}"/></p>
                    <p>Surname : <c:out value="${reserve.user.surname}"/></p>
                    <p>Email : <c:out value="${reserve.user.email}"/></p>
                </div>
            </div>
            <div class="seven columns data ">
                <div class=" imgd">
                    <img class="img" src="../resources/images/img1/1.jpg" width="250" height="150">
                </div>
                <div>
                    <p>Number : <c:out value="${reserve.room.number}"/></p>
                    <p>Arrive date : <c:out value="${reserve.dateIn}"/></p>
                    <p>Leave date : <c:out value="${reserve.dateOut}"/></p>
                </div>
            </div>
            <div class="two columns data ">
                <div>
                    <p> <c:out value="${reserve.totalPrice}"/></p>
                </div>
                <p class="more"><a class="deleteR" href="${reserve.id}">Cancel</a></p>
            </div>
        </div>
        </c:forEach>
    </div>



     <div class=" container chooseDateDialog1" id="delete" title="Chose dates" hidden>
                    <div id="hide" class="row">
                        <p>This dioloq window.You can moove it in the window</p>

                        delete this reserve?

                        <div>
                           <a id="cancelD" class="previous" href="#">Previous</a>
                           <a id="deleteForm" class="makeorder" href="75" data-method="delete">Delete</a>
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