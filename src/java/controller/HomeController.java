package controller;

import dao.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeController", urlPatterns = "/home")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SliderDAO sliderDAO = new SliderDAO();
        PostDAO postDAO = new PostDAO();
        CourseDAO courseDAO = new CourseDAO();

        // Get sliders, hot posts, and featured courses
        List<Slider> sliders = sliderDAO.getVisibleSliders();
        List<Post> hotPosts = postDAO.getHotPosts();
        List<Course> featuredCourses = courseDAO.getFeaturedCourses();

        // Set attributes to forward to JSP
        request.setAttribute("sliders", sliders);
        request.setAttribute("hotPosts", hotPosts);
        request.setAttribute("featuredCourses", featuredCourses);

        // Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }
}


