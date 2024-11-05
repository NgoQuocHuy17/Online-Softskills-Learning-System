<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Lessons List</title>
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
        <style>
            .badge-active {
                background-color: #28a745; /* Green for Active */
                color: white; /* Text color for Active */
            }
            .badge-inactive {
                background-color: #6c757d; /* Grey for Inactive */
                color: white; /* Text color for Inactive */
            }
        </style>
    </head>

    <body>
        <jsp:include page="header.jsp" />

        <div class="container mt-5">
            <h2>Lessons List</h2>

            <!-- Dropdown to select number of items per page -->
            <div class="mb-3">
                <form method="get" class="d-flex">
                    <label for="itemsPerPage" class="me-2">Items per page:</label>
                    <select name="itemsPerPage" id="itemsPerPage" class="form-select me-2" onchange="this.form.submit()">
                        <option value="3" ${itemsPerPage == 3 ? 'selected' : ''}>3</option>
                        <option value="5" ${itemsPerPage == 5 ? 'selected' : ''}>5</option>
                        <option value="10" ${itemsPerPage == 10 ? 'selected' : ''}>10</option>
                        <option value="20" ${itemsPerPage == 20 ? 'selected' : ''}>20</option>
                        <option value="50" ${itemsPerPage == 50 ? 'selected' : ''}>50</option>
                    </select>
                    <input type="hidden" name="courseId" value="${courseId}"/>
                    <input type="hidden" name="page" value="${currentPage}"/>
                    <input type="hidden" name="status" value="${status}"/>
                </form>
            </div>
            <form class="filter-status mb-4" method="get" action="LessonListController">
                <div class="form-group">
                    <label for="status">Filter by Status:</label>
                    <select name="status" class="form-control" onchange="this.form.submit()">
                        <option value="All" ${param.status == 'All' ? 'selected' : ''}>All</option>
                        <option value="Active" ${param.status == 'Active' ? 'selected' : ''}>Active</option>
                        <option value="Inactive" ${param.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
                    </select>
                    <input type="hidden" name="courseId" value="${param.courseId}">
                    <input type="hidden" name="searchTitle" value="${param.searchTitle}">
                </div>
            </form>

            <table class="table table-striped table-bordered mt-3">
                <thead>
                    <tr>
                        <th>Lesson Number</th>
                        <th>Lesson Name</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- data part -->
                    <c:set var="lessonsPerPage" value="${itemsPerPage != null ? itemsPerPage : 10}" /> <!-- User selected value or default to 10 -->
                    <c:set var="currentPage" value="${currentPage}" />

                    <c:forEach var="lesson" items="${lessons}" varStatus="status">
                        <tr>
                            <td>${(currentPage - 1) * lessonsPerPage + status.index + 1}</td> <!-- Calculate the lesson number -->
                            <td>${lesson.title}</td>
                            <td>${lesson.description}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${lesson.status == true}">
                                        <span class="badge badge-active">Active</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge badge-inactive">Inactive</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <!-- Activate/Deactivate Button -->
                                <form action="LessonListController" method="post" class="d-inline">
                                    <input type="hidden" name="courseId" value="${courseId}"/>
                                    <input type="hidden" name="lessonId" value="${lesson.id}">
                                    <input type="hidden" name="page" value="${currentPage}"/>
                                    <input type="hidden" name="status" value="${param.status}"/>
                                    <input type="hidden" name="itemsPerPage" value="${itemsPerPage}"/>
                                    <input type="hidden" name="toggleStatus" value="${lesson.status ? 'deactivate' : 'activate'}"> 
                                    <button type="submit" class="btn ${lesson.status ? 'btn-warning' : 'btn-success'} btn-sm">
                                        <c:choose>
                                            <c:when test="${lesson.status == true}">Deactivate</c:when>
                                            <c:otherwise>Activate</c:otherwise>
                                        </c:choose>
                                    </button>
                                </form>

                                <!-- Edit Lesson Button -->
                                <a href="LessonDetailController?lessonID=${lesson.id}" class="btn btn-primary btn-sm">Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Pagination controls -->
            <nav>
                <ul class="pagination justify-content-center">
                    <c:forEach var="page" begin="1" end="${totalPages}">
                        <li class="page-item ${page == currentPage ? 'active' : ''}">
                            <a class="page-link" href="?page=${page}&courseId=${courseId}&itemsPerPage=${lessonsPerPage}&status=${status}">${page}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>


            <!-- Button to add a new lesson -->
            <div class="text-end">
                <a href="LessonDetails" class="btn btn-success">Add New Lesson</a>
            </div>
        </div>
        <jsp:include page="footer.jsp" />

        <!-- JS dependencies -->
        <script src="js/jquery.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/bootstrap-datetimepicker.min.js"></script>
        
        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/plugins/slick/slick.js"></script>
        <script src="assets/plugins/aos/aos.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>
