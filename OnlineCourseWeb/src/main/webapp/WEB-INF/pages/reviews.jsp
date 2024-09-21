<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="baseUrl" value="?firstName=${param.firstName}&lastName=${param.lastName}&courseTitle=${param.courseTitle}&rating=${param.rating}&fromCreatedDate=${param.fromCreatedDate}&toCreatedDate=${param.toCreatedDate}" />

<div class="content-wrapper" style="overflow-y: auto;">
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6"></div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Review </li>
                    </ol>
                </div>
            </div>
        </div>
    </div>

    <div class="card container">
        <div class="card-header border-transparent">
            <h3 class="card-title">Review </h3>
            <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                </button>
            </div>
        </div>

        <!-- Search and Filter Form -->
        <div class="card-body">
            <form method="GET" action="<c:url value='/reviews'/>">
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
                        <label for="rating">Rating</label>
                        <input type="number" name="rating" id="rating" class="form-control" placeholder="Rating" value="${param.rating}">
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
                            <th>Rating</th>
                            <th>Review Content</th>
                            <th>Created Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="review" items="${reviews}" varStatus="index">
                            <tr id="review${review.id}">
                                <td>${index.index + 1}</td>
                                <td>${review.userId.fullName}</td>
                                <td>${review.courseId.title}</td>
                                <td>${review.rating}</td>
                                <td>${review.comment}</td>
                                <td>
                                    <fmt:formatDate value="${review.createdDate}" pattern="yyyy-MM-dd hh:mm:ss" />
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
