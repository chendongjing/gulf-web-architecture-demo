<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglibs.jspf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>全网健康度</title>
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
<div class="widget-toolbox" id="widget-toolbox-1">
					<div class="input-group">
					<span class="input-icon">地市:
					    <select id="sel-city" name="sel-city"
						style="width: 70px !important;border-radius:4px;height:28px;padding-top: 0px;" placeholder="请选择地市" onChange="getAreaList();">
						<option value=""></option>
						</select>
						<input type="hidden" id="city-lng"/>
				        <input type="hidden" id="city-lat"/>
					</span>
					<span class="input-icon">
					日期 :
					<input style="border:0px;border-radius:4px;width:128px;height:24px;padding-top: 3px;" type="text" id="cdate" name="cdate" placeholder="请选择日期">
					</span>
						 <span class="input-icon" style="padding-left: 10px;">
							<button type="button" class="btn btn-sm btn-white btn-round" id="btn-point">
								<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
								查询
							</button>
						</span>
					</div>
				 
				</div>
		<!-- PAGE CONTENT BEGINS -->
		<div class="widget-box widget-color-blue col-md-12">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="row">
						<div class="space-6"></div>

						<div class="col-sm-6">
							<div class="widget-box">
								<div class="widget-body">
									<div class="widget-main">
										<div id="pie1-main" style="width: 100%; min-height: 280px;"></div>
									</div>
								</div>
							</div>
						</div>

						<div class="vspace-12-sm"></div>

						<div class="col-sm-6">
							<div class="widget-box">
								<div class="widget-body">
									<div class="widget-main">
										<div id="pie2-main" style="width: 100%; min-height: 280px;"></div>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="space-6"></div>

						<div class="col-sm-6">
							<div class="widget-box">
								<div class="widget-body">
									<div class="widget-main">
										<div id="line-main" style="width: 100%; min-height: 300px;"></div>
									</div>
								</div>
							</div>
						</div>

						<div class="vspace-12-sm"></div>

						<div class="col-sm-6">
							<div class="widget-box">
								<div class="widget-body">
									<div class="widget-main">
										<div id="bar-main" style="width: 100%; min-height: 300px;"></div>
									</div>
								</div>
							</div>
						</div>

					</div>

					<!-- PAGE CONTENT ENDS -->
				</div>
			</div>
		</div>
	</div>	
</body>

  <myscript>
  		<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/js/ace-extra.js"></script>
    	<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/vendors/jquery/js/jquery.min.js?v=2.1.4"></script>
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
		
  		<script type="text/javascript" src="<%= request.getContextPath() %>/static/scripts/modules/js/wholeNetFacts.js"></script>
	  	<script type="text/javascript">
		jQuery(function($) {
			var cdate = $("#cdate").val().replace(/-/g, '');
			var city_code = $("#sel-city").val();
			var startRow = cdate+city_code;
			var params = {
				"startRow":startRow,
			};

			// 全网分布
			var pie1 = pie_showScalePie({});
			pie1.show("pie1-main", params);
           //地市分布
			var pie2 = pie_showAreaPie({});
			pie2.show("pie2-main", params);
           //月趋势图
			var line = line_tendencyChart({});
			line.show("line-main", params);
           //分体类别数目分布
			var bar = bar_classChart({});
			bar.show("bar-main", params);
			
			//初始化市区
			getCityList({"vrank":4});
		});
		
		//查询按钮
		$("#btn-point").on("click",function(e){
			 cdate = $("#cdate").val().replace(/-/g, '');
			 city_code = $("#sel-city").val();
			 startRow = cdate+city_code;
			 params = {
				"startRow":startRow,
			};
			var loading1 = layer.msg('查询中...', {
				icon: 16,
				shade: 0.5,
				offset: 't',
			});
			// 全网分布
			var pie1 = pie_showScalePie({});
			pie1.show("pie1-main", params);
           //地市分布
			var pie2 = pie_showAreaPie({});
			pie2.show("pie2-main", params);
           //月趋势图
			var line = line_tendencyChart({});
			line.show("line-main", params);
           //分体类别数目分布
			var bar = bar_classChart({});
			bar.show("bar-main", params);
			
			
		})
	  	 
	    </script>
  </myscript>
  
</html>  