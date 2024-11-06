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
@WebFilter({"/UserList", "/UserDetails", "/UpdateUserDetails", "/SettingListController", "/RegistrationsList", "/RegistrationDetails", "/AddUser", "/AddUser.jsp", "/registrationDetails.jsp", "/RegistrationsList.jsp", "/setting-list.jsp", "/UserDetails.jsp", "/UserDetailsTest.jsp", "/UsersList.jsp", "/UserListTest.jsp"})
public class FilterAdmins implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        // Check if user is logged in
        if (user == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }

        // Ensure role is non-null to avoid NullPointerException
        if (user.getRole() == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
            return;
        }

        // Check if the user is an admin for general protected pages
        if (!"admin".equalsIgnoreCase(user.getRole())) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
            return;
        }

        // Allow access if all checks are passed
        chain.doFilter(request, response);
    }
}
