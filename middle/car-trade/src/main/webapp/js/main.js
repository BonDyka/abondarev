$(document).ready(function () {
    $('input').on('keypress', function () {
        $(this).removeClass('invalid');
    });

    $('#auth_data').on('click', function () {
        let errMsg = $('#err_msg');
        errMsg.text('');
        let form = $(this).parent();
        if (checkForm(form)) {
            let data = form.serializeArray();
            authorize(data);
        } else {
            errMsg.text('Fields can\'t be empty');
        }
    });

    $('#reg_data').on('click', function () {
        let errMsgReg = $('#err_msg_reg');
        errMsgReg.text('');
        let form = $(this).parent();
        if (checkForm(form)) {
            let data = form.serializeArray();
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

    $('#add_anno').on('click', function () {
        changeLocation();
    });

    $('input[type=range]').on('mouseenter mousemove change', function () {
        $('#volumeVal').text($(this).val());
    });

    checkSession();

    if (document.location.pathname === '/add_announce') {
        fillTransmissionTypes();
        fillCarBodyTypes();
        $('#create_announce').on('click', function () {
            createAnnounce();
        });
    } else {
        getAnnouncesList(showList);

        $('#filter').on('click', function () {
            filterList(showList);
        });
    }
});

function validateNumbers(evt) {
    let theEvent = evt || window.event;
    let key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode( key );
    let regex = /[0-9]/;
    if( !regex.test(key) ) {
        theEvent.returnValue = false;
        if (theEvent.preventDefault) theEvent.preventDefault();
    }
}

function createAnnounce() {
    let form = $('#add-announce-form');
    if (checkForm(form)) {
        let fd = new FormData();
        let textInput = form.serializeArray();
        $.each(textInput, function (index, textData) {
            fd.append(textData.name, textData.value);
        });

        let fileInputs = form.find('input[type=file]');
        $.each(fileInputs, function (index, file) {
            fd.append(file.name, file.files[0]);
        });

        $.ajax({
            url: '/add_announce',
            type: 'post',
            processData: false,
            contentType: false,
            data: fd,
            complete: function (data) {
                if (data.status === 200) {
                    document.location.replace("/");
                } else {
                    $('#err_msg').text("Announcement wasn't saved!");
                }
            }
        });
    } else {
        $('#err_msg').text("This fields can't be empty");
    }
}

function changeLocation() {
    document.location.replace("/add_announce")
}

function checkSession() {
    $.ajax({
        url: 'check_session',
        complete: function (resp) {
            if (resp.status === 200) {
                changeButton();
                showGreeting(resp.responseJSON.login);
            } else {}
        }
    })
}

function getAnnouncesList(callback) {
    $.ajax({
        url: 'announces-list',
        complete: function (answer) {
            callback(JSON.parse(answer.responseText));
        }
    });
}

function filterList(callback) {
    let fd = $('#filter-form').serializeArray();
    $.ajax({
        url: "/filter",
        data: fd,
        complete: function (answer) {
            let list = JSON.parse(answer.responseText);
            callback(list);
        }
    })
}

function showList(list) {
    let cardTmp = $('#tmps').html();
    let announcesTab = $('#announces');
    announcesTab.html('');
    list.forEach(function (item) {
        announcesTab.append(cardTmp);
        let card = announcesTab.children('.item:last');
        card.attr('id', item.id);
        fillCardInfo(item, card);
    });
}

function fillCardInfo(item, card) {
    card.find('.item_table-header').text(item.car.name);
    card.find('.price').text(item.price);
    card.find('.item-photo').html('<img src="/photos' + item.photos[0].path + '" class="w-100 h-100" />');
    let car = item.car;
    card.find('.specific-params').text(car.engine.volume + ' ' + car.transmission.type
        + ' (' + car.engine.power + 'ps), ' + car.body.type + ', ' + car.engine.type);
    card.find('.owner').html(item.author.fname + ' ' + item.author.lname + '<br /> tel: ' + item.author.phone);
}

function checkForm(form) {
    let result = true;
    let formInputs = form.find('input');
    for (let index = 0; index < formInputs.length; index++) {
        let input = $(formInputs[index]);
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
            let answer = JSON.parse(resp.responseText);
            if (resp.status === 200) {
                let modal = $('#asign');
                modal.find('input').each(function () {
                    $(this).val('');
                });
                modal.modal('hide');
                changeButton();
                showGreeting(answer.login);
            } else {
                showErrorMsg(answer);
            }
        }
    });
}

function changeButton() {
    $('#asign_btn')
        .attr({
            'data-toggle' : '',
            'data-target' : '',
            'id' : 'log_out'
        })
        .text('Sign Out');
    //$('#add_anno').prop('disabled', false);
}

//Shows greetings for assigned user.
function showGreeting(uLogin) {
    $('#greeting').text('You enter as ' + uLogin + '!');
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
           let answer = resp.responseJSON;
            console.log(answer);
            let regModal = $('#registration');
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
            let list = resp.responseJSON;
            let html = '';
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
            let list = resp.responseJSON;
            let html = '';
            list.forEach(function (item) {
                html += '<option value=' + item.id + '>' + item.type + '</option>'
            });
            $('#car-body').html(html);
        }
    })
}