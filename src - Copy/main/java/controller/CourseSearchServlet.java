package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.CourseDAO;
import model.Course;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/course-search")
public class CourseSearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the search query parameter from the request
        String query = request.getParameter("query");

        // Fetch matching courses from the DAO
        CourseDAO courseDAO = new CourseDAO();
        List<Course> suggestions = courseDAO.searchCoursesByTitle(query);

        // Return suggestions as clickable links
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Write each course as an anchor element wrapped in a list item
        for (Course course : suggestions) {
            out.println("<li class='list-group-item'>"
                      + "<a href='course-detail?courseId=" + course.getId() + "'>" 
                      + course.getTitle() + "</a>"
                      + "</li>");
        }

        out.flush();
    }
}
