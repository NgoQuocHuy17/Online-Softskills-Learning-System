
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.User" %>
<%@ page import="model.Registration" %>
<%@ page import="model.Package" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Registration Details</title>
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
            <footer class="footer">
                <jsp:include page="header-admin.jsp" />
            </footer>

            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-md-12 col-12">
                            <h2 class="breadcrumb-title">Registration Details</h2>
                        </div>
                    </div>
                </div>
            </div>
            <%
                Registration registration = (Registration) request.getAttribute("registration");
                User user = (User) request.getAttribute("user");
            %>
            <div class="content">
                <div class="container-fluid">
                    <!-- Form chi tiết đăng ký -->
                    <div class="col-md-4 col-12 text-right">
                        <a href="add-registration.jsp" class="btn btn-success">Add New Registration</a>
                    </div>
                    <br>
                    <form action="UpdateRegistrationDetails" method="post" enctype="multipart/form-data">
                        <div class="row form-row">
                            <c:if test="${not empty message}">
                                <div class="alert alert-warning">${message}</div>
                            </c:if>
                            <input type="hidden" name="registrationId" value="${registration.id}">

                            <!-- Thông tin đăng ký -->
                            <div class="col-12">
                                <h4>Registration Information</h4>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Course Name</label>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.id == registration.createdBy}">
                                            <select class="form-control" id="courseSelect" name="courseId" onchange="updatePackages()">
                                                <c:forEach var="course" items="${courses}">
                                                    <option value="${course.id}" <c:if test="${course.id == registration.courseId}">selected</c:if>>${course.title}</option>
                                                </c:forEach>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="courseName" value="${course.title}" readonly>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Package Name</label>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.id == registration.createdBy}">
                                            <select class="form-control" id="packageSelect" name="packageId" onchange="updatePackagePrice()">
                                                <c:forEach var="pkg" items="${packages}">
                                                    <option value="${pkg.id}" data-price="${pkg.price}" data-sale-price="${pkg.salePrice}" <c:if test="${pkg.id == registration.packageId}">selected</c:if>>${pkg.packageName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="packageName" value="${pkg.packageName}" readonly>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Package Price / Sale Price</label>
                                    <input type="text" class="form-control" id="packagePrice" name="price" value="${pkg.price} / ${pkg.salePrice}" readonly>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Sale</label>
                                    <input type="text" class="form-control" name="sale" value="From ${pkg.saleStart} to ${pkg.saleEnd}" readonly>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Registration Valid From</label>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.id == registration.createdBy}">
                                            <input type="text" class="form-control" name="validFrom" value="${registration.getValidFrom()}">
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="validFrom" value="${registration.getValidFrom()}" readonly>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Registration Valid To</label>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.id == registration.createdBy}">
                                            <input type="text" class="form-control" name="validTo" value="${registration.getValidTo()}">
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="validTo" value="${registration.getValidTo()}" readonly>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Status</label>
                                    <select class="form-control" name="status">
                                        <option value="Submitted" <%= "Submitted".equals(registration.getStatus()) ? "selected" : ""%>>Submitted</option>
                                        <option value="Paid" <%= "Paid".equals(registration.getStatus()) ? "selected" : ""%>>Paid</option>
                                        <option value="Cancelled" <%= "Cancelled".equals(registration.getStatus()) ? "selected" : ""%>>Cancelled</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Notes</label>
                                    <textarea class="form-control" name="notes">${registration.getNotes()}</textarea>
                                </div>
                            </div>

                            <!-- Thông tin người đăng ký -->
                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Full Name</label>
                                    <input type="text" class="form-control" name="fullName" value="${user.getFullName()}" readonly>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Gender</label>
                                    <select class="form-control" name="gender" disabled>
                                        <option value="Male" <%= "Male".equals(user.getGender()) ? "selected" : ""%>>Male</option>
                                        <option value="Female" <%= "Female".equals(user.getGender()) ? "selected" : ""%>>Female</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Email</label>
                                    <input type="text" class="form-control mb-2" value="${user.getEmail()}" readonly>
                                    <c:forEach var="email" items="${emails}">
                                        <input type="text" class="form-control mb-2" value="${email.contactValue}" readonly>
                                    </c:forEach>
                                </div>
                            </div>

                            <div class="col-12 col-md-6">
                                <div class="form-group">
                                    <label>Phone Numbers</label>
                                    <div class="form-group">
                                        <c:forEach var="phone" items="${phones}">
                                            <input type="text" class="form-control mb-2" value="${phone.contactValue}" readonly>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>

                            <div class="submit-section">
                                <button type="submit" class="btn btn-primary submit-btn">Save Changes</button>
                            </div>
                        </div>
                    </form>

                    <br>
                    <!-- Form upload media -->
                    <div class="row form-row">
                        <div class="col-12">
                            <h4>Upload More Media</h4>
                            <form action="AddRegistrationMedia" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="registrationId" value="<%= registration.getId()%>">

                                <div class="form-group">
                                    <label for="newImages">Upload Images</label>
                                    <input type="file" id="newImages" name="newImage" class="form-control" accept="image/*" multiple>
                                    <label for="imageNote">Image Note</label>
                                    <input type="text" id="imageNote" name="imageNote" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label for="newVideos">Upload Videos</label>
                                    <input type="file" id="newVideos" name="newVideo" class="form-control" accept="video/*" multiple>
                                    <label for="videoNote">Video Note</label>
                                    <input type="text" id="videoNote" name="videoNote" class="form-control">
                                </div>

                                <div class="submit-section">
                                    <button type="submit" class="btn btn-primary submit-btn">Save Media</button>
                                </div>
                            </form>
                        </div>
                    </div>

                    <br>
                    <!-- Form xóa media -->
                    <div class="row form-row">
                        <div class="col-12">
                            <h4>Uploaded Images</h4>
                            <div class="image-gallery">
                                <c:forEach var="image" items="${images}">
                                    <form action="DeleteRegistrationMedia" method="post" class="d-flex align-items-center">
                                        <input type="hidden" name="registrationId" value="${registration.getId()}">
                                        <input type="hidden" name="mediaId" value="${image.id}">
                                        <div>
                                            <img width="160" src="data:image/jpeg;base64,${image.mediaData}" alt="User Image" class="img-thumbnail limited-size">
                                            <p>Note: ${image.note}</p>
                                        </div>
                                        <div class="ml-auto">
                                            <button type="submit" class="btn btn-danger btn-sm" style="margin-left: 10px;">Delete</button>
                                        </div>
                                    </form>
                                    <br>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="col-12">
                            <h4>Uploaded Videos</h4>
                            <div class="video-gallery">
                                <c:forEach var="video" items="${videos}">
                                    <form action="DeleteRegistrationMedia" method="post" class="d-flex align-items-center">
                                        <input type="hidden" name="registrationId" value="${registration.getId()}">
                                        <input type="hidden" name="mediaId" value="${video.id}">
                                        <div>
                                            <video width="320" height="240" controls>
                                                <source src="data:image/jpeg;base64,${video.mediaData}" type="video/mp4">
                                            </video>
                                            <p>Note: ${video.note}</p>
                                        </div>
                                        <div class="ml-auto">
                                            <button type="submit" class="btn btn-danger btn-sm" style="margin-left: 15px;">Delete</button>
                                        </div>
                                    </form>
                                    <br>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <footer class="footer">
                <jsp:include page="footer.jsp" />
            </footer>
        </div>

        <script>
            function updatePackages() {
                var courseId = $('#courseSelect').val();

                // Gửi yêu cầu Ajax đến servlet để lấy danh sách gói
                $.ajax({
                    url: 'GetPackagesByCourse',
                    type: 'GET',
                    data: {courseId: courseId},
                    success: function (data) {
                        var $packageSelect = $('#packageSelect');
                        $packageSelect.empty(); // Xóa các tùy chọn hiện tại

                        // Thêm các tùy chọn gói mới
                        $.each(data, function (index, pkg) {
                            $packageSelect.append($('<option>', {
                                value: pkg.id,
                                text: pkg.packageName,
                                'data-price': pkg.price,
                                'data-sale-price': pkg.salePrice
                            }));
                        });

                        // Cập nhật giá sau khi cập nhật danh sách gói
                        updatePackagePrice();
                    },
                    error: function (xhr, status, error) {
                        console.error('Error fetching packages:', error);
                    }
                });
            }

            function updatePackagePrice() {
                var $selectedPackage = $('#packageSelect').find('option:selected');
                var price = $selectedPackage.data('price');
                var salePrice = $selectedPackage.data('salePrice');
                $('#packagePrice').val(price + ' / ' + salePrice);
            }

            // Gọi hàm updatePackages khi trang tải để thiết lập các gói ban đầu
            $(document).ready(function () {
                updatePackages();
            });
        </script>

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

