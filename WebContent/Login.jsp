<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RentIt-Login</title>
<link href="login.css" rel="stylesheet" type="text/css"
         media="screen">
</head>
<body>
<div class="login-page">
<div class="header-logo">
<a href="/Rent_It/search">
  <img src = "images/logo1.png"></a>
  </div>
  <div class="form">
    <form class="register-form" >
      <input type="text" placeholder="name"/>
      <input type="password" placeholder="password"/>
      <input type="text" placeholder="email address"/>
      <button>create</button>
      <p class="message">Already registered? <a href="#">Sign In</a></p>
    </form>
    <form class="login-form" action="LoginServlet" method="post">
      <input type="text" id="email" name="email" placeholder="Email"/>
      <input type="password" id="password" name="password" placeholder="Password"/>
      <input class="loginbutton" type="submit" value="Login" />
      <p class="message">Not registered? <a href="Register.jsp">Create an account</a></p>
    </form>
  </div>
</div>
<footer class="container-fluid text-center">
  <p>2017©Copyright,RentIt.com</p>  
</footer>
</body>
</html>