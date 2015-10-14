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
		},
		submitHandler : function(form) {
			form.submit();
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
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
});

function showImage(id){
	var link ="";
	$.ajax({
		url : "/suply-manager/fileAttached/get",
		type : "GET",
		data : {
			itemId : id
		},
		async :false,
		cache : false,
		dataType : "JSON",
		success : function(response) {
			link = "<img class='thumbnail' src='/resources/default/images/"+ response.name+ "'/>"
		}
	});
	return link;
}

function displayTable() {
	var dataDepartments = [];
	$.ajax({
		url : "/suply-manager/document/getAll",
		type : "GET",
		dataType : "JSON",
		async :false,
		cache : false,
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataDepartments.push([
						i,
						value.content,value.brief.content,value.documentType.name,showImage(value.id),
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
					"sTitle" : "Hồ Sơ"
				}, {
					"sTitle" : "Loại Đơn Từ"
				}, {
					"sTitle" : "Hình ảnh"
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
		url : "/suply-manager/document/get",
		type : "GET",
		data : {
			itemId : id
		},
		dataType : "JSON",
		success : function(response) {
			$("#updateItemForm .documentId").val(response.id);
			$("#updateItemForm .content").val(response.content);
			$("#updateItemForm .briefBox").selectpicker('val',""+response.brief.id);
			$("#updateItemForm .documentTypeBox").val("val",""+response.documentType.id);
			$("#updateItem").modal("show");
		}
	});
}

function deleteItem(id) {
	if (confirm("Are you sure you want to proceed?") == true) {
		$.ajax({
			url : "/suply-manager/document/delete",
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
		var formData = new FormData($("#updateItemForm")[0]);
		$.ajax({
			url : "/suply-manager/document/update",
			type : "POST",
			data :formData ,
			contentType:false,
			processData:false,
			dataType : "JSON",
			success : function(response) {
			},
			complete:function(){
				displayTable();
			}
		});
	}
	$("#updateItemForm .content").val("");
	$("#updateItem").modal("hide");
}

function insertItem() {
	if($("#newItemForm").valid()){
		var formData = new FormData($("#newItemForm")[0]);
		$.ajax({
			url : "/suply-manager/document/new",
			type : "POST",
			data : formData,
			contentType:false,
			processData:false,
			dataType : "JSON",
			success : function(response) {
			},
			complete:function(){
				displayTable();
			}
		});
	}
	$("#content").val(" ");
	$("#newItem").modal("hide");
}
