<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="baseUrl" value="?firstName=${param.firstName}&lastName=${param.lastName}&active=${param.active}&fromCreatedDate=${param.fromCreatedDate}&toCreatedDate=${param.toCreatedDate}&fromUpdatedDate=${param.fromUpdatedDate}&toUpdatedDate=${param.toUpdatedDate}" />

<div class="content-wrapper" style="overflow-y: auto;">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">

                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Student</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>

    <!-- Main content -->
    <div class="card container">
        <div class="card-header border-transparent">
            <h3 class="card-title">Student</h3>

            <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                </button>
            </div>
        </div>

        <!-- Search and Filter Form -->
        <div class="card-body">
            <form method="GET" action="<c:url value='/students'/>">

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
                        <label for="fromUpdatedDate">From Updated Date</label>
                        <input type="date" name="fromUpdatedDate" id="fromUpdatedDate" class="form-control" value="${param.fromUpdatedDate}">
                    </div>
                    <div class="col-md-3">
                        <label for="toUpdatedDate">To Updated Date</label>
                        <input type="date" name="toUpdatedDate" id="toUpdatedDate" class="form-control" value="${param.toUpdatedDate}">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-3">
                        <label for="firstName">First Name</label>
                        <input type="text" name="firstName" id="firstName" class="form-control" placeholder="First Name" value="${param.firstName}">
                    </div>
                    <div class="col-md-3">
                        <label for="lastName">Last Name</label>
                        <input type="text" name="lastName" id="lastName" class="form-control" placeholder="Last Name" value="${param.lastName}">
                    </div>
                    <div class="col-md-3">
                        <label for="active">Active</label>
                        <select name="active" id="active" class="form-control">
                            <option value="">All Active</option>
                            <option value="true">Active</option>
                            <option value="false">Inactive</option>

                        </select>
                    </div>
                    <div class="col-md-3">
                        <label>&nbsp;</label> <!-- Empty label to push the Filter button down -->
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
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Active</th>
                            <th>Created Date</th>
                            <th>Updated Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${students}" varStatus="index">
                            <tr id="student${student.id}">
                                <td>${index.index + 1}</td>
                                <td>${student.firstName}</td>
                                <td>${student.lastName}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${student.active}">
                                            <span class="badge badge-success">ACTIVE</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-danger">INACTIVE</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <fmt:formatDate value="${student.createdDate}" pattern="yyyy-MM-dd hh:mm:ss" />
                                </td>
                                <td>
                                    <fmt:formatDate value="${student.updatedDate}" pattern="yyyy-MM-dd hh:mm:ss" />
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${student.active}">
                                            <a href="<c:url value='/students/${student.id}/lock'/>" class="text-danger mr-2" title="Lock"
                                               onclick="return confirmAction('lock', '${student.firstName} ${student.lastName}');">
                                                <i class="fas fa-lock"></i>
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="<c:url value='/students/${student.id}/unlock'/>" class="text-success mr-2" title="Unlock"
                                               onclick="return confirmAction('unlock', '${student.firstName} ${student.lastName}');">
                                                <i class="fas fa-lock-open"></i>
                                            </a>
                                        </c:otherwise>
                                    </c:choose>

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

<script type="text/javascript">
    function confirmAction(action, studentName) {
        var actionText = action === 'lock' ? 'lock' : 'unlock';
        return confirm('Do you want ' + actionText + ' student account ' + studentName + '?');
    }
</script>
