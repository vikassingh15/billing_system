<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="side-navbar">
    <!-- Sidebar Header-->
    <div class="sidebar-header d-flex align-items-center">
        <div class="avatar"><img src="<c:url value='/resources/img/user.png'/>" alt="..." class="img-fluid rounded-circle"></div>
        <div class="title">
            <h1 class="h4">Lavesh K.</h1>
        </div>
    </div>
    <span class="heading">Main</span>
    <ul class="list-unstyled">
        <li <c:if test="${active == 'dashboard'}">class="active"</c:if>><a href="<c:url value="/admin/"/>"> <i class="icon-home"></i>Dashboard </a></li>
        <li <c:if test="${active == 'invoices'}">class="active"</c:if>><a href="<c:url value="/invoice/list"/>"> <i class="fa fa-money"></i>Invoices </a></li>
        <li <c:if test="${active == 'purchaseOrder'}">class="active"</c:if>><a href="<c:url value="/po/list"/>"> <i class="fa fa-shopping-cart"></i>Purchases </a></li>
    	<li <c:if test="${active == 'receipt'}">class="active"</c:if>><a href="<c:url value="/receipt/list"/>"> <i class="fa fa-copy"></i>Receipts</a></li>
    	<li <c:if test="${active == 'client'}">class="active"</c:if>><a href="<c:url value="/vendor/list"/>"> <i class="fa fa-bar-chart"></i>Vendors</a></li>
    	<li <c:if test="${active == 'customers'}">class="active"</c:if>><a href="<c:url value="/customers"/>"> <i class="fa fa-users"></i>Customers </a></li>
    	<li <c:if test="${active == 'Reports'}">class="active"</c:if>><a href="<c:url value="#"/>"> <i class="fa fa-print"></i>Reports</a></li>
    </ul>
</nav>