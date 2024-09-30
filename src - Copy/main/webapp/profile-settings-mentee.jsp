<%-- 
    Document   : profile-settings-mentee
    Created on : May 26, 2024, 5:20:36 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/profile-settings-mentee.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:32:23 GMT -->
    <head>
        <meta charset="utf-8">
        <title>Mentoring</title>
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
            <jsp:include page="layout_header.jsp"/>



            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-md-12 col-12">
                            <nav aria-label="breadcrumb" class="page-breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Profile Settings</li>
                                </ol>
                            </nav>
                            <h2 class="breadcrumb-title">Profile Settings</h2>
                        </div>
                    </div>
                </div>
            </div>


            <div class="content">
                <div class="container-fluid">
                    <div class="row">

                        <div class="col-md-5 col-lg-4 col-xl-3 theiaStickySidebar">

                            <div class="profile-sidebar">
                                <div class="user-widget">
                                    <div class="pro-avatar">JD</div>
                                    <div class="rating">
                                        <i class="fas fa-star filled"></i>
                                        <i class="fas fa-star filled"></i>
                                        <i class="fas fa-star filled"></i>
                                        <i class="fas fa-star filled"></i>
                                        <i class="fas fa-star"></i>
                                    </div>
                                    <div class="user-info-cont">
                                        <h4 class="usr-name">Jonathan Doe</h4>
                                        <p class="mentor-type">English Literature (M.A)</p>
                                    </div>
                                </div>
                                <div class="progress-bar-custom">
                                    <h6>Complete your profiles ></h6>
                                    <div class="pro-progress">
                                        <div class="tooltip-toggle" tabindex="0"></div>
                                        <div class="tooltip">80%</div>
                                    </div>
                                </div>
                                <div class="custom-sidebar-nav">
                                    <ul>
                                        <li><a href="dashboard.html"><i class="fas fa-home"></i>Dashboard <span><i class="fas fa-chevron-right"></i></span></a></li>
                                        <li><a href="bookings.html"><i class="fas fa-clock"></i>Bookings <span><i class="fas fa-chevron-right"></i></span></a></li>
                                        <li><a href="schedule-timings.html"><i class="fas fa-hourglass-start"></i>Schedule Timings <span><i class="fas fa-chevron-right"></i></span></a></li>
                                        <li><a href="chat.html"><i class="fas fa-comments"></i>Messages <span><i class="fas fa-chevron-right"></i></span></a></li>
                                        <li><a href="profile.html"><i class="fas fa-user-cog"></i>Profile <span><i class="fas fa-chevron-right"></i></span></a></li>
                                        <li><a href="login.html"><i class="fas fa-sign-out-alt"></i>Logout <span><i class="fas fa-chevron-right"></i></span></a></li>
                                    </ul>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-7 col-lg-8 col-xl-9">
                            <div class="card">
                                <div class="card-body">

                                    <form>
                                        <div class="row form-row">
                                            <div class="col-12 col-md-12">
                                                <div class="form-group">
                                                    <div class="change-avatar">
                                                        <div class="profile-img">
                                                            <img src="assets/img/user/user.jpg" alt="User Image">
                                                        </div>
                                                        <div class="upload-img">
                                                            <div class="change-photo-btn">
                                                                <span><i class="fa fa-upload"></i> Upload Photo</span>
                                                                <input type="file" class="upload">
                                                            </div>
                                                            <small class="form-text text-muted">Allowed JPG, GIF or PNG. Max size of 2MB</small>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>First Name</label>
                                                    <input type="text" class="form-control" value="Jonathan">
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>Last Name</label>
                                                    <input type="text" class="form-control" value="Doe">
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>Date of Birth</label>
                                                    <div class="cal-icon">
                                                        <input type="text" class="form-control datetimepicker" value="24-07-1983">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>Blood Group</label>
                                                    <select class="form-control select">
                                                        <option>A-</option>
                                                        <option>A+</option>
                                                        <option>B-</option>
                                                        <option>B+</option>
                                                        <option>AB-</option>
                                                        <option>AB+</option>
                                                        <option>O-</option>
                                                        <option>O+</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>Email ID</label>
                                                    <input type="email" class="form-control" value="jonathandoe@example.com">
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>Mobile</label>
                                                    <input type="text" value="+1 202-555-0125" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-group">
                                                    <label>Address</label>
                                                    <input type="text" class="form-control" value="806 Twin Willow Lane">
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>City</label>
                                                    <input type="text" class="form-control" value="Old Forge">
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>State</label>
                                                    <input type="text" class="form-control" value="Newyork">
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>Zip Code</label>
                                                    <input type="text" class="form-control" value="13420">
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6">
                                                <div class="form-group">
                                                    <label>Country</label>
                                                    <input type="text" class="form-control" value="United States">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="submit-section">
                                            <button type="submit" class="btn btn-primary submit-btn">Save Changes</button>
                                        </div>
                                    </form>

                                </div>
                            </div>
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
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                                        <div class="social-icon">
                                            <ul>
                                                <li>
                                                    <a href="#" target="_blank"><i class="fab fa-facebook-f"></i> </a>
                                                </li>
                                                <li>
                                                    <a href="#" target="_blank"><i class="fab fa-twitter"></i> </a>
                                                </li>
                                                <li>
                                                    <a href="#" target="_blank"><i class="fab fa-linkedin-in"></i></a>
                                                </li>
                                                <li>
                                                    <a href="#" target="_blank"><i class="fab fa-instagram"></i></a>
                                                </li>
                                                <li>
                                                    <a href="#" target="_blank"><i class="fab fa-dribbble"></i> </a>
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
                                            <a href="https://mentoring.dreamguystech.com/cdn-cgi/l/email-protection" class="__cf_email__" data-cfemail="08656d667c677a61666f486d70696578646d266b6765">[email&#160;protected]</a>
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
                                    <div class="copyright-text">
                                        <p class="mb-0">&copy; 2020 Mentoring. All rights reserved.</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </footer>

        </div>


        <script data-cfasync="false" src="../../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="assets/js/jquery-3.6.0.min.js"></script>

        <script src="assets/js/bootstrap.bundle.min.js"></script>

        <script src="assets/plugins/select2/js/select2.min.js"></script>

        <script src="assets/js/moment.min.js"></script>
        <script src="assets/js/bootstrap-datetimepicker.min.js"></script>

        <script src="assets/plugins/theia-sticky-sidebar/ResizeSensor.js"></script>
        <script src="assets/plugins/theia-sticky-sidebar/theia-sticky-sidebar.js"></script>

        <script src="assets/js/script.js"></script>
    </body>

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/profile-settings-mentee.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:32:23 GMT -->
</html>
