$(function() {
	// Set get date
	$(".dateInput").datepicker('setDate', new Date());
	initComponent();
	validate();
});

function searchBrief(){
	var dataItems = [];
	var fromDate = $("#searchBriefForm input[name='fromDate']").val();
	var toDate = $("#searchBriefForm input[name='toDate']").val();
	var code = $("#searchBriefForm input[name='code']").val();
	var customerName = $("#searchBriefForm input[name='customerName']").val();
	var briefTypeBox = $("#searchBriefForm select[name='briefTypeBox']").val();
	var stockBox = $("#searchBriefForm select[name='stockBox']").val();
	
	
	if($("#searchBriefForm").valid()){
		fromDate+=" 00:00:00";
		toDate+=" 23:59:59";
		$.ajax({
			url : "/suply-manager/brief/searchBriefsInReport",
			type : "GET",
			dataType : "JSON",
			data : {
				fromDate : fromDate,
				toDate : toDate,
				code : code,
				customerName : customerName,
				briefTypeBox : briefTypeBox,
				stockBox : stockBox
			},
			success : function(response) {
				var i = 0;
				$.each(response, function(key, value) {
					i++;
					if(value.customer.code.indexOf(code) >= 0 && value.customer.name.indexOf(customerName) >= 0 && value.briefType.id == briefTypeBox && value.stock.id == stockBox)
					{
						dataItems.push([ i, value.content,
											moment(value.createDate).format("Do MMMM YYYY"),
											value.customer.code, value.customer.name,
											value.department.name, value.stock.name,
											value.briefType.name ]);
					}
					if(value.customer.code.indexOf(code) >= 0 && value.customer.name.indexOf(customerName) >= 0 && briefTypeBox == "0" && stockBox == "0")
					{
						dataItems.push([ i, value.content,
											moment(value.createDate).format("Do MMMM YYYY"),
											value.customer.code, value.customer.name,
											value.department.name, value.stock.name,
											value.briefType.name ]);
					}
					if(value.customer.code.indexOf(code) >= 0 && value.customer.name.indexOf(customerName) >= 0 && value.briefType.id == briefTypeBox && stockBox == "0")
					{
						dataItems.push([ i, value.content,
											moment(value.createDate).format("Do MMMM YYYY"),
											value.customer.code, value.customer.name,
											value.department.name, value.stock.name,
											value.briefType.name ]);
					}
					if(value.customer.code.indexOf(code) >= 0 && value.customer.name.indexOf(customerName) >= 0 && briefTypeBox == "0" && value.stock.id == stockBox)
					{
						dataItems.push([ i, value.content,
											moment(value.createDate).format("Do MMMM YYYY"),
											value.customer.code, value.customer.name,
											value.department.name, value.stock.name,
											value.briefType.name ]);
					}

//					dataItems.push([ i, value.content,
//							moment(value.createDate).format("Do MMMM YYYY"),
//							value.customer.code, value.customer.name,
//							value.department.name, value.stock.name,
//							value.briefType.name ]);
				});
				$('#tableBrief').dataTable({
					"bDestroy" : true,
					"bSort" : true,
					"bFilter" : true,
					"bLengthChange" : true,
					"bPaginate" : true,
					"sDom" : '<"top">rt<"bottom"flp><"clear">',
					"aaData" : dataItems,
					"aaSorting" : [],
					"aoColumns" : [ {
						"sTitle" : "STT"
					}, {
						"sTitle" : "Nội dung"
					}, {
						"sTitle" : "Ngày lập"
					}, {
						"sTitle" : "Mã Khách hàng"
					}, {
						"sTitle" : "Khách hàng"
					}, {
						"sTitle" : "Phòng ban"
					}, {
						"sTitle" : "Kho"
					}, {
						"sTitle" : "Loại Hồ Sơ"
					} ]
				});
			}
		});
	}
}

function validate(){
	$("#searchBriefForm").validate({
		rules : {
			fromDate:{
				required:true
			},
			toDate:{
				required:true
			}
		},
		messages : {
			fromDate:{
				required:"Từ ngày không được để trống"
			},
			toDate:{
				required:"Tới ngày không được để trống"
			}
		},
	});
}

function initComponent(){
	displayNotePaid();
	chartBrief();
	displayBrief();
	listGraphByStock();
	listBriefByDepartment();
}

function displayBrief() {
	var dataItems = [];
	$.ajax({
		url : "/suply-manager/brief/getAll",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataItems.push([ i, value.content,
						moment(value.createDate).format("Do MMMM YYYY"),
						value.customer.code, value.customer.name,
						value.department.name, value.stock.name,
						value.briefType.name ]);
			});
			$('#tableBrief').dataTable({
				"bDestroy" : true,
				"bSort" : true,
				"bFilter" : true,
				"bLengthChange" : true,
				"bPaginate" : true,
				"sDom" : '<"top">rt<"bottom"flp><"clear">',
				"aaData" : dataItems,
				"aaSorting" : [],
				"aoColumns" : [ {
					"sTitle" : "STT"
				}, {
					"sTitle" : "Nội dung"
				}, {
					"sTitle" : "Ngày lập"
				}, {
					"sTitle" : "Mã Khách hàng"
				}, {
					"sTitle" : "Khách hàng"
				}, {
					"sTitle" : "Phòng ban"
				}, {
					"sTitle" : "Kho"
				}, {
					"sTitle" : "Loại Hồ Sơ"
				} ]
			});
		}
	});
}

function displayNotePaid() {
	var dataItems = [];
	$.ajax({
		url : "/suply-manager/note/getNotePaid",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataItems.push([
						i,
						value.code,
						value.department.name,
						moment(value.borrowDate).format(
								"Do MMMM YYYY ,h:mm:ss A"),
						moment(value.paidDate)
								.format("Do MMMM YYYY ,h:mm:ss A") ]);
			});
			$('#tablePaidNote').dataTable({
				"bDestroy" : true,
				"bSort" : true,
				"bFilter" : true,
				"bLengthChange" : true,
				"bPaginate" : true,
				"sDom" : '<"top">rt<"bottom"flp><"clear">',
				"aaData" : dataItems,
				"aaSorting" : [],
				"aoColumns" : [ {
					"sTitle" : "STT"
				}, {
					"sTitle" : "Code"
				}, {
					"sTitle" : "Phòng ban"
				}, {
					"sTitle" : "Ngày mượn"
				}, {
					"sTitle" : "Ngày Trả"
				} ]
			});
		}
	});
}

function chartBrief() {
	var listData = [];
	$.ajax({
		url : "/suply-manager/brief/listBriefNote",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				var name = value.noteType;
				var noteType = "";
				if (name == null) {
					noteType = "Chưa mượn"
				} else {
					noteType = value.noteType;
				}
				listData.push({
					y : value.count,
					legendText : noteType,
					label : noteType
				})
			});
			// Show do thi
			var chart = new CanvasJS.Chart("chartBrief", {
				title : {
					text : "Phân loại hồ sơ mượn trả"
				},
				exportFileName : "Pie Chart",
				exportEnabled : true,
				animationEnabled : true,
				legend : {
					verticalAlign : "bottom",
					horizontalAlign : "center"
				},
				data : [ {
					type : "pie",
					showInLegend : true,
					toolTipContent : "{legendText}: <strong>số lượng hồ sơ là {y}</strong>",
					indexLabel : "{label} {y}",
					dataPoints : listData
				} ]
			});
			chart.render();

		}
	});
}

function listGraphByStock(){
	var listData = [];
	$.ajax({
		url : "/suply-manager/brief/listGraphByStock",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				var name = value.name;
				listData.push({
					y : value.count,
					legendText : name,
					label : name
				})
			});
			// Show do thi
			var chart = new CanvasJS.Chart("chartBriefStock", {
				title : {
					text : "Biểu đồ hồ sơ theo kho"
				},
				exportFileName : "Pie Chart",
				exportEnabled : true,
				animationEnabled : true,
				legend : {
					verticalAlign : "bottom",
					horizontalAlign : "center"
				},
				data : [ {
					type : "pie",
					showInLegend : true,
					toolTipContent : "{legendText}: <strong>số lượng hồ sơ là {y}</strong>",
					indexLabel : "{label} {y}",
					dataPoints : listData
				} ]
			});
			chart.render();

		}
	});
}

function listBriefByDepartment(){
	var listData = [];
	$.ajax({
		url : "/suply-manager/brief/listBriefByDepartment",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				var name = value.name;
				listData.push({
					y : value.count,
					legendText : name,
					label : name
				})
			});
			// Show do thi
			var chart = new CanvasJS.Chart("chartBriefDepartment", {
				title:{
			        text: "Hồ sơ đã mượn theo phòng ban"    
			      },
			      animationEnabled: true,
			      axisY: {
			        title: "Số lượng hồ sơ"
			      },
			      legend: {
			        verticalAlign: "bottom",
			        horizontalAlign: "center"
			      },
			      theme: "theme2",
				data : [ {
					type: "column",  
			        showInLegend: true, 
			        legendMarkerColor: "grey",
			        legendText: "HS = hồ sơ",
					dataPoints : listData
				} ]
			});
			chart.render();

		}
	});
}