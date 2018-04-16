<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<!-- 网络优化-重复覆盖小区画像(地图) -->
<html lang="en">
	<head>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/css/bootstrap.css" />
		<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/css/font-awesome.css" />
		<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/vendors/font-awesome/css/font-awesome.min.css" />
		<!-- bootstrap datetimepicker -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/scripts/modules/css/bootstrap-datetimepicker.min.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/bootstrap-addtabs/bootstrap.addtabs.css" type="text/css"/>
		<!-- ace settings handler -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/css/chosen.css" />
	</head>
	<body class="no-skin">
		<div class="main-container" id="main-container">
		<!-- PAGE CONTENT BEGINS -->
		<div class="widget-box widget-color-blue">
			<div class="widget-header widget-header-small">
				<h5 class="widget-title bigger lighter"><i class="ace-icon fa fa-table"></i><span id="eci-name"></span><span id="eci-id" style="display:none"></span></h5>
				<div class="widget-toolbar">
					<a href="#" data-action="fullscreen" class="orange2"><i class="ace-icon fa fa-expand"></i></a>					
				</div>
			</div>
			<div class="widget-body  no-padding">
				<div class="widget-toolbox" id="widget-toolbox-1">
					<div class="input-group">
					<span class="input-icon">地市:
					    <select id="sel-city" name="sel-city"
						style="width: 70px !important;border-radius:4px;height:28px;padding-top: 0px;" onChange="getAreaList();">
						<option value=""></option>
						</select>
						<input type="hidden" id="city-lng"/>
				        <input type="hidden" id="city-lat"/>
					</span>
					<span class="input-icon">区县:
					    <select id="sel-area" name="sel-area"
						style="width: 80px !important;border-radius:4px;height:28px;padding-top: 0px;"  onChange="getLngLat();">
						<option value=""></option>
					</select>
					<input type="hidden" id="area-lng"/>
				    <input type="hidden" id="area-lat"/>
					</span>
					<span class="input-icon">
					日期 :
					<input style="border:0px;border-radius:4px;width:128px;height:24px;padding-top: 3px;" type="text" id="cdate" name="cdate" placeholder="请选择日期" >
					</span>
						 <span class="input-icon" style="padding-left: 10px;">
							<button type="button" class="btn btn-sm btn-white btn-round" id="btn-point">
								<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
								重叠覆盖定位
							</button>
						</span>
					</div>
				</div>
				<div class="widget-main no-padding">
					<div id="map-main" style="width: 100%; min-height: 600px;overflow: hidden;margin:0;font-family:'微软雅黑';"></div>
				</div>
			</div>
		</div>
		<!-- PAGE CONTENT ENDS -->
		</div>
	</body>
	<myscript>				
	    <script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/js/ace-extra.js"></script>
    	<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/vendors/jquery/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/vendors/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/jquery.serializeJSON-master/jquery.serializejson.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/echarts/echarts.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/vendors/layer/layer.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/js/apiv2.0.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/js/GeoUtils_min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/js/getmodules2.0.js"></script>
		<!--日期控件  -->
		<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/scripts/js/bootstrap-datetimepicker.zh-CN.js"></script>
		
		<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/modules/js/common/common.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/modules/js/repeatCoverageDist.js"></script>
		<script type="text/javascript">
			jQuery(function($) {
				jQuery.MapConvert = {
					    x_pi: 3.14159265358979324 * 3000.0 / 180.0,
					    /// <summary>
					    /// 中国正常坐标系GCJ02协议的坐标，转到 百度地图对应的 BD09 协议坐标
					    ///  point 为传入的对象，例如{lat:xxxxx,lng:xxxxx}
					    /// </summary>
					    Convert_GCJ02_To_BD09: function (point) {
					        var x = point.lng, y = point.lat;
					        var z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * jQuery.MapConvert.x_pi);
					        var theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * jQuery.MapConvert.x_pi);
					        point.lng = z * Math.cos(theta) + 0.0065;
					        point.lat = z * Math.sin(theta) + 0.006;
					    },
					    /// <summary>
					    /// 百度地图对应的 BD09 协议坐标，转到 中国正常坐标系GCJ02协议的坐标
					    /// </summary>
					    Convert_BD09_To_GCJ02: function (point) {
					        var x = point.lng - 0.0065, y = point.lat - 0.006;
					        var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * jQuery.MapConvert.x_pi);
					        var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * jQuery.MapConvert.x_pi);
					        point.lng = z * Math.cos(theta);
					        point.lat = z * Math.sin(theta);
					    }
					}
				
				var map = Map_Depthcoverage({});
				
				//初始化市区
				getCityList({"vrank":4});
				//获取地图初始定位
				getLngLat();
				
				var lng = $('#city-lng').val();
				var lat = $('#city-lat').val();
				//初始化地图
				map.show("map-main", {"lng": lng , "lat": lat,"keyRow":""});
				
				//区县定位按钮
				$("#btn-point").on("click",function(e){
					if ($('#city-lng').val()=='') {
						layer.alert("请选择地市!");
						return false;
					}
					if ($('#area-lng').val()=='') {
						layer.alert("请选择区县!");
						return false;
					}
					if ($('#cdate').val()=='') {
						layer.alert("请选择日期!");
						return false;
					}
					
					lng = $('#area-lng').val();
					lat = $('#area-lat').val();
				    //keyRow值 = cdate + city_code + area_code
				    var reg = new RegExp("-","g");//g,表示全部替换。
				    cdate = $("#cdate").val().replace(reg, '');
					city_code = $("#sel-city").find("option:selected").val();
					area_code = $("#sel-area").find("option:selected").val();
					var keyRow = cdate +"3"+ city_code + area_code;  //3表示是重叠覆盖
					map.show("map-main", {"lng": lng , "lat": lat,"keyRow":keyRow});
				});
				
			});
			</script>
	</myscript>
</html>