<%-- 
    Document   : setting-list
    Created on : Oct 11, 2024, 5:06:12 AM
    Author     : Minh
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Settings List</title>
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
                            <ul class="main-nav">
                                <li><a href="index.html">Home</a></li>
                                <li class="active"><a href="setting-list.html">Settings List</a></li>
                                <li class="login-link"><a href="login.html">Login / Signup</a></li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </header>

            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-md-12 col-12">
                            <nav aria-label="breadcrumb" class="page-breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Settings List</li>
                                </ol>
                            </nav>
                            <h2 class="breadcrumb-title">Settings List</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <form action="settings/list" method="get" class="mb-3">
                                <input type="text" name="search" placeholder="Search by value" class="form-control" value="${param.search}">
                                <select name="type" class="form-control mt-2">
                                    <option value="">Select Type</option>
                                    <option value="Type1" <c:if test="${param.type == 'Type1'}">selected</c:if>>Type1</option>
                                    <option value="Type2" <c:if test="${param.type == 'Type2'}">selected</c:if>>Type2</option>
                                    </select>
                                    <select name="status" class="form-control mt-2">
                                        <option value="">Select Status</option>
                                        <option value="Active" <c:if test="${param.status == 'Active'}">selected</c:if>>Active</option>
                                    <option value="Inactive" <c:if test="${param.status == 'Inactive'}">selected</c:if>>Inactive</option>
                                    </select>
                                    <button type="submit" class="btn btn-primary mt-2">Filter</button>
                                </form>
                                <a href="settings/add" class="btn btn-success mb-3">Add New Setting</a>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th><a href="?sort=id&order=${param.order == 'asc' ? 'desc' : 'asc'}">ID</a></th>
                                        <th><a href="?sort=type&order=${param.order == 'asc' ? 'desc' : 'asc'}">Type</a></th>
                                        <th><a href="?sort=value&order=${param.order == 'asc' ? 'desc' : 'asc'}">Value</a></th>
                                        <th><a href="?sort=orderNum&order=${param.order == 'asc' ? 'desc' : 'asc'}">Order</a></th>
                                        <th><a href="?sort=status&order=${param.order == 'asc' ? 'desc' : 'asc'}">Status</a></th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="setting" items="${settings}">
                                        <tr>
                                            <td>${setting.id}</td>
                                            <td>${setting.type}</td>
                                            <td>${setting.value}</td>
                                            <td>${setting.orderNum}</td>
                                            <td>${setting.status}</td>
                                            <td>
                                                <a href="settings/edit/${setting.id}" class="btn btn-warning btn-sm">Edit</a>
                                                <a href="settings/delete/${setting.id}" class="btn btn-danger btn-sm">Delete</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
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
                                        <img src="assets/img/logo.png" alt="logo">
                                    </div>
                                    <div class="footer-about-content">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                        <div class="social-icon">
                                            <ul>
                                                <li><a href="#" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
                                                <li><a href="#" target="_blank"><i class="fab fa-twitter"></i></a></li>
                                                <li><a href="#" target="_blank"><i class="fab fa-linkedin-in"></i></a></li>
                                                <li><a href="#" target="_blank"><i class="fab fa-instagram"></i></a></li>
                                                <li><a href="#" target="_blank"><i class="fab fa-dribbble"></i></a></li>
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
                                        <li><a href="login.html">Login</a></li>
                                        <li><a href="register.html">Register</a></li>
                                        <li><a href="booking.html">Booking</a></li>
                                        <li><a href="dashboard-mentee.html">Mentee Dashboard</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6">
                                <div class="footer-widget footer-menu">
                                    <h2 class="footer-title">For Mentors</h2>
                                    <ul>
                                        <li><a href="appointments.html">Appointments</a></li>
                                        <li><a href="chat.html">Chat</a></li>
                                        <li><a href="login.html">Login</a></li>
                                        <li><a href="register.html">Register</a></li>
                                        <li><a href="dashboard.html">Mentor Dashboard</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6">
                                <div class="footer-widget footer-contact">
                                    <h2 class="footer-title">Contact Us</h2>
                                    <div class="footer-contact-info">
                                        <div class="footer-address">
                                            <span><i class="fas fa-map-marker-alt"></i></span>
                                            <p> 3556 Beech Street, San Francisco,<br> California, CA 94108 </p>
                                        </div>
                                        <p>
                                            <i class="fas fa-phone-alt"></i>
                                            +1 315 369 5943
                                        </p>
                                        <p class="mb-0">
                                            <i class="fas fa-envelope"></i>
                                            <a href="mailto:info@example.com">info@example.com</a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="footer-bottom">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12 text-center">
                                <p class="mb-0">© 2024 Online Soft Skills Learning System. All rights reserved.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>

        </div>

        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>