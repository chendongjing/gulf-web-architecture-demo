 
//地图显示部分
function Map_Depthcoverage(options) {
	var dataList;
	var map = {};
	var opts = {
			width : 300,     // 信息窗口宽度
			height: 180,     // 信息窗口高度
			title : "" , // 信息窗口标题
			enableMessage:true,//设置允许信息窗发送短息
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
		     }
		     
			    for (var idx in dataList) {
				var point = new BMap.Point(dataList[idx].lng, dataList[idx].lat);
				
			    //小区名称
				var eciName = dataList[idx].eci_name;
				//根因
				var eciCause = dataList[idx].eci_cause;
				//覆盖类型
				var coverType = dataList[idx].cover_type;
				//建议
				var eciAdvice = dataList[idx].eci_advice
				
				//问题小区
				exceptionIcon = new BMap.Icon(projectName+"/static/scripts/modules/images/eci_marker.png",new BMap.Size(23, 25)); 
				var marker = new BMap.Marker(point, {icon:exceptionIcon});  // 创建标注
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
						//建议
						var pro_advice = dataList[i].pro_advice;
					var infoWindow = new BMap.InfoWindow("<div style='overflow-y:scroll;height:180px'>小区名称："+ eciName 
							+ "<br>根因:" + eciCause
							+ "<br>建议:" + eciAdvice
							+ "<br>专家建议:" + pro_advice
							+"</div>"
							, opts);  // 创建信息窗口对象 
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
			url = projectName+"/home/netStructOpt/getEciList.json";
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
		            		return;
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
		}
	}
};
