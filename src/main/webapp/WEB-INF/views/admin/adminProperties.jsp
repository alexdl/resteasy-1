<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<div id="properties" class="tab-pane">
    <div class="page-header">
        <h1>Admin Console
            <small>System Properties</small>
        </h1>
    </div>

    <div id="adminPropertyInformation">
        <p>User information and profile pages can be found here.</p>
    </div>

    <c:choose>
        <c:when test="${!empty systemProperties}">
            <table id="propertyTable" class="table table-striped table-condensed">
                <tr>
                    <th>Key</th>
                    <th>Value</th>
                    <th></th>
                </tr>
                <c:forEach var="property" items="${systemProperties}">
                    <tr>
                        <td><c:out value="${property.key}"/></td>
                        <td><c:out value="${property.value}"/></td>
                        <td>Edit</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <b>No Properties</b>
        </c:otherwise>
    </c:choose>

</div>