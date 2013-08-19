<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<div id="properties" class="tab-pane active">
    <div id="adminPropertyInformation">
        <h3>Analyze and Set System Properties.</h3>
    </div>

    <div id="currentPropertyTable">
        <table id="propertyTable" class="table table-striped table-condensed">
            <tr>
                <th>Key</th>
                <th>Value</th>
                <th></th>
            </tr>
            <tr>
                <td>resteasy.awesome.leader</td>
                <td>Sean Staley</td>
                <td>
                    <button type="button" class="btn btn-default btn-small">
                        <span class="icon-pencil"></span>
                    </button>
                </td>
            </tr>

        </table>
    </div>

    <div id="applyNewProperty">
        <form id="addPropertyForm" autocomplete="off">
            <fieldset>
                <legend>Apply New Property</legend>
                <label>
                    <input class="input-xlarge" type="text" placeholder="New Property Key">
                </label>
                <label>
                    <textarea rows="3" maxlength="255" placeholder="Property Value"></textarea>
                </label>
                <button id="savePropertyBtn" class="btn btn-primary">Save</button>
            </fieldset>
        </form>
    </div>

    <div class="progress progress-striped active hidden">
        <div class="bar" style="width: 100%;"></div>
    </div>

</div>


