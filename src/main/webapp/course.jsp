
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Course List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">

        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">

        <!-- Bootstrap CSS -->
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
            <h1 class="text-center mb-4">Available Courses</h1>

            <div class="search-bar mb-4 position-relative">
                <label for="searchCourse">Search for Courses:</label>
                <div class="input-group">
                    <input type="text" id="searchCourse" class="form-control" placeholder="Type course title..." autocomplete="off">
                    <button id="searchButton" class="btn btn-outline-secondary" type="button">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
                <ul id="autocomplete-list" class="list-group mt-1"></ul>
            </div>


            <!-- Form để chọn Category -->
            <form class="filter-category mb-4" method="get" action="course">
                <div class="form-group">
                    <label for="category">Filter by Category:</label>
                    <select name="category" id="category" class="form-control" onchange="this.form.submit()">
                        <option value="All" ${category == 'All' ? 'selected' : ''}>All</option>
                        <!-- Loop through categories -->
                        <c:forEach var="cat" items="${categories}">
                            <option value="${cat}" ${category == cat ? 'selected' : ''}>${cat}</option>
                        </c:forEach>
                    </select>
                    <noscript><input type="submit" value="Filter"></noscript>
                </div>
            </form>

            <!-- Course List -->
            <div class="row">
                <c:forEach var="course" items="${courses}">
                    <div class="col-md-12 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h2 class="card-title">${course.title}</h2>
                                <p class="card-text"><strong>Tagline:</strong> ${course.tagLine}</p>
                                <p class="card-text"><strong>Category:</strong> ${course.category}</p>
                                <p class="card-text"><strong>List Price:</strong> ${course.listPrice}</p>
                                <p class="card-text"><strong>Sale Price:</strong> ${course.salePrice}</p>
                                <p class="card-text"><strong>Status:</strong> ${course.status}</p>
                                <a href="course-detail?courseId=${course.id}" class="btn btn-primary">View Details</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Phân trang -->
            <div class="pagination mt-4">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <!-- Nút Previous -->
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="course?page=${currentPage - 1}&category=${category}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <!-- Page Numbers -->
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link" href="course?page=${i}&category=${category}">${i}</a>
                            </li>
                        </c:forEach>

                        <!-- Nút Next -->
                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="course?page=${currentPage + 1}&category=${category}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>

        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/plugins/slick/slick.js"></script>
        <script src="assets/plugins/aos/aos.js"></script>
        <script src="assets/js/script.js"></script>

        <script>
                        $(document).ready(function () {
                            let currentFocus = -1; // Tracks the focused suggestion

                            // Function to handle search
                            function handleSearch() {
                                var searchTerm = $('#searchCourse').val();
                                if (searchTerm.length > 2) {
                                    $.ajax({
                                        url: 'course-search',
                                        type: 'GET',
                                        data: {query: searchTerm},
                                        success: function (data) {
                                            $('#autocomplete-list').empty();
                                            $('#autocomplete-list').html(data);
                                            currentFocus = -1; // Reset the focus index
                                        }
                                    });
                                } else {
                                    $('#autocomplete-list').empty(); // Clear suggestions if input is short
                                }
                            }

                            // Event handler for input change
                            $('#searchCourse').on('input', function () {
                                handleSearch();
                            });

                            // Event handler for search button click
                            $('#searchButton').on('click', function () {
                                handleSearch(); // Trigger the search when button is clicked
                            });

                            // Handle keypress for up, down, and enter keys
                            $('#searchCourse').on('keydown', function (e) {
                                let items = $('#autocomplete-list li');

                                if (e.key === "ArrowDown") {
                                    // Move focus down
                                    currentFocus++;
                                    if (currentFocus >= items.length)
                                        currentFocus = 0; // Loop back to top
                                    addActive(items);
                                    $('#searchCourse').val($(items[currentFocus]).text()); // Set the search box with the focused item
                                } else if (e.key === "ArrowUp") {
                                    // Move focus up
                                    currentFocus--;
                                    if (currentFocus < 0)
                                        currentFocus = items.length - 1; // Loop back to bottom
                                    addActive(items);
                                    $('#searchCourse').val($(items[currentFocus]).text()); // Set the search box with the focused item
                                } else if (e.key === "Enter") {
                                    // Prevent form submission
                                    e.preventDefault();
                                    if (currentFocus > -1 && items[currentFocus]) {
                                        // Simulate a click on the selected item
                                        items[currentFocus].click();
                                    }
                                }
                            });

                            // Add "active" class to the focused item
                            function addActive(items) {
                                if (!items)
                                    return false;
                                removeActive(items); // Remove previous active class
                                if (currentFocus >= items.length)
                                    currentFocus = 0;
                                if (currentFocus < 0)
                                    currentFocus = items.length - 1;
                                $(items[currentFocus]).addClass('active'); // Add class to the currently focused item
                            }

                            // Remove "active" class from all items
                            function removeActive(items) {
                                $(items).removeClass('active');
                            }

                            // Handle click on suggestions
                            $(document).on('click', '#autocomplete-list li', function () {
                                $('#searchCourse').val($(this).text()); // Set the selected text to the input field
                                $('#autocomplete-list').empty();  // Clear suggestions after selection
                            });
                        });
        </script>
    </body>
</html>