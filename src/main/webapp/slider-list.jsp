<%-- 
    Document   : slider-list
    Created on : Oct 11, 2024, 12:29:29?PM
    Author     : Minh
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Slider" %>
<%@ page import="java.util.List" %>
<%
    List<Slider> sliders = (List<Slider>) request.getAttribute("sliders");
    int totalSliders = (Integer) request.getAttribute("totalSliders");
    int currentPage = (Integer) request.getAttribute("currentPage");
    String statusFilter = request.getParameter("status");
    String searchQuery = request.getParameter("searchQuery");
    int slidersPerPage = 5; // Adjust as necessary
    int totalPages = (int) Math.ceil((double) totalSliders / slidersPerPage);
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Slider List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <%@ include file="header.jsp"%>
        <div class="main-wrapper">
            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-md-12 col-12">
                            <nav aria-label="breadcrumb" class="page-breadcrumb">
                                <!--<ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Slider List</li>
                                </ol>-->
                            </nav>
                            <h2 class="breadcrumb-title">Slider List</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <h5>Sliders</h5>

                            <!-- Filter Form -->
                            <form method="GET" action="SliderListController">
                                <input type="text" name="searchQuery" placeholder="Search by title or backlink" value="${param.searchQuery}">
                                <select name="status">
                                    <option value="">All</option>
                                    <option value="active" <c:if test="${'active' == param.status}">selected</c:if>>Active</option>
                                    <option value="inactive" <c:if test="${'inactive' == param.status}">selected</c:if>>Inactive</option>
                                    </select>
                                    <input type="submit" value="Filter" class="btn btn-primary">
                                </form>

                                <!-- Sliders Table -->
                            <c:if test="${not empty sliders}">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Title</th>
                                            <th>Image</th>
                                            <th>Status</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="slider" items="${sliders}">
                                            <tr>
                                                <td>${slider.id}</td>
                                                <td>
                                                    <a href="sliderdetails?id=${slider.id}">${slider.title}</a>
                                                </td>
                                                <td><img src="${slider.imageUrl}" alt="${slider.title}" style="max-width: 100px;"></td>
                                                <td>${slider.status}</td>
                                                <td>
                                                    <a href="editSlider.jsp?id=${slider.id}" class="btn btn-warning">Edit</a>
                                                    <a href="SliderToggleServlet?id=${slider.id}&status=${slider.status == 'active' ? 'inactive' : 'active'}" class="btn btn-danger">Hide</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                            <c:if test="${empty sliders}">
                                <p>No sliders found.</p>
                            </c:if>

                            <!-- Pagination -->
                            <div class="pagination">
                                <c:if test="${currentPage > 1}">
                                    <a href="?currentPage=${currentPage - 1}&status=${statusFilter}&searchQuery=${searchQuery}">Previous</a>
                                </c:if>
                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <a href="?currentPage=${i}&status=${statusFilter}&searchQuery=${searchQuery}" class="<c:if test='${i == currentPage}'>active</c:if>">${i}</a>
                                </c:forEach>
                                <c:if test="${currentPage < totalPages}">
                                    <a href="?currentPage=${currentPage + 1}&status=${statusFilter}&searchQuery=${searchQuery}">Next</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="footer.jsp" %>
        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/plugins/slick/slick.js"></script>
        <script src="assets/plugins/aos/aos.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>

