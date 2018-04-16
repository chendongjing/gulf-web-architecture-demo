//var curUsername = $('#head_username', window.parent.document).val();//获取当前用户
//var curUserid = $('#head_userid', window.parent.document).val();//当前用户id

//var basePath=window.document.location.pathname;
//var projectName=basePath.substring(0,basePath.substr(1).indexOf('/')+1);
//地图显示部分
function Map_Depthcoverage(options) {
	var dataList;
	var keyList;
	var map = {};
	var opts = {
			width : 400,     // 信息窗口宽度
			height: 180,     // 信息窗口高度
			title : "" , // 信息窗口标题
			enableMessage:false,//设置允许信息窗发送短息
			message:""
		};

	return {
		'show':function(renderId, params) {
			var outputPath = getMapPic();
            var fromat = ".png";	
            var tileLayer = new BMap.TileLayer();
            tileLayer.getTilesUrl = function (tileCoord, zoom) {
                var x = tileCoord.x;
                var y = tileCoord.y;
                var url = outputPath + zoom + '/' + x + '/' + y + fromat;
                return url;
            }
            var overlays = [];
            dataList = null;
            var tileMapType = new BMap.MapType('tileMapType', tileLayer,{minZoom: 1, maxZoom: 16});          
            map = new BMap.Map("map-main", { mapType: tileMapType });
			var point = new BMap.Point(params.lng,params.lat);
			 
			map.centerAndZoom(point,16); //设置地图级别16在获取东西经度差时约等于5km
			map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放		
			map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT}));    // 添加比例尺控件		
		    
		     if(params.keyRow != ''){
		    	//获取用户分布
				this.get_users(point,params, overlays);	
				this.get_KeyIndicators(point,params, overlays);
		     }
		     for(var index in keyList){
	    	 var arr=[];
	     	 var chinese= [];
	     	 var num=[];
	    	 arr=[
	    		 	keyList[index].ind_top1,
			    	keyList[index].ind_top2,
			    	keyList[index].ind_top3,
	     		    keyList[index].ind_top4,
	     		    keyList[index].ind_top5
	     		    ];
		    	 for(var i=0 ; i< arr.length ; i++){
		    		var array=[];
		 	    	var nums=[];
		    		  array=arr[i].split("|");
		    		 for(var j=0;j<array.length;j++){
		    			var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
		    			if(reg.test(array[j])){
		    				chinese.push(array[j]);
		    			}else{
		    				if(array[j].startsWith("\.")){
		    					var s="0";
		    					num.push(s+array[j]);	
		    				}else{
		    					num.push(array[j]);		
		    				}
		    			}
		    		 }
		    	 }
		     	}
			   for (var idx in dataList) {
				var eciPoint = new BMap.Point(dataList[idx].lng, dataList[idx].lat);
			    //小区名称
				var eciName = dataList[idx].eci_name;
				//根因
				var eciCause = dataList[idx].eci_cause;
				//建议
				var eciAdvice = dataList[idx].eci_advice;
				//专家建议
				var pro_advice = dataList[idx].pro_advice;
				//置信度
				var cause_p=(Math.round(dataList[i].cause_p*100)/100)*100;
				//问题小区
				exceptionIcon = new BMap.Icon(projectName+"/static/scripts/modules/images/eci_marker.png",new BMap.Size(23, 25)); 
				var marker = new BMap.Marker(eciPoint, {icon:exceptionIcon});  // 创建标注
				map.addOverlay(marker);
				 
				marker.addEventListener("click",attribute);
				//获取覆盖物位置
				function attribute(e){
				var p = e.target;
				 for (var i in dataList) {
					    //小区名称
						 eciName = dataList[i].eci_name;
						//根因
						 eciCause = dataList[i].eci_cause;
						//建议
						 eciAdvice = dataList[i].eci_advice;
						//专家建议
						var pro_advice = dataList[i].pro_advice;
						//置信度
						
						 cause_p=(Math.round(dataList[i].cause_p*100)/100)*100;
						
						var op="<div style='overflow-y:scroll;height:180px' id='echarts-container'>小区名称："
							+ eciName 
							+ "<br>根因:" + eciCause
							+ "<br>建议:" + eciAdvice
							+ "<br>专家建议:" + pro_advice
							+"<br>置信度:" + cause_p+"%"
							+"</div>";	
						var infoWindow=new BMap.InfoWindow(op,opts);// 创建信息窗口对象 
						var EchartDiv=document.getElementById("map-main");
						if(!EchartDiv){
							alert("!!!!!!");
							return ;
						};
						//var myChart=echarts.init(EchartDiv);
						var option = {
							    tooltip : {
							        trigger: 'axis',
							        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
							            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
							        }
							    },
							    grid: {
							        left: '3%',
							        right: '4%',
							        bottom: '3%',
							        containLabel: true
							    },
							    xAxis:  {
							        type: 'value'
							    },
							    yAxis: {
							        type: 'category',
							        data: chinese
							    },
							    series: [
							        {
							            name: '云上贵州',
							            type: 'bar',
							            stack: '总量',
							            label: {
							                normal: {
							                    show: true,
							                    position: 'right'
							                }
							            },
							            data: num
							        }
							    ]
							};
					//	myChart.setOption(option);
						
						if(dataList[i].lng == p.getPosition().lng && dataList[i].lat == p.getPosition().lat){	
							
							map.openInfoWindow(infoWindow,new BMap.Point(p.getPosition().lng, p.getPosition().lat)); //开启信息窗口
							
					 }
				 }
				   
				}	
				overlays.push(marker);
				 if(dataList[idx].eci_location !=''){
					//基站落点
					var jz_center = new BMap.Point(dataList[idx].jz_lng, dataList[idx].jz_lat);
					map.centerAndZoom(jz_center, 16);
					var centreIcon = new BMap.Icon(projectName+"/static/scripts/modules/images/baseStation.png", new BMap.Size(23, 25));
					var centreMarker = new BMap.Marker(jz_center, {icon:centreIcon});  // 创建标注
					// 将标注添加到地图中
					var infoWindow = new BMap.InfoWindow("基站名称："+dataList[idx].jzName+ "<br>经度:" + dataList[idx].jz_lng+ "<br>纬度:" + dataList[idx].jz_lat, opts);
					marker.addEventListener("click", function(){       
						map.openInfoWindow(infoWindow, jz_center); //开启信息窗口
					});
					map.openInfoWindow(infoWindow, jz_center); //开启信息窗口
					map.addOverlay(centreMarker);
					overlays.push(centreMarker);
				 }
				 
    		 
			}
			 	  
		},
		
		//获取用户感知分布
		'get_users':function (point,params, overlays) {
			var loading1 = layer.msg('处理中', {
				icon: 16,
				shade: 0.5,
				offset: 't',
			});
			// 获取小区
			url = projectName+"/home/problemDist/getEciList.json";
		    $.ajax({
		        type : "get",
		        async : false,       
		        url : url,
		        data : {"startRow":params.keyRow},
		        dataType : "json",

		        success : function(result) {
		            if (result.success) {
		            	if (result.data.length == 0) {
		            		//清空列表缓存
		            		dataList = null;
		            		alert("选择范围内没有小区！");
		            		return false;
		            	}
		            	dataList = result.data;
                     
		            }
		       },
		       
		       error : function(errorMsg) {
		           //请求失败时执行该函数
		    	   layer.close(loading1);
		    	   alert("地图请求数据失败!");
		       }
		    });
		},
		//获取关键性指标
		'get_KeyIndicators':function (point,params, overlays) {
			var loading1 = layer.msg('处理中', {
				icon: 16,
				shade: 0.5,
				offset: 't',
			});
			// 获取关键性指标
			url = projectName+"/home/problemDist/getKeyIndicators.json";
		    $.ajax({
		        type : "get",
		        async : false,       
		        url : url,
		        data : {"startRow":params.keyRow},
		        dataType : "json",

		        success : function(result) {
		            if (result.success) {
		            	if (result.data.length == 0) {
		            		//清空列表缓存
		            		keyList = null;
		            		alert("没东西");
		            		return false;
		            	}
		            	keyList = result.data;
                     
		            }
		       },
		       
		       error : function(errorMsg) {
		           //请求失败时执行该函数
		    	   layer.close(loading1);
		    	   alert("地图请求数据失败!");
		       }
		    });
		}
		
		
	}
};

