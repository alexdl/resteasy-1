/**
 * Javascript file that is used on the page dealing with the creation of a given user.
 *
 * @author Sean M. Staley
 * @version 1.0
 */

// Constants
var VALID_USERNAME_REGEX = /^[a-z0-9_-]{3,16}$/;

var autocompleter = new google.maps.places.AutocompleteService();
var userLocation = null;

/*
 * Array used to make sure the user has entered all fields needed before enabling the Save button.
 *
 * Array reads as follows:
 * [username, password, name, personal address, personal zip, personal phone, org name, org address, org zip, org phone, promo code]
 */
var userCompleted = [false, false, false, false, false, false, false, false, false, false, false, false];

var USERNAME_FIELD = 0;
var PASSWORD_FIELD = 1;
var NAME_FIELD = 2;
var P_ADDRESS_FIELD = 3;
var P_ZIP_FIELD = 4
var P_PHONE_FIELD = 5;
var P_EMAIL_FIELD = 6;
var O_NAME_FIELD = 7;
var O_ADDRESS_FIELD = 8;
var O_ZIP_FIELD = 9;
var O_PHONE_FIELD = 10;
var PROMO_FIELD = 11;

/**
 * Simple method that will replace whatever is in the given validationNode as an error or success.
 *
 * @param validationNode The DOM node that we interact with to place the result.
 * @param isError <code>true</code> if an error message is requested. Otherwise, a good message will be used.
 */
function validationTextRender(validationNode, isError) {
    if (isError) {
        validationNode.html('<span><b class="icon-ban-circle"></b>&nbsp;Sorry, username taken.</span>');
        userCompleted[USERNAME_FIELD] = false;
    }
    else {
        validationNode.html('<span><b class="icon-ok-circle"></b>&nbsp;Looks good!</span>');
        userCompleted[USERNAME_FIELD] = true;
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
 * Method used to check and see if a requested username exists in the application already.
 *
 * @param username String representation of the requested username.
 * @param usernameIssuesNode DOM node where callback success or failure can be placed on the page.
 */
function passwordCheck(password, passwordErrorNode) {
    var url = '/user/create/password';

    $.ajax({
        url: url,
        dataType: 'json',
        timeout: 5000,
        type: 'POST',
        data: {"password": password},
        success: function (msg) {
            if (msg) {
                if (!passwordErrorNode.hasClass('success')) {
                    passwordErrorNode.removeClass();
                    passwordErrorNode.addClass('success icon-ok-circle');
                }
            } else {
                if (!passwordErrorNode.hasClass('error')) {
                    passwordErrorNode.removeClass();
                    passwordErrorNode.addClass('error icon-ban-circle');
                    passwordErrorNode.attr('<span>&nbsp;Invalid Password Syntax</span>');
                }
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

    userCompleted[P_ADDRESS_FIELD] = true;

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

    userCompleted[O_ADDRESS_FIELD] = true;

    return organizationName;
}

function zipCodeCheck(dom) {
    var id = dom.attr("id");

    if (id = 'personalPostalCode') {
        if (dom.val() !== "") {
            userCompleted[P_ZIP_FIELD] = true;
        } else {
            userCompleted[P_ZIP_FIELD] = false;
        }
    } else {
        if (dom.val() !== "") {
            userCompleted[O_ZIP_FIELD] = true;
        } else {
            userCompleted[O_ZIP_FIELD] = false;
        }
    }
}

/**
 * Method used to check all fields that are required in the registering part of the application.
 */
function checkRequiredFields() {
    for (var i in userCompleted) {
        if (!userCompleted[i]) {
            return;
        }
    }

    $('#createUserButton').prop('disabled', false);
    $('#createUserButton').removeClass('btn-disabled disabled');
}

// jQuery is ready to load once the page is loaded up.
$(document).ready(function () {

    var tabIndex;

    var tabs = $('a[data-toggle="tab"]');

    tabs.on('shown',function (e) {
        tabIndex = $(e.target).closest('li').index();
    }).eq(0).trigger('shown');

    $('._tabs_navigation').on('click', 'a', function () {
        var index = tabIndex + ($(this).index() ? 1 : -1);
        if (index >= 0 && index < tabs.length) {
            tabs.eq(index).tab('show');
        }
        return false;
    });

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

    $('#prevtab').on('click', function () {
        tabs.filter('.active').prev('li').find('a[data-toggle="tab"]').tab('show');
    });

    $('#nexttab').on('click', function () {
        tabs.filter('.active').next('li').find('a[data-toggle="tab"]').tab('show');
    });

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

    $('#personalPostalCode').on('change', function () {
        if ($(this).val() !== "") {
            userCompleted[P_ZIP_FIELD] = true;
        } else {
            userCompleted[P_ZIP_FIELD] = false;
        }
    });

    $('#orgZipCode').on('change', function () {
        if ($(this).val() !== "") {
            userCompleted[O_ZIP_FIELD] = true;
        } else {
            userCompleted[O_ZIP_FIELD] = false;
        }
    });

    $('#phoneNumberInput').on('change', function () {
        if ($(this).val() !== "") {
            userCompleted[P_PHONE_FIELD] = true;
        } else {
            userCompleted[P_ADDRESS_FIELD] = false;
        }
    });

    $('#organizationNumberInput').on('change', function () {
        if ($(this).val()) {
            userCompleted[O_PHONE_FIELD] = true;
        } else {
            userCompleted[O_ADDRESS_FIELD] = false;
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
            userCompleted[O_NAME_FIELD] = true;
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

    $('#emailInput').on('change', function () {
        if ($(this).val() !== "") {
            userCompleted[P_EMAIL_FIELD] = true;
        } else {
            userCompleted[P_EMAIL_FIELD] = false;
        }
    });

    $('.requiredNameField').on('change', function () {
        if ($('#firstNameInput').val() && $('#lastNameInput').val()) {
            userCompleted[NAME_FIELD] = true;
        } else {
            userCompleted[NAME_FIELD] = false;
        }
    });

    // Validates that the password has required characters
    $('#passwordInput').on('keyup', function () {
        var passwordErrorNode = $('#passwordError');

        if ($(this).val() !== '') {
            passwordCheck($(this).val(), passwordErrorNode);
        }
    });

    // Validates the two password boxes are the same
    $('#verifyPasswordInput').on('keyup', function () {
        var password1 = $('#passwordInput').val();
        var password2 = $('#verifyPasswordInput').val();
        var passwordCheck = $('#passwordSame');

        if (password1 === password2) {
            passwordCheck.html('<span><b class="icon-ok-circle" />&nbsp;Passwords Match!</span>');
            userCompleted[PASSWORD_FIELD] = true;
        } else {
            passwordCheck.html('<span><b class="icon-ban-circle" />&nbsp;Passwords Do Not Match</span>');
            userCompleted[PASSWORD_FIELD] = false;
        }
    });

    // Checks the code of the incoming user.
    $('#promotionalCodeButton').on('click', function () {
        var url = '/user/create/promo/' + $('#promotionalCode').val();

        $.ajax({
            url: url,
            dataType: 'json',
            timeout: 5000,
            type: 'GET',
            success: function (msg) {
                if (msg) {
                    userCompleted[PROMO_FIELD] = true;
                    checkRequiredFields();
                }
            }
        });
    });

    $('.requiredField').on('change', function () {
        checkRequiredFields();
    })
});