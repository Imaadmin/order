const $tools = mcfish.Tools;
const $api = mcfish.API;


var sdata = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
var xdata = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31];

var sdata2 = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
var xdata2 = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31];


$(function(){
	
	//获取平台头部数据信息 
	getHomeData();
	
	//用户曲线图
	getUserEveryday();
	
	//订单曲线图
	getOrderEveryday();

});


/**
 * 获取平台头部数据信息 
 */
function getHomeData(){
	$api.asyncRequest("getHomeData","GET").then(function(res){
		var data = res.data;
		$("#totalUsers").html(data.totalUsers);
		$("#totalEquipment").html(data.totalVariety);
		$("#totalOrder").html(data.totalOrder);		
	});
}



/**
 * 用户曲线图
 */
function getUserEveryday() {
	var myChart = echarts.init(document.getElementById("user-chart"));
	option1 = {
		title: {
			text: '本月用户数',
			x: 'center'
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				label: {
					backgroundColor: '#283b56'
				}
			}
		},
		legend: {
			data: ['用户人数'],
			x: 'left'
		},
		toolbox: {
			show: true,
			feature: {
				dataView: {
					readOnly: true
				},
				magicType: {
					show: true,
					type: ['line', 'bar']
				},
				restore: {
					show: true
				}
			}
		},
		dataZoom: {
			show: false,
			start: 0,
			end: 100
		},
		xAxis: [{
			type: 'category',
			boundaryGap: true,
			axisLabel: {
				interval: 0, //横轴信息全部显示
				splitNumber: 0,
				scale: true,
				rotate: 0
			},
			data: xdata
		}],
		yAxis: [{
			type: 'value',
			scale: true,
			name: '人数',
			min: 0,
			splitLine: {
				show: true
			}
		}],
		series: [{
				name: '该日用户人数',
				type: 'bar',
				showSymbol: false,
				hoverAnimation: false,
				data: sdata

			},

		]
	};

	
	$api.asyncRequest("getEverydayUser","GET").then(function(res){
		var item = res.data;
		var st = 0;
		var sum = 0;
		for(var i = 0; i < item.length; i++) {
			st = parseInt(item[i].time) - 1;
			sdata.splice(st, 0, item[i].userCount);
			sum += item[i].userCount;
		}
		option1.title.text = '本月用户数('+sum+')';
		myChart.setOption(option1, true);
	});
}


/**
 * 订单曲线图
 */
function getOrderEveryday() {
	var myChart2 = echarts.init(document.getElementById("line-chart1"));
	option = {
		title: {
			text: '本月订单数',
			x: 'center'
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				label: {
					backgroundColor: '#283b56'
				}
			}
		},
		legend: {
			data: ['订单数'],
			x: 'left'
		},
		toolbox: {
			show: true,
			feature: {
				dataView: {
					readOnly: true
				},
				magicType: {
					show: true,
					type: ['line', 'bar']
				},
				restore: {
					show: true
				}
			}
		},
		dataZoom: {
			show: false,
			start: 0,
			end: 100
		},
		xAxis: [{
			type: 'category',
			boundaryGap: true,
			axisLabel: {
				interval: 0, //横轴信息全部显示
				splitNumber: 0,
				scale: true,
				rotate: 0
			},
			data: xdata2
		}],
		yAxis: [{
			type: 'value',
			scale: true,
			name: '订单数',
			min: 0,
			splitLine: {
				show: true
			}
		}],
		series: [{
				name: '该日订单数',
				type: 'bar',
				showSymbol: false,
				hoverAnimation: false,
				data: sdata2

			},

		]
	};


	$api.asyncRequest("getEverydayOrder","GET").then(function(res){
		var item2 = res.data;
		var st2 = 0;
		var sum = 0;
		for(var i = 0; i < item2.length; i++) {
			st2 = parseInt(item2[i].time) - 1;
			sdata2.splice(st2, 0, item2[i].orderCount);
			sum += item2[i].orderCount;
		}
		option.title.text = '本月订单数('+sum+")";
		myChart2.setOption(option, true);
	});
	
}