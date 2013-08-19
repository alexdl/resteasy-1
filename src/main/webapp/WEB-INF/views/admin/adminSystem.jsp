<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<div id="system" class="tab-pane">
    <div class="page-header">
        <h1>Admin Console
            <small>System Configuration</small>
        </h1>
    </div>

    <div id="systemTabs" class="tabs tabbable tabs-left">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#properties" data-toggle="tab">Properties</a></li>
            <li><a href="#cache" data-toggle="tab">Caches</a></li>
            <li><a href="#mail" data-toggle="tab">Email Servers</a></li>
        </ul>
        <div class="tab-content">

            <c:import url="system/systemProperties.jsp"/>

            <c:import url="system/systemMail.jsp"/>

            <c:import url="system/systemCache.jsp"/>
        </div>
    </div>
</div>