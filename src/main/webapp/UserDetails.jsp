<%-- 
    Document   : UserDetails
    Created on : 7 thg 10, 2024, 16:35:04
    Author     : hung6
--%>

<%@page import="model.UserContact"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>

<!DOCTYPE html>

<html lang="en">

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/profile-settings.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:32:21 GMT -->

    <head>
        <meta charset="utf-8">
        <title>User Details</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" href="assets/plugins/select2/css/select2.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>

    <body>
        <div class="main-wrapper">
            <header class="header">
                <div class="header-fixed">
                    <nav class="navbar navbar-expand-lg header-nav">
                        <div class="navbar-header">
                            <a id="mobile_btn" href="javascript:void(0);">
                                <span class="bar-icon">
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                </span>
                            </a>
                        </div>
                        <div class="main-menu-wrapper">
                            <div class="menu-header">
                                <a href="index.html" class="menu-logo">
                                    <img src="assets/img/logo.png" class="img-fluid" alt="Logo">
                                </a>
                                <a id="menu_close" class="menu-close" href="javascript:void(0);">
                                    <i class="fas fa-times"></i>
                                </a>
                            </div>
                            <ul class="main-nav">
                                <li>
                                    <a href="home">Home</a>
                                </li>
                            </ul>

                        </div>
                        <ul class="nav header-navbar-rht">

                            <li class="nav-item dropdown has-arrow logged-item">
                                <a href="#" class="dropdown-toggle nav-link" data-bs-toggle="dropdown">
                                    <span class="user-img">
                                        <img class="rounded-circle" src="assets/img/user/user.jpg" width="31"
                                             alt="Darren Elder">
                                    </span>
                                </a>
                                <div class="dropdown-menu dropdown-menu-end">
                                    <div class="user-header">
                                        <div class="avatar avatar-sm">
                                            <img src="assets/img/user/user.jpg" alt="User Image"
                                                 class="avatar-img rounded-circle">
                                        </div>
                                        <div class="user-text">
                                            <h6>NAME?</h6> <!-- Lấy fullName từ session -->
                                            <p>ROLE?</p> <!-- Lấy role từ session -->
                                        </div>

                                    </div>
                                    <a class="dropdown-item" href="profile-settings.jsp">Profile Settings</a>
                                    <a class="dropdown-item" href="login.jsp">Logout</a>
                                </div>
                            </li>

                        </ul>
                    </nav>
                </div>
            </header>

            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-md-12 col-12">
                            <h2 class="breadcrumb-title">User Details</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="card">
                            <div class="card-body">
                                <form action="UpdateUserDetails" method="post">
                                    <div class="row form-row">
                                        <%
                                            String message = (String) request.getAttribute("message");
                                            if (message != null) {
                                        %>
                                        <div class="alert alert-danger">
                                            <%= message%>
                                        </div>
                                        <%
                                            }
                                            // Lấy dữ liệu người dùng từ thuộc tính servlet
                                            String avatarUrl = (String) request.getAttribute("avatar_url");
                                            String fullName = (String) request.getAttribute("fullName");
                                            String gender = (String) request.getAttribute("gender");
                                            String role = (String) request.getAttribute("role");
                                            String status = (String) request.getAttribute("status");
                                        %>
                                        <div class="col-12 col-md-12">
                                            <div class="form-group">
                                                <div class="change-avatar">
                                                    <div class="profile-img">
                                                        <img src="<%= avatarUrl%>" alt="User Image">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Full Name</label>
                                                <input type="text" class="form-control" name="fullName" value="<%= fullName%>" readonly>
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Gender</label>
                                                <select class="form-control" name="gender" disabled>
                                                    <option value="Male" <%= "Male".equals(gender) ? "selected" : ""%>>Male</option>
                                                    <option value="Female" <%= "Female".equals(gender) ? "selected" : ""%>>Female</option>
                                                    <option value="Other" <%= "Other".equals(gender) ? "selected" : ""%>>Other</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Role</label>
                                                <select class="form-control" name="role">
                                                    <option value="Admin" <%= "Admin".equals(role) ? "selected" : ""%>>Admin</option>
                                                    <option value="User" <%= "User".equals(role) ? "selected" : ""%>>User</option>
                                                    <option value="Guest" <%= "Guest".equals(role) ? "selected" : ""%>>Guest</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Status</label>
                                                <select class="form-control" name="status">
                                                    <option value="Active" <%= "Active".equals(status) ? "selected" : ""%>>Active</option>
                                                    <option value="Inactive" <%= "Inactive".equals(status) ? "selected" : ""%>>Inactive</option>
                                                    <option value="Pending" <%= "Pending".equals(status) ? "selected" : ""%>>Pending</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Hiển thị tất cả số điện thoại -->
                                    <div class="col-12 col-md-12">
                                        <label>Phone</label>
                                        <div class="form-group">
                                            <c:forEach var="email" items="${emails}">
                                                ${email.contactValue} <br/>
                                            </c:forEach>
                                        </div>
                                    </div>

                                    <!-- Hiển thị tất cả email -->
                                    <div class="col-12 col-md-12">
                                        <label>Email</label>
                                        <div class="form-group">
                                            <c:forEach var="phone" items="${phones}">
                                                ${phone.contactValue} <br/>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="submit-section">
                                        <button type="submit" class="btn btn-primary submit-btn">Save Changes</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <footer class="footer">
                <div class="footer-top">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-3 col-md-6">

                                <div class="footer-widget footer-about">
                                    <div class="footer-logo">
                                        <img src="" alt="logo">
                                    </div>
                                    <div class="footer-about-content">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                                            incididunt ut labore et dolore magna aliqua. </p>
                                        <div class="social-icon">
                                            <ul>
                                                <li>
                                                    <a href="#" target="_blank"><i class="fab fa-facebook-f"></i> </a>
                                                </li>
                                                <li>
                                                    <a href="#" target="_blank"><i class="fab fa-twitter"></i> </a>
                                                </li>
                                                <li>
                                                    <a href="#" target="_blank"><i class="fab fa-instagram"></i></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="col-lg-3 col-md-6">

                                <div class="footer-widget footer-menu">
                                    <h2 class="footer-title">For Mentee</h2>
                                    <ul>
                                        <li><a href="search.html">Search Mentors</a></li>
                                        <li><a href="login.jsp">Login</a></li>
                                        <li><a href="register.jsp">Register</a></li>
                                        <li><a href="">Booking</a></li>
                                        <li><a href="">Mentee Dashboard</a></li>
                                    </ul>
                                </div>

                            </div>
                            <div class="col-lg-3 col-md-6">

                                <div class="footer-widget footer-menu">
                                    <h2 class="footer-title">For Mentors</h2>
                                    <ul>
                                        <li><a href="">Appointments</a></li>
                                        <li><a href="">Chat</a></li>
                                        <li><a href="login.jsp">Login</a></li>
                                        <li><a href="register.jsp">Register</a></li>
                                        <li><a href="">Mentor Dashboard</a></li>
                                    </ul>
                                </div>

                            </div>
                            <div class="col-lg-3 col-md-6">

                                <div class="footer-widget footer-contact">
                                    <h2 class="footer-title">Contact Us</h2>
                                    <div class="footer-contact-info">
                                        <div class="footer-address">
                                            <span><i class="fas fa-map-marker-alt"></i></span>
                                            <p> FPT University </p>
                                        </div>
                                        <p>
                                            <i class="fas fa-phone-alt"></i>
                                            +1 315 369 5943
                                        </p>
                                        <p class="mb-0">
                                            <i class="fas fa-envelope"></i>
                                            Email
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="footer-bottom">
                    <div class="container-fluid">

                        <div class="copyright">
                            <div class="row">
                                <div class="col-12 text-center">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
        </div>

        <script data-cfasync="false" src="../../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/plugins/select2/js/select2.min.js"></script>
        <script src="assets/js/moment.min.js"></script>
        <script src="assets/js/bootstrap-datetimepicker.min.js"></script>
        <script src="assets/plugins/theia-sticky-sidebar/ResizeSensor.js"></script>
        <script src="assets/plugins/theia-sticky-sidebar/theia-sticky-sidebar.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>

