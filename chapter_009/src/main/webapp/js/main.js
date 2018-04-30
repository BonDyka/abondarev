$(document).ready(function(){

    $('.form-control').on('click', function(){
        $(this).removeClass('empty-field');
    });

    $('#in').on("click", function(){
        if (validateIn()) {
            var checkUserData = checkUser($('#log').val(), $('#pwd').val());
            if (checkUserData.isValid) {
                document.location.replace(checkUserData.nextPage);
            } else {
                $('#msg').text('Incorrect login or/and password!').show();
            }
        } else {
            $('#msg').text('Both login and password must be filled!').show();
        }
    });

    $('#signup').on('click', fillSelect('#country'));

    $('#country').on('change', function () {
        fillSelect('#city', $(this).val());
    });

    $('#signUpForm').on("submit", function(){
        var valid = validateUp();
        if (!valid) {
            $('#msg2').text('User with these login and/or email already exists!').show();
        }
        return valid;
    });
});

function validateIn() {
    var result = true;
    var log = $('#log');
    var pwd = $('#pwd');

    if (log.val() === '') {
        log.addClass('empty-field');
        result = false;
    }
    if (pwd.val() === '') {
        pwd.addClass('empty-field');
        result = false;
    }
    return result;
}

function validateUp() {
    var result = true;

    var formData = $('#signUpForm').serializeArray();
    if (formData.length <6) {
        result = result && false;
    }
    for (var i = 0; i < formData.length; i++) {
        if (formData[i].value === '') {
            result = result && false;
            $('#signUpForm [name="' + formData[i].name + '"]').addClass('empty-field');
        }
    }

    if (result) {
        $.ajax({
            url: 'add',
            method: 'post',
            data: formData,
            async: false,
            complete: function (resp) {
                result = JSON.parse(resp.responseText).created;
            }
        });
    }

    return result;
}

function checkUser(login, password) {
    var result = '';
    $.ajax({
        url: 'signin',
        method: 'post',
        async: false,
        data: {'login': login, 'password': password},
        success: function (resp) {
            result = JSON.parse(resp);
        }
    });
    return result;
}

function fillSelect(selectId, countryId) {
    if (selectId === '#city') {
        $(selectId).prop('disabled', false).html('').append('<option value="">Choice your city</option>');
    }
    var places = getPlaces(countryId);
    for (var i = 0; i < places.length; i++) {
        $(selectId).append('<option value="' + places[i].id + '">' + places[i].name + '</option>');
    }
}

function getPlaces(countryId) {
    var result;
    var placeName = 'city';
    if (!countryId) {
        placeName = 'country';
    }
    $.ajax({
        url: 'places',
        method: 'post',
        async: false,
        data: {places: placeName, country_id: countryId},
        success: function(resp) {
            result = resp;
        }
    });

    return result;
}



