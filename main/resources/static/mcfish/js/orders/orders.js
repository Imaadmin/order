const $tools = mcfish.Tools;
const $api = mcfish.API;
$(function(){
    //获取订单列表
    getOrderList();
})


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
    }
    var colData = [
        {"data" : "orderOid",'sClass' : "text-left col-min-website"},
        {"data" : "phone",'sClass' : "text-center col-min-phone"},
        {"data": "total", 'sClass': "text-left col-width-120",
            "render": function ( data, type, full, meta ) {
                return mcfish.Tools.toMoney(data,2);
            }
        },
        {"data": "state", 'sClass': "text-center col-width-120",
            "render": function ( data, type, full, meta ) {
                var str = "";
                if (data == 1) {
                    str = "<span class='label label-danger'>未支付</span> ";
                }
                if (data == 2) {
                    str = "<span class='label label-warning'>已支付未上餐</span>";
                }
                if (data == 3) {
                    str = "<span class='label label-success'>已上餐</span>";
                }
                return str;
            }
        },
        {"data" : "createTime",'sClass' : "text-center col-width-160",
            "render": function ( data, type, full, meta ) {
                return mcfish.Tools.getMyDate(data,1);
            }
        },
        {"data" : "orderOid",'sClass' : "text-center col-width-oper2",
            "render" : function(data, type, full, meta) {
                var str = ""
                var href = $tools.getBasicUrl() + "toOrderInfoPage?oid=" + data;
                str += "<a  href='"+ href +"' target='menuFrame' class='tab_text_blue pointer'> 查看详情 </span>";
                return str;
            }
        },
    ]
    orderTable = $api.getDataTable('#orderList',ajaxParams, colData);
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



