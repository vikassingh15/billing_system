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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	    $(document).ready(function () {
	        var counter = parseInt($("#counter").val());
	    	
	        $("#addrow").on("click", function () {
	            var newRow = $("<tr>");
	            var cols = "";
				
	            cols += '<td>'+(counter+2)+'</td>';
	            cols += '<td><select id="selinvoice" name="receiptItemsDTOs['+(counter+1)+'].invoiceItemId" placeholder="Invoice" class="form-control form-control-success"><option value="140">Inv-140 ( 1.0 dated 2018-08-15 00:00:00.0 )</option><option value="142">Inv-142 ( 12.0 dated 2018-08-15 00:00:00.0 )</option></select></td>';
	            cols += '<td><select id="selstatus" name="receiptItemsDTOs['+(counter+1)+'].status" placeholder="Status" class="form-control form-control-success"><option value="1">Part Payment</option><option value="2">Full Payment</option></select></td>';
	            cols += '<td><input id="receiptAmount" name="receiptItemsDTOs['+(counter+1)+'].receiptAmount" placeholder="Contact Person" type="text" class="form-control form-control-success amount" value="0.0"  onblur="calculateTotal()"></td>';
	            cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger " value="Delete"></td>';
	            newRow.append(cols);
	            $("table.order-list").append(newRow);
	            counter++;
	        });
	
	
	
	        $("table.order-list").on("click", ".ibtnDel", function (event) {
	            $(this).closest("tr").remove();       
	            counter -= 1
	        });
	    });
	    
        function searchInvoiceList() {
        	var vendorId = $("#selvendor").val();
       	    $.ajax({
       	        type: "POST",
       	        url: "/receipt/ajax/getInvoiceList",
       	        data: {
       	        	vendorId : vendorId
       	        },
       	        cache: false,
       	        timeout: 600000,
       	        success: function (data) {
					var jsonObject = JSON.stringify(data);
					console.log(data);
       	        },
       	        error: function (e) {
       	        	console.log(e);
       	        }
       	    });
		}
        

	    function calculateTotal(){
	    	var sum = 0;
	    	var discount=0;
	        $(".amount").each(function(){
	            sum += +$(this).val();
	        });
	        $("#totalamount").val(parseFloat(sum));
	    }
	   
</script>
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
                    	<c:if test="${optype eq 'create' }">Add Receipt</c:if>
                    	<c:if test="${optype eq 'edit' }">Edit Receipt</c:if>
                    </h2>
                </div>
            </header>
            <!-- Breadcrumb-->
            <div class="breadcrumb-holder container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<c:url value="/admin/"/>">Dashboard</a></li>
                    <li class="breadcrumb-item active">
                    	<c:if test="${optype eq 'create'}">Add Receipt</c:if>
                    	<c:if test="${optype eq 'edit' }">Edit Receipt</c:if>
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
                                    	<c:if test="${optype eq 'create' }">Add Receipt Details</c:if>
                    					<c:if test="${optype eq 'edit' }">Edit Receipt Details</c:if>
                                    </h3>
                                </div>
                                <div class="card-body">
                                    <form:form class="form-horizontal" autocomplete="off" method="post" action="/receipt/submitReceipt" modelAttribute="receiptDTO">
                                       		<form:hidden path="id" />

                                            <spring:bind path="vendor">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">Vendor</label>
                                           			<div class="col-sm-9">
                                           				<form:select path="vendor" id="selvendor" items="${lstvendor}" placeholder="Vendor Name" class="form-control form-control-success" onchange="searchInvoiceList()"/>
               											<form:errors path="vendor" cssClass="error"></form:errors>
                                           			</div>
           										</div>
       										</spring:bind>
                                        
	                                        <spring:bind path="newClientName">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
               										<label class="col-sm-3 form-control-label">Vendor</label>
                                           			<div class="col-sm-9">
                                               			<form:input path="newClientName" id="newClientName" name="newClientName" type="text" placeholder="New Customer Name" class="form-control form-control-success"/>
               											<form:errors path="newClientName" cssClass="error"></form:errors>
                                           			</div>
           										</div>
	       									</spring:bind>
	       									
	       									<spring:bind path="paymodetype">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
                                          			<label class="col-sm-3 form-control-label">Payment Mode Type</label>
                                           			<div class="col-sm-9">
                                           				<form:select path="paymodetype" id="selpaymentmodetype" items="${lstpaymentmodetype}" placeholder="Paymentmodetype" class="form-control form-control-success"/>
               											<form:errors path="paymodetype" cssClass="error"></form:errors>
                                           			</div>
           										</div>
	       									</spring:bind>
	       									
	       									<spring:bind path="receipNo">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
                                          			<label class="col-sm-3 form-control-label">Receipt No</label>
                                           			<div class="col-sm-9">
                                               			<form:input path="receipNo" id="receipNo" name="receipNo" type="text" placeholder="Receip No" class="form-control form-control-success"/>
               											<form:errors path="receipNo" cssClass="error"></form:errors>
                                           			</div>
           										</div>
	       									</spring:bind>	       		
	       									
	       									<spring:bind path="receiptDate">
          										<div class="form-group row ${status.error ? 'has-error' : ''}">
                                          			<label class="col-sm-3 form-control-label">Receipt Date</label>
                                           			<div class="col-sm-9">
                                              				<form:input path="receiptDate" id="receiptDate" name="receiptDate" type="date" placeholder="Receipt Date" class="form-control form-control-success"/>
               											<form:errors path="receiptDate" cssClass="error"></form:errors>
                                           			</div>
           										</div>
	       									</spring:bind>	       																
                                        
                                        
                                        <div class="container">
                                        	<div class="line"></div>
                                        	<div id="dynamic"></div>
                                        	<div class="table-responsive">
	                                        	<table id="myTable" class=" table order-list table-bordered	">
												    <thead>
												    	<tr>
												    		<td colspan="5" style="text-align: right;">
												                <input type="button" class="btn btn-success " id="addrow" value="Add" />
												            </td>
												    	</tr>
												        <tr style="background-color : #ccc;">
												        	<td>#</td>
												            <td>Invoice</td>
												            <td>Status</td>
												            <td>Receipt</td>
												            <td>Action</td>
												        </tr>
												    </thead>
												    <tbody>
												    	<c:if test="${optype eq 'create'}">
													    	<tr>
													        	<td>1</td>
													        	<form:hidden path="receiptItemsDTOs[0].id" />
													        	<td>
													           		<form:select path="receiptItemsDTOs[0].invoiceItemId" id="selinvoice" items="${lstinvoices}" placeholder="Invoice" class="form-control form-control-success"/>
													            </td>
													        	<td>
													           		<form:select path="receiptItemsDTOs[0].status" id="selstatus" items="${lststatus}" placeholder="Status" class="form-control form-control-success"/>
													            </td>
													        	<td>
													            	<form:input path="receiptItemsDTOs[0].receiptAmount" id="receiptAmount" name="receiptAmount" type="text" placeholder="Contact Person" class="form-control form-control-success amount"  onblur="calculateTotal()"/>
													            </td>
													            <td>
													            	<a class="deleteRow">
																		<input type="button" class="btn btn-md btn-danger " value="Delete"  onclick="alert('You can not delete inserted row')">
																	</a>
													            </td>
													            <c:set value="0" var="counter"></c:set>
													        </tr>
												        </c:if>
												        <c:if test="${optype eq 'edit'}">
												        	<c:forEach items="${receiptDTO.receiptItemsDTOs}" var="receipt" varStatus="rec">
													        	<tr>
														        	<td>${rec.index+1}</td>
														        	<form:hidden path="receiptItemsDTOs[${rec.index}].id" />
													        	    <td>
													        	    	<spring:bind path="receiptItemsDTOs[${rec.index}].invoiceItemId">
														           			<form:select path="receiptItemsDTOs[${rec.index}].invoiceItemId" id="selinvoice" items="${lstinvoices}" placeholder="Invoice" class="form-control form-control-success"/>
               																<form:errors path="receiptItemsDTOs[${rec.index}].invoiceItemId" cssClass="error"></form:errors>
														            	</spring:bind>
														            </td>
													        	    <td>
													        	    	<spring:bind path="receiptItemsDTOs[${rec.index}].status">
														           			<form:select path="receiptItemsDTOs[${rec.index}].status" id="selstatus" items="${lststatus}" placeholder="Status" class="form-control form-control-success"/>
               																<form:errors path="receiptItemsDTOs[${rec.index}].status" cssClass="error"></form:errors>
														            	</spring:bind>
														            </td>
													       			<td>
													        	    	<spring:bind path="receiptItemsDTOs[${rec.index}].receiptAmount">
															            	<form:input path="receiptItemsDTOs[${rec.index}].receiptAmount" id="receiptAmount" name="receiptAmount" type="text" placeholder="Contact Person" class="form-control form-control-success amount"  onblur="calculateTotal()"/>
               																<form:errors path="receiptItemsDTOs[${rec.index}].receiptAmount" cssClass="error"></form:errors>
														            	</spring:bind>
														            </td>
														            <td>
														            	<a class="deleteRow">
																			<input type="button" class="btn btn-md btn-danger " value="Delete" >
																		</a>
														            </td>
														            <c:set value="${rec.index}" var="counter"></c:set>
														        </tr>
												        	</c:forEach>
												        </c:if>
												        <input type="hidden" id="counter" name="counter" value="${counter}"/>
												    </tbody>
												   	<tfoot>
												        <tr style="background-color : #ccc;">
												            <td colspan="3" style="text-align: left;">
												                Sub total :
												            </td>
												            <td>
												            	<form:input path="totalamount" id="totalamount" type="text" placeholder="totalamount" class="form-control form-control-success"  onblur="calculateTotal()" readonly="true"/>
												            </td>
												            <td></td>
												  	      </tr>
												        <tr>
												        </tr>
												    </tfoot>
												</table>
											</div>
                                       		<div class="line"></div>
										</div>
                                        <div class="form-group row">
                                            <div class="col-sm-4 offset-sm-3">
                                                <a href="<c:url value="/invoice/list"/>" class="btn btn-secondary">Cancel</a>
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