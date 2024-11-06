<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Registrations List</title>
    </head>
    <body>
        <h1>List of Registrations</h1>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>User ID</th>
                <th>Package ID</th>
                <th>Course ID</th>
                <th>Total Cost</th>
                <th>Status</th>
                <th>Valid From</th>
                <th>Valid To</th>
                <th>Created By</th>
                <th>Details</th> <!-- Thêm tiêu đề cho cột Details -->
            </tr>
            <c:forEach var="registration" items="${registrations}">
                <tr>
                    <td>${registration.id}</td>
                    <td>${registration.userId}</td>
                    <td>${registration.packageId}</td>
                    <td>${registration.courseId}</td>
                    <td>${registration.totalCost}</td>
                    <td>${registration.status}</td>
                    <td>${registration.validFrom}</td>
                    <td>${registration.validTo}</td>
                    <td>${registration.createdBy}</td>
                    <td>
                        <!-- Nút Details gửi ID của registration tới servlet RegistrationDetails.java -->
                        <form action="RegistrationDetails" method="get">
                            <input type="hidden" name="registrationId" value="${registration.id}"/>
                            <input type="submit" value="Details"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
