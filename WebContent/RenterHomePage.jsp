<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RenterHomePage</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="js/jquery-1.12.2.min.js"></script>
	<script src="js/jquery.tools.min.js"></script>
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
      <ul class="nav navbar-nav searchpadding">    
        <li><form id="searchform" action="/Rent_It/search">
      	  Search <input style = "color:black" type="text" class="search-bar" name="keyword" id="keyword" size="60">
      	  </form></li>
      </ul>
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

			<div class="container">    
  <div class="row">
    <%int counter = 0; %>
    <c:forEach items="<%=session.getAttribute(\"homePageList\") %>" var="itemelement">
    				<%if(counter >= 3){ %>
    				</div><br>
    				<div class="row">
    				<%counter=0;} %>
  					<div class="col-sm-4">
	      				<div class="panel panel-primary">
	       				 	<div class="panel-heading">
	       				 		<c:out value = "${itemelement.item_name}"/></br>
	       				 		 Pickup Location : <c:out value = "${itemelement.item_zipcode} " />
	       				 	</div>
	        				 <div class="panel-body"><img src="${itemelement.item_image_url}" class="img-responsive" style="height:200px" alt="Image"></div>
	        				 <form id="reserve_item_form" action="ReserveItemServlet" method="post">
		       					 <div class="panel-footer">Post Status : <c:out value = "${itemelement.item_status}"/><br>
		       					 	Posted Date : <c:out value = "${itemelement.item_posteddate}" /><br>
		       					 	<input type="hidden" name="item_id" id="item_id" value="${itemelement.item_id}"/>
		       					 	<input class= "btn-submitpost" type="submit" name="submit" value="Reserve">
		       					 </div>
	       					</form>
	      				</div>
					</div>
			  <%counter++; %>
			</c:forEach>
    
  </div>
</div><br>
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