<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blog List</title>

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

        <div class="container mt-5">
            <h1 class="text-center mb-4">Blog Posts</h1>

            <div class="mb-3">
                <button class="btn btn-primary" data-toggle="modal" data-target="#createPostModal">Create New Post</button>
            </div>

            <!-- Modal for Creating a Blog Post -->
            <div class="modal fade" id="createPostModal" tabindex="-1" role="dialog" aria-labelledby="createPostModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="createPostModalLabel">Create New Post</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form action="blog-create" method="post">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="postTitle">Title</label>
                                    <input type="text" class="form-control" id="postTitle" name="title" required>
                                </div>
                                <div class="form-group">
                                    <label for="postContent">Content</label>
                                    <textarea class="form-control" id="postContent" name="content" rows="3" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="postCategory">Category</label>
                                    <input type="text" class="form-control" id="postCategory" name="categoryName" required>
                                </div>
                                <div class="form-group">
                                    <label for="postThumbnail">Thumbnail URL</label>
                                    <input type="url" class="form-control" id="postThumbnail" name="thumbnailUrl" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Create Post</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Check if blog posts list is empty -->
            <c:choose>
                <c:when test="${not empty blogPosts}">
                    <div class="row">
                        <c:forEach var="post" items="${blogPosts}">
                            <div class="col-12 mb-4">
                                <div class="card">
                                    <a href="blog-details?id=${post.id}">
                                        <img src="${post.thumbnailUrl}" class="card-img-top" alt="${post.title}" style="max-height: 400px; object-fit: cover;">
                                    </a>
                                    <div class="card-body">
                                        <h2 class="card-title">
                                            <a href="blog-details?id=${post.id}">${post.title}</a>
                                        </h2>
                                        <p class="card-text">${post.content}</p>
                                        <p class="card-text"><strong>Category: </strong>${post.categoryName}</p>
                                        <p class="card-text"><small class="text-muted">Posted on: ${post.createdAt}</small></p>

                                        <!-- Buttons for updating and deleting -->
                                        <div>
                                            <a href="blog-update?id=${post.id}&page=${currentPage}" class="btn btn-warning">Update</a>
                                            <a href="blog-delete?id=${post.id}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this post?');">Delete</a>
                                            <c:choose>
                                                <c:when test="${post.status == 'Active'}">
                                                    <a href="blog-status?id=${post.id}&status=inactive&page=${currentPage}" class="btn btn-secondary">Deactivate</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="blog-status?id=${post.id}&status=active&page=${currentPage}" class="btn btn-success">Activate</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="row">
                        <nav aria-label="Page navigation">
                            <ul class="pagination justify-content-center">
                                <c:if test="${currentPage > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="blog-list?page=${currentPage - 1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo; Previous</span>
                                        </a>
                                    </li>
                                </c:if>

                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                                        <a class="page-link" href="blog-list?page=${i}">${i}</a>
                                    </li>
                                </c:forEach>

                                <c:if test="${currentPage < totalPages}">
                                    <li class="page-item">
                                        <a class="page-link" href="blog-list?page=${currentPage + 1}" aria-label="Next">
                                            <span aria-hidden="true">Next &raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="text-center">
                        <h2>No blog posts available at the moment.</h2>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <jsp:include page="footer.jsp"/>
        
        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/plugins/slick/slick.js"></script>
        <script src="assets/plugins/aos/aos.js"></script>
        <script src="assets/js/script.js"></script>   
    </body>
</html>