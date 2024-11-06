<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Add Registration</title>
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
                <jsp:include page="header-admin.jsp" />
            </header>

            <div class="breadcrumb-bar">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-md-12 col-12">
                            <h2 class="breadcrumb-title">Add New Registration</h2>
                        </div>

                    </div>
                </div>
            </div>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="card">
                            <div class="card-body">
                                <form action="AddRegistration" method="post">
                                    <div class="row form-row">
                                        <c:if test="${not empty message}">
                                            <div class="alert alert-warning">${message}</div>
                                        </c:if>
                                        <!-- User ID -->
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>User ID</label>
                                                <input type="number" class="form-control" name="userId">
                                            </div>
                                        </div>

                                        <!-- Course ID Dropdown -->
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Course</label>
                                                <select class="form-control" id="courseSelect" name="courseId" onchange="updatePackages()" required>
                                                    <c:forEach var="course" items="${courses}">
                                                        <option value="${course.id}">${course.title}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Package ID Dropdown -->
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Package</label>
                                                <select class="form-control" id="packageSelect" name="packageId" onchange="updateTotalCostAndPrice()" required>
                                                    <!-- Options will be dynamically updated based on the selected course -->
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Price / Sale Price -->
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Price / Sale Price</label>
                                                <input type="text" class="form-control" id="priceSalePrice" readonly>
                                            </div>
                                        </div>

                                        <!-- Notes -->
                                        <div class="col-12 col-md-6">
                                            <div class="form-group">
                                                <label>Notes</label>
                                                <textarea class="form-control" name="notes"></textarea>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="submit-section">
                                        <button type="submit" class="btn btn-primary submit-btn">Add Registration</button>
                                    </div>
                                </form>
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
                                'data-price': pkg.price, // Lưu trữ giá của gói trong thuộc tính dữ liệu
                                'data-sale-price': pkg.salePrice // Lưu trữ giá bán của gói trong thuộc tính dữ liệu
                            }));
                        });

                        // Cập nhật tổng chi phí sau khi cập nhật danh sách gói
                        updateTotalCostAndPrice();
                    },
                    error: function (xhr, status, error) {
                        console.error('Error fetching packages:', error);
                    }
                });
            }

            function updateTotalCostAndPrice() {
                var $selectedPackage = $('#packageSelect').find('option:selected');
                var price = $selectedPackage.data('price');
                var salePrice = $selectedPackage.data('sale-price');
                $('#priceSalePrice').val(price + ' $ / ' + salePrice + ' $');
                $('#totalCost').val(salePrice + ' $'); // Sử dụng giá bán làm tổng chi phí
            }

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

