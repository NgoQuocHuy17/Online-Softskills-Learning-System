<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Homepage</title>

    </head>

    <body>


        <!-- Main content with Sidebar -->
        <div class="container">
            <jsp:include page="header.jsp"/>

            <!-- Nội dung chính -->
            <div class="main-content">
                <!-- Sliders Section -->
                <div class="sliders">
                    <h2>Sliders</h2>
                    <c:forEach var="slider" items="${sliders}">
                        <div class="slider-item">
                            <a href="${slider.backlink}">
                                <img src="${slider.imageUrl}" alt="${slider.title}">
                                <h3>${slider.title}</h3>
                            </a>
                        </div>
                    </c:forEach>
                </div>

                <!-- Hot Posts Section -->
                <div class="hot-posts">
                    <h2>Hot Posts</h2>
                    <c:forEach var="post" items="${hotPosts}">
                        <div class="post-item">
                            <a href="post-details.jsp?id=${post.id}">
                                <img src="${post.thumbnailUrl}" alt="${post.title}">
                                <h3>${post.title}</h3>
                                <p>Date: ${post.createdAt}</p>
                            </a>
                        </div>
                    </c:forEach>
                </div>

                <!-- Feature Course Section -->
                <div class="featured-courses">
                    <h2>Featured Courses</h2>
                    <c:forEach var="course" items="${featuredCourses}">
                        <div class="course-item">
                            <a href="course-details.jsp?id=${course.id}">
                                <img src="default-image.jpg" alt="${course.title}">
                                <h3>${course.title}</h3>
                                <p>${course.description}</p>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>

        </div>

    </body>
</html>
