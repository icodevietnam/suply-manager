$(function() {
	displayTable();
	$("#newItemForm").validate({
		rules : {
			briefTypeName:{
				required:true
			}
		},
		messages : {
			briefTypeName:{
				required:"Name không được để trống"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
	
	$("#updateItemForm").validate({
		rules : {
			briefTypeName:{
				required:true
			}
		},
		messages : {
			briefTypeName:{
				required:"Name không được để trống"
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
		url : "/suply-manager/briefType/getAll",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataDepartments.push([
						i,
						value.name,
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
				},{
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
		url : "/suply-manager/briefType/get",
		type : "GET",
		data : {
			itemId : id
		},
		dataType : "JSON",
		success : function(response) {
			$("#updateItemForm .briefTypeId").val(response.id);
			$("#updateItemForm .briefTypeName").val(response.name);
			$("#updateItem").modal("show");
		}
	});
}

function deleteItem(id) {
	if (confirm("Are you sure you want to proceed?") == true) {
		$.ajax({
			url : "/suply-manager/briefType/delete",
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
		var briefTypeId = $("#updateItemForm .briefTypeId").val();
		var briefTypeName = $("#updateItemForm .briefTypeName").val();
		$.ajax({
			url : "/suply-manager/briefType/update",
			type : "POST",
			data : {
				briefTypeId : briefTypeId,
				briefTypeName : briefTypeName,
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
			}
		});
	}
	$("#updateItemForm .briefTypeId").val(" ");
	$("#updateItemForm .briefTypeName").val(" ");
	$("#updateItem").modal("hide");
}

function insertItem() {
	
	if($("#newItemForm").valid()){
		var briefTypeName = $("#briefTypeName").val();
		$.ajax({
			url : "/suply-manager/briefType/new",
			type : "POST",
			data : {
				briefTypeName : briefTypeName,
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
			}
		});
	}
	$("#newItem").modal("hide");
	$("#briefTypeName").val(" ");
}
