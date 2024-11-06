<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Course, model.Package" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Course Register</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" href="assets/plugins/select2/css/select2.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>

    <body>
        <div class="main-wrapper">
            <header class="header">
                <jsp:include page="header-guest.jsp" />
            </header>

            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-md-12 col-12">
                            <h2 class="breadcrumb-title">Course Register</h2>
                        </div>
                    </div>
                </div>
            </div>

            <%
                // Kiểm tra xem có user nào đang đăng nhập hay không
                User user = (User) session.getAttribute("user");
            %>

            <div class="content">
                <div class="container-fluid">
                    <c:if test="${not empty message}">
                        <div class="alert alert-warning">${message}</div>
                    </c:if>
                    <div class="row">
                        <div class="card">
                            <div class="card-body">
                                <!-- Thông tin về khóa học và lựa chọn gói -->
                                <h2>${course.title}</h2>
                                <h4>${course.tagLine}</h4>
                                <h4>Category: ${course.category}</h4>
                                <p>Description: ${course.description}</p>

                                <!-- Form chọn gói -->
                                <form id="packageForm" action="CourseRegister" method="get">
                                    <input type="hidden" name="courseId" value="${course.id}">
                                    <div class="form-group">
                                        <label for="packageSelect">Select Package:</label>
                                        <select class="form-control" id="packageSelect" name="choosingPackageId" required>
                                            <c:forEach var="pkg" items="${packages}">
                                                <option value="${pkg.id}" <c:if test="${pkg.id == param.choosingPackageId}">selected</c:if>>
                                                    ${pkg.packageName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Choose Package</button>

                                </form>
                                <form action="CourseRegister" method="post">
                                    <input type="hidden" name="courseId" value="${course.id}">
                                    <input type="hidden" name="choosingPackageId" value="${choosingPackageId}">
                                    <input type="hidden" name="userId" value="${user.id}">

                                    <c:if test="${choosingPackageId != null}">
                                        <h4>
                                            <br>
                                            <strong>Price for the package: </strong> ${selectedPackagePrice}$
                                        </h4>
                                        <%if (user != null) {%>
                                        <button type="submit" class="btn btn-primary">Register this course</button>
                                        <%}%>
                                    </c:if>
                                </form>
                            </div>
                            <!-- Khối đăng ký khóa học nếu chưa có người dùng đăng nhập -->
                            <div class="card-body">
                                <%
                                    if (user == null) {
                                %>
                                <h3>Register for this Course</h3>
                                <form action="CourseRegisterGuest" method="post">
                                    <input type="hidden" name="courseId" value="${course.id}">
                                    <input type="hidden" name="choosingPackageId" value="${choosingPackageId}">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <div class="form-group">
                                        <label>Full Name</label>
                                        <input type="text" class="form-control" name="fullName" placeholder="" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Login Email</label>
                                        <input type="email" class="form-control" name="email" placeholder="" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Gender</label>
                                        <select class="form-control" name="gender" required>
                                            <option value="Male">Male</option>
                                            <option value="Female">Female</option>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label>Email for contact</label>
                                        <div id="emailInputs">
                                            <div class="input-group mb-2">
                                                <input type="email" class="form-control" name="emails[]" placeholder="Enter email" required>
                                                <div class="input-group-append">
                                                    <div class="input-group-text">
                                                        <input type="radio" name="preferredContact" id="email0" value="email0" checked> Prefer
                                                    </div>
                                                    <button type="button" class="btn btn-danger btn-remove" onclick="removeInput(this)">×</button>
                                                </div>
                                            </div>
                                        </div>
                                        <button type="button" class="btn btn-secondary" id="addEmail">+ More Email</button>
                                    </div>

                                    <div class="form-group">
                                        <label>Phone Numbers</label>
                                        <div id="phoneInputs">
                                            <div class="input-group mb-2">
                                                <input type="text" class="form-control" name="phones[]" placeholder="Enter phone number" required>
                                                <div class="input-group-append">
                                                    <div class="input-group-text">
                                                        <input type="radio" name="preferredContact" id="phone0" value="phone0"> Prefer
                                                    </div>
                                                    <button type="button" class="btn btn-danger btn-remove" onclick="removeInput(this)">×</button>
                                                </div>
                                            </div>
                                        </div>
                                        <button type="button" class="btn btn-secondary" id="addPhone">+ More Phone Number</button>
                                    </div>

                                    <c:if test="${choosingPackageId != null}">
                                        <button type="submit" class="btn btn-primary">Register this course</button>
                                    </c:if>
                                </form>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>

                    <!-- JavaScript to dynamically add input fields -->
                    <script>
                        let emailIndex = 1;
                        let phoneIndex = 1;

                        document.getElementById('addEmail').addEventListener('click', function () {
                            var emailInputs = document.getElementById('emailInputs');
                            var newEmailInputGroup = document.createElement('div');
                            newEmailInputGroup.className = 'input-group mb-2';
                            newEmailInputGroup.innerHTML = `
            <input type="email" class="form-control" name="emails[]" placeholder="Enter another email" required>
            <div class="input-group-append">
                <div class="input-group-text">
                    <input type="radio" name="preferredContact" id="email${emailIndex}" value="email${emailIndex}"> Prefer
                </div>
                <button type="button" class="btn btn-danger btn-remove" onclick="removeInput(this)">×</button>
            </div>
        `;
                            emailInputs.appendChild(newEmailInputGroup);
                            emailIndex++;
                        });

                        document.getElementById('addPhone').addEventListener('click', function () {
                            var phoneInputs = document.getElementById('phoneInputs');
                            var newPhoneInputGroup = document.createElement('div');
                            newPhoneInputGroup.className = 'input-group mb-2';
                            newPhoneInputGroup.innerHTML = `
            <input type="text" class="form-control" name="phones[]" placeholder="Enter another phone number" required>
            <div class="input-group-append">
                <div class="input-group-text">
                    <input type="radio" name="preferredContact" id="phone${phoneIndex}" value="phone${phoneIndex}"> Prefer
                </div>
                <button type="button" class="btn btn-danger btn-remove" onclick="removeInput(this)">×</button>
            </div>
        `;
                            phoneInputs.appendChild(newPhoneInputGroup);
                            phoneIndex++;
                        });

                        function removeInput(element) {
                            element.closest('.input-group').remove();
                        }
                    </script>

                </div>
            </div>


            <footer class="footer">
                <jsp:include page="footer.jsp" />

            </footer>
        </div>

        <script data-cfasync="false" src="../../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/plugins/select2/js/select2.min.js"></script>
        <script src="assets/js/moment.min.js"></script>
        <script src="assets/js/bootstrap-datetimepicker.min.js"></script>
        <script src="assets/plugins/theia-sticky-sidebar/ResizeSensor.js"></script>
        <script src="assets/plugins/theia-sticky-sidebar/theia-sticky-sidebar.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>

