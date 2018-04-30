$(document).ready(function(){
    var currentUser = null;
    var listOfUsers = [];
    $.ajax({
        url: 'view',
        method: 'post',
        async: false,
        complete: function(data) {
            var t = JSON.parse(data.responseText);
            currentUser = JSON.parse(t.currentUser);
            listOfUsers = JSON.parse(t.listOfUsers);
        }
    });
    if(currentUser !== null) {
        setUserData(currentUser);
        showUsers(listOfUsers, currentUser);
        fillFormData(currentUser);
    }

    $('.form-control').on('click', function(){
        $(this).removeClass('empty-field');
    });

    $('#delete').on('click', function () {
        window.location.replace(removeUser(currentUser.login));
    });

    $('.del').on('click', function () {
        var userLogin = $(this).closest('tr').find('td:nth-of-type(2)').text();
        window.location.replace(removeUser(userLogin));
    });

    $('#edit').on('click', function () {
        var formDataArray = $('form.mx-auto').serializeArray();
        if (validateForm(formDataArray)) {
            changeUser(formDataArray);
        }
    })

    $('.change_role').on('click', function () {
        var userLogin = $(this).closest('tr').find('.login-from-list').text();
        var userData = null;
        for (var i = 0; i < listOfUsers.length; i++) {
            if (listOfUsers[i].login === userLogin) {
                userData = listOfUsers[i];
                break;
            }
        }
        if (userData.role.id === 1) {
            userData.role = '2';
        } else {
            userData.role = '1';
        }
        var countries = getPlaces();
        for (var j = 0; j < countries.length; j++) {
            if (countries[j].name === userData.country) {
                userData.country = countries[j].id;
                break;
            }
        }
        var cities = getPlaces(userData.country);
        for (var k = 0; k < cities.length; k++) {
            if (cities[k].name === userData.city) {
                userData.city = cities[k].id;
            }
        }
        changeUser(userData);
    })
});

/*
 * Sets data of user into document
 */
function setUserData(user){
    $('#nickname').html('<h4>'+ user.login +'</h4>');
    var html = '<h5>Name: '+ user.name + '</h5><h5>Name: '+ user.email + '</h5><h5>Name: '+ user.country
        + '</h5><h5>Name: '+ user.city + '</h5><h5>Name: '+ user.createDate + '</h5>';
    $('#info').html(html);
}

function fillFormData(user){
    $('#name').val(user.name);
    $('#login').val(user.login);
    $('#password').val(user.password);
    $('#email').val(user.email);
    var userRoleName = user.role.name;
    $('#role').find('option').each(function () {
        if ($(this).text() === userRoleName) {
            $(this).attr('selected', 'selected');
        }
    });
    if (userRoleName === 'admin') {
        $('#role').closest('.form-group').removeClass('for_admin');
    }
    setCountries(user.country);
    setCities(user.city);
}

function setCountries(country){
    $.ajax({
        url: 'places',
        method: 'post',
        async: false,
        data: {places: 'country'},
        complete: function(data) {
            var arr = data.responseText;
            var ob = JSON.parse(arr);
            addOption("#country", ob, country);
        }
    })
}

function setCities(city){
    var countryId = $('#country').val();
    $('#city').html('');
    $.ajax({
        url: 'places',
        method: 'post',
        async: false,
        data: {places: 'city', country_id: countryId},
        complete: function(data) {
            var arr = data.responseText;
            var ob = JSON.parse(arr);
            addOption("#city", ob, city);
        }
    })
}

function addOption(id, array, selected) {
    for(var i = 0; i != array.length; i++) {
        var obj = array[i];
        $(id).append("<option value=" + obj.id + ">" + obj.name + "</option>");
        if(obj.name === selected) {
            $(id + 'option[value="' + obj.id +'"]').prop('selected', true);
        }
    }
}

function showUsers(listOfUsers, user){
    var html = '';
    var userRole = user.role.name;
    var adminActions = '<button class="change_role btn w-100 m-1">Change role</button><br><button class="del btn w-100 m-1">Delete</button>';
    for (var i = 0; i < listOfUsers.length; i++) {
        var name = listOfUsers[i].name;
        if (name === user.name) {
            continue;
        }
        var login = listOfUsers[i].login;
        var email = listOfUsers[i].email;
        var country = listOfUsers[i].country;
        var city = listOfUsers[i].city;
        var created = listOfUsers[i].createDate;
        html += '<tr><td>' + name + '</td><td class="login-from-list">'+ login +'</td><td>' + email +'</td><td>'+ country +'</td><td>'+ city
            +'</td><td>' + created +'</td><td class="for_admin">' + adminActions + '</td></tr>';
    }
    $('tbody').html(html);
    if (userRole === 'admin') {
        $('.for_admin').show();
    }
}

function removeUser(userLogin) {
    var result = '/';
    $.ajax({
        url: 'delete',
        method: 'get',
        data: {'login': userLogin},
        async: false,
        complete: function (resp) {
            result = resp.responseText;
        }
    });
    return result;
}

function validateForm(formData) {
    var result = true;
    for (var i = 0; i < formData.length; i++) {
        if (formData[i].value === '') {
            $('[name="' + formData[i].name + '"').addClass('empty-field');
            result = result && false;
        }
    }
    return result;
}

function changeUser(data) {
    $.ajax({
        url: 'edit',
        method: 'post',
        data: data,
        complete: function (resp, status) {
            if (status === 'success') {
                window.location.replace("/view");
            }
        }
    })
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