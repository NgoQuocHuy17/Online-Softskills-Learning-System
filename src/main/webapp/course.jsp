<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Course List</title>
        <style>
            /* Your existing CSS */
        </style>
    </head>
    <body>
        <h1>Available Courses</h1>

        <!-- Form để chọn Category -->
        <form class="filter-category" method="get" action="course">
            <label for="category">Filter by Category:</label>
            <select name="category" id="category" onchange="this.form.submit()">
                <option value="All" ${category == 'All' ? 'selected' : ''}>All</option>
                <!-- Check if categories are properly passed and loop over them -->
                <c:forEach var="cat" items="${categories}">
                    <option value="${cat}" ${category == cat ? 'selected' : ''}>${cat}</option>
                </c:forEach>
            </select>
            <noscript><input type="submit" value="Filter"></noscript>
        </form>

        <!-- Course List -->
        <div class="course-list">
            <c:forEach var="course" items="${courses}">
                <div class="course-item">
                    <h2>${course.title}</h2>
                    <p><strong>Tagline:</strong> ${course.tagLine}</p>
                    <p><strong>Category:</strong> ${course.category}</p>
                    <p><strong>List Price:</strong> ${course.listPrice}</p>
                    <p><strong>Sale Price:</strong> ${course.salePrice}</p>
                    <p><strong>Status:</strong> ${course.status}</p>
                    <a href="#" class="course-link">View Details</a>
                </div>
            </c:forEach>
        </div>


        <!-- Phân trang -->
        <div class="pagination">
            <!-- Nút Previous -->
            <c:if test="${currentPage > 1}">
                <a href="course?page=${currentPage - 1}&category=${category}">Previous</a>
            </c:if>

            <!-- Nút Next -->
            <c:if test="${currentPage < totalPages}">
                <a href="course?page=${currentPage + 1}&category=${category}">Next</a>
            </c:if>
        </div>
    </body>
</html>
