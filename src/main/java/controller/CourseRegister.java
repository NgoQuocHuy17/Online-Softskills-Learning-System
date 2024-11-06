package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Course;
import view.CourseDAO;
import view.PackageDAO;
import model.Package;

@WebServlet(name = "CourseRegister", urlPatterns = {"/CourseRegister"})
public class CourseRegister extends HttpServlet {

    private final CourseDAO courseDAO = new CourseDAO();
    private final PackageDAO packageDAO = new PackageDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseIdParam = request.getParameter("courseId");
        String choosingPackageIdParam = request.getParameter("choosingPackageId");

        if (courseIdParam != null) {
            int courseId = Integer.parseInt(courseIdParam);
            Course course = courseDAO.getCourseById(courseId);
            List<Package> packages = packageDAO.getPackagesByCourseId(courseId);

            request.setAttribute("course", course);
            request.setAttribute("packages", packages);

            if (choosingPackageIdParam != null) {
                int choosingPackageId = Integer.parseInt(choosingPackageIdParam);
                double price = packageDAO.getPriceById(choosingPackageId); // Lấy giá tương ứng từ DAO
                request.setAttribute("selectedPackagePrice", price);
            }

            request.getRequestDispatcher("CourseRegister.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Course không được tìm thấy");
            request.getRequestDispatcher("/CourseRegister.jsp").forward(request, response);
        }
    }
}
