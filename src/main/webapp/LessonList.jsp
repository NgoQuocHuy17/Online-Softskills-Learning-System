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
            <div class="row">
                <form action="LessonListController" method="get">
                    <c:if test="${!ListEmpty}">
                        <div class="row">
                            <div class="col-md-3">

                                <input type="hidden" name="courseId" value="${courseId}"/>
                                <input type="hidden" name="page" value="${currentPage}"/>
                                <!-- Sidebar for column visibility selection on the left side -->


                                <h4>Choose Columns</h4>
                                
                                    <!-- Column Visibility Selection Checkboxes -->
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="showLessonNumber" name="showLessonNumber" value="true" ${showLessonNumber == 'true' ? 'checked' : ''}>
                                        <label class="form-check-label" for="showLessonNumber">Lesson Number</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="showLessonName" name="showLessonName" value="true" ${showLessonName == 'true' ? 'checked' : ''}>
                                        <label class="form-check-label" for="showLessonName">Lesson Name</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="showDescription" name="showDescription" value="true" ${showDescription == 'true' ? 'checked' : ''}>
                                        <label class="form-check-label" for="showDescription">Description</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="showStatus" name="showStatus" value="true" ${showStatus == 'true' ? 'checked' : ''}>
                                        <label class="form-check-label" for="showStatus">Status</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="showActions" name="showActions" value="true" ${showActions == 'true' ? 'checked' : ''}>
                                        <label class="form-check-label" for="showActions">Actions</label>
                                    </div>
                                </div>
                           


                            <!-- Main content area for lesson list filters -->
                            <div class="col-md-9">
                                <!-- Items per page, Status filter, and Submit button in one form -->
                                <div class="d-flex flex-wrap  mb-4">
                                    <!-- Items per page selection -->
                                    <label for="itemsPerPage" class="me-2">Items per page:</label>
                                    <input type="number" name="itemsPerPage" id="itemsPerPage" class="form-control me-2" value="${itemsPerPage != null ? itemsPerPage : 10}" onchange="this.form.submit()"/>



                                    <!-- Status Filter Dropdown -->
                                    <label for="status" class="ms-3">Filter by Status:</label>
                                    <select name="status" class="form-select ms-2" onchange="this.form.submit()">
                                        <option value="All">All</option>
                                        <option value="Active" ${param.status == 'Active' ? 'selected' : ''}>Active</option>
                                        <option value="Inactive" ${param.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
                                    </select>

                                    <!-- Hidden Inputs for Additional Data -->

                                    <button type="submit" class="btn btn-primary ms-3">Apply Filters</button>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </form>        
                <table class="table table-striped table-bordered mt-3">
                    <thead>
                        <tr>
                            <c:if test="${showLessonNumber}">
                                <th>Lesson Number</th>
                                </c:if>
                                <c:if test="${showLessonName}">
                                <th>Lesson Name</th>
                                </c:if>
                                <c:if test="${showDescription}">
                                <th>Description</th>
                                </c:if>
                                <c:if test="${showStatus}">
                                <th>Status</th>
                                </c:if>
                                <c:if test="${showActions}">
                                <th>Actions</th>
                                </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- data part -->
                        <c:set var="lessonsPerPage" value="${itemsPerPage != null ? itemsPerPage : 10}" /> <!-- User selected value or default to 10 -->
                        <c:set var="currentPage" value="${currentPage}" />

                        <c:forEach var="lesson" items="${lessons}" varStatus="status">
                            <tr>
                                <c:if test="${showLessonNumber}">
                                    <td>${(currentPage - 1) * lessonsPerPage + status.index + 1}</td> <!-- Calculate the lesson number -->
                                </c:if>

                                <c:if test="${showLessonName}">
                                    <td>${lesson.title}</td>
                                </c:if>
                                <c:if test="${showDescription}">
                                    <td>${lesson.description}</td>
                                </c:if>
                                <c:if test="${showStatus}">
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
                                </c:if>
                                <c:if test="${showActions}">
                                    <td>
                                        <!-- Activate/Deactivate Button -->
                                        <form action="LessonListController" method="post" class="d-inline">
                                            <input type="hidden" name="courseId" value="${courseId}"/>
                                            <input type="hidden" name="lessonId" value="${lesson.id}">
                                            <input type="hidden" name="page" value="${currentPage}"/>
                                            <input type="hidden" name="status" value="${param.status}"/>
                                            <input type="hidden" name="itemsPerPage" value="${itemsPerPage}"/>
                                            <input type="hidden" name="toggleStatus" value="${lesson.status ? 'deactivate' : 'activate'}"> 

                                            <input type="hidden" name="showLessonNumber" value="${showLessonNumber}"/>
                                            <input type="hidden" name="showLessonName" value="${showLessonName}"/>
                                            <input type="hidden" name="showDescription" value="${showDescription}"/>
                                            <input type="hidden" name="showStatus" value="${showStatus}"/>
                                            <input type="hidden" name="showActions" value="${showActions}"/>
                                            <button type="submit" class="btnbtn-sm">
                                                <c:choose>
                                                    <c:when test="${lesson.status == true}">Deactivate</c:when>
                                                    <c:otherwise>Activate</c:otherwise>
                                                </c:choose>
                                            </button>
                                        </form>

                                        <!-- Edit Lesson Button -->
                                        <a href="LessonDetailController?lessonID=${lesson.id}" class="btn btn-primary btn-sm">Edit</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Pagination controls -->
        <nav>
            <ul class="pagination justify-content-center">
                <c:forEach var="page" begin="1" end="${totalPages}">
                    <li class="page-item ${page == currentPage ? 'active' : ''}">
                        <a class="page-link" href="?page=${page}&courseId=${courseId}&itemsPerPage=${itemsPerPage}&status=${status}
                           &showLessonNumber=${showLessonNumber}&showLessonName=${showLessonName}&showDescription=${showDescription}
                           &showStatus=${showStatus}&showActions=${showActions}">
                            ${page}
                        </a>
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
