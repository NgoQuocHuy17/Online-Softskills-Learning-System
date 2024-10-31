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
            <h1 class="text-center mb-4">Subject List</h1>

            <!-- Create New Button -->
            <div class="mb-4 text-right">
                <a href="NewSubject.jsp" class="btn btn-success">Create New Course</a>
            </div>

            <!-- Search Box -->
            <form method="get" action="SubjectList" class="mb-4">
                <div class="input-group">
                    <input type="text" id="searchCourse" name="searchTitle" class="form-control" 
                           placeholder="Search by course title..." value="${param.searchTitle}">
                    <button class="btn btn-primary" type="submit">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </form>

            <!-- Form to filter by Status -->
            <form class="filter-status mb-4" method="get" action="SubjectList">
                <div class="form-group">
                    <label for="status">Filter by Status:</label>
                    <select name="status" id="status" class="form-control" onchange="this.form.submit()">
                        <option value="All" ${param.status == 'All' ? 'selected' : ''}>All</option>
                        <c:forEach var="stat" items="${statuses}">
                            <option value="${stat}" ${param.status == stat ? 'selected' : ''}>${stat}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="searchTitle" value="${param.searchTitle}">
                </div>
            </form>

            <!-- Course List -->
            <c:forEach var="course" items="${courses}">
                <div class="col-md-12 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h2 class="card-title">
                                ID: ${course.id} - Name: ${course.title}
                            </h2>
                            <p class="card-text"><strong>Category:</strong> ${course.category}</p>
                            <p class="card-text"><strong>Owner:</strong> ${course.ownerId}</p>
                            <p class="card-text"><strong>Status:</strong> ${course.status}</p>

                            <!-- View and Edit buttons -->
                            <a href="course-detail?courseId=${course.id}" class="btn btn-primary">View</a>
                            <a href="subjectDetail?action=edit&courseId=${course.id}" class="btn btn-outline-success">Edit</a>

                        </div>
                    </div>
                </div>
            </c:forEach>


            <!-- Pagination controls -->
            <!-- Pagination -->
            <div class="pagination justify-content-center">
                <ul class="pagination">
                    <!-- Previous button -->
                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="SubjectList?page=${currentPage - 1}&searchTitle=${searchTitle}&status=${status}">Previous</a>
                    </li>

                    <!-- Page numbers -->
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                            <a class="page-link" href="SubjectList?page=${i}&searchTitle=${searchTitle}&status=${status}">${i}</a>
                        </li>
                    </c:forEach>

                    <!-- Next button -->
                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                        <a class="page-link" href="SubjectList?page=${currentPage + 1}&searchTitle=${searchTitle}&status=${status}">Next</a>
                    </li>
                </ul>
            </div>

            <jsp:include page="footer.jsp" />
        </div>

        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/plugins/slick/slick.js"></script>
        <script src="assets/plugins/aos/aos.js"></script>
        <script src="assets/js/script.js"></script>
    </body>

</html>
