<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title><spring:message code="sys.user.title" arguments="${platformName}"/></title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- 导入CSS -->
		<!-- 导入CSS -->
		<link rel="shortcut icon" href="<%= request.getContextPath() %>/static/common/favicon.ico">
		<link href="<%= request.getContextPath() %>/static/scripts/vendors/bootstrap/css/bootstrap.min.css?v=v3.2.0" rel="stylesheet">
		<link href="<%= request.getContextPath() %>/static/scripts/vendors/font-awesome/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
		<!-- text fonts -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/css/ace-fonts.min.css">

		<!-- ace styles -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style">

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/css/ace-skins.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/css/ace-rtl.min.css">
		

		<!-- 导入CSS --><!--[if lte IE 9]>
		  <link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/css/ace-ie.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/css/ace-tab.css">

		<link rel="stylesheet" href="<%= request.getContextPath() %>/static/scripts/vendors/layer/skin/layer.css" id="layui_layer_skinlayercss" style=""><style>@keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}@-moz-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}@-webkit-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}@-ms-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}@-o-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}.ace-save-state{animation-duration:10ms;-o-animation-duration:10ms;-ms-animation-duration:10ms;-moz-animation-duration:10ms;-webkit-animation-duration:10ms;animation-delay:0s;-o-animation-delay:0s;-ms-animation-delay:0s;-moz-animation-delay:0s;-webkit-animation-delay:0s;animation-name:nodeInserted;-o-animation-name:nodeInserted;-ms-animation-name:nodeInserted;-moz-animation-name:nodeInserted;-webkit-animation-name:nodeInserted}</style></head>

	</head>

	<body class="no-skin" style="Overflow:hidden;">
		<!-- #section:basics/navbar.layout -->
		<div id="navbar" class="navbar navbar-default ace-save-state">
			 <%@include file="ace/header.jsp"%>
		</div>
		<!-- /section:basics/navbar.layout -->
		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
			<!-- #section:basics/sidebar -->
			<div id="sidebar" class="sidebar  responsive  ace-save-state">
				 <%@include file="ace/left.jsp"%>
			</div>
			<!-- /section:basics/sidebar -->
			<div class="main-content">
				<div class="main-content-inner">
					<!-- /section:basics/content.breadcrumbs -->
					<div class="page-content">
						<!-- #section:settings.box -->
						
						<div class="row content-tabs">
			                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
			                </button>
			                <nav class="page-tabs J_menuTabs">
			                    <div class="page-tabs-content">
			                        <a href="<%= request.getContextPath() %>/home" class="active J_menuTab" data-id="<%= request.getContextPath() %>/home/wholeNetFacts">全网健康度</a>
			                    </div>
			                </nav>
			                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
			                </button>
			                <div class="btn-group roll-nav roll-right">
			                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>
			
			                    </button>
			                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
			                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
			                        </li>
			                        <li class="divider"></li>
			                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
			                        </li>
			                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
			                        </li>
			                    </ul>
			                </div>
			                
			            </div>
			            <div class="row J_mainContent" id="content-main">
			                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="<%= request.getContextPath() %>/home/wholeNetFacts" frameborder="0" data-id="<%= request.getContextPath() %>/home/homepage" seamless></iframe>
			            </div>
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
		
		<!-- 全局js -->
		<script src="<%= request.getContextPath() %>/static/scripts/vendors/jquery/js/jquery.min.js?v=2.1.4"></script>
		<script src="<%= request.getContextPath() %>/static/scripts/vendors/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
		<script src="<%= request.getContextPath() %>/static/scripts/vendors/metisMenu/jquery.metisMenu.js"></script>
		<script src="<%= request.getContextPath() %>/static/scripts/vendors/slimscroll/jquery.slimscroll.min.js"></script>
		<script src="<%= request.getContextPath() %>/static/scripts/vendors/layer/layer.min.js"></script>
		<!-- ace scripts -->
		<script src="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/js/ace-elements.min.js"></script>
		<script src="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/js/ace.min.js"></script>
		<script src="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/js/ace-extra.min.js"></script>
		
		<script type="text/javascript" src="<%= request.getContextPath() %>/static/common/js/contabs.js"></script>
		
		
	</body>
</html>