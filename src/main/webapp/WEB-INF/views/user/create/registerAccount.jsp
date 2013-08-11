<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <form:input id="usernameInput" cssClass="requiredField" type="text" placeholder="Your Username"
                            path="username"/>
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
                <form:input id="passwordInput" type="password" path="password" placeholder="Example: $3Cret"/>
            </td>
            <td>
                <span id="passwordError"></span>
            </td>
        </tr>
        <tr>
            <td>
                <label class="text-left"><b class="icon-asterisk"></b>Verify Password</label>
            </td>
            <td>
                <input id="verifyPasswordInput" type="password" class="requiredField" placeholder="Example: $3Cret"/>
            </td>
            <td id="passwordSame"></td>
        </tr>
    </table>
</div>