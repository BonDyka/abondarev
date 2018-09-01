$(document).ready(function () {
    setPageInfo();
    fillMusicTypesList();
    fillRolesList();
    $('#edit').on("click", function () {
        var  userId = $('#id').val();
        $.ajax({
            url: '/edit',
            type: 'get',
            data: {"id": userId},
            complete: function (result) {
                var answerObj = JSON.parse(result.responseText);
                var editUser = JSON.parse(answerObj.editUser);
                var currentUserRole = answerObj.currentUserRole;

                setUserInfoToEditForm(editUser);
                hideUnavaliableRoles(editUser.role.id, currentUserRole);
            }
        });
    })
});

function setPageInfo() {
    $.ajax({
        url: '/view',
        type: 'post',
        async: false,
        complete: function (result) {
            var answerObj = JSON.parse(result.responseText);
            var currentUser = JSON.parse(answerObj.currentUser);
            var usersList = JSON.parse(answerObj.users);
            console.log(usersList);
            showGreeting(currentUser);
            showUsersList(usersList);
        }
    })
}

function showGreeting(user) {
    $('#greeting').html('<h3> Hello, ' + user.login + '!</h3>');
    $('#id').val(user.id);
}

function showUsersList(array) {
    var table = $('#list_of_users').find('table');
    array.forEach(function (item) {
        table.append('<tr><td>' + item.login + '</td><td>' + item.address.country + '</td><td>' + item.role.title
            + '</td><td><button id="' + item.id + '" class="info btn">Info</button></td></tr>');
    })
}

function fillMusicTypesList() {
    $.ajax({
        url: '/show_mt',
        type: 'get',
        complete: function (result) {
            var answer = JSON.parse(result.responseText);
            var array = JSON.parse(answer.types);
            array.forEach(function (item) {
                $('#mt').append('<option value="' + item.id + '">' + item.genre + '</option>')
            });
        }
    })
}

function fillRolesList() {
    $.ajax({
        url: '/show_roles',
        type: 'get',
        complete: function (result) {
            var answer = JSON.parse(result.responseText);
            var array = JSON.parse(answer.roles);
            array.forEach(function (item) {
                $('#role').append('<option value="' + item.id + '">' + item.title + '</option>')
            });
        }
    })
}

function setUserInfoToEditForm(user) {
    $('[name="login"]').val(user.login);
    $('#password').val(user.password);
    $('#country').val(user.address.country);
    $('#city').val(user.address.city);
    $('#role').find('[value="'+ user.role.id + '"]').attr("selected", "selected");
    user.types.forEach(function (item) {
        $('#mt').find('[value="'+ item.id + '"]').attr("selected", "selected");
    })
}

function hideUnavaliableRoles(editUserRole, currentUserRole) {
    if (currentUserRole === 'USER') {
        $('#role').find('[value!="'+ editUserRole + '"]').hide();
    } else if (currentUserRole === 'MANDATOR') {
        $('#role').find('[value="1"]').hide();
    }
}




