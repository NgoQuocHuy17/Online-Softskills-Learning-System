
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
                        <div class="col-md-12 col-12">
                            <form class="search-filter-form" action="UserList" method="get">
                                <div class="input-group">
                                    <!-- Search Input -->
                                    <input type="text" name="searchTerm" placeholder="Search user..." class="form-control" value="${param.searchTerm}">

                                    <!--Filter -->
                                    <select name="gender" class="form-control">
                                        <option value="">All Genders</option>
                                        <option value="Male" ${param.gender == 'Male' ? 'selected' : ''}>Male</option>
                                        <option value="Female" ${param.gender == 'Female' ? 'selected' : ''}>Female</option>
                                    </select>
                                    <select name="role" class="form-control">
                                        <option value="">All Roles</option>
                                        <option value="Admin" ${param.role == 'Admin' ? 'selected' : ''}>Admin</option>
                                        <option value="User" ${param.role == 'User' ? 'selected' : ''}>User</option>
                                        <option value="Teacher" ${param.role == 'Teacher' ? 'selected' : ''}>Teacher</option>
                                    </select>
                                    <select name="status" class="form-control">
                                        <option value="">All Statuses</option>
                                        <option value="Valid" ${param.status == 'Valid' ? 'selected' : ''}>Active</option>
                                        <option value="Invalid" ${param.status == 'Invalid' ? 'selected' : ''}>Inactive</option>
                                    </select>

                                    <!-- Page Size -->
                                    <input type="number" name="pageSize" placeholder="Page Size" class="form-control" min="1" value="${param.pageSize}"/>

                                    <!-- Submit -->
                                    <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i> Search & Filter</button>
                                </div>

                                <!-- HideField Checkboxes -->
                                <div class="form-group mt-3">
                                    <label class="mr-2">Hide Columns: </label>
                                    <label><input type="checkbox" name="hideFullName" value="true" ${param.hideFullName == 'true' ? 'checked' : ''}> Full Name</label>
                                    <label><input type="checkbox" name="hideGender" value="true" ${param.hideGender == 'true' ? 'checked' : ''}> Gender</label>
                                    <label><input type="checkbox" name="hideEmail" value="true" ${param.hideEmail == 'true' ? 'checked' : ''}> Email</label>
                                    <label><input type="checkbox" name="hideMobile" value="true" ${param.hideMobile == 'true' ? 'checked' : ''}> Mobile</label>
                                    <label><input type="checkbox" name="hideRole" value="true" ${param.hideRole == 'true' ? 'checked' : ''}> Role</label>
                                    <label><input type="checkbox" name="hideStatus" value="true" ${param.hideStatus == 'true' ? 'checked' : ''}> Status</label>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-4 col-12 text-right">
                            <a href="AddUser.jsp" class="btn btn-success">Add User</a>
                        </div>
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
                                            <a href="?sort=id&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">
                                                <i class="fas fa-arrow-up"></i>
                                            </a>
                                            <a href="?sort=id&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">
                                                <i class="fas fa-arrow-down"></i>
                                            </a>
                                        </th>
                                        <c:if test="${!hideFullName}">
                                            <th>
                                                Full Name
                                                <a href="?sort=full_name&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-up"></i>
                                                </a>
                                                <a href="?sort=full_name&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-down"></i>
                                                </a>
                                            </th>
                                        </c:if>
                                        <c:if test="${!hideGender}">
                                            <th>
                                                Gender
                                                <a href="?sort=gender&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-up"></i>
                                                </a>
                                                <a href="?sort=gender&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-down"></i>
                                                </a>
                                            </th>
                                        </c:if>
                                        <c:if test="${!hideEmail}">
                                            <th>
                                                Email
                                                <a href="?sort=email&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-up"></i>
                                                </a>
                                                <a href="?sort=email&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-down"></i>
                                                </a>
                                            </th>
                                        </c:if>
                                        <c:if test="${!hideMobile}">
                                            <th>
                                                Mobile
                                                <a href="?sort=mobile&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-up"></i>
                                                </a>
                                                <a href="?sort=mobile&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-down"></i>
                                                </a>
                                            </th>
                                        </c:if>
                                        <c:if test="${!hideRole}">
                                            <th>
                                                Role
                                                <a href="?sort=role&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-up"></i>
                                                </a>
                                                <a href="?sort=role&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-down"></i>
                                                </a>
                                            </th>
                                        </c:if>
                                        <c:if test="${!hideStatus}">
                                            <th>
                                                Status
                                                <a href="?sort=isValid&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-up"></i>
                                                </a>
                                                <a href="?sort=isValid&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}>
                                                   <i class="fas fa-arrow-down"></i>
                                                </a>
                                            </th>
                                        </c:if>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="user" items="${userList}">
                                        <tr>
                                            <c:if test="${!hideId}">
                                                <td>${user.id}</td>
                                            </c:if>
                                            <c:if test="${!hideFullName}">
                                                <td>${user.fullName}</td>
                                            </c:if>
                                            <c:if test="${!hideGender}">
                                                <td>${user.gender}</td>
                                            </c:if>
                                            <c:if test="${!hideEmail}">
                                                <td>
                                                    <strong>${user.email}</strong> <br/>
                                                    <c:set var="emailKey" value="emails_${user.id}" />
                                                    <c:set var="userEmails" value="${requestScope[emailKey]}" />
                                                    <c:forEach var="email" items="${userEmails}">
                                                        ${email.contactValue} <br/>
                                                    </c:forEach>
                                                </td>
                                            </c:if>
                                            <c:if test="${!hideMobile}">
                                                <td>
                                                    <c:set var="phoneKey" value="phones_${user.id}" />
                                                    <c:set var="userPhones" value="${requestScope[phoneKey]}" />
                                                    <c:forEach var="phone" items="${userPhones}">
                                                        ${phone.contactValue} <br/>
                                                    </c:forEach>
                                                </td>
                                            </c:if>
                                            <c:if test="${!hideRole}">
                                                <td>${user.role}</td>
                                            </c:if>
                                            <c:if test="${!hideStatus}">
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
                                            </c:if>
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
                        <c:if test="${totalPages > 1 && not empty userList}">
                            <div class="blog-pagination mt-4">
                                <nav>
                                    <ul class="pagination justify-content-center">
                                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                            <a class="page-link" href="?page=${currentPage - 1}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&sort=${param.sort}&sortOrder=${param.sortOrder}&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}" tabindex="-1">
                                                <i class="fas fa-angle-double-left"></i>
                                            </a>
                                        </li>
                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                <a class="page-link" href="?page=${i}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&sort=${param.sort}&sortOrder=${param.sortOrder}&hideField=&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">${i}</a>
                                            </li>
                                        </c:forEach>
                                        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                            <a class="page-link" href="?page=${currentPage + 1}&searchTerm=${param.searchTerm}&gender=${param.gender}&role=${param.role}&status=${param.status}&sort=${param.sort}&sortOrder=${param.sortOrder}&hideField=&hideFullName=${hideFullName}&hideGender=${hideGender}&hideEmail=${hideEmail}&hideMobile=${hideMobile}&hideRole=${hideRole}&hideStatus=${hideStatus}&pageSize=${pageSize}">
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
                <jsp:include page="footer.jsp" />
            </footer>

        </div>
        <script data-cfasync="false" src="../../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/plugins/theia-sticky-sidebar/ResizeSensor.js"></script>
        <script src="assets/plugins/theia-sticky-sidebar/theia-sticky-sidebar.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>