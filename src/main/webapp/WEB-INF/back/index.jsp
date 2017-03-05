<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>杭州豆制食品配送发货单</title>
<link href="/back/css/main.css" rel="stylesheet" type="text/css"/>
<link type="text/css" rel="stylesheet" href="/vendor/easyui/themes/icon.css"/>
<link type="text/css" rel="stylesheet" href="/vendor/easyui/themes/default/easyui.css"/>
<script language="javascript" type="text/javascript" src="/vendor/jquery.js" charset="utf-8"></script>
<script type="text/javascript" src="/vendor/cookie/jquery.cookie.js"></script>
<script type="text/javascript" src="/vendor/mousewheel/jquery.mousewheel.min.js"></script>
<script type="text/javascript" src="/vendor/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/vendor/easing/jquery.easing.js"></script>
<script type="text/javascript" src="/back/js/navigation.js"></script>
<script type="text/javascript" src="/back/js/count.js"></script>
<script type="text/javascript" src="/back/js/menu.js"></script>
<script type="text/javascript">
	$(function(){
		// 角色切换
		$("#roles").find(".role").click(function(){
			location.href = "/back/role.do?roleId="+$(this).attr("roleId");
		});
		
		//一级导航点中样式变换
		$("#firstGuideList").find(".firstGuideObject").click(function(){
			$("#firstGuideList").find(".firstGuideObjectHot").attr("class","firstGuideObject");
			$(this).attr("class","firstGuideObjectHot");
		}); 
		
		// 切换身份
	    $('#switch').menubutton({
	    	menuAlign:'right',
	        menu: '#users'
	    });
		
		// 菜单显示控制
		$("#menuLeft").delegate(".firstLevelMenuTitle", "click",function(){
			$("body").layout("collapse","west");
		});
		
		// second menu
		$("#menuLeft").delegate(".meunTitle", "click", function(){
			var parent = $(this).parent();
			parent.toggleClass("secCloseMenu");
			parent.toggleClass("secOpenMenu");
		});
		
		// 初始化
		var firstHeadNav = $("#navigation a").first();
		firstHeadNav.parent().attr("class","firstGuideObjectHot");
		$.resolveHrefFmt(firstHeadNav.attr("href"));
		
	});
		
</script>
<style>
<!--
.icon-close {
  background: url('/back/images/close.png') no-repeat center center;
}
.icon-refresh {
  background: url('/back/images/refresh.png') no-repeat center center;
}
.icon-loading {
  margin-top:10px;
  margin-left:10px;
  background: url('/back/images/loading.gif') no-repeat scroll left center;
}
-->
</style>
</head>
<body class="easyui-layout">
 	<div id="mainHead" data-options="region:'north',split:true, border:false" style="height:100px;">
 		<!--页头-->
		<!-- <div id="mainHead"> -->
			<!--最左边，logo区域-->
			<%--<div id="logoArea"></div>--%>
            <div style="float: left;color:#fff;font-size: 40px;line-height: 80px;padding-left: 10px;">
                杭州豆制食品配送
            </div>
		    <!--最右边，个人快捷操作区域-->
			<div id="oneSelfArea">
		    	<div id="mySettingArea">
		            <a href="<c:out value="${conf['logout.url']}" default="/back/logout.do"/>">
                        <span style="color:#fff;font-size: 20px;">退出</span>
                    </a>
		        </div>
		    </div>
			<!--中间：一级导航区域-->
		    <div id="firstGuideArea">
		      	<div id="firstGuideList">
		        	<table align="center" cellpadding="0" cellspacing="0">
		              <tr>
		              	<td width="30" align="left" class="navbtn">
		                <a href="javascript:void(0);" id="navPrev">
		                	<img src="/back/images/guideTurnLeft.jpg" alt="向左" width="10" height="37" border="0" align="absmiddle" />
		                </a>
		                </td>
		                <td style="text-align:center !important;">
		              <div id="navigation">
		                	<c:set var="setCurrentKey" value="${false}"/>
		                    <c:set var="currentKey" value=""/>
		                    <c:forEach var="m" items="${menus}" varStatus="s">
		                        <shiro:hasPermission name="${m.key}">
		                            <c:if test="${!setCurrentKey}">
		                                <c:set var="currentKey" value="${m.key}"/>
		                                <c:set var="setCurrentKey" value="${true}"/>
		                            </c:if>
		                            <div class="firstGuideObject" key="${m.key}">
		                            	<a href="javascript:$.navigation('${m.key}');" <c:if test="${!empty m.count}">count="${m.count}"</c:if>>
		                                    <div class="guidePic">
		                                        <img src="/back/images/${m.img}" alt="${m.img}" border="0" align="absmiddle" />
		                                    </div>
		                                    <div class="guideTitle">
		                                        ${m.name}
		                                    </div>
		                                </a>
		                            </div>
		                        </shiro:hasPermission>
		                    </c:forEach>
		                </div>
		                </td>
		                <td width="30" align="right" class="navbtn">
		                <a href="javascript:void(0);" id="navNext">
		                	<img src="/back/images/guideTurnRight.jpg" alt="向右" width="10" height="37" border="0" align="absmiddle" />
		                </a>
		                </td>
		              </tr>
		            </table>              
		      </div>
		    </div>
		<!-- </div> -->
 	</div>
    <div id="menuLeft" data-options="region:'west',split:true, border:false" style="width:200px;"></div>
    <div data-options="region:'center', border:false" >
	    <div id="tabUICenter" class="easyui-tabs" data-options="fit:true, plain:true, narrow:true,scrollDuration:2" >
		</div>
    </div>
    
    <!-- menu -->
	<div id="smartmenu" class="easyui-menu" style="width:150px;">
		<div id="m-close" data-options="name:'m-close', iconCls:'icon-close'">关闭</div>
		<div id="m-close-other" data-options="name:'m-close-other'">关闭其它</div>
		<div id="m-close-all" data-options="name:'m-close-all'">关闭所有</div>
		<div id="m-close-left" data-options="name:'m-close-left'">关闭左侧标签</div>
		<div id="m-close-right" data-options="name:'m-close-right'">关闭右则标签</div>
		<div class="menu-sep"></div>
		<div id="m-refresh" data-options="name:'m-refresh', iconCls:'icon-refresh'">重新载入</div>
	</div>
</body>
</html>
