<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="content-wrapper" style="overflow-y: auto;">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Courses</h1>
                </div><!-- /.col -->
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Courses</li>
                    </ol>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="card container">
        <div class="card-header border-transparent">
            <h3 class="card-title">Courses</h3>

            <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                </button>
                <button type="button" class="btn btn-tool" data-card-widget="remove">
                    <i class="fas fa-times"></i>
                </button>
            </div>
        </div>
        <!-- /.card-header -->
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table m-0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Status</th>
                            <th>Course Type</th>
                            <th>Price</th>
                            <th>Instructor</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Duy?t qua danh sách các khóa h?c -->
                        <c:forEach var="course" items="${courses}">
                            <tr>
                                <td><a href="#">${course.id}</a></td>
                                <td>${course.title}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${course.status eq 'INACTIVE'}">
                                            <span class="badge badge-danger">${course.status}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-success">${course.status}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><span class="badge badge-info">${course.courseType}</span></td>
                                <td>
                                    <fmt:formatNumber value="${course.price}" type="number" groupingUsed="true" />
                                    VND
                                </td>
                                <td>${course.instructorId} </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- /.table-responsive -->
        </div>
        <!-- /.card-body -->
        <div class="card-footer clearfix">
            <a href="javascript:void(0)" class="btn btn-sm btn-info float-left">Place New Order</a>
            <a href="javascript:void(0)" class="btn btn-sm btn-secondary float-right">View All Orders</a>
        </div>
        <!-- /.card-footer -->
    </div>
    <!-- /.content -->
</div>
