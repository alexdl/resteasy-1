<%--
  Created by IntelliJ IDEA.
  User: sean
  Date: 8/4/13
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../static/header.jsp"/>
<body class="admin">
<div class="container">

    <div id="tabs" class="navbar navbar-static-top">
        <div class="navbar-inner">
            <a class="brand">Admin Console</a>
            <ul class="nav">
                <li class="active"><a href="#overview" data-toggle="tab">Overview</a></li>
                <li><a href="#system" data-toggle="tab">System</a></li>
                <li id="userTab"><a href="#users" data-toggle="tab">Users</a></li>
                <li><a href="#analytics" data-toggle="tab">Analytics</a></li>
            </ul>
        </div>
    </div>

    <div class="tab-content">
        <c:import url='adminOverview.jsp'/>

        <c:import url='adminUsers.jsp'/>
    </div>

</div>
<!-- /container -->
<c:import url="../static/jsscripts.jsp"/>
<script type="text/javascript" src='<c:url value="/resources/js/admin/admin.js"/>'></script>
</body>
</html>