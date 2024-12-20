<%-- 
    Document   : header.jsp
    Created on : May 21, 2024, 11:29:08 PM
    Author     : asus
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
    // Lấy đối tượng User từ session
    User loggedInUser = (User) session.getAttribute("user");
%>
<header class="header header-four">
    <div class="header-fixed">
        <nav class="navbar navbar-expand-lg header-nav scroll-sticky">
            <div class="container">
                <div class="navbar-header">
                    <a id="mobile_btn" href="javascript:void(0);">
                        <span class="bar-icon">
                            <span></span>
                            <span></span>
                            <span></span>
                        </span>
                    </a>
                    <a href="home" class="navbar-brand logo">
                        <img src="assets/img/logo-6.png" class="img-fluid" alt="Logo">
                    </a>
                </div>
                <div class="main-menu-wrapper">
                    <div class="menu-header">
                        <a href="home" class="menu-logo">
                            <img src="assets/img/logo-6.png" class="img-fluid" alt="Logo">
                        </a>
                        <a id="menu_close" class="menu-close" href="javascript:void(0);">
                            <i class="fas fa-times"></i>
                        </a>
                    </div>
                    <ul class="main-nav">
                        <li class="home">
                            <a href="home">Home</a>
                        </li>

                        <% if (loggedInUser != null) { %>
                        <% if (loggedInUser.getRole().equals("Teacher")) { %>
                        <!-- Chỉ hiện phần Mentor nếu role là Teacher -->
                        <li class="has-submenu">
                            <a href="#">Expert<i class="fas fa-chevron-down"></i></a>
                            <ul class="submenu">
                                <li><a href="dashboard.html">Expert Dashboard</a></li>
                                <li class="has-submenu">
                                    <a href="blog.html">Blog</a>
                                    <ul class="submenu">
                                        <li><a href="blog">Blog</a></li>
                                        <li><a href="blog-details.html">Blog View</a></li>
                                        <li><a href="add-blog.html">Add Blog</a></li>
                                        <li><a href="edit-blog.html">Edit Blog</a></li>
                                    </ul>
                                </li>
                                <li><a href="chat.html">Chat</a></li>
                                <li><a href="profile-settings.html">Profile Settings</a></li>
                                <li><a href="reviews.html">Reviews</a></li>
                            </ul>
                        </li>

                        <% } else if (loggedInUser.getRole().equals("Student")) { %>
                        <!-- Chỉ hiện phần Mentee nếu role là Student -->
                        <li class="has-submenu">
                            <a href="#">Student <i class="fas fa-chevron-down"></i></a>
                            <ul class="submenu">
                                <li class="has-submenu">
                                    <a href="#">Experts</a>
                                    <ul class="submenu">
                                        <li><a href="map-grid.html">Map Grid</a></li>
                                        <li><a href="map-list.html">Map List</a></li>
                                    </ul>
                                </li>
                                <li><a href="dashboard-mentee.html">Student Dashboard</a></li>
                                <li><a href="profile-settings-mentee.html">Profile Settings</a></li>
                            </ul>
                        </li>
                        <% } else if (loggedInUser.getRole().equals("Admin")) { %>
                        <!-- Chỉ hiện phần Admin nếu role là Admin -->
                        <li class="has-submenu">
                            <a href="admin/index.html">Admin<i class="fas fa-chevron-down"></i></a>
                            <ul class="submenu">
                                <li><a href="UserList">User List</a></li>
                                <li><a href="RegistrationList">Registration List</a></li>

                            </ul>
                        </li>
                        <% } %>
                        <% } %>


                        <li class="has-submenu">
                            <% if (loggedInUser != null && (loggedInUser.getRole().equals("Teacher") || loggedInUser.getRole().equals("Admin"))) { %>
                            <a href="SubjectList">Courses<i class="fas fa-chevron-down"></i></a>
                                <% } else { %>
                            <a href="course">Courses<i class="fas fa-chevron-down"></i></a>
                                <% } %>

                            <ul class="submenu">
                                <li><a href="course">Course List</a></li>
                                <li><a href="MyCourses">My Courses</a></li>
                                <li><a href="SubjectList">Subject List</a></li>

                            </ul>
                        </li>

                        <li class="home">
                            <a href="blog-list">Blog</a>
                        </li>

                    </ul>
                </div>

                <!-- Phần bên phải header -->
                <ul class="nav header-navbar-rht">
                    <% if (loggedInUser != null) {%>
                    <!-- Nếu người dùng đã đăng nhập, hiển thị avatar -->
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="" 
                                 class="rounded-circle" style="width: 30px; height: 30px;">

                            <span><%= loggedInUser.getFullName()%></span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                            <li><a class="dropdown-item" href="MyRegistrations">My Registrations</a></li>
                            <li><a class="dropdown-item" href="UserProfile">Profile Settings</a></li>
                            <li><a class="dropdown-item" href="Logout">Logout</a></li>
                        </ul>
                    </li>
                    <% } else { %>
                    <!-- Nếu chưa đăng nhập, hiển thị nút Login/Signup -->
                    <li class="nav-item">
                        <a class="nav-link header-login-two" href="login.jsp">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link header-login" href="register.jsp">Sign up</a>
                    </li>
                    <% }%>
                </ul>
            </div>
        </nav>
    </div>
</header>
