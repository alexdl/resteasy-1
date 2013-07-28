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
<form:form id="createUserForm" commandName="registering-user-entity" action="/api/user/create" method="POST">

<ul class="nav nav-tabs" id="menuTabs">
    <li class="active"><a href="#account" data-toggle="tab">Account Information</a></li>
    <li><a href="#personal" data-toggle="tab">Personal Information</a></li>
    <li><a href="#organization" data-toggle="tab">Organization Information</a></li>
    <li><a href="#review" data-toggle="tab">Review Information</a></li>
</ul>

<div class="tab-content">
    <%-- Account Information Tabs --%>
<div id="account" class="tab-pane active">
    <legend>Tell us about your account...</legend>

    <table id="accountTable" class="table">
        <tr id="usernameSpeech">
            <td colspan="3">
                <p class="text-info">
                    Your username is essential to use the application. Your username should start with a
                    letter or number, and should not be longer than 16 characters.
                </p>
            </td>
        </tr>
        <tr id="createUsernameRow">
            <td>
                <form:label class="text-left" path="username">
                    <b class="icon-asterisk"></b>Username
                </form:label>
            </td>
            <td>
                <form:input id="usernameInput" type="text" placeholder="Your Username" path="username"/>
            </td>
            <td id="usernameExists"></td>
        </tr>

        <tr>
            <td><%-- Emptyness --%></td>
        </tr>

        <tr id="passwordSpeech">
            <td colspan="3">
                <p class="text-info">
                    Your password is case-sensitive and must contain at least 1 integer, 1 special
                    character, 1 letter, and must be between 8 and 16 characters in length.
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <form:label class="text-left" path="password">
                    <b class="icon-asterisk"></b>Password&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </form:label>
            </td>
            <td>
                <form:input id="passwordInput" type="password" path="password"
                            placeholder="Example: $3Cret"/>
            </td>
            <td>
                <span id="passwordError"></span>
            </td>
        </tr>
        <tr>
            <td>
                <label class="text-left"><b class="icon-asterisk">
                </b>Verify Password
                </label>
            </td>
            <td>
                <input id="verifyPasswordInput" type="password" placeholder="Example: $3Cret"/>
            </td>
        </tr>
    </table>
</div>

    <%-- Personal Information Tabs --%>
<div id="personal" class="tab-pane">

    <legend>Tell us a little about you...</legend>

    <table id="personalTable" class="table">
        <tr id="personalSpeech">
            <td colspan="3">
                <p class="text-info">
                    Information about you is critical to your profile and experience.
                </p>
            </td>
        </tr>
        <tr id="createNameRow">
            <td>
                <form:label path="firstName"><b class="icon-asterisk"></b>Your Name</form:label>
            </td>
            <td colspan="2">
                <form:input id="firstNameInput" path="firstName" type="text" cssClass="input-small"
                            placeholder="First Name"/>
                <form:input id="middleNameInput" path="middleName" type="text" cssClass="input-small"
                            placeholder="Middle Name"/>
                <form:input id="lastNameInput" path="lastName" type="text" cssClass="input-small"
                            placeholder="Last Name"/>
            </td>
        </tr>

        <tr>
            <td><%-- Emptyness --%></td>
        </tr>

        <tr id="contactSpeech">
            <td colspan="3">
                <p class="text-info">
                    Your contact information is all about how we can contact you when needed.
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="email"><b class="icon-asterisk"></b>Email</form:label>
            </td>
            <td>
                <form:input id="emailInput" path="email" type="text" placeholder="sean@staleylabs.com"/>
            </td>
            <td>
                <span id="emailError"></span>
            </td>
        </tr>
        <tr>
            <td>
                <label><b class="icon-asterisk"></b>Address Information</label>
            </td>
            <td colspan="2">
                <form:input id="addressLine1" type="text" autocomplete="off" class="input-xlarge"
                            path="addressLine1" data-provide="typeahead"
                            placeholder="Address Line 1"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td colspan="2">
                <form:input id="addressLine2" type="text" autocomplete="off" class="input-xlarge"
                            path="addressLine2" placeholder="Address Line 2"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td colspan="2">
                <form:input id="cityName" type="text" autocomplete="off" class="input-large"
                            path="cityName" placeholder="City"/>
                <form:input id="stateCode" type="text" autocomplete="off" class="input-small"
                            path="stateCode" maxlength="2" placeholder="State"/>
                <form:input id="postalCode" type="text" autocomplete="off" class="input-small postalCodeAutocomplete"
                            path="postalCode" maxlength="5" placeholder="Zip Code"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="personalPhoneNumber" class="align-middle">Personal Phone</form:label>
            </td>
            <td>
                <form:input id="phoneNumberInput" path="personalPhoneNumber" class="input-medium" size="15"
                            placeholder="1 (123) 456-7890"/>
            </td>
        </tr>
    </table>
</div>

    <%-- Organization Information Tabs --%>
<div id="organization" class="tab-pane">

    <legend>Tell us some about your company...</legend>

    <table id="organizationTable" class="table">
        <tr>
            <td>
                <form:label path="organizationName"><b class="icon-asterisk"></b>Organization Name</form:label>
            </td>
            <td>
                <form:input id="organizationName" type="text" path="organizationName" placeholder="StaleyLabs"/>
            </td>
        </tr>
        <tr>
            <td>
                <label><b class="icon-asterisk"></b>Address Information</label>
            </td>
            <td colspan="2">
                <form:input id="orgAddressLine1" type="text" autocomplete="off" class="input-xlarge"
                            path="organizationAddressLine1" data-provide="typeahead"
                            placeholder="1100 N Anchor Way"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td colspan="2">
                <form:input id="orgAddressLine2" type="text" autocomplete="off" class="input-xlarge"
                            path="organizationAddressLine2" placeholder="Suite 400"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td colspan="2">
                <form:input id="orgCityName" type="text" autocomplete="off" class="input-large"
                            path="organizationCityName" placeholder="New Martinsville"/>
                <form:input id="orgStateCode" type="text" autocomplete="off" class="input-small"
                            path="organizationStateCode" maxlength="2" placeholder="WV"/>
                <form:input id="orgZipCode" type="text" autocomplete="off" class="input-small postalCodeAutocomplete"
                            path="organizationPostalCode" maxlength="5" placeholder="26155"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="organizationPhoneNumber" class="align-middle">Personal Phone</form:label>
            </td>
            <td>
                <form:input id="phoneNumberInput" path="organizationPhoneNumber" class="input-medium" size="15"
                            placeholder="1 (123) 456-7890"/>
            </td>
        </tr>
    </table>
</div>

<div id="review" class="tab-pane">

    <legend>Review the information provided</legend>

    <table id="reviewTable" class="table">
        <tr>
            <td><label>Closed Beta! Must enter a code to create a username!</label></td>
            <td>
                <input id="promotionalCode" type="text" class="input-medium" placeholder="Enter Promo Code"/>
            </td>
            <td>
                <button id="promotionalCodeButton" class="btn btn-mini btn-info">Check Code</button>
            </td>
        </tr>
    </table>

    <div class="form-actions">
        <form:button id="createUserButton" type="submit" class="btn btn-primary disabled" disabled="disabled">Create</form:button>
        <form:button id="cancelRegistrationButton" type="reset" class="btn btn-warn">Discard Form</form:button>
    </div>

</div>
</div>
</form:form>
</div>

<%-- Warning message --%>
<div id="createWarning" class="alert close">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Warning!</strong> Best check yo self, you're not looking too good.
</div>
<!-- /container -->


<c:import url="../static/jsscripts.jsp"/>
<script type="text/javascript" src='<c:url value="/resources/js/user/create-user.js"/>'></script>
</body>
</html>