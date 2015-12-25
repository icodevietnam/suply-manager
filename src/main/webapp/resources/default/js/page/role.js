$(function() {
	displayTable();
	$("#newItemForm").validate({
		rules : {
			roleName:{
				required:true
			},
			roleDescription:{
				required:true
			}
		},
		messages : {
			roleName:{
				required:"Tên không được để trống"
			},
			roleDescription:{
				required:"Diễn giải không được để trống"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
	
	$("#updateItemForm").validate({
		rules : {
			roleName:{
				required:true
			},
			roleDescription:{
				required:true
			}
		},
		messages : {
			roleName:{
				required:"Tên không được để trống"
			},
			roleDescription:{
				required:"Diễn giải không được để trống"
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
		url : "/suply-manager/role/getAll",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataDepartments.push([
						i,
						value.name,value.description,
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
					"sTitle" : "Chú Thích"
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
		url : "/suply-manager/role/get",
		type : "GET",
		data : {
			itemId : id
		},
		dataType : "JSON",
		success : function(response) {
			$("#updateItemForm .roleId").val(response.id);
			$("#updateItemForm .roleName").val(response.name);
			$("#updateItemForm .roleDescription").val(response.description);
			$("#updateItem").modal("show");
		}
	});
}

function deleteItem(id) {
	if (confirm("Are you sure you want to proceed?") == true) {
		$.ajax({
			url : "/suply-manager/role/delete",
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
		var roleId = $("#updateItemForm .roleId").val();
		var roleName = $("#updateItemForm .roleName").val();
		var roleDescription = $("#updateItemForm .roleDescription").val();
		$.ajax({
			url : "/suply-manager/role/update",
			type : "POST",
			data : {
				roleId : roleId,
				roleName : roleName,
				roleDescription : roleDescription
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
				$("#updateItemForm .roleId").val(" ");
				$("#updateItemForm .roleName").val(" ");
				$("#updateItemForm .roleDescription").val(" ");
				$("#updateItem").modal("hide");
			}
		});
	}
}

function insertItem() {
	
	if($("#newItemForm").valid()){
		var roleName = $("#roleName").val();
		var roleDescription = $("#roleDescription").val();
		$.ajax({
			url : "/suply-manager/role/new",
			type : "POST",
			data : {
				roleName : roleName,
				roleDescription : roleDescription
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
				$("#newItem").modal("hide");
				$("#roleName").val(" ");
				$("#roleDescription").val(" ");
			}
		});
	}
}
