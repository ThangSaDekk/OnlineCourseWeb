<%-- 
    Document   : invoiceDetails
    Created on : Aug 24, 2024, 6:09:06 PM
    Author     : TAN DAT
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="content-wrapper " style="overflow-y: auto;">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <div class="card-body clearfix float-sm-left">

                    </div>
                </div><!-- /.col -->
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Invoice</li>
                    </ol>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->
    <section class="content ">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <!-- Main content -->
                    <div class="invoice p-3 mb-3 bg-gradient-lightblue shadow-sm">
                        <c:set var="currentDate" value="<%= new java.util.Date()%>" />
                        <!-- title row -->
                        <div class="row">
                            <div class="col-12">
                                <h4>
                                    <i class="fas fa-address-book"></i> CourseOnline.Co
                                    <small class="float-right">Date: <fmt:formatDate value="${currentDate}" pattern="dd-MM-yyyy"/></small>
                                </h4>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- info row -->

                        <div class="row invoice-info">
                            <c:set var="currentPayerName" value="" />
                            <c:set var="currentPayerEmail" value="" />
                            <c:set var="currentInvoiceNumber" value="" />
                            <c:set var="currentPayerPhone" value="" />
                            <c:set var="currentPayerName1" value="" />
                       
                            <!-- /.col -->
                            <div class="col-sm-4 invoice-col">
                                <h4><strong>Information Payer</strong></h4>
                                <address>
                                    <c:forEach var="dto" items="${enrollmentDTOs}">
                                        <c:if test="${dto.payerName != currentPayerName}">
                                            <u>Full Name:</u> ${dto.payerName}<br>
                                            <c:set var="currentPayerName" value="${dto.payerName}" />
                                        </c:if>

                                        <c:if test="${dto.payerPhone != currentPayerPhone}">
                                            <u>Phone:</u> ${dto.payerPhone}<br>
                                            <c:set var="currentPayerPhone" value="${dto.payerPhone}" />
                                        </c:if>
                                        <c:if test="${dto.payerEmail != currentPayerEmail}">
                                            <u>Email:</u> ${dto.payerEmail}<br>
                                            <c:set var="currentPayerEmail" value="${dto.payerEmail}" />
                                        </c:if>
                                    </c:forEach>
                            
                                </address>
                            </div>
                            <!-- /.col -->
                            <div class="col-sm-8     invoice-col">
                                <h4><strong>Information Payment</strong></h4>
                                <c:forEach var="dto" items="${enrollmentDTOs}">
                                    <c:if test="${dto.referenceCode != currentInvoiceNumber}">
                                        <u>Invoice Number:</u> ${dto.referenceCode}<br>
                                        <c:set var="currentInvoiceNumber" value="${dto.referenceCode}" />
                                    </c:if>

                                </c:forEach>
                               <u>Payment Due:</u> <fmt:formatDate value="${currentDate}" pattern="dd-MM-yyyy"/><br>
                                
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->


                        <!-- Table row -->
                        <div class="row">
                            <div class="col-12 table-responsive">
                                <c:if test="${not empty enrollmentDTOs}">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Payer Name</th>
                                                <th>Payer Email</th>
                                                <th>User</th>
                                                <th>Course Title</th>
                                                <th>Price Course</th>
                                                <th>Create Date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <h3><strong>Details Invoice</strong></h3>
                                            <c:forEach var="dto" items="${enrollmentDTOs}">
                                                <tr>                                            
                                                    <td>${dto.payerName}</td>
                                                    <td>${dto.payerEmail}</td>
                                                    <td>${dto.userId.firstName} ${dto.userId.lastName}</td>                                            
                                                    <td>${dto.courseId.title}</td>
                                                    <td>${dto.courseId.price} VND</td>
                                                    <td>${dto.createdDate}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if>
                                <c:if test="${empty enrollmentDTOs}">
                                    <h1>No enrollment details available.</h1>
                                </c:if>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->

                        <div class="row">
                            <!-- accepted payments column -->
                            <div class="col-6">
              
                             
                            </div>
                            <!-- /.col -->
                            <div class="col-6">

                                <p class="lead">Amount Due: <fmt:formatDate value="${currentDate}" pattern="dd-MM-yyyy"/></p>

                                <div class="table-responsive">
                                    <c:set var="currentTotalAmount" value="" />

                                    <table class="table">
                                        <c:forEach var="dto" items="${invoiceStatsDTOs}">

                                            <c:if test="${dto.totalAmount != currentTotalAmount}">
                                                <tr>
                                                    <th style="width:50%">Total:</th>
                                                    <td>${dto.totalAmount} VND</td>
                                                </tr>

                                                <c:set var="currentTotalAmount" value="${dto.totalAmount}" />
                                            </c:if>
                                        </c:forEach>
                                    </table>

                                </div>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->
                        <div class="row no-print">
                            <div class="col-12">
                                <button onclick="window.print()" type="button" class="btn btn-primary float-right" style="margin-right: 5px;">
                                    <i class="fas fa-download"></i> Print PDF
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- /.invoice -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
</div>
