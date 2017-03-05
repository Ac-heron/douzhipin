/**
 * 日志组件JS操作
 * 依赖easyUI,信息提示页面
 * @author liuqiang
 */
$(function(){
	var editLogArray = {};
	function combDate(date){
		if(date != null){
			var d = new Date(date);
			return d.getFullYear() + '.'+(d.getMonth()+1) + '.' + d.getDate();
		}
		return "";
	}
	$.fn.extend({
		/**
		 * 取得修改记录
		 * @param params.tableName 表名
		 * @param params.filedName 字段名
		 * @param params.infoId 信息的ID
		 */
		showEditLog:function(params){
			var $this = $(this);
			$.get("/record/editLog/all.do", params, function(data){
				var listNews = "";
				listNews +='<div class="listNews">';
				listNews +='<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listNewsTable">';
				listNews +='<thead>';
				listNews +='<tr>';
				listNews +='<th>原值</th>';
				listNews +='<th>新值</th>';
				listNews +='<th>修改人</th>';
				listNews +='<th>修改时间</th>';
				listNews +=' </tr>';
				listNews +='</thead>';
				listNews +='<tbody>';
				if(!$.isEmptyObject(data)){
					$.each(data, function(i, map){						
						listNews +='<tr class="'+(i%2==0?'normal1':'normal2')+'">';
						listNews +='<td align="left">';
						listNews += map.oldValue;
						listNews +='</td>';
						listNews +='<td align="left">';
						listNews += map.newValue;
						listNews +='</td>';
						listNews +='<td align="left">';
						listNews += map.doName;
						listNews +='</td>';
						listNews +='<td align="center">';
						listNews += combDate(map.doDate);
						listNews +='</td>';
						listNews +='</tr>';
					});
				}else{
					listNews += '<tr><td colspan="4">没有数据</td></tr>';
				}
				listNews +='</tbody>';
				listNews +='</table>';
				listNews +='</div>';
				
				editLogArray[params.tableName+'_'+params.filedName] = listNews;
				
				$this.tooltip({
					content:editLogArray[params.tableName+'_'+params.filedName],
					position:'bottom',
					onShow: function(){
						var t = $(this);
						t.tooltip('arrow').css('left', 20);
						t.tooltip('tip').css('left', t.offset().left);
						t.tooltip('tip').unbind().bind('mouseenter', function(){
							t.tooltip('show');
						 	}).bind('mouseleave', function(){
						 		t.tooltip('hide');
						 });
					}
				});
			}, "json");
			
		},
		editLog:function(editLog){
			// 双击可编辑
			$(this).bind("dblclick",function(){
				var oldValue = $.trim($(this).text());
				$(this).text("");
				$(this).append('<input type="text" value="'+oldValue+'" oldValue="'+oldValue+'"/>');
				$(this).find("input").focus();
				// input框失去焦点时触发
				$(this).find("input").bind("blur",function(){
					var $this = $(this);
					var newValue = $.trim($(this).val());
					var oldValue = $.trim($(this).attr("oldValue"));
					if(newValue == ""){
						$this.parent().text(oldValue);
					}
					else if(newValue == oldValue){
						$this.parent().text(oldValue);
						//showMessage("无修改");
					}
					else{
						editLog.oldValue = oldValue;
						editLog.newValue = newValue;
						//doStatus记录类型{0:新增,1:修改,2:删除}
						editLog = $.extend({doStatus:1}, editLog);
						$.ajax({
							async:false,
							type : "POST",
				            contentType: "application/json; charset=utf-8",
							data:JSON.stringify(editLog),
							url:"/record/editLog/add.do",
							dataType:"json",
							success:function(data){
								if(data.status){
									$this.parent().text(newValue);
									//showMessage(editLog.filedDesc + "修改记录保存成功");
								}else{
									$this.parent().text(oldValue);
									//showMessage(editLog.filedDesc + "修改记录保存失败");
								}
							}
						});
					}
				});
			});
		}
	});
	
	$(".editLog").click(function(){
		
	});
});