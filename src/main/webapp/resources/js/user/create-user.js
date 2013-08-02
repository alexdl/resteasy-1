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

var autocompleter = new google.maps.places.AutocompleteService();

var userLocation = null;

/**
 * Simple method that will replace whatever is in the given validationNode as an error or success.
 *
 * @param validationNode The DOM node that we interact with to place the result.
 * @param isError <code>true</code> if an error message is requested. Otherwise, a good message will be used.
 */
function validationTextRender(validationNode, isError) {
    if (isError) {
        validationNode.html('<span><b class="icon-ban-circle"></b>&nbsp;Sorry, username taken.</span>');
    }
    else {
        validationNode.html('<span><b class="icon-ok-circle"></b>&nbsp;Looks good!</span>');
    }
}

/**
 * Method used to check and see if a requested username exists in the application already.
 *
 * @param username String representation of the requested username.
 * @param usernameIssuesNode DOM node where callback success or failure can be placed on the page.
 */
function userExistCheck(username, usernameIssuesNode) {
    var url = '/user/create/taken/' + username;

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

/**
 * Applies personal information provided by Google Places Autocomplete to create user form.
 *
 * @param addressArray The prediction selected by the end user.
 * @returns {*} Sets the personal information address fields, then returns address line 1.
 */
function applyToAddress(addressArray) {
    var addressLine1 = addressArray[0].trim();

    $('#cityName').val(addressArray[1].trim());
    $('#stateCode').val(addressArray[2].trim());

    return addressLine1;
}

/**
 * Applies the organization information to the fields on the create user form.
 *
 * @param addressArray Object that contains information about a Google Maps Place.
 * @returns {*} The name of the organization selected by the end user.
 */
function applyToOrganization(addressArray) {
    var organizationName = addressArray[0];
    var orgAddressLine1 = addressArray[1].trim();
    var orgCity = addressArray[2].trim();
    var orgState = addressArray[3].trim();

    $('#orgAddressLine1').val(orgAddressLine1);
    $('#orgCityName').val(orgCity);
    $('#orgStateCode').val(orgState);

    return organizationName;
}

// jQuery is ready to load once the page is loaded up.
$(document).ready(function () {

    $('.nav-tabs').tab();

    $('#createUserButton').prop('disabled', true);

    // User's location
    if (navigator.geolocation) {
        userLocation = navigator.geolocation.getCurrentPosition(function (position) {
            userLocation = new google.maps.LatLng(position.coords.latitude, position.coords.longitude, true);
        }, function () {
            userLocation = new google.maps.LatLng(39.5, -98.35, true);
        });
    }
    else {
        console.log("Geolocation is not supported by this browser.");
    }

    // Personal Address
    $('#addressLine1').typeahead({
        source: function (query, process) {
            var types = ['geocode'];

            autocompleter.getPlacePredictions({
                input: query,
                types: types,
                location: userLocation,
                radius: 100
            }, function (predictions, status) {
                if (status == google.maps.places.PlacesServiceStatus.OK) {
                    process($.map(predictions, function (prediction) {
                        return prediction.description;
                    }))
                }
            })
        },
        updater: function (item) {
            return applyToAddress(item.split(','));
        }
    });

    // Zip Code Lookup
    $('.postalCodeAutocomplete').typeahead({
        source: function (query, process) {
            var types = ['(regions)'];

            autocompleter.getPlacePredictions({
                input: query,
                types: types,
                location: userLocation,
                radius: 100
            }, function (predictions, status) {
                if (status == google.maps.places.PlacesServiceStatus.OK) {
                    process($.map(predictions, function (prediction) {
                        return prediction.description;
                    }))
                }
            })
        },
        updater: function (item) {
            return item.replace(/[A-Za-z$-,]/g, "").trim();
        }
    });

    // Organization Information
    $('#organizationName').typeahead({
        source: function (query, process) {
            var types = ['establishment'];

            autocompleter.getPlacePredictions({
                input: query,
                types: types,
                location: userLocation,
                radius: 100
            }, function (predictions, status) {
                if (status == google.maps.places.PlacesServiceStatus.OK) {
                    process($.map(predictions, function (prediction) {
                        return prediction.description;
                    }))
                }
            })
        },
        updater: function (item) {
            return applyToOrganization(item.split(','));
        }
    });

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
    $('#verifyPasswordInput').on('keyup', function () {
        var password1 = $('#passwordInput').val();
        var password2 = $('#verifyPasswordInput').val();
        var passwordCheck = $('#passwordSame');

        if (password1 === password2) {
            passwordCheck.html('<span><b class="icon-ok-circle" />&nbsp;Passwords Match!</span>');
        } else {
            passwordCheck.html('<span><b class="icon-ban-circle" />&nbsp;Passwords Do Not Match</span>');
        }
    });

    // Validates that the password has required characters
    $('#passwordInput').on('keyup', function () {
        var passwordErrorNode = $('#passwordError');

        passwordErrorNode.toggleClass('error icon-ban-circle', !($(this).val().match(VALID_PASSWORD_REGEX)));
        passwordErrorNode.toggleClass('success icon-ok-circle', $(this).val().match(VALID_PASSWORD_REGEX));
    });

    $('#promotionalCodeButton').on('click', function () {
        var url = '/user/create/promo/' + $('#promotionalCode').val();
        var button = $('#createUserButton');

        $.ajax({
            url: url,
            dataType: 'json',
            timeout: 5000,
            type: 'GET',
            success: function (msg) {
                if (msg) {
                    button.prop('disabled', false);
                    button.removeClass('btn-disabled disabled');
                }
            }
        });
    });
});