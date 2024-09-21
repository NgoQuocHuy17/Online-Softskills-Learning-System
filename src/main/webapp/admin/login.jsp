<%-- 
    Document   : login
    Created on : May 24, 2024, 12:55:56 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/admin/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:33:27 GMT -->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title>Mentoring - Login</title>

        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">

        <link rel="stylesheet" href="assets/css/bootstrap.min.css">

        <link rel="stylesheet" href="assets/css/font-awesome.min.css">

        <link rel="stylesheet" href="assets/css/style.css">
        
        
        
    </head>
    <body>

        <div class="main-wrapper login-body">
            <div class="login-wrapper">
                <div class="container">
                    <div class="loginbox">
                        <div class="login-left">
                            <img class="img-fluid" src="assets/img/logo-white.png" alt="Logo">
                        </div>
                        <div class="login-right">
                            <div class="login-right-wrap">
                                <h1>Login</h1>
                                <p class="account-subtitle">Access to our dashboard</p>
                                <h5 style="color: red; align-content: center;">
                                    ${requestScope.error}
                                </h5>
                                <form action="account_admin" method="POST">
                                    <input type="hidden" name="action_admin" value="login"/>
                                    <div class="form-group">
                                        <input class="form-control" name="user_name" type="text" placeholder="UserName">
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" type="password" name="password" placeholder="Password">
                                    </div>
                                    <div class="form-group">
                                        <button class="btn btn-primary btn-block w-100" type="submit">Login</button>
                                    </div>
                                </form>

                                <div class="text-center forgotpass"><a href="forgot-password.html">Forgot Password?</a></div>
                                <div class="login-or">
                                    <span class="or-line"></span>
                                    <span class="span-or">or</span>
                                </div>

<!--                                <div class="social-login">
                                    <span>Login with</span>
                                    <a href="#" class="facebook"><i class="fa fa-facebook"></i></a><a href="#" class="google"><i class="fa fa-google"></i></a>
                                </div>-->

                                <div class="text-center dont-have">Don’t have an account? <a href="register.jsp">Register</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script src="assets/js/jquery-3.6.0.min.js"></script>

        <script src="assets/js/bootstrap.bundle.min.js"></script>

        <script src="assets/js/feather.min.js"></script>

        <script src="assets/js/script.js"></script>
    </body>

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/admin/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:33:27 GMT -->
</html>