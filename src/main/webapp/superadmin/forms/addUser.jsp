<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Bootstrap Material Admin by Bootstrapious.com</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="robots" content="all,follow">
    <jsp:include page="../common/css.jsp"/>
</head>
<body>
<div class="page">
    <!-- Main Navbar-->
    <jsp:include page="../common/Header.jsp"/>
    <div class="page-content d-flex align-items-stretch">
        <!-- Side Navbar -->
        <jsp:include page="../common/sidebar.jsp"/>
        <div class="content-inner">
            <!-- Page Header-->
            <header class="page-header">
                <div class="container-fluid">
                    <h2 class="no-margin-bottom">
                        <c:if test="${optype eq 'create' }">Add User</c:if>
                    	<c:if test="${optype eq 'edit' }">Edit User</c:if> 
					</h2>
                </div>
            </header>
            <!-- Breadcrumb-->
            <div class="breadcrumb-holder container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<c:url value="/superadmin/"/>">Dashboard</a></li>
                    <li class="breadcrumb-item active">
                        <c:if test="${optype eq 'create' }">Add User</c:if>
                    	<c:if test="${optype eq 'edit' }">Edit User</c:if> 
                    </li>
                </ul>
            </div>
            <!-- Forms Section-->
            <section class="forms">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header d-flex align-items-center">
                                    <h3 class="h4">
                                    	<c:if test="${optype eq 'create' }">Add User Detail</c:if>
                    					<c:if test="${optype eq 'edit' }">Edit User Detail</c:if> 
                                    </h3>
                                </div>
                                <div class="card-body">
                                    <form:form class="form-horizontal" autocomplete="off" method="post" action="/superadmin/user" modelAttribute="userDTO">
                                        <form:hidden path="id" />
                                        
                                         <spring:bind path="company">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">Company</label>
                                        		<div class="col-sm-9">
                                        			<form:select path="company" id="selcompany" items="${lstcompany}" placeholder="Company" class="form-control form-control-success"/>
            										<form:errors path="company" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind>  
    									
    									<spring:bind path="roles">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">User Role</label>
                                        		<div class="col-sm-9">
                                        			<form:select path="roles" id="selroles" items="${lstroles}" placeholder="User Role" class="form-control form-control-success"/>
            										<form:errors path="roles" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind> 
                                        
                                        <spring:bind path="username">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">User Name</label>
                                           			<div class="col-sm-9">
                                                		<form:input path="username" id="username" name="username" type="text" placeholder="User Name" class="form-control form-control-success"/>
               											<form:errors path="username" cssClass="error"></form:errors>
                                           			</div>
           										</div>
       									</spring:bind>
       									
       									<spring:bind path="password">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">Password</label>
                                           			<div class="col-sm-9">
                                               			<form:input id="password" name="password" path="password" type="text" placeholder="Password" class="form-control form-control-success"/>
               											<form:errors path="password" cssClass="error"></form:errors>
                                           			</div>
           										</div>
       									</spring:bind>
       									
                                      	<spring:bind path="passwordConfirm">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">Password Confirm</label>
                                           			<div class="col-sm-9">
                                                		<form:input path="passwordConfirm" id="passwordConfirm" name="passwordConfirm" type="text" placeholder="Password Confirm" class="form-control form-control-success"/>
               											<form:errors path="passwordConfirm" cssClass="error"></form:errors>
                                           			</div>
           										</div>
       									</spring:bind>
       									
                                        <div class="form-group row">
                                            <div class="col-sm-4 offset-sm-3">
                                                <a href="<c:url value="/superadmin/users"/>" class="btn btn-secondary">Cancel</a>
                                                <button type="submit" class="btn btn-primary">Save</button>
                                            </div>
                                        </div>
                                        
                                    </form:form>
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
                            <%--<p>Design by <a href="https://bootstrapious.com/admin-templates" class="external">Bootstrapious</a></p>--%>
                            <!-- Please do not remove the backlink to us unless you support further theme's development at https://bootstrapious.com/donate. It is part of the license conditions. Thank you for understanding :)-->
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </div>
</div>
<jsp:include page="../common/js.jsp"/>
</body>
</html>