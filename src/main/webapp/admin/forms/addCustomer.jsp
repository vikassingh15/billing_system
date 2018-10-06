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
                        <c:if test="${optype eq 'create' }">Add Customer</c:if>
                    	<c:if test="${optype eq 'edit' }">Edit Customer</c:if> 
					</h2>
                </div>
            </header>
            <!-- Breadcrumb-->
            <div class="breadcrumb-holder container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<c:url value="/admin/"/>">Dashboard</a></li>
                    <li class="breadcrumb-item active">
                        <c:if test="${optype eq 'create' }">Add Customer</c:if>
                    	<c:if test="${optype eq 'edit' }">Edit Customer</c:if> 
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
                                    	<c:if test="${optype eq 'create' }">Add Customer Detail</c:if>
                    					<c:if test="${optype eq 'edit' }">Edit Customer Detail</c:if> 
                                    </h3>
                                </div>
                                <div class="card-body">
                                    <form:form class="form-horizontal" autocomplete="off" method="post" action="/customer" modelAttribute="customerDTO">
                                        <form:hidden path="id" />
                                        
                                        <spring:bind path="name">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">Customer Name</label>
                                           			<div class="col-sm-9">
                                                		<form:input path="name" id="name" name="name" type="text" placeholder="Customer Name" class="form-control form-control-success"/>
               											<form:errors path="name" cssClass="error"></form:errors>
                                           			</div>
           										</div>
       									</spring:bind>
       									
       									<spring:bind path="address">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">Address</label>
                                           			<div class="col-sm-9">
                                               			<form:textarea id="address" name="address" path="address" type="text" placeholder="Address" class="form-control form-control-success"/>
               											<form:errors path="address" cssClass="error"></form:errors>
                                           			</div>
           										</div>
       									</spring:bind>
       									
       									<div class="form-group row">
                                            <label class="col-sm-3 form-control-label">City &amp; Pin</label>
                                            <div class="col-sm-9">
                                                <div class="row">
                                                    <div class="col-md-7">
                                                    	<spring:bind path="city">
	                                                    	<form:input path="city" placeholder="City" class="form-control" type="text"/>
	                                                       	<form:errors path="city" cssClass="error"></form:errors>
                                                        </spring:bind>
                                                    </div>
                                                    <div class="col-md-5">
                                                    	<spring:bind path="pin">
	                                                        <form:input path="pin" placeholder="Pin" class="form-control" type="text"/>
	                                                        <form:errors path="pin" cssClass="error"></form:errors>	
                                                        </spring:bind>				
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
       									
                                      	<spring:bind path="state">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">State</label>
                                           			<div class="col-sm-9 select">
                                                		<form:select path="state" name="state" id="state" class="form-control">
                                                    		<option>option 1</option>
                                                    		<option>option 2</option>
                                                    		<option>option 3</option>
                                                    		<option>option 4</option>
                                                		</form:select>
                                                		<form:errors path="state" cssClass="error"></form:errors>					
                                            		</div>
           										</div>
       									</spring:bind>       																	
       									
                                        <div class="line"></div>
                                        
       									<spring:bind path="careofPerson">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">Care of Person</label>
                                           			<div class="col-sm-9">
                                                		<form:input path="careofPerson" id="careofPerson" name="careofPerson" type="text" placeholder="Core of Person" class="form-control form-control-success"/>
               											<form:errors path="careofPerson" cssClass="error"></form:errors>
                                           			</div>
           										</div>
       									</spring:bind>   
       									
       									<spring:bind path="copmobileNo">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">Mobile(Care of Person)</label>
                                           			<div class="col-sm-9">
                                                		<form:input path="copmobileNo" id="copmobileNo" name="copmobileNo" type="text" placeholder="Mobile(Care of Person)" class="form-control form-control-success"/>
               											<form:errors path="copmobileNo" cssClass="error"></form:errors>
                                           			</div>
           										</div>
       									</spring:bind>
       									
       									<spring:bind path="paymentTerms">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">Payment terms</label>
                                           			<div class="col-sm-9">
                                               			<form:textarea id="paymentTerms" name="paymentTerms" path="paymentTerms" type="text" placeholder="Payment terms" class="form-control form-control-success"/>
               											<form:errors path="paymentTerms" cssClass="error"></form:errors>
                                           			</div>
           										</div>
       									</spring:bind>
       									                                     
                                      	<spring:bind path="email">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">Email</label>
                                           			<div class="col-sm-9">
                                                		<form:input path="email" id="customerEmail" name="customerEmail" type="email" placeholder="Email Address" class="form-control form-control-success"/>
               											<form:errors path="email" cssClass="error"></form:errors>
                                           			</div>
           										</div>
       									</spring:bind>
       									
                                      	<spring:bind path="website">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">Website</label>
                                           			<div class="col-sm-9">
                                                		<form:input path="website" id="website" name="website" type="text" placeholder="Website URL" class="form-control form-control-success"/>
               											<form:errors path="website" cssClass="error"></form:errors>
                                           			</div>
           										</div>
       									</spring:bind>
       									
                                      	<spring:bind path="mobile">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">Mobile Number</label>
                                           			<div class="col-sm-9">
                                               			<form:input path="mobile" id="mobile" name="mobile" type="text" placeholder="Mobile Number" class="form-control form-control-success"/>
               											<form:errors path="mobile" cssClass="error"></form:errors>
                                           			</div>
           										</div>
       									</spring:bind>
       									
                                      	<spring:bind path="phone">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">Phone</label>
                                           			<div class="col-sm-9">
                                                		<form:input path="phone" id="phone" name="phone" type="text" placeholder="Telephone Number" class="form-control form-control-success"/>
               											<form:errors path="phone" cssClass="error"></form:errors>
                                           			</div>
           										</div>
       									</spring:bind>
       									
                                        <div class="form-group row">
                                            <div class="col-sm-4 offset-sm-3">
                                                <a href="<c:url value="/customers"/>" class="btn btn-secondary">Cancel</a>
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