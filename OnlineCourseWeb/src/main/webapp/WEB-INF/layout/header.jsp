<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Preloader -->
<div class="preloader flex-column justify-content-center align-items-center">
    <img class="animation__shake" src="<c:url value="/dist/img/AdminLTELogo.png"/>" alt="AdminLTELogo" height="60" width="60">
</div>

<!-- Navbar -->
<nav class="main-header navbar navbar-expand navbar-dark navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
        </li>
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <a class="nav-link" data-widget="navbar-search" href="#" role="button">
                <i class="fas fa-search"></i>
            </a>
            <div class="navbar-search-block">
                <form class="form-inline">
                    <div class="input-group input-group-sm">
                        <input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search">
                        <div class="input-group-append">
                            <button class="btn btn-navbar" type="submit">
                                <i class="fas fa-search"></i>
                            </button>
                            <button class="btn btn-navbar" type="button" data-widget="navbar-search">
                                <i class="fas fa-times"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </li>

        <li class="nav-item">
            <a class="nav-link" data-widget="fullscreen" href="#" role="button">
                <i class="fas fa-expand-arrows-alt"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-widget="control-sidebar" data-controlsidebar-slide="true" href="#" role="button">
                <i class="fas fa-th-large"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/logout" />" role="button">
                <i class="fas fa-sign-out-alt"></i> Logout
            </a>
        </li>
    </ul>
</nav>
<!-- /.right nav bar -->

<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-cyan elevation-4">
    <!-- Brand Logo -->
    <a class="brand-link">
        <img src="<c:url value="/dist/img/AdminLTELogo.png"/>" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
        <span class="brand-text font-weight-light">Online Course Admin</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
   



        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Drashboard -->
                <li class="nav-item menu-open">
                    <a href="<c:url value="/"/>" class="nav-link active">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            Dashboard
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="<c:url value="/stats"/>" class="nav-link">
                        <i class="nav-icon fas fa-chart-pie"></i>
                        <p>
                            Charts
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-list"></i>
                        <p>
                            Manager
                            <i class="fas fa-angle-left right"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                         <!-- Ch?a làm -->
                        <li class="nav-item">
                            <a href="<c:url value="/categories"/>" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Category</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/courses"/>" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Course</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/instructor"/>" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Instructor</p>
                            </a>
                        </li>
                         <!-- Ch?a làm -->
                        <li class="nav-item">
                            <a href="<c:url value="/students"/>" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Student</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/enrollments"/>" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Enrollment</p>
                            </a>
                        </li>
                        <!-- Ch?a làm -->
                        <li class="nav-item">
                            <a href="<c:url value="/processes"/>" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Process</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/invoice"/>" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Invoice</p>
                            </a>
                        </li>
                         <!-- Ch?a làm -->
                        <li class="nav-item">
                            <a href="<c:url value="/reviews"/>" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Review</p>
                            </a>
                        </li>
                          <!-- Ch?a làm -->
                        <li class="nav-item">
                            <a href="<c:url value="/faqs"/>" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>faq</p>
                            </a>
                        </li>
                              <!-- Ch?a làm -->
                        <li class="nav-item">
                            <a href="<c:url value="/introductions"/>" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Introduction</p>
                            </a>
                        </li>

                    </ul>
                </li>


            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>
