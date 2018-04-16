<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/common/taglibs.jspf"%>

<!-- 默认展开 -->
<li class="open">
	  <a href="#" class="dropdown-toggle">
			<i class="menu-icon fa fa-video-camera"></i>
			<span class="menu-text">
				根因定位
			</span>
			<b class="arrow fa fa-angle-down"></b>
	  </a> 
	   
	 <b class="arrow"></b>
        
	 <ul class="submenu">
				
			<li class="">
					  <a class="J_menuItem" href="${ctx }/home/problemDistPlot" data-index="1">
								<i class="menu-icon fa fa-video-camera"></i>
								<span class="menu-text">问题小区画像</span>
					  </a>
					<b class="arrow"></b>
					<ul class="submenu">
					</ul>
				</li> 
			<li class="">
				  <a class="J_menuItem" href="${ctx }/home/problemDistList" data-index="2">
						<i class="menu-icon fa fa-video-camera"></i>
						<span class="menu-text">问题小区列表</span>
				  </a>
				<b class="arrow"></b>
				<ul class="submenu">
				</ul>
			</li> 
	 </ul>
</li>
<!-- 网络结构优化 -->
<li class="">
	   <a href="#" class="dropdown-toggle">
			<i class="menu-icon fa fa-television"></i>
			<span class="menu-text">
				网络结构优化
			</span>
			<b class="arrow fa fa-angle-down"></b>
	  </a> 
	<b class="arrow"></b>
     <!--全网画像  -->  
	<ul class="submenu">
			<li class="">
				  <a class="J_menuItem" href="${ctx }/home/allNetPlot" data-index="1">
							<i class="menu-icon fa fa-tv"></i>
							<span class="menu-text">全网画像</span>
				  </a>
				<b class="arrow"></b>
				<ul class="submenu">
				</ul>
			</li>
         <!-- 弱覆盖小区 -->
			<li class="">
						  <a class="J_menuItem" href="${ctx }/home/weakCoverageDist" data-index="2">
									<i class="menu-icon fa fa-tv"></i>
									<span class="menu-text">弱覆盖小区 </span>
						  </a>
				<b class="arrow"></b>
				<ul class="submenu">
				 
				</ul>
			</li>
		 <!-- 过度覆盖小区 -->
			<li class="">
						  <a class="J_menuItem" href="${ctx }/home/overlayDist" data-index="2">
									<i class="menu-icon fa fa-tv"></i>
									<span class="menu-text">过度覆盖小区 </span>
						  </a>
				<b class="arrow"></b>
				<ul class="submenu">
				 
				</ul>
			</li>
         <!-- 重复覆盖区域-->
			<li class="">
						  <a class="J_menuItem" href="${ctx }/home/repeatCoverageDist" data-index="3">
									<i class="menu-icon fa fa-tv"></i>
									<span class="menu-text">重叠覆盖区域 </span>
						  </a>
				<b class="arrow"></b>
				<ul class="submenu">
				 
				</ul>
			</li>
         <!-- 空洞覆盖区域 -->
			<li class="">
				  <a class="J_menuItem" href="${ctx }/home/emptyCoverageDist" data-index="4">
						<i class="menu-icon fa fa-tv"></i>
						<span class="menu-text">空洞覆盖区域 </span>
				  </a>
				<b class="arrow"></b>
				<ul class="submenu">
				 
				</ul>
			</li>
         <!-- 优化列表 -->
			<li class="">
				  <a class="J_menuItem" href="${ctx }/home/optimizeList" data-index="5">
						<i class="menu-icon fa fa-tv"></i>
						<span class="menu-text">优化列表 </span>
				  </a>
				<b class="arrow"></b>
				<ul class="submenu">
				 
				</ul>
			</li>
      </ul>
</li>