<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<div id="createPeople" class="tab-pane">

    <legend id="adminCreatePeopleInfo">
        <p>Create a user to be used in the application.</p>
    </legend>

    <form role="form">
        <p class="lead">User Information</p>

        <div class="form-group">
            <label for="usernameInput">Username</label>
            <input type="text" class="form-control" id="usernameInput" placeholder="Enter username">
        </div>

        <div class="form-group">
            <label for="passwordInput">Password</label>
            <input type="password" class="form-control" id="passwordInput" placeholder="Enter password">
        </div>

        <div class="form-group form-inline">
            <label for="firstNameInput" class="sr-only">Name</label> <br>
            <input type="text" class="form-control" id="firstNameInput" placeholder="First Name">

            <label for="middleNameInput" class="sr-only hidden"></label>
            <input type="text" class="form-control" id="middleNameInput" placeholder="Middle Name">

            <label for="lastNameInput" class="sr-only"></label>
            <input type="text" class="form-control" id="lastNameInput" placeholder="Last Name">
        </div>
        <br>

        <p class="lead">Address Information</p>

        <div class="form-group form-inline">
            <label for="addressLine1Input"></label>
            <input type="text" class="form-control" id="addressLine1Input" placeholder="Address Line 1">

            <label for="addressLine2Input"></label>
            <input type="text" class="form-control" id="addressLine2Input" placeholder="Address Line 2">

            <br><br>

            <label for="cityInput"></label>
            <input type="text" class="form-control" id="cityInput" placeholder="City">

            <label for="stateInput"></label>
            <input type="text" class="form-control" id="stateInput" placeholder="State Code">

            <label for="postalCodeInput"></label>
            <input type="text" class="form-control" id="postalCodeInput" placeholder="Postal Code">
        </div>
        <br>

        <p class="lead">Contact Information</p>

        <div class="form-group form-inline">
            <label for="personalPhoneInput">Personal Phone</label>
            <input type="text" class="form-control" id="personalPhoneInput" placeholder="5038919363">

            <label for="emailInput">Email</label>
            <input type="email" class="form-control" id="emailInput" placeholder="sean@resteasy.com">
        </div>
        <br>

        <p class="lead">User Organization</p>
        <select class="form-control">
            <option>Add New Organization</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
        </select>
        <br><br>

        <a id="createUserButton" href="#" class="btn btn-primary btn-small active" role="button">Create</a>
    </form>
</div>