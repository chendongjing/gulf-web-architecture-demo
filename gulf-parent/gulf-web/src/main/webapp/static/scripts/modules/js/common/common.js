var curUsername = $('#head_username', window.parent.document).val();//获取当前用户
var curUserid = $('#head_userid', window.parent.document).val();//当前用户id
var basePath=window.document.location.pathname;
var projectName=basePath.substring(0,basePath.substr(1).indexOf('/')+1);

/**
 * 获取服务器上的百度地图地址
 * 这里是放百度地图的，你可以连在线的，也可以下载下来连离线的
 */
getMapPic = function(){
	return "http://192.168.5.145:8080/hnMapPic/";
}

//临时存储市区
var cityList;

/**
 * 
 * param {"vrank":4}
 * 获取市和区县
 * @returns
 */
function getCityList(param){
	var url = projectName+"/home/getCityAndArea/list.json";
	//绑定Ajax的内容
    $.ajaxSettings.async = false; //同步
    $.getJSON(url, param, function (result) {
    	$("#sel-city").empty();//清空下拉框
    	$("#sel-area").empty();
    	cityList = result.data;
        $.each(cityList, function (i, item) {
        	for(var j = 0; j < cityList.length; j++){
	        	if(cityList[j].vid == item.pid && item.vrank =='2'){
	        		$("#sel-city").append("<option value='"+ item.vid + "'>" + item.vname + "</option>");
	        	}
        	}
        });
        
        $("#sel-city option:first").attr("selected", "selected");//默认选中第一项
        
    });
    getAreaList();
    $.ajaxSettings.async = true;//恢复异步
}

/**
 * 获取区县
 * @returns
 */
function getAreaList(){
	$('#sel-area').empty();//清空下拉框
	var sel_city = $("#sel-city").find("option:selected").val();
	$('#sel-area').append("<option value=''>请选择</option>");
	$.each(cityList, function (i, item) {
    	if(sel_city == item.pid && item.vrank == '3'){
    		$('#sel-area').append("<option value='"+ item.vid + "'>" + item.vname + "</option>");
    	}
    });
}


/**
 * 获取地图定位经纬度
 * 如果区县不为空则定位区县的位置，否则定位地市的位置
 * 
 * @returns
 */
function getLngLat(){
	var sel_city = $("#sel-city").find("option:selected").val();
	var sel_area = $("#sel-area").find("option:selected").val();
	if(sel_area !== ''){
		$.each(cityList, function (i, item) {
			if(sel_city == item.pid && item.vrank == '3'){
	    		$("#area-lng").val(item.lng);
	    		$("#area-lat").val(item.lat);
	    	}
	    });
		
	}else{
		
		$.each(cityList, function (i, item) {
			
        	if(sel_city == item.vid && item.vrank =='2'){
	    		$("#city-lng").val(item.lng);
	    		$("#city-lat").val(item.lat);
    	    }
			
	    });
	}
	
}

//时间控件设置默认时间位当前时间
$("#cdate").datetimepicker({
	language : 'zh-CN',//显示中文
	format : 'yyyy-mm-dd',//显示格式
	minView : "month",//设置只显示到月份
	initialDate : new Date(),//初始化当前日期
	autoclose : true,//选中自动关闭
	todayBtn : true//显示今日按钮
});
//获取今天的前一天  yyyy-mm-dd
function get_today() {
    var date = new Date();
    var seperator1 = "-";
    var month = date.getMonth() + 1;
    var strDate = date.getDate()-1;
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
             
    return currentdate;
}

$("#cdate").val(get_today());
