<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
	            cols += '<td><select id="selproducttype'+(counter+1)+'" name="invoiceItems['+(counter+1)+'].productType" placeholder="Product Type" class="form-control form-control-success" onchange="showHideFields('+(counter+1)+')"><option value="1">Product</option><option value="2">Fix Charge</option></select></td>';
	            cols += '<td><input id="particulars'+(counter+1)+'" name="invoiceItems['+(counter+1)+'].particulars" placeholder="Particulars" type="text" class="form-control form-control-success"></td>';
	            cols += '<td><select id="selsubtype'+(counter+1)+'" name="invoiceItems['+(counter+1)+'].type" placeholder="Type" class="form-control form-control-success" disabled="disabled" onchange="showHideFields('+(counter+1)+')"><option value="1">Select type</option><option value="2">Molding Charge</option><option value="3">Polish</option><option value="4">Hall Charge</option><option value="5">Transportation</option></select></td>';
	            cols += '<td><input id="side'+(counter+1)+'" name="invoiceItems['+(counter+1)+'].side" placeholder="Side" type="text" class="form-control form-control-success" disabled="disabled" ></td>';
		        cols += '<td><input id="length'+(counter+1)+'" name="invoiceItems['+(counter+1)+'].length" placeholder="Length" type="text" class="form-control form-control-success" disabled="disabled"></td>';
	            cols += '<td><input id="width'+(counter+1)+'" name="invoiceItems['+(counter+1)+'].width" placeholder="Width" type="text" class="form-control form-control-success" disabled="disabled"></td>';
	            cols += '<td><input id="quantity'+(counter+1)+'" name="invoiceItems['+(counter+1)+'].quantity" placeholder="Quantity" type="text" class="form-control form-control-success" onblur="calculateAmount('+(counter+1)+')"></td>';
	            cols += '<td><input id="rate'+(counter+1)+'" name="invoiceItems['+(counter+1)+'].rate" placeholder="Rate" type="text" class="form-control form-control-success" onblur="calculateAmount('+(counter+1)+')"></td>';
	            cols += '<td><input id="amount'+(counter+1)+'" name="invoiceItems['+(counter+1)+'].amount" placeholder="Amount" type="text" class="form-control form-control-success amount" readonly="readonly" onblur="calculateTotal('+(counter+1)+')"></td>';
				cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger " value="X"></td>';
	            newRow.append(cols);
	            $("table.order-list").append(newRow);
	            counter++;
	        });
	        
	
	        $("table.order-list").on("click", ".ibtnDel", function (event) {
	            $(this).closest("tr").remove();
	            counter -= 1
	        });

        	<c:if test="${optype eq 'edit' }">
		        var listSize=${invoiceDTO.invoiceItems.size()}
		        for(var i=0;i<listSize;i++){
		        	debugger;
		        	showHideFields(i);
		    	}
	        </c:if>
	    });
	    
	    function calculateAmount(index){
        	var quantity = document.getElementById("quantity"+index+"").value;
        	var rate = document.getElementById("rate"+index+"").value;
        	$('#amount'+index+'').val(parseFloat(quantity * rate));
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
	    
	    function showHideFields(index){
			var type = $("#selproducttype"+index+"").val();	
			var subtype = $("#selsubtype"+index+"").val();
			if(type == 1){
				$("#selsubtype"+index+"").attr("disabled", "disabled");
				$("#side"+index+"").attr("disabled", "disabled");
				$("#length"+index+"").attr("disabled", "disabled");
				$("#width"+index+"").attr("disabled", "disabled");
				$("#quantity"+index+"").removeAttr("disabled","disabled");
				$("#rate"+index+"").removeAttr("disabled","disabled");
				$("#amount"+index+"").attr("readonly", "readonly");
			}else if(type == 2){
				if(subtype == 1){
					$("#selsubtype"+index+"").removeAttr("disabled", "disabled");
					$("#side"+index+"").attr("disabled", "disabled");
					$("#length"+index+"").attr("disabled", "disabled");
					$("#width"+index+"").attr("disabled", "disabled");
					$("#quantity"+index+"").attr("disabled","disabled");
					$("#rate"+index+"").attr("disabled","disabled");
					$("#amount"+index+"").removeAttr("readonly", "readonly");
				}else if(subtype == 2){
					$("#selsubtype"+index+"").removeAttr("disabled", "disabled");
					$("#side"+index+"").removeAttr("disabled", "disabled");
					$("#length"+index+"").removeAttr("disabled", "disabled");
					$("#width"+index+"").removeAttr("disabled", "disabled");
					$("#quantity"+index+"").removeAttr("disabled","disabled");
					$("#rate"+index+"").removeAttr("disabled","disabled");
					$("#amount"+index+"").attr("readonly", "readonly");
				}else if(subtype == 3){
					$("#selsubtype"+index+"").removeAttr("disabled", "disabled");
					$("#side"+index+"").attr("disabled", "disabled");
					$("#length"+index+"").removeAttr("disabled", "disabled");
					$("#width"+index+"").removeAttr("disabled", "disabled");
					$("#quantity"+index+"").removeAttr("disabled","disabled");
					$("#rate"+index+"").removeAttr("disabled","disabled");
					$("#amount"+index+"").attr("readonly", "readonly");
				}else if(subtype == 4 || subtype == 5){
					$("#selsubtype"+index+"").removeAttr("disabled", "disabled");
					$("#side"+index+"").attr("disabled", "disabled");
					$("#length"+index+"").attr("disabled", "disabled");
					$("#width"+index+"").attr("disabled", "disabled");
					$("#quantity"+index+"").attr("disabled","disabled");
					$("#rate"+index+"").attr("disabled","disabled");
					$("#amount"+index+"").removeAttr("readonly", "readonly");
				}
			} 
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
                    	<c:if test="${optype eq 'create' }">Add Invoice</c:if>
                    	<c:if test="${optype eq 'edit' }">Edit Invoice</c:if> 
                    </h2>
                </div>
            </header>
            <!-- Breadcrumb-->
            <div class="breadcrumb-holder container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<c:url value="/admin/"/>">Dashboard</a></li>
                    <li class="breadcrumb-item active">
                    	<c:if test="${optype eq 'create' }">Add Invoice</c:if>
                    	<c:if test="${optype eq 'edit' }">Edit Invoice</c:if> 
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
				                    	<c:if test="${optype eq 'create' }">Add Invoice Detail</c:if>
				                    	<c:if test="${optype eq 'edit' }">Edit Invoice Detail</c:if>                     
				                    </h3>
                                </div>
                                <div class="card-body">
                                    <form:form class="form-horizontal" autocomplete="off" method="post" action="/invoice/submitInvoice" modelAttribute="invoiceDTO">
                                        <form:hidden path="id" />
                                        <c:set value="${invoiceDTO.invoiceItems.size()}" var="listSize"></c:set>
                                        <spring:bind path="vendor">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">Vendor</label>
                                        		<div class="col-sm-9">
                                        			<form:select path="vendor" id="selvendor" items="${lstvendor}" placeholder="Vendor Name" class="form-control form-control-success" onchange="searchInvoiceList()"/>
            										<form:errors path="vendor" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind> 
    									
                                        <spring:bind path="contactPerson">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">Contact Person</label>
                                        		<div class="col-sm-9">
                                                	<form:input path="contactPerson" id="contactPerson" name="contactPerson" type="text" placeholder="Contact Person" class="form-control form-control-success"/>
            										<form:errors path="contactPerson" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind>    									
                                        
                                        <spring:bind path="address">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">Address</label>
                                        		<div class="col-sm-9">
                                                	<form:input path="address" id="address" name="address" type="text" placeholder="Address" class="form-control form-control-success"/>
            										<form:errors path="address" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind> 
                                        
                                        <spring:bind path="invoiceNo">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">Invoice No</label>
                                        		<div class="col-sm-9">
                                                	<form:input path="invoiceNo" id="invoiceNo" name="invoiceNo" type="text" placeholder="Invoice No" class="form-control form-control-success"/>
            										<form:errors path="invoiceNo" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind>
                                        
                                        <spring:bind path="invoiceDate">
       										<div class="form-group row ${status.error ? 'has-error' : ''}">
            									<label class="col-sm-3 form-control-label">Invoice Date</label>
                                        		<div class="col-sm-9">
                                                	<form:input path="invoiceDate" id="invoiceDate" name="invoiceDate" type="date" placeholder="Invoice Date" class="form-control form-control-success"/>
            										<form:errors path="invoiceDate" cssClass="error"></form:errors>
                                        		</div>
        									</div>
    									</spring:bind>
                                        
                                        <div class="container">
                                       		<div class="line"></div>
                                       		<div class="table-responsive">
	                                       		<table id="myTable" class=" table order-list table-bordered	">
												    <thead>
												    	<tr>
												    		<td colspan="11" style="text-align: right;">
												                <input type="button" class="btn btn-success " id="addrow" value="Add" />
												            </td>
												    	</tr>
												        <tr style="background-color : #ccc;">
												        	<td>#</td>
												            <td>Product Type</td>
												            <td>Particulars</td>
												            <td>Type</td>
												            <td>Side</td>
												            <td>Length</td>
												            <td>Width</td>
												            <td>Quantity</td>
												            <td>Rate</td>
												            <td>Amount</td>
												            <td>Action</td>
												        </tr>
												        <c:set value="0" var="counter"></c:set>
												        <c:if test="${optype eq 'create'}">
												        	<tr>
											    		  	<td>1</td>
												        	<form:hidden path="invoiceItems[0].id" />
												           	<td>
												           		<form:select path="invoiceItems[0].productType" id="selproducttype0" items="${lstproducttype}" placeholder="Product Type" class="form-control form-control-success" onchange="showHideFields(0)"/>
												            </td>
												            <td>
												            	<form:input path="invoiceItems[0].particulars" id="particulars0" name="particulars" type="text" placeholder="Particulars" class="form-control form-control-success"/>
												            </td>
												            <td>
												            	<form:select path="invoiceItems[0].type" id="selsubtype0" items="${lstsubtype}" placeholder="Type" class="form-control form-control-success" disabled="true" onchange="showHideFields(0)"/>
												            </td>
												            <td>
												            	<form:input path="invoiceItems[0].side" id="side0" name="side" type="text" placeholder="Side" class="form-control form-control-success" disabled="true"/>
												            </td>
												            <td>
												            	<form:input path="invoiceItems[0].length" id="length0" name="length" type="text" placeholder="Length" class="form-control form-control-success" disabled="true"/>
												            </td>
												            <td>
												            	<form:input path="invoiceItems[0].width" id="width0" name="width" type="text" placeholder="Width" class="form-control form-control-success" disabled="true"/>
												            </td>
												            <td>
												            	<form:input path="invoiceItems[0].quantity" id="quantity0" name="quantity" type="text" placeholder="Quantity" class="form-control form-control-success" onblur="calculateAmount(0)"/>
												            </td>
												            <td>
												            	<form:input path="invoiceItems[0].rate" id="rate0" name="rate" type="text" placeholder="Rate" class="form-control form-control-success" onblur="calculateAmount(0)"/>
												            </td>
												            <td>
												            	<form:input path="invoiceItems[0].amount" id="amount0" name="amount" type="text" placeholder="Amount" class="form-control form-control-success amount" readonly="true" onblur="calculateTotal(0)"/>
												            </td>
												            <td>
												            	<a class="deleteRow">
																	<input type="button" class="btn btn-md btn-danger " value="X">
																</a>
												            </td>
												         </tr>
												    </c:if>
												        
													</thead>
													<tbody>
												    	<c:if test="${optype eq 'edit'}">
												    		<c:forEach items="${invoiceDTO.invoiceItems}" var="invoiceItem" varStatus="inv">
												    		<tr>
												    		  	<td>${inv.index+1}</td>
													        	<form:hidden path="invoiceItems[${inv.index}].id" />
													           	<td>
													          	 	<spring:bind path="invoiceItems[${inv.index}].productType">
													           			<form:select path="invoiceItems[${inv.index}].productType" id="selproducttype${inv.index}" items="${lstproducttype}" placeholder="Product Type" class="form-control form-control-success"  onchange="showHideFields(${inv.index})"/>
            															<form:errors path="invoiceItems[${inv.index}].productType" cssClass="error"></form:errors>
																	</spring:bind>		
													            </td>
													            <td>
													          	 	<spring:bind path="invoiceItems[${inv.index}].particulars">
													            		<form:input path="invoiceItems[${inv.index}].particulars" id="particulars${inv.index}" name="particulars" type="text" placeholder="Particulars" class="form-control form-control-success"/>
            															<form:errors path="invoiceItems[${inv.index}].particulars" cssClass="error"></form:errors>
																	</spring:bind>													           
																</td>
													            <td>
													          	 	<spring:bind path="invoiceItems[${inv.index}].type">
													            	<form:select path="invoiceItems[${inv.index}].type" id="selsubtype${inv.index}" items="${lstsubtype}" placeholder="Type" class="form-control form-control-success" onchange="showHideFields(${inv.index})"/>
            															<form:errors path="invoiceItems[${inv.index}].type" cssClass="error"></form:errors>
																	</spring:bind>														            
																</td>
													            <td>
													          	 	<spring:bind path="invoiceItems[${inv.index}].side">
													            		<form:input path="invoiceItems[${inv.index}].side" id="side${inv.index}" name="side" type="text" placeholder="Side" class="form-control form-control-success"/>
            															<form:errors path="invoiceItems[${inv.index}].side" cssClass="error"></form:errors>
																	</spring:bind>														            
																</td>
													            <td>
													          	 	<spring:bind path="invoiceItems[${inv.index}].length">
													            		<form:input path="invoiceItems[${inv.index}].length" id="length${inv.index}" name="length" type="text" placeholder="Length" class="form-control form-control-success"/>
            															<form:errors path="invoiceItems[${inv.index}].length" cssClass="error"></form:errors>
																	</spring:bind>														            
																</td>
													            <td>
													          	 	<spring:bind path="invoiceItems[${inv.index}].width">
													            		<form:input path="invoiceItems[${inv.index}].width" id="width${inv.index}" name="width" type="text" placeholder="Width" class="form-control form-control-success"/>
            															<form:errors path="invoiceItems[${inv.index}].width" cssClass="error"></form:errors>
																	</spring:bind>														           
																</td>
													            <td>
													          	 	<spring:bind path="invoiceItems[${inv.index}].quantity">
													            		<form:input path="invoiceItems[${inv.index}].quantity" id="quantity${inv.index}" name="quantity" type="text" placeholder="Quantity" class="form-control form-control-success" onblur="calculateAmount(${inv.index})"/>
            															<form:errors path="invoiceItems[${inv.index}].quantity" cssClass="error"></form:errors>
																	</spring:bind>														            
																</td>
													            <td>
													          	 	<spring:bind path="invoiceItems[${inv.index}].rate">
													            		<form:input path="invoiceItems[${inv.index}].rate" id="rate${inv.index}" name="rate" type="text" placeholder="Rate" class="form-control form-control-success" onblur="calculateAmount(${inv.index})"/>
            															<form:errors path="invoiceItems[${inv.index}].rate" cssClass="error"></form:errors>
																	</spring:bind>														            
																</td>
													            <td>
													          	 	<spring:bind path="invoiceItems[${inv.index}].amount">
													            		<form:input path="invoiceItems[${inv.index}].amount" id="amount${inv.index}" name="amount" type="text" placeholder="Amount"  class="form-control form-control-success amount" onblur="calculateTotal()"/>
            															<form:errors path="invoiceItems[${inv.index}].amount" cssClass="error"></form:errors>
																	</spring:bind>														            
																</td>
													            <td>
													            	<a class="deleteRow">
																		<input type="button" class="btn btn-md btn-danger " value="X">
																	</a>
													            </td>
													         </tr>
													       <c:set value="${inv.index}" var="counter"></c:set>
													    </c:forEach>
												    	</c:if>
												    	<input type="hidden" id="counter" name="counter" value="${counter}"/>
												    </tbody>
												   	<tfoot>
												        <tr style="background-color : #ccc;">
												            <td colspan="9" style="text-align: left;">
												                Sub total :
												            </td>
												            <td>
												            	<form:input path="totalamount" id="totalamount" name="totalamount" type="text" placeholder="Discount" class="form-control form-control-success" onblur="calculateTotal()" readonly="true"/>
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
                                            	<spring:bind path="discounttype">
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
                                                	<form:input path="payableTotal" id="payableTotal" type="text" placeholder="RoundOff" class="form-control form-control-success" readonly="true"/>
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