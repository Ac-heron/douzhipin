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
<title></title>
</head>
<body>
<jsp:include page="/WEB-INF/back/commons/show_message.jsp"/>
<s:form action="list.do" method="post" modelAttribute="query">
    <div class="editButtonsArea">
        <div class="add"><a href="add.do">新增商店</a></div>
        <div class="add"><a href="/ReportServer?reportlet=douzhipin.cpt" target="_blank">发货单汇总</a></div> 
    </div>
  <div class="listNews">
  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listNewsTable">
      <thead>
      <tr>
	    <th><span>商店名称</span></th>
	    <th><span>发货时间</span></th>
        <th width="20%">编辑</th>
      </tr>
      </thead>
      <tbody>
	  <c:forEach var="bean" varStatus="s" items="${pageNav.list}">
	  <tr class="${s.index%2==0?'normal1':'normal2' }">
	    <td align="center">
	    	${bean.shopname}
	    </td>
	    
	    <td align="center">
	    	<fmt:formatDate value="${bean.fahuodate}" pattern="yyyy-MM-dd"/>
	    </td>
	  <td align="center" class="listEditTd">
	    	<a class="ls-opt" href="alter.do?id=${bean.id}">修改</a>&nbsp;&nbsp;
            <a class="ls-opt" href="printContract.do?id=${bean.id}" target="_blank">打印</a>&nbsp;&nbsp;
	    	<a class="ls-opt" href="javascript:$.remove('${bean.id}');">删除</a>
      </td>
	  </tr>
	  </c:forEach>
      </tbody>
      <tfoot>
      <tr>
        <td colspan="3"><%--${pageNav.view}--%></td>
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
