<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Bootstrap Material Admin by Bootstrapious.com</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="robots" content="all,follow">
    <jsp:include page="common/css.jsp"/>
</head>
<body>
<div class="page">
    <!-- Main Navbar-->
    <jsp:include page="common/Header.jsp"/>
    <div class="page-content d-flex align-items-stretch">
        <!-- Side Navbar -->
        <jsp:include page="common/sidebar.jsp"/>
        <div class="content-inner">
            <!-- Page Header-->
            <header class="page-header">
                <div class="container-fluid">
                    <h2 class="no-margin-bottom">Invoices</h2>
                </div>
            </header>
            <!-- Breadcrumb-->
            <div class="breadcrumb-holder container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<c:url value="/admin/"/>">Dashboard</a></li>
                    <li class="breadcrumb-item active">Invoices</li>
                </ul>
            </div>
            <section class="tables">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-close">
                                    <div class="dropdown">
                                        <a href="<c:url value="/invoice/addInvoice"/>" class="btn btn-primary btn-sm"><i class="fa fa-plus-square"></i>  Add Invoice</a>
                                    </div>
                                </div>
                                <div class="card-header d-flex align-items-center">
                                    <h3 class="h4">Invoices</h3>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-sm">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>No</th>
                                                <th>Date</th>
                                                <th>Vendor</th>
                                                <th>Products</th>
                                                <th>Total</th>
                                                <th>Payable</th>
                                                <th>Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
	                                            <c:forEach items="${invoiceList}" var="invoice" varStatus="inv">
	                                            	<tr>
	                                                    <th scope="row">${inv.index+1}</th>
	                                               	    <td>${invoice.id}</td>
                                                   		<td><fmt:formatDate value="${invoice.invoiceDate}" pattern="dd-MM-yyyy" /></td>
	                                                    <td>${invoice.contactPerson}</td>
	                                                    <td>${invoice.advance}</td>
	                                                    <td>${invoice.payableTotal}</td>
	                                                    <td>${invoice.payableTotal}</td>
	                                                    <td>
	                                                        <span class="btn-group">
	                                                            <a href="/invoice/editInvoice/${invoice.id}" class="btn btn-small"><i class="fa fa-pencil"></i></a>
	                                                            <a href="/invoice/deleteInvoice/${invoice.id}" class="btn btn-small"><i class="fa fa-trash"></i></a>
	                                                        </span>
	                                                    </td>
	                                                </tr>
	                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Page Footer-->
            <footer class="main-footer">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-6">
                            <p>Your company &copy; 2017-2019</p>
                        </div>
                        <div class="col-sm-6 text-right">
                            <p>Design by <a href="https://bootstrapious.com/admin-templates" class="external">Bootstrapious</a></p>
                            <!-- Please do not remove the backlink to us unless you support further theme's development at https://bootstrapious.com/donate. It is part of the license conditions. Thank you for understanding :)-->
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </div>
</div>
<jsp:include page="common/js.jsp"/>
</body>
</html>