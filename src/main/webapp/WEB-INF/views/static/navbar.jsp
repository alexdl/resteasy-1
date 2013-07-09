<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Navigation Bar for the application. This will be displayed on all of the pages.
  User: sean
  Date: 7/6/13
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<security:authentication var="username" property="principal.username"/>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="#">RestEasy::beta</a>

            <div class="nav-collapse collapse navbar-text pull-right dropdown" id="usermenu">
                <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                    <c:out value="${username}"/><b class="caret"></b>
                </a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                    <li><a href="/user/<c:out value="${username}" />">Profile Settings</a></li>
                    <li class="divider"></li>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li><a href="/admin">Admin Console</a></li>
                    </security:authorize>
                    <li><a href="/logout">Logoff</a></li>
                </ul>
            </div>
            <ul class="nav">
                <li class="active"><a href="/welcome">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
        </div>
    </div>
    <!--/.nav-collapse -->
</div>