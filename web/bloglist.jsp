<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blog List</title>

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

        <div class="container mt-5">
            <h1 class="text-center mb-4">Blog Posts</h1>

            <div class="row">
                <!-- Vòng lặp qua danh sách blog -->
                <c:forEach var="post" items="${blogPosts}">
                    <div class="col-12 mb-4">
                        <div class="card">
                            <!-- Make the image clickable as well, if desired -->
                            <a href="blog-details.jsp?id=${post.id}">
                                <img src="${post.thumbnailUrl}" class="card-img-top" alt="${post.title}" style="max-height: 400px; object-fit: cover;">
                            </a>
                            <div class="card-body">
                                <!-- Wrap the blog title in a link to blog-details.jsp -->
                                <h2 class="card-title">
                                    <a href="blog-details.jsp?id=${post.id}">${post.title}</a>
                                </h2>
                                <p class="card-text">${post.content}</p>
                                <p class="card-text"><strong>Category: </strong>${post.categoryName}</p>
                                <p class="card-text"><small class="text-muted">Posted on: ${post.createdAt}</small></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Phân trang -->
            <div class="row">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <!-- Nút trang trước -->
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="bloglist?page=${currentPage - 1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo; Previous</span>
                                </a>
                            </li>
                        </c:if>

                        <!-- Số trang -->
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link" href="bloglist?page=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <!-- Nút trang sau -->
                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="bloglist?page=${currentPage + 1}" aria-label="Next">
                                    <span aria-hidden="true">Next &raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>

        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/plugins/slick/slick.js"></script>
        <script src="assets/plugins/aos/aos.js"></script>
        <script src="assets/js/script.js"></script>   
    </body>
</html>
