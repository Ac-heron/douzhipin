<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <jsp:include page="/WEB-INF/back/commons/head.jsp" />
    <title>杭州豆制食品配送发货单</title>
    <script type="text/javascript">
        $(function () {
            // 主表验证
            var validatorArray = [];
            // 绑定
            $("form").validator(validatorArray);

            // 修改AJAX
            var operation = "${operation}";
            if (operation == "alter") {
                var id = "${bean.id}";
                $("#bcContractDetail").oneBindInit({
                    param: {primaryTableId: id},
                    url: "/contract/bcContractDetail/list.do",
                    attrs: ["id", "dcode", "dname", "dpackage", "dprice", "caigounum", "yanshounum", "jine", "remark"]
                });
            }
            //修改时点击删除事件
            $("#attached").find(":button").on("click", function () {
                $(this).parent("div").remove();
            });


            $("input[id$='_caigounum']").live("keyup", function () {
                countjine($(this));
            });

            function countjine($this) {
                var id = $($this).attr("id");
                var arr = id.split("_");
                var dprice = $("input[id$='" + arr[1] + "_dprice']").val();
                var num = $("input[id$='" + arr[1] + "_caigounum']").val();
                if (num == "") {
                    num = 1;
                }
                if (dprice == "") {
                    dprice = 1;
                }
                var fzl = parseFloat(dprice) * parseInt(num);
                if (!isNaN(fzl)) {
                    $("input[id$='" + arr[1] + "_jine']").val(fzl);
                    $("input[id$='" + arr[1] + "_yanshounum']").val(num);
                } else {
                    $("input[id$='" + arr[1] + "_jine']").val("");
                }
            }

        });

    </script>
</head>
<body>
<jsp:include page="/WEB-INF/back/commons/show_message.jsp" />
<div class="areaTitle">
    <div class="key">杭州豆制食品配送发货单 - ${operation=='add' ? '添加' : '修改'}</div>
    <div class="ext"></div>
</div>
<div class="areaContent">
    <div class="areaTitleInner">
        <div id="bsContract" class="tabHot">商店信息</div>
        <div id="bcContractDetail" class="tabNormal">发货豆制品</div>
    </div>
    <s:form action="${operation}.do" method="post" modelAttribute="bean">
        <s:hidden path="id" />
        <input type="hidden" id="redirect" name="redirect" value="alter" />
        <div id="bsContract_area" class="tabarea">
            <div class="formAreaContent"><br />
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTable">
                    <tr>
                        <th width="15%">店：</th>
                        <td width="35%">
                            <s:input path="shopname" size="20" maxlength="100" />
                        </td>
                        <th width="15%">验收人：</th>
                        <td width="35%">
                            <s:input path="yanshouman" size="20" maxlength="22" />
                        </td>

                    <tr>
                        <th width="15%">店经理：</th>
                        <td width="35%">
                            <s:input path="shopmanager" size="20" maxlength="22" />
                        </td>
                        <th width="15%">发货日期 ：</th>
                        <td width="35%">
                            <s:input path="fahuodate" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" size="20" maxlength="30" />
                        </td>
                    </tr>
                </table>
                <br />
            </div>
        </div>

        <div id="bcContractDetail_area" class="tabarea">
            <div class="editButtonsArea">
                <div class="add"><a id="addBcContractDetail" class="operation" href="javascript:void(0);" url="/contract/bcContractDetail/add.do">增加</a></div>
                <div class="delete"><a id="removeBcContractDetail" class="operation" href="javascript:void(0);" url="/contract/bcContractDetail/remove.do">删除</a></div>
            </div>
            <div class="formAreaContent">
                <table id="tbBcContractDetail" width="100%" cellspacing="0" cellpadding="0" border="0" class="listNewsTable">
                    <thead>
                    <tr>
                        <th width="3%"><input type="checkbox" /></th>
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
                    <tr class="normal1">
                        <td align="center">
                            <input type="checkbox" value="" />
                            <input type="hidden" id="details_0_id" name="details[0].id" />
                        </td>
                        <td align="center" class="positionName">
                            <input type="text" id="details_0_dcode" name="details[0].dcode" size="10" maxlength="100" />
                        </td>
                        <td align="center">
                            <input type="text" id="details_0_dname" name="details[0].dname" size="10" maxlength="300" />
                        </td>
                        <td align="center">
                            <input type="text" id="details_0_dpackage" name="details[0].dpackage" size="10" maxlength="22" />
                        </td>
                        <td align="center">
                            <input type="text" id="details_0_dprice" name="details[0].dprice" size="10" maxlength="22" />
                        </td>
                        <td align="center">
                            <input type="text" id="details_0_caigounum" name="details[0].caigounum" size="10" maxlength="500" />
                        </td>
                        <td align="center">
                            <input type="text" id="details_0_yanshounum" name="details[0].yanshounum" size="10" maxlength="500" />
                        </td>
                        <td align="center">
                            <input type="text" id="details_0_jine" name="details[0].jine" readonly="readonly" style="background-color: #E8E8E8;" size="10" maxlength="500" />
                        </td>
                        <td align="center">
                            <input type="text" id="details_0_remark" name="details[0].remark" size="10" maxlength="500" />
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="formSubArea">
            <input type="submit" class="subBtn" value="保存" onclick="$('#redirect').val('list');" />
            <input type="button" value="返回列表" class="subBtn" onclick="javascript:location.href='list.do'" />
        </div>
    </s:form>
</div>
</body>
</html>
