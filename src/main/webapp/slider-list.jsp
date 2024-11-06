<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div class="main-wrapper">
            <jsp:include page="header.jsp"/>
            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-md-12 col-12">
                            <form class="search-filter-form" action="slider-list" method="get">
                                <div class="input-group">

                                    <!-- Search Input -->
                                    <input type="text" name="searchTerm" placeholder="Search slider..." class="form-control" value="${param.searchTerm}">

                                    <!--Filter -->
                                    <select name="status" class="form-control">
                                        <option value="">All Statuses</option>
                                        <option value="Active" ${param.status == 'Active' ? 'selected' : ''}>Active</option>
                                        <option value="Inactive" ${param.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
                                    </select>
                                    <!-- Page Size -->
                                    <input type="number" name="pageSize" placeholder="Page Size" class="form-control" min="1" value="${param.pageSize}"/>

                                    <!-- Submit -->
                                    <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i> Search & Filter</button>
                                </div>

                                <!-- ShowField Checkboxes -->
                                <div class="form-group mt-3" style="display: flex; flex-wrap: wrap; gap: 15px; align-items: center;">
                                    <label class="mr-2">Show Columns: </label>
                                    <label><input type="checkbox" name="showId" value="true" ${param.showId != null || empty param.showId ? 'checked' : ''}>Id</label>
                                    <label><input type="checkbox" name="showTitle" value="true" ${param.showTitle != null || empty param.showTitle ? 'checked' : ''}>Title</label>
                                    <label><input type="checkbox" name="showImage" value="true" ${param.showImage != null || empty param.showImage ? 'checked' : ''}>Image</label>
                                    <label><input type="checkbox" name="showBacklink" value="true" ${param.showBacklink != null || empty param.showBacklink ? 'checked' : ''}>Back Link</label>
                                    <label><input type="checkbox" name="showStatus" value="true" ${param.showStatus != null || empty param.showStatus ? 'checked' : ''}>Status</label>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <!--                        <div class="col-md-4 col-12 text-right">
                                                    <a href="AddUser.jsp" class="btn btn-success">Add Slider</a>
                                                </div>-->
                        <h2 class="mt-4">Slider List</h2>
                        <c:if test="${empty sliders}">
                            <div class="alert alert-warning">
                                No valid slider.
                            </div>
                        </c:if>
                        <c:if test="${not empty sliders}">
                            <table class="table table-bordered table-hover">
                                <thead class="thead-dark">
                                    <tr>
                                        <c:if test="${showId}">
                                            <th>
                                                Id
                                                <a href="?sort=id&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&status=${param.status}&showId=${showId}&showTitle=${showTitle}&showImage=${showImage}&showBacklink=${showBacklink}&showStatus=${showStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-up"></i>
                                                </a>
                                                <a href="?sort=id&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&status=${param.status}&showId=${showId}&showTitle=${showTitle}&showImage=${showImage}&showBacklink=${showBacklink}&showStatus=${showStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-down"></i>
                                                </a>
                                            </th>
                                        </c:if>
                                        <c:if test="${showTitle}">
                                            <th>
                                                Title
                                                <a href="?sort=title&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&status=${param.status}&showId=${showId}&showTitle=${showTitle}&showImage=${showImage}&showBacklink=${showBacklink}&showStatus=${showStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-up"></i>
                                                </a>
                                                <a href="?sort=title&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&status=${param.status}&showId=${showId}&showTitle=${showTitle}&showImage=${showImage}&showBacklink=${showBacklink}&showStatus=${showStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-down"></i>
                                                </a>
                                            </th>
                                        </c:if>
                                        <c:if test="${showImage}">
                                            <th>
                                                Image
                                                <a href="?sort=image_url&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&status=${param.status}&showId=${showId}&showTitle=${showTitle}&showImage=${showImage}&showBacklink=${showBacklink}&showStatus=${showStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-up"></i>
                                                </a>
                                                <a href="?sort=image_url&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&status=${param.status}&showId=${showId}&showTitle=${showTitle}&showImage=${showImage}&showBacklink=${showBacklink}&showStatus=${showStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-down"></i>
                                                </a>
                                            </th>
                                        </c:if>
                                        <c:if test="${showBacklink}">
                                            <th>
                                                Back Link
                                                <a href="?sort=backlink&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&status=${param.status}&showId=${showId}&showTitle=${showTitle}&showImage=${showImage}&showBacklink=${showBacklink}&showStatus=${showStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-up"></i>
                                                </a>
                                                <a href="?sort=backlink&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&status=${param.status}&showId=${showId}&showTitle=${showTitle}&showImage=${showImage}&showBacklink=${showBacklink}&showStatus=${showStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-down"></i>
                                                </a>
                                            </th>
                                        </c:if>
                                        <c:if test="${showStatus}">
                                            <th>
                                                Status
                                                <a href="?sort=status&sortOrder=asc&page=${currentPage}&searchTerm=${param.searchTerm}&status=${param.status}&showId=${showId}&showTitle=${showTitle}&showImage=${showImage}&showBacklink=${showBacklink}&showStatus=${showStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-up"></i>
                                                </a>
                                                <a href="?sort=status&sortOrder=desc&page=${currentPage}&searchTerm=${param.searchTerm}&status=${param.status}&showId=${showId}&showTitle=${showTitle}&showImage=${showImage}&showBacklink=${showBacklink}&showStatus=${showStatus}&pageSize=${pageSize}">
                                                    <i class="fas fa-arrow-down"></i>
                                                </a>
                                            </th>
                                        </c:if>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="slider" items="${sliders}">
                                        <tr>
                                            <c:if test="${showId}">
                                                <td>${slider.id}</td>
                                            </c:if>
                                            <c:if test="${showTitle}">
                                                <td>${slider.title}</td>
                                            </c:if>
                                            <c:if test="${showImage}">
                                                <td><img src="data:image/png;base64,${slider.imageUrlAsBase64}" width="100px"/></td>
                                                </c:if>
                                                <c:if test="${showBacklink}">
                                                <td>${slider.backlink}</td>
                                            </c:if>
                                            <c:if test="${showStatus}">
                                                <td>${slider.status}</td>
                                            </c:if>
                                            <td>
                                                <form action="slider-details" method="post">
                                                    <input type="hidden" name="id" value="${slider.id}"/>
                                                    <button type="submit" class="btn btn-primary">Hide</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>

                    <div class="row">
                        <c:if test="${totalPages > 1 && not empty sliders}">
                            <div class="blog-pagination mt-4">
                                <nav>
                                    <ul class="pagination justify-content-center">
                                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                            <a class="page-link" href="?page=${currentPage - 1}&searchTerm=${param.searchTerm}&status=${param.status}&pageSize=${pageSize}&sort=${param.sort}&sortOrder=${param.sortOrder}&showId=${showId}&showTitle=${showTitle}&showImage=${showImage}&showBacklink=${showBacklink}&showStatus=${showStatus}" tabindex="-1">
                                                <i class="fas fa-angle-double-left"></i>
                                            </a>
                                        </li>
                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                <a class="page-link" href="?page=${i}&searchTerm=${param.searchTerm}&status=${param.status}&pageSize=${pageSize}&sort=${param.sort}&sortOrder=${param.sortOrder}&showId=${showId}&showTitle=${showTitle}&showImage=${showImage}&showBacklink=${showBacklink}&showStatus=${showStatus}">${i}</a>
                                            </li>
                                        </c:forEach>
                                        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                            <a class="page-link" href="?page=${currentPage + 1}&searchTerm=${param.searchTerm}&status=${param.status}&pageSize=${pageSize}&sort=${param.sort}&sortOrder=${param.sortOrder}&showId=${showId}&showTitle=${showTitle}&showImage=${showImage}&showBacklink=${showBacklink}&showStatus=${showStatus}">
                                                <i class="fas fa-angle-double-right"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <jsp:include page="footer.jsp" />
        </div>
        <script data-cfasync="false" src="../../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/plugins/theia-sticky-sidebar/ResizeSensor.js"></script>
        <script src="assets/plugins/theia-sticky-sidebar/theia-sticky-sidebar.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>