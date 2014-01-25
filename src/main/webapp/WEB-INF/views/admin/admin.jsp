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

    <c:import url="static/adminHeader.jsp"/>

    <div class="tab-content">
        <c:import url='adminOverview.jsp'/>

        <c:import url="adminSystem.jsp"/>

        <c:import url='adminUsers.jsp'/>
    </div>

</div>
<!-- /container -->
<c:import url="../static/jsscripts.jsp"/>
<script type="text/javascript" src='<c:url value="/resources/js/admin/admin.js"/>'></script>
</body>
</html>