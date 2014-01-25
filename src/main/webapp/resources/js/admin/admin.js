/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 8/4/13
 * Time: 9:14 PM
 * To change this template use File | Settings | File Templates.
 */
var USER_PAGE_NUMBER = 1;

$(document).ready(function () {
    $('.tabs').tab();

    $('#userTab, #peopleSubTab').on('click', function () {
        var url = "/api/user/" + USER_PAGE_NUMBER;

        $.ajax({
            url: url,
            dataType: 'json',
            timeout: 5000,
            type: 'GET',
            success: function (msg) {
                $('#userTable tbody').empty();

                if (msg) {
                    for (var i in msg) {
                        var user = msg[i];

                        var username = user.username;
                        var fullName = user.firstName + ' ' + user.lastName;

                        $('#userTable').find('tbody:last').append(
                            '<tr><td>' + user.id + '</td>' +
                                '<td><a href="/admin/user/' + username + '">' + username + '</a></td>' +
                                '<td>' + fullName + '</td>' +
                                '<td>' + user.emailAddress + '</td>' +
                                '<td>' + user.enabled + '</td>' +
                                '</tr>'
                        );
                    }
                }
            }
        });
    });

    $('#savePropertyBtn').on('click', function () {
        var url = "/admin/applySystemProperty";

        $('#propertyProgressBar').removeClass('hidden');

        $.ajax({
            url: url,
            dataType: 'json',
            data: {"propertyKey": $('#propertyKey').val(),
                "propertyValue": $('#propertyValue').val()},
            timeout: 5000,
            type: 'POST',
            success: function (msg) {
                if (msg) {

                }
            }
        });
    });

    $('#createUserButton').click(function () {
        var registeringUser = {
            username: $('#usernameInput').val(),
            password: $('#passwordInput').val(),
            firstName: $('#firstNameInput').val(),
            middleName: $('#middleNameInput').val(),
            lastName: $('#lastNameInput').val(),
            addressLine1: $('#addressLine1Input').val(),
            addressLine2: $('#addressLine2Input').val(),
            cityName: $('#cityInput').val(),
            stateCode: $('#stateInput').val(),
            postalCode: $('#postalCodeInput').val(),
            personalPhoneNumber: $('#personalPhoneInput').val(),
            email: $('#emailInput').val()

        };

        console.log(registeringUser);

        $.ajax({
            url: "/api/user/create",
            contentType: 'application/json',
            dataType: 'json',
            timeout: 5000,
            type: 'POST',
            data: JSON.stringify(registeringUser),
            success: function (msg) {
                console.log(msg);
            },
            error: function (msg) {
                console.log(msg);
            }
        });
    });

    $('#deleteUserBtn').click(function () {
        if (confirm("You really want to do this?")) {
            var userId = $('#userId').text();

            $.ajax({
                url: "/api/user/delete/" + userId,
                timeout: 5000,
                type: 'DELETE',
                success: function (msg) {
                    alert("The user has been deleted.");
                    document.location.href = '/admin';
                }
            });
        }
    })
});