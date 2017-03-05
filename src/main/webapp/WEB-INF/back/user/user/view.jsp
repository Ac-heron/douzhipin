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
<title>用户成员基本信息</title>
</head>
<body>
<div class="areaTitle">
    <div class="key">用户成员基本信息 - 查看</div>
    <div class="ext"></div>
</div>
<div class="areaContent">
	<div class="editButtonsArea">
		<shiro:hasPermission name="user:userBasic:toAdd">
	 	<div class="add"><a href="add.do">新增</a></div>
		</shiro:hasPermission>
	 	<shiro:hasPermission name="user:userBasic:toAlter">
	 	<div class="edit"><a href="alter.do?id=${bean.id}">修改</a></div>
	 	</shiro:hasPermission>
	 	<shiro:hasPermission name="user:userBasic:remove">
	    <div class="delete"><a href="javascript:$.remove('${bean.id}');">删除</a></div>
	 	</shiro:hasPermission>
	</div>
	<div class="detailAreaTitle">基本信息
	<div class="ext">
	<input type="button" class="detailAreaClosetBtn" value=" " />
	</div>
	</div>
	<div class="detailAreaContent">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="detailTable">
	 	<tr>
		 	<th width="15%">用户名称：</th>
		   <td width="35%" >
		   	${bean.userName}
		   </td>
		   <th width="15%">用户代码：</th>
		   <td width="35%" >
		   	${bean.userCode}
		   </td>
		</tr>
	 	<tr>
	   <%-- <th width="15%">企业登陆密码：</th>
	   <td width="35%" >
	   	${bean.userPwd}
	   </td> --%>
		</tr>
	 	<tr>
		 	<th width="15%">状态：</th>
		   <td width="35%" >
		   <c:if test="${bean.status==1 }">有效</c:if>
		   <c:if test="${bean.status==0 }">无效</c:if>
		   </td>
		   <th width="15%">联系人：</th>
		   <td width="35%" >
		   	${bean.linkeName}
		   </td>
		</tr>
	 	<tr>
		 	<th width="15%">联系电话：</th>
		   <td width="35%" >
		   	${bean.linkePhone}
		   </td>
		</tr>
	  </table>
	</div>
	<div class="detailBtnArea"> 
		<input type="button" value="返回列表" class="subBtn" onclick="javascript:location.href='list.do'"/>
	</div>
</div>
</body>
</html>
