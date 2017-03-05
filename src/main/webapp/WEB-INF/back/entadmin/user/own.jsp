<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/WEB-INF/back/commons/head.jsp"/>
<script type="text/javascript">
$(function(){
	$("form").validator([{
		id:"userName",
		required:true,
		missingMessage:"请输入企业名称"
	},{
		id:"linkeName",
		required:true,
		missingMessage:"请输入联系人"
	}]);
});
</script>
<title>维护个人资料页面</title>
</head>
<body>
<jsp:include page="/WEB-INF/back/commons/show_message.jsp"/>
<div class="areaTitle">
    <div class="key">维护个人资料页面</div>
    <div class="ext"></div>
</div>
<s:form action="own.do" method="post" modelAttribute="bean">
<s:hidden path="id" />
<input type="hidden" id="redirect" name="redirect" value="own"/>
<div class="areaContent">
  <div class="formAreaTitle">基本信息</div>
  <div class="formAreaContent"><br />
  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable">
  		<tr>
	  	<th width="15%">用户名称：</th>
	    <td width="35%" >
	    	<s:input path="userName" size="20" maxlength="50" />
	    </td>
	  	<th width="15%">登录账号：</th>
	    <td width="35%" >
	    	${bean.userCode}
	    </td>
	    </tr>
	  	<tr>
	  	<th width="15%">联系人：</th>
	    <td width="35%" >
	    	<s:input path="linkeName" size="20" maxlength="20" />
	    </td>
	    <th width="15%">联系电话：</th>
	    <td width="35%" >
	    	<s:input path="linkePhone" size="20" maxlength="20" />
	    </td>
		</tr>
    </table>
  	<br />
  </div>
  <div class="formSubArea"> 
  	<input type="submit" class="subBtn" value="保存"/>
  	<input type="submit" class="subBtn" value="保存并重新登录" onclick="$('#redirect').val('login');"/>
  	<input type="reset" value="重置表单" class="subBtn" />
  </div>
</div>
</s:form>
</body>
</html>
