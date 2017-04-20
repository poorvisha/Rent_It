<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
        <li><a href="OwnerHomePage.jsp">Home</a></li>
        <li><a href="PostItem.jsp">NewPost</a></li>
      </ul>
       <ul class="nav navbar-nav navbar-right">
        <li><a class="g-signin2" data-onsuccess="onSignIn"></a></li>
      	<li><a id="MyReservations" href="/Rent_It/HistoryServlet"> MyRentals</a></li>
        <li><a id="login" href="#"><span class="glyphicon glyphicon-user"></span> Login</a></li>
        <li><a id= "signup" href="#"><span class="glyphicon glyphicon-user"></span> SignUp</a></li>
         <li><a id= "logout" href="/Rent_It/LogoutServlet"><span></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<h3>Post your item !!!</h3>
	<div>
		<form id="post_item_form" action="PostItemServlet" method="post" enctype="multipart/form-data">
			<table id="post_item" align="center">
				<tr>
					<td>Title*</td>
					<td><input type="text" name="item_name" id="item_name"
						size="40" maxlength="30" /></td>
				</tr>
				<tr>
					<td >Description*</td>
					<td><textarea rows="2" cols="38.5" name="item_description"
						id="item_description" size="40"  maxlength="150"></textarea></td>
						<td>
						
				</tr>
				<tr>
				<td>Item Image</td>
				<td>
                  	 <img name="profilepic" id="profilepic" alt="ItemImage" align="center" src="${pageContext.request.contextPath}/images/${eMailId}" width="100" height="100">
                  	 
                     <input type="button" align="center" class="btn btn-success" value="Upload" id="upload" name ="upload" style='visibility: visible;' onclick ="fileopen()">
                     <input type="file" align="center" name="photo" id="photo" size="30" style='visibility: hidden;' onchange="readURL(this);" > </td> 
                  	
				</tr>
				<tr>
					<td>Category*</td>
					<td><select name="item_category" id="item_category">
							<option value="">--select--</option>
							<option value="automobiles">Automobiles</option>
							<option value="gardentools">Garden tools</option>
							<option value="electronics">Electronics</option>
							<option value="books">Books</option>
							<option value="homeappliances">Home Appliances</option>
					</select></td>
				</tr>
				<tr>
					<td>Zipcode*</td>
					<td><input type="text" name="zipcode" id="zipcode" size="30"
						maxlength="9"></td>
				</tr>
				<tr>
					<td>Cost per day*</td>
					<td><input type="text" name="daycost" id="daycost" size="30"
						maxlength="9"></td>
				</tr>
				<tr>

					<td>Availability From*</td>
					<td><input id="available_from" name="available_from"
						type="date" value="2017-04-20" /></td>

					<td> To*<input
						id="available_to" name="available_to" type="date"
						value="2017-04-23" /></td>
				</tr>
				<tr>
					<td>Pick Up Location*</td>
					<td><input type="text" name="addressline1" id="addressline1"
						placeholder="Street Address" size="30" maxlength="40" /></td>
					<td><input type="text" name="city" id="city"
						placeholder="City" size="30"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="text" name="state" id="state"
						placeholder="State" size="30"></td>
				</tr>
				<tr>
					<td>Contact*</td>
					<td><input type="text" name="contact" id="contact"
						placeholder="555-555-5555" size="30"></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td><input class= "btn-submitpost" type="submit" name="submit" value="Post"></td>               
				</tr>

			</table>
		</form>
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

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
        	console.log(e.target.result);
        	var files = document.getElementsByName('profilepic');
       	files[0].src=e.target.result;
            //document.getElementById('profilepic').src=e.target.result;
        };

        reader.readAsDataURL(input.files[0]);
    }
}

function fileopen()
{
	var files = document.getElementsByName('photo');
	files[0].click();
}

</script>
</body>
</html>