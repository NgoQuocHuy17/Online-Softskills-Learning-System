/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

//import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import view.PackageDAO;
import model.Package;

@WebServlet(name = "GetPackagesByCourse", urlPatterns = {"/GetPackagesByCourse"})
public class GetPackagesByCourse extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        // Lấy danh sách các gói dựa trên courseId
        PackageDAO packageDAO = new PackageDAO();
        List<Package> packages = packageDAO.getPackagesByCourseId(courseId);

        //Chuyển đổi danh sách gói thành JSON
        Gson gson = new Gson();
        String json = gson.toJson(packages);

        // Thiết lập kiểu phản hồi là JSON và trả về dữ liệu
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
