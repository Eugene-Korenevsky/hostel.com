<%@ tag language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style>
   #body {
    background: #c7b39b url(resources/images/main.jpg) no-repeat  ; /* Цвет фона и путь к файлу */
    color: #fff;}
	/* Цвет текста */
	 #head {
	        margin-top: -2.5%;
            width: 1355px;
            height: 75px;
            background-color: rgb(202, 32, 32);
        }
        
        #text {
            text-align: center;
            font-family: 'Courier New', Courier, monospace;
            text-transform: uppercase;
            font-size: 2em;
            font-weight: bold;
            font-style: italic;
        }
        
        #foot {
            width: 1360px;
            height: 62px;
            background-color: rgb(202, 32, 32);
            margin-top: -2.4%;
        }
        
        #body {
            margin-top: -1.2%;
            width: 1355px;
            height: 507px;
            text-align: center;
        }
        
        #inputtext {
            width: 700px;
            align-content: flex-start;
            vertical-align: middle;
            text-align: right;
        }
        #login {
            width: 700px;
            align-content: center;
            vertical-align: middle;
            text-align: right;
        }
        #choseroom {
            width: 1400px;
            align-content: center;
            vertical-align: middle;
            text-align: center;
        }
        
        #table {
            width: 1300px;
            height: 440px;
            line-height: normal;
            caption-side: bottom;
            text-align: left;
            font-size: 14px;
        }
        
        #input {
            width: 130px;
        }
        
        #tbody {
            display: block;
            overflow: auto;
            height: 440px;
            width: 1300px;
            text-align: left;
        }
		#form{
			display: inline-block;
		}
		#button:hover{
			color:  #FF4500;
		}
		#row:hover{
			background:#E0FFFF;
			color:  #FF4500;
		}
  </style>
 <title>the best hostel in the word</title>
    <meta charset="UTF-8">
	<link href="resources/css/main.css" type="text/css" rel="stylesheet" />
</head>
<header>
    <div id="head">
        <p id="text">welcome to our website</p>
    </div>
</header>
<body>
<jsp:doBody/>
</body>
<footer>
    <div id="foot">
        <p id="text"></p>
    </div>
</footer>
</html>