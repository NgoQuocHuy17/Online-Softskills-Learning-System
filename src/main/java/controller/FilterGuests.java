///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package controller;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
//import model.User;
//
///**
// *
// * @author Minh
// */
//@WebFilter({"/updateProfile", "/newPassword", "/MyRegistrations", "/MyCourses", "/changepass", "/CancelRegistration", "/change-password.jsp", "/changepass.jsp", "/chat-mentee.jsp", 
//    "/logout.jsp", "/myCourses.jsp", "/myRegistrations.jsp", "/profile-settings.jsp", "/profile.jsp"})
//public class FilterGuests implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        // Cast to http servlet
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        // Get session
//        HttpSession session = httpRequest.getSession(false);
//        User user = null;
//
//        // Check if session exists and user is logged in
//        if (session != null) {
//            user = (User) session.getAttribute("user");
//        }
//
//        // If user is not logged in
//        if (user == null) {
//            httpResponse.sendRedirect("home");
//            return;
//        }
//
//        // Allow access to other pages
//        chain.doFilter(request, response);
//    }
//
//}
