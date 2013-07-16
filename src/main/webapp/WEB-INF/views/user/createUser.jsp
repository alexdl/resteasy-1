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
        <form:form id="createUserForm" commandName="user-entity" action="/api/user/create" method="POST">

            <ul class="nav nav-tabs" id="menuTabs">
                <li class="active"><a href="#account" data-toggle="tab">Account Information</a></li>
                <li><a href="#personal" data-toggle="tab">Personal Information</a></li>
                <li><a href="#organization" data-toggle="tab">Organization Information</a></li>
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
                                <form:label class="text-center" path="username">
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
                                <form:label class="text-center" path="password">
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
                                <label class="text-center"><b class="icon-asterisk">
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
                    <form:label path="firstName"><b class="icon-asterisk"></b>First Name</form:label>
                    <form:input id="firstNameInput" path="firstName" type="text" placeholder="What's your first name?"/>

                    <form:label path="lastName"><b class="icon-asterisk"></b>Last Name</form:label>
                    <form:input path="lastName" id="lastNameInput" type="text" placeholder="Last name?"/>

                    <form:label path="emailAddress"><b class="icon-asterisk"></b>Email</form:label>
                    <form:input id="emailInput" path="emailAddress" type="text" placeholder="Email Address?"/>

                    <button data-activate="#organization" class="btn btn-primary">Next</button>
                </div>

                    <%-- Organization Information Tabs --%>
                <div id="organization" class="tab-pane">
                    <label><b class="icon-asterisk"></b>Email</label>
                    <input id="emailInput" type="text" placeholder="Email Address?">

                    <button type="submit" class="btn btn-primary">Create!</button>
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