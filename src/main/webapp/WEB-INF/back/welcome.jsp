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
	$("html").click(function(){
		parent.$.smartMenu.remove();
	});
});
</script>
<style type="text/css">
	#mytask tbody tr{
		cursor: pointer;	
	}
</style>
<title>系统主界面</title>
</head>
<body>
<!--欢迎页面开始-->
<div class="areaContent">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="50%" valign="top">
        <div class="areaTitle">
            <div class="key">我的任务</div>
            <div class="ext">
            <shiro:hasPermission name="tasks:tTask:mylist">
            <a href="/tasks/tTask/mylist.do">更多&gt;&gt;</a>
            </shiro:hasPermission>
            </div>
        </div>
        <div class="areaMarginContent" id="mytask">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="listNewsTable">
             <thead>
			      <tr>
				    <th><span class="ls-sort" pagesort="name">任务名</span></th>
				    <th width="200px"><span class="ls-sort">所属模块</span></th>
				    <th>创建时间</th>
			      </tr>
     		 </thead>
            <tbody>
			  <c:forEach var="bean" varStatus="s" items="${mytaskList.list}">
			  <tr class="${s.index%2==0?'normal1':'normal2' }">
			    <input type="hidden" class="taskid" value="${bean.id}"/>
                <td align="center" title="${bean.name}">
                	<div style="display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis; width: 200px">
			    	${bean.name}
			    	</div>
			    </td>
                  <td align="center">
			    	${bean.project.name}【${bean.project.parentProject.name}】
			    </td>
			    <td align="center">
			    	<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>
			    </td>
			    </tr>
			  </c:forEach>
      </tbody>
            </table>
        </div>
    </td>
    <td width="8" valign="top">&nbsp;</td>
    <td width="50%" valign="top">
	    <div class="areaTitle">
	            <div class="key">项目动态</div>
	            <div class="ext"><a href="/active/pActive/list.do">更多&gt;&gt;</a></div>
	        </div>
	        <div class="areaMarginContent">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listNewsTable">
			      <thead>
			      <tr>
				    <th><span class="ls-sort" pagesort="content">内容</span></th>
				    <th><span class="ls-sort" pagesort="timeAdd">时间</span></th>
			      </tr>
			      </thead>
			      <tbody>
				   <c:forEach var="bean" varStatus="s" items="${activeList.list}">
					  <tr class="${s.index%2==0?'normal1':'normal2' }">
					    <td align="center">
					    	${bean.content}
					    </td>
					    <td align="center">
					    	<fmt:formatDate value="${bean.timeAdd}" pattern="yyyy-MM-dd" />
					    </td>
					  </tr>
				  </c:forEach>
			      </tbody>
			    </table>
	        </div>
    </td>
  </tr>
  <tr>
    <td height="8" valign="top"></td>
    <td width="8" height="8" valign="top"></td>
    <td height="8" valign="top"></td>
  </tr>
  <tr>
    <td valign="top">
    	 <div class="areaTitle">
            <div class="key">项目进度</div>
            <div class="ext"><a href="/project/project/list.do">更多&gt;&gt;</a></div>
        </div>
        <div class="areaMarginContent">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="listNewsTable">
				 <thead>
			      <tr>
				    <th width="50%">名称</th>
				    <th>进度</th>
			      </tr>
			      </thead>
			      <tbody>
			      <c:forEach var="bean" varStatus="s" items="${projectList.list}">
					  <tr class="${s.index%2==0?'normal1':'normal2' }">
					    <td align="center">
					    	${bean.name}
					    </td>
					    <td>
					    	<div class='progress_bar' style="width:100%;height:26px;border-radius:12px;	background:#d2d2d2;position:relative;">
					    		<div class='number' style="position:absolute;left:50%;line-height:26px;">${bean.percent2Int}%</div>					    		
								<div class='loading' style="width:${bean.percent2Int}%;height:100%;border-radius:12px;background:#ff5842;text-align:center;"></div>
							</div>
					    </td>
					  </tr>
				  </c:forEach>
			      
			      </tbody>
            </table>
        </div>
    </td>
    <td width="8" valign="top">&nbsp;</td>
    <td valign="top">
        <div class="areaTitle">
          <div class="key">xxxx</div>
            <div class="ext"><a href="">更多&gt;&gt;</a></div>
        </div>
        <div class="areaMarginContent">
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mailTableList">
              <c:forEach var="mail" items="${mailList}">
					
              </c:forEach>
          </table>
        </div>
    </td>
  </tr>
</table>

<script type="text/javascript">
	$(function(){
		$("#mytask").find("tbody").find("tr").live("click",function(){
			var id=$(this).find(".taskid").val();
			location.href="/tasks/tTask/view.do?id="+id+"&mytask=mine";
		});
	});
</script>

<!--欢迎页面结束-->
<br/>
</body>
</html>
