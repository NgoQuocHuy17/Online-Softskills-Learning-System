<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Media</title>
</head>
<body>
    <h1>Add Media</h1>
    <form action="subjectDetail" method="post">
        <input type="hidden" name="action" value="addMedia">
        <input type="hidden" name="courseId" value="${courseId}">

        <label>Media Title:</label>
        <input type="text" name="mediaTitle" required><br>

        <label>Media Type:</label>
        <input type="text" name="mediaType" required><br>

        <label>File Name:</label>
        <input type="text" name="fileName" required><br>

        <label>Display Order:</label>
        <input type="number" name="displayOrder" min="0" required><br>

        <button type="submit">Add Media</button>
    </form>
</body>
</html>
