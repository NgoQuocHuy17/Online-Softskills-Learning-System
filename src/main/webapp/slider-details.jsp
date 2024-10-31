<%-- 
    Document   : slider-details
    Created on : Oct 11, 2024, 12:30:09?PM
    Author     : Minh
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Slider" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Slider Details</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <div class="main-wrapper">
            <%@ include file="header.jsp"%>
            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-md-12 col-12">
                            <nav aria-label="breadcrumb" class="page-breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Slider Details</li>
                                </ol>
                            </nav>
                            <h2 class="breadcrumb-title">Slider Details</h2>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <c:if test="${not empty slider}">
                                <div class="slider-details d-flex align-items-start">
                                    <img src="${slider.imageUrl}" alt="${slider.title}" class="slider-image img-fluid" width="750px">
                                    <div class="slider-info ms-3"> <!-- Add a left margin -->
                                        <h2>${slider.title}</h2>
                                        <p><strong>Backlink:</strong> <a href="${slider.backlink}" target="_blank">${slider.backlink}</a></p>
                                        <p><strong>Status:</strong> ${slider.status}</p>
                                        <c:if test="${not empty formattedCreatedAt}">
                                            <p><strong>Created At:</strong> ${formattedCreatedAt}</p>
                                        </c:if>
                                        <c:if test="${not empty formattedUpdatedAt}">
                                            <p><strong>Updated At:</strong> ${formattedUpdatedAt}</p>
                                        </c:if>
                                        <a href="sliderlist" class="btn btn-primary mt-3">Back to Slider List</a>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${empty slider}">
                                <p>Slider not found.</p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <%@ include file="footer.jsp" %>
        </div>
        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/plugins/slick/slick.js"></script>
        <script src="assets/plugins/aos/aos.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>

