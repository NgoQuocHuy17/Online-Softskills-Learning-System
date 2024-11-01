<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Lessons List</title>
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="assets/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="assets/plugins/slick/slick.css">
        <link rel="stylesheet" href="assets/plugins/slick/slick-theme.css">
        <link rel="stylesheet" href="assets/plugins/aos/aos.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="container">
            <h1>Lesson Details</h1>

            <!-- Form for Lesson Details -->
            <form action="LessonActionController" method="post">
                <input type="hidden" name="action" value="update"/>
                <input type="hidden" name="lessonId" value="${lesson.id}"/>
                <input type="hidden" name="courseId" value="${lesson.courseID}"/>


                <div class="card mb-3">
                    <div class="card-body">
                        <h3 class="card-title">Lesson Title:</h3>
                        <input type="text" name="title" class="form-control" value="${lesson.title}" required />

                        <h3 class="card-title">Description:</h3>
                        <textarea name="description" class="form-control" rows="4" required>${lesson.description}</textarea>

                        <p><strong>Status:</strong> ${lesson.status ? "Active" : "Inactive"}</p>
                    </div>
                </div>

                <!-- Submit button for updating lesson details -->
                <div class="submit-section d-flex justify-content-between">
                    <button type="submit" class="btn btn-primary submit-btn">Update Lesson Details</button>
                </div>
            </form>

            <h2>Lesson Content</h2>
            <!-- Form for Lesson Content -->
            <c:forEach var="content" items="${content}">
                <div class="card mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Content Type: ${content.contentType}</h5>

                        <!-- Individual form for each content -->
                        <form action="LessonActionController" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="action" value="updateContent"/>
                            <input type="hidden" name="id" value="${content.id}"/>
                            <input type="hidden" name="lessonId" value="${lesson.id}"/>

                            <c:choose>

                                <c:when test="${content.contentType == 'image'}">
                                    <p><strong>Current Image:</strong></p>
                                    <c:if test="${content.contentURL != null}">
                                        <img src="assets/img/${content.contentURL}" alt="Image" style="max-width: 100%; height: auto;" />
                                    </c:if>
                                    <input type="file" name="file" class="form-control" accept="image/*" />
                                    <textarea name="contentdes" class="form-control">${content.contentDescription}</textarea>
                                </c:when>

                                <c:when test="${content.contentType == 'pdf'}">
                                    <p><strong>Current PDF:</strong></p>
                                    <c:if test="${content.contentURL != null}">
                                        <a href="assets/img/${content.contentURL}" target="_blank">View Current PDF</a>
                                    </c:if>
                                    <input type="file" name="file" class="form-control" accept=".pdf" />
                                    <textarea name="contentdes" class="form-control">${content.contentDescription}</textarea>
                                </c:when>
                                <c:when test="${content.contentType == 'video'}">
                                    <p><strong>Content URL:</strong> 
                                        <input type="text" name="contentURL" class="form-control" value="${content.contentURL}" required />
                                        <textarea name="contentdes" class="form-control">${content.contentDescription}</textarea>
                                    </p>
                                </c:when>
                                <c:when test="${content.contentType == 'quiz'}">
                                    <p><strong>Current Quiz URL:</strong></p>
                                    <c:if test="${content.contentURL != null}">
                                        <input type="text" name="contentURL" class="form-control" value="${content.contentURL}" required />
                                        <textarea name="contentdes" class="form-control">${content.contentDescription}</textarea>
                                    </c:if>
                                </c:when>

                                <c:when test="${content.contentType == 'text' && content.textContent != null}">
                                    <p><strong>Text Content:</strong> 
                                        <textarea name="textContent" class="form-control">${content.textContent}</textarea>
                                    </p>
                                </c:when>
                            </c:choose>





                            <!-- Read-only Order input -->

                            <input type="hidden" name="save" value="save"/>
                            <button type="submit" class="btn btn-primary submit-btn">Update Lesson</button>
                        </form>
                        <!-- Delete button for each content -->
                        <form action="LessonActionController" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="deleteContent"/>
                            <input type="hidden" name="id" value="${content.id}"/>
                            <input type="hidden" name="lessonId" value="${lesson.id}"/>
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                        <!-- Closing individual content form -->
                    </div>
                </div>
            </c:forEach>
            <!-- Button to trigger Add Content form -->
            <div class="d-flex justify-content-end mb-3">
                <button type="button" class="btn btn-success" onclick="document.getElementById('addContentForm').style.display = 'block';">Add New Content</button>
            </div>

            <!-- Add Content Form (initially hidden) -->
            <div id="addContentForm" style="display: none;" class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">Add New Content</h5>

                    <form action="LessonActionController" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="action" value="addContent"/>
                        <input type="hidden" name="lessonId" value="${lesson.id}"/>

                        <!-- Content Type Selection -->
                        <div class="mb-3">
                            <label for="contentType" class="form-label">Content Type</label>
                            <select name="contentType" class="form-control" required>
                                <option value="image">Image</option>
                                <option value="pdf">PDF</option>
                                <option value="video">Video</option>
                                <option value="quiz">Quiz</option>
                                <option value="text">Text</option>
                            </select>
                        </div>

                        <!-- Content Description -->
                        <div class="mb-3">
                            <label for="contentDescription" class="form-label">Content Description</label>
                            <textarea name="contentDescription" class="form-control" rows="3" required></textarea>
                        </div>

                        <!-- File Upload / URL / Text Input -->
                        <div class="mb-3">
                            <label for="file" class="form-label">File/URL/Text</label>
                            <input type="file" name="file" class="form-control" id="file" accept="image/*,.pdf"/>
                            <input type="text" name="contentURL" class="form-control mt-2" placeholder="Enter URL (for video/quiz)" />
                            <textarea name="textContent" class="form-control mt-2" rows="4" placeholder="Enter text content"></textarea>
                        </div>

                        <!-- Submit Button -->
                        <button type="submit" class="btn btn-primary">Add Content</button>
                        <button type="button" class="btn btn-secondary" onclick="document.getElementById('addContentForm').style.display = 'none';">Cancel</button>
                    </form>
                </div>
            </div>

        </div>

        <!-- Bootstrap and jQuery -->
        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
