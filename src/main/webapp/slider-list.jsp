<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Slider" %>
<%@ page import="java.util.List" %>

<%
    // Get the sliders, total sliders, current page, and filter parameters from request attributes
    List<Slider> sliders = (List<Slider>) request.getAttribute("sliders");
    int totalSliders = (Integer) request.getAttribute("totalSliders");
    int currentPage = (Integer) request.getAttribute("currentPage");
    String statusFilter = request.getParameter("status");
    String searchQuery = request.getParameter("searchQuery");
    int slidersPerPage = 5; // Adjust as necessary
    int totalPages = (int) Math.ceil((double) totalSliders / slidersPerPage);
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Slider List</title>
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
                            <a href="index.html" class="navbar-brand logo">
                                <img src="assets/img/logo.png" class="img-fluid" alt="Logo">
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
                                    <a href="index.html">Home <i class="fas fa-chevron-down"></i></a>
                                    <ul class="submenu">
                                        <li><a href="index.html">Home</a></li>
                                        <li><a href="index-two.html">Home 2</a></li>
                                        <li><a href="index-three.html">Home 3</a></li>
                                        <li><a href="index-four.html">Home 4</a></li>
                                        <li><a href="index-five.html">Home 5</a></li>
                                        <li><a href="index-six.html">Home 6</a></li>
                                        <li><a href="index-seven.html">Home 7</a></li>
                                    </ul>
                                </li>
                                <li class="has-submenu active">
                                    <a href="#">Sliders <i class="fas fa-chevron-down"></i></a>
                                    <ul class="submenu">
                                        <li class="active"><a href="slider-list.jsp">Slider List</a></li>
                                        <li><a href="add-slider.jsp">Add Slider</a></li>
                                    </ul>
                                </li>
                                <!-- Other menu items here -->
                                <li class="login-link">
                                    <a href="login.html">Login / Signup</a>
                                </li>
                            </ul>
                        </div>
                        <ul class="nav header-navbar-rht">
                            <li class="nav-item dropdown has-arrow logged-item">
                                <a href="#" class="dropdown-toggle nav-link" data-bs-toggle="dropdown">
                                    <span class="user-img">
                                        <img class="rounded-circle" src="assets/img/user/user.jpg" width="31" alt="User">
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
                            <nav aria-label="breadcrumb" class="page-breadcrumb">
                                <!--<ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Slider List</li>
                                </ol>-->
                            </nav>
                            <h2 class="breadcrumb-title">Slider List</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <h5>Sliders</h5>

                            <!-- Filter Form -->
                            <form method="GET" action="SliderListController">
                                <input type="text" name="searchQuery" placeholder="Search by title or backlink" value="${param.searchQuery}">
                                <select name="status">
                                    <option value="">All</option>
                                    <option value="active" <c:if test="${'active' == param.status}">selected</c:if>>Active</option>
                                    <option value="inactive" <c:if test="${'inactive' == param.status}">selected</c:if>>Inactive</option>
                                    </select>
                                    <input type="submit" value="Filter" class="btn btn-primary">
                                </form>

                                <!-- Sliders Table -->
                            <c:if test="${not empty sliders}">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Title</th>
                                            <th>Image</th>
                                            <th>Status</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="slider" items="${sliders}">
                                            <tr>
                                                <td>${slider.id}</td>
                                                <td>
                                                    <a href="SliderDetailsController?id=${slider.id}">${slider.title}</a>
                                                </td>
                                                <td><img src="${slider.imageUrl}" alt="${slider.title}" style="max-width: 100px;"></td>
                                                <td>${slider.status}</td>
                                                <td>
                                                    <a href="editSlider.jsp?id=${slider.id}" class="btn btn-warning">Edit</a>
                                                    <a href="SliderToggleServlet?id=${slider.id}&status=${slider.status == 'active' ? 'inactive' : 'active'}" class="btn btn-danger">Hide</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                            <c:if test="${empty sliders}">
                                <p>No sliders found.</p>
                            </c:if>

                            <!-- Pagination -->
                            <div class="pagination">
                                <c:if test="${currentPage > 1}">
                                    <a href="?currentPage=${currentPage - 1}&status=${statusFilter}&searchQuery=${searchQuery}">Previous</a>
                                </c:if>
                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <a href="?currentPage=${i}&status=${statusFilter}&searchQuery=${searchQuery}" class="<c:if test='${i == currentPage}'>active</c:if>">${i}</a>
                                </c:forEach>
                                <c:if test="${currentPage < totalPages}">
                                    <a href="?currentPage=${currentPage + 1}&status=${statusFilter}&searchQuery=${searchQuery}">Next</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <footer class="footer">
                <div class="footer-top">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12 col-12">
                                <div class="footer-content">
                                    <p>&copy; 2024 Your Website. All rights reserved.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
        </div>

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>
