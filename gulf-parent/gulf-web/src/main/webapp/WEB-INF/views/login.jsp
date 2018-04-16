<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="cache-control" content="no-cache">
<title><spring:message code="sys.login.title"></spring:message></title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/scripts/login/css/style.css">
	<script src="<%=request.getContextPath()%>/static/scripts/login/js/fontSize-width.js" type="text/javascript"></script>

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/scripts/login/css/checkbix.min.css">
	<script src="<%=request.getContextPath()%>/static/scripts/login/js/checkbix.min.js"></script>
<script type="text/javascript">
	Checkbix.init();
	//Enter  login
	function keyLogin() {
		if (event.keyCode == 13) {
			document.getElementById("lg").click();
		}
	}
	//点击更换验证码图片
	function changeJcaptchaSrc(){
        $("#img_jcaptcha").attr("src","${ctx}/gulf-web/jcaptcha.jpg?data=" + new Date());
    }
</script>

<!-- 清除mscookie -->
<%
	Cookie msCookie = new Cookie("mscookie", null);
	msCookie.setMaxAge(0);
	msCookie.setPath("/");
	response.addCookie(msCookie);
%>

</head>
<body onkeydown="keyLogin();">

	<!--login start-->
	<div class="hklogin">
		<div class="hkaccount">
			<div class="hkphoto">
				<p>
					<img
						src="<%=request.getContextPath()%>/static/scripts/login/images/photo.png"
						alt='<spring:message code="system.name"></spring:message>'>
				</p>
			</div>
		</div>
		<div class="hkconttext">
			<form id="form-login" action="" method="post">
				<div class="hktxt">
					<i class="hkicon_use"></i> <input type="text" class="hkop" name="username" id="username" placeholder="<spring:message code="sys.login.loginName"></spring:message>" />
				</div>
				<div class="hktxt">
					<i class="hkicon_pass"></i> <input type="password" class="hkop" name="password" id="password" placeholder="<spring:message code="sys.login.password"></spring:message>" />
				</div>
				<!-- 开启验证码  -->
				<c:if test="${showCaptcha eq 0}">
	                <div class="txt">
	                	<div class="col-sm-12">
	                		<div class="col-sm-9"> 
                   				<i class="icon_code"></i><input style="margin-top: 23px;" type="text" id="j_captcha" name="j_captcha" class="form-control" placeholder="<spring:message code="sys.login.captcha.placeholder"/>" required="">
	                    	</div>
	                    	<div class="col-sm-3" style="margin-left: 75%;margin-top: -14%;">
	                     		<img id="img_jcaptcha"  src="${ctx}/gulf-web/jcaptcha.jpg" onclick="changeJcaptchaSrc();" />
	                     		<a href="JavaScript:changeJcaptchaSrc();"></a>
	               			</div>
	               		</div>
	                </div>
	                <div class="clearfix"></div>
	            </c:if>
				<div class="hktxt2">
					<input type="submit"
						value='<spring:message code="sys.login.buttonName"></spring:message>'
						id="lg" onClick="login()">
				</div>
			</form>
		</div>
	</div>
	<div class="footer2">
		<p>
			<spring:message code="sys.login.footer"></spring:message>
		</p>
	</div>
</body>
</html>