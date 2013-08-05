<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="../static/header.jsp"/>
<body class="public">
<div class="container">
<div id="createInformation" class="page-header">
    <h1>Create Your Account</h1>

    <p class="lead">
        Take the first step in letting us help you manage your business. During this creation process, you will be
        asked to provide information about yourself and your organization. In order to provide you with the optimal
        experience, we encourage you to fill out all the fields on this form.
    </p>
</div>

<div class="tabbable">
<%-- Create User Form Start --%>
<form:form id="createUserForm" commandName="registering-user-entity" action="/user/create" method="POST">

<ul class="nav nav-tabs" id="menuTabs">
    <li class="active"><a href="#account" data-toggle="tab">Account Information</a></li>
    <li><a href="#personal" data-toggle="tab">Personal Information</a></li>
    <li><a href="#organization" data-toggle="tab">Organization Information</a></li>
    <li><a href="#review" data-toggle="tab">Review Information</a></li>
</ul>

<div class="tab-content">
    <c:import url='create/registerAccount.jsp' />

    <c:import url='create/registerPersonal.jsp' />

    <c:import url='create/registerOrganization.jsp' />

    <c:import url='create/registerReview.jsp' />
</div>
</form:form>

<div class="btn-toolbar clearfix">
    <div class="btn-group pull-right _tabs_navigation" data-toggle="buttons-radio">
        <a class="btn btn-small btn-info" href="#">
            <i class="icon-arrow-left icon-white"></i>
        </a>
        <a class="btn btn-small btn-info" href="#">
            <i class="icon-arrow-right icon-white"></i>
        </a>
    </div>
</div>
</div>

<%-- Warning message --%>
<div id="createWarning" class="alert close">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Warning!</strong> Best check yo self, you're not looking too good.
</div>

<div class="pull-right">
    <img src="<c:url value='/resources/images/poweredbygoogle.gif'/>"/>
</div>

<c:import url="../static/jsscripts.jsp"/>
<script type="text/javascript" src='<c:url value="/resources/js/user/create-user.js"/>'></script>
</body>
</html>