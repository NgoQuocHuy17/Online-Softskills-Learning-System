<%-- 
    Document   : logout
    Created on : Nov 1, 2024, 2:16:47 AM
    Author     : daihi
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Invalidate the session to log out the user
    session.invalidate();
    
    // Redirect to the login page or home page
    response.sendRedirect("home");
%>
