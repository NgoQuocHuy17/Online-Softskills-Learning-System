<%-- 
    Document   : login
    Created on : May 20, 2024, 7:17:29 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:32:30 GMT -->
    <head>
        <meta charset="utf-8">
        <title>Mentoring</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">

        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">

        <link rel="stylesheet" href="assets/css/bootstrap.min.css">

        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">

        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body class="account-page">

        <div class="main-wrapper">

            <div class="bg-pattern-style">
                <div class="content">

                    <div class="account-content">
                        <div class="account-box">
                            <div class="login-right">
                                <div class="login-header text-center">
                                    <c:set var="cookie" value="${pageContext.request.cookies}"/>
                                    <h3> <span>Happy Programming</span></h3>
                                    <p class="text-muted"></p>
                                </div>
                                <h4 style="color: red; align-content: center;">
                                    ${requestScope.error}
                                </h4>
                                <form method="Post" action="account">
                                    <input type="hidden" name="action" value="login"/>
                                    <div class="form-group">
                                        <label class="form-control-label">UserName</label>
                                        <input type="text" value="${cookie.cookie_username.value}" name="user_name" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-control-label">Password</label>
                                        <div class="pass-group">
                                            <input type="password" name="password" value="${cookie.cookie_password.value}" class="form-control pass-input">
                                            <span class="fas fa-eye toggle-password"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="form-check form-check-xs custom-checkbox">
                                            <input type="checkbox" ${(cookie.cookie_remember.value!=null)?'checked':''} class="form-check-input" name="agreeCheckboxUser" value="ON" id="agree_checkbox_user">
                                            <label class="form-check-label" for="agree_checkbox_user">Remember Password
                                        </div>
                                    </div>
                                    <div class="text-end">
                                        <a class="forgot-link" href="forgot-password.html">Forgot Password ?</a>
                                    </div>
                                    <button class="btn btn-primary login-btn" type="submit">Sign In</button>
                                    <div class="text-center dont-have">Don’t have an account? <a href="register.jsp">Register</a></div>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

        </div>


        <script src="assets/js/jquery-3.6.0.min.js"></script>

        <script src="assets/js/bootstrap.bundle.min.js"></script>

        <script src="assets/js/script.js"></script>
    </body>

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:32:30 GMT -->
</html>
