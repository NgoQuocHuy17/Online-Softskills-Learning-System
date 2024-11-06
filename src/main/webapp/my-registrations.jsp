
<%@page import="model.Package"%>
<%@page import="model.Course"%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>My Registration</title>
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
                            <h2 class="breadcrumb-title">My Registrations</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <c:if test="${not empty sessionScope.message}">
                            <div class="alert alert-info" role="alert">
                                ${sessionScope.message}
                            </div>
                            <c:set var="message" value="${sessionScope.message}" />
                            <c:remove var="message" />
                        </c:if>
                        <div class="col-md-5 col-lg-4 col-xl-3 theiaStickySidebar">
                            <div class="profile-sidebar">
                                <div class="sidebar">
                                    <form action="MyRegistrations" method="get">
                                        <!-- Search Box -->
                                        <div class="search-box">
                                            <input 
                                                type="text" 
                                                name="searchTerm" 
                                                placeholder="Search subject title..." 
                                                class="form-control" 
                                                value="${param.searchTerm}">
                                        </div>

                                        <!-- Category Dropdown -->
                                        <div class="category-dropdown mt-3">
                                            <label for="category">Category:</label>
                                            <select id="category" name="category" class="form-control">
                                                <option value="">All Category</option>
                                                <c:forEach var="cat" items="${categories}">
                                                    <option 
                                                        value="${cat}" 
                                                        ${cat == param.category ? "selected" : ""}>
                                                        ${cat}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <!-- Page Size Dropdown -->
                                        <div class="page-size-input mt-3">
                                            <label for="pageSize">Page Size:</label>
                                            <input type="number" id="pageSize" name="pageSize" class="form-control" value="${param.pageSize}" min="1" max="100" step="1">
                                        </div>

                                        <!-- Display Options -->
                                        <div class="display-options mt-3">
                                            <label>Show Fields:</label>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="showTitle" name="showTitle" value="true" ${param.showTitle == 'true' ? 'checked' : ''}>
                                                <label class="form-check-label" for="showTitle"> Title</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="showCategory" name="showCategory" value="true" ${param.showCategory == 'true' ? 'checked' : ''}>
                                                <label class="form-check-label" for="showCategory"> Category</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="showPackage" name="showPackage" value="true" ${param.showPackage == 'true' ? 'checked' : ''}>
                                                <label class="form-check-label" for="showPackage"> Package</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="showCost" name="showCost" value="true" ${param.showCost == 'true' ? 'checked' : ''}>
                                                <label class="form-check-label" for="showCost"> Cost</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="showStatus" name="showStatus" value="true" ${param.showStatus == 'true' ? 'checked' : ''}>
                                                <label class="form-check-label" for="showStatus"> Status</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="showValid" name="showValid" value="true" ${param.showValid == 'true' ? 'checked' : ''}>
                                                <label class="form-check-label" for="showValid"> Valid time</label>
                                            </div>
                                        </div>

                                        <!-- Submit Button -->
                                        <div class="mt-3">
                                            <button type="submit" class="btn btn-primary w-100">Search</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-7 col-lg-8 col-xl-9">
                            <div class="appointments">
                                <c:forEach var="registration" items="${registrations}">
                                    <div class="appointment-list">
                                        <div class="profile-info-widget">
                                            <div class="profile-det-info">
                                                <h3><strong>Registration ID: ${registration.id}</strong></h3>
                                                <c:if test="${showTitle}">
                                                    <c:set var="courseKey" value="course_${registration.id}" />
                                                    <c:set var="course" value="${requestScope[courseKey]}" />
                                                    <h5>Course Title: ${course.title}</h5>
                                                </c:if>

                                                <c:if test="${showCategory}">
                                                    <h5>Course Category: ${course.category}</h5>
                                                </c:if>

                                                <c:if test="${showPackage}">
                                                    <c:set var="pkgKey" value="package_${registration.id}" />
                                                    <c:set var="pkg" value="${requestScope[pkgKey]}" />
                                                    <h5>Package Name: ${pkg.packageName}</h5>
                                                </c:if>

                                                <c:if test="${showCost}">
                                                    <h5>Total Cost: ${registration.totalCost}$</h5>
                                                </c:if>

                                                <c:if test="${showStatus}">
                                                    <h5>Status: <strong>${registration.status}</strong></h5>
                                                </c:if>

                                                <c:if test="${showValid}">
                                                    <h5><i class="far fa-clock"></i> Valid From: ${registration.validFrom}</h5>
                                                    <h5><i class="far fa-clock"></i> Valid To: ${registration.validTo}</h5>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="appointment-action">
                                            <!-- Only show Edit and Cancel buttons if status is "Submitted" -->
                                            <c:if test="${registration.status == 'Submitted'}">
                                                <a href="" class="btn btn-sm bg-success-light">
                                                    <i class=""></i> Edit
                                                </a>
                                                <a href="CancelRegistration?id=${registration.id}" class="btn btn-sm bg-danger-light">
                                                    <i class="fas fa-times"></i> Cancel
                                                </a>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <div class="pagination">
                                <c:if test="${totalPages > 1}">
                                    <ul class="pagination">
                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                <a class="page-link" href="MyRegistrations?page=${i}&pageSize=${param.pageSize}&category=${param.category}&searchTerm=${param.searchTerm}&showTitle=${param.showTitle}&showCategory=${param.showCategory}&showPackage=${param.showPackage}&showCost=${param.showCost}&showStatus=${param.showStatus}&showValid=${param.showValid}">
                                                    ${i}
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                            </div>
                        </div>
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

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/appointments.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:33:04 GMT -->
</html>