
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>My Courses</title>
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
                            <form action="my-courses" method="get">
                                <div class="row">
                                    <!-- Page Size Dropdown -->
                                    <div class="col-md-6">
                                        <div class="page-size-dropdown mt-3">
                                            <label for="pageSize">Page Size:</label>
                                            <select id="pageSize" name="pageSize" class="form-control">
                                                <c:forEach var="size" items="${[5, 10, 15, 20, 25]}">
                                                    <option 
                                                        value="${size}" 
                                                        ${size == param.pageSize ? "selected" : ""}>
                                                        ${size}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <!-- Show Fields Checkboxes -->
                                    <div class="col-md-6">
                                        <div class="display-options mt-3">
                                            <label>Show Fields:</label>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="showTitle" value="true" ${param.showTitle == 'true' ? 'checked' : ''}>
                                                <label class="form-check-label" for="showTitle">Title</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="showTagline" value="true" ${param.showTagline == 'true' ? 'checked' : ''}>
                                                <label class="form-check-label" for="showTagline">Tagline</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="showDescription" value="true" ${param.showDescription == 'true' ? 'checked' : ''}>
                                                <label class="form-check-label" for="showDescription">Description</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="showCategory" value="true" ${param.showCategory == 'true' ? 'checked' : ''}>
                                                <label class="form-check-label" for="showCategory">Category</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Submit Button -->
                                <div class="mt-3">
                                    <button type="submit" class="btn btn-primary w-100">Apply</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <!-- Header for the page -->
                        <h2 class="mt-4">My Courses</h2>
                    </div>

                    <div class="row">
                        <!-- Loop through the list of courses and display course information -->
                        <c:forEach var="course" items="${courseList}">
                            <div class="row mb-4">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <h3 class="card-text"><strong>Course ID: </strong>${course.id}</h3>

                                            <c:if test="${showTitle}">
                                                <h4 class="card-title">Title: ${course.title}</h4>
                                            </c:if>
                                            <c:if test="${showTagline}">
                                                <p class="card-subtitle mb-2"><strong>Tagline: </strong>${course.tagLine}</p>
                                            </c:if>
                                            <c:if test="${showDescription}">
                                                <p class="card-text"><strong>Description: </strong>${course.description}</p>
                                            </c:if>
                                            <c:if test="${showCategory}">
                                                <p class="card-text"><strong>Category: </strong>${course.category}</p>
                                            </c:if>

                                            <!-- "Learn" button -->
                                            <a href="#" class="btn btn-primary">Learn</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <!-- Pagination Section -->
                    <div class="pagination">
                        <c:if test="${totalPages > 1}">
                            <ul class="pagination">
                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                        <a class="page-link" 
                                           href="my-courses?page=${i}&pageSize=${param.pageSize}&showTitle=${param.showTitle}&showTagline=${param.showTagline}&showDescription=${param.showDescription}&showCategory=${param.showCategory}">
                                            ${i}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
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

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/mentee-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:32:09 GMT -->
</html>
