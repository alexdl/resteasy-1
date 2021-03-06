<%--
  Created by IntelliJ IDEA.
  User: sean
  Date: 7/6/13
  Time: 11:35 AM
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../static/header.jsp"/>
<body>
<c:import url="../static/navbar.jsp" />

<div class="container">

    <c:out value="${user.username}" />
    <c:out value="${user.firstName}" />
    <c:out value="${user.lastName}" />
    <c:out value="${user.emailAddress}" />

</div> <!-- /container -->

<c:import url="../static/jsscripts.jsp"/>
</body>
</html>