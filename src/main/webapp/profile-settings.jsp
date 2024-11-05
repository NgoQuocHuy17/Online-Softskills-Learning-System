
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.User" %>

<!DOCTYPE html>
<%
    User loggedInUser = (User) session.getAttribute("user");

    String fullName = loggedInUser.getFullName();
    String role = loggedInUser.getRole();
    String gender = loggedInUser.getGender();
    String email = loggedInUser.getEmail();
%>

<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Profile Settings</title>
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
                                            <h6><%= fullName%></h6> <!-- Lấy fullName từ session -->
                                            <p><%= role%></p> <!-- Lấy role từ session -->
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
                            <h2 class="breadcrumb-title">Profile Settings</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="card">
                            <div class="card-body">
                                <form action="UpdateProfile" method="post">
                                    <div class="row form-row">
                                        <%
                                            String message = (String) request.getAttribute("message");
                                            if (message != null) {
                                                String alertType = message.startsWith("Failed") ? "alert-danger" : "alert-success";
                                        %>
                                        <div class="alert <%= alertType%>">
                                            <%= message%>
                                        </div>
                                        <% }%>
                                        <div class="col-12 col-md-12">
                                            <div class="form-group">
                                                <div class="change-avatar">
                                                    <div class="profile-img">
                                                        <img src="assets/img/user/user.jpg" alt="User Image">
                                                    </div>
                                                    <div class="upload-img">
                                                        <div class="change-photo-btn">
                                                            <span><i class="fa fa-upload"></i> Upload Photo</span>
                                                            <input type="file" class="upload" name="avatar">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Full Name</label>
                                                <input type="text" class="form-control" name="fullName" value="<%= fullName%>">
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Gender</label>
                                                <select class="form-control" name="gender">
                                                    <option value="Male" <%= "Male".equals(gender) ? "selected" : ""%>>Male</option>
                                                    <option value="Female" <%= "Female".equals(gender) ? "selected" : ""%>>Female</option>
                                                    <option value="Other" <%= "Other".equals(gender) ? "selected" : ""%>>Other</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Email</label>
                                                <input type="email" class="form-control" name="email" value="<%= email%>" readonly>
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Mobile</label>
                                                <input type="text" name="mobile" value="mobile?" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="submit-section d-flex justify-content-between">
                                        <button type="submit" class="btn btn-primary submit-btn">Save Changes</button>
                                        <a href="change-password.jsp" class="btn btn-primary submit-btn">Change Password</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <footer class="footer">
                <jsp:include page="footer.jsp" />
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
