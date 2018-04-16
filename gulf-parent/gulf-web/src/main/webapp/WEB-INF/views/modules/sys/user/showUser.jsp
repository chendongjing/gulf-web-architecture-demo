<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglibs.jspf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title><spring:message code="sys.user.title"></spring:message></title>
  </head>
  <body>
  
  <div id="mysql">00</div>
  <div id="hbase"></div>
  </body>
  <myscript>
  		<script src="<%= request.getContextPath() %>/static/scripts/vendors/jquery/js/jquery.min.js?v=2.1.4"></script>
	  	<script type="text/javascript">
	  	//mysql连接
	  	 $.ajax({
			url : '${ctx}/admin/common/findObjectByParam',
				type : 'post',
				dataType : 'json',
				data : {id:1},
				success : function(result) {
					
					$("#mysql").html("mysql:"+result.USERNAME);
				}
			})
			
	  	//hbase连接
		 $.ajax({
			url : '${ctx}/admin/common/findhHaseByParam',
				type : 'get',
				dataType : 'json',
				data : {id:'1'},
				success : function(result) {
					
					$("#hbase").html("hbase："+result.name);
				}
			})
	  </script>
  </myscript>
  
</html>  