<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<!-- 系统设置-用户管理 -->
<html lang="en">
<head>
<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/css/bootstrap.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/css/bootstrap.min.css" />
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
<!-- dataTables.min.css -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/modules/css/base.dataTables.min.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/modules/css/dataTables.min.css" />

<style type="text/css">
.tab-content>.tab-pane, .pill-content>.pill-pane {
	display: block;
	height: 0;
	overflow-y: hidden;
}

.tab-content>.active, .pill-content>.active {
	height: auto;
}

::-webkit-scrollbar {
	width: 5px;
}

::-webkit-scrollbar-track {
	background-color: rgb(242, 242, 242);
}

::-webkit-scrollbar-thumb {
	background-color: rgb(168, 168, 168);
}

.portlet-title>.caption {
	float: left;
	display: inline-block;
	font-size: 18px;
}

.font-dark {
	color: #2f353b !important;
}

.uppercase {
	text-transform: uppercase !important;
}

.bold {
	font-weight: 700 !important;
}

.portlet-title>.caption>.caption-helper {
	padding: 0;
	margin: 0;
	line-height: 13px;
	color: #9eacb4;
	font-size: 13px;
	font-weight: 400;
}

.portlet-title>.actions {
	float: right;
	padding-bottom: 3px;
}
.col-md-8 {
    width: 99.666%;
}
 
</style>
<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/js/ace-extra.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/vendors/jquery/js/jquery.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/vendors/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/jquery.serializeJSON-master/jquery.serializejson.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/echarts/echarts.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/vendors/layer/layer.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/js/apiv2.0.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/js/GeoUtils_min.js"></script>
</head>
<body class="no-skin" style='overflow: -Scroll; overflow-x: hidden'>
<div class="main-container" id="main-container">
	<div class="col-md-8">
		<div class="tab-content no-border padding-24">
		<div id="faq-list-1" class="panel-group accordion-style1 accordion-style2">
				<div class="panel panel-default">
					<div class="panel-collapse collapse in" id="faq-1-1">
						<div class="panel-body">
							 
								<div class="row" style="padding-top: 20px; float: left; width: 267px;">
								<div class="col-sm-12" style="border-bottom: 0px solid #eee; width: 400px; padding-left: 5px; padding-right: 5px;">
									<div class="form-group">
										<label class="col-sm-3 control-label" for="txt-applyName" style="width: 88px; height: 20px;">登录名 </label>
										<div class="col-sm-9" style="padding: 0px 0px 2px 0px; margin-top: -5px;width:65%">
										    <input  type ="text" style="width:120px;" id="loginName"  name = "loginName" value=""/>
										</div>
									</div>
								</div>
							</div>
							 
							<div class="row" style="padding-top: 20px; float: left; width: 185px;margin-left: 12px;">
								<div class="col-sm-12" style="border-bottom: 0px solid #eee; width: 400px; padding-left: 5px; padding-right: 5px;">
									<div class="form-group">
										<span class="input-icon"> 
										<button type="button" class="btn btn-sm btn-white btn-round" id="getAllEciList">
											<span class=" fa fa-search icon-on-right bigger-110"></span>
											查询
										</button>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				 <div style ="padding-bottom: 20px; padding-right: 11px;float:right" id="addUser" class="addUser">
				   <button type="button" class="btn btn-sm btn-white btn-round" id="createUser">
							<span class=" fa  bigger-110"></span>
													添加
					</button>
		  		 </div>
			</div>
		  
			<div id="faq-list-3"
				class="panel-group accordion-style1 accordion-style2">
				<div class="panel panel-default">
					 
					<div class="panel-collapse collapse in" id="faq-3-1">
						<div class="panel-body">
							<div class="widget-box widget-color-blue">
								<div class="widget-header widget-header-small">
									<h5 class="widget-title bigger lighter">
										<i class="ace-icon fa fa-list"></i>用户管理
									</h5>
									<div class="widget-toolbar">
										<a href="#" id="btn-reload"> 
											<i class="ace-icon fa fa-refresh white"></i>
										</a> 
										<a href="#" data-action="fullscreen" class="orange2">
											<i class="ace-icon fa fa-expand"></i>
										</a>
									</div>
								</div>
								<div class="widget-body">
									<div class="widget-main no-padding">
										<table id="problemEciTable" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
												    <th>登录名</th>
												    <th>用户名</th>
												    <th>所属地市</th>
													<th>管理员</th>
													<th>操作</th>
												</tr>
											</thead>
											</tbody>
										</table>
									</div>
									<div id="pageOutContainer" class="pageOutContainer"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<myscript>
<!--日期控件  -->
<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/scripts/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/modules/js/common/common.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/modules/js/userManager.js"></script>
<!-- dataTables -->
<script
	src="<%=request.getContextPath()%>/static/scripts/ace-1.3.4/assets/js/dataTables/jquery.dataTables.js"></script>
<script
	src="<%=request.getContextPath()%>/static/scripts/ace-1.3.4/assets/js/dataTables/jquery.dataTables.bootstrap.js"></script>
<script
	src="<%=request.getContextPath()%>/static/scripts/ace-1.3.4/assets/js/dataTables/extensions/buttons/dataTables.buttons.js"></script>
<script
	src="<%=request.getContextPath()%>/static/scripts/ace-1.3.4/assets/js/dataTables/extensions/buttons/buttons.flash.js"></script>
<script
	src="<%=request.getContextPath()%>/static/scripts/ace-1.3.4/assets/js/dataTables/extensions/buttons/buttons.html5.js"></script>
<script
	src="<%=request.getContextPath()%>/static/scripts/ace-1.3.4/assets/js/dataTables/extensions/buttons/buttons.print.js"></script>
<script
	src="<%=request.getContextPath()%>/static/scripts/ace-1.3.4/assets/js/dataTables/extensions/buttons/buttons.colVis.js"></script>
<script
	src="<%=request.getContextPath()%>/static/scripts/ace-1.3.4/assets/js/dataTables/extensions/select/dataTables.select.js"></script>
	<!-- dataTables  end-->
<script type="text/javascript">

jQuery(function($) {
	var table_apply = Table_SuperSearchApplyList({});
	table_apply.show('problemEciTable', {});
	
	//小区列表高度
	var mainContainerHeight = $($('#main-container').context).height();
	$('#apply-table_wrapper .dataTables_scroll').css("min-height",(mainContainerHeight - 135)+"px");
	
	//初始化市区
	getCityList({"vrank":4});
	
	//查询按钮
	$("#getAllEciList").on("click",function(e){
		//startRow值 = cdate + city_code + area_code
	    var reg = new RegExp("-","g");//g,表示全部替换。
	    var cdate = $("#cdate").val().replace(reg, '');
		var city_code = $("#sel-city").find("option:selected").val();
		var area_code = $("#sel-area").find("option:selected").val();
		var coverType = $("#coverType").find("option:selected").val();
		if (city_code=='') {
			layer.alert("请选择地市!");
			return false;
		}
		if (area_code=='') {
			layer.alert("请选择区县!");
			return false;
		}
		if(coverType == ''){
			layer.alert("请选择覆盖类型!");
			return false;
		}
		
		if (cdate=='') {
			layer.alert("请选择日期!");
			return false;
		}
		var loading1 = layer.msg('查询中...', {
			icon: 16,
			shade: 0.5,
			offset: 't',
		});
		var startRow = cdate +coverType+ city_code + area_code;
		var datatableUrl = projectName+"/home/netStructOpt/getEciList.json?startRow="+startRow;
		$('#problemEciTable').DataTable().ajax.url( datatableUrl ).load();
		
	})
	
	
});
</script>
</myscript>
</html>