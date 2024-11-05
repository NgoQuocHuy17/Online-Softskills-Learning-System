<%-- 
    Document   : changePassword
    Created on : May 20, 2024, 9:20:33 PM
    Author     : ngoqu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/forgot-password.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:32:30 GMT -->
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
                                <div class="login-header" style="margin-bottom: 0px">
                                    <h3 class="text-center">Forgot Password?</h3>
                                    <label class="text-muted" style="font-size: 12px; margin-bottom: 0px;margin-top: 5px
                                           ">------If you've forgotten your password, enter your account or email----</label>
                                </div>
                                <h3 class="text-center" style="margin-bottom: 10px"><%
                                if(request.getAttribute("message2")!=null)
                                {
                                out.print("<p class='text-danger ml-1'>"+request.getAttribute("message2")+"</p>");
                                }
                                
                                    %>
                                </h3>
                                <form action="forgot-password" method="post">
                                    <div class="form-group">
                                        <label class="form-control-label">Email Address or username</label>
                                        <input type="text" class="form-control" name="input"placeholder="Enter your email or username" required>
                                    </div>
                                    <div class="text-end">
                                        <a class="forgot-link" href="login.html">Remember your password?</a>
                                    </div>
                                    <button class="btn btn-primary login-btn" type="submit">Reset Password</button>
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

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/forgot-password.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:32:30 GMT -->
</html>

