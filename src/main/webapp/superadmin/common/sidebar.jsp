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
        <li <c:if test="${active == 'dashboard'}">class="active"</c:if>><a href="<c:url value="/superadmin/"/>"> <i class="icon-home"></i>Dashboard </a></li>
        <li <c:if test="${active == 'companies'}">class="active"</c:if>><a href="<c:url value="/superadmin/companies"/>"> <i class="fa fa-building-o"></i>Companies </a></li>
        <li <c:if test="${active == 'users'}">class="active"</c:if>><a href="<c:url value="/superadmin/users"/>"> <i class="fa fa-user-o"></i>Users </a></li>
    </ul>
</nav>