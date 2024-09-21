<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<c:url value='/categories/add-up' var="action"/>   
<div class="content-wrapper" style="overflow-y: auto;">

    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>
                        <c:choose>
                            <c:when test="${not empty categoryDTO.id}">
                                Update Category
                            </c:when>
                            <c:otherwise>
                                Add Category
                            </c:otherwise>
                        </c:choose>
                    </h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="<c:url value='/' />">Home</a></li>
                        <li class="breadcrumb-item active">
                            <c:choose>
                                <c:when test="${not empty categoryDTO.id}">
                                    Update Category
                                </c:when>
                                <c:otherwise>
                                    Add Category
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <c:if test="${not empty errors}">
        <div class="alert alert-danger container">
            <strong>Error List:</strong><br>
            <c:forEach var="error" items="${errors}" varStatus="index">
                Error: ${index.index + 1}. ${error.defaultMessage} <br>
            </c:forEach>
        </div>
    </c:if>
    <!-- Main content -->
    <section class="content">
        <div>
            <div class="card card-primary">
                <div class="card-header">
                    <h3 class="card-title">
                        <c:choose>
                            <c:when test="${not empty categoryDTO.id}">
                                Update Category
                            </c:when>
                            <c:otherwise>
                                Add Category
                            </c:otherwise>
                        </c:choose>
                    </h3>
                    <div class="card-tools">
                        <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Thu g?n">
                            <i class="fas fa-minus"></i>
                        </button>
                    </div>
                </div>
                <div class="card-body">
                    <!-- Form thêm/s?a danh m?c -->
                    <form:form method="POST" modelAttribute="categoryDTO" enctype="multipart/form-data" action="${action}">
                        <form:hidden path="id" />
                        <form:hidden path="createdDate" />
                        <form:hidden path="updatedDate" />

                        <div class="form-group">
                            <label for="name">Name</label>
                            <form:input path="name" id="name" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label for="description">Description</label>
                            <form:textarea path="description" id="description" class="form-control" rows="4"/>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <a href="<c:url value='/categories'/>" class="btn btn-secondary">Cancel</a>
                                <c:choose>
                                    <c:when test="${not empty categoryDTO.id}">
                                        <input type="submit" value="Updated Category" class="btn btn-success float-right">
                                    </c:when>
                                    <c:otherwise>
                                        <input type="submit" value="Created Category" class="btn btn-success float-right">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </form:form>
                </div>
                <!-- /.card-body -->
            </div>
            <!-- /.card -->
        </div>
    </section>
    <!-- /.content -->
</div>
