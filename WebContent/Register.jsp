<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RentIt-Register User</title>
<link href="signup.css" rel="stylesheet" type="text/css" media="screen">
</head>
<body>
<div class="header-logo">
<a href="/Rent_It/search">
  <img src = "images/logo1.png"></a>
  </div>
	<div class="form">
      <div class="tab-content">
        <div id="signup">   
          <h1>Sign Up for Free</h1>        
          <form action="RegisterServlet" method="post">       
          <div class="top-row">
            <div class="field-wrap">
              <input id="first_name" name="first_name" type="text" required autocomplete="off" placeholder="First Name*"/>
            </div>     
            <div class="field-wrap">
              <input id= "last_name" name="last_name" type="text" required autocomplete="off" placeholder="Last Name*"/>
            </div>
          </div>
          <div class="field-wrap">
            <input id="email" name="email" type="email" required autocomplete="off" placeholder="Email*"/>
          </div>       
          <div class="field-wrap">
            <input id="password" name="password" type="password" required autocomplete="off" placeholder="Password*"/>
          </div>       
           <div class="field-wrap">
            <select name="user_type" id="user_type">
                  <option  value="">--Select a User Type--</option>
                  <option  value="Owner" >Owner</option>
                  <option  value="Renter">Renter</option>
                   </select><span class="req">*</span>
          </div>
                
          <button type="submit" class="button button-block" />Get Started</button>  
           <div>
           <h3>Already have an account ?</h3>
           </div>      
            <a href="Login.jsp" class="button button-block" />Login</a>  
          </form>
        </div>      
        <div id="login">   
          <h1>Welcome Back!</h1>
          
          <form action="/" method="post">
          
            <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span>
            </label>
            <input type="email" required autocomplete="off" />
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password" required autocomplete="off" />
          </div>
          
          <p class="forgot">
						<a href="#">Forgot Password?</a>
					</p>
          
          <button class="button button-block" />Log In</button>      
          </form>

        </div>
        
      </div>
		<!-- tab-content -->
      
</div> <!-- /form -->
  <script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>
   
<footer class="container-fluid text-center">
  <p>2017©Copyright,RentIt.com</p>  
</footer>

</body>

</html>