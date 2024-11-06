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
            <div class="row">
                <h1 class="text-center mb-4">Subject List</h1>
                <form method="get" action="SubjectList" >
                    <div class="row">
                        <div class="col-md-3">

                            <h4>Choose Columns</h4>
                            <c:if test="${!showEmpty}">
                                <!-- Column Visibility Selection Checkboxes -->
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="showId" name="showId" value="true" ${showId == 'true' ? 'checked' : ''}>
                                    <label class="form-check-label" for="showId">Course ID</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="showTitle" name="showTitle" value="true" ${showTitle == 'true' ? 'checked' : ''}>
                                    <label class="form-check-label" for="showTitle">Course Title</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="showCategory" name="showCategory" value="true" ${showCategory == 'true' ? 'checked' : ''}>
                                    <label class="form-check-label" for="showCategory">Category</label>
                                </div>
                                <c:if test="${loggedInUser.role == 'Admin'}">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="showOwner" name="showOwner" value="true" ${showOwner == 'true' ? 'checked' : ''}>
                                        <label class="form-check-label" for="showOwner">Owner ID</label>
                                    </div>
                                </c:if>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="showStatus" name="showStatus" value="true" ${showStatus == 'true' ? 'checked' : ''}>
                                    <label class="form-check-label" for="showStatus">Status</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="showTagLine" name="showTagLine" value="true" ${showTagLine == 'true' ? 'checked' : ''}>
                                    <label class="form-check-label" for="showTagLine">Tag Line</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="showDescription" name="showDescription" value="true" ${showDescription == 'true' ? 'checked' : ''}>
                                    <label class="form-check-label" for="showDescription">Description</label>
                                </div>

                                <label for="itemsPerPage" class="me-2">Items per page:</label>
                                <input type="number" name="itemsPerPage" id="itemsPerPage" class="form-control me-2" value="${itemsPerPage != null ? itemsPerPage : 10}" onchange="this.form.submit()"/>
                                <button type="submit" class="btn btn-primary ms-3">Apply Filters</button>
                            </c:if>  
                        </div>


                        <div class="col-md-9">
                            <!-- Search Box -->
                            <div class="input-group">
                                <input type="text" id="searchCourse" name="searchTitle" class="form-control" 
                                       placeholder="Search by course title..." value="${param.searchTitle}">
                                <button class="btn btn-primary" type="submit">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>

                            <!-- Filter by Status -->
                            <div class="form-group mt-3">
                                <label for="status">Filter by Status:</label>
                                <select name="status" id="status" class="form-control" onchange="this.form.submit()">
                                    <option value="All" ${param.status == 'All' ? 'selected' : ''}>All</option>
                                    <c:forEach var="stat" items="${statuses}">
                                        <option value="${stat}" ${param.status == stat ? 'selected' : ''}>${stat}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div> 
                    </div> 
                </form>
                <!-- Message if no courses are available -->
                <c:if test="${hide}">
                    <div class="text-center mt-5">
                        <h1 style="opacity: 0.7; color: #6c757d;">No Courses Are Available</h1>
                    </div>
                </c:if>

                <!-- Course List -->
                <c:forEach var="course" items="${courses}">
                    <c:if test="${showCategory == 'true' or showLessonName == 'true' or showOwnerId == 'true' or showStatus == 'true' or showTagLine == 'true' or showDescription == 'true'}">
                        <div class="col-md-12 mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <c:if test="${showId == 'true'}">
                                        <h2 class="card-title">ID: ${course.id}</h2>
                                    </c:if>

                                    <c:if test="${showTitle == 'true'}">
                                        <h2 class="card-title">Name: ${course.title}</h2>
                                    </c:if>

                                    <c:if test="${showCategory == 'true'}">
                                        <p class="card-text"><strong>Category:</strong> ${course.category}</p>
                                    </c:if>

                                    <c:if test="${showOwner == 'true' && loggedInUser.role == 'Admin'}">
                                        <p class="card-text"><strong>Owner ID:</strong> ${course.ownerId}</p>
                                    </c:if>

                                    <c:if test="${showStatus == 'true'}">
                                        <p class="card-text"><strong>Status:</strong> ${course.status}</p>
                                    </c:if>

                                    <c:if test="${showTagLine == 'true'}">
                                        <p class="card-text"><strong>Tag Line:</strong> ${course.tagLine}</p>
                                    </c:if>

                                    <c:if test="${showDescription == 'true'}">
                                        <p class="card-text"><strong>Description:</strong> ${course.description}</p>
                                    </c:if>
                                </div>
                            </div>



                            <c:choose>
                                <c:when test="${loggedInUser.role == 'Admin'}">
                                    <a href="course-details?courseId=${course.id}" class="btn btn-primary">View</a>
                                    <a href="subjectDetail?action=edit&courseId=${course.id}" class="btn btn-outline-success">Edit</a>
                                </c:when>
                                <c:when test="${loggedInUser.role == 'Teacher'}">
                                    <c:choose>
                                        <c:when test="${loggedInUser.id == course.ownerId}">
                                            <a href="course-details?courseId=${course.id}" class="btn btn-primary">View</a>
                                            <a href="subjectDetail?action=edit&courseId=${course.id}" class="btn btn-outline-success">Edit</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="course-details?courseId=${course.id}" class="btn btn-primary">View</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <a href="course-details?courseId=${course.id}" class="btn btn-primary">View</a>
                                </c:otherwise>
                            </c:choose>


                        </div>
                    </c:if>
                </c:forEach>


                <div class="mb-4 text-right">
                    <a href="NewSubject.jsp" class="btn btn-success">Create New Course</a>
                </div>
                <!-- Pagination controls -->
                <c:if test="${!hide}"> 
                    <div class="pagination justify-content-center">
                        <ul class="pagination">
                            <!-- Previous button -->
                            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                <a class="page-link" href="SubjectList?page=${currentPage - 1}&searchTitle=${searchTitle}&status=${status}&itemsPerPage=${itemsPerPage}">Previous</a>
                            </li>

                            <!-- Page numbers -->
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="page-item ${currentPage == i ? 'active' : ''}">
                                    <a class="page-link" href="SubjectList?page=${i}&searchTitle=${searchTitle}&status=${status}&itemsPerPage=${itemsPerPage}">${i}</a>
                                </li>
                            </c:forEach>

                            <!-- Next button -->
                            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                <a class="page-link" href="SubjectList?page=${currentPage + 1}&searchTitle=${searchTitle}&status=${status}&itemsPerPage=${itemsPerPage}">Next</a>
                            </li>
                        </ul>
                    </div>
                </c:if>

            </div>
        </div>

        <jsp:include page="footer.jsp" />

        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/plugins/slick/slick.js"></script>
        <script src="assets/plugins/aos/aos.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>
