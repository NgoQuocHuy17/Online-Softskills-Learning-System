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

            <label>Category:</label>
            <input type="text" name="category" value="${course.category}" required><br>

            <label>Basic Package Price:</label>
            <input type="number" name="basicPackagePrice" value="${course.basicPackagePrice}" required><br>

            <label>Advanced Package Price:</label>
            <input type="number" name="advancedPackagePrice" value="${course.advancedPackagePrice}" required><br>

            <label>Status:</label>
            <select name="status">
                <option value="Published" ${course.status == 'Published' ? 'selected' : ''}>Published</option>
                <option value="Unpublished" ${course.status == 'Unpublished' ? 'selected' : ''}>Unpublished</option>
            </select><br>

            <label>Sponsored:</label>
            <input type="checkbox" name="isSponsored" ${course.sponsored ? 'checked="checked"' : ''}><br>


            <h3>Media</h3>
            <table border="1">
                <thead>
                    <tr>
                        <th>Media Title</th>
                        <th>Media Type</th>
                        <th>File Name</th>
                        <th>Display Order</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="media" items="${mediaList}">
                        <tr>
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
                                <input type="number" name="displayOrder" value="${media.displayOrder}" min="0" required>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <!-- Nút thêm Media -->
            <a href="addMedia?courseId=${course.id}"><button type="button">Add Media</button></a>

            <h3>Content</h3>
            <table border="1">
                <thead>
                    <tr>
                        <th>Content</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="content" items="${contentList}">
                        <tr>
                            <td>
                                <input type="hidden" name="contentId" value="${content.id}">
                                <input type="text" name="contentText" value="${content.content}" required>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <!-- Nút thêm Content -->
            <a href="addContent?courseId=${course.id}"><button type="button">Add Content</button></a>

            <button type="submit">Save Changes</button>

        </form>




    </body>
</html>
