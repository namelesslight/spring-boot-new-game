var num = 0;
var count = 5;
var level = 0;
var remember = false;
var totalTime = 0;
$(function () {
    $("#out-login a").click(function () {
        $.removeCookie('user_name');
        alert($.cookie('user_name'));
    })

    $("#start").click(function () {
        if ($("#start").val() == "开始游戏") {
            count = 5;
            level = 0;
            totalTime = 0;
            remember = false;
            $("#game").html("");
            createTable();
            createBtn();
            $("#content").html("");
            $("#start").val("记好了");
            timer();
            logTotalTime();
            return null;
        }
        if ($("#start").val() == "记好了") {
            remember = true;
            $("#start").val("");

        }
        if ($("#start").val() == "继续") {
            remember = false;
            $("#game").html("");
            createTable();
            createBtn();
            $("#start").val("记好了");
            timer();
            logTotalTime();
            return null;
        }
    });
    var createTable = function () {
        for (var i = 0; i < 9; i++) {
            $("#game").append("<tr id=" + i + "></tr>");
            for (var j = 0; j < 9; j++) {
                $("#" + i).append("<td id=" + i + j + " class='location'></td>");
            }
        }
    }
    var createBtn = function () {
        num = 0;
        for (var i = 1; i <= count; i++) {
            var x = Math.floor(Math.random() * 9);
            var y = Math.floor(Math.random() * 9);
            if ($("#" + x + y).html().length == 0) {
                $("#" + x + y).append("<button class='numBtn'  value='" + i + "'>" + i + "</button>");
                $("#" + x + y).on("click", ".numBtn", function () {
                    if (remember == true) {
                        num++;
                        console.log(num);
                        $(this).css("background", "yellow");
                        if (num == count) {
                            level++;
                            count++;
                            $("#start").val("继续");
                            remember = false;
                        }
                        if (num != $(this).val()) {
                            setBestLevel(level);
                            setBestAverage(totalTime / (level + 1));
                            $("#content").html("游戏失败,你的分数为:" + level);
                            $("#start").val("开始游戏");
                            alert("游戏失败,你的分数为:" + level + ",速度为" + totalTime / (level + 1) + "秒/关");
                            $("#game").html("");
                        }
                    }
                });
            } else {
                i--;
            }
        }
    }
});

function timer() {
    var timerLog = 8 + (level + 1) * 2;
    var time = self.setInterval(function () {
        timerLog--;
        console.log(timerLog);
        $("#content").html("倒计时" + timerLog);
        if (timerLog == 0 || $("#start").val() == "") {
            clearInterval(time);
            remember = true;
            $(".numBtn").html(" ");
        }
    }, 1000);
}

function logTotalTime() {
    var log = self.setInterval(function () {
        totalTime++;
        $("#totalTime").html("总用时:" + totalTime);
        if ($("#start").val() == "开始游戏" || $("#start").val() == "继续") {
            clearInterval(log);
        }
    }, 1000)
}

function setBestLevel(level) {
    $.ajax({
        url: '/setBestLevel',
        method: 'patch',
        async: true,
        dataType: 'JSON',
        data: {user_name: decodeURI($.cookie('user_name')), best_level: level},
    })
}

function setBestAverage(average) {
    $.ajax({
        url: '/setBestAverage',
        method: 'patch',
        async: true,
        dataType: 'JSON',
        data: {user_name: decodeURI($.cookie('user_name')), best_average: average},
    })
}