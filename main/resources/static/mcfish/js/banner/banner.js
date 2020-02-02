const $tools = mcfish.Tools;
const $api = mcfish.API;

$(function(){
	//获取优惠券列表
	getCouponList();

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
            $("#actionCouponImg").attr("src", res.data );

        })
    });

    $("#deleteImg").click(function () {
        $("#actionCouponImg").attr("src", "");
        $("#picUrl").val("");
    });
});


/**
 * 获取优惠券列表
 */
function getCouponList(){
	
	var status = $("#couponStatus").val();
	
	var ajaxParams = {
			api			: 'getAllCoupon',
			type		: 'GET',
			searching	: true,
			data		: {
							status :status
						}
	 };
	 var colData = [
	 				{"data" : "bannerImg",'sClass' : "text-center col-width-120",
	 					"render" : function(data, type, full, meta) {
                            if (data != null && data != "") {
                                return "<a href='" + data + "' target='blank'><img src='" + data + "' class='couponImg' /></a>";
                            }
                            return "<img src='" + data + "' class='couponImg' />";
                        }
	 				},
	 				{"data" : "bannerUrl",'sClass' : "text-center col-width-120"},
	 				{"data" : "contents",'sClass' : "text-center col-width-220"},
					{"data" : "state",'sClass' : "text-center col-width-80",
	 					"render" : function(data, type, full, meta) {
							var str = "";
							if(data== 1){
								 str="<span class='label label-success'>上线</span> ";
							} 
							if(data == 0) {
								str="<span class='label label-danger'>下线</span>";
							}
                        	return str;
						}
	 				},
					 {"data" : "createTime",'sClass' : "text-center col-width-160",
						 "render": function ( data, type, full, meta ) {
							 return mcfish.Tools.getMyDate(data,1);
						 }
					 },
					{"data" : "id",'sClass' : "text-center col-width-oper3",
						"render" : function(data, type, full, meta) {
							var str = "<span class='tab_text_blue pointer' onclick=openActionCouponView(" + data +");>编辑</span>&nbsp;&nbsp;";
							if(full.state == 0){
								str += "<span class='tab_text_blue pointer' onclick=updateCouponStatus(" + data +",1);>上线</span>&nbsp;&nbsp;";
							}else{
								str += "<span class='tab_text_blue pointer' onclick=updateCouponStatus(" + data +",0);>下线</span>&nbsp;&nbsp;";
							}
							str += "<span class='tab_text_blue pointer' onclick=delCoupon(" + data +");>删除</span>";
							return str;
						}
					},
				];
	couponTable = $api.getDataTable('#couponList',ajaxParams, colData);
}



/**
 * 删除Banner
 * @param {Object} id
 */
function delCoupon(id){
	parent.window.openTipsDialog("确定删除","删除后数据将不存在",function(){
		$api.asyncRequest("deletCoupon","POST",{id:id}).then(function(res){
			mizhu.toast(res.resmsg,1000);
			couponTable.ajax.reload(null,false);
		});
	})
}


/**
 * 修改Banner状态
 * @param {Object} id banner ID
 * @param {Object} status 目标状态
 */
function updateCouponStatus(id, status){
	parent.window.openTipsDialog("提示","确定修改Banner状态",function(){
		$api.asyncRequest("updateCoupon","POST",{id:id,status:status}).then(function(res){
			mizhu.toast(res.resmsg,1000);
			couponTable.ajax.reload(null,false);
		});
	})
}



/**
 * 打开添加编辑/添加弹窗
 * @param {Object} id 存在编辑、不存在新增
 */
function openActionCouponView(id){
	
	//清除Banner弹窗中的历史数据
	clearCouponView();
	
	if(id){
		$("#couponBoxTitle").html("编辑Banner");
		$("#actionCouponBtn").attr("onclick","comfirmSaveCoupon("+ id +")");
		
		$api.asyncRequest("getCouponInfo","POST",{id:id}).then(function(res){
			var data = res.data;
			
			$tools.setImage("actionCouponImg",data.bannerImg);
			$("#actionCouponUrl").val(data.bannerUrl);
			$("#actionCouponStatus").val(data.state);
			$("#actionCouponBrief").val(data.contents);

			$("#actionCouponView").modal("toggle");
		});
		
	}else{
		$("#couponBoxTitle").html("新增Banner");
		$("#actionCouponBtn").attr("onclick","comfirmSaveCoupon()");
		$("#actionCouponView").modal("toggle");
	}
}


/**
 * 清除Banner弹窗中的历史数据
 */
function clearCouponView(){
	$tools.setImage("actionCouponImg",null);
	$("#actionCouponBrief").val("");
	$("#actionCouponUrl").val("");
	$("#actionCouponStatus").val(0);
}


/**
 * 保存Banner
 * @param {Object} id
 */
function comfirmSaveCoupon(id){
	
	var image		= $("#actionCouponImg").attr("src");
	var brief		= $("#actionCouponBrief").val();
	var url			= $("#actionCouponUrl").val();
	var status		= $("#actionCouponStatus").val();
	
	if(image == ""){
		mizhu.toast("请上传Banner图片",1000);
		return false;
	}

	if(brief == ""){
		mizhu.toast("请对Banner进行摘要说明",1000);
		return false;
	}

	var data = {
		image	: image,
		brief	: brief,
		url		: url,
		status	: status
	};
	
	var url = "addCoupon";
	
	if(id){
		data.id = id;
		url 	= "updateCoupon";
	}
	
	$api.asyncRequest(url,"POST",data).then(function(res){
		$("#actionCouponView").modal("toggle");
		mizhu.toast(res.resmsg,1000);
		couponTable.ajax.reload(null,false);
	});
}


/**
 * 表格搜索
 */
function search(flag,obj){
	var value = $(obj).val();
	switch (flag){
		case 1:
			if(couponTable){
				couponTable.search(value).draw();
			}
			break;
		default:
			break;
	}
}