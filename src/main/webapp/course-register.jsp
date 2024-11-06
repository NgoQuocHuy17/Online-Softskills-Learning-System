<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Course, model.Package" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Course Register</title>
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
                                    <a href="course">Back to course list</a>
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
                            <h2 class="breadcrumb-title">Course Register</h2>
                        </div>
                    </div>
                </div>
            </div>

            <%
                // Lấy thông tin khóa học từ servlet
                Course course = (Course) request.getAttribute("course");
                String choosingPackageId = (String) request.getAttribute("choosingPackageId");
                List<Package> packages = (List<Package>) request.getAttribute("packages");

                // Kiểm tra xem có user nào đang đăng nhập hay không
                String user = (String) session.getAttribute("user");
            %>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="card">
                            <div class="card-body">
                                <!-- Thông tin về khóa học và lựa chọn gói -->
                                <h2><%= course.getTitle()%></h2>
                                <h4><%= course.getTagLine()%></h4>
                                <p><%= course.getDescription()%></p>

                                <!-- Form chọn gói -->
                                <form id="packageForm" action="CourseRegister" method="get">
                                    <input type="hidden" name="courseId" value="<%= course.getId()%>">
                                    <div class="form-group">
                                        <label for="packageSelect">Select Package:</label>
                                        <select class="form-control" id="packageSelect" name="choosingPackageId" required>
                                            <c:forEach var="pkg" items="${packages}">
                                                <option value="${pkg.id}" 
                                                        <c:if test="${pkg.id == param.choosingPackageId}">selected</c:if>>
                                                    ${pkg.packageName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Choose Package</button>
                                    <c:if test="${selectedPackagePrice != null}">
                                        <h4>
                                            <strong>Price for the package: </strong> ${selectedPackagePrice}$
                                        </h4>
                                        <%if (user != null) {%>
                                        <button type="submit" class="btn btn-primary">Register</button>
                                        <%}%>
                                    </c:if>
                                </form>
                            </div>
                        </div>
                    </div>

                    <!-- Khối đăng ký khóa học nếu chưa có người dùng đăng nhập -->
                    <div class="row mt-4">
                        <div class="card">
                            <div class="card-body">
                                <%
                                    if (user == null) {
                                %>
                                <h3>Register for this Course</h3>
                                <form action="RegisterCourse" method="post">
                                    <input type="hidden" name="choosingPackageId" value="<%= choosingPackageId%>">

                                    <div class="form-group">
                                        <label>Full Name</label>
                                        <input type="text" class="form-control" name="fullName" required>
                                    </div>

                                    <div class="form-group">
                                        <label>Gender</label>
                                        <select class="form-control" name="gender" required>
                                            <option value="Male">Male</option>
                                            <option value="Female">Female</option>
                                            <option value="Other">Other</option>
                                        </select>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Login Email</label>
                                        <input type="email" class="form-control" name="email" required>
                                    </div>

                                    <div class="form-group">
                                        <label>Email for contact</label>
                                        <div id="emailInputs">
                                            <div class="input-group mb-2">
                                                <input type="email" class="form-control" name="emails[]" placeholder="Enter email" required>
                                                <div class="input-group-append">
                                                    <div class="input-group-text">
                                                        <input type="radio" name="preferredContact" value="email0" checked> Prefer
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button type="button" class="btn btn-secondary" id="addEmail">+ Add Email</button>
                                    </div>

                                    <div class="form-group">
                                        <label>Phone Numbers</label>
                                        <div id="phoneInputs">
                                            <div class="input-group mb-2">
                                                <input type="text" class="form-control" name="phones[]" placeholder="Enter phone number" required>
                                                <div class="input-group-append">
                                                    <div class="input-group-text">
                                                        <input type="radio" name="preferredContact" value="phone0"> Prefer
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button type="button" class="btn btn-secondary" id="addPhone">+ Add Phone</button>
                                    </div>

                                    <button type="submit" class="btn btn-primary">Register this course</button>
                                </form>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>

                    <!-- JavaScript to dynamically add input fields -->
                    <script>
                        let emailIndex = 1;
                        let phoneIndex = 1;

                        document.getElementById('addEmail').addEventListener('click', function () {
                            var emailInputs = document.getElementById('emailInputs');
                            var newEmailInputGroup = document.createElement('div');
                            newEmailInputGroup.className = 'input-group mb-2';
                            newEmailInputGroup.innerHTML = `
                                <input type="email" class="form-control" name="emails[]" placeholder="Enter another email">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <input type="radio" name="preferredContact" value="email${emailIndex}"> Prefer
                                    </div>
                                </div>
                            `;
                            emailInputs.appendChild(newEmailInputGroup);
                            emailIndex++;
                        });

                        document.getElementById('addPhone').addEventListener('click', function () {
                            var phoneInputs = document.getElementById('phoneInputs');
                            var newPhoneInputGroup = document.createElement('div');
                            newPhoneInputGroup.className = 'input-group mb-2';
                            newPhoneInputGroup.innerHTML = `
                                <input type="text" class="form-control" name="phones[]" placeholder="Enter another phone number">
                                <div class="input-group-append">
                                    <div class="input-group-text">
                                        <input type="radio" name="preferredContact" value="phone${phoneIndex}"> Prefer
                                    </div>
                                </div>
                            `;
                            phoneInputs.appendChild(newPhoneInputGroup);
                            phoneIndex++;
                        });
                    </script>
                </div>
            </div>


            <footer class="footer">
                <div class="footer-top">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-3 col-md-6">

                                <div class="footer-widget footer-about">
                                    <div class="footer-logo">
                                        <img src="" alt="logo">
                                    </div>
                                    <div class="footer-about-content">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                                            incididunt ut labore et dolore magna aliqua. </p>
                                        <div class="social-icon">
                                            <ul>
                                                <li>
                                                    <a href="#" target="_blank"><i class="fab fa-facebook-f"></i> </a>
                                                </li>
                                                <li>
                                                    <a href="#" target="_blank"><i class="fab fa-twitter"></i> </a>
                                                </li>
                                                <li>
                                                    <a href="#" target="_blank"><i class="fab fa-instagram"></i></a>
                                                </li>
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
                                        <li><a href="login.jsp">Login</a></li>
                                        <li><a href="register.jsp">Register</a></li>
                                        <li><a href="">Booking</a></li>
                                        <li><a href="">Mentee Dashboard</a></li>
                                    </ul>
                                </div>

                            </div>
                            <div class="col-lg-3 col-md-6">

                                <div class="footer-widget footer-menu">
                                    <h2 class="footer-title">For Mentors</h2>
                                    <ul>
                                        <li><a href="">Appointments</a></li>
                                        <li><a href="">Chat</a></li>
                                        <li><a href="login.jsp">Login</a></li>
                                        <li><a href="register.jsp">Register</a></li>
                                        <li><a href="">Mentor Dashboard</a></li>
                                    </ul>
                                </div>

                            </div>
                            <div class="col-lg-3 col-md-6">

                                <div class="footer-widget footer-contact">
                                    <h2 class="footer-title">Contact Us</h2>
                                    <div class="footer-contact-info">
                                        <div class="footer-address">
                                            <span><i class="fas fa-map-marker-alt"></i></span>
                                            <p> FPT University </p>
                                        </div>
                                        <p>
                                            <i class="fas fa-phone-alt"></i>
                                            +1 315 369 5943
                                        </p>
                                        <p class="mb-0">
                                            <i class="fas fa-envelope"></i>
                                            Email
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="footer-bottom">
                    <div class="container-fluid">

                        <div class="copyright">
                            <div class="row">
                                <div class="col-12 text-center">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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

