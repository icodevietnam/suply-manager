$(function() {
	displayTable();
	$('.combobox').selectpicker();
	$("#newItemForm").validate({
		rules : {
			content:{
				required:true
			}
		},
		messages : {
			content:{
				required:"Nội dung không được để trống"
			}
		}
	});
	
	$("#updateItemForm").validate({
		rules : {
			content:{
				required:true
			}
		},
		messages : {
			content:{
				required:"Nội dung không được để trống"
			}
		}
	});
});

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
						i,value.content,value.customer.name,value.department.name,value.stock.name,value.briefType.name,
						"<button class='btn btn-sm btn-primary' onclick='editItem("
								+ value.id + ")' >Sửa</button>",
						"<button class='btn btn-sm btn-danger' onclick='deleteItem("
								+ value.id + ")'>Xoá</button>" ]);
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
					"sTitle" : "Sửa"
				}, {
					"sTitle" : "Xoá"
				} ]
			});
		}
	});
}

function editItem(id) {
	$.ajax({
		url : "/suply-manager/brief/get",
		type : "GET",
		data : {
			itemId : id
		},
		dataType : "JSON",
		success : function(response) {
			$("#updateItemForm .briefId").val(response.id);
			$("#updateItemForm .content").val(response.content);
			$("#updateItemForm .departmentBox").selectpicker('val',""+response.department.id);
			$("#updateItemForm .stockBox").selectpicker('val',""+response.stock.id);
			$("#updateItemForm .briefTypeBox").selectpicker('val',""+response.briefType.id);
			$("#updateItemForm .customerBox").selectpicker('val',""+response.customer.id);
			$("#updateItem").modal("show");
		}
	});
}

function deleteItem(id) {
	if (confirm("Are you sure you want to proceed?") == true) {
		$.ajax({
			url : "/suply-manager/brief/delete",
			type : "POST",
			data : {
				itemId : id
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
		var briefId = $("#updateItemForm .briefId").val();
		var content = $("#updateItemForm .content").val();
		var briefTypeId = $("#updateItemForm .briefTypeBox").val();
		var customerId = $("#updateItemForm .customerBox").val();
		var stockId = $("#updateItemForm .stockBox").val();
		var departmentId = $("#updateItemForm .departmentBox").val();
		$.ajax({
			url : "/suply-manager/brief/update",
			type : "POST",
			data : {
				briefId : briefId,
				content : content,
				briefTypeId : briefTypeId,
				customerId : customerId,
				stockId : stockId,
				departmentId : departmentId
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
			}
		});
	}
	$("#updateItemForm .content").val("");
	$("#updateItem").modal("hide");
}

function insertItem() {
	
	if($("#newItemForm").valid()){
		var content = $("#content").val();
		var briefTypeId = $("#briefTypeBox").val();
		var customerId = $("#customerBox").val();
		var stockId = $("#stockBox").val();
		var departmentId = $("#departmentBox").val();
		$.ajax({
			url : "/suply-manager/brief/new",
			type : "POST",
			data : {
				content : content,
				briefTypeId : briefTypeId,
				customerId : customerId,
				stockId : stockId,
				departmentId : departmentId
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
			}
		});
	}
	$("#newItem").modal("hide");
	$("#content").val(" ");
}
