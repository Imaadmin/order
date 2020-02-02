const $tools = mcfish.Tools;
const $api = mcfish.API;
$(function () {
    //获取分类列表
    getCategoriesList();
});


/******************************************************************************************
 ************************     报名课程列表页面-start            ****************************
 ******************************************************************************************/


/**
 * 获取基础信息列表
 */
function getCategoriesList() {
    var ajaxParams = {
        api: 'getCategoriesList',
        type: 'POST',
        searching: true,
        paging: true,
        data: {
    }
    };
    var colData = [
        {"data": "id", 'sClass': "text-left col-width-80"},
        {"data": "fatherCategoryName", 'sClass': "text-left col-width-160"},
        {"data": "categoryName", 'sClass': "text-left col-width-160"},
        {"data": "createTime",'sClass' : "text-center col-width-160",
            "render": function ( data, type, full, meta ) {
                return mcfish.Tools.getMyDate(data,1);
            }
        },
        {
            "data": "id", 'sClass': "text-center col-width-oper2",
            "render": function (data, type, full, meta) {
                return  "<span class='tab_text_blue pointer' onclick=deleteCategory(" + data + ");>删除</span>&nbsp;&nbsp;";
            }
        },
    ];
    categoriesTable = $api.getDataTable('#categoriesList', ajaxParams, colData);
}


/**
 * 打开添加父分类弹窗
 */
function addCategories() {

    //清除弹窗中的历史数据
    clearView();
    $("#updatabtn").attr("onclick", "saveEditInfo()");
    $("#AddCategoryView").modal("toggle");
}


/**
 * 清除分类名弹窗中的历史数据
 */
function clearView() {
    $("#categoryName").val("");
    $("#categoryNameSun").val("");
    $("#categoryId").val("")
}


/**
 * 保存新添加父分类名
 */
function saveEditInfo() {

    var categoryName = $("#categoryName").val();

    if (categoryName == "" || categoryName == null) {
        mizhu.toast("请输入分类名", 1000);
        return false;
    }

    var data = {
        categoryName: categoryName,
    };

    var url = "addCategory";


    $api.asyncRequest(url, "POST", data).then(function (res) {

        $("#AddCategoryView").modal("toggle");
        mizhu.toast(res.resmsg, 1000);
        categoriesTable.ajax.reload(null, false);
    });

}


function addSunCategories() {

    //清除弹窗中的历史数据
    clearView();
    getAllFatherCategories();

}


/**
 * 获得所有父分类
 * @returns
 */
function getAllFatherCategories() {
    var url = "getAllFatherCategories";
    $api.asyncRequest(url, "POST", null).then(function (res) {
        var str1 = '<option value="">请选择父分类</option>';
        var str = '';
        $.each(res.data, function (i, dom) {
            str += '<option value=' + dom.id + '>' + dom.categoryName + '</option>';
        });
        $("#categoryId").html(str1 + str);
    });
    $("#updatabtnSun").attr("onclick", "saveSunEditInfo()");
    $("#AddSunCategoryView").modal("toggle");
}


/**
 * 保存新添加分类名
 */
function saveSunEditInfo() {

    var categoryFatherId = $("#categoryId").val();
    var categoryNameSun = $("#categoryNameSun").val();

    if (categoryFatherId == "" || categoryName == null) {
        mizhu.toast("请选择父分类", 1000);
        return false;
    }
    if (categoryNameSun == "" || categoryNameSun == null) {
        mizhu.toast("请输入分类名", 1000);
        return false;
    }

    var data = {
        categoryNameSun: categoryNameSun,
        categoryFatherId:categoryFatherId,
    };

    var url = "addSunCategory";


    $api.asyncRequest(url, "POST", data).then(function (res) {

        $("#AddSunCategoryView").modal("toggle");
        mizhu.toast(res.resmsg, 1000);
        categoriesTable.ajax.reload(null, false);
    });

}


/**
 * 删除分类
 * @param id
 * @returns
 */
function deleteCategory(id) {
    parent.window.openTipsDialog("确定删除分类", "删除后数据将不存在", function () {
        $api.asyncRequest("deleteCategory", "POST", {id: id}).then(function (res) {
            mizhu.toast(res.resmsg, 1000);
            categoriesTable.ajax.reload(null, false);
        });
    })
}


/**
 * 表格搜索
 */
function search(flag, obj) {
    var value = $(obj).val();
    switch (flag) {
        case 1:
            if (categoriesTable) {
                categoriesTable.search(value).draw();
            }
            break;
        default:
            break;
    }
}


/******************************************************************************************
 *************************           报名课程列表页面-end               ********************
 ******************************************************************************************/





