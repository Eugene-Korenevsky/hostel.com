<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>

<fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/>

<u:html>

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


</u:html>