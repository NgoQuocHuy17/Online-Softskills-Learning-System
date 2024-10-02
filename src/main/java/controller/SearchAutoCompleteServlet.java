// File: SearchAutoCompleteServlet.java

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import view.CourseDAO;

@WebServlet("/searchAutoComplete")
public class SearchAutoCompleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String term = request.getParameter("term");
        CourseDAO courseDAO = new CourseDAO();
        List<String> courseTitles = courseDAO.getCourseTitlesByTerm(term);

        // Trả về danh sách tiêu đề khóa học dưới dạng chuỗi phân tách bằng dấu phẩy
        StringBuilder result = new StringBuilder();
        for (String title : courseTitles) {
            if (result.length() > 0) {
                result.append(",");  // Thêm dấu phẩy giữa các tiêu đề
            }
            result.append(title);
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result.toString());  // Trả về chuỗi tiêu đề phân cách bởi dấu phẩy
    }
}
