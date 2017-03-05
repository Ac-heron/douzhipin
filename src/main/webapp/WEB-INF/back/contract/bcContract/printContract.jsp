<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>杭州豆制食品配送发货单</title>
    <meta name="viewport" content="width=device-width" />
    <style type="text/css">
        /*处方详情*/
        .cf_main {
            width: 90%;
            margin: 0 auto;
            background-color: #fff;
        }

        .cf_title {
            font-size: 26px;
            font-weight: normal;
            font-family: "微软雅黑";
            height:40px;
            line-height: 40px;
            color:#28281C;
            text-align: center;
        }

        .cf_info {
            background-color: #fff;
            padding-left: 10px;
            font-family: "微软雅黑";
        }
        .cf_info .span-1 {
            margin-left: 100px;
        }
        .cf_info .span-2 {
            margin-left: 100px;
        }

        .cf_table_div {
            padding: 0 10px;
        }

        .cf_table_div table {
            width:100%;
            text-align: center;
            border:1px solid #000;
            padding-top: 10px;
            border-collapse: collapse;
        }
        .cf_table_div table tr td,.cf_table_div tr th{
            height: 40px;
            line-height: 40px;
            border:1px solid #000;
        }

    </style>
</head>
<body >

<div class="cf_main">
    <div class="cf_title">杭州豆制食品配送发货单</div>
    <div class="cf_info">
        <p>
            <span class="span-1">${bean.shopname}</span>
            <span class="span-2">发货时间：<fmt:formatDate value="${bean.fahuodate}" pattern="yyyy-MM-dd"/></span>
        </p>
    </div>
    <div class="cf_table_div">
        <table>
            <thead>
            <tr>
                <th>代码</th>
                <th>商品名称</th>
                <th>包装</th>
                <th>单价</th>
                <th>采购数量</th>
                <th>验收数量</th>
                <th>金额</th>
                <th>备注</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="detail" varStatus="s" items="${bean.details}"> 
            <tr>
                <td>${detail.dcode}</td>
                <td>${detail.dname}</td>
                <td>${detail.dpackage}</td>
                <td>${detail.dprice}</td>
                <td>${detail.caigounum}</td>
                <td>${detail.yanshounum}</td>
                <td>${detail.jine}</td>
                <td>${detail.remark}</td>
            </tr>
            </c:forEach>
            </tbody>
            <tr>
                <td>&nbsp;</td>
                <td>合计金额（元）</td>
                <td colspan="4">&nbsp;</td>
                <td>${totaljine}</td>
                <td>&nbsp;</td>
            </tr>
        </table>
    </div>
    <div class="cf_info">
        <p>
            <span class="span-1">验收人：${bean.yanshouman}</span>
            <span class="span-2">店经理：${bean.shopmanager}</span>
        </p>
    </div>
</div>
</body>
</html>

