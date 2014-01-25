<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<div id="users" class="tab-pane">
    <div class="page-header">
        <h1>Admin Console
            <small>Users</small>
        </h1>
    </div>

    <div id="systemTabs" class="tabs tabbable tabs-left">
        <ul class="nav nav-tabs">
            <li class="active"><a id="peopleSubTab" href="#people" data-toggle="tab">Users</a></li>
            <li><a href="#organizations" data-toggle="tab">Organizations</a></li>
            <li><a href="#createPeople" data-toggle="tab">Create User</a></li>
            <li><a href="#createOrganization" data-toggle="tab">Create Organization</a></li>
        </ul>
        <div class="tab-content">

            <c:import url="users/userPeople.jsp"/>

            <c:import url="users/userOrganizations.jsp"/>

            <c:import url="users/userPeopleCreate.jsp"/>

            <c:import url="users/userOrganizationCreate.jsp"/>
        </div>
    </div>
</div>
