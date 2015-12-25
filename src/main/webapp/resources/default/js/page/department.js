$(function() {
	displayTable();
	$("#newItemForm").validate({
		rules : {
			departmentName:{
				required:true
			}
		},
		messages : {
			departmentName:{
				required:"Tên không được để trống"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
	
	$("#updateItemForm").validate({
		rules : {
			departmentName:{
				required:true
			}
		},
		messages : {
			departmentName:{
				required:"Tên không được để trống"
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
		url : "/suply-manager/department/getAll",
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
		url : "/suply-manager/department/get",
		type : "GET",
		data : {
			itemId : id
		},
		dataType : "JSON",
		success : function(response) {
			$("#updateItemForm .departmentId").val(response.id);
			$("#updateItemForm .departmentName").val(response.name);
			$("#updateItem").modal("show");
		}
	});
}

function deleteItem(id) {
	if (confirm("Are you sure you want to proceed?") == true) {
		$.ajax({
			url : "/suply-manager/department/delete",
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
		var departmentId = $("#updateItemForm .departmentId").val();
		var departmentName = $("#updateItemForm .departmentName").val();
		console.log(departmentId +"-" + departmentName);
		$.ajax({
			url : "/suply-manager/department/update",
			type : "POST",
			data : {
				departmentId : departmentId,
				departmentName : departmentName
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
				$("#updateItem").modal("hide");
				$("#updateItemForm .departmentId").val(" ");
				$("#updateItemForm .departmentName").val(" ");
			}
		});
	}
}

function insertItem() {
	
	if($("#newItemForm").valid()){
		var departmentName = $("#departmentName").val();
		$.ajax({
			url : "/suply-manager/department/new",
			type : "POST",
			data : {
				departmentName : departmentName
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
				$("#newItem").modal("hide");
				$("#departmentName").val(" ");
			}
		});
	}
}
