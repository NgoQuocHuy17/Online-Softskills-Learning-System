<%-- 
    Document   : blog-update
    Created on : Nov 1, 2024, 8:22:43 AM
    Author     : Minh
--%>

<%-- 
    Document   : blog-update
    Created on : Nov 1, 2024, 8:22:43 AM
    Author     : Minh
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update Blog Post</title>

        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>

        <jsp:include page="header.jsp"/>
        
        <div class="container mt-5">
            <h1 class="text-center mb-4">Update Blog Post</h1>

            <c:if test="${not empty post}">
                <form action="blog-update?id=${post.id}" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="postTitle">Title</label>
                        <input type="text" class="form-control" id="postTitle" name="title" value="${post.title}" required>
                    </div>
                    <div class="form-group">
                        <label for="postContent">Content</label>
                        <textarea class="form-control" id="postContent" name="content" rows="5" required>${post.content}</textarea>
                    </div>

                    <div class="form-group">
                        <label for="postCategory">Category</label>
                        <select class="form-control" id="postCategory" name="categoryId" required>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.id}" <c:if test="${category.id == post.categoryId}">selected</c:if>>${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="currentThumbnail">Current Thumbnail</label>
                        <div>
                            <img src="${post.thumbnailUrl}" alt="Current Thumbnail" class="img-thumbnail mb-2" style="max-height: 200px;">
                        </div>

                        <label for="existingThumbnail">Choose Existing Thumbnail</label>
                        <select class="form-control" id="existingThumbnail" name="existingThumbnail">
                            <option value="">Select an existing thumbnail</option>
                            <c:forEach var="thumbnail" items="${thumbnails}">
                                <option value="${thumbnail}" <c:if test="${thumbnail == post.thumbnailUrl}">selected</c:if>>${thumbnail}</option>
                            </c:forEach>
                        </select>
                        <small class="form-text text-muted">You can choose an existing thumbnail or upload a new one.</small>

                        <label for="postThumbnailUpload">Upload New Thumbnail (optional)</label>
                        <input type="file" class="form-control" id="postThumbnailUpload" name="thumbnail" accept="image/*">
                    </div>

                    <div class="form-group">
                        <label>Status</label><br>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" id="statusActive" name="status" value="active" <c:if test="${post.status == 'Active'}">checked</c:if> required>
                            <label class="form-check-label" for="statusActive">Active</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" id="statusInactive" name="status" value="inactive" <c:if test="${post.status == 'Inactive'}">checked</c:if> required>
                            <label class="form-check-label" for="statusInactive">Inactive</label>
                        </div>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Update Post</button>
                        <a href="blog-list?page=${page}" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </c:if>

            <c:if test="${empty post}">
                <div class="text-center">
                    <h2>Blog post not found.</h2>
                </div>
            </c:if>
        </div>

        <jsp:include page="footer.jsp"/>

        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
