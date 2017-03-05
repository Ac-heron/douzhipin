<%@page import="net.tuxun.core.util.PropertyUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>杭州豆制食品配送发货单</title>
<jsp:include page="/WEB-INF/back/commons/head.jsp"/>
<script type="text/javascript">
if( window.top != window.self ){
	parent.location.href = location.href;
}
$(function(){
	$("form").submit(function(){
		return true;
	});
});
</script>
</head>
<body class="loginBodyStyle">
	<form action="/back/login.do" method="post">
    <div class="loginPageParentDiv">
        <div class="loginAreaTable">
        	<div class="loginTitle"></div>
            <div class="loginMsg" style="height:16px;">
            	<c:if test="${!empty shiroLoginFailure}">
                    <c:choose>
                        <c:when test="${shiroLoginFailure=='net.tuxun.component.admin.shiro.exception.CaptchaException'}">
                           	验证码错误
                        </c:when>
                        <c:otherwise>
                           	用户名或密码错误
                        </c:otherwise>
                    </c:choose>
				</c:if>
				<c:if test="${!empty param.message}">
					<c:choose>
						<c:when test="${param.message=='kickOut'}">
	                       	账号在别处登录
	                    </c:when>
	                    <c:otherwise>
	                      	登录超时
	                    </c:otherwise>
                    </c:choose>
				</c:if>
            </div>
            <div class="loginItem">用户名：<input name="username" type="text" class="loginUser" style="font-size: 15px;font-weight:bold"/></div>
            <div class="loginItem">密&emsp;码：<input name="password" type="password" class="loginPwd"/></div>
            <c:if test="${sessionScope.shiroCaptchaRequired}">
          <div class="loginItem">验证码：<input type="text" id="captcha" name="captcha" class="loginChk"/>
            <a href="javascript:void(0);"><img src="${ctx}/captcha.servlet" align="absmiddle"  title="看不清可点击切换" onclick="this.src='/captcha.servlet?d='+new Date()*1" border="0"/></a>
            </div>
            </c:if>
            <div class="loginBtn">
              <input type="image" name="imageField" id="imageField" src="/back/images/loginBtn.jpg"/> 
              <input type="checkbox" name="rememberMe" value="true"/>记住我！
            </div>
        </div>
        <div></div>
    </div>
	</form>
</body>
</html>
