<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="baseUrl" value="?fromCreatedDate=${param.fromCreatedDate}&toCreatedDate=${param.toCreatedDate}&kw=${param.kw}" />

<div class="content-wrapper" style="overflow-y: auto;">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <!-- Add any header content here if needed -->
                </div><!-- /.col -->
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">FAQs</li>
                    </ol>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="card container">
        <div class="card-header border-transparent">
            <h3 class="card-title">FAQs</h3>

            <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                </button>
            </div>
        </div>
        <!-- /.card-header -->

        <!-- Search and Filter Form -->
        <div class="card-body">
            <form method="GET" action="<c:url value='/faqs'/>">
                <div class="row mb-3">
                    <div class="col-md-3">
                        <label for="fromCreatedDate">From Created Date</label>
                        <input type="date" id="fromCreatedDate" name="fromCreatedDate" class="form-control" value="${param.fromCreatedDate}">
                    </div>
                    <div class="col-md-3">
                        <label for="toCreatedDate">To Created Date</label>
                        <input type="date" id="toCreatedDate" name="toCreatedDate" class="form-control" value="${param.toCreatedDate}">
                    </div>
                    <div class="col-md-6">
                        <label for="kw">Search by Question</label>
                        <input type="text" id="kw" name="kw" class="form-control" placeholder="Search by question..." value="${param.kw}">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary btn-block">Filter</button>
                    </div>
                </div>
            </form>
        </div>

        <!-- FAQ Table -->
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table m-0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Question</th>
                            <th>Answer</th>
                            <th>Created Date</th>
                            <th>Updated Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="faq" items="${faqs}" varStatus="index">
                            <tr id="faq${faq.id}">
                                <td>${index.index + 1}</td>
                                <td>${faq.question}</td>
                                <td>${faq.answer}</td>
                                <td>
                                    <fmt:formatDate value="${faq.createdDate}" pattern="dd-MM-yyyy hh:mm:ss" />
                                </td>
                                <td>
                                    <fmt:formatDate value="${faq.updatedDate}" pattern="dd-MM-yyyy hh:mm:ss" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- /.table-responsive -->
        </div>

        <!-- Pagination -->
        <div class="card-footer clearfix">
            <ul class="pagination pagination-sm m-0 float-right">
                <c:if test="${param.page > 1}">
                    <li class="page-item">
                        <a class="page-link" href="${baseUrl}&page=${param.page - 1}">&laquo;</a>
                    </li>
                </c:if>
                <c:forEach var="i" begin="1" end="${pageTotal}">
                    <li class="page-item ${i == param.page ? 'active' : ''}">
                        <a class="page-link" href="${baseUrl}&page=${i}">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${param.page < pageTotal}">
                    <li class="page-item">
                        <a class="page-link" href="${baseUrl}&page=${param.page + 1}">&raquo;</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
    <!-- /.content -->
</div>
