(function($){
	var ms = {
		init:function(obj,args){
			return (function(){
				ms.fillHtml(obj,args);
				ms.bindEvent(obj,args);
			})();
		},
		//填充html
		fillHtml:function(obj,args){
			return (function(){
				obj.empty();
				//上一页
				if(args.current > 1){
					obj.append('<a href="javascript:;" class="prevPage">上一页</a>');
				}else{
					obj.remove('.prevPage');
					obj.append('<span class="disabled">上一页</span>');
				}
				//中间页码
				if(args.current != 1 && args.current >= 4 && args.pageCount != 4){
					obj.append('<a href="javascript:;" class="tcdNumber">'+1+'</a>');
				}
				if(args.current-2 > 2 && args.current <= args.pageCount && args.pageCount > 5){
					obj.append('<span>...</span>');
				}
				var start = args.current -2,end = args.current+2;
				if((start > 1 && args.current < 4)||args.current == 1){
					end++;
				}
				if(args.current > args.pageCount-4 && args.current >= args.pageCount){
					start--;
				}
				for (;start <= end; start++) {
					if(start <= args.pageCount && start >= 1){
						if(start != args.current){
							obj.append('<a href="javascript:;" class="tcdNumber">'+ start +'</a>');
						}else{
							obj.append('<span class="current">'+ start +'</span>');
						}
					}
				}
				if(args.current + 2 < args.pageCount - 1 && args.current >= 1 && args.pageCount > 5){
					obj.append('<span>...</span>');
				}
				if(args.current != args.pageCount && args.current < args.pageCount -2  && args.pageCount != 4){
					obj.append('<a href="javascript:;" class="tcdNumber">'+args.pageCount+'</a>');
				}
				//下一页
				if(args.current < args.pageCount){
					obj.append('<a href="javascript:;" class="nextPage">下一页</a>');
				}else{
					obj.remove('.nextPage');
					obj.append('<span class="disabled">下一页</span>');
				}
			})();
		},
		//绑定事件
		bindEvent:function(obj,args){
			return (function(){
				obj.on("click","a.tcdNumber",function(){
					var current = parseInt($(this).text());
					ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(current);
					};
					
					var username=$("#username").text();
					alert(username);
					$.ajax({   
						type : "get",
						url : "/user/searchCarsPage",
						data : {current:current},
						dataType : "json",
						success : function(pagelist){
							 $("#pagelisttable").empty();
							 $("#pagelisttable").append(
									 "<tr>"+
										"<th>车牌号</th>"+
										"<th>类型</th>"+
										"<th>车长</th>"+
										"<th>载重</th>"+
										"<th>注册时间</th>"+
										"<th>车的总数量</th>"+
										"<th>使用中的数量</th>"+
										"<th>起发地</th>"+
										"<th>目的地</th>"+
										"<th>操作</th>"+
									"</tr>"
							 );
							$.each(pagelist.page, function(){     
								$("#pagelisttable").append(
									"<tr>"+
										"<td>"+this.car_number+"</td>"+
										"<td>"+this.car_type+"</td>"+
										"<td>"+this.car_length+"</td>"+
										"<td>"+this.bear_weight+"</td>"+
										"<td>"+this.register_time+"</td>"+
										"<td>"+this.all_account+"</td>"+
										"<td>"+this.using_account+"</td>"+
										"<td>"+this.start_station+"</td>"+
										"<td>"+this.destination_station+"</td>"+
										"<td>"+
											"<a href='#' id='toTransportInfoEdit' class='edit' onclick='toTransportInfoEdit()'>编辑&nbsp;</a>"+
											"<a href='#'  onclick='toDelete()'>删除</a>"+
										"</td>"+
									"</tr>"
								 );
								
								
							 });
							
							
							}
					}); 
					
					
					
				});
				//上一页
				obj.on("click","a.prevPage",function(){
					var current = parseInt(obj.children("span.current").text());
					ms.fillHtml(obj,{"current":current-1,"pageCount":args.pageCount});
					
					$.ajax({   
						type : "get",
						url : "/user/searchCarsPage",
						data : {current:current},
						dataType : "json",
						success : function(pagelist){
							 $("#pagelisttable").empty();
							 $("#pagelisttable").append(
									 "<tr>"+
										"<th>车牌号</th>"+
										"<th>类型</th>"+
										"<th>车长</th>"+
										"<th>载重</th>"+
										"<th>注册时间</th>"+
										"<th>车的总数量</th>"+
										"<th>使用中的数量</th>"+
										"<th>起发地</th>"+
										"<th>目的地</th>"+
										"<th>操作</th>"+
									"</tr>"
							 );
							$.each(pagelist.page, function(){     
								
								$("#pagelisttable").append(
										"<tr>"+
										"<td>"+this.car_number+"</td>"+
										"<td>"+this.car_type+"</td>"+
										"<td>"+this.car_length+"</td>"+
										"<td>"+this.bear_weight+"</td>"+
										"<td>"+this.register_time+"</td>"+
										"<td>"+this.all_account+"</td>"+
										"<td>"+this.using_account+"</td>"+
										"<td>"+this.start_station+"</td>"+
										"<td>"+this.destination_station+"</td>"+
										"<td>"+
											"<a href='#' id='toTransportInfoEdit' onclick='toTransportInfoEdit()' class='edit'>编辑</a>"+
											"<a href='#'>删除</a>"+
										"</td>"+
									"</tr>"
								 );
								
								
							 });
							}
					});
					
					
					
					if(typeof(args.backFn)=="function"){
						args.backFn(current-1);
					};
				});
				//下一页
				obj.on("click","a.nextPage",function(){
					var current = parseInt(obj.children("span.current").text());
					ms.fillHtml(obj,{"current":current+1,"pageCount":args.pageCount});
					
					
					$.ajax({   
						type : "get",
						url : "/user/searchCarsPage",
						data : {current:current},
						dataType : "json",
						success : function(pagelist){
							 $("#pagelisttable").empty();
							 $("#pagelisttable").append(
									 "<tr>"+
										"<th>车牌号</th>"+
										"<th>类型</th>"+
										"<th>车长</th>"+
										"<th>载重</th>"+
										"<th>注册时间</th>"+
										"<th>车的总数量</th>"+
										"<th>使用中的数量</th>"+
										"<th>起发地</th>"+
										"<th>目的地</th>"+
										"<th>操作</th>"+
									"</tr>"
							 );
							$.each(pagelist.page, function(){     
								
								$("#pagelisttable").append(
										"<tr>"+
										"<td>"+this.car_number+"</td>"+
										"<td>"+this.car_type+"</td>"+
										"<td>"+this.car_length+"</td>"+
										"<td>"+this.bear_weight+"</td>"+
										"<td>"+this.register_time+"</td>"+
										"<td>"+this.all_account+"</td>"+
										"<td>"+this.using_account+"</td>"+
										"<td>"+this.start_station+"</td>"+
										"<td>"+this.destination_station+"</td>"+
										"<td>"+
											"<a href='#' id='toTransportInfoEdit' onclick='toTransportInfoEdit()' class='edit'>编辑</a>"+
											"<a href='#'>删除</a>"+
										"</td>"+
									"</tr>"
								 );
								
								
							 });
							}
					});
					
					
					if(typeof(args.backFn)=="function"){
						args.backFn(current+1);
					}
				});
			})();
		}
	}
	$.fn.createPage = function(options){
		var args = $.extend({
			pageCount : 10,
			current : 1,
			backFn : function(){}
		},options);
		ms.init(this,args);
	}
})(jQuery);