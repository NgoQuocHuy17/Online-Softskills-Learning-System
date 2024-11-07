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
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// *
// * @author Minh
// */
////development only pages
//@WebFilter ({"/footer.jsp", "/header.jsp", "/index.jsp", "/layout_header.jsp"})
//public class FilterAll implements Filter{
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        
//        // Cast to http servlet
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        
//        // Send to home
//        httpResponse.sendRedirect("home");
//        
//        // Allow access to other pages
//        chain.doFilter(request, response);
//    }
//    
//}
