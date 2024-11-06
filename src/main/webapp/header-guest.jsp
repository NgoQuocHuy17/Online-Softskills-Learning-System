
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<header class="header">
    <div class="header-fixed">
        <nav class="navbar navbar-expand-lg header-nav">
            <div class="navbar-header">
                <a id="mobile_btn" href="javascript:void(0);">
                    <span class="bar-icon">
                        <span></span>
                        <span></span>
                        <span></span>
                    </span>
                </a>
                <a href="home" class="navbar-brand logo">
                    <img src="assets/img/logo-6.png" class="img-fluid" alt="Logo">
                </a>

            </div>
            <div class="main-menu-wrapper">
                <div class="menu-header">
                    <a href="home" class="menu-logo">
                        <img src="assets/img/logo-6.png" class="img-fluid" alt="Logo">
                    </a>
                    <a id="menu_close" class="menu-close" href="javascript:void(0);">
                        <i class="fas fa-times"></i>
                    </a>
                </div>
                <ul class="main-nav">
                    <li>
                        <a href="home">Back Home</a>
                    </li>
                    <li>
                        <a href="course">Course list</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</header>