<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sean
  Date: 8/4/13
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="overview" class="tab-pane active">
    <div class="page-header">
        <h1>Admin Console
            <small>Overview</small>
        </h1>
    </div>
    <div id="systemDiagnostics">

        <legend>General Information</legend>
        <table class="table table-bordered">
            <tr class="info">
                <td><c:out value="${uptimeInformation}"/></td>
            </tr>
        </table>

        <legend>Hardware Information</legend>
        <table class="table table-bordered">
            <c:forEach var="info" items="${systemInformation}">
                <tr class="info">
                    <td><c:out value="${info.key}"/></td>
                    <td><c:out value="${info.value}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>