$(function() {
	displayTable();
});

function searchData() {
	var searchText = $('#searchName').val();
	var dataDepartments = [];
	$.ajax({
		url : "/suply-manager/brief/searchName",
		type : "GET",
		dataType : "JSON",
		data : {
			searchName : searchText
		},
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataDepartments.push([
						i,
						value.customer.code,
						value.content,
						value.customer.name,
						value.department.name,
						moment(value.createDate),
						value.stock.name,
						value.briefType.name,
						"<button class='btn btn-sm btn-primary' onclick='detail("
								+ value.id + ")'>Chi tiết</button>" ]);
			});
			$('#tblBrief').dataTable({
				"bDestroy" : true,
				"bSort" : true,
				"bFilter" : true,
				"bLengthChange" : true,
				"bPaginate" : true,
				"sDom" : '<"top">rt<"bottom"flp><"clear">',
				"aaData" : dataDepartments,
				"aaSorting" : [],
				"aoColumns" : [ {
					"sTitle" : "STT"
				},{
					"sTitle" : "Mã khách hàng"
				}, {
					"sTitle" : "Nội dung"
				}, {
					"sTitle" : "Khách hàng"
				}, {
					"sTitle" : "Phòng ban"
				}, {
					"sTitle" : "Ngày lập hồ sơ"
				}, {
					"sTitle" : "Kho"
				}, {
					"sTitle" : "Loại Hồ Sơ"
				}, {
					"sTitle" : "Chi tiết hồ sơ"
				} ]
			});
		}
	});
}

function displayTable() {
	var dataDepartments = [];
	$.ajax({
		url : "/suply-manager/brief/getAll",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataDepartments.push([
						i,
						value.customer.code,
						value.content,
						value.customer.name,
						value.department.name,
						moment(value.createDate),
						value.stock.name,
						value.briefType.name,
						"<button class='btn btn-sm btn-primary' onclick='detail("
								+ value.id + ")'>Chi tiết</button>" ]);
			});
			$('#tblBrief').dataTable({
				"bDestroy" : true,
				"bSort" : true,
				"bFilter" : true,
				"bLengthChange" : true,
				"bPaginate" : true,
				"sDom" : '<"top">rt<"bottom"flp><"clear">',
				"aaData" : dataDepartments,
				"aaSorting" : [],
				"aoColumns" : [ {
					"sTitle" : "STT"
				},{
					"sTitle" : "Mã khách hàng"
				}, {
					"sTitle" : "Nội dung"
				}, {
					"sTitle" : "Khách hàng"
				}, {
					"sTitle" : "Phòng ban"
				}, {
					"sTitle" : "Ngày lập hồ sơ"
				}, {
					"sTitle" : "Kho"
				}, {
					"sTitle" : "Loại Hồ Sơ"
				}, {
					"sTitle" : "Chi tiết hồ sơ"
				} ]
			});
		}
	});
}

function detail(id) {
	$("#detailBrief").modal("show");
	$.ajax({
		url : "/suply-manager/showDetailBrief",
		type : "GET",
		dataType : "JSON",
		data : {
			briefId : id
		},
		success : function(response) {
			var i = 0;
			var listImage = response.listImage;
			$("#detailBrief .content").html("Nội dung :" + response.content);
			$("#detailBrief .department").html("Nội dung :" + response.department.name);
			$("#detailBrief .customerName").html("Nội dung :" + response.customer.name);
			$("#detailBrief .customerCode").html("Nội dung :" + response.customer.code);
			$("#detailBrief .stock").html("Nội dung :" + response.stock.name);
			$("#detailBrief .image").empty();
		},
		complete : function(){
			$.ajax({
				url : "/suply-manager/showImageBrief",
				type : "GET",
				dataType : "JSON",
				data : {
					briefId : id
				},
				success : function(response) {
					$.each(response,function(key,value){
						$("#detailBrief .image").append("<a href='" + value.absolutelyPath + "' > "+
						"<img style='width:140px;margin-left:2px;margin-bottom:5px;' src='"+ value.absolutelyPath + "'  />" +
						"</a>");
					});
				}
			});
		}
	});
}