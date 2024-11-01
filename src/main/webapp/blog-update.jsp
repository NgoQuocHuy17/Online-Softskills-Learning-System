<%-- 
    Document   : blog-update
    Created on : Nov 1, 2024, 8:22:43 AM
    Author     : Minh
--%>
<%-- 
    Document   : blog-create
    Created on : Nov 1, 2024, 8:22:43 AM
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
        <title>Create New Blog Post</title>

        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <style>
            .thumbnail-selection {
                display: flex;
                flex-wrap: wrap;
                gap: 10px;
            }
            .thumbnail-option {
                border: 1px solid #ccc;
                border-radius: 5px;
                overflow: hidden;
                width: 100px;
                cursor: pointer;
            }
            .thumbnail-option img {
                width: 100%;
                height: auto;
            }
            .selected {
                border-color: #007bff;
            }
        </style>
    </head>
    <body>

        <jsp:include page="header.jsp"/>

        <div class="container mt-5">
            <h1 class="text-center mb-4">Create New Blog Post</h1>

            <form action="blog-create" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="postTitle">Title</label>
                    <input type="text" class="form-control" id="postTitle" name="title" required>
                </div>
                <div class="form-group">
                    <label for="postContent">Content</label>
                    <textarea class="form-control" id="postContent" name="content" rows="5" required></textarea>
                </div>

                <div class="form-group">
                    <label for="postCategory">Category</label>
                    <select class="form-control" id="postCategory" name="categoryId" required>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="postAuthor">Author</label>
                    <select class="form-control" id="postAuthor" name="authorId" required>
                        <option value="">Select an Author</option>
                        <c:forEach var="author" items="${authors}">
                            <option value="${author.id}">${author.fullName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="existingThumbnail">Choose Existing Thumbnail</label>
                    <div class="thumbnail-selection" id="thumbnailSelection">
                        <c:forEach var="thumbnail" items="${thumbnails}">
                            <div class="thumbnail-option" onclick="selectThumbnail('${thumbnail}')">
                                <img src="${pageContext.request.contextPath}/assets/img/blog/${thumbnail}" alt="${thumbnail}">
                            </div>
                        </c:forEach>
                    </div>
                    <input type="hidden" name="existingThumbnail" id="selectedThumbnail" value="">
                    <small class="form-text text-muted">You can choose an existing thumbnail or upload a new one.</small>

                    <label for="postThumbnailUpload">Upload New Thumbnail (optional)</label>
                    <input type="file" class="form-control" id="postThumbnailUpload" name="thumbnail" accept="image/*">
                </div>

                <div class="form-group">
                    <label>Status</label><br>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" id="statusActive" name="status" value="active" required>
                        <label class="form-check-label" for="statusActive">Active</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" id="statusInactive" name="status" value="inactive" required>
                        <label class="form-check-label" for="statusInactive">Inactive</label>
                    </div>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Create Post</button>
                    <a href="blog-list" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>

        <jsp:include page="footer.jsp"/>

        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script>
                                function selectThumbnail(thumbnail) {
                                    document.getElementById('selectedThumbnail').value = thumbnail;
                                    const options = document.querySelectorAll('.thumbnail-option');
                                    options.forEach(option => option.classList.remove('selected'));
                                    event.currentTarget.classList.add('selected');
                                }
        </script>
    </body>
</html>
