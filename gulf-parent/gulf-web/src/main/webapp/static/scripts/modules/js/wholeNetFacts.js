var basePath=window.document.location.pathname;
var projectName=basePath.substring(0,basePath.substr(1).indexOf('/')+1);
var dataMapList = [];
//全网分布（问题小区和正常小区比例和数目）
pie_showScalePie = function(options) {
	
	var base_options = $.extend({
		    title : {
		        text: '全网健康度',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{b} : {c} 个({d}%)"
		    },
		    legend: {
		        orient: 'horizontal',
		        left: 'center',
		        top: 'bottom',
		        data:['问题小区','正常小区',]
		    },
		    series : [
		        {
		            name: '',
		            type: 'pie',
		            selectedMode: true,
		            selectedOffset: 0,
		            radius : '55%',
		            center: ['50%', '60%'],
		            
		            data:[
		            	  {value:0, name:'正常小区', itemStyle:{normal:{  color:'#68BC31'}}},
			              {value:0, name:'问题小区', itemStyle:{normal:{ color:'#DA5430'}}}
		            ],
		            
		            itemStyle: {
		            	normal: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		}, options || {});

	return {
		'show':function(renderId,params) {
			var myChart = echarts.init(document.getElementById(renderId));
			myChart.showLoading({
			    text: '正在努力的读取数据中...',
			});

		    $.ajax({
		        type : "get",
		        async : false,       
		        url : projectName+'/home/wholeNetFact/showScalePie.json',
		        data : params,
		        dataType : "json",
		        
		        success : function(result) {

		            if (result.success='True') {
		                dataMapList = result.data;
		            	dataList = base_options.series[0].data;
		            	var cnt_eci_normal = 0;
		            	var cnt_eci_low = 0;
		            	if(dataMapList.length > 0){
		            	for(var idx in dataList) {
		            		cnt_eci_normal += parseInt(dataMapList[idx].cnt_eci_normal);
		            		cnt_eci_low += parseInt(dataMapList[idx].cnt_eci_low);
		            	} 
		            	dataList[0].value = cnt_eci_normal;
		            	dataList[1].value = cnt_eci_low;
		            	}
						myChart.hideLoading();
						myChart.setOption(base_options);

		            }
		       },
		       error : function(errorMsg) {
		    	   	alert("图表请求数据失败!");
		        	myChart.hideLoading();
		       }
		    });
		}
		
	}
};

//第二个饼图的LegendData
function getLegendData(){
	var jsonstr = [];
	for (var i = 0; i < dataMapList.length; i++) {
	var json = {};
	json.name = dataMapList[i].dist_name;
	jsonstr.push(json);
	}
	return jsonstr;
	}
//第二个饼图的SeriesData
function getSeriesData(){
	var jsonstr = [];
	if(dataMapList.length == 0){
		jsonstr = [{value:0,name:"暂无数据",itemStyle:{normal:{ color:'#68BC31'}}}];
	}
	for (var i = 0; i < dataMapList.length; i++) {
	var json = {};
	json.name = dataMapList[i].dist_name;
	json.value = parseInt(dataMapList[i].cnt_eci_none);
	if(i == 0){
		json.itemStyle = "{normal:{ color:'#2091CF'}}";	
	}else if(i % 2 == 0){
		json.itemStyle = "{normal:{ color:'#DA5430'}}";
	}else if(i % 3 == 0){
		json.itemStyle = "{normal:{ color:'#68BC31'}}";
	}else if(i % 5 == 0){
		json.itemStyle = "{normal:{ color:'#AF4E96'}}";
	}else{
		json.itemStyle = "{normal:{ color:'#68BC31'}}";
	}
	jsonstr.push(json);
	}
	return jsonstr;
	}

//问题小区地域分布比例和数目（区县）
pie_showAreaPie = function(options) {
	var base_options = $.extend({
		    title : {
		        text: '问题小区地域分布',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{b} : {c} 个({d}%)"
		    },
		    legend: [{
		        orient: 'horizontal',
		        left: 'center',
		        top: 'bottom',
		        data:getLegendData()
		    }],
		    series : [
		        {
		            name: '',
		            type: 'pie',
		            selectedMode: true,
		            selectedOffset: 0,
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:getSeriesData(),
		            color: ['#2091CF','#DA5430'],
		            itemStyle: {
		            	normal: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		}, options || {});

	return {
		'show':function(renderId,params) {
			var myChart = echarts.init(document.getElementById(renderId));
			myChart.on('pieselectchanged', this.doSelected);

			myChart.showLoading({
			    text: '正在努力的读取数据中...',
			});
		 
			myChart.hideLoading();    //隐藏加载动画
			myChart.setOption(base_options);

		},
	 
	}
};

//问题小区数量月趋势图
line_tendencyChart = function(options) {

	var base_options = $.extend({
	    title : {
	        text: '全网健康度月趋势图',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {
	            type: 'shadow'
	        }
	    },
	    legend: {
	        orient: 'horizontal',
	        left: 'center',
	        top: 'bottom',
	        data:['好','差']
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : ['201706','201707','201708','201709','201710','201711','201712']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            splitArea : {show : true}
	        }
	    ],
	    grid: {
	        x2:40
	    },
	    series : [
	        {
	            name:'好',
	            type:'line',
	            itemStyle:{normal:{ color:'#2091CF'}},
	            data:[4325,3535,5353,5353,4322,4323,2353]
	            
	        },
	         
	        {
	            name:'差',
	            type:'line',
	            itemStyle:{normal:{ color:'#DA5430'}},
	            data:[100,324,434,233,422,324,558]
	           
	        }
	    ]
	}, options || {});

	return {
		'show':function(renderId,params) {
			var myChart = echarts.init(document.getElementById(renderId));
			myChart.showLoading({
			    text: '正在努力的读取数据中...',
			});
		
			myChart.hideLoading();    //隐藏加载动画
			myChart.setOption(base_options);
		}
	}
};

//问题小区问题类别表现数目
bar_classChart = function(options) {

	var base_options = $.extend({
		title: {
		    text: '覆盖问题分布',
	        x:'center'
		},
		tooltip: {},
		legend: {
	        orient: 'horizontal',
	        left: 'center',
	        top: 'bottom',
		    data:[{name:'弱覆盖'},{name:'空洞覆盖'},{name:'重叠覆盖'},{name:'过度覆盖'}]
		},
		
		xAxis: {
		    data: ['弱覆盖','空洞覆盖','重叠覆盖','过度覆盖']
		},
		yAxis: {},
		series: [{
		    name: '',
		    type: 'bar',
	        data: [
	        	{name:'弱覆盖',  value: 0,  itemStyle:{normal:{ color:'#2091CF'}}}, 
	        	{name:'空洞覆盖', value: 0,  itemStyle:{normal:{ color:'#68BC31'}}}, 
	        	{name:'重叠覆盖', value: 0,  itemStyle:{normal:{ color:'#AF4E96'}}}, 
	        	{name:'过度覆盖', value: 0,  itemStyle:{normal:{ color:'#DA5430'}}}
	        ]
		}]
	}, options || {});

	return {
		'show':function(renderId,params) {
			var myChart = echarts.init(document.getElementById(renderId));
			
			myChart.showLoading({
			    text: '正在努力的读取数据中...',
			});
			dataList = base_options.series[0].data;
			var cnt_eci_week = 0;
        	var cnt_eci_none = 0;
        	var cnt_eci_repeat = 0;
        	var cnt_eci_over = 0;
        	if(dataMapList.length > 0){
        	
        	for(var idx in dataList) {
        		cnt_eci_week += parseInt(dataMapList[idx].cnt_eci_week);
        		cnt_eci_none += parseInt(dataMapList[idx].cnt_eci_none);
        		cnt_eci_repeat += parseInt(dataMapList[idx].cnt_eci_repeat);
        		cnt_eci_over += parseInt(dataMapList[idx].cnt_eci_over);
        	} 
        	dataList[0].value = cnt_eci_week;
        	dataList[1].value = cnt_eci_none;
        	dataList[2].value = cnt_eci_repeat;
        	dataList[3].value = cnt_eci_over;
        	}
			myChart.hideLoading();    //隐藏加载动画
			myChart.setOption(base_options);
		}
	}
};
