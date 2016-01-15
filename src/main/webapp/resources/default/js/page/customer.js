$(function() {
	displayTable();
	$(".dateInput").datepicker();
	
	$.validator.addMethod("regex", function(value, element, regexpr) {          
	     return regexpr.test(value);
	   }, "Mã chưa đúng định dạng");    
	
	
	$("#newItemForm").validate({
		rules : {
			code : {
				required : true,
				minlength : 14,
				maxlength : 14,
				regex : /^PE[0-9]{12}$/
			},
			customerName:{
				required:true
			},
			customerBirthDay:{
				required:true
			},
			customerEmail:{
				email : true
			},
			customerAddress:{
				required:true
			},
			customerPhone:{
				required:true
			}
		},
		messages : {
			code : {
				required : "Mã không được để trống"
			},
			customerName:{
				required:"Tên không được để trống"
			},
			customerBirthDay:{
				required:"Ngày sinh không được để trống"
			},
			customerEmail : {
				email:"Không đúng định dạng thư điện tử"
			},
			customerAddress : {
				required:"Địa chỉ không được để trống",
			},
			customerPhone : {
				required:"Điện thoại không được để trống"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
	
	$("#updateItemForm").validate({
		rules : {
			code : {
				required : true,
				minlength : 14,
				maxlength : 14,
				regex : /^PE[0-9]{12}$/
			},
			customerName:{
				required:true
			},
			customerBirthDay:{
				required:true
			},
			customerEmail:{
				email : true
			},
			customerAddress:{
				required:true
			},
			customerPhone:{
				required:true
			}
		},
		messages : {
			code : {
				required : "Mã không được để trống"
			},
			customerName:{
				required:"Tên không được để trống"
			},
			customerBirthDay:{
				required:"Ngày sinh không được để trống"
			},
			customerEmail : {
				email:"Không đúng định dạng thư điện tử"
			},
			customerAddress : {
				required:"Địa chỉ không được để trống",
			},
			customerPhone : {
				required:"Điện thoại không được để trống"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
});

function displayTable() {
	var dataDepartments = [];
	$.ajax({
		url : "/suply-manager/customer/getAll",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataDepartments.push([
						i,value.code,
						value.name,value.birthDate,value.email,value.address,value.phone,
						"<button class='btn btn-sm btn-primary' onclick=\"editItem('"
								+ value.code + "')\" >Sửa</button>",
						"<button class='btn btn-sm btn-danger' onclick=\"deleteItem('"
								+ value.code + "')\">Xoá</button>" ]);
			});
			$('#tblDepartment').dataTable({
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
					"sTitle" : "Mã"
				},{
					"sTitle" : "Tên"
				}, {
					"sTitle" : "Ngày sinh"
				}, {
					"sTitle" : "Email"
				}, {
					"sTitle" : "Địa chỉ"
				}, {
					"sTitle" : "ĐT"
				}, {
					"sTitle" : "Sửa"
				}, {
					"sTitle" : "Xoá"
				} ]
			});
		}
	});
}

function editItem(code) {
	$.ajax({
		url : "/suply-manager/customer/get",
		type : "GET",
		data : {
			code : code
		},
		dataType : "JSON",
		success : function(response) {
			$("#updateItemForm .oldCode").val(response.code);
			$("#updateItemForm .code").val(response.code);
			$("#updateItemForm .customerName").val(response.name);
			$("#updateItemForm .customerBirthDay").val(response.birthDate);
			$("#updateItemForm .customerEmail").val(response.email);
			$("#updateItemForm .customerAddress").val(response.address);
			$("#updateItemForm .customerPhone").val(response.phone);
			$("#updateItem").modal("show");
		}
	});
}

function deleteItem(code) {
	if (confirm("Are you sure you want to proceed?") == true) {
		$.ajax({
			url : "/suply-manager/customer/delete",
			type : "POST",
			data : {
				code : code
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
			}
		});
	}
}

function editedItem() {
	if($("#updateItemForm").valid()){
		var oldCode = $("#updateItemForm .oldCode").val();
		var code = $("#updateItemForm .code").val();
		var customerName = $("#updateItemForm .customerName").val();
		var customerBirthDay = $("#updateItemForm .customerBirthDay").val();
		var customerEmail = $("#updateItemForm .customerEmail").val();
		var customerAddress = $("#updateItemForm .customerAddress").val();
		var customerPhone = $("#updateItemForm .customerPhone").val();
		$.ajax({
			url : "/suply-manager/customer/update",
			type : "POST",
			data : {
				code : code,
				oldCode : oldCode,
				customerName : customerName,
				customerBirthDay : customerBirthDay,
				customerEmail : customerEmail,
				customerAddress : customerAddress,
				customerPhone : customerPhone
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
				$("#updateItemForm .code").val(" ");
				$("#updateItemForm .customerName").val(" ");
				$("#updateItemForm .customerBirthDay").val(" ");
				$("#updateItemForm .customerEmail").val(" ");
				$("#updateItemForm .customerAddress").val(" ");
				$("#updateItemForm .customerPhone").val(" ");
				$("#updateItem").modal("hide");
			}
		});
	}
}

function insertItem() {
	
	if($("#newItemForm").valid()){
		var customerCode = $("#customerCode").val();
		var customerName = $("#customerName").val();
		var customerBirthDay = $("#customerBirthDay").val();
		var customerEmail = $("#customerEmail").val();
		var customerAddress = $("#customerAddress").val();
		var customerPhone = $("#customerPhone").val();
		$.ajax({
			url : "/suply-manager/customer/new",
			type : "POST",
			data : {
				code : customerCode,
				customerName : customerName,
				customerBirthDay : customerBirthDay,
				customerEmail : customerEmail,
				customerAddress : customerAddress,
				customerPhone : customerPhone
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
				$("#newItem").modal("hide");
				$("#customerName").val("");
				$("#customerBirthDay").val("");
				$("#customerEmail").val("");
				$("#customerAddress").val("");
				$("#customerPhone").val("");
			}
		});
	}
}
