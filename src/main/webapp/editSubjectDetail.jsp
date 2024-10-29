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
            <style>
                .media-cell {
                    padding: 10px;
                    text-align: center;
                    vertical-align: top;
                }
                .media-title {
                    margin-top: 5px;
                    font-weight: bold;
                }
            </style>

            <table border="1" width="100%">
                <thead>
                    <tr>
                        <th style="width: 70%;">Media</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="media" items="${mediaList}">
                        <tr>
                            <td class="media-cell">
                                <!-- Hiển thị hình ảnh hoặc video -->
                                <c:choose>
                                    <c:when test="${media.mediaType == 'image'}">
                                        <img src="media/${media.fileName}?type=image" alt="${media.title}" width="150" height="150"/>
                                    </c:when>
                                    <c:when test="${media.mediaType == 'video'}">
                                        <video width="150" height="150" controls>
                                            <source src="media/${media.fileName}?type=video" type="video/mp4">
                                            Your browser does not support the video tag.
                                        </video>
                                    </c:when>
                                    <c:otherwise>
                                        <span>Unknown Media Type</span>
                                    </c:otherwise>
                                </c:choose>
                                <!-- Hiển thị tiêu đề dưới media -->
                                <div class="media-title">${media.title}</div>
                            </td>
                            <td class="media-cell">
                                <!-- Nút Edit và Remove -->
                                <a href="editMedia?mediaId=${media.id}&courseId=${course.id}">
                                    <button type="button">Edit</button>
                                </a>
                                <a href="deleteMedia?mediaId=${media.id}&courseId=${course.id}" 
                                   onclick="return confirm('Are you sure you want to delete this media?');">
                                    <button type="button">Remove</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Nút thêm Media -->
            <a href="addMedia?courseId=${course.id}"><button type="button">Add Media</button></a>

            <h3>Content</h3>
            <table border="1" width="100%">
                <thead>
                    <tr>
                        <th>Content</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="content" items="${contentList}">
                        <tr>
                            <td>
                                <!-- Textarea cho phép sửa ngay lập tức -->
                                <input type="hidden" name="contentId" value="${content.id}">
                                <textarea name="content" style="width: 100%; height: 100px;">${content.content}</textarea>
                            </td>
                            <td>
                                <!-- Nút Remove -->
                                <a href="deleteContent?contentId=${content.id}&courseId=${course.id}" 
                                   onclick="return confirm('Are you sure you want to delete this content?');">
                                    <button type="button">Remove</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Nút Lưu thay đổi -->
            <button type="submit">Save Changes</button>
        </form>

    </body>
</html>
