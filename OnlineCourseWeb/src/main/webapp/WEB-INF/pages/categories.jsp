<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="content-wrapper" style="overflow-y: auto;">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <div class="card-body clearfix float-sm-left">
                        <a href="<c:url value='/categories/add-up'/>" class="btn btn-sm btn-success float-left p-3">
                            <i class="fas fa-plus-circle"></i> <!-- Bi?u t??ng thêm n?i dung -->
                        </a>
                    </div>
                </div><!-- /.col -->
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Categories</li>
                    </ol>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <!-- Hi?n th? thông báo thành công n?u có -->
    <c:if test="${not empty successMsg}">
        <div class="alert alert-success container">
            ${successMsg}
        </div>
    </c:if>

    <div class="card container">
        <div class="card-header border-transparent" id="main">
            <h3 class="card-title">Categories</h3>

            <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                </button>
            </div>
        </div>
        <!-- /.card-header -->

        <!-- Search and Filter Form (Optional) -->
        <div class="card-body">
            <!-- Form tìm ki?m ho?c l?c n?u c?n -->
        </div>

        <!-- Category List Table -->
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table m-0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Category Name</th>
                            <th>Description</th>
                            <th>Created Date</th>
                            <th>Updated Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="category" items="${categories}" varStatus="status">
                            <tr id="category${category.id}">
                                <td>${status.index + 1}</td>
                                <td>${category.name}</td>
                                <td>${category.description}</td>
                                <td><fmt:formatDate value="${category.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                <td><fmt:formatDate value="${category.updatedDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                <td>
                                    <a href="<c:url value='/categories/${category.id}/add-up'/>" class="text-primary mr-2" title="Edit"><i class="fas fa-edit"></i></a>
                                    <c:url value="/api/categories/${category.id}/" var="cD" />
                                    <a href="#" onclick="deleteElement('${cD}')" class="text-danger" title="Delete"><i class="fas fa-trash"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- /.table-responsive -->
        </div>
        <!-- /.card-body -->
    </div>
    <!-- /.card -->
</div>
<!-- /.content-wrapper -->
