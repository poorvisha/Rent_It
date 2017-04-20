<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PaymentPage</title>
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
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        Payment Details
                    </h3>
                    <div class="checkbox pull-right">
                        <label>
                            <input type="checkbox" />
                            Remember
                        </label>
                    </div>
                </div>
                <div class="panel-body">
                    <form role="form" id="update_payment_form" action="UpdatePaymentServlet" method="post">
                    <div class="form-group">
                        <label for="cardNumber">
                            CARD NUMBER</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="cardNumber" name="cardNumber" placeholder="Valid Card Number"
                                required autofocus />
                            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                        </div>
                    </div>
                         <label for="cardNumber">
                            CARD HOLDER NAME</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="card_holder_name" name="card_holder_name" placeholder="Valid Card Holder Name"
                                required autofocus />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-7 col-md-7">
                            <div class="form-group">
                                <label for="expityMonth">
                                    EXP MONTH</label>
                                   <label for="expityYear">
                                    EXP YEAR</label>
                                <div class="col-xs-6 col-lg-6 pl-ziro">
                                    <input type="text" class="form-control" id="expityMonth" name="expityMonth" placeholder="MM" required />
                                </div>
                                <div class="col-xs-6 col-lg-6 pl-ziro">
                                    <input type="text" class="form-control" id="expityYear" name="expityYear" placeholder="YY" required /></div>
                            </div>
                        </div>
                        <div class="col-xs-5 col-md-5 pull-right">
                            <div class="form-group">
                                <label for="cvCode">
                                    CV CODE</label>
                                <input type="password" class="form-control" id="cvCode" placeholder="CV" required />
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="order_id" id="name" value = <%=request.getAttribute("order_id")%>>
                    <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="#"><span class="badge pull-right"><span class="glyphicon glyphicon-usd"></span><%=request.getAttribute("total_cost")%></span> Final Payment</a>
                </li>
            </ul>
            <br/>
            <input type="submit" role="button" class="btn btn-success btn-lg btn-block"/>
                    </form>
                </div>
            </div>
            
        </div>
    </div>
</div>
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