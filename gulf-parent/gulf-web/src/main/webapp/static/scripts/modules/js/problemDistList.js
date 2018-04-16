Table_SuperSearchApplyList = function(options) {
	
	var base_options = $.extend({
		bAutoWidth: false,
		"aoColumns": [
			{ data:'cdate', "targets": [ 1 ], "bSortable": false, bSearchable: false },
			{ data:'city_name', "targets": [ 2 ], "bSortable": false, bSearchable: false },
			{ data:'dist_name', "targets": [ 3 ], "bSortable": false, bSearchable: false },
			{ data:'eci_name', "targets": [ 4 ], "bSortable": false, bSearchable: false },
			{ data:'lng', "targets": [ 5 ], "bSortable": false, bSearchable: false },
			{ data:'lat', "targets": [ 6 ], "bSortable": false, bSearchable: false
				 
			},
			{ data:'press', "targets": [ 7 ], "bSortable": false, bSearchable: false},
			{ data:'eci_location', "targets": [ 8 ], "bSortable": false, bSearchable: false,},
			{ data:'cover_type', "targets": [ 9 ], "bSortable": false, bSearchable: false,},
			{ data:'eci_cause', "targets": [ 10 ], "bSortable": false, bSearchable: false},
			{ data:'eci_advice', "targets": [ 11 ], "bSortable": false, bSearchable: false},
			{ data:'pro_advice', "targets": [ 12 ], "bSortable": false, bSearchable: false},
			{ data:'state', "targets": [ 13 ], "bSortable": false, bSearchable: false},
			{ "targets": [ 14 ], "bSortable": false, bSearchable: false,
				'data':function ( source, type, val) {
					var rowkey = source.cdate+source.city_code+source.dist_code+source.eci_id;
					var text = "";
					text = text + '<div class="hidden-sm hidden-xs action-buttons">';
					text = text + '	<a class="red" href="javascript:doDelete(\''+ rowkey +'\')">';
					text = text + '处理';
					text = text + '	</a>';
					text = text + '</div>';
					return text;
			  	}
			},
			{"className":'details-control',"orderable":false,"data":null,"defaultContent": ''}
		],

		"bProcessing": true,
		 "ajax": {
		    "url": "",
		    
		    "dataSrc": function ( json ) {
		    	return json.data;
		    }
		 },
		 "aaSorting": [
			 [ 0, "desc" ]
		 ],
		"bPaginate": true,
		"bLengthChange": false,
		aoFooter : false,
		select: true,
		"bFilter": false,
		"bInfo": false,
		"language": {
			"sProcessing" : "数据加载中...",
			"sInfo": "",
			"sZeroRecords": "暂无数据",
			"sInfoFiltered": "",
			"sLengthMenu": "每页显示 10 条记录",
			"oPaginate" : {  
		        "sFirst" : "首页",  
		        "sPrevious" : "上页",  
		        "sNext" : "下页",  
		        "sLast" : "末页"  
		    }
		},
		dom: 'Bfrtip',  
		"buttons": [  
                   {  
                       'extend': 'excel',  
                       'text': '导出',//定义导出excel按钮的文字  
                       'exportOptions': {  
                           'modifier': {  
                               'page': 'current'  
                           }  
                       }  
                   }  
               ],  
		"select": {
			style: 'single',
			blurable: true
		},
		"initComplete": function(settings, json) {
			resize_iframe_height("iframe-supersearch-main");
		}
    }, options || {});
	
	return {

		'show': function(renderId, params) {
			$.fn.dataTable.ext.errMode = 'none'; //不显示任何错误信息
			var myTable = $('#' + renderId).wrap("<div class='dataTables_borderWrap' />") .DataTable(base_options); 
			
			
			$(".exportButton").append($("div.dt-buttons",".dataTables_borderWrap"));//将分页显示到外面
			$(".pageOutContainer").append($("div.row:last",".dataTables_borderWrap"));//将分页显示到外面
		}
	}

};

