/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 8/4/13
 * Time: 9:14 PM
 * To change this template use File | Settings | File Templates.
 */
var USER_PAGE_NUMBER = 1;


$(document).ready(function () {
    $('#tabs').tab();

    $('#userTab').on('click', function () {
        var url = "/api/user/" + USER_PAGE_NUMBER;

        $.ajax({
            url: url,
            dataType: 'json',
            timeout: 5000,
            type: 'GET',
            success: function (msg) {
                if (msg) {
                    for (var i in msg) {
                        var user = msg[i];

                        var username = user.username;
                        var fullName = user.firstName + ' ' + user.lastName;

                        $('#userTable tr:last').after(
                            '<tr><td>' + user.id + '</td>' +
                                '<td><a href="/admin/user/"' + username + '>' + username + '</a></td>' +
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
});