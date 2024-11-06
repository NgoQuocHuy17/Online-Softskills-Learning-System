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
@WebFilter({"/UserList", "/UserDetails", "/UpdateUserDetails", "/SettingListController", "/AddUser", "/AddUser.jsp", "/setting-list.jsp", "/UserDetails.jsp", "/UserDetailsTest.jsp", 
    "/UsersList.jsp", "/UserListTest.jsp"})
public class FilterAdmins implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // Cast to http servlet
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get session
        HttpSession session = httpRequest.getSession(false);
        User user = null;
        
        // Check if session exists
        if (session != null){
            user = (User) session.getAttribute("user");
        }

        // Check if user has logged in and has a role and is it admin
        if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
            httpResponse.sendRedirect("home");
            return;
        }

        // Allow access if all checks are passed
        chain.doFilter(request, response);
    }
}
