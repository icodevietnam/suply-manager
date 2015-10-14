$(function() {
	displayTable();
	$(".combobox").selectpicker();
	$("#newItemForm").validate({
		rules : {
			documentTypeName:{
				required:true
			},
		},
		messages : {
			documentTypeName:{
				required:"Name không được để trống"
			},
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
	
	$("#updateItemForm").validate({
		rules : {
			documentTypeName:{
				required:true
			},
			documentTypePosition:{
				required:true
			}
		},
		messages : {
			documentTypeName:{
				required:"Name không được để trống"
			},
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
});

function displayTable() {
	var dataDepartments = [];
	$.ajax({
		url : "/suply-manager/documentType/getAll",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataDepartments.push([
						i,
						value.name,value.department.name,
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
					"sTitle" : "Tên"
				}, {
					"sTitle" : "Phòng"
				}, {
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
		url : "/suply-manager/documentType/get",
		type : "GET",
		data : {
			itemId : id
		},
		dataType : "JSON",
		success : function(response) {
			$("#updateItemForm .documentTypeId").val(response.id);
			$("#updateItemForm .documentTypeName").val(response.name);
			//$("#updateItemForm .documentTypePosition").val(response.position);
			$("#updateItemForm .departmentBox").selectpicker('val',""+response.department.id);
			$("#updateItem").modal("show");
		}
	});
}

function deleteItem(id) {
	if (confirm("Are you sure you want to proceed?") == true) {
		$.ajax({
			url : "/suply-manager/documentType/delete",
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
		var documentTypeId = $("#updateItemForm .documentTypeId").val();
		var documentTypeName = $("#updateItemForm .documentTypeName").val();
		var departmentId = $("#updateItemForm .departmentBox").val();
		$.ajax({
			url : "/suply-manager/documentType/update",
			type : "POST",
			data : {
				documentTypeId : documentTypeId,
				documentTypeName : documentTypeName,
				departmentId : departmentId
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
			}
		});
	}
	$("#updateItemForm .documentTypeId").val(" ");
	$("#updateItemForm .documentTypeName").val(" ");
	$("#updateItem").modal("hide");
}

function insertItem() {
	
	if($("#newItemForm").valid()){
		var documentTypeName = $("#documentTypeName").val();
		var departmentId = $("#departmentBox").val();
		$.ajax({
			url : "/suply-manager/documentType/new",
			type : "POST",
			data : {
				documentTypeName : documentTypeName,
				departmentId : departmentId
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
			}
		});
	}
	$("#newItem").modal("hide");
	$("#documentTypeName").val(" ");
}
