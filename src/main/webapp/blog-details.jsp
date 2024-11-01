<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Course Detail</title>

        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">

        <!-- CSS Files -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <style>
            .media-item {
                display: none;
            }
            button {
                position: absolute;
                top: 50%;
                transform: translateY(-50%);
                background-color: rgba(0, 0, 0, 0.5);
                color: white;
                border: none;
                padding: 10px;
                cursor: pointer;
            }
            #prevBtn {
                left: 10px;
            }
            #nextBtn {
                right: 10px;
            }
            /* Add more space below the package price list */
            .post-left ul li {
                margin-bottom: 20px; /* Increased spacing between list items */
            }

            /* Add more margin to the media slider */
            .media-slider {
                position: relative;
                width: 100%;
                margin-bottom: 40px; /* Increased space below the media slider */
            }

            /* Add more margin to separate course content */
            .course-content {
                padding: 10px;
                border: 1px solid #ddd;
                background-color: #f9f9f9;
                margin-top: 40px; /* Increased space between the media slider and course content */
            }
        </style>
    </head>
    <body>
        <!-- Include Header -->
        <jsp:include page="header.jsp" />
        <!-- Course Detail Section -->
        <div class="main-wrapper">
            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-8 col-md-12">
                            <div class="blog-view">
                                <div class="blog blog-single-post">
                                    <h3 class="blog-title">${courseDetail.title}</h3>
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
                                                <li>
                                                    <i class="far fa-calendar"></i>
                                                    <c:out value="${formattedCreatedAt}"/>
                                                </li>
                                                <li>
                                                    <i class="far fa-calendar"></i>
                                                    <c:out value="${formattedUpdatedAt}"/>
                                                </li>
                                                <li>
                                                    <i class="fa fa-tags"></i>
                                                    <c:out value="${courseDetail.category}"/>
                                                </li>
                                                <li>
                                                    <i class="fa fa-star"></i>
                                                    Sponsored: <c:out value="${courseDetail.sponsoredStatus}"/>
                                                </li>
                                                <li>
                                                    <i class="fa fa-info-circle"></i>
                                                    Status: <c:out value="${courseDetail.status}"/>
                                                </li>
                                                <li>
                                                    <i class="fa fa-comment"></i>
                                                    Tagline: <c:out value="${courseDetail.tagLine}"/>
                                                </li>
                                                <li>
                                                    <i class="fa fa-file-alt"></i>
                                                    Description: <c:out value="${courseDetail.description}"/>
                                                </li>
                                                <li>
                                                    <i class="fa fa-dollar-sign"></i>
                                                    Basic Package Price: <strong>$${courseDetail.basicPackagePrice}</strong>
                                                </li>
                                                <li>
                                                    <i class="fa fa-dollar-sign"></i>
                                                    Advanced Package Price: <strong>$${courseDetail.advancedPackagePrice}</strong>
                                                </li>
                                            </ul>
                                        </div>

                                        <!-- Media Slider -->
                                        <div class="media-slider">
                                            <div id="mediaContainer">
                                                <c:forEach var="courseMedia" items="${courseMedias}" varStatus="status">
                                                    <div class="media-item" style="display: ${status.index == 0 ? 'block' : 'none'};">
                                                        <c:if test="${courseMedia.mediaType == 'Image'}">
                                                            <img src="${courseMedia.fileName}" alt="${courseMedia.title}" class="img-fluid"/>
                                                        </c:if>
                                                        <c:if test="${courseMedia.mediaType == 'Video'}">
                                                            <video src="${courseMedia.fileName}" alt="${courseMedia.title}" controls="true" class="img-fluid"/>
                                                        </c:if>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <!-- Navigation Arrows -->
                                            <button id="prevBtn" onclick="showMedia(-1)">&#10094;</button>
                                            <button id="nextBtn" onclick="showMedia(1)">&#10095;</button>
                                        </div>

                                        <!-- Course Content -->
                                        <div class="course-content">
                                            <p>
                                                <c:out value="${courseContent.content}"/>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Sidebar Section -->
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
                                    <h4 class="card-title">Latest Courses</h4>
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
                                                    <c:out value="${formattedUpdatedAt}"/>
                                                </p>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="card category-widget">
                                <div class="card-header">
                                    <h4 class="card-title">Course Categories</h4>
                                </div>
                                <div class="card-body">
                                    <ul class="categories">
                                        <c:if test="${categories != null}">
                                            <c:forEach var="category" items="${categories}">
                                                <li><a href="#">HTML <span>(62)</span></a></li>
                                                </c:forEach>
                                            </c:if>
                                    </ul>
                                </div>
                            </div>
                            <div class="card tags-widget">
                                <div class="card-header">
                                    <h4 class="card-title">Tags</h4>
                                </div>
                                <div class="card-body">
                                    <ul class="tags">
                                        <c:if test="${tags != null}">
                                            <c:forEach var="tag" items="${tags}">
                                                <li><a href="#" class="tag">HTML</a></li>
                                                </c:forEach>
                                            </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Include Footer -->
        <jsp:include page="footer.jsp" />

        <script>
            let currentIndex = 0;
            const mediaItems = document.querySelectorAll('.media-item');

            function showMedia(direction) {
                mediaItems[currentIndex].style.display = 'none';
                currentIndex = (currentIndex + direction + mediaItems.length) % mediaItems.length;
                mediaItems[currentIndex].style.display = 'block';
            }

            // Initial display setup
            if (mediaItems.length > 0) {
                mediaItems[currentIndex].style.display = 'block';
            }
        </script>

        <!-- JS Files -->
        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/plugins/slick/slick.js"></script>
        <script src="assets/plugins/aos/aos.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>