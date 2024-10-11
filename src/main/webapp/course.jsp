<!-- File: course.jsp -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>

    <head>
        <meta charset="UTF-8">
        <title>Course List</title>
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
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    </head>

    <body>
        <jsp:include page="header.jsp" />

        <div class="container mt-5">
            <h1 class="text-center mb-4">Available Courses</h1>

            <!-- Search Box -->
            <form method="get" action="course" class="mb-4">
                <div class="input-group">
                    <input type="text" id="searchCourse" name="searchTitle" class="form-control" 
                           placeholder="Search by course title..." value="${param.searchTitle}">
                    <!-- Hidden input để giữ category hiện tại -->
                    <input type="hidden" name="category" value="${param.category}">

                    <button class="btn btn-primary" type="submit">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </form>

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
                    <!-- Hidden input để giữ searchTitle hiện tại -->
                    <input type="hidden" name="searchTitle" value="${param.searchTitle}">
                    <noscript><input type="submit" value="Filter"></noscript>
                </div>
            </form>

            <!-- Course List -->
            <c:forEach var="course" items="${courses}">
                <div class="col-md-12 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h2 class="card-title">
                                ${course.title}
                                <c:if test="${course.sponsored}">
                                    <span class="hot-icon" title="Hot Course">🔥</span>
                                </c:if>
                            </h2>
                            <p class="card-text"><strong>Tagline:</strong> ${course.tagLine}</p>
                            <p class="card-text"><strong>Category:</strong> ${course.category}</p>
                            <p class="card-text"><strong>Basic Package Price:</strong> ${course.basicPackagePrice}</p>
                            <p class="card-text"><strong>Advanced Package Price:</strong> ${course.advancedPackagePrice}</p>

                            <!-- Sử dụng fmt:formatDate để định dạng updatedAt thành dd/MM/yyyy -->
                            <p class="card-text">
                                <strong>Update at:</strong> 
                                <fmt:formatDate value="${course.updatedAt}" pattern="dd/MM/yyyy" />
                            </p>

                            <!-- Nút View Details -->
                            <a href="course-detail?courseId=${course.id}" class="btn btn-primary">View Details</a>

                            <!-- Nút Register thêm vào -->
                            <a href="CourseRegister?courseId=${course.id}" class="btn btn-success ml-2">Register</a>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <!-- Pagination -->
            <div class="pagination mt-4">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <!-- Previous Button -->
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="course?page=${currentPage - 1}&category=${category}"
                                   aria-label="Previous">
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

                        <!-- Next Button -->
                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="course?page=${currentPage + 1}&category=${category}"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>

        <jsp:include page="footer.jsp" />

        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/plugins/slick/slick.js"></script>
        <script src="assets/plugins/aos/aos.js"></script>
        <script src="assets/js/script.js"></script>

        <!-- Include jQuery and jQuery UI for Autocomplete -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
                        $(document).ready(function () {
                            $("#searchCourse").autocomplete({
                                source: function (request, response) {
                                    $.ajax({
                                        url: "searchAutoComplete",
                                        data: {term: request.term},
                                        success: function (data) {
                                            var titles = data.split(",");
                                            response(titles);
                                        }
                                    });
                                },
                                autoFocus: true
                            }).attr('autocomplete', 'off');
                        });
        </script>

    </body>

</html>