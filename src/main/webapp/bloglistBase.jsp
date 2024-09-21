<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blog List</title>
    </head>
    <body>

        <div class="blog-container">
            <h1>Blog Posts</h1>

            <!-- Form lọc danh mục -->
            <form action="bloglist" method="get">
                <h3>Select Categories</h3>
                <c:forEach var="category" items="${categories}">
                    <input type="checkbox" name="categories" value="${category.id}" 
                           <c:if test="${fn:contains(selectedCategories, category.id)}">checked</c:if> />
                    <label>${category.name}</label><br>
                </c:forEach>
                <button type="submit">Filter</button>
            </form>

            <!-- Danh sách bài viết -->
            <c:forEach var="post" items="${blogPosts}">
                <div class="blog-post">
                    <h2>${post.title}</h2>
                    <img src="${post.thumbnailUrl}" alt="${post.title}" style="max-width:100%;height:auto;">
                    <p>${post.content}</p>
                    <p><strong>Category: </strong>${post.categoryName}</p> <!-- Hiển thị tên danh mục -->
                    <p><small>Posted on: ${post.createdAt}</small></p>
                </div>
            </c:forEach>

            <!-- Phân trang -->
            <div class="pagination">
                <!-- Nút trang trước -->
                <c:if test="${currentPage > 1}">
                    <a href="bloglist?page=${currentPage - 1}&categories=${fn:join(selectedCategories, ',')}">&laquo; Previous</a>
                </c:if>
                <c:if test="${currentPage == 1}">
                    <a class="disabled">&laquo; Previous</a>
                </c:if>

                <!-- Số trang -->
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <c:choose>
                        <c:when test="${i == currentPage}">
                            <a class="current">${i}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="bloglist?page=${i}&categories=${fn:join(selectedCategories, ',')}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <!-- Nút trang sau -->
                <c:if test="${currentPage < totalPages}">
                    <a href="bloglist?page=${currentPage + 1}&categories=${fn:join(selectedCategories, ',')}">Next &raquo;</a>
                </c:if>
                <c:if test="${currentPage == totalPages}">
                    <a class="disabled">Next &raquo;</a>
                </c:if>
            </div>
        </div>

    </body>
</html>
