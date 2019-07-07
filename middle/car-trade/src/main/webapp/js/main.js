$(document).ready(function () {
    $('input').on('keypress', function () {
        $(this).removeClass('invalid');
    });

    $('#auth_data').on('click', function () {
        var errMsg = $('#err_msg');
        errMsg.text('');
        var form = $(this).parent();
        if (checkForm(form)) {
            var data = form.serializeArray();
            authorize(data);
        } else {
            errMsg.text('Fields can\'t be empty');
        }
    });

    $('#reg_data').on('click', function () {
        var errMsgReg = $('#err_msg_reg');
        errMsgReg.text('');
        var form = $(this).parent();
        if (checkForm(form)) {
            var data = form.serializeArray();
            registration(data);
        } else {
            errMsgReg.text('Fields can\'t be empty');
        }
    });

    $('#auth').on('click', function () {
        $('#err_msg').text('');
    });

    $('#reg').on('click', function () {
        $('#err_msg_reg').text('');
        $('input').val('').removeClass('invalid')
    });

    fillTransmissionTypes();
    fillCarBodyTypes();
    getAnnouncesList(showList);
});

function getAnnouncesList(callback) {
    $.ajax({
        url: 'announces-list',
        complete: function (answer) {
            var announces = JSON.parse(answer.responseText);
            callback(announces);
        }
    });
}

function showList(list) {
    var card = $('#tmps .item');
    var announcesTab = $('#announces');
    list.forEach(function (item) {
        card.attr('id', item.id);
        fillCardInfo(item, card);
        announcesTab.append(card);
    });
}

function fillCardInfo(item, card) {
    card.find('.item_table-header').text(item.car.name);
    card.find('.price').text(item.price);
    var car = item.car;
    card.find('.specific-params').text(car.engine.volume + ' ' + car.transmission.type
        + ' (' + car.engine.power + 'ps), ' + car.body.type + ', ' + car.engine.type);
    card.find('.owner').html(item.author.fname + ' ' + item.author.lname + '<br /> tel: ' + item.author.phone);
}

function checkForm(form) {
    var result = true;
    var formInputs = form.find('input');
    for (var index = 0; index < formInputs.length; index++) {
        var input = $(formInputs[index]);
        if (input.val() === '') {
            input.addClass('invalid');
            result = result && false;
        }
    }
    return result;
}

function authorize(formData) {
    $.ajax({
        url: '/sign_in',
        method: 'post',
        data: formData,
        complete: function (resp) {
            var answer = JSON.parse(resp.responseText);
            if (resp.status === 200) {
                var modal = $('#asign');
                modal.find('input').each(function () {
                    $(this).val('');
                });
                $('#asign_btn')
                    .attr({
                        'data-toggle' : '',
                        'data-target' : '',
                        'id' : 'log_out'
                    })
                    .text('Sign Out');
                $('#add_anno').prop('disabled', false);
                modal.modal('hide');
                showGreeting(answer);
            } else {
                showErrorMsg(answer);
            }
        }
    });
}

//Shows greetings for assigned user.
function showGreeting(user) {
    $('#greeting').text('You enter as ' + user.login + '!');
}

//Shows error message for sign in modal.
function showErrorMsg(msgObj) {
    $('#err_msg').removeClass('text-success').addClass('err_msg').text(msgObj.msg);
    $('#asign').find('input').each(function () {
        $(this).addClass('invalid');
    });
}

function registration(data) {
    $.ajax({
       url: '/sign_up',
       method: 'post',
       data: data,
       complete: function (resp) {
           var answer = resp.responseJSON;
            console.log(answer);
            var regModal = $('#registration');
            if (resp.status === 200) {
                regModal.find('input').val('');
                regModal.modal('hide');
                $('#asign').modal('show');
                $('#err_msg').removeClass('err_msg').addClass('text-success').text(answer.msg);
            } else {
                $('#err_msg_reg').text(answer.msg);
            }
       }
    });
}

function fillTransmissionTypes() {
    $.ajax({
        url: '/transmissions',
        complete: function (resp) {
            var list = resp.responseJSON;
            var html = '';
            list.forEach(function (item) {
                html += '<option value=' + item.id + '>' + item.type + '</option>'
            });
            $('#trns').html(html);
        }
    })
}

function fillCarBodyTypes() {
    $.ajax({
        url: '/car_bodies',
        complete: function (resp) {
            var list = resp.responseJSON;
            var html = '';
            list.forEach(function (item) {
                html += '<option value=' + item.id + '>' + item.type + '</option>'
            });
            $('#car-body').html(html);
        }
    })
}