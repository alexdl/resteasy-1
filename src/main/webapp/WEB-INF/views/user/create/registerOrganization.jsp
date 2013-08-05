<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- Organization Information Tabs --%>
<div id="organization" class="tab-pane">

    <legend>Tell us some about your company...</legend>

    <table id="organizationTable" class="table">
        <tr>
            <td>
                <form:label path="organizationName"><b class="icon-asterisk"></b>Organization Name</form:label>
            </td>
            <td>
                <form:input id="organizationName" type="text" path="organizationName" placeholder="StaleyLabs"
                            autocomplete="false"/>
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