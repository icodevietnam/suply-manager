$(function() {
});

function displayTable() {
	var name = $("#customerName").val() ;
	var email = $("#customerEmail").val() ;
	var dataDepartments = [];
	$.ajax({
		url : "/suply-manager/brief/searchCustomer",
		type : "GET",
		dataType : "JSON",
		data : {
			name : name,
			email : email
		},
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataDepartments.push([
						i,value.name,value.address,value.email,value.birthDate,
						"<button class='btn btn-sm btn-primary' onclick='displayBrief("
								+ value.id + ")' >Xem Hồ Sơ</button>"]);
			});
			$('#tblItems').dataTable({
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
				}, {
					"sTitle" : "Tên"
				}, {
					"sTitle" : "Địa chỉ"
				}, {
					"sTitle" : "Email"
				},{
					"sTitle" : "Ngày sinh"
				},  {
					"sTitle" : "Xem Hồ Sơ"
				}]
			});
		}
	});

}

function displayBrief(cusId){
	var dataDepartments = [];
	$.ajax({
		url : "/suply-manager/brief/searchBrief",
		type : "GET",
		dataType : "JSON",
		data :{
			cusId : cusId
		},
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataDepartments.push([
						i,value.content,value.customer.name,value.department.name,value.stock.name,value.briefType.name,
						"<button class='btn btn-sm btn-primary' onclick='displayDocument("
								+ value.id + ")' >Mở đơn từ</button>" ]);
			});
			$('#tblItems').dataTable({
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
				}, {
					"sTitle" : "Nội dung"
				}, {
					"sTitle" : "Khách hàng"
				},{
					"sTitle" : "Phòng ban"
				}, {
					"sTitle" : "Kho"
				}, {
					"sTitle" : "Loại Hồ Sơ"
				},  {
					"sTitle" : "Đơn Từ"
				}]
			});
		}
	});
}

