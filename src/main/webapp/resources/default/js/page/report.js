$(function() {
	// Set get date
	$(".dateInput").datepicker('setDate', new Date());

	displayNotePaid();
	chartBrief();
	displayBrief();
	listGraphByStock();
	listBriefByDepartment();
});

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