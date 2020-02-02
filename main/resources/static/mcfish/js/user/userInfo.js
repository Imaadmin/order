const $tools = mcfish.Tools;
const $api = mcfish.API;
const myurl = $tools.parseURL(window.location.href);
const uid = parseInt(myurl.params['id']);

$(function () {
    //获取用户详细信息
    getUserInfo();
    getUserOrderList();
});


/**
 * 获取课程详细信息
 * @returns
 */
function getUserInfo() {
    $api.asyncRequest("user/getUserInfoById", "GET", {uid: uid}).then(function (res) {
        //渲染用户信息到页面
        drawUserInfo(res.data);
    });
}


/**
 * 渲染用户信息到页面
 * @param {Object} data
 */
function drawUserInfo(data) {
    $("#info-phone").html(data.phone);
}


/**
 * 获取用户订单列表
 */
function getUserOrderList() {
    var ajaxParams = {
        api: 'user/getUserOrderList',
        type: 'GET',
        searching: true,
        data: {
            uid: uid,
        }
    };
    var colData = [
        {"data": "orderOid", 'sClass': "text-left col-width-280"},
        {"data": "total", 'sClass': "text-left col-width-80",
            "render": function ( data, type, full, meta ) {
                return mcfish.Tools.toMoney(data,2);
            }
        },
        {"data": "state", 'sClass': "text-center col-width-140",
            "render": function ( data, type, full, meta ) {
                var str = "";
                if (data == 1) {
                    str = "<span class='label label-info'>已下单</span> ";
                }
                if (data == 2) {
                    str = "<span class='label label-danger'>送货中</span>";
                }
                if (data == 3) {
                    str = "<span class='label label-success'>已完成</span>";
                }
                if (data == 4) {
                    str = "<span class='label label-warning'>已取消</span>";
                }
                return str;
            }
        },
        {"data" : "address",'sClass' : "text-center col-width-180"},
        {"data" : "createTime",'sClass' : "text-center col-width-160",
            "render": function ( data, type, full, meta ) {
                return mcfish.Tools.getMyDate(data,2);
            }
        },

        {
            "data": "orderOid", 'sClass': "text-center col-width-oper2",
            "render": function (data, type, full, meta) {
                var str = "";
                if (full.state == 1) {
                    str += "<span class='tab_text_blue pointer' onclick=changeUserCourseStatus('" + data + "',2);>发货</span>&nbsp;&nbsp;";
                }
                if (full.state == 2) {
                    str += "<span class='tab_text_blue pointer' onclick=changeUserCourseStatus('" + data + "',3);>完成</span>&nbsp;&nbsp;";
                }
                return str;
            }
        },
    ];
    Usertable = $api.getDataTable('#userOrderList', ajaxParams, colData);
}


/**
 * 修改订单状态
 * @param id 用户id
 * @param status 目标状态
 * @returns
 */
function changeUserCourseStatus(oid, status) {
    parent.window.openTipsDialog("提示", "确定更改状态吗？", function () {
        $api.asyncRequest("user/updateUserOrderStatus", "POST", {oid: oid, status: status}).then(function (res) {
            mizhu.toast(res.resmsg, 1000);
            Usertable.ajax.reload(null, false);
        });
    })
}