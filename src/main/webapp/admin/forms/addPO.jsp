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
<script type="text/javascript" src="/resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
	        var counter = parseInt($("#counter").val());
	        
	        $("#addrow").on("click", function () {
	        	debugger;
	            var newRow = $("<tr>");
	            var cols = "";
	          
	            cols += '<td>'+(counter+2)+'</td>';
	            cols += '<td><select id="selproducttype'+(counter+1)+'" name="poitems['+(counter+1)+'].type" placeholder="Product Type" class="form-control form-control-success"  onchange="hideQuantityRate('+(counter+1)+')"><option value="1">Product</option><option value="2">Fix Charge</option></select></td>';
	            cols += '<td><input id="particulars'+(counter+1)+'" name="poitems['+(counter+1)+'].particulars" placeholder="Particulars" type="text" class="form-control form-control-success" ></td>';
	            cols += '<td><input id="sqFt'+(counter+1)+'" name="poitems['+(counter+1)+'].sqFt" placeholder="sqFt" type="text" class="form-control form-control-success" onblur="calculateAmount('+(counter+1)+')" value="0.0"></td>';
	            cols += '<td><input id="rate'+(counter+1)+'" name="poitems['+(counter+1)+'].rate" placeholder="Rate" type="text" class="form-control form-control-success" onblur="calculateAmount('+(counter+1)+')" value="0.0"></td>';
	            cols += '<td><input id="amount'+(counter+1)+'" name="poitems['+(counter+1)+'].amount" placeholder="Amount" type="text" class="form-control form-control-success amount" readonly="readonly" value="0.0" onblur="calculateTotal()"></td>';
	
	            cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger " value="Delete"></td>';
	            newRow.append(cols);
	            $("table.order-list").append(newRow);
	            counter++;
	        });
	
	
	        $("table.order-list").on("click", ".ibtnDel", function (event) {
	            $(this).closest("tr").remove();       
	            calculateTotal();
	            counter -= 1
	        });
	
	
	    });
		
		function hideQuantityRate(index){
			debugger;
			var type = $("#selproducttype"+index+"").val();	
			if(type == 1){
				$("#sqFt"+index+"").removeAttr("readonly", "readonly");
				$("#rate"+index+"").removeAttr("readonly", "readonly");
				$("#amount"+index+"").attr("readonly", "readonly");
			}else{
				$("#sqFt"+index+"").attr("readonly","readonly");
				$("#rate"+index+"").attr("readonly","readonly");
				$("#amount"+index+"").removeAttr("readonly", "readonly");
				$("#sqFt"+index+"").val(parseFloat(0.0));
				$("#rate"+index+"").val(parseFloat(0.0));
				$("#amount"+index+"").val(parseFloat(0.0));
			} 
			calculateAmount(index);
		}
	    
	    function calculateAmount(index){
        	var sqFt = document.getElementById("sqFt"+index+"").value;
        	var rate = document.getElementById("rate"+index+"").value;
        	$('#amount'+index+'').val(parseFloat(sqFt * rate));
        	var sum = 0;
	        $(".amount").each(function(){
	            sum += +$(this).val();
	        });
	        $("#totalamount").val(parseFloat(sum));
	        calculateTotal();
	    }
	    
	    function calculateTotal(){
	    	var sum = 0;
	    	var discount=0;
	        $(".amount").each(function(){
	            sum += +$(this).val();
	        });
	        $("#totalamount").val(parseFloat(sum));
	        var type=$("#seldiscount").val();
	        if(type==1){
	        	discount = parseFloat(document.getElementById("discount").value);
			}else{
				discount = parseFloat(parseFloat(document.getElementById("discount").value)*parseFloat(sum))/100
			}
	        var advance=  parseFloat(document.getElementById("advance").value);
		    $("#payableTotal").val(parseFloat(sum-(discount+advance)));
		    $("#roundOff").val(Math.ceil(parseFloat(sum-(discount+advance))));
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
                    	<c:if test="${optype eq 'create' }">Add Purchase Order</c:if>
                    	<c:if test="${optype eq 'edit' }">Edit Purchase Order</c:if>                     
					</h2>
                </div>
            </header>
            <!-- Breadcrumb-->
            <div class="breadcrumb-holder container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<c:url value="/admin/"/>">Dashboard</a></li>
                    <li class="breadcrumb-item active">
                    	<c:if test="${optype eq 'create' }">Add Purchase Order</c:if>
                    	<c:if test="${optype eq 'edit' }">Edit Purchase Order</c:if>   
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
                                        <c:if test="${optype eq 'create' }">Add Purchase Order</c:if>
                    					<c:if test="${optype eq 'edit' }">Edit Purchase Order</c:if>   
                                    </h3>
                                </div>
                                <div class="card-body">
                                    <form:form class="form-horizontal" autocomplete="off" method="post" action="/po/submitPO" modelAttribute="purchaseOrderDTO">
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
    									    
                                        <spring:bind path="newCustomerName">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">Care of Person</label>
                                        		<div class="col-sm-9">
                                                	<form:input path="newCustomerName" id="newCustomerName" type="text" placeholder="Care of Person" class="form-control form-control-success"/>
            										<form:errors path="newCustomerName" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind>       									                                 
									
                                        
                                        <spring:bind path="priceApprovedBy">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">Price Approved By</label>
                                        		<div class="col-sm-9">
                                                	<form:input path="priceApprovedBy" id="priceApprovedBy" type="text" placeholder="Price Approved By" class="form-control form-control-success"/>
            										<form:errors path="priceApprovedBy" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind> 
    									
    									<spring:bind path="contactPersonName">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">Contact Person Name</label>
                                        		<div class="col-sm-9">
                                                	<form:input path="contactPersonName" id="contactPersonName" type="text" placeholder="Contact Person Name" class="form-control form-control-success"/>
            										<form:errors path="contactPersonName" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind> 
                                        
                                        <spring:bind path="address">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">Address</label>
                                        		<div class="col-sm-9">
                                                	<form:input path="address" id="address" type="text" placeholder="Address" class="form-control form-control-success"/>
            										<form:errors path="address" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind>
                                        
                                        <spring:bind path="poDate">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">PO Date</label>
                                        		<div class="col-sm-9">
                                                	<form:input path="poDate" id="poDate" type="date" placeholder="PO Date" class="form-control form-control-success"/>
            										<form:errors path="poDate" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind>
                                        
                                        <div class="container">
                                        	<div class="line"></div>
                                        	<div class="table-responsive">
	                                        	<table id="myTable" class=" table order-list table-bordered	">
												    <thead>
												    	<tr>
												    		<td colspan="7" style="text-align: right;">
												                <input type="button" class="btn btn-success " id="addrow" value="Add" />
												            </td>
												    	</tr>
												        <tr style="background-color : #ccc;">
												        	<td width="6%">#</td>
												            <td width="20%">Product Type</td>
												            <td width="16%">Particulars</td>
												            <td width="16%">Quantity</td>
												            <td width="16%">Rate</td>
												            <td width="16%">Amount</td>
												            <td width="10%">Action</td>
												        </tr>
												    </thead>
												    <tbody>
												    	<c:set value="0" var="counter"></c:set>
												        <c:if test="${optype eq 'create'}">
													        <tr>
													        	<td>1</td>
													        	<form:hidden path="poitems[0].id" />
													           	<td>
																	<spring:bind path="poitems[0].type">
													           			<form:select path="poitems[0].type" id="selproducttype0" items="${lstproducttype}" placeholder="Product Type" class="form-control form-control-success" onchange="hideQuantityRate(0)"/>
													            		<form:errors path="poitems[0].type" cssClass="error"></form:errors>
													            	</spring:bind>
													            </td>
													           	<td>
																	<spring:bind path="poitems[0].particulars">
													            		<form:input path="poitems[0].particulars" id="particulars0" type="text" placeholder="Particulars" class="form-control form-control-success"/>
													            		<form:errors path="poitems[0].particulars" cssClass="error"></form:errors>
													            	</spring:bind>
													            </td>
													           	<td>
																	<spring:bind path="poitems[0].sqFt">
													            		<form:input path="poitems[0].sqFt" id="sqFt0" type="text" placeholder="Quantity" class="form-control form-control-success" onblur="calculateAmount(0)"/>
													            		<form:errors path="poitems[0].sqFt" cssClass="error"></form:errors>
													            	</spring:bind>
													            </td>
													           	<td>
																	<spring:bind path="poitems[0].rate">
														            	<form:input path="poitems[0].rate" id="rate0" type="text" placeholder="Rate" class="form-control form-control-success" onblur="calculateAmount(0)"/>
													            		<form:errors path="poitems[0].rate" cssClass="error"></form:errors>
													            	</spring:bind>
													            </td>
													           	<td>
																	<spring:bind path="poitems[0].amount">
													            		<form:input path="poitems[0].amount" id="amount0" type="text" placeholder="Amount" class="form-control form-control-success amount" readonly="true" onblur="calculateTotal()"/>
													            		<form:errors path="poitems[0].amount" cssClass="error"></form:errors>
													            	</spring:bind>
													            </td>
													            <td>
													            	<a class="deleteRow">
																		<input type="button" class="btn btn-md btn-danger " value="Delete" onclick="alert('You can not delete inserted row')">
																	</a>
													            </td>
													        </tr>
												        </c:if>
												        <c:if test="${optype eq 'edit'}">
												        	<c:forEach items="${purchaseOrderDTO.poitems}" var="poitem" varStatus="po">
												        		<tr>
													        	<td>${po.index+1}</td>
													        	<form:hidden path="poitems[${po.index}].id" />
													        	<td>
																	<spring:bind path="poitems[${po.index}].type">
													           			<form:select path="poitems[${po.index}].type" id="selproducttype${po.index}" items="${lstproducttype}" placeholder="Product Type" class="form-control form-control-success ptype" onchange="hideQuantityRate(${po.index})"/>
													            		<form:errors path="poitems[${po.index}].type" cssClass="error"></form:errors>
													           		</spring:bind>
													            </td>
													           	<td>
													           		<spring:bind path="poitems[${po.index}].particulars">
													            		<form:input path="poitems[${po.index}].particulars" id="particulars${po.index}" type="text" placeholder="Particulars" class="form-control form-control-success" onblur="calculateAmount(${po.index})"/>
													            		<form:errors path="poitems[${po.index}].particulars" cssClass="error"></form:errors>
													           		</spring:bind>
													            </td>
													           	<td>
													           	  	<spring:bind path="poitems[${po.index}].sqFt">
													            		<form:input path="poitems[${po.index}].sqFt" id="sqFt${po.index}"  type="text" placeholder="sqFt" readonly="${(poitem.type eq 2 )? \"true\" : \"false\"}" class="form-control form-control-success" onblur="calculateAmount(${po.index})"/>
													            		<form:errors path="poitems[${po.index}].sqFt" cssClass="error"></form:errors>
													           		</spring:bind>
													            </td>
													           	<td>
													           	  	<spring:bind path="poitems[${po.index}].rate">
													           			<form:input path="poitems[${po.index}].rate" id="rate${po.index}"  type="text" placeholder="Rate" readonly="${(poitem.type eq 2 )? \"true\" : \"false\"}" class="form-control form-control-success" onblur="calculateAmount(${po.index})" />
													            		<form:errors path="poitems[${po.index}].rate" cssClass="error"></form:errors>
													           		</spring:bind>													            	
													            </td>
													           	<td>
													           	  	<spring:bind path="poitems[${po.index}].amount">
													           			<form:input path="poitems[${po.index}].amount" id="amount${po.index}" type="text" placeholder="Amount" readonly="${(poitem.type eq 1 )? \"true\" : \"false\"}" class="form-control form-control-success amount" onblur="calculateTotal()"/>
													            		<form:errors path="poitems[${po.index}].amount" cssClass="error"></form:errors>
													           		</spring:bind>													            	
													            </td>
													            <td>
													            	<a class="deleteRow" >
																		<input type="button" class="ibtnDel btn btn-md btn-danger " value="Delete"/>
																	</a>
													            </td>
													            <c:set value="${po.index}" var="counter"></c:set>
													        </tr>
												        	</c:forEach>
												        </c:if>
												        <input type="hidden" id="counter" name="counter" value="${counter}"/>
												    </tbody>
												   	<tfoot>
												        <tr style="background-color : #ccc;">
												            <td colspan="5" style="text-align: left;">
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
                                            <label class="col-sm-3 form-control-label">Discount</label>
                                            <div class="col-sm-3">
                                            	<spring:bind path="discounttype">
                                            		<form:select path="discounttype" id="seldiscount" items="${lstdiscount}" placeholder="Discount Type" class="form-control form-control-success" onchange="calculateTotal()"/>
                                              		<form:errors path="discounttype" cssClass="error"></form:errors>
                                                </spring:bind>
                                            </div>
                                            <div class="col-sm-3">
                                            	<spring:bind path="discount">
                                                	<form:input path="discount" id="discount" name="discount" type="text" placeholder="Discount" class="form-control form-control-success" onblur="calculateTotal()"/>
                                                	<form:errors path="discount" cssClass="error"></form:errors>
                                                </spring:bind>
                                            </div>
                                        </div>
                                        
                                        <spring:bind path="advance">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">Advance</label>
                                        		<div class="col-sm-9">
                                                	<form:input path="advance" id="advance" type="text" placeholder="Advance" class="form-control form-control-success" onblur="calculateTotal()"/>
            										<form:errors path="advance" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind>
    									                                        
                                        <spring:bind path="roundOff">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">RoundOff</label>
                                        		<div class="col-sm-9">
                                                	<form:input path="roundOff" id="roundOff" type="text" placeholder="RoundOff" class="form-control form-control-success" readonly="true"/>
            										<form:errors path="roundOff" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind>
                                       
                                        <spring:bind path="payableTotal">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">Payable Total</label>
                                        		<div class="col-sm-9">
                                                	<form:input path="payableTotal" id="payableTotal" type="text" placeholder="PayableTotal" class="form-control form-control-success" readonly="true"/>
            										<form:errors path="payableTotal" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind>
                                        
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