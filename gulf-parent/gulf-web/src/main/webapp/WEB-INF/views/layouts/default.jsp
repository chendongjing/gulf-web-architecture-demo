<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		
		<sitemesh:write property='head' />
	</head>

	<body class="no-skin">
	
		<sitemesh:write property='body' />

		<!-- basic scripts -->
    	<script src="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/js/jquery.min.js"></script>
		<script src="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/js/bootstrap.min.js"></script>
		<script src="<%= request.getContextPath() %>/static/scripts/ace-1.3.4/js/ace.min.js"></script>
		
		<!-- inline scripts related to this page -->
		<sitemesh:write property='myscript' />

	</body>
</html>