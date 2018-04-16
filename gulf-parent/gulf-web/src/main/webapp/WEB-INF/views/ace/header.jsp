<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="navbar-container ace-save-state" id="navbar-container">
	<!-- #section:basics/sidebar.mobile.toggle -->
	<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
		<span class="sr-only">Toggle sidebar</span>

		<span class="icon-bar"></span>

		<span class="icon-bar"></span>

		<span class="icon-bar"></span>
	</button>
	<!-- 添加mscookie -->
	<%
		Cookie msCookie = new Cookie("mscookie", "mscookie@sinontt.com");
		msCookie.setPath("/");
		response.addCookie(msCookie);
	%>
	<!-- /section:basics/sidebar.mobile.toggle -->
	<div class="navbar-header pull-left">
		<!-- #section:basics/navbar.layout.brand -->

		<!-- /section:basics/navbar.layout.brand -->

		<!-- #section:basics/navbar.toggle -->

		<!-- /section:basics/navbar.toggle -->
	</div>

	<!-- #section:basics/navbar.dropdown -->
	<div class="navbar-buttons navbar-header pull-right" role="navigation">
		<ul class="nav ace-nav">

			<!-- #section:basics/navbar.user_menu -->
			<li class="light-blue dropdown-modal">
				<a data-toggle="dropdown" href="#" class="dropdown-toggle">
					<img class="nav-user-photo" src="" alt="" />
					<span class="user-info">
						<small>欢迎您,</small>
						<shiro:principal/>
						<input id="head_username" class="hide" value="">
					</span>
					<i class="ace-icon fa fa-caret-down"></i>
				</a>

				<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
					
                   
					<li class="divider"></li>
					<li>
						<a href="<%= request.getContextPath() %>/logout">
							<i class="ace-icon fa fa-power-off"></i>
							安全退出
						</a>
					</li>
				</ul>
			</li>

			<!-- /section:basics/navbar.user_menu -->
		</ul>
	</div>

	<!-- /section:basics/navbar.dropdown -->
</div><!-- /.navbar-container -->
<script>
function changeStyle(theme){
	   $.get('${adminPath}/theme/'+theme+'?url='+window.top.location.href,function(result){   window.location.reload();});
}
</script>