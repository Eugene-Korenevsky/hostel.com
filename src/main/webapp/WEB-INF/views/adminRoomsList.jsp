<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
    <script src="../resources/js/jquery-3.5.1.min.js"></script>
    <script src="../resources/js/jquery.validate.js"></script>
    <script>
        $(document).ready(function() {
                    var url1;
                    $("#hello").hide();
                    var room1;

                     $(".chRoom").on("click", function() {
                        var value1 = $(this).attr('href');
                        $("#changeRoom").show();
                        console.log("id = " + value1);
                        $.getJSON("room/"+value1,function( room ){
                            room1 = room;
                            $("#price1").attr('value', room.price);
                            $("#sits1").attr('value', room.sits);
                            $("#number1").attr('value', room.number);
                            $("#classRoom").attr('value', room.roomClass);
                            for (var i = 0; i < room.descriptions.length; i++) {
                              str = room.descriptions[i].replace(/\s/g, '');
                              $("#roomDesc").append('<p class="desc" id="' + str + '">'
                              + room.descriptions[i]
                              + '<a class="removeDesc"  href="' + str + '">Delete</a></p>');
                            }

                        });
                        return false;
                     });

                     $("#cancelCh").click(function() {
                         for (var i = 0; i < room1.descriptions.length; i++) {
                            str = room1.descriptions[i].replace(/\s/g, '');
                            $("#" + str).remove();
                         }
                         $("#changeRoom").hide();
                         return false;
                     });

                     $("html").on("click", ".removeDesc", function() {
                        var desc = $(this).attr('href');
                        $("#" + desc).remove();
                        for (var i = 0; i < room1.descriptions.length; i++) {
                            str = room1.descriptions[i].replace(/\s/g, '');
                            if (desc == str) {
                                console.log("ok");
                                console.log(room1.descriptions[i]);
                                room1.descriptions.splice(i, 1);
                                break;
                            }
                        }
                        return false;
                    });

                     $(".addDesc").on("click", function() {
                       var desc = $(this).attr('href');
                       str = desc.replace(/\s/g, '');
                       found = false;
                       for (var i = 0; i < room1.descriptions.length; i++) {
                           if (desc == room1.descriptions[i]) {
                               found = true;
                               break;
                           }
                       }
                       if (!found) {
                           $("#roomDesc").append('<p class="desc" id="' + str + '">' +
                               desc + '<a class="removeDesc"  href="' + str + '">Delete</a></p>');
                           room1.descriptions.push(desc);
                       }
                       return false;
                     });

                     $("#submit1").click(function() {
                        console.log("sub");

                        room1.number = $("#number1").val();
                        room1.sits = $("#sits1").val();
                        room1.price = $("#price1").val();
                        room1.roomClass = $("#classRoom").val();

                        result_json = JSON.stringify(room1);
                        $.ajax({
                           type: 'PUT',
                           url: 'room',
                           contentType: 'application/json',
                           data: result_json,
                           success: function(data) {
                                console.log("done");
                                document.location.href = "room";
                           },
                           error:  function(){
                              alert('Ошибка!');
                           }
                        });
                        return false;


                     });




                    $("#cancelD").click(function() {
                        $("#delete").hide();
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
                         req.open("DELETE", "room/" + url1, false);
                         req.send(null);
                         document.location.href = "room";
                     });



                    $("#makeRoom").on("click", function(e) {
                        console.log("hello");
                        $("#hello").show();
                        return false;
                    });

                    $("#cancel").click(function() {
                        $("#hello").hide();
                        return false;
                    });

                    $("#form").validate({
                         rules: {
                            number: {
                            required: true,
                            min: 1
                            },
                         sits: {
                            required: true,
                            min: 1
                             },
                         price: {
                            required: true,
                            min: 0
                            },
                         roomClass: {
                            required: true,
                            minlength: 1
                            }
                         },
                         messages: {
                         number: {
                            required: "Write room number",
                            min: "can't be less then 0"
                            },
                         sits: {
                            required: "Write sits",
                            min: "can't be less then 0"
                            },
                         price: {
                            required: "Write price",
                            min: "can't be less then 0"
                            },
                         roomClass: {
                            required: "Write class",
                            minlength: "min 1 letter"
                         }
                         },
                         success: function(label) {
                             label.addClass("valid").text("-")
                             }
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
                        <a  href="../room">rooms</a>
                        <a class="home" href="../home">Home</a>
                    </nav>
                </div>
            </div>

        </div>
    </header>











    <div class="container">


        <div class="row rooms">
            <div class="seven columns">
                <p class="preinfo">: </p>
            </div>
            <div class="five columns">
                <div class="textcenter">
                    <p class="floatleft">Add new room</p>
                    <p class="add">
                        <a id="makeRoom" class="add" href="#"><img src="../resources/images/img/add1_1_50X50.png"></a>
                    </p>
                </div>

            </div>

        </div>

        <c:forEach var="room" items="${rooms}" varStatus="status">
        <div class="row rowdata">

            <div class="four columns imgd">
                <img class="img" src="../resources/images/img1/1.jpg" width="250" height="150">
            </div>
            <div class="five columns">
                <div class="data profile1">
                    <p>Number : <c:out value="${room.number}"/> </p>
                </div>
                <div class="data profile1">
                    <p>Sits : <c:out value="${room.sits}"/></p>
                </div>
                <div class="data profile1">
                    <p>Class : <c:out value="${room.roomClass}"/></p>
                </div>
                <div class="data profile1">
                    <p>Price : <c:out value="${room.price}"/></p>
                </div>
                <div class="data1 profile1 borderradiosbottomnone">
                    <p>Descriptions :
                      <c:forEach var="desc" items="${room.descriptions}" varStatus="status">
                        <p class="desc1"><c:out value="${ desc.description }"  /></p>
                      </c:forEach>
                    </p>
                </div>
                <div class="data margintop1">
                    <p class="more"><a class="chRoom" href="${room.id}">Change</a></p>
                </div>
                <div class="data profile1">
                   <p>Delete Room</p>
                   <p class="more right"><a class="deleteR" href="${room.id}">Delete</a></p>
                </div>

            </div>
            <div class="three columns">

            </div>

        </div>
        </c:forEach>
    </div>


    <div class=" container chooseDateDialog1" id="hello" title="Chose dates">
            <div id="hide" class="row">
                <p>This dioloq window.You can moove it in the window</p>

                <sf:form id="form" class="form" action="room" method="POST" modelAttribute ="room">

                    <label class="label" for="number">Number </label>
                    <sf:input path="number"  name="number" type="number"/>
                    <label class="error" for="number"></label>

                    <label class="label" for="sits">Sits </label>
                    <sf:input path="sits"  name="sits" type="number"/>
                    <label class="error" for="sits"></label>

                    <label class="label" for="roomClass">Class </label>
                    <sf:input path="roomClass"  name="roomClass" type="text"/>
                    <label class="error" for="roomClass"></label>

                    <label class="label" for="price">price </label>
                    <sf:input path="price"  step="0.01" name="price" type="number"/>
                    <label class="error" for="price"></label>
                     <c:forEach var="item" items="${descriptionsList}">
                         <label class"label" for="${item.description}">
                         <c:out value="${item.description}"/>
                         </label>
                         <sf:checkbox name="${item.description}"
                          path="descriptions" value="${item.description}"/>
                     </c:forEach>
                     <div class="row">
                     <p><input id="submit" class="makeorder dialog" name="submit" type="submit" value="Create" /></p>
                </sf:form>

                <a id="cancel" class="previous dialog" href="#">Previous</a></p>
                </div>
            </div>

        </div>
        <div class=" container chooseDateDialog1" id="delete" title="Chose dates" hidden>
                <div id="hide" class="row">
                    <p>This dioloq window.You can moove it in the window</p>

                    delete this room?

                    <div>
                       <a id="cancelD" class="previous" href="#">Previous</a>
                       <a id="deleteForm" class="makeorder" href="75" data-method="delete">Delete</a>
                    </div>


                </div>

        </div>


        <!-- change room -->

        <div class=" container chooseDateDialog1" id="changeRoom" title="Chose dates" hidden>
                <div id="hide" class="row">
                    <p>This dioloq window.You can moove it in the window</p>

                    <form id="form1" class="form" action="1223">
                        <label class="label" for="roomNumber1">Number </label>
                        <input id="number1" name="roomNumber1" type="number" value="">
                        <label class="error" for="roomNumber"></label>

                        <label class="label" for="sits">Sits </label>
                        <input id="sits1" name="sits" type="number" value="">
                        <label class="error" for="sits"></label>

                        <label class="label" for="class">Class </label>
                        <input id="classRoom" name="class" type="text" value="">
                        <label class="error" for="class"></label>

                        <label class="label" for="price">price </label>
                        <input id="price1" step="0.01" name="price" type="number" value="">
                        <label class="error" for="price"></label>

                        <div class=" data" id="roomDesc">


                        </div>

                        <div class=" data">
                            <c:forEach var="item" items="${descriptionsList}">
                            <p class="desc" id="${item.id}"><c:out value="${item.description}"/><strong hidden>.</strong>
                                <a class="addDesc" href="${item.description}">Add</a>
                            </p>
                            </c:forEach>

                        </div>


                        <div class="row">
                            <p><input id="submit1" class="makeorder dialog" name="submit" type="submit" value="submit" /></p>
                    </form>

                    <a id="cancelCh" class="previous dialog" href="#">Previous</a></p>
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