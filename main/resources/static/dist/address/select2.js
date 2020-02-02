//插件初始化
var province = $("#province, #province1"), city = $("#city, #city1"), town = $("#town, #town1");
for (var i = 0; i < provinceList.length; i++) {
    if (i == 0) {
        province.append("<option value=" + "" + ">" + "请选择" + "</option>");
    }
    addEle(province, provinceList[i].name);
}

function addEle(ele, value) {
    var array = value.split(",");
    var optionStr = "<option value=" + array[1] + ">" + array[0] + "</option>";
    ele.append(optionStr);
}

function removeEle(ele) {
    /*ele.find("option").remove();*/
    ele.empty();
    var optionStar = "<option value=" + "" + ">" + "请选择" + "</option>";
    ele.append(optionStar);
}

var provinceText, cityText, cityItem;
province.on("change", function () {
    var option = $("#province option:selected");
    provinceText = option.text();
    $.each(provinceList, function (i, item) {
        var array = item.name.split(",");
        if (provinceText == array[0]) {
            cityItem = i;
            return cityItem
        }
    });
    removeEle(city);
    removeEle(town);
    $.each(provinceList[cityItem].cityList, function (i, item) {
        addEle(city, item.name)
    })
});
city.on("change", function () {
    $("#newR").show();			//新建电子围栏信息选择了省/市就要显示新建电子围栏按钮
    $("#newC").show();
    var option = $("#city option:selected");
    cityText = option.text();
    removeEle(town);
    $.each(provinceList, function (i, item) {
        var array = item.name.split(",");
        if (provinceText == array[0]) {
            cityItem = i;
            return cityItem
        }
    });
    $.each(provinceList[cityItem].cityList, function (i, item) {
        var array = item.name.split(",");
        if (cityText == array[0]) {
            for (var n = 0; n < item.areaList.length; n++) {
                addEle(town, item.areaList[n])
            }
        }
    });
});



