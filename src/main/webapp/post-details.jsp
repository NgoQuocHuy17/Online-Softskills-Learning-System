<%-- 
    Document   : post-details
    Created on : Nov 1, 2024, 6:09:22 AM
    Author     : Minh
--%>
<%@page import="model.Post"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Post Details</title>

        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">

        <!-- CSS Files -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <ol>
            <%
                List<Post> posts = (List<Post>) request.getAttribute("posts");
                List<String> formattedCreatedAts = (List<String>) request.getAttribute("formattedCreatedAts");
                
                for (int idx = 0; idx < posts.size(); idx++) {
                Post post = posts.get(idx);
                String formattedCreatedAt = formattedCreatedAts.get(idx);
            %>
                <li><img src="${post.thumbnailUrl}"/></li>
                <li><title>${post.title}</title></li>
                <li><p>formattedCreatedAt</p></li>
            <%                        
                }
            %>
        </ol>

        <jsp:include page="footer.jsp" />
        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/plugins/slick/slick.js"></script>
        <script src="assets/plugins/aos/aos.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>
