
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.User;
import view.CourseDAO;
import view.UserCourseDAO;

@WebServlet(name = "MyCourses", urlPatterns = {"/MyCourses"})
public class MyCourses extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // Retrieve the logged-in user from the session
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        
        // Initialize DAOs
        UserCourseDAO userCourseDAO = new UserCourseDAO();
        CourseDAO courseDAO = new CourseDAO();
        
        // Get all course IDs that the user has access to
        List<Integer> courseIds = userCourseDAO.getCourseIdsByUserId(userId);
        
        // Fetch course details using course IDs
        List<Course> courseList = new ArrayList<>();
        for (int courseId : courseIds) {
            Course course = courseDAO.getCourseById(courseId);
            if (course != null) {
                courseList.add(course);
            }
        }
        
        // Set the list of courses as an attribute and forward to myCourses.jsp
        request.setAttribute("courseList", courseList);
        request.getRequestDispatcher("myCourses.jsp").forward(request, response);
    }
}
