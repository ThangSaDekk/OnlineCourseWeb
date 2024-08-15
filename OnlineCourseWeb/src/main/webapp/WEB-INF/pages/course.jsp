<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="content-wrapper" style="overflow-y: auto;">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="ml-5"></h1>
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
    <div class="card-body clearfix">
        <a href="<c:url value='/courses/add-up'/>" class="btn btn-lg btn-success float-right mr-5 " style="font-size: 1.25rem;">
            <i class="fas fa-plus p-2">    </i>
        </a>
    </div>
    <div class="card container">
        <div class="card-header border-transparent">
            <h3 class="card-title">Courses</h3>

            <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
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
                            <th></th> <!-- Thêm c?t Actions -->
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Duy?t qua danh sách các khóa h?c -->
                        <c:forEach var="course" items="${courses}"  varStatus="status">
                            <tr id="course${course.id}">
                                <td>${status.index + 1}</td>
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
                                <td>${course.instructorId.userId.lastName} ${course.instructorId.userId.firstName} </td>
                                <td>
                                    <a href="<c:url value="/courses/add-up/${course.id}"/>" class="text-primary mr-2" title="Edit"><i class="fas fa-edit"></i></a>
                                    <a href="#" class="text-success mr-2" title="View"><i class="fas fa-eye"></i></a>
                                        <c:url value="/api/courses/${course.id}/" var="cD" />
                                    <c:url value="course${course.id}" var="cE" />
                                    <a href="#" onclick="deleteCourse('${cD}','${cE}')" class="text-danger" title="Delete"><i class="fas fa-trash"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- /.table-responsive -->
        </div>
    </div>
    <!-- /.content -->
</div>

