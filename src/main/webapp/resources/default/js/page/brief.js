$(function() {
	displayTable();
	
	$('.imageDemo1').attr('src',"/suply-manager/resources/default/images/no-image.jpg");
	$('.imageDemo2').attr('src',"/suply-manager/resources/default/images/no-image.jpg");
	$('.imageDemo3').attr('src',"/suply-manager/resources/default/images/no-image.jpg");
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
	
	$("input[name='file1']").change(function(){
		  readUrl1(this);
	  });
	$("input[name='file2']").change(function(){
		  readUrl2(this);
	  });
	$("input[name='file3']").change(function(){
		  readUrl3(this);
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
						"<button class='btn btn-sm btn-primary' onclick='showImage("
						+ value.id + ")' >Xem hình ảnh</button>",
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
					"sTitle" : "Hình ảnh"
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
			$("#updateItemForm .customerBox").selectpicker('val',""+response.customer.code);
			showListfile(id);
			$("#updateItem").modal("show");
		}
	});
}

function showImage(id) {
	$.ajax({
		url : "/suply-manager/brief/get",
		type : "GET",
		data : {
			itemId : id
		},
		dataType : "JSON",
		success : function(response) {
			$("#showImageForm .briefId").val(response.id);
			showImageInForm(id);
			$("#showImage").modal("show");
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
				if(response == false){
					alert("Không thể xóa vì trong hồ sơ có hình ảnh");
				}
			},
			complete:function(){
				displayTable();
			}
		});
	}
}

function editedItem() {
	if($("#updateItemForm").valid()){
		var formData = new FormData($("#updateItemForm")[0]);
		$.ajax({
			url : "/suply-manager/brief/update",
			type : "POST",
			data : formData,
			contentType:false,
			processData:false,
			dataType : "JSON",
			success : function(response) {
			},
			complete:function(){
				displayTable();
				$("#newItem").modal("hide");
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
			url : "/suply-manager/brief/new",
			type : "POST",
			data : formData,
			dataType : "JSON",
			contentType:false,
			processData:false,
			success : function(response) {
			},
			complete:function(){
				displayTable();
				$("#newItem").modal("hide");
			}
		});
	}
	$("#content").val(" ");
}

function showListfile(briefId) {
	$.ajax({
		url : "/suply-manager/file/getBrief",
		type : "POST",
		data : {
			briefId : briefId
		},
		dataType : "JSON",
		success : function(response) {
			$('.showFile').empty();
			$.each(response,function(key,value){
				$('.showFile').append("<div class='col-lg-3'>" +
						"<a href='" + value.absolutelyPath + "' >" +
						"<img style='width:140px;margin-left:2px;margin-bottom:5px;' src='" + value.absolutelyPath + "' /> </a>" +
						"<button type='button' class='btn btn-danger' onclick ='deleteImage("+value.id+","+briefId+")' > Xoá </button>" +
				"</div>");
			});
		},
	});
}

function showImageInForm(briefId) {
	$.ajax({
		url : "/suply-manager/file/getBrief",
		type : "POST",
		data : {
			briefId : briefId
		},
		dataType : "JSON",
		success : function(response) {
			$('.showFile').empty();
			$.each(response,function(key,value){
				$('.showFile').append("<div class='col-lg-3'>" +
						"<a href='" + value.absolutelyPath + "' >" +
						"<img style='width:140px;margin-left:2px;margin-bottom:5px;' src='" + value.absolutelyPath + "' /> </a>" +
				"</div>");
			});
		},
	});
}

function deleteImage(imageId,briefId){
	if (confirm("Are you sure you want to proceed?") == true) {
		$.ajax({
			url : "/suply-manager/file/delete",
			type : "POST",
			data : {
				fileId : imageId
			},
			dataType : "imageId",
			success : function(response) {
			},
			complete:function(){
				showListfile(briefId);
			}
		});
	}
}

function readUrl1(input){
	 if (input.files && input.files[0]) {
        var reader = new FileReader();
        
        reader.onload = function (e) {
            $('.imageDemo1').attr('src', e.target.result);
        }
        
        reader.readAsDataURL(input.files[0]);
    }
}

function readUrl2(input){
	 if (input.files && input.files[0]) {
       var reader = new FileReader();
       
       reader.onload = function (e) {
           $('.imageDemo2').attr('src', e.target.result);
       }
       
       reader.readAsDataURL(input.files[0]);
   }
}

function readUrl3(input){
	 if (input.files && input.files[0]) {
       var reader = new FileReader();
       
       reader.onload = function (e) {
           $('.imageDemo3').attr('src', e.target.result);
       }
       
       reader.readAsDataURL(input.files[0]);
   }
}