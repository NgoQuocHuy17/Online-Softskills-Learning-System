<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Add New Course</title>
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
                                <li>
                                    <a href="home">Home</a>
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
                            <h2 class="breadcrumb-title">Add New Course</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="card">
                            <div class="card-body">
                                <form action="new-subject" method="post" enctype="multipart/form-data" >
                                    <div class="row form-row">
                                        <!-- Course Name -->
                                        <div class="col-12 col-md-12">
                                            <div class="form-group">
                                                <label>Course Name</label>
                                                <input type="text" class="form-control" name="name" value="" required>
                                            </div>
                                        </div>
                                        <!-- Thumbnail -->
                                        <div class="col-12 col-md-12">
                                            <div class="form-group">
                                                <label>Thumbnail Image</label>
                                               <input type="file" name="images" multiple accept="image/*"><br><br>
                                            </div>
                                        </div>
                                        <!-- Category -->
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Category</label>
                                                <input type="text" class="form-control" name="category" required>
                                            </div>
                                        </div>
                                        <!-- Featured -->
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Featured</label>
                                                <select class="form-control" name="featured">
                                                    <option value="true">Yes</option>
                                                    <option value="false">No</option>
                                                </select>
                                            </div>
                                        </div>
                                        <!-- Owner -->
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Owner</label>
                                                <input type="text" class="form-control" name="owner" required value="${user.fullName}">
                                            </div>
                                        </div>
                                        <!-- Status -->
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Status</label>
                                                <select class="form-control" name="status">
                                                    <option value="Published">Published</option>
                                                    <option value="Draft">Draft</option>
                                                </select>
                                            </div>
                                        </div>
                                        <!-- Description -->
                                        <div class="col-12 col-md-12">
                                            <div class="form-group">
                                                <label>Description</label>
                                                <textarea class="form-control" name="description" rows="4" required></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="submit-section">
                                        <button type="submit" class="btn btn-primary submit-btn">Add Course</button>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <jsp:include page="footer.jsp" />

            <script src="assets/js/jquery-3.6.0.min.js"></script>
            <script src="assets/js/bootstrap.bundle.min.js"></script>
            <script src="assets/js/script.js"></script>
        </div>
    </body>
</html>
