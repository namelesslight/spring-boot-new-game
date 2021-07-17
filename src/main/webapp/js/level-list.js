$(function () {
    $.ajax({
        url: '/getBestLevel',
        method: 'get',
        async: true,
        dataType: 'JSON',
        data: {user_name: decodeURI($.cookie('user_name'))},
        success: function (result) {
            $("#your-best-level").html('你的最高关卡为' + result);
        }
    })
    if ($.cookie('page') == null || $.cookie('page') == "") {
        $.cookie('page', 1);
    }
    $("#page-sign").html('第' + $.cookie('page') + '页');
    $.ajax({
        url: '/getUserByLevel',
        method: 'get',
        async: true,
        dataType: 'JSON',
        data: {current_page: $.cookie('page'), page_size: 5},
        success: function (result) {
            var count = 1;
            console.log(count);
            $.each(result.records, function () {
                $("#level-list-body").append("<tr>" +
                    "<td>" + (result.current - 1) * 5 + count + "</td>" +
                    "<td>" + this.userName + "</td>" +
                    "<td>" + this.userBestLevel + "</td></tr>");
            })
            count++;
            for (var i = 1; i <= result.pages; i++) {
                $("#pages").append("<li><a href='" + getRealPath() + "/goLevelList'>" + i + "</li>")
            }
            $("#pages li").on("click", "a", function () {
                $.cookie('page', $(this).html())
            });
        }
    })
    $.removeCookie('page');
})

function getRealPath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var localhostPath = curWwwPath.substring(0, curWwwPath.indexOf(pathName));
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPath + projectName;
}