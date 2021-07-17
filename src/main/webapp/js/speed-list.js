$(function () {
    $.ajax({
        url: '/getBestAverage',
        method: 'get',
        async: true,
        dataType: 'JSON',
        data: {user_name: decodeURI($.cookie('user_name'))},
        success: function (result) {
            $("#your-best-speed").html('你的最快速度为' + result + '(秒/关)');
        }
    })
    if ($.cookie('page') == null || $.cookie('page') == "") {
        $.cookie('page', 1);
    }
    $.ajax({
        url: '/getUserByAverage',
        method: 'get',
        async: true,
        dataType: 'JSON',
        data: {current_page: $.cookie('page'), page_size: 5},
        success: function (result) {
            var count = 1;
            $.each(result.records, function () {
                $("#speed-list-body").append("<tr>" +
                    "<td>" + (result.current - 1) * 5 + count + "</td>" +
                    "<td>" + this.userName + "</td>" +
                    "<td>" + this.userBestAverage + "</td></tr>");
            })
            count++;
            for (var i = 1; i <= result.pages; i++) {
                $("#pages").append("<li><a href='" + getRealPath() + "/goSpeedList'>" + i + "</li>")
            }
            $("#pages li").on("click", "a", function () {
                $.cookie('page', $(this).html());
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