<%-- 
    Document   : dashboard-mentee
    Created on : May 26, 2024, 5:31:14 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/dashboard-mentee.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:32:23 GMT -->
    <head>
        <meta charset="utf-8">
        <title>Mentoring</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">

        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">

        <link rel="stylesheet" href="assets/css/bootstrap.min.css">

        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">

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
                                    <li class="breadcrumb-item active" aria-current="page">Dashboard</li>
                                </ol>
                            </nav>
                            <h2 class="breadcrumb-title">Dashboard</h2>
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
                                        <li><a href="dashboard-mentee.html" class="active"><i class="fas fa-home"></i>Dashboard <span><i class="fas fa-chevron-right"></i></span></a></li>
                                        <li><a href="bookings-mentee.html"><i class="fas fa-clock"></i>Bookings <span><i class="fas fa-chevron-right"></i></span></a></li>
                                        <li><a href="chat-mentee.html"><i class="fas fa-comments"></i>Messages <span><i class="fas fa-chevron-right"></i></span></a></li>
                                        <li><a href="favourites.html"><i class="fas fa-star"></i>Favourites <span><i class="fas fa-chevron-right"></i></span></a></li>
                                        <li><a href="profile-mentee.html"><i class="fas fa-user-cog"></i>Profile <span><i class="fas fa-chevron-right"></i></span></a></li>
                                        <li><a href="login.html"><i class="fas fa-sign-out-alt"></i>Logout <span><i class="fas fa-chevron-right"></i></span></a></li>
                                    </ul>
                                </div>
                            </div>

                        </div>
                        <div class="col-md-7 col-lg-8 col-xl-9">
                            <div class="row">
                                <div class="col-md-12 col-lg-4 dash-board-list blue">
                                    <div class="dash-widget">
                                        <div class="circle-bar">
                                            <div class="icon-col">
                                                <i class="fas fa-calendar-check"></i>
                                            </div>
                                        </div>
                                        <div class="dash-widget-info">
                                            <h3>23</h3>
                                            <h6>Appointments</h6>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12 col-lg-4 dash-board-list yellow">
                                    <div class="dash-widget">
                                        <div class="circle-bar">
                                            <div class="icon-col">
                                                <i class="fas fa-star"></i>
                                            </div>
                                        </div>
                                        <div class="dash-widget-info">
                                            <h3>33</h3>
                                            <h6>Mentoring Points</h6>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12 col-lg-4 dash-board-list pink">
                                    <div class="dash-widget">
                                        <div class="circle-bar">
                                            <div class="icon-col">
                                                <i class="fas fa-graduation-cap"></i>
                                            </div>
                                        </div>
                                        <div class="dash-widget-info">
                                            <h3>14</h3>
                                            <h6>Rank</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <h4 class="mb-4">Mentor Lists</h4>
                                    <div class="card card-table">
                                        <div class="card-body">
                                            <div class="table-responsive">
                                                <table class="table table-hover table-center mb-0">
                                                    <thead>
                                                        <tr>
                                                            <th>BASIC INFO</th>
                                                            <th>CREATED DATE</th>
                                                            <th class="text-center">TAGS</th>
                                                            <th class="text-center">ACTION</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <h2 class="table-avatar">
                                                                    <a href="profile.html" class="avatar avatar-sm me-2"><img class="avatar-img rounded-circle" src="assets/img/user/user14.jpg" alt="User Image"></a>
                                                                    <a href="profile.html">Tyrone Roberts<span><span class="__cf_email__" data-cfemail="12666b607d7c77607d70776066615273767d70773c717d7f">[email&#160;protected]</span></span></a>
                                                                </h2>
                                                            </td>
                                                            <td>08 April 2020</td>
                                                            <td class="text-center"><span class="pending">PENDING</span></td>
                                                            <td class="text-center"><a href="profile-mentee.html" class="btn btn-sm bg-info-light"><i class="far fa-eye"></i> View</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <h2 class="table-avatar">
                                                                    <a href="profile.html" class="avatar avatar-sm me-2"><img class="avatar-img rounded-circle" src="assets/img/user/user15.jpg" alt="User Image"></a>
                                                                    <a href="profile.html">Julie Pennington <span><span class="__cf_email__" data-cfemail="046e71686d614465606b66612a676b69">[email&#160;protected]</span></span></a>
                                                                </h2>
                                                            </td>
                                                            <td>08 April 2020</td>
                                                            <td class="text-center"><span class="pending">PENDING</span></td>
                                                            <td class="text-center"><a href="profile-mentee.html" class="btn btn-sm bg-info-light"><i class="far fa-eye"></i> View</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <h2 class="table-avatar">
                                                                    <a href="profile.html" class="avatar avatar-sm me-2"><img class="avatar-img rounded-circle" src="assets/img/user/user12.jpg" alt="User Image"></a>
                                                                    <a href="profile.html">Allen Davis <span><span class="__cf_email__" data-cfemail="98f9f4f4fdf6fcf9eef1ebd8f9fcf7fafdb6fbf7f5">[email&#160;protected]</span></span></a>
                                                                </h2>
                                                            </td>
                                                            <td>08 April 2020</td>
                                                            <td class="text-center"><span class="pending">PENDING</span></td>
                                                            <td class="text-center"><a href="profile-mentee.html" class="btn btn-sm bg-info-light"><i class="far fa-eye"></i> View</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <h2 class="table-avatar">
                                                                    <a href="profile.html" class="avatar avatar-sm me-2"><img class="avatar-img rounded-circle" src="assets/img/user/user13.jpg" alt="User Image"></a>
                                                                    <a href="profile.html">Patricia Manzi <span><span class="__cf_email__" data-cfemail="54243520263d373d3539353a2e3d1435303b36317a373b39">[email&#160;protected]</span></span></a>
                                                                </h2>
                                                            </td>
                                                            <td>08 April 2020</td>
                                                            <td class="text-center"><span class="accept">ACCEPTED</span></td>
                                                            <td class="text-center"><a href="profile-mentee.html" class="btn btn-sm bg-info-light"><i class="far fa-eye"></i> View</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <h2 class="table-avatar">
                                                                    <a href="profile.html" class="avatar avatar-sm me-2"><img class="avatar-img rounded-circle" src="assets/img/user/user5.jpg" alt="User Image"></a>
                                                                    <a href="profile.html">Olive Lawrence <span><span class="__cf_email__" data-cfemail="7a1516130c1f161b0d081f14191f3a1b1e15181f54191517">[email&#160;protected]</span></span></a>
                                                                </h2>
                                                            </td>
                                                            <td>08 April 2020</td>
                                                            <td class="text-center"><span class="accept">ACCEPTED</span></td>
                                                            <td class="text-center"><a href="profile-mentee.html" class="btn btn-sm bg-info-light"><i class="far fa-eye"></i> View</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <h2 class="table-avatar">
                                                                    <a href="profile.html" class="avatar avatar-sm me-2"><img class="avatar-img rounded-circle" src="assets/img/user/user10.jpg" alt="User Image"></a>
                                                                    <a href="profile.html">Frances Foster <span><span class="__cf_email__" data-cfemail="7d1b0f1c131e180e3d1c19121f18531e1210">[email&#160;protected]</span></span></a>
                                                                </h2>
                                                            </td>
                                                            <td>08 April 2020</td>
                                                            <td class="text-center"><span class="accept">ACCEPTED</span></td>
                                                            <td class="text-center"><a href="profile-mentee.html" class="btn btn-sm bg-info-light"><i class="far fa-eye"></i> View</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <h2 class="table-avatar">
                                                                    <a href="profile.html" class="avatar avatar-sm me-2"><img class="avatar-img rounded-circle" src="assets/img/user/user7.jpg" alt="User Image"></a>
                                                                    <a href="profile.html">Deloris Briscoe <span><span class="__cf_email__" data-cfemail="2246474e4d504b5140504b51414d476243464d40470c414d4f">[email&#160;protected]</span></span></a>
                                                                </h2>
                                                            </td>
                                                            <td>08 April 2020</td>
                                                            <td class="text-center"><span class="reject">REJECTED</span></td>
                                                            <td class="text-center"><a href="profile-mentee.html" class="btn btn-sm bg-info-light"><i class="far fa-eye"></i> View</a></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
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
                                            <a href="https://mentoring.dreamguystech.com/cdn-cgi/l/email-protection" class="__cf_email__" data-cfemail="38555d564c574a51565f785d40595548545d165b5755">[email&#160;protected]</a>
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

        <script src="assets/plugins/theia-sticky-sidebar/ResizeSensor.js"></script>
        <script src="assets/plugins/theia-sticky-sidebar/theia-sticky-sidebar.js"></script>

        <script src="assets/js/script.js"></script>
    </body>

    <!-- Mirrored from mentoring.dreamguystech.com/html/template/dashboard-mentee.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 14 May 2023 10:32:23 GMT -->
</html>