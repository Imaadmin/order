const $tools = mcfish.Tools;
const $api = mcfish.API;
$(function(){
    //获取订单列表
    getOrderList();
});


/******************************************************************************************
 ************************     订单管理列表页面-start            ****************************
 ******************************************************************************************/


/**
 * 获取基础信息列表
 */
function getOrderList(){
    var state = $("#state").val();
    var ajaxParams = {
        api: 'getOrderList',
        type: 'POST',
        searching:true,
        paging:true,
        data: {
            state	: state,
        }
    };
    var colData = [
        {"data" : "orderOid",'sClass' : "text-left col-width-240"},
        {"data" : "phone",'sClass' : "text-center col-width-120"},
        {"data": "total", 'sClass': "text-left col-width-80",
            "render": function ( data, type, full, meta ) {
                return mcfish.Tools.toMoney(data,2);
            }
        },
        {"data": "state", 'sClass': "text-center col-width-120",
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
                return mcfish.Tools.getMyDate(data,1);
            }
        },
        {"data" : "orderOid",'sClass' : "text-center col-width-oper2",
            "render" : function(data, type, full, meta) {
                var str = "";
                if (full.state == 1) {
                    str += "<span class='tab_text_blue pointer' onclick=changeUserCourseStatus('" + data + "',2);>发货</span>&nbsp;&nbsp;";
                }
                if (full.state == 2) {
                    str += "<span class='tab_text_blue pointer' onclick=changeUserCourseStatus('" + data + "',3);>完成</span>&nbsp;&nbsp;";
                }
                var href = $tools.getBasicUrl() + "toOrderInfoPage?oid=" + data;
                str += "<a  href='"+ href +"' target='menuFrame' class='tab_text_blue pointer'> 查看详情 </span>";
                return str;
            }
        },
    ];
    orderTable = $api.getDataTable('#orderList',ajaxParams, colData);
}


/**
 * 修改订单状态
 * @param id 用户id
 * @param status 目标状态
 * @returns
 */
function changeUserCourseStatus(oid, status) {
    parent.window.openTipsDialog("提示", "确定更改状态吗？", function () {
        $api.asyncRequest("updateUserOrderStatus", "POST", {oid: oid, status: status}).then(function (res) {
            mizhu.toast(res.resmsg, 1000);
            orderTable.ajax.reload(null, false);
        });
    })
}


/**
 * 表格搜索
 */
function search(obj){
    var value = $(obj).val();
    if(orderTable){
        orderTable.search(value).draw();
    }
}


/******************************************************************************************
 *************************           订单管理列表页面-end               ********************
 ******************************************************************************************/



