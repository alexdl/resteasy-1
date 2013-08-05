<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="review" class="tab-pane">

    <legend>Review the information you provided...</legend>

    <table class="table table-condensed">
        <tr>
            <td>Account<br>Information</td>
            <td class="pull-left">
                <strong id="reviewUsername">UserName</strong><br>
                <span id="reviewEmail">sean.staley@gmail.com</span>
            </td>
        </tr>
        <tr>
            <td>Personal<br>Information</td>
            <td class="pull-left">
                <strong id="reviewFullName">Sean M Staley</strong>
                <address id="reviewPersonalAddress">Stuff</address>
            </td>
        </tr>
        <tr>
            <td>Organization<br>Information</td>
            <td class="pull-left">
                <strong id="reviewOrgName">StaleyLabs</strong>
                <address id="reviewOrgAddress">Stuff</address>
            </td>
        </tr>
    </table>

    <legend>Promotional Codes</legend>

    <table id="promotionalTable" class="table">
        <tr>
            <td><label>Closed Beta! Must enter a code to create a username!</label></td>
            <td>
                <input id="promotionalCode" type="text" class="input-medium" placeholder="Enter Promo Code"/>
            </td>
            <td>
                <button id="promotionalCodeButton" type="button" class="btn btn-mini btn-info">Check Code</button>
            </td>
        </tr>
    </table>

    <div class="form-actions">
        <form:button id="createUserButton" type="submit" class="btn btn-primary disabled"
                     disabled="disabled">Create</form:button>
        <form:button id="cancelRegistrationButton" type="reset" class="btn btn-warn">Discard Form</form:button>
    </div>

</div>