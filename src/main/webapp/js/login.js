$(function () {
    if ($.cookie('login_inf') != null) {
        $("#login-inf").html($.cookie('login_inf'));
    }
    $("#login-btn").click(function () {
        $.ajax({
            url: '/userLogin',
            type: 'get',
            async: true,
            dataType: 'JSON',
            data: {user_name: $("#login-user-name").val(), user_password: $("#login-user-password").val()},
            success: function (result) {
                if (result == 1) {
                    $.removeCookie('login_inf');
                    window.location.href = getRealPath() + "/goGame";
                } else {
                    $("#login-inf").html("账号或密码错误");
                }
            }
        })
    })
})

function getRealPath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var localhostPath = curWwwPath.substring(0, curWwwPath.indexOf(pathName));
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPath + projectName;
}