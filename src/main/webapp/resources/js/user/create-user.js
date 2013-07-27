/**
 * Javascript file that is used on the page dealing with the creation of a given user.
 *
 * @author Sean M. Staley
 * @version 1.0
 */

// Constants
var VALID_USERNAME_REGEX = /^[a-z0-9_-]{3,16}$/;

var VALID_PASSWORD_REGEX = /^[a-z0-9_-]{6,18}$/;

var geocoder = new google.maps.Geocoder();

var GOOGLE_API = "AIzaSyBQZ0_uW5KIx_PQNoicqOBuTl7KMSltebs";

// jQuery is ready to load once the page is loaded up.
$(document).ready(function () {

    /**
     * Simple method that will replace whatever is in the given validationNode as an error or success.
     *
     * @param validationNode The DOM node that we interact with to place the result.
     * @param isError <code>true</code> if an error message is requested. Otherwise, a good message will be used.
     */
    function validationTextRender(validationNode, isError) {
        if (isError) {
            validationNode.replaceWith('<span><b class="icon-ban-circle"></b>&nbsp;Woops! Looks like your input was invalid.</span>');
        }
        else {
            validationNode.replaceWith('<span><b class="icon-ok-circle"></b>&nbsp;Looks good!</span>');
        }
    }

    /**
     * Method used to check and see if a requested username exists in the application already.
     *
     * @param username String representation of the requested username.
     * @param usernameIssuesNode DOM node where callback success or failure can be placed on the page.
     */
    function userExistCheck(username, usernameIssuesNode) {
        var url = '/user/taken/' + username;

        $.ajax({
            url: url,
            dataType: 'json',
            timeout: 5000,
            type: 'GET',
            success: function (msg) {
                if (msg) {
                    validationTextRender(usernameIssuesNode, true);
                } else {
                    validationTextRender(usernameIssuesNode, false);
                }
            }
        });
    }

    function applyToAddress(addressArray, currentNode) {
        var addressLine1 = addressArray[0].trim();
        var city = addressArray[1].trim();
        var state = addressArray[2].trim();
        var returnObject = addressLine1;

        if (currentNode != 'cityName') {
            $('#cityName').val(city);
        } else {
            returnObject = city;
        }

        if (currentNode != 'stateCode') {
            $('#stateCode').val(state);
        } else {
            returnObject = state;
        }

        return returnObject;
    }

    // Personal Address
    $('#addressLine1').typeahead({
        source: function (query, process) {
            var options = {
                types: ['(cities)'],
                componentRestrictions: {country: "us"}
            };

            var autocomplete = new google.maps.places.Autocomplete(query, options);

            return autocomplete.getPlace();

            /*var service = new google.maps.places.AutocompleteService();

            service.getPlacePredictions({ input: query, options: options}, function (predictions, status) {
                if (status == google.maps.places.PlacesServiceStatus.OK) {
                    process($.map(predictions, function (prediction) {
                        return prediction.description;
                    }));
                }*/
            },
        updater: function (item) {
            return applyToAddress(item.split(','), $(this).attr('id'));
        }
    });


    $('.nav-tabs').tab();

    // Checking to see if the user's username has been taken
    $('#usernameInput').on('blur', function () {
        var username = $(this).val();
        var usernameIssues = $('#usernameExists');

        if (username !== "") {
            if (username.match(VALID_USERNAME_REGEX)) {
                userExistCheck(username, usernameIssues);
            } else {
                validationTextRender(usernameIssues, true);
            }
        } else {
            usernameIssues.empty();
        }
    });

    // Validates the two password boxes are the same

    // Validates that the password has required characters
    $('#passwordInput').on('keydown', function () {
        var passwordErrorNode = $('#passwordError');

        passwordErrorNode.toggleClass('error icon-ban-circle', !($(this).val().match(VALID_PASSWORD_REGEX)));
        passwordErrorNode.toggleClass('success icon-ok-circle', $(this).val().match(VALID_PASSWORD_REGEX));
    });
});