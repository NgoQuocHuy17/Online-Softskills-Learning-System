<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Media</title>
    </head>
    <body>
        <h1>Add Media to Course: ${course.title}</h1>

        <!-- Form upload file, cần enctype="multipart/form-data" -->
        <form action="addMedia" method="post" enctype="multipart/form-data">
            <!-- Trường ẩn để gửi courseId -->
            <input type="hidden" name="courseId" value="${course.id}">

            <label>Media Type:</label>
            <select name="mediaType" required>
                <option value="image">Image</option>
                <option value="video">Video</option>
            </select><br>

            <label>File:</label>
            <input type="file" name="file" required><br>

            <label>Title:</label>
            <input type="text" name="title" required><br>

            <label>Display Order:</label>
            <input type="number" name="displayOrder" required><br>

            <button type="submit">Add Media</button>
        </form>

        <!-- Nút quay lại trang chi tiết khóa học -->
        <a href="subjectDetail?courseId=${course.id}"><button type="button">Back to Course</button></a>
    </body>
</html>
