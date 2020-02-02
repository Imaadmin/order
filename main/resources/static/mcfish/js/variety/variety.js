const $tools = mcfish.Tools;
const $api = mcfish.API;
$(function () {

    //获取所有分类列表
    getAllCategories();
    //获取菜品列表
    getVarietyList();
    $("#picUrl").change(function () {

        var file = $(this)[0].files[0];

        if (!file) {
            mizhu.toast('上传文件不能为空');
            return
        }
        if (!$tools.fileTypeJudge(file, ["jpg", "png", "jpeg"])) {
            mizhu.toast('请上传正确格式的文档');
            return
        }

        var form = new FormData();    // FormData 对象

        form.append("file", file);    // 文件对象

        $api.processRequest('fileUpload', 'POST', form).then(function (res) {
            $("#varietyIcon").attr("src", res.data );

        })
    });

    $("#deleteImg").click(function () {
        $("#varietyIcon").attr("src", "");
        $("#picUrl").val("");
    });
});


/******************************************************************************************
 ************************     商品管理列表页面-start            ****************************
 ******************************************************************************************/


/**
 * 获得所有分类
 * @returns
 */
function getAllCategories() {
    var url = "getAllCategories";
    $api.asyncRequest(url, "POST", null).then(function (res) {
        var str1 = '<option value="">请选择分类</option>';
        var str = '';
        $.each(res.data, function (i, dom) {
            str += '<option value=' + dom.id + '>' + dom.categoryName + '</option>';
        });
        $("#categoryId").html(str1 + str);
    });
    getVarietyList();
}


/**
 * 获得所有分类
 * @returns
 */
function getAllCategories2() {
    var url = "getAllCategories";
    $api.asyncRequest(url, "POST", null).then(function (res) {
        var str1 = '<option value="">请选择分类</option>';
        var str = '';
        $.each(res.data, function (i, dom) {
            str += '<option value=' + dom.id + '>' + dom.categoryName + '</option>';
        });
        $("#category").html(str1 + str);
    })
}


/**
 * 获取菜品列表
 */
function getVarietyList() {

    var categoryId = $("#categoryId").val();
    var ajaxParams = {
        api: 'getVarietyList',
        type: 'POST',
        searching: true,
        paging: true,
        data: {
            categoryId: categoryId
        }
    };
    var colData = [
        {
            "data": "url", 'sClass': "text-center col-width-120",
            "render": function (data, type, full, meta) {
                if (data != null && data != "") {
                    return "<a href='" + data + "' target='blank'><img src='" + data + "' class='couponImg' /></a>";
                }
                return "<img src='" + data + "' class='couponImg' />";
            }
        },
        {"data": "varietyName", 'sClass': "text-left col-width-160"},
        {"data": "categoryName", 'sClass': "text-left col-width-160"},
        {"data": "price", 'sClass': "text-left col-width-120",
            "render": function ( data, type, full, meta ) {
                return mcfish.Tools.toMoney(data,2 );
            }
        },
        {"data": "count", 'sClass': "text-left col-width-80"},
        {"data": "contents", 'sClass': "text-left col-width-160"},
        {
            "data": "createTime", 'sClass': "text-center col-width-180",
            "render": function (data, type, full, meta) {
                return mcfish.Tools.getMyDate(data, 1);
            }
        },
    ];
    VarietyTable = $api.getDataTable('#VarietyList', ajaxParams, colData);
}


/**
 * 打开添加报名课程弹窗
 */
function addVariety() {

    //清除弹窗中的历史数据
    clearView();
    $("#updatabtn").attr("onclick", "saveEditInfo()");
    $("#AddVarietyView").modal("toggle");
    getAllCategories2();
}


/**
 * 清除代理弹窗中的历史数据
 */
function clearView() {
    $("#category").children().remove();
    $("#varietyName").val("");
    $("#price").val("");
    $("#contents").val("");
    $("#count").val("");
    $("#varietyIcon").attr("src", "http://wx.qlogo.cn/mmopen/vi_32/nrZwlTIgnN95WrbOdcJHYfGR6PqAd3NEVNevWIlADOibM8ojK51moJcgUyia8lcUmOVk8PkliaA3V9jBfemicdJQOA/0");
}


/**
 * 保存新添商品
 */
function saveEditInfo() {

    var url = $("#varietyIcon").attr("src");
    var cid = $("#category").val();
    var varietyName = $("#varietyName").val();
    var contents = $("#contents").val();
    var price = $("#price").val();
    var count = $("#count").val();

    if (!varietyName) {
        mizhu.toast("请输入商品名", 1000);
        return false;
    }
    if (!cid) {
        mizhu.toast("请选择分类", 1000);
        return false;
    }
    if (!contents) {
        mizhu.toast("请输入简介", 1000);
        return false;
    }
    if (!price) {
        mizhu.toast("请输入价格", 1000);
        return false;
    }
    if (!count) {
        mizhu.toast("请输入库存", 1000);
        return false;
    }

    var reg = /^[0-9]+(.[0-9]{2})?$/;
    if (!reg.test(price)) {
        mizhu.toast("请输入格式正确的价格 如1.00", 1000);
        return false;
    }
    var reg = /^[0-9]?$/;
    if (!reg.test(count)) {
        mizhu.toast("请输入一个整数", 1000);
        return false;
    }
    if (!url) {
        mizhu.toast("请上传图片", 1000);
        return false;
    }
    var data = {
        url:         url,
        cid:         cid,
        varietyName: varietyName,
        contents:    contents,
        price:       price,
        count:       count,
    };

    var url = "addVariety";
    $api.asyncRequest(url, "POST", data).then(function (res) {

        $("#AddVarietyView").modal("toggle");
        mizhu.toast(res.resmsg, 1000);
        VarietyTable.ajax.reload(null, false);
    });

}


/**
 * 表格搜索
 */
function search(obj) {
    if (VarietyTable) {
        var value = $(obj).val();
        VarietyTable.search(value).draw();
    }
}


/******************************************************************************************
 *************************           菜品管理列表页面-end               ********************
 ******************************************************************************************/

