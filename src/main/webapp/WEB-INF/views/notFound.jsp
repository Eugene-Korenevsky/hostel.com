<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/>
<fmt:message key="found" var="notFound" bundle="${rs}" />

<!DOCTYPE html>
<html>

<head>
    <title>the best hostel in the word</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/com.company-1.0-SNAPSHOT/resources/css/normalize.css" rel="stylesheet" />
    <link href="/com.company-1.0-SNAPSHOT/resources/css/sm-core-css.css" rel="stylesheet">
    <link href="/com.company-1.0-SNAPSHOT/resources/css/sm-clean.css" rel="stylesheet">
    <link href="/com.company-1.0-SNAPSHOT/resources/css/main1.css" type="text/css" rel="stylesheet" />
    <link href="/com.company-1.0-SNAPSHOT/resources/css/skeleton.css" rel="stylesheet" />
    <link href="/com.company-1.0-SNAPSHOT/resources/css/help.css" rel="stylesheet" />
</head>
<body>
<header>
        <div class="container">
            <div class="row">
                <div class="three columns">
                    <a href="home.html">
                        <img src="/com.company-1.0-SNAPSHOT/resources/images/img/hotel_1_1.png" class="logo">
                    </a>
                    <p class="text">.com</p>
                </div>



                <div class="nine columns">
                    <nav class="nav">
                        <a href="#">About Us</a>
                        <a href="rooms">rooms</a>
                        <a class="home" href="home">Home</a>
                    </nav>
                </div>
            </div>

        </div>
    </header>
    <div class="container">
                        <div class="row">
                             <div class="twelve columns">
                                  <p class="text" ><c:out value="${ notFound }"  /></p>
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
         <div class="row">
               <div class="twelve columns">
                   <p class="text" ></p>
               </div>
         </div>
    </div>
</body>