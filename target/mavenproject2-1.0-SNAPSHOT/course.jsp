<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Course List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">

        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">

        <!-- Bootstrap CSS -->
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
            <h1 class="text-center mb-4">Available Courses</h1>

            <!-- Form để chọn Category -->
            <form class="filter-category mb-4" method="get" action="course">
                <div class="form-group">
                    <label for="category">Filter by Category:</label>
                    <select name="category" id="category" class="form-control" onchange="this.form.submit()">
                        <option value="All" ${category == 'All' ? 'selected' : ''}>All</option>
                        <!-- Loop through categories -->
                        <c:forEach var="cat" items="${categories}">
                            <option value="${cat}" ${category == cat ? 'selected' : ''}>${cat}</option>
                        </c:forEach>
                    </select>
                    <noscript><input type="submit" value="Filter"></noscript>
                </div>
            </form>

            <!-- Course List -->
            <div class="row">
                <c:forEach var="course" items="${courses}">
                    <div class="col-md-12 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h2 class="card-title">${course.title}</h2>
                                <p class="card-text"><strong>Tagline:</strong> ${course.tagLine}</p>
                                <p class="card-text"><strong>Category:</strong> ${course.category}</p>
                                <p class="card-text"><strong>List Price:</strong> ${course.listPrice}</p>
                                <p class="card-text"><strong>Sale Price:</strong> ${course.salePrice}</p>
                                <p class="card-text"><strong>Status:</strong> ${course.status}</p>
                                <a href="course-detail?courseId=${course.id}" class="btn btn-primary">View Details</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Phân trang -->
            <div class="pagination mt-4">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <!-- Nút Previous -->
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="course?page=${currentPage - 1}&category=${category}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <!-- Page Numbers -->
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link" href="course?page=${i}&category=${category}">${i}</a>
                            </li>
                        </c:forEach>

                        <!-- Nút Next -->
                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="course?page=${currentPage + 1}&category=${category}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>

        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/plugins/slick/slick.js"></script>
        <script src="assets/plugins/aos/aos.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>
