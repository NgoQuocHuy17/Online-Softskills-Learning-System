<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
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
    <h2>Lessons List</h2>
    <table class="table table-striped table-bordered mt-3">
        <thead>
            <tr>
                <th>Lesson ID</th>
                <th>Lesson Name</th>
                <th>Description</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Example data loop for each lesson in the selected package -->
            <c:forEach var="lesson" items="${lessons}">
                <tr>
                    <td>${lesson.id}</td>
                    <td>${lesson.title}</td>
                    <td>${lesson.description}</td>
                    <td>
                        <span class="badge ${lesson.active ? 'badge-success' : 'badge-secondary'}">
                            ${lesson.active ? 'Active' : 'Inactive'}
                        </span>
                    </td>
                    <td>
                        <!-- Activate/Deactivate Button -->
                        <form action="ToggleLessonStatus" method="post" class="d-inline">
                            <input type="hidden" name="lessonId" value="${lesson.id}">
                            <button type="submit" class="btn ${lesson.active ? 'btn-warning' : 'btn-success'} btn-sm">
                                ${lesson.active ? 'Deactivate' : 'Activate'}
                            </button>
                        </form>

                        <!-- Edit Lesson Button -->
                        <a href="LessonDetails?lessonId=${lesson.id}" class="btn btn-primary btn-sm">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Button to add a new lesson -->
    <div class="text-end">
        <a href="LessonDetails" class="btn btn-success">Add New Lesson</a>
    </div>
</div>

<!-- JS dependencies -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/bootstrap-datetimepicker.min.js"></script>
</body>
</html>
