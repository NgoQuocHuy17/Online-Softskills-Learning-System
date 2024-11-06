
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<header class="header">
    <%
        User user = (User) session.getAttribute("user");
    %>
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
                    <li>
                        <a href="UserList">User List</a>
                    </li>
                     <li>
                        <a href="RegistrationsList">Registrations List</a>
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
