<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PostItemPage</title>
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
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="RenterHomePage.jsp">Home</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a id="MyReservations" href="/Rent_It/HistoryServlet"> MyReservations</a></li>
        <li><a id="login" href="#"><span class="glyphicon glyphicon-user"></span> Login</a></li>
        <li><a id= "signup" href="#"><span class="glyphicon glyphicon-user"></span> SignUp</a></li>
         <li><a id= "logout" href="/Rent_It/LogoutServlet"><span></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
	<div>
	<h3>Your Reservations...</h3>
			<table class="table">
  <thead class="thead-inverse">
  <tr>
      <th>Order ID</th>
      <th>Order Status</th>
      <th>Item</th>
      <th>Reserved Date</th>
      <th>Total Cost</th>
      <th>Pickup ZIP</th>
      <th>Rented From</th>
    </tr>
  </thead>
<c:forEach items="<%=request.getAttribute(\"orderItemsList\") %>" var="itemelement">    
  <tbody>
    <tr>
      <td><c:out value = "${itemelement.order_id}"/></td>
      <td><c:out value = "${itemelement.order_status}"/></td>
      <td><c:out value = "${itemelement.itemsvo.item_name}"/></td>
      <td><c:out value = "${itemelement.reserve_from}"/> to <c:out value = "${itemelement.reserve_to}"/></td>
      <td><c:out value = "$ ${itemelement.total_cost}"/></td>
      <td><c:out value = "${itemelement.itemsvo.item_zipcode}"/></td>
      <td><c:out value = "${itemelement.user_first_name}"/></td>
    </tr>

	</c:forEach>
	  </tbody>
</table>
	</div>
	
	<footer class="container-fluid text-center navbar-fixed-bottom">
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