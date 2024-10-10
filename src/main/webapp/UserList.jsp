<%-- 
    Document   : UserList
    Created on : 7 thg 10, 2024, 16:09:16
    Author     : hung6
--%>

<%@page import="model.UserContact"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>UserList</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">

        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">

        <link rel="stylesheet" href="assets/css/bootstrap.min.css">

        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">

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
                                <li class="has-submenu">
                                    <a href="home">Home</a>   
                                </li>

                                <li class="login-link">
                                    <a href="login.html">Login / Signup</a>
                                </li>
                            </ul>
                        </div>
                        <ul class="nav header-navbar-rht">

                            <li class="nav-item dropdown has-arrow logged-item">
                                <a href="#" class="dropdown-toggle nav-link" data-bs-toggle="dropdown">
                                    <span class="user-img">
                                        <img class="rounded-circle" src="assets/img/user/user.jpg" width="31" alt="Darren Elder">
                                    </span>
                                </a>
                                <div class="dropdown-menu dropdown-menu-end">
                                    <div class="user-header">
                                        <div class="avatar avatar-sm">
                                            <img src="assets/img/user/user.jpg" alt="User Image" class="avatar-img rounded-circle">
                                        </div>
                                        <div class="user-text">
                                            <h6>Jonathan Doe</h6>
                                            <p class="text-muted mb-0">Mentor</p>
                                        </div>
                                    </div>
                                    <a class="dropdown-item" href="dashboard.html">Dashboard</a>
                                    <a class="dropdown-item" href="profile-settings.html">Profile Settings</a>
                                    <a class="dropdown-item" href="login.html">Logout</a>
                                </div>
                            </li>

                        </ul>
                    </nav>
                </div>
            </header>


            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">

                        <div class="col-md-4 col-12">
                            <form class="search-form custom-search-form" action="UserList" method="get">
                                <div class="input-group">
                                    <input type="text" name="searchTerm" placeholder="Search user..." class="form-control" value="${param.searchTerm}">
                                    <input type="hidden" name="gender" value="${param.gender}"/>
                                    <input type="hidden" name="role" value="${param.role}"/>
                                    <input type="hidden" name="status" value="${param.status}"/>
                                    <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>
                                </div>
                            </form>

                        </div>

                        <div class="col-md-4 col-12">
                            <form class="filter-form" action="UserList" method="get">
                                <div class="input-group">
                                    <input type="hidden" name="searchTerm" value="${param.searchTerm}"/>
                                    <select name="gender" class="form-control">
                                        <option value="">All Genders</option>
                                        <option value="Male" ${param.gender == 'Male' ? 'selected' : ''}>Male</option>
                                        <option value="Female" ${param.gender == 'Female' ? 'selected' : ''}>Female</option>
                                    </select>
                                    <select name="role" class="form-control">
                                        <option value="">All Roles</option>
                                        <option value="Admin" ${param.role == 'Admin' ? 'selected' : ''}>Admin</option>
                                        <option value="User" ${param.role == 'User' ? 'selected' : ''}>User</option>
                                    </select>
                                    <select name="status" class="form-control">
                                        <option value="">All Statuses</option>
                                        <option value="Valid" ${param.status == 'Valid' ? 'selected' : ''}>Valid</option>
                                        <option value="Invalid" ${param.status == 'Invalid' ? 'selected' : ''}>Invalid</option>
                                    </select>
                                    <button type="submit" class="btn btn-primary">Filter</button>
                                </div>
                            </form>

                        </div>

                        <div class="col-md-4 col-12 text-right">
                            <a href="AddUser.jsp" class="btn btn-success">Add User</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <!-- Table for displaying user information -->
                        <h2 class="mt-4">User List</h2>
                        <c:if test="${empty userList}">
                            <div class="alert alert-warning">
                                Không có người dùng hợp lệ
                            </div>
                        </c:if>
                        <c:if test="${not empty userList}">
                            <table class="table table-bordered table-hover">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>
                                            User ID
                                            <a href="?sort=id&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}">
                                                <i class="fas fa-arrow-up"></i>
                                            </a>
                                            <a href="?sort=id&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}">
                                                <i class="fas fa-arrow-down"></i>
                                            </a>
                                        </th>
                                        <th>
                                            Full Name  
                                            <a href="?sort=full_name&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}">
                                                <i class="fas fa-arrow-up"></i>
                                            </a>
                                            <a href="?sort=full_name&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}">
                                                <i class="fas fa-arrow-down"></i>
                                            </a>
                                        </th>
                                        <th>
                                            Gender 
                                            <a href="?sort=gender&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}">
                                                <i class="fas fa-arrow-up"></i>
                                            </a>
                                            <a href="?sort=gender&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}">
                                                <i class="fas fa-arrow-down"></i>
                                            </a>
                                        </th>
                                        <th>
                                            Email 
                                            <a href="?sort=email&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}">
                                                <i class="fas fa-arrow-up"></i>
                                            </a>
                                            <a href="?sort=email&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}">
                                                <i class="fas fa-arrow-down"></i>
                                            </a>
                                        </th>
                                        <th>
                                            Mobile 
                                            <a href="?sort=mobile&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}">
                                                <i class="fas fa-arrow-up"></i>
                                            </a>
                                            <a href="?sort=mobile&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}">
                                                <i class="fas fa-arrow-down"></i>
                                            </a>
                                        </th>
                                        <th>
                                            Role 
                                            <a href="?sort=role&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}">
                                                <i class="fas fa-arrow-up"></i>
                                            </a>
                                            <a href="?sort=role&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}">
                                                <i class="fas fa-arrow-down"></i>
                                            </a>
                                        </th>
                                        <th>
                                            Status 
                                            <a href="?sort=isValid&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}">
                                                <i class="fas fa-arrow-up"></i>
                                            </a>
                                            <a href="?sort=status&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}">
                                                <i class="fas fa-arrow-down"></i>
                                            </a>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="user" items="${userList}">
                                        <tr>
                                            <td>${user.id}</td>
                                            <td>${user.fullName}</td>
                                            <td>${user.gender}</td>
                                            <td>
                                                <strong>${user.email}</strong> <br/>
                                                <c:set var="emailKey" value="emails_${user.id}" />
                                                <c:set var="userEmails" value="${requestScope[emailKey]}" />
                                                <c:forEach var="email" items="${userEmails}">
                                                    ${email.contactValue} <br/>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <c:set var="phoneKey" value="phones_${user.id}" />
                                                <c:set var="userPhones" value="${requestScope[phoneKey]}" />
                                                <c:forEach var="phone" items="${userPhones}">
                                                    ${phone.contactValue} <br/>
                                                </c:forEach>
                                            </td>
                                            <td>${user.role}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${user.isValid == 1}">
                                                        Active
                                                    </c:when>
                                                    <c:otherwise>
                                                        Inactive
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <form action="UserDetails" method="get">
                                                    <input type="hidden" name="userId" value="${user.id}"/>
                                                    <button type="submit" class="btn btn-primary">Details</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>

                    <div class="row">
                        <!-- Phân trang -->
                        <c:if test="${totalPages > 1 && not empty userList}">
                            <div class="blog-pagination mt-4">
                                <nav>
                                    <ul class="pagination justify-content-center">
                                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                            <a class="page-link" href="?page=${currentPage - 1}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&sort=${param.sort}&sortOrder=${param.sortOrder}" tabindex="-1">
                                                <i class="fas fa-angle-double-left"></i>
                                            </a>
                                        </li>
                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                <a class="page-link" href="?page=${i}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&sort=${param.sort}&sortOrder=${param.sortOrder}">${i}</a>
                                            </li>
                                        </c:forEach>
                                        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                            <a class="page-link" href="?page=${currentPage + 1}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&sort=${param.sort}&sortOrder=${param.sortOrder}">
                                                <i class="fas fa-angle-double-right"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </c:if>
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


        <script data-cfasync="false" src="../../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="assets/js/jquery-3.6.0.min.js"></script>

        <script src="assets/js/bootstrap.bundle.min.js"></script>

        <script src="assets/plugins/theia-sticky-sidebar/ResizeSensor.js"></script>
        <script src="assets/plugins/theia-sticky-sidebar/theia-sticky-sidebar.js"></script>

        <script src="assets/js/script.js"></script>
    </body>

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/mentee-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:32:09 GMT -->
</html>