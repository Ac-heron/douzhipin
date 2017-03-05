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
<title>用户基本信息</title>
<script type="text/javascript">
$(function(){
	
	 $.extend($.fn.validatebox.defaults.rules, {
		    // 验证唯一性
		    fieldname : {
		    	validator : function(value, param) {
		    		var bool = false;
		    		var array = new Object();
		    		array["value"] = value;
		    		array["id"] = $(param[0]).val();
		    		array["fieldname"] = $(param[1]).attr("id");
		    		$.ajax({
		    			async:false,//保证同步
		    			type:"GET",
		    			data:array,
		    			url:"/entadmin/user/unique.do",
		    			dataType:"json",
		    			success:function(data){
		    				bool = data.success; 
		    			}
		    		});
		    		return bool;
		        }, 
		        message : "用户名已存在，请更换"
		    }
		}); 
	
	
	$("form").validator([{
		id:"userCode",
		required:true,
		missingMessage:"请输入用户编码",
		invalidMessage:"已存在的用户编码",
		validType:["fieldname['#id','#userCode']"]
	},{
		id:"userName",
		required:true,
		missingMessage:"请输入用户名称"
	},{
		id:"linkePhone",
		required:true,
		missingMessage:"请输入联系电话"
	},{
		id:"linkeName",
		required:true,
		missingMessage:"请输入联系人"
	}]);
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/back/commons/show_message.jsp"/>
<div class="areaTitle">
    <div class="key">用户基本信息 - ${operation=='add' ? '添加' : '修改'}</div>
    <div class="ext"></div>
</div>
<div class="areaContent">
	<c:if test="${operation=='alter'}">
	<div class="editButtonsArea">
		<shiro:hasPermission name="user:userBasic:toAdd">
	 	<div class="add"><a href="add.do">新增</a></div>
		</shiro:hasPermission>
		<shiro:hasPermission name="user:userBasic:view">
	 	<div class="look"><a href="view.do?id=${bean.id}">查看</a></div>
		</shiro:hasPermission>
		<shiro:hasPermission name="user:userBasic:remove">
	    <div class="delete"><a href="javascript:$.remove('${bean.id}');">删除</a></div>
		</shiro:hasPermission>
	</div>
	</c:if>
	
	<s:form action="${operation}.do" method="post" modelAttribute="bean">
	<s:hidden path="id" />
	<input type="hidden" id="redirect" name="redirect" value="alter"/>
	  <div class="formAreaTitle">基本信息</div>
	  <div class="formAreaContent"><br />
	  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable">
	  		<tr>
	  			<th width="15%">分配角色：</th>
			    <td width="35%">
			    	<s:select  path="currentRoleId">
			    		<c:forEach var="role" items="${roles.list}">
			    			<option  value="${role.id}" ${role.id==bean.currentRole.id?"selected":""}>
			    				${role.name}
			    			</option>
			    		</c:forEach>
			    	</s:select>
			    	<!-- <input id="btnAdd" type="button" value="+"/>
			    	<input id="btnRemove" type="button" value="-"/> -->
			    </td>
			    <th width="15%">用户编码：</th>
			    <td width="35%" >
			    	<s:input path="userCode" size="20" maxlength="128" />
			    </td>
	  		</tr>
		  	<tr>
			  	<th width="15%">用户名称：</th>
			    <td width="35%" >
			    	<s:input path="userName" size="20" maxlength="128" />
			    </td>
			    <th width="15%">状态：</th>
			    <td width="35%" >
			    	<s:select id="selectStatus" path="status">
			    		<c:choose>
			    			<c:when test="${bean.status==1 }">
			    				<s:option value="1" selected="selected">有效</s:option>
			    				<s:option value="0">注销</s:option>
			    			</c:when>
			    			<c:when test="${bean.status==0 }">
			    				<s:option value="1">有效</s:option>
			    				<s:option value="0" selected="selected">注销</s:option>
			    			</c:when>
			    			<c:otherwise>
			    				<s:option value="1">有效</s:option>
			    				<s:option value="0">注销</s:option>
			    			</c:otherwise>
			    		</c:choose>
			    	</s:select>
			    </td>
			</tr>
		  	<tr>
			    <th width="15%">联系电话：</th>
			    <td width="35%" >
			    	<s:input path="linkePhone" size="20" maxlength="32" />
			    </td>
			    
			   
			   <%-- 
			    <th width="15%">联系人：</th>
			    <td width="35%" >
			    	<s:input path="linkeName" size="20" maxlength="256" />
			    </td>
			    <c:if test="${operation=='alter'}">
				    <td style="display:none">
				    	<s:input id="userCode" path="userCode"/>
				    </td>
			    </c:if> --%>
			</tr>
	    </table>
	  	<br />
	  </div>
	  <div class="formSubArea"> 
	  	<input type="submit" class="subBtn" value="保存"/>
	  	<input type="submit" class="subBtn" value="保存并返回" onclick="$('#redirect').val('list');"/>
	    <c:if test="${operation=='add'}">
	    <input type="submit" class="subBtn" value="保存并新增" onclick="$('#redirect').val('add');"/>
	    </c:if>
	  	<input type="reset" value="重置表单" class="subBtn" />
	  	<input type="button" value="返回列表" class="subBtn" onclick="javascript:location.href='list.do'"/>
	  </div>
	</s:form>
</div>
</body>
</html>
