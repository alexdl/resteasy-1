<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="../static/header.jsp"/>
<body class="admin">
<div class="container">

    <c:import url="static/adminHeader.jsp"/>

    <img src="..." alt="..." class="img-circle">

    <legend id="name" class="text-right">
        <c:out value="${user.lastName}"/>, <c:out value="${user.firstName}"/> <c:out value="${user.middleName}"/>
    </legend>

    <form>
        <div class="form-inline">
            <label>User ID: </label>
            <span id="userId"><c:out value="${user.id}"/></span>
        </div>

        <div class="form-inline">
            <label for="username">Username: </label>
            <input id="username" class="uneditable-input" placeholder="<c:out value="${user.username}"/>" disabled>
        </div>

        <div class="form-inline">
            <label>Creation Date: </label>
            <fmt:formatDate value="${creationDate}" pattern="MM/dd/yyyy HH:mm"/>
        </div>

        <div class="form-inline">
            <label>Last Logged In: </label>
            <fmt:formatDate value="${lastLoggedIn}" pattern="MM/dd/yyyy HH:mm"/>
        </div>
    </form>


    <button id="deleteUserBtn" type="button" class="btn btn-danger">Delete User</button>

</div>
<!-- /container -->
<c:import url="../static/jsscripts.jsp"/>
<script type="text/javascript" src='<c:url value="/resources/js/admin/admin.js"/>'></script>
</body>
</html>