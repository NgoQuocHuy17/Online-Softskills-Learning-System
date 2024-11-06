
<%@page import="model.UserContact"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                            <h2 class="breadcrumb-title">User Details</h2>
                        </div>
                        <div class="col-md-4 col-12 text-right">
                            <a href="add-user.jsp" class="btn btn-success">Add New User</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <!-- Form lưu thông tin người dùng -->
                    <form action="UpdateUserDetails" method="post" enctype="multipart/form-data">
                        <div class="row form-row">
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
                            <input type="hidden" name="userId" value="<%= user.getId()%>">
                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <div class="change-avatar">
                                        <div class="profile-img">
                                            <img src="data:image/jpeg;base64,${avatar.mediaData}" alt="User Image">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Full Name</label>
                                    <input type="text" class="form-control" name="fullName" value="${user.getFullName()}" readonly>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Gender</label>
                                    <select class="form-control" name="gender" disabled>
                                        <option value="Male" <%= "Male".equals(user.getGender()) ? "selected" : ""%>>Male</option>
                                        <option value="Female" <%= "Female".equals(user.getGender()) ? "selected" : ""%>>Female</option>
                                        <option value="Other" <%= "Other".equals(user.getGender()) ? "selected" : ""%>>Other</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Role</label>
                                    <select class="form-control" name="role">
                                        <option value="Admin" <%= "Admin".equals(user.getRole()) ? "selected" : ""%>>Admin</option>
                                        <option value="Teacher" <%= "Teacher".equals(user.getRole()) ? "selected" : ""%>>Teacher</option>
                                        <option value="Student" <%= "Student".equals(user.getRole()) ? "selected" : ""%>>Student</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Status</label>
                                    <select class="form-control" name="status">
                                        <option value="Active" <%= "Active".equals(user.getStatus() == 1 ? "Active" : "Inactive") ? "selected" : ""%>>Active</option>
                                        <option value="Inactive" <%= "Inactive".equals(user.getStatus() == 0 ? "Inactive" : "Active") ? "selected" : ""%>>Inactive</option>
                                    </select>
                                </div>
                            </div>

                            <!-- Thêm ô hiển thị address -->
                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Address</label>
                                    <input type="text" class="form-control" name="address" value="${user.getAddress()}" readonly>
                                </div>
                            </div>

                            <!-- Hiển thị tất cả số điện thoại -->
                            <div class="col-12 col-md-6">
                                <label>Phone</label>
                                <div class="form-group">
                                    <c:forEach var="phone" items="${phones}">
                                        <input type="text" class="form-control mb-2" value="${phone.contactValue}" readonly>
                                    </c:forEach>
                                </div>
                            </div>

                            <!-- Hiển thị email -->
                            <div class="col-12 col-md-6">
                                <label>Email</label>
                                <div class="form-group">
                                    <input type="text" class="form-control mb-2" value="${user.getEmail()}" readonly>
                                    <c:forEach var="email" items="${emails}">
                                        <input type="text" class="form-control mb-2" value="${email.contactValue}" readonly>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>

                        <div class="submit-section">
                            <button type="submit" class="btn btn-primary submit-btn">Save Information</button>
                        </div>
                    </form>
                    <br>
                    <!-- Form upload thêm ảnh và video -->
                    <form action="UpdateUserDetails" method="post" enctype="multipart/form-data">
                        <div class="row form-row">
                            <div class="col-12">
                                <h4>Upload More Media</h4>
                                <input type="hidden" name="userId" value="<%= user.getId()%>">

                                <div class="form-group">
                                    <label for="newImages">Upload Images</label>
                                    <input type="file" id="newImages" name="newImage" class="form-control" accept="image/*" multiple>
                                    <label for="imageNote">Image Note</label>
                                    <input type="text" id="imageNote" name="imageNote" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label for="newVideos">Upload Videos</label>
                                    <input type="file" id="newVideos" name="newVideo" class="form-control" accept="video/*" multiple>
                                    <label for="videoNote">Video Note</label>
                                    <input type="text" id="videoNote" name="videoNote" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="submit-section">
                            <button type="submit" class="btn btn-primary submit-btn">Save Media</button>
                        </div>
                    </form>

                    <br>
                    <!-- Form xóa ảnh và video -->
                    <div class="row form-row">
                        <div class="col-12">
                            <h4>Uploaded Images</h4>
                            <div class="image-gallery">
                                <c:forEach var="image" items="${images}">
                                    <form action="DeleteUserMedia" method="post" class="d-flex align-items-center">
                                        <input type="hidden" name="userId" value="${user.id}">
                                        <input type="hidden" name="mediaId" value="${image.id}">
                                        <div>
                                            <img width="160" src="data:image/jpeg;base64,${image.mediaData}" alt="User Image" class="img-thumbnail limited-size">
                                            <p>Note: ${image.note}</p>
                                        </div>
                                        <div class="ml-auto">
                                            <button type="submit" class="btn btn-danger btn-sm" style="margin-left: 10px;">Delete</button>
                                        </div>
                                    </form>
                                    <br>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="col-12">
                            <h4>Uploaded Videos</h4>
                            <div class="video-gallery">
                                <c:forEach var="video" items="${videos}">
                                    <form action="DeleteUserMedia" method="post" class="d-flex align-items-center">
                                        <input type="hidden" name="userId" value="${user.id}">
                                        <input type="hidden" name="mediaId" value="${video.id}">
                                        <div>
                                            <video width="320" height="240" controls>
                                                <source src="data:image/jpeg;base64,${video.mediaData}" type="video/mp4">
                                            </video>
                                            <p>Note: ${video.note}</p>
                                        </div>
                                        <div class="ml-auto">
                                            <button type="submit" class="btn btn-danger btn-sm" style="margin-left: 15px;">Delete</button>
                                        </div>
                                    </form>
                                    <br>
                                </c:forEach>
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

