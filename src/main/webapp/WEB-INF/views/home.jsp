<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>

<fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/>

<u:html>



     <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js">
     </script>
     <script>
            $(document).ready(function() {
                $("#newlinks a").click(function() {
                    var url = $(this).attr("href");
                    console.log(url);
                    $("#headlines").load(url);
                    return false;
                });


                $("#linksF a").click(function() {
                    var id = 1;
                    console.log("click");
                    console.log(id);
                    //$.ajax({
                    //type:"GET",
                    //url:"roomsListAjax1/"+id,
                    //dataType:"json",
                    //contentType:"application/json",
                    $.getJSON("roomsListAjax1/"+id,function( room ){
                      var s = "";
                      console.log("processData");
                      s += "<br> Room --------";
                      s += "<br> " + room.number; //+ data[i].number;
                      s += "<br> Hello " + room.price //+ data[i].price;
                      $("#headlines2").html(s);
                    //}
                    });
                     return false;
                 });


                $("#links a").click(function() {
                    console.log("click");
                    $.get("roomsListAjax","?",processResponse);
                   // $.ajax({
                   // type:"GET",
                   // url:"roomsListAjax",
                   // success:processResponse
                   // });
                    return false;
                    });
                function processResponse(data) {
                      var s = "";
                      console.log("processData");
                      console.log(data);
                     // for (var i = 0; i < data.length; i++) {
                          s += "<br> Room --------";
                          s += "<br> " + data; //+ data[i].number;
                          s += "<br> Hello" //+ data[i].price;
                     // }
                      $("#headlines1").html(s);
                }
            });
     </script>


    <div id="body">
       <u:choseroom/>
       
        <table id="table">
            <tbody id="tbody">
			
                <tr>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><form action="roomsList" method"GET">
            <button id="button" type="submit"><fmt:message key="room.button" bundle="${rs}" /></button>
        </form>
		</td>
                </tr>
               
                <tr>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><form action="findUserOrders" method"GET">
            <button id="button" type="submit"><fmt:message key="order.button" bundle="${rs}" /></button>
        </form></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
					<td><form action="findUserReserves" method"GET">
            <button id="button" type="submit"><fmt:message key="reserve.button" bundle="${rs}" /></button></td>
                </tr>
                
              
			  <fmt:message key="first.page.text" bundle="${rs}" />
              			  
             <td><c:out value="${ hello }"  /></td>   
            </tbody>



        </table>
    </div>
	 </div>
	 <p></p>
         <ul id="newlinks">
             <a href="resources/15.html">15.html</a>
             <a href="resources/14.html">14.html</a>
             <a href="resources/16.html">16.html</a>
         </ul>
         <div id="headlines">

         </div>
         <ul id="links">
                      <a href="#">15.html</a>
                      <a href="#">14.html</a>
                      <a href="#">16.html</a>
         </ul>
         <div id="headlines1">

          </div>

          <ul id="linksF">
               <a href="#">15.html</a>
               <a href="#">14.html</a>
               <a href="#">16.html</a>
          </ul>
          <div id="headlines2">

          </div>

          <ul id="links2">
                <a href="#">15.html</a>
                <a href="#">14.html</a>
                <a href="#">16.html</a>
          </ul>
          <div id="headlines3">

          </div>

</u:html>