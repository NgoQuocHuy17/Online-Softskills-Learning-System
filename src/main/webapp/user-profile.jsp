
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.User" %>
<%@ page import="model.UserMedia" %>

<!DOCTYPE html>


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
        <%
            User user = (User) request.getAttribute("user");
        %>
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
                                            <h6><%= user.getFullName()%></h6> <!-- Lấy fullName từ session -->
                                            <p><%= user.getRole()%></p> <!-- Lấy role từ session -->
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
                                <!-- Form cập nhật thông tin người dùng -->
                                <form action="UpdateProfile" method="post" enctype="multipart/form-data">
                                    <div class="row form-row">
                                        <% String message = (String) request.getAttribute("message");
                                            if (message != null) {
                                        %>
                                        <div class="alert ">
                                            <%= message%>
                                        </div>
                                        <% }%>
                                        <div class="col-12 col-md-12">
                                            <div class="form-group">
                                                <div class="change-avatar">
                                                    <div class="profile-img">
                                                        <img src="data:image/jpeg;base64,${avatar.mediaData}" alt="User Image">
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
                                                <input type="text" class="form-control" name="fullName" value="<%= user.getFullName()%>">
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Gender</label>
                                                <select class="form-control" name="gender">
                                                    <option value="Male" <%= "Male".equals(user.getGender()) ? "selected" : ""%>>Male</option>
                                                    <option value="Female" <%= "Female".equals(user.getGender()) ? "selected" : ""%>>Female</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Email</label>
                                                <input type="email" class="form-control" name="email" value="<%= user.getEmail()%>" readonly>
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Address</label>
                                                <input type="text" class="form-control" name="address" value="<%= user.getAddress()%>">
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
                    <!-- Form xóa số điện thoại và email -->
                    <div class="row">
                        <div class="card">
                            <div class="card-body">
                                <h4>User Contact</h4>
                                <form action="DeleteUserContact" method="post">
                                    <input type="hidden" name="userId" value="${user.getId()}">
                                    <div class="row form-row">
                                        <div class="col-12 col-md-6">
                                            <label>Mobile Numbers</label>
                                            <c:forEach var="phone" items="${phones}">
                                                <div class="d-flex align-items-center mb-2">
                                                    <input type="text" class="form-control" name="phone" value="${phone.contactValue}" readonly>
                                                    <button type="submit" class="btn btn-danger btn-sm ml-2">Delete</button>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="col-12 col-md-6">
                                            <label>Emails</label>
                                            <c:forEach var="email" items="${emails}">
                                                <div class="d-flex align-items-center mb-2">
                                                    <input type="email" class="form-control" name="email" value="${email.contactValue}" readonly>
                                                    <button type="submit" class="btn btn-danger btn-sm ml-2">Delete</button>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Form thêm số điện thoại và email -->
                    <div class="row">
                        <div class="card">
                            <div class="card-body">
                                <h4>Update User Contact</h4>
                                <form action="UpdateUserContact" method="post">
                                    <input type="hidden" name="userId" value="${user.getId()}">
                                    <div class="row form-row">
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Contact Type</label>
                                                <select class="form-control" name="contactType">
                                                    <option value="Phone">Phone</option>
                                                    <option value="Email">Email</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Contact Value</label>
                                                <input type="text" class="form-control" name="contactValue">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="submit-section">
                                        <button type="submit" class="btn btn-primary submit-btn">Add Contact</button>
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
