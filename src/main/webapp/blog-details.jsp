<%-- 
    Document   : blogdetails
    Created on : Sep 27, 2024, 1:33:27 PM
    Author     : Minh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
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
                                <li class="has-submenu">
                                    <a href>Mentor <i class="fas fa-chevron-down"></i></a>
                                    <ul class="submenu">
                                        <li><a href="dashboard.html">Mentor Dashboard</a></li>
                                        <li><a href="bookings.html">Bookings</a></li>
                                        <li><a href="schedule-timings.html">Schedule Timing</a></li>
                                        <li><a href="mentee-list.html">Mentee List</a></li>
                                        <li><a href="profile-mentee.html">Mentee Profile</a></li>
                                        <li class="has-submenu">
                                            <a href="blog.html">Blog</a>
                                            <ul class="submenu">
                                                <li><a href="blog.html">Blog</a></li>
                                                <li><a href="blog-details.html">Blog View</a></li>
                                                <li><a href="add-blog.html">Add Blog</a></li>
                                                <li><a href="edit-blog.html">Edit Blog</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="chat.html">Chat</a></li>
                                        <li><a href="invoices.html">Invoices</a></li>
                                        <li><a href="profile-settings.html">Profile Settings</a></li>
                                        <li><a href="reviews.html">Reviews</a></li>
                                        <li><a href="mentor-register.html">Mentor Register</a></li>
                                    </ul>
                                </li>
                                <li class="has-submenu">
                                    <a href>Mentee <i class="fas fa-chevron-down"></i></a>
                                    <ul class="submenu">
                                        <li class="has-submenu">
                                            <a href="#">Mentors</a>
                                            <ul class="submenu">
                                                <li><a href="map-grid.html">Map Grid</a></li>
                                                <li><a href="map-list.html">Map List</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="search.html">Search Mentor</a></li>
                                        <li><a href="profile.html">Mentor Profile</a></li>
                                        <li><a href="bookings-mentee.html">Bookings</a></li>
                                        <li><a href="checkout.html">Checkout</a></li>
                                        <li><a href="booking-success.html">Booking Success</a></li>
                                        <li><a href="dashboard-mentee.html">Mentee Dashboard</a></li>
                                        <li><a href="favourites.html">Favourites</a></li>
                                        <li><a href="chat-mentee.html">Chat</a></li>
                                        <li><a href="profile-settings-mentee.html">Profile Settings</a></li>
                                        <li><a href="change-password.html">Change Password</a></li>
                                    </ul>
                                </li>
                                <li class="has-submenu">
                                    <a href>Pages <i class="fas fa-chevron-down"></i></a>
                                    <ul class="submenu">
                                        <li><a href="voice-call.html">Voice Call</a></li>
                                        <li><a href="video-call.html">Video Call</a></li>
                                        <li><a href="search.html">Search Mentors</a></li>
                                        <li><a href="components.html">Components</a></li>
                                        <li class="has-submenu">
                                            <a href="invoices.html">Invoices</a>
                                            <ul class="submenu">
                                                <li><a href="invoices.html">Invoices</a></li>
                                                <li><a href="invoice-view.html">Invoice View</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="blank-page.html">Starter Page</a></li>
                                        <li><a href="login.html">Login</a></li>
                                        <li><a href="register.html">Register</a></li>
                                        <li><a href="forgot-password.html">Forgot Password</a></li>
                                    </ul>
                                </li>
                                <li class="has-submenu active">
                                    <a href>Blog <i class="fas fa-chevron-down"></i></a>
                                    <ul class="submenu">
                                        <li><a href="blog-list.html">Blog List</a></li>
                                        <li><a href="blog-grid.html">Blog Grid</a></li>
                                        <li class="active"><a href="blog-details.html">
                                                <c:out value="${blogPost.title}"/>
                                                Blog Details
                                            </a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="admin/index.html" target="_blank">Admin</a>
                                </li>
                                <li class="login-link">
                                    <a href="login.html">Login / Signup</a>
                                </li>
                            </ul>
                        </div>
                        <ul class="nav header-navbar-rht">
                            <li class="nav-item dropdown has-arrow logged-item">
                                <a href="#" class="dropdown-toggle nav-link" data-bs-toggle="dropdown">
                                    <span class="user-img">
                                        <!--                                        <img class="rounded-circle" src="assets/img/user/user.jpg" width="31" alt="Darren Elder">-->
                                        <span class="user-img">
                                            <img class="rounded-circle" 
                                                 src="${author.avatarUrl}" 
                                                 width="31"
                                                 alt="${author.fullName}">
                                        </span>
                                    </span>
                                </a>
                                <div class="dropdown-menu dropdown-menu-end">
                                    <div class="user-header">
                                        <div class="avatar avatar-sm">
                                            <img src="${author.avatarUrl}" alt="User Image" class="avatar-img rounded-circle">
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
                            <!--                            <nav aria-label="breadcrumb" class="page-breadcrumb">
                                                            <ol class="breadcrumb">
                                                                <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                                                                <li class="breadcrumb-item active" aria-current="page">Blog</li>
                                                            </ol>
                                                        </nav>-->
                            <h2 class="breadcrumb-title">Blog Details</h2>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-8 col-md-12">
                            <div class="blog-view">
                                <div class="blog blog-single-post">
                                    <div class="blog-image">
                                        <a href="javascript:void(0);">
                                            <img alt src="${blogPost.thumbnailUrl}" class="img-fluid">
                                        </a>
                                    </div>
                                    <h3 class="blog-title">${blogPost.title}</h3>
                                    <div class="blog-info clearfix">
                                        <div class="post-left">
                                            <ul>
                                                <li>
                                                    <div class="post-author">
                                                        <img src="${author.avatarUrl}" alt="Post Author">
                                                        <span>
                                                            <c:out value="${author.fullName}"/>
                                                        </span>
                                                    </div>
                                                </li>
                                                <li><i class="far fa-calendar"></i>
                                                    <c:out value="${formattedCreatedAt}"/>
                                                </li>
                                                <li><i class="far fa-calendar"></i>
                                                    <c:out value="${formattedUpdatedAt}"/>
                                                </li>
                                                <!--                                                <li><i class="far fa-comments"></i>12 Comments</li>-->
                                                <li><i class="fa fa-tags"></i>
                                                    <c:out value="${category.name}"/>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="blog-content">
                                        <!--
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                                            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?</p>
                                            <p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.</p>
                                        -->
                                        <p>
                                            <c:out value="${blogPost.content}"/>
                                        </p>
                                    </div>
                                </div>
                                <!--                                <div class="card blog-share clearfix">
                                                                    <div class="card-header">
                                                                        <h4 class="card-title">Share the post</h4>
                                                                    </div>
                                                                    <div class="card-body">
                                                                        <ul class="social-share">
                                                                            <li><a href="#" title="Facebook"><i class="fab fa-facebook"></i></a></li>
                                                                            <li><a href="#" title="Twitter"><i class="fab fa-twitter"></i></a></li>
                                                                            <li><a href="#" title="Linkedin"><i class="fab fa-linkedin"></i></a></li>
                                                                            <li><a href="#" title="Google Plus"><i class="fab fa-google-plus"></i></a></li>
                                                                            <li><a href="#" title="Youtube"><i class="fab fa-youtube"></i></a></li>
                                                                        </ul>
                                                                    </div>
                                                                </div>-->
                                <!--                                <div class="card author-widget clearfix">
                                                                    <div class="card-header">
                                                                        <h4 class="card-title">About Author</h4>
                                                                    </div>
                                                                    <div class="card-body">
                                                                        <div class="about-author">
                                                                            <div class="about-author-img">
                                                                                <div class="author-img-wrap">
                                                                                                                                    <a href="profile.html"><img class="img-fluid rounded-circle" alt src=""></a>
                                                                                    <img class="img-fluid rounded-circle" alt src="${author.avatarUrl}">
                                                                                </div>
                                                                            </div>
                                                                            <div class="author-details">
                                                                                <a class="blog-author-name">
                                <c:out value="${author.fullName}"/>
                            </a>
                            <p class="mb-0">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation.</p>
                        </div>
                    </div>
                </div>
            </div>-->
                                <!--                                <div class="card blog-comments clearfix">
                                                                    <div class="card-header">
                                                                        <h4 class="card-title">Comments (12)</h4>
                                                                    </div>
                                                                    <div class="card-body pb-0">
                                                                        <ul class="comments-list">
                                                                            <li>
                                                                                <div class="comment">
                                                                                    <div class="comment-author">
                                                                                        <img class="avatar" alt src="assets/img/user/user4.jpg">
                                                                                    </div>
                                                                                    <div class="comment-block">
                                                                                        <span class="comment-by">
                                                                                            <span class="blog-author-name">Michelle Fairfax</span>
                                                                                        </span>
                                                                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae, gravida pellentesque urna varius vitae. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                                                                        <p class="blog-date">Dec 6, 2017</p>
                                                                                        <a class="comment-btn" href="#">
                                                                                            <i class="fas fa-reply"></i> Reply
                                                                                        </a>
                                                                                    </div>
                                                                                </div>
                                                                                <ul class="comments-list reply">
                                                                                    <li>
                                                                                        <div class="comment">
                                                                                            <div class="comment-author">
                                                                                                <img class="avatar" alt src="assets/img/user/user5.jpg">
                                                                                            </div>
                                                                                            <div class="comment-block">
                                                                                                <span class="comment-by">
                                                                                                    <span class="blog-author-name">Gina Moore</span>
                                                                                                </span>
                                                                                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae, gravida pellentesque urna varius vitae.</p>
                                                                                                <p class="blog-date">Dec 6, 2017</p>
                                                                                                <a class="comment-btn" href="#">
                                                                                                    <i class="fas fa-reply"></i> Reply
                                                                                                </a>
                                                                                            </div>
                                                                                        </div>
                                                                                    </li>
                                                                                    <li>
                                                                                        <div class="comment">
                                                                                            <div class="comment-author">
                                                                                                <img class="avatar" alt src="assets/img/user/user3.jpg">
                                                                                            </div>
                                                                                            <div class="comment-block">
                                                                                                <span class="comment-by">
                                                                                                    <span class="blog-author-name">Carl Kelly</span>
                                                                                                </span>
                                                                                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae, gravida pellentesque urna varius vitae.</p>
                                                                                                <p class="blog-date">December 7, 2017</p>
                                                                                                <a class="comment-btn" href="#">
                                                                                                    <i class="fas fa-reply"></i> Reply
                                                                                                </a>
                                                                                            </div>
                                                                                        </div>
                                                                                    </li>
                                                                                </ul>
                                                                            </li>
                                                                            <li>
                                                                                <div class="comment">
                                                                                    <div class="comment-author">
                                                                                        <img class="avatar" alt src="assets/img/user/user6.jpg">
                                                                                    </div>
                                                                                    <div class="comment-block">
                                                                                        <span class="comment-by">
                                                                                            <span class="blog-author-name">Elsie Gilley</span>
                                                                                        </span>
                                                                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                                                                        <p class="blog-date">December 11, 2017</p>
                                                                                    </div>
                                                                                </div>
                                                                            </li>
                                                                            <li>
                                                                                <div class="comment">
                                                                                    <div class="comment-author">
                                                                                        <img class="avatar" alt src="assets/img/user/user7.jpg">
                                                                                    </div>
                                                                                    <div class="comment-block">
                                                                                        <span class="comment-by">
                                                                                            <span class="blog-author-name">Joan Gardner</span>
                                                                                        </span>
                                                                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                                                                        <p class="blog-date">December 13, 2017</p>
                                                                                        <a class="comment-btn" href="#">
                                                                                            <i class="fas fa-reply"></i> Reply
                                                                                        </a>
                                                                                    </div>
                                                                                </div>
                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                </div>-->
                                <!--                                <div class="card new-comment clearfix">
                                                                    <div class="card-header">
                                                                        <h4 class="card-title">Leave Comment</h4>
                                                                    </div>
                                                                    <div class="card-body">
                                                                        <form>
                                                                            <div class="form-group">
                                                                                <label>Name <span class="text-danger">*</span></label>
                                                                                <input type="text" class="form-control">
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label>Your Email Address <span class="text-danger">*</span></label>
                                                                                <input type="email" class="form-control">
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label>Comments</label>
                                                                                <textarea rows="4" class="form-control"></textarea>
                                                                            </div>
                                                                            <div class="submit-section">
                                                                                <button class="btn btn-primary submit-btn" type="submit">Submit</button>
                                                                            </div>
                                                                        </form>
                                                                    </div>
                                                                </div>-->
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-12 sidebar-right theiaStickySidebar">
                            <div class="card search-widget">
                                <div class="card-body">
                                    <form class="search-form">
                                        <div class="input-group">
                                            <input type="text" placeholder="Search..." class="form-control">
                                            <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="card post-widget">
                                <div class="card-header">
                                    <h4 class="card-title">Latest Posts</h4>
                                </div>
                                <div class="card-body">
                                    <c:forEach var="blogPost" items="${blogPosts}" end="4">
                                        <li>
                                            <div class="post-thumb">
                                                <a href="blog-details.html?bloglistid=${blogPost.id}">
                                                    <img class="img-fluid" src="${blogPost.thumbnailUrl}" alt="">
                                                </a>
                                            </div>
                                            <div class="post-info">
                                                <h4>
                                                    <a href="BlogDetailsController?bloglistid=${blogPost.id}">
                                                        <c:out value="${blogPost.title}"/>
                                                    </a>
                                                </h4>
                                                <p>
                                                    <c:out value="${formattedCreatedAt}"/>
                                                </p>
                                            </div>
                                        </li>
                                    </c:forEach>
                                    <!--                                        <li>
                                                                                <div class="post-thumb">
                                                                                    <a href="blog-details.html">
                                                                                        <img class="img-fluid" src="assets/img/blog/blog-thumb-02.jpg" alt>
                                                                                    </a>
                                                                                </div>
                                                                                <div class="post-info">
                                                                                    <h4>
                                                                                        <a href="blog-details.html">It is a long established fact that a reader will be</a>
                                                                                    </h4>
                                                                                    <p>3 Dec 2019</p>
                                                                                </div>
                                                                            </li>
                                                                            <li>
                                                                                <div class="post-thumb">
                                                                                    <a href="blog-details.html">
                                                                                        <img class="img-fluid" src="assets/img/blog/blog-thumb-03.jpg" alt>
                                                                                    </a>
                                                                                </div>
                                                                                <div class="post-info">
                                                                                    <h4>
                                                                                        <a href="blog-details.html">here are many variations of passages of Lorem Ipsum</a>
                                                                                    </h4>
                                                                                    <p>3 Dec 2019</p>
                                                                                </div>
                                                                            </li>
                                                                            <li>
                                                                                <div class="post-thumb">
                                                                                    <a href="blog-details.html">
                                                                                        <img class="img-fluid" src="assets/img/blog/blog-thumb-04.jpg" alt>
                                                                                    </a>
                                                                                </div>
                                                                                <div class="post-info">
                                                                                    <h4>
                                                                                        <a href="blog-details.html">The standard chunk of Lorem Ipsum used since the</a>
                                                                                    </h4>
                                                                                    <p>2 Dec 2019</p>
                                                                                </div>
                                                                            </li>
                                                                            <li>
                                                                                <div class="post-thumb">
                                                                                    <a href="blog-details.html">
                                                                                        <img class="img-fluid" src="assets/img/blog/blog-thumb-05.jpg" alt>
                                                                                    </a>
                                                                                </div>
                                                                                <div class="post-info">
                                                                                    <h4>
                                                                                        <a href="blog-details.html">to generate Lorem Ipsum which looks reasonable.</a>
                                                                                    </h4>
                                                                                    <p>1 Dec 2019</p>
                                                                                </div>
                                                                            </li>-->
                                    </ul>
                                </div>
                            </div>
                            <div class="card category-widget">
                                <div class="card-header">
                                    <h4 class="card-title">Blog Categories</h4>
                                </div>
                                <div class="card-body">
                                    <ul class="categories">
                                        <li><a href="#">HTML <span>(62)</span></a></li>
                                        <li><a href="#">Css <span>(27)</span></a></li>
                                        <li><a href="#">Java Script <span>(41)</span></a></li>
                                        <li><a href="#">Photoshop <span>(16)</span></a></li>
                                        <li><a href="#">Wordpress <span>(55)</span></a></li>
                                        <li><a href="#">VB <span>(07)</span></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card tags-widget">
                                <div class="card-header">
                                    <h4 class="card-title">Tags</h4>
                                </div>
                                <div class="card-body">
                                    <ul class="tags">
                                        <li><a href="#" class="tag">HTML</a></li>
                                        <li><a href="#" class="tag">Css</a></li>
                                        <li><a href="#" class="tag">Java Script</a></li>
                                        <li><a href="#" class="tag">Jquery</a></li>
                                        <li><a href="#" class="tag">Wordpress</a></li>
                                        <li><a href="#" class="tag">Php</a></li>
                                        <li><a href="#" class="tag">Angular js</a></li>
                                        <li><a href="#" class="tag">React js</a></li>
                                        <li><a href="#" class="tag">Vue js</a></li>
                                        <li><a href="#" class="tag">Photoshop</a></li>
                                        <li><a href="#" class="tag">Ajax</a></li>
                                        <li><a href="#" class="tag">Json</a></li>
                                        <li><a href="#" class="tag">C</a></li>
                                        <li><a href="#" class="tag">C++</a></li>
                                        <li><a href="#" class="tag">Vb</a></li>
                                        <li><a href="#" class="tag">Vb.net</a></li>
                                        <li><a href="#" class="tag">Asp.net</a></li>
                                    </ul>
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
                                            <a href="https://mentoring.dreamguystech.com/cdn-cgi/l/email-protection" class="__cf_email__" data-cfemail="ec81898298839e85828bac89948d819c8089c28f8381">[email&#160;protected]</a>
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
</html>