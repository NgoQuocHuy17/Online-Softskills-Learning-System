<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Homepage</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">

        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">

        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="assets/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="assets/plugins/slick/slick.css">
        <link rel="stylesheet" href="assets/plugins/slick/slick-theme.css">
        <link rel="stylesheet" href="assets/plugins/aos/aos.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>

    <body>

        <jsp:include page="header.jsp"/>

        <div class="container sliders my-5">
            <h2 class="text-center mb-4">Sliders</h2>
            <c:choose>
                <c:when test="${not empty sliders}">
                    <div id="carouselSliders" class="carousel slide" data-bs-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <c:forEach var="slider" items="${sliders}" varStatus="status">
                                <li data-bs-target="#carouselSliders" data-bs-slide-to="${status.index}" class="${status.first ? 'active' : ''}"></li>
                            </c:forEach>
                        </ol>

                        <!-- Sliders Items -->
                        <div class="carousel-inner">
                            <c:forEach var="slider" items="${sliders}" varStatus="status">
                                <div class="carousel-item ${status.first ? 'active' : ''}">
                                    <a href="${slider.backlink}">
                                        <img src="${slider.imageUrl}" class="d-block w-100" alt="${slider.title}">
                                        <div class="carousel-caption d-none d-md-block">
                                            <h3>${slider.title}</h3>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>

                        <!-- Controls (Next & Prev) -->
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselSliders" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselSliders" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info" role="alert">
                        No sliders available at the moment.
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Hot Posts Section -->
        <div class="container hot-posts my-5">
            <h2 class="text-center mb-4">Hot Posts</h2>
            <c:choose>
                <c:when test="${not empty hotPosts}">
                    <div class="row">
                        <c:forEach var="post" items="${hotPosts}">
                            <div class="col-md-4 mb-4">
                                <div class="card h-100">
                                    <img src="${post.thumbnailUrl}" class="card-img-top" alt="${post.title}">
                                    <div class="card-body">
                                        <h5 class="card-title">${post.title}</h5>
                                        <p class="card-text">Date: ${post.createdAt}</p>
                                        <a href="post-details.jsp?id=${post.id}" class="btn btn-primary">Read more</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info" role="alert">
                        No hot posts available at the moment.
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Feature Course Section -->
        <div class="container featured-courses my-5">
            <h2 class="text-center mb-4">Featured Courses</h2>
            <c:choose>
                <c:when test="${not empty featuredCourses}">
                    <div class="row">
                        <c:forEach var="course" items="${featuredCourses}">
                            <div class="col-md-4 mb-4">
                                <div class="card h-100">
                                    <img src="default-image.jpg" class="card-img-top" alt="${course.title}">
                                    <div class="card-body">
                                        <h5 class="card-title">${course.title}</h5>
                                        <p class="card-text">${course.description}</p>
                                        <a href="course-details.jsp?id=${course.id}" class="btn btn-primary">View Course</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info" role="alert">
                        No featured courses available at the moment.
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/plugins/slick/slick.js"></script>
        <script src="assets/plugins/aos/aos.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>
