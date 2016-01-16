$(function() {
});

function displayTable() {
	var code = "PE" + $("#customerCode").val() ;
	var name = $("#customerName").val() ;
	var email = $("#customerEmail").val() ;
	var dataDepartments = [];
	$.ajax({
		url : "/suply-manager/brief/searchCustomer",
		type : "GET",
		dataType : "JSON",
		data : {
			code : code,
			name : name,
			email : email
		},
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataDepartments.push([
						i,value.code,value.name,value.address,value.email,value.birthDate,
						"<a style='color:white;' class='btn btn-sm btn-primary' href='/suply-manager/brief/"+ value.code +"' >Xem Hồ Sơ</a>"]);
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
				},{
					"sTitle" : "Mã"
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

