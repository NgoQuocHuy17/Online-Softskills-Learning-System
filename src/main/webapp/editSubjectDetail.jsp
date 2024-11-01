<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Subject Detail</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
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

        <div class="container mt-5 p-4 bg-white rounded shadow">
            <h1 class="mb-4 text-center text-primary">Edit Subject Detail</h1>
            <form action="subjectDetail" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="courseId" value="${course.id}">

                <div class="mb-3">
                    <label class="form-label">Course Title:</label>
                    <input type="text" name="title" class="form-control" value="${course.title}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Description:</label>
                    <textarea name="description" class="form-control">${course.description}</textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label">Category:</label>
                    <input type="text" name="category" class="form-control" value="${course.category}" required>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Basic Package Price:</label>
                        <input type="number" name="basicPackagePrice" class="form-control" value="${course.basicPackagePrice}" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Advanced Package Price:</label>
                        <input type="number" name="advancedPackagePrice" class="form-control" value="${course.advancedPackagePrice}" required>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Status:</label>
                    <select name="status" class="form-select">
                        <option value="Published" ${course.status == 'Published' ? 'selected' : ''}>Published</option>
                        <option value="Draft" ${course.status == 'Draft' ? 'selected' : ''}>Draft</option>
                    </select>
                </div>

                <div class="mb-3 form-check">
                    <input type="checkbox" name="isSponsored" class="form-check-input" ${course.sponsored ? 'checked' : ''}>
                    <label class="form-check-label">Sponsored</label>
                </div>

                <div class="mb-4">
                    <h3 class="mb-3 text-secondary">Content</h3>
                    <textarea name="content" class="form-control" style="height: 200px;">${content.content}</textarea>
                </div>
                <!-- Media Section -->
                <h3 class="mt-4 mb-3 text-secondary">Media</h3>
                <table class="table table-bordered table-hover text-center align-middle">
                    <thead class="table-light">
                        <tr>
                            <th>Display Order</th>
                            <th>Media Type</th>
                            <th>Media</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="media" items="${mediaList}">
                            <tr>
                                <td>${media.displayOrder}</td>
                                <td>${media.mediaType}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${media.mediaType == 'image'}">
                                            <img src="assets/img/course/${media.fileName}?type=image" alt="${media.title}" class="img-thumbnail" style="width: 150px; height: 150px; object-fit: cover;">
                                        </c:when>
                                        <c:when test="${media.mediaType == 'video'}">
                                            <video class="img-thumbnail" style="width: 150px; height: 150px; object-fit: cover;" controls>
                                                <source src="assets/video/course/${media.fileName}?type=video" type="video/mp4">
                                                Your browser does not support the video tag.
                                            </video>
                                        </c:when>
                                        <c:otherwise>
                                            <span>Unknown Media Type</span>
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="mt-2 fw-bold">${media.title}</div>
                                </td>
                                <td>
                                    <form action="subjectDetail" method="post" style="display: inline;">
                                        <input type="hidden" name="action" value="moveUp">
                                        <input type="hidden" name="mediaId" value="${media.id}">
                                        <input type="hidden" name="courseId" value="${course.id}">
                                        <button type="submit" class="btn btn-link p-0" ${media.displayOrder <= 1 ? 'disabled' : ''}>▲</button>
                                    </form>
                                    <form action="subjectDetail" method="post" style="display: inline;">
                                        <input type="hidden" name="action" value="moveDown">
                                        <input type="hidden" name="mediaId" value="${media.id}">
                                        <input type="hidden" name="courseId" value="${course.id}">
                                        <button type="submit" class="btn btn-link p-0" ${media.displayOrder >= maxOrder ? 'disabled' : ''}>▼</button>
                                    </form>
                                    <a href="subjectDetail?action=remove&mediaId=${media.id}&courseId=${course.id}" 
                                       onclick="return confirm('Are you sure you want to delete this media?');" 
                                       class="btn btn-danger btn-sm">Remove</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <a href="addMedia?courseId=${course.id}" class="btn btn-outline-secondary mb-4">Add Media</a>

                <button type="submit" class="btn btn-success w-100">Save Changes</button>
            </form>
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
