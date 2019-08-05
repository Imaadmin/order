const $tools = mcfish.Tools
const $api = mcfish.API
const myurl = $tools.parseURL(window.location.href)
const tableFlag = parseInt(myurl.params['tableFlag'])


$(function(){
	//获取用户列表
    getUserList();
})



/*************************************************************************************************************/
/******************************             用户页面-start          **************************************/
/*************************************************************************************************************/


/**
 * 获取用户列表
 */
function getUserList(){
    var ajaxParams = {
        api: 'user/getUserList',
        type: 'GET',
        searching:true,
        data: {
        }
    }
    var colData = [
        {"data" : "head",'sClass' : "text-center col-width-100",
            "render": function ( data, type, full, meta ) {
                var str = "<img src='/static/mcfish/image/head.png' class='userImg' />";
                if(data!= null && data != ""){
                    str = "<img src='" + $tools.toHeadImage(data)+ "' class='userImg' />";
                }
                return str;
            }
        },
        {"data" : "phone",'sClass' : "text-center col-min-phone"},
        {"data" : "createTime",'sClass' : "text-center col-width-160",
            "render": function ( data, type, full, meta ) {
                return mcfish.Tools.getMyDate(data,1);
            }
        },
        {"data" : "id",'sClass' : "text-center col-width-oper2",
            "render" : function(data, type, full, meta) {
                var str = "";
                var href = $tools.getBasicUrl() + "toUserInfoPage?id=" + data;
                str += "<a  href='"+ href +"' target='menuFrame' class='tab_text_blue pointer'> 查看订单详情 </span>";
                return str;
            }
        },
    ]
    UserTable = $api.getDataTable('#userList',ajaxParams, colData);
}


/*************************************************************************************************************/
/******************************              用户页面-end          ***************************************/
/*************************************************************************************************************/



/**
 * 表格搜索
 */
function search(flag,obj){
    var value = $(obj).val();
    switch (flag){
        case 1:
            if(UserTable){
                UserTable.search(value).draw();
            }
            break;
        default:
            break;
    }
}
