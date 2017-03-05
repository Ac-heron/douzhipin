/**
 * 查询分页数据弹出选择层
 * url,param,back自定义添加的属性
 * 其它的属性请参照layer官网文档 http://sentsin.com/jquery/layer/api.html#base
 */
$(function(){
	var openLayerObject = new Object();
	$.fn.extend({
		/**
		 * 注册弹出窗口事件
		 */
		layerOpen : function(options) {
			var defaults = {
					type: 2, // 表示为iframe不需要变动
				    shadeClose: true, 
				    shade: [0.3, '#000'], 
				    title: false, // 弹出窗口标签
				    closeBtn: [0, true],
				    border: [0],
				    offset: ['20px',''],
				    area: ['800px', ($(window).height() - 200) +'px'], // area[窗口宽度, 窗口高度]
				    iframe: {
				    	src: '', 
				    	scrolling: 'auto'
				    }
			};
			var options = $.extend(defaults, options);
			// 判断条件是否具备
			if(options.url == null || options.back == null){
				alert("请求的URL或回调函数必需设置");
				return;
			}
			// 参数拼接
			options.iframe.src = options.url;
			
			// 点击弹出窗口
			$(this).click(function() {
				// 缓存保存属性
				$(this).data("options", $.extend(true, {}, options));
				var optionsBak = $(this).data("options")
				if(optionsBak.param != null){	
					$.each(optionsBak.param, function(i, n) {
						if($.isFunction(n)){
							//alert(n);
							optionsBak.param[i] = optionsBak.param[i]();
						}
					});
					optionsBak.iframe.src += '?' + $.param(optionsBak.param);
				}
				var i = $.layer(optionsBak); 
				//alert(options);
				$(this).data("options", $.extend(true, {}, options));
				openLayerObject["layer" + i] = $(this);
			});
		},
		
		/**
		 * 取得窗口初始化的属性
		 */
		layerOptions : function() {
			return $(this).data("options");
		},
	});
	
	$.extend({
		/**
		 * 执行回调函数
		 */
		layerBack : function(data, i) {
			var $this = openLayerObject["layer" + i];
			openLayerObject["layer" + i] = null;
			$this.data("options").back(data, $this);
			layer.close(i);
		}
	});
});