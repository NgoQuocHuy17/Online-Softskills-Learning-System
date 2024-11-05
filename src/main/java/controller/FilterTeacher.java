/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

/**
 *
 * @author Minh
 */
@WebFilter ({"/post-details.jsp", "/slider-list.jsp", "/slider-details.jsp", "/subject-list.jsp", "/subject-details.jsp"})
public class FilterTeacher implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        User user = null;

        // Check if session exists and user is logged in
        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        // If user is not logged in
        if (user == null) {
            // Redirect to login page
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }
        
        //if user is not teacher or admin return home
        if (!user.getRole().equalsIgnoreCase("teacher")
                && !user.getRole().equalsIgnoreCase("admin")){
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
            return;
        }
        
        // Allow access to other pages
        chain.doFilter(request, response);
    }
    
}
