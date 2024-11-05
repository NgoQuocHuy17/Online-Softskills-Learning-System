<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Add Media</title>

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

        <!-- Nút quay lại trang chi tiết khóa học -->
        <div class="container my-4">
            <a href="subject-details?action=edit&courseId=${course.id}" class="btn btn-secondary mb-3">
                <i class="fas fa-arrow-left"></i> Back to Course
            </a>

            <h1 class="text-center mb-4">Add Media to Course: ${course.title}</h1>

            <!-- Hiển thị thông báo lỗi nếu có -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    ${error}
                </div>
            </c:if>

            <!-- Form upload file -->
            <div class="card shadow-sm p-4">
                <form action="add-media" method="post" enctype="multipart/form-data">
                    <!-- Trường ẩn để gửi courseId -->
                    <input type="hidden" name="courseId" value="${course.id}">

                    <div class="mb-3">
                        <label class="form-label">Media Type:</label>
                        <select name="mediaType" class="form-select" required>
                            <option value="image">Image</option>
                            <option value="video">Video</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">File:</label>
                        <input type="file" name="file" class="form-control" id="fileInput" required>
                    </div>

                    <!-- Preview -->
                    <div id="previewContainer" class="mb-3" style="display: none;">
                        <img id="previewImage" src="#" alt="Preview" class="img-fluid img-thumbnail d-none"/>
                        <video id="previewVideo" class="d-none" controls></video>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Title:</label>
                        <input type="text" name="title" class="form-control" required>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Add Media</button>
                </form>
            </div>
        </div>

        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/plugins/slick/slick.js"></script>
        <script src="assets/plugins/aos/aos.js"></script>
        <script src="assets/js/script.js"></script>

        <script>
            // Preview file
            document.getElementById('fileInput').addEventListener('change', function (event) {
                var file = event.target.files[0];
                if (!file)
                    return;

                var previewImage = document.getElementById('previewImage');
                var previewVideo = document.getElementById('previewVideo');
                var previewContainer = document.getElementById('previewContainer');

                previewContainer.style.display = 'block';

                if (file.type.startsWith('image')) {
                    previewImage.src = URL.createObjectURL(file);
                    previewImage.classList.remove('d-none');
                    previewVideo.classList.add('d-none');
                } else if (file.type.startsWith('video')) {
                    previewVideo.src = URL.createObjectURL(file);
                    previewVideo.classList.remove('d-none');
                    previewImage.classList.add('d-none');
                } else {
                    previewContainer.style.display = 'none';
                }
            });
        </script>
    </body>
</html>
