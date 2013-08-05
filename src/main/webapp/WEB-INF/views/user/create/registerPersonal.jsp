<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
