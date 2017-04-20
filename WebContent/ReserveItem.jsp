<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ReserveItemPage</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="style1.css" rel="stylesheet" type="text/css"
         media="screen">
        
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      
      <img class="logoimg" src = "images/logo1.png">
      <a id="homelink" class="navbar-brandsearch" href="/Rent_It/search">Home</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
      	<li><a class="g-signin2" data-onsuccess="onSignIn"></a></li>
      	<li><a id="MyReservations" href="/Rent_It/HistoryServlet"> MyReservations</a></li>
        <li><a id="login" href="#"><span class="glyphicon glyphicon-user"></span> Login</a></li>
        <li><a id= "signup" href="#"><span class="glyphicon glyphicon-user"></span> SignUp</a></li>
         <li><a id= "logout" href="/Rent_It/LogoutServlet"><span></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<c:forEach items="<%=request.getAttribute(\"itemsVOList\") %>" var="itemelement">
	<div>
	<h3>Reserve Now !!!</h3>
		<form id="submit_order_form" action="SubmitOrderServlet" method="post">
			<table id="post_item" align="center">
				<tr>
					<td>Item : </td>
					<td><c:out value = "${itemelement.item_name}"/></td>
				</tr>
				<tr>
					<td >Description :</td>
					<td><c:out value = "${itemelement.item_description}"/></td>
				</tr>
				<tr>
					<td>Cost per day : </td>
					<td><c:out value = "$ ${itemelement.item_costperday}"/></td>
				</tr>
				<tr>
					<td>Availability :</td>
					<td><c:out value = "${itemelement.item_available_from}"/>
					 To
					<c:out value = "${itemelement.item_available_to}"/></td>
				</tr>
				<tr>
					<td>PickUp Location : </td>
					<td><c:out value = "${itemelement.pickup_address}"/>,<c:out value = "${itemelement.pickup_city}"/>,
					<c:out value = "${itemelement.pickup_state}"/> - <c:out value = "${itemelement.item_zipcode}"/></td>
				</tr>
				<tr>
					<td >Contact :</td>
					<td><c:out value = "${itemelement.contact}"/></td>
				</tr>
				<tr>
					<td>Reserve From*</td>
					<td><input id="reserve_from" name="reserve_from"
						type="date" value="2017-03-13" /></td>

					<td> To*<input
						id="reserve_to" name="reserve_to" type="date"
						value="2017-03-15" /></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td><input class= "btn-submitpost" type="submit" name="submit" value="Book"></td>               
				</tr>
			</table>
			<input type="hidden" name="item_id" id="item_id" value = "${itemelement.item_id}"/>
			<input type="hidden" name="item_costperday" id="item_costperday" value = "${itemelement.item_costperday}"/>
			<input type="hidden" name="owner_id" id="owner_id" value = "${itemelement.item_postedby}"/>
		</form>
	</div>
	</c:forEach>
	<footer class="container-fluid text-center">
  <p>2017©Copyright,RentIt.com</p>  
  <form class="form-inline">For latest posts:
    <input type="email" class="form-control" size="50" placeholder="Email Address">
    <button type="button" class="btn btn-danger">Subscribe</button>
  </form>
</footer>
<script>
var loggedInStatus = <%=session.getAttribute("loggedin_status")%>;
var first_name = '<%=session.getAttribute("first_name")%>';
var user_type = '<%=session.getAttribute("user_type")%>';
if (true == loggedInStatus){
	$('#login').html('<span class="glyphicon glyphicon-user"></span>'+first_name);
	$('#signup').hide();
	$('.g-signin2').hide();
	$('#logout').show();
	$('#MyReservations').show();
	if(user_type == 'Owner'){
		$('#homelink').attr("href", "/Rent_It/OwnerHomePage.jsp")
	}else if(user_type == 'Renter'){
		$('#homelink').attr("href", "/Rent_It/RenterHomePage.jsp");
	}
}
else
{ 
	$('#logout').hide(); 
}
</script>
</body>
</html>