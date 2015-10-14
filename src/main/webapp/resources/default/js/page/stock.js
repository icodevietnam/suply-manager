$(function() {
	displayTable();
	$("#newItemForm").validate({
		rules : {
			stockName:{
				required:true
			},
			stockPosition:{
				required:true
			}
		},
		messages : {
			stockName:{
				required:"Name không được để trống"
			},
			stockPosition:{
				required:"Diễn giải không được để trống"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
	
	$("#updateItemForm").validate({
		rules : {
			stockName:{
				required:true
			},
			stockPosition:{
				required:true
			}
		},
		messages : {
			stockName:{
				required:"Name không được để trống"
			},
			stockPosition:{
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
		url : "/suply-manager/stock/getAll",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataDepartments.push([
						i,
						value.name,value.position,
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
					"sTitle" : "Vị trí"
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
		url : "/suply-manager/stock/get",
		type : "GET",
		data : {
			itemId : id
		},
		dataType : "JSON",
		success : function(response) {
			$("#updateItemForm .stockId").val(response.id);
			$("#updateItemForm .stockName").val(response.name);
			$("#updateItemForm .stockPosition").val(response.position);
			$("#updateItem").modal("show");
		}
	});
}

function deleteItem(id) {
	if (confirm("Are you sure you want to proceed?") == true) {
		$.ajax({
			url : "/suply-manager/stock/delete",
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
		var stockId = $("#updateItemForm .stockId").val();
		var stockName = $("#updateItemForm .stockName").val();
		var stockPosition = $("#updateItemForm .stockPosition").val();
		$.ajax({
			url : "/suply-manager/stock/update",
			type : "POST",
			data : {
				stockId : stockId,
				stockName : stockName,
				stockPosition : stockPosition
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
			}
		});
	}
	$("#updateItemForm .stockId").val(" ");
	$("#updateItemForm .stockName").val(" ");
	$("#updateItemForm .stockPosition").val(" ");
	$("#updateItem").modal("hide");
}

function insertItem() {
	
	if($("#newItemForm").valid()){
		var stockName = $("#stockName").val();
		var stockPosition = $("#stockPosition").val();
		$.ajax({
			url : "/suply-manager/stock/new",
			type : "POST",
			data : {
				stockName : stockName,
				stockPosition : stockPosition
			},
			dataType : "JSON",
			success : function(response) {
				displayTable();
			}
		});
	}
	$("#newItem").modal("hide");
	$("#stockName").val(" ");
	$("#stockPosition").val(" ");
}
