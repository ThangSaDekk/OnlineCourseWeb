<%-- 
    Document   : instructor.jsp
    Created on : Aug 17, 2024, 3:31:34 PM
    Author     : TAN DAT
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <style>
        /* CSS ?? ?i?u ch?nh ?? r?ng c?a các c?t */
        .column-name {
            width: 20%; /* Thay ??i theo nhu c?u */
        }
        .column-description {
            width: 35%; /* Thay ??i theo nhu c?u */
        }
        .column-id, .column-expertise {
            width: 15%; /* C?n ch?nh c?t ID và Expertise */
        }
 </style>

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
                        <li class="breadcrumb-item active">Instructor</li>
                    </ol>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="card-body clearfix">
        <a href="<c:url value="/instructor/add-up"/>" class="btn btn-sm btn-info float-right mr-5">Add Instructor</a>
            <i class="fas fa-plus p-2">    </i>
        </a>
    </div>
    <div class="card container">
        <div class="card-header border-transparent">
            <h3 class="card-title">Instructor</h3>

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
                            <th class="column-id">ID</th>
                            <th class="column-name">Name</th>
                            <th class="column-expertise">Expertise</th>
                            <th class="column-description">Description</th>
                            <th></th> <!-- Th?m c?t Actions -->
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Duy?t qua danh s?ch c?c giang vien -->
                        <c:forEach var="instructor" items="${instructor}">
                            <tr>
                                <td><a href="#">${instructor.id}</a></td>
                                <td>${instructor.userId.lastName} ${instructor.userId.firstName}</td>
                                <td><span class="badge badge-info">${instructor.expertise}</span></td>

                                <td>${instructor.description} </td>
                                <td>
                                    <a href="<c:url value="/instructor/add-up/${instructor.id}"/>" class="text-primary mr-2" title="Edit"><i class="fas fa-edit"></i></a>
                                    <a href="#" class="text-success mr-2" title="View"><i class="fas fa-eye"></i></a>
                                        <c:url value="/api/instructor/${instructor.id}/" var="cD" />
                                    <c:url value="instructor ${instructor.id}" var="cE" />
                                    <a href="#" onclick="deleteUserInstructor('${cD}','${cE}')" class="text-danger" title="Delete"><i class="fas fa-trash"></i></a>
                                </td>
<!-
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
