<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Subject Detail</title>
    </head>
    <body>
        <h1>Edit Subject Detail</h1>
        <form action="subjectDetail" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="courseId" value="${course.id}">

            <label>Course Title:</label>
            <input type="text" name="title" value="${course.title}" required><br>

            <label>Description:</label>
            <textarea name="description">${course.description}</textarea><br>

            <h3>Media</h3>

            <!-- Bảng hiển thị media -->
            <table border="1">
                <thead>
                    <tr>
                        <th>Media Title</th>
                        <th>Media Type</th>
                        <th>File Name</th>
                        <th>Content</th>
                        <th>Is Thumbnail</th>
                        <th>Display Order</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Lặp qua danh sách media -->
                    <c:forEach var="media" items="${mediaList}">
                        <tr>
                            <!-- Các trường chỉnh sửa của media -->
                            <td>
                                <input type="hidden" name="mediaId" value="${media.id}">
                                <input type="text" name="mediaTitle" value="${media.title}" required>
                            </td>
                            <td>
                                <input type="text" name="mediaType" value="${media.mediaType}" required>
                            </td>
                            <td>
                                <input type="text" name="fileName" value="${media.fileName}" required>
                            </td>
                            <td>
                                <textarea name="mediaContent">${media.content}</textarea>
                            </td>
                            <td>
                                <input type="checkbox" name="isThumbnail" ${media.isThumbnail ? 'checked' : ''}>
                            </td>
                            <td>
                                <input type="number" name="displayOrder" value="${media.displayOrder}" required>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <button type="submit">Save Changes</button>
        </form>


    </body>
</html>
