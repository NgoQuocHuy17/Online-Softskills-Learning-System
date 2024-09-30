<%-- 
    Document   : register
    Created on : May 24, 2024, 1:59:25 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/admin/register.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:34:00 GMT -->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title>Mentoring - Register</title>

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
                                <h1>Register</h1>
                                <p class="account-subtitle">Access to our dashboard</p>

                                <form id="myForm" onsubmit="return validateForm()" action="account_admin" method="POST">
                                    <input type="hidden" name="action_admin" value="register"/>
                                    <div class="form-group">
                                        <input class="form-control" type="text" oninput="CheckFullName(this)" oninvalid="CheckFullName(this)" name="full_name" placeholder="Full Name">
                                        <span id="span_admin_fullname"></span>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" type="text" oninput="CheckAccountName(this)" oninvalid="CheckAccountName(this)" name="user_name" placeholder="User Name">
                                        <span id="span_admin_username"></span>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" type="text" oninput="CheckEmail(this)" oninvalid="CheckEmail(this)" name="email" placeholder="Email">
                                        <span id="span_admin_email"></span>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" type="password" oninput="CheckPassword(this)"  oninvalid="CheckPassword(this)" id="password" name="password" placeholder="Password">
                                        <i class="bi bi-eye-slash" id="togglePassword"></i>
                                        <span id="span_admin_password"></span>
                                    </div>
                                    <div class="form-group">
                                        
                                        <input class="form-control" type="password" oninput="CheckConfirmPassword(this)" oninvalid="CheckConfirmPassword(this)" name="confirm-password" placeholder="Confirm Password">
                                       
                                        <span id="span_admin_cofirmpassword"></span>
                                    </div>
                                    <div class="form-group">
                                        <select name="role" class="form-control form-select form-select-lg mb-3" id="roleSelect" aria-label="Large select example">
                                            <option selected>Choose Option</option>
                                            <option value="3">Admin</option>
                                            <option value="4">Manager</option>

                                        </select>
                                        <span id="span-error-message" class="error-message"></span>
                                    </div>


                                    <div class="form-group mb-0">
                                        <button class="btn btn-primary btn-block w-100" type="submit">Register</button>
                                    </div>
                                </form>

                                <!--                                <div class="login-or">
                                                                    <span class="or-line"></span>
                                                                    <span class="span-or">or</span>
                                                                </div>-->

                                <!--                                <div class="social-login">
                                                                    <span>Register with</span>
                                                                    <a href="#" class="facebook"><i class="fa fa-facebook"></i></a><a href="#" class="google"><i class="fa fa-google"></i></a>
                                                                </div>-->

                                <div class="text-center dont-have">Already have an account? <a href="login.html">Login</a></div>
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

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/admin/register.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:34:00 GMT -->
</html>
