$(function () {
    var nameFlag = false;
    var passwordFlag = false;
    var rePasswordFlag = false;
    $("#register-user-name").change(function () {
        if (getUserName($("#register-user-name").val()) != null) {
            $("#register-name-inf").html("用户名已使用");
            $("#register-name-inf").css("color", "red");
        } else {
            $("#register-name-inf").html("用户名可用");
            $("#register-name-inf").css("color", "green");
            nameFlag = true;
        }
    })
    $("#register-user-password").change(function () {
        if ($("#register-user-password").val().length < 6 || $("register-user-password").val() > 20) {
            $("#register-password-inf").html("密码长度应在6到20味之间");
            $("#register-password-inf").css("color", "red");
        } else {
            $("#register-password-inf").html("密码可用");
            $("#register-password-inf").css("color", "green");
            passwordFlag = true;
        }
    })
    $("#register-re-password").change(function () {
        if ($("#register-user-password").html() != $("#register-re-password").html()) {
            $("#register-re-password-inf").html("与原密码不一致");
            $("#register-re-password-inf").css("color", "red");
        } else {
            $("#register-re-password-inf").html("密码一致");
            $("#register-re-password-inf").css("color", "green");
            rePasswordFlag = true;
        }
    })
    $("#register-btn").click(function () {
        if (nameFlag == true && passwordFlag == true && rePasswordFlag == true) {
            $.ajax({
                url: '/userRegister',
                async: true,
                method: 'put',
                dataType: 'JSON',
                data: {user_name: $("#register-user-name").val(), user_password: $("#register-user-password").val()},
                success: function (result) {
                    if (result == 1) {
                        window.location.href = getRealPath() + "/login.html";
                    }
                }

            })
        }
    })
})

function getUserName(userName) {
    $.ajax({
        url: '/getOneUser',
        method: 'get',
        async: true,
        dataType: 'JSON',
        data: {user_name: userName},
        success: function (result) {
            return result;
        }
    })
}

function getRealPath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var localhostPath = curWwwPath.substring(0, curWwwPath.indexOf(pathName));
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPath + projectName;
}