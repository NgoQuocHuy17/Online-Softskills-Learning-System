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
                        <c:forEach var="cat" items="${categories}">
                            <option value="${cat}" ${category == cat ? 'selected' : ''}>${cat}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="searchTitle" value="${param.searchTitle}">
                    <noscript><input type="submit" value="Filter"></noscript>
                </div>
            </form>

            <!-- Form để chọn thuộc tính hiển thị -->
            <form method="get" action="course" class="mb-4" id="displayForm">
                <div class="form-group">
                    <label>Chọn thuộc tính hiển thị:</label><br>
                    <c:forEach items="${displayAttributes}" var="attr">
                        <input type="checkbox" name="${attr}" id="${attr}" 
                               ${displaySettings[attr] ? 'checked' : ''}> ${attr}<br>
                    </c:forEach>
                </div>
                <button type="button" id="showAll" class="btn btn-secondary mr-2">Hiển thị tất cả</button>
                <button type="button" id="hideAll" class="btn btn-secondary">Ẩn tất cả</button>
                <input type="hidden" name="searchTitle" value="${param.searchTitle}">
                <input type="hidden" name="category" value="${param.category}">
                <input type="hidden" name="page" value="${currentPage}">
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

                            <c:if test="${displaySettings.displayTagline}">
                                <p class="card-text"><strong>Tagline:</strong> ${course.tagLine}</p>
                            </c:if>

                            <c:if test="${displaySettings.displayCategory}">
                                <p class="card-text"><strong>Category:</strong> ${course.category}</p>
                            </c:if>

                            <c:if test="${displaySettings.displayBasicPrice}">
                                <p class="card-text"><strong>Basic Package Price:</strong> ${course.basicPackagePrice}</p>
                            </c:if>

                            <c:if test="${displaySettings.displayAdvancedPrice}">
                                <p class="card-text"><strong>Advanced Package Price:</strong> ${course.advancedPackagePrice}</p>
                            </c:if>

                            <c:if test="${displaySettings.displayUpdateAt}">
                                <p class="card-text">
                                    <strong>Update at:</strong> 
                                    <fmt:formatDate value="${course.updatedAt}" pattern="dd/MM/yyyy" />
                                </p>
                            </c:if>

                            <a href="course-detail?courseId=${course.id}" class="btn btn-primary">View Details</a>
                            <a href="/register?courseId=${course.id}" class="btn btn-success ml-2">Register</a>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <!-- Pagination -->
            <div class="pagination mt-4">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="course?page=${currentPage - 1}&category=${category}&searchTitle=${param.searchTitle}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link" href="course?page=${i}&category=${category}&searchTitle=${param.searchTitle}">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="course?page=${currentPage + 1}&category=${category}&searchTitle=${param.searchTitle}"
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
        <!-- Các script JS giữ nguyên -->

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

            document.querySelectorAll('#displayForm input[type=checkbox]').forEach(checkbox => {
                checkbox.addEventListener('change', function () {
                    document.getElementById('displayForm').submit();
                });
            });

            document.getElementById('showAll').addEventListener('click', function () {
                document.querySelectorAll('#displayForm input[type=checkbox]').forEach(cb => cb.checked = true);
                document.getElementById('displayForm').submit();
            });

            document.getElementById('hideAll').addEventListener('click', function () {
                document.querySelectorAll('#displayForm input[type=checkbox]').forEach(cb => cb.checked = false);
                document.getElementById('displayForm').submit();
            });
        </script>
    </body>
</html>
