<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Content</title>
</head>
<body>
    <h1>Add Content</h1>
    <form action="subject-details" method="post">
        <input type="hidden" name="action" value="addContent">
        <input type="hidden" name="courseId" value="${courseId}">

        <label>Content Text:</label>
        <input type="text" name="contentText" required><br>

        <button type="submit">Add Content</button>
    </form>
</body>
</html>
