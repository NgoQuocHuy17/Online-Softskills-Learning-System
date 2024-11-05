<%-- 
    Document   : SignUp.jsp
    Created on : May 15, 2024, 6:47:14 PM
    Author     : asus
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Colorlib Templates">
        <meta name="author" content="Colorlib">
        <meta name="keywords" content="Colorlib Templates">

        <!-- Title Page-->
        <title>Sign Up</title>

        <!-- Icons font CSS-->
        <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <!-- Font special for pages-->
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Vendor CSS-->
        <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="css/main.css" rel="stylesheet" media="all">
    </head>

    <body>
        <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
            <div class="wrapper wrapper--w680">
                <div class="card card-4">
                    <div class="card-body">
                        <h2 class="title">Sign Up</h2>
                        <h4 style="color: red; align-content: center;">
                            ${requestScope.error}
                        </h4>
                        <form id="myForm" action="account" method="Post" onsubmit="return validateForm()">
<<<<<<< Updated upstream:src/main/webapp/SignUp.jsp
                            <!--                            <input type="hidden" name="action" value="checkregister"/>-->
                            <div class="row row-space">
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Full Name</label>
                                        <input class="input--style-4" value="${requestScope.full_name}" oninvalid="CheckFullName(this);" oninput="CheckFullName(this);" type="text" name="fullname">
                                        <span id="span_fullname"></span>
                                    </div>
=======
<!--                            <input type="hidden" name="action" value="checkregister"/>-->
                            <div class="row row-space">
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Account Name</label>
                                        <input class="input--style-4" value="${requestScope.account_name}" type="text" oninvalid="CheckAccountName(this);" oninput="CheckAccountName(this);" name="account_name">
                                        <span id="span_accountName"></span>
                                    </div>

>>>>>>> Stashed changes:build/web/SignUp.jsp
                                </div>
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Email</label>
                                        <input class="input--style-4" value="${requestScope.email}" oninvalid="CheckEmail(this);" oninput="CheckEmail(this);" type="email" name="email">
                                        <span id="span_email"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="row row-space">
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Password</label>
                                        <input class="input--style-4" value="${requestScope.password}" id ="password" oninvalid="CheckPassword(this);" oninput="CheckPassword(this);" type="password" name="password">
                                        <span id = "span_password"></span>
                                    </div>
                                </div>
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Confirm Password</label>
                                        <input class="input--style-4" value="${requestScope.confirm_password}" oninvalid="CheckConfirmPassword(this);" oninput="CheckConfirmPassword(this);" type="password" name="confirm_password">
                                        <span id="span_rePassword"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="row row-space">
<<<<<<< Updated upstream:src/main/webapp/SignUp.jsp

                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Phone Number</label>
                                        <input class="input--style-4" value="${requestScope.phone_number}" oninvalid="CheckPhoneNumber(this);" oninput="CheckPhoneNumber(this);" type="text" name="phonenumber">
                                        <span id="span_phoneNumber"></span>
                                    </div>
                                </div>
=======
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Full Name</label>
                                        <input class="input--style-4" value="${requestScope.full_name}" oninvalid="CheckFullName(this);" oninput="CheckFullName(this);" type="text" name="full_name">
                                        <span id="span_fullname"></span>
                                    </div>
                                </div>
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Phone Number</label>
                                        <input class="input--style-4" value="${requestScope.phone_number}" oninvalid="CheckPhoneNumber(this);" oninput="CheckPhoneNumber(this);" type="text" name="phone">
                                        <span id="span_phoneNumber"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="row row-space">
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Date Of Birth</label>
                                        <div class="input-group-icon">
                                            <input class="input--style-4 js-datepicker" value="${requestScope.dob}" type="text" name="birthday">
                                            <i class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i>

                                        </div>
                                        <span id="span_date"></span>
                                    </div>
                                </div>
>>>>>>> Stashed changes:build/web/SignUp.jsp
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Gender</label>
                                        <div class="p-t-10">
                                            <label class="radio-container m-r-45">Male
                                                <input ${(requestScope.sex) ? "checked=\"checked\"" : ""} type="radio" name="gender" value="true" required>
                                                <span class="checkmark"></span>
                                            </label>
                                            <label class="radio-container">Female
                                                <input ${(!requestScope.sex) ? "checked=\"checked\"" : ""} type="radio" name="gender" value="false" required>
                                                <span class="checkmark"></span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
<<<<<<< Updated upstream:src/main/webapp/SignUp.jsp

=======
                            <div class="row row-space">
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Address</label>
                                        <input class="input--style-4" value="${requestScope.address}" oninvalid="CheckAddress(this);" oninput="CheckAddress(this);" type="text" name="address">
                                        <span id="span_address"></span>
                                    </div>
                                </div>
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label">Role</label>
                                        <div class="rs-select2 js-select-simple select--no-search">
                                            <select name="role" id="roleSelect">
                                                <option disabled="disabled" selected="selected">Choose option</option>
                                                <option value="1" > Mentee </option>
                                                <option value="2" > Mentor </option>

                                            </select>
                                            <div class="select-dropdown"></div>
                                            <span id="error-message" class="error-message"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!--                            <div class="input-group">
                                                            <label class="label">Address</label>
                                                            <input class="input--style-4" type="text" name="email">
                                                        </div>-->
>>>>>>> Stashed changes:build/web/SignUp.jsp
                            <div class="p-t-15">
                                <button class="btn btn--radius-2 btn--blue" id="submit" type="submit">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Jquery JS-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <!-- Vendor JS-->
        <script src="vendor/select2/select2.min.js"></script>
        <script src="vendor/datepicker/moment.min.js"></script>
        <script src="vendor/datepicker/daterangepicker.js"></script>

        <!-- Main JS-->
        <script src="js/global.js"></script>

    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->
