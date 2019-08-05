var mccallback;
var menuTimer = null;

const $tools = mcfish.Tools;
const $api = mcfish.API;


$(function () {

    //左侧菜单切换
    $('.sidebar-menu li').each(function (index, item) {
        $(this).on('click', function () {
            $(this).addClass('active').siblings().removeClass('active')
        })
    })

    //顶部导航切换
    $('.toggle-nav-line').each(function (index, item) {
        $(this).on('click', function () {
            var id = $(this).data('id')
            $('.toggle-nav-line').removeClass('toggle-nav-item')
            if (index === id) {
                $(this).addClass('toggle-nav-item')
            }
        })
    })


    //弹窗确定事件回调处理
    $('.tips_dialog_button').click(function () {
        mccallback();
        mccallback = null;
    })


})

//点击刷新按钮
function refresh() {
    location.reload();
}


//打开操作提示小弹窗
function openTipsDialog(title, content, callback) {
    $("#tips_dialog_title").html(title);
    $("#tips_dialog_content").html(content);
    $("#tips_dialog_sm").modal("toggle");
    mccallback = callback;
}


//子页面调用父页面的提示信息
function childToast(info, time) {
    mizhu.toast(info, time)
}


function menutoggle() {
    $(document.body).toggleClass('sidebar-collapse')
    if ($(document.body).hasClass('sidebar-collapse')) { // 折叠时
        $('.top-project, .add-pro-col').hide()
        $('.toggbtn').addClass('letmarginz')
        $('.sidebar-menu li > a').css("padding", "12px 5px 12px 10px")
    } else { // 折叠宽度
        $('.top-project, .add-pro-col').show()
        $('.toggbtn').removeClass('letmarginz')
        $('.sidebar-menu li > a').css("padding", "12px 5px 12px 15px")
    }
}

menuTimer = setInterval(function () { // 菜单栏询阶段---宽度
    var w = $('#leftMenu').width()
    if (w < 60) {
        $('#leftMenu li .text').removeClass('letRdnone')
    } else {
        $('#leftMenu li .text').addClass('letRdnone')
    }
    if ($(document.body).hasClass('sidebar-collapse')) { // 折叠时
        $('.top-project, .add-pro-col').hide()
        $('.toggbtn').addClass('letmarginz')
    } else { // 折叠宽度
        $('.top-project, .add-pro-col').show()
        $('.toggbtn').removeClass('letmarginz')
    }
    $('#leftMenu li').each(function (index, item) {
        $(this).on('click', function () {
            $(this).addClass('active').siblings().removeClass('active')
            window.menuFrame.location.href = $(this).find("a").attr("href");
        })
    })
}, 100)


/**
 * 退出登录
 */
function loginOut() {

    //1.向服务器请求注销
    $api.userLogOut(function (res) {
        //2.本地清除session中的信息
        sessionStorage.clear();
        //3.回到登录页
        mcfish.Tools.comebackURL();
    });
}


/**
 * 打开修改密码弹窗
 */
function openEditPwdView() {
    $("#oldPassword").val("");
    $("#newPassword").val("");
    $("#newPassword2").val("");
    $("#editPwdView").modal("toggle");
}


/**
 * 保存密码
 */
function savePwd() {
    var oldPassword = $("#oldPassword").val();
    var newPassword = $("#newPassword").val();
    var newPassword2 = $("#newPassword2").val();

    if (oldPassword == "") {
        mizhu.toast("请输入原始密码", 3000);
        return false;
    }

    if (newPassword == "") {
        mizhu.toast("请输入新密码", 3000);
        return false;
    }


    if (newPassword == oldPassword) {
        mizhu.toast("新密码不能原来密码一致", 3000);
        return false;
    }


    if (newPassword.length > 16) {
        mizhu.toast("新密码超过16位", 3000);
        return false;
    }

    if (newPassword.length < 6) {
        mizhu.toast("新密码小于6位", 3000);
        return false;
    }

    if (newPassword2 == "") {
        mizhu.toast("请再次输入新密码", 3000);
        return false;
    }


    if (newPassword != newPassword2) {
        mizhu.toast("两次新密码不一致", 3000);
        return false;
    }


    var data = {
        oldPassword: oldPassword,
        newPassword: newPassword
    }

    $api.asyncRequest("shareChangePassword.do", "POST", data).then(function (res) {
        mizhu.toast(res.resmsg, 1000);
        $("#editPwdView").modal("toggle");
        $tools.timerExecute(function () {
            window.location.href = $tools.getBasicUrl() + "/tologin";
        }, 1, 3000);
    });
}
