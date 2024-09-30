<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Course Detail</title>

        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">

        <!-- CSS Files -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <!-- Include Header -->
        <jsp:include page="header.jsp" />

        <!-- Course Detail Section -->
        <section class="container mt-5">

            <c:if test="${not empty courseDetail}">
                <h2 class="text-primary">${courseDetail.sectionTitle}</h2> <!-- Hiển thị tiêu đề phần nội dung -->
                <p>${courseDetail.content}</p> <!-- Hiển thị nội dung -->

                <c:if test="${not empty courseDetail.imagePath}">
                    <img src="${courseDetail.imagePath}" alt="${courseDetail.sectionTitle}" class="img-fluid my-4" /> <!-- Hiển thị hình ảnh -->
                </c:if>

                <c:if test="${not empty courseDetail.videoUrl}">
                    <div class="video-wrapper my-4">
                        <video class="w-100" controls>
                            <source src="${courseDetail.videoUrl}" type="video/mp4">
                            Your browser does not support the video tag.
                        </video> <!-- Hiển thị video nếu có -->
                    </div>
                </c:if>
            </c:if>
        </section>

        <!-- Include Footer -->
        <jsp:include page="footer.jsp" />

        <!-- JS Files -->
        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>
