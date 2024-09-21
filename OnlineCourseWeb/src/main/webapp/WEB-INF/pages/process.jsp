<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="baseUrl" value="?fullName=${param.fullName}&point=${param.point}&title=${param.title}&fromCreatedDate=${param.fromCreatedDate}&toCreatedDate=${param.toCreatedDate}&fromUpdatedDate=${param.fromUpdatedDate}&toUpdatedDate=${param.toUpdatedDate}" />

<div class="content-wrapper" style="overflow-y: auto;">
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6"></div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Process</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>

    <div class="card container">
        <div class="card-header border-transparent">
            <h3 class="card-title">Process</h3>
            <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                </button>
            </div>
        </div>

        <!-- Search and Filter Form -->
        <div class="card-body">
            <form method="GET" action="<c:url value='/processes'/>">
                <div class="row mb-3">
                    <div class="col-md-3">
                        <label for="fromCreatedDate">From Created Date</label>
                        <input type="date" name="fromCreatedDate" id="fromCreatedDate" class="form-control" value="${param.fromCreatedDate}">
                    </div>
                    <div class="col-md-3">
                        <label for="toCreatedDate">To Created Date</label>
                        <input type="date" name="toCreatedDate" id="toCreatedDate" class="form-control" value="${param.toCreatedDate}">
                    </div>
                    <div class="col-md-3">
                        <label for="firstName">First Name</label>
                        <input type="text" name="firstName" id="firstName" class="form-control" placeholder="First Name" value="${param.firstName}">
                    </div>
                    <div class="col-md-3">
                        <label for="lastName">Last Name</label>
                        <input type="text" name="lastName" id="lastName" class="form-control" placeholder="Last Name" value="${param.lastName}">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-3">
                        <label for="courseTitle">Course Title</label>
                        <input type="text" name="courseTitle" id="courseTitle" class="form-control" placeholder="Course Title" value="${param.courseTitle}">
                    </div>
                    <div class="col-md-3">
                        <label for="contentTitle">Content Title</label>
                        <input type="text" name="contentTitle" id="contentTitle" class="form-control" placeholder="Content Title" value="${param.contentTitle}">
                    </div>

                    <div class="col-md-3">
                        <label for="point">Point</label>
                        <input type="number" name="point" id="point" class="form-control" placeholder="Point" value="${param.point}">
                    </div>

                    <div class="col-md-3">
                        <label>&nbsp;</label>
                        <button type="submit" class="btn btn-primary btn-block">Filter</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table m-0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>Course Title</th>
                            <th>Content Title</th>
                            <th>Point</th>
                            <th>Created Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="process" items="${processes}" varStatus="index">
                            <tr id="process${process.id}">
                                <td>${index.index + 1}</td>
                                <td>${process.userId.fullName}</td>
                                <td>${process.contentId.courseId.title}</td>
                                <td>${process.contentId.title}</td>
                                <td>${process.point}</td>
                                <td>
                                    <fmt:formatDate value="${process.createdDate}" pattern="yyyy-MM-dd hh:mm:ss" />
                                </td>


                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="card-footer clearfix">
            <ul class="pagination pagination-sm m-0 float-right">
                <c:forEach var="i" begin="1" end="${pageTotal}">
                    <li class="page-item ${i == param.page ? 'active' : ''}">
                        <a class="page-link" href="${baseUrl}&page=${i}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
