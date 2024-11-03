
<%@page import="java.util.Base64"%>
<%@page import="model.UserMedia"%>
<%@page import="model.User" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>User Details</title>
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
                                    <a href="UserList">Back to User List</a>
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
                                            <h6>NAME?</h6> <!-- Lấy fullName từ session -->
                                            <p>ROLE?</p> <!-- Lấy role từ session -->
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
                            <h2 class="breadcrumb-title">User Details Test</h2>
                        </div>
                        <div class="col-md-4 col-12 text-right">
                            <a href="AddUser.jsp" class="btn btn-success">Add New User</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="card">
                            <div class="card-body">
                                <h1>User Details</h1>

                                <%
                                    String message = (String) request.getAttribute("message");
                                    if (message != null) {
                                %>
                                <div class="alert alert-success">
                                    <%= message%>
                                </div>
                                <%
                                    }
                                    User user = (User) request.getAttribute("user");
                                %>

                                <h3>User Information</h3>
                                <div>
                                    <p>Full Name: <%= user.getFullName()%></p>
                                    <p>Email: <%= user.getEmail()%></p>
                                    <p>Role: <%= user.getRole()%></p>
                                    <p>Created At: <%= user.getCreatedAt()%></p>
                                </div>

                                <h3>Upload Media</h3>
                                <form action="UpdateUserDetailsTest" method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="userId" value="<%= user.getId()%>"> <!-- Trường ẩn để gửi userId -->
                                    <div>
                                        <label for="mediaUpload">Upload Media:</label>
                                        <input type="file" name="media" id="mediaUpload" accept="image/*,video/*">
                                    </div>
                                    <button type="submit">Upload</button>
                                </form>

                                <h3>Uploaded Media</h3>
                                <div class="media-gallery">
                                    <h4>Images:</h4>
                                    <div class="image-gallery">
                                        <c:forEach var="image" items="${images}">
                                            <div class="image-item">
                                                <img src="data:image/jpeg;base64,${image.mediaData}" alt="User Image" style="max-width: 200px; max-height: 200px;"/>
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <h4>Videos:</h4>
                                    <div class="video-gallery">
                                        <c:forEach var="video" items="${videos}">
                                            <div class="video-item">
                                                <video width="320" height="240" controls>
                                                    <source src="data:video/mp4;base64,${video.mediaData}" type="video/mp4">
                                                </video>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
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

