
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Register</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>

    <body class="account-page">
        <div class="main-wrapper">
            <div class="bg-pattern-style bg-pattern-style-register">
                <div class="content">
                    <div class="account-content">
                        <div class="account-box">
                            <div class="login-right">
                                <div class="login-header">
                                    <h3><span>Student</span> Register</h3>
                                    <p class="text-muted">Access to our dashboard</p>
                                </div>
                                <c:if test="${not empty message}">
                                    <div class="alert alert-warning">${message}</div>
                                </c:if>
                                <form action="Register" method="post">
                                    <div class="form-group">
                                        <label class="form-control-label">Full Name</label>
                                        <input id="full-name" type="text" class="form-control" name="full_name" autofocus>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-control-label">Email Address</label>
                                        <input id="email" type="email" class="form-control" name="email">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-control-label">Phone Number</label>
                                        <input id="phone-number" type="text" class="form-control" name="phone_number">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-control-label">Password</label>
                                        <input id="password" type="password" class="form-control" name="password">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-control-label">Confirm Password</label>
                                        <input id="password-confirm" type="password" class="form-control" name="password_confirmation">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-control-label">Gender</label>
                                        <div class="form-inline">
                                            <input class="form-check-input" type="radio" name="gender" id="male" value="Male">
                                            <label class="form-check-label" for="male">Male</label>
                                            <input class="form-check-input" type="radio" name="gender" id="female" value="Female">
                                            <label class="form-check-label" for="female">Female</label>
                                        </div>
                                    </div>
                                    <button class="btn btn-primary login-btn" type="submit">Create Account</button>
                                    <div class="account-footer text-center mt-3">
                                        Already have an account? <a class="forgot-link mb-0" href="login.jsp">Login</a>
                                    </div>
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
</html>
