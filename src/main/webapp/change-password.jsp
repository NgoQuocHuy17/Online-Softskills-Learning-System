<%-- 
    Document   : newPassword
    Created on : May 21, 2024, 9:05:37 PM
    Author     : ngoqu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html>
    <head>
    <head>

        <meta charset="utf-8">
        <title>Mentoring</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">

        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">

        <link rel="stylesheet" href="assets/css/bootstrap.min.css">

        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">

        <link rel="stylesheet" href="assets/css/style.css">
        <link
            href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'
            rel='stylesheet'>
        <link
            href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'
            rel='stylesheet'>
        <style>
            .placeicon {
                font-family: fontawesome
            }

            .custom-control-label::before {
                background-color: #dee2e6;
                border: #dee2e6
            }
        </style>
    </head>
</head>

<body class="account-page">

    <div class="main-wrapper">

        <div class="bg-pattern-style">
            <div class="content">

                <div class="account-content">
                    <div class="account-box">
                        <div class="login-right">
                            <div class="login-header">
                                <h1 class="text-center" >
                                    <strong>Change Password</strong>
                                </h1>
                                <h3 class="text-center" style="margin-bottom: 10px"><%
                                    if (request.getAttribute("message1") != null) {
                                        out.print("<p class='text-danger ml-1'>" + request.getAttribute("message1") + "</p>");
                                    }

                                    %>
                                </h3>
                                <form action="changepass" method="POST">
                                    <div class="form-group"style="margin-bottom: 10px">
                                        <input type="password" name="oldpassword" placeholder="&#xf084; &nbsp; Old Password"
                                               class="form-control border-info placeicon" required>
                                    </div>
                                    <div class="form-group"style="margin-bottom: 10px">
                                        <input type="password" name="password" placeholder="&#xf084; &nbsp; New Password"
                                               class="form-control border-info placeicon"required>
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="confPassword"
                                               placeholder="&#xf084; &nbsp; Confirm New Password"
                                               class="form-control border-info placeicon"required>
                                    </div>

                                    <!-- Log in Button -->
                                    <button class="btn btn-primary login-btn" type="submit">Change Password</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script type='text/javascript'
    src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>

</body>
</html>
