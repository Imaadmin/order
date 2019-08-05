const $tools = mcfish.Tools
const $api = mcfish.API
const myurl = $tools.parseURL(window.location.href)
const oid = myurl.params['oid']

$(function () {
    //获取订单详细信息
    $("#info-id").html(oid);
    getOrderInfoList();
})



/**
 * 获取订单详情列表
 */
function getOrderInfoList() {
    var ajaxParams = {
        api: 'getOrderInfoList',
        type: 'GET',
        searching: true,
        data: {
            oid: oid,
        }
    }
    var colData = [
        {"data": "varietyName", 'sClass': "text-left col-width-160"},
        {"data": "subtotal", 'sClass': "text-left col-width-160",
            "render": function ( data, type, full, meta ) {
                return mcfish.Tools.toMoney(data,2 );
            }
        },
        {"data": "count", 'sClass': "text-left col-width-160"},
        {"data" : "subtotal",'sClass' : "text-left col-width-160",
            "render": function ( data, type, full, meta ) {
                return mcfish.Tools.toMoney(data * full.count ,2 );
            }
        },

    ]
    Usertable = $api.getDataTable('#userOrderList', ajaxParams, colData);
}





