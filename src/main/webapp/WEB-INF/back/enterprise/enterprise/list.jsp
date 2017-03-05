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
	$("#areaCode").treeSelect({
		url:"/entadmin/area/tree.do",
		multiple:false,
		cascadeCheck:false,
		values:"${search['areaCode']}"
	});
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/back/commons/show_message.jsp"/>
<div class="areaTitle">
    <div class="key">用户基本信息 - 列表</div>
</div>
<s:form action="list.do" method="post" modelAttribute="query">
<div class="areaContent">
	<div class="searchArea">
    	<table border="0" cellspacing="5" cellpadding="0">
         <thead>
          <tr>
            <th>用户名称：</th>
            <td>
            	<div class="inputDiv">
            	<s:input path="search['entName']" />
            	</div>
            </td>
            <th>状态：</th>
            <td>
            	<div class="inputDiv">
            		<s:select id="status" path="search['status']" >
            			<s:option value=""/>
						<s:option value="1">有效</s:option>
						<s:option value="0">注销</s:option>
            		</s:select>
            	</div>
            </td>
            <th>用户代码：</th>
            <td>
            	<div class="inputDiv">
            	<s:input path="search['entCode']" />
            	</div>
            </td>
            	<th>联系人：</th>
            <td>
            	<div class="inputDiv">
            	<s:input path="search['linkeName']" />
            	</div>
            </td>
            <td>
            	<input type="submit" value=" 搜  索 " class="searchAreaSubBtn" />
            </td>
          </tr>
          </thead>
        </table>
  </div>
  <div class="editButtonsArea">
  	<shiro:hasPermission name="enterprise:enterpriseBasic:toAdd">
  	<div class="add"><a href="add.do">新增</a></div>
  	</shiro:hasPermission>
  	<shiro:hasPermission name="enterprise:enterpriseBasic:view">
  	<div class="look"><a href="javascript:$.href('view.do?id=');">查看</a></div>
  	</shiro:hasPermission>
  	<shiro:hasPermission name="enterprise:enterpriseBasic:toAlter">
    <div class="edit"><a href="javascript:$.href('alter.do?id=');">修改</a></div>
  	</shiro:hasPermission>
  	<shiro:hasPermission name="enterprise:enterpriseBasic:remove">
    <div class="delete"><a href="javascript:$.remove();">删除</a></div>
  	</shiro:hasPermission>
  </div>
  <div class="listNews">
  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listNewsTable">
      <thead>
      <tr>
        <th width="3%" align="center"><input type="checkbox" id="allBox"/></th>
	    <th><span class="ls-sort" pagesort="entName">用户名称</span></th>
	    <th><span class="ls-sort" pagesort="entCode">用户代码</span></th>
	    <th><span class="ls-sort" pagesort="enterpriseType">用户类型</span></th>
	    <th><span class="ls-sort" pagesort="areaName">所属地区名称</span></th>
	    <th><span class="ls-sort" pagesort="status">状态</span></th>
	    <th><span class="ls-sort" pagesort="linkePhone">联系电话</span></th>
        <th width="15%">编辑</th>
      </tr>
      </thead>
      <tbody>
	  <c:forEach var="bean" varStatus="s" items="${pageNav.list}">
	  <tr class="${s.index%2==0?'normal1':'normal2' }">
	    <td align="center"><input type="checkbox" name="id" value="${bean.id}"/></td>
	    <td align="center">
	    	${bean.entName}
	    </td>
	    <td align="center">
	    	${bean.entCode}
	    </td>
	    <td align="center">
	    	${bean.enterpriseType}
	    </td>
	    <td align="center">
	    	${bean.areaName}
	    </td>
	    <td align="center">
	    	<c:if test="${bean.status==1}">有效</c:if>
	    	<c:if test="${bean.status==0}">注销</c:if>
	    </td>
	    <td align="center">
	    	${bean.linkePhone}
	    </td>
	  <td align="center" class="listEditTd">
	  		<shiro:hasPermission name="enterprise:enterpriseBasic:view">
	  		<a class="ls-opt" href="view.do?id=${bean.id}">查看</a>
	  		</shiro:hasPermission>
	  		<shiro:hasPermission name="enterprise:enterpriseBasic:toAlter">
	    	<a class="ls-opt" href="alter.do?id=${bean.id}">修改</a>
	  		</shiro:hasPermission>
	  	<%-- 	<c:choose>
	       		<c:when test="${bean.discCodeId != null}">
				<shiro:hasPermission name="admin:discCode:toAlter">
	       		<a class="ls-opt" href="/enterprise/entdiskcode/alter.do?id=${bean.discCodeId}">U盾管理</a>
	       		</shiro:hasPermission>
	       		</c:when>
	       		<c:otherwise>
	       		<shiro:hasPermission name="admin:diskCode:toAdd">
	       		<a class="ls-opt" href="/enterprise/entdiskcode/add.do?userId=${bean.id}">发放U盾</a>
	       		</shiro:hasPermission>
	       		</c:otherwise>
       		</c:choose> --%>
	  		<shiro:hasPermission name="enterprise:enterpriseBasic:remove">
	    	<a class="ls-opt" href="javascript:$.remove('${bean.id}');">删除</a>
	  		</shiro:hasPermission>
	  		<shiro:hasPermission name="enterprise:enterpriseBasic:alter">
	    	<a class="ls-opt" href="initializationpwd.do?id=${bean.id}">密码初始化</a>
	  		</shiro:hasPermission>
	  </td>
	  </tr>
	  </c:forEach>
	  <%-- <c:if test="${fn:length(pageNav.list) le 0}"> 
		<div class="ls-norecord margin-top5">没有相关的数据!!</div>
	  </c:if> --%>
      </tbody>
      <tfoot>
      <tr>
        <td colspan="6">${pageNav.view}</td>
      </tr>
      </tfoot>
    </table>
  </div>
</div>
<div class="pagesort">
<c:forEach var="bean" varStatus="s" items="${query.collates}">
<span>
	<input type="hidden" name="collates[${s.index}].sortField" value="${bean.sortField}"/>
	<input type="hidden" name="collates[${s.index}].sortType" value="${bean.sortType}"/>
</span>
</c:forEach>
</div>
</s:form>
</body>
</html>
