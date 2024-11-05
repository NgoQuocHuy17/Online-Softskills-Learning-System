<html>
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

    <body>
    <body class="account-page">

        <div class="main-wrapper">

            <div class="bg-pattern-style">
                <div class="content">

                    <div class="account-content">
                        <div class="account-box">
                            <div class="login-right">
                                <div class="login-header">
                                    <h3 class="text-center">
                                        <i class="fa fa-lock fa-4x"></i>
                                    </h3>
                                    <h2 class="text-center">Enter OTP</h2>
                                    <h3 class="text-center" style="margin-bottom: 10px">
                                        <%
                                    if(request.getAttribute("message")!=null)
                                    {
                                    out.print("<p class='text-danger ml-1'>"+request.getAttribute("message")+"</p>");
                                    }
                                
                                        %>
                                    </h3>
                                      
                                        <form action="ValidateOtp" method="post">
                                            <div class="form-group">

                                                <input type="number" class="form-control" name="otp" maxlength = "6"
                                                       oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                                                       placeholder="Enter Received Otp" required>
                                            </div>
                                            
                                            <div class="text-end">
                                                <a class="forgot-link" href="login.html">Remember your password?</a>
                                            </div>
                                            
                                           
                                            <button class="btn btn-primary login-btn" type="submit">Verify Otp</button>
                                        </form>
                                    <form style="text-align: center" action="forgotPassword" method="post">
                                             <input type="hidden" name="email" value="<%= request.getParameter("input")%>">
                                            Didn't recived Otp? <input style="border: 0" type="submit" value="Resend OTP">
                                          </form>
                                    </div>
                                </div>
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