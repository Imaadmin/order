$(function () {
    checkRememBer();

    // mcfish.API.getSystemValue("keyValue", "POST", {});
    // $("#project").html("校园超市");
    // $("#indexProjectName").html("校园超市");
    $("#logo").attr("src","http://www.jiuyect.com:4869/209037165a5e5dbd3274507b8cefd26a");
    $("body").css("background", "url(https://img.tukuppt.com/bg_grid/00/04/41/6op62bfz3a.jpg!/fh/350)");
    $("body").css("background-size", "cover");
});

//加载时，检测是否设置了“记住密码”
function checkRememBer() {
    if (document.cookie.length > 0) {
        var flag = document.cookie.indexOf("userAcAndPwd");
        if (flag != -1) {
            var $tools = mcfish.Tools;
            var userAcAndPwd = $tools.GetCookie("userAcAndPwd");
            var account = userAcAndPwd.split(",")[0];
            var password = userAcAndPwd.split(",")[1];
            $("#account").val(account);
            $("#password").val(password);
            $("#remFlag").attr("checked", "checked");
        }
    }
}

//记住密码功能
function remember() {
    var $tools = mcfish.Tools;
    var remFlag = $("#remFlag").is(':checked');
    if (remFlag == true) { //如果选中设置remFlag为1
        //cookie存用户名和密码
        $tools.SetCookie("userAcAndPwd", $("#account").val() + "," + $("#password").val(), "d30");
    } else { //如果没选中设置remFlag为""
        $tools.DelCookie("userAcAndPwd");
    }
}

//记住密码功能
function remember2() {
    var remFlag = $("#remFlag").is(':checked');
    if (remFlag == true) { //如果选中设置remFlag为1
        //cookie存用户名和密码,回显的是真实的用户名和密码,存在安全问题.
        var conFlag = confirm("记录密码功能不宜在公共场所(如网吧等)使用,以防密码泄露.您确定要使用此功能吗?");
        if (conFlag) { //确认标志
            $("#remFlag").val("1");
        } else {
            $("#remFlag").removeAttr('checked');
            $("#remFlag").val("");
        }
    } else { //如果没选中设置remFlag为""
        $("#remFlag").val("");
    }
}

document.addEventListener('keydown', function (e) {
    if (e.keyCode == 13) {
        $("#loginBtn").trigger("click");
    }
});

