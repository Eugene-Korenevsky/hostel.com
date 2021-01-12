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

                var user1;
                $(".changeRole").on("click", function() {
                    var value1 = $(this).attr('href');
                    console.log(value1);
                    $("#hello").show();
                    $.getJSON("users/"+value1,function( user ){
                      console.log(user);
                      user1 = user;
                    });
                    return false;
                });

                $("#cancel").click(function() {
                    $("#hello").hide();
                    return false;
                });


                $("#submit1").click(function() {
                   console.log("sub");
                   var sel = $("#select").val();
                   var sel1 = $("#select option:selected").text();
                   console.log(sel);
                   console.log(sel1);
                   user1.role.id = sel;
                   user1.role.role = sel1;
                   console.log(user1.role.id);
                   console.log(user1.role.role);
                   result_json = JSON.stringify(user1);
                   console.log(user1);
                   $.ajax({
                      type: 'PUT',
                      url: 'users',
                      contentType: 'application/json',
                      data: result_json,
                      success: function(data) {
                         console.log("done");
                         document.location.href = "user";
                      },
                      error:  function(){
                          alert('Ошибка!');
                      }
                   });
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
                        <a  href="../rooms">rooms</a>
                        <a class="home" href="../home">Home</a>
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
            <div class="four columns">
                Room
            </div>
            <div class="four columns">
                Role
            </div>
        </div>
        <c:forEach var="user" items="${users}" varStatus="status">
        <div class="row rowdata">
            <div class="four columns data">
                <div class="profile">

                </div>

            </div>
            <div class="four columns data ">
                <div>
                    <p> Name : <c:out value="${user.name}"/></p>
                    <p>Surname : <c:out value="${user.surname}"/></p>
                    <p>Email : <c:out value="${user.email}"/></p>
                </div>
            </div>
            <div class="four columns data ">
                <div>
                    <p> Role : <c:out value="${user.role.role}"/></p>
                </div>
                <p class="more"><a class="changeRole" href="${user.id}">Change role</a></p>
            </div>
        </div>
        </c:forEach>

        </c:otherwise>
      </c:choose>
    </div>


     <div class=" container chooseDateDialog1" id="hello" title="Chose dates" hidden>
            <div id="hide" class="row">
                <p>This dioloq window.You can moove it in the window</p>

                <form id="form" class="form">
                    <label class="label" for="roleId">Role </label>
                    <select id="select" name="roleId" size="1">
                       <c:forEach var="elem1" items="${roles}" varStatus="status">
                       <option value="${ elem1.id }"><c:out value="${ elem1.role }"  /></option>
  				       </c:forEach>

                    </select>
                    <div class="row">
                        <p><input id="submit1" class="makeorder dialog" name="submit" type="submit" value="submit" /></p>
                </form>

                <a id="cancel" class="previous dialog" href="#">Previous</a></p>
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