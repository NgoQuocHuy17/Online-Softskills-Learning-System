<%-- 
    Document   : register
    Created on : May 19, 2024, 10:34:35 PM
    Author     : asus
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>x
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/register.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:32:30 GMT -->
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

            <div class="bg-pattern-style bg-pattern-style-register">
                <div class="content">

                    <div class="account-content">
                        <div class="account-box">
                            <div class="login-right">
                                <div class="login-header">
                                    <h3><span></span> Register</h3>
                                    <p class="text-muted">Access to our dashboard</p>
                                </div>
                                <h4 style="color: red; align-content: center;">
                                    ${requestScope.error}
                                </h4>
                                <form id="myForm" action="account" method="Post" onsubmit="return validateForm()" > 
                                    <input type="hidden" name="action" value="checkregister"/>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="form-control-label">Account Name</label>
                                                <input id="first-name" value="${requestScope.account_name}" oninvalid="CheckAccountName(this);" oninput="CheckAccountName(this);" type="text" class="form-control" name="account_name" autofocus>
                                                <span id="span_accountName"></span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="form-control-label">Email</label>
                                                <input id="last-name" value="${requestScope.email}" oninvalid="CheckEmail(this);" oninput="CheckEmail(this);" type="text" class="form-control" name="email">
                                                <span id="span_email"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <!--                                    <div class="form-group">
                                                                            <label class="form-control-label">Email Address</label>
                                                                            <input id="email" type="email" class="form-control">
                                                                        </div>-->
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="form-control-label">Password</label>
                                                <input id="password" value="${requestScope.password}" type="password" oninvalid="CheckPassword(this);" oninput="CheckPassword(this);" class="form-control" name="password">

                                                <span id = "span_password"></span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="form-control-label">Confirm Password</label>
                                                <input id="password-confirm" value="${requestScope.confirm_password}" type="password" oninvalid="CheckConfirmPassword(this);" oninput="CheckConfirmPassword(this);" class="form-control" name="confirm_password">

                                                <span id="span_rePassword"></span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="form-control-label">FullName</label>
                                                <input id="password" type="text" value="${requestScope.full_name}" oninvalid="CheckFullName(this);" oninput="CheckFullName(this);" class="form-control" name="full_name">
                                                <span id="span_fullname"></span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="form-control-label">PhoneNumber</label>
                                                <input id="password-confirm" value="${requestScope.phone_number}" type="text" oninvalid="CheckPhoneNumber(this);" oninput="CheckPhoneNumber(this);" class="form-control" name="phone">
                                                <span id="span_phoneNumber"></span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="form-control-label">Date Of Birth</label>
                                                <input id="password" type="date" oninvalid="CheckDate(this);" oninput="CheckDate(this);" value="${requestScope.dob}" class="form-control" name="birthday">
                                                <span id="span_date"></span>
                                            </div>
                                            
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="form-control-label">Gender</label>

                                                <div class="form-inline">
                                                    <input class="form-check-input" ${(requestScope.sex) ? "checked=\"checked\"" : ""}  type="radio" name="gender" id="inlineRadio1" value="true">
                                                    <label class="form-check-label" for="inlineRadio1">Male</label>


                                                    <input class="form-check-input" ${(!requestScope.sex) ? "checked=\"checked\"" : ""} type="radio" name="gender" id="inlineRadio2" value="false">
                                                    <label class="form-check-label" for="inlineRadio2">Female</label>
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="form-control-label">Address</label>
                                                <input id="password" value="${requestScope.address}" type="text" oninvalid="CheckAddress(this);" oninput="CheckAddress(this);" class="form-control" name="address">
                                                <span id="span_address"></span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="form-control-label">Role</label>
                                                <select name="role" id="roleSelect" class="form-select form-control" aria-label="Default select example">
                                                    <option disabled="disabled" selected>Choose option</option>
                                                    <option value="1">Mentee</option>
                                                    <option value="2">Mentor</option>

                                                </select>
                                                <span id="error-message" class="error-message"></span>
                                            </div>
                                        </div>
                                    </div>

                                    <!--                                    <div class="form-group">
                                                                            <div class="form-check form-check-xs custom-checkbox">
                                                                                <input type="checkbox" class="form-check-input" name="agreeCheckboxUser" id="agree_checkbox_user">
                                                                                <label class="form-check-label" for="agree_checkbox_user">I agree to Mentoring</label> <a tabindex="-1" href="javascript:void(0);">Privacy Policy</a> &amp; <a tabindex="-1" href="javascript:void(0);"> Terms.</a>
                                                                            </div>
                                                                        </div>-->
                                    <button class="btn btn-primary login-btn" id="submit" type="submit">Sign Up</button>
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

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/register.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:32:30 GMT -->
</html>
