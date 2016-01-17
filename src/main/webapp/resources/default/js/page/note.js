$(function(){
	//Show None Brief
	displayNoneBrief();
	displayNote();
	displayNotePaid();
});

function checkBoxLength(){
	if($(':checkbox:checked').length != 0){
		$("#createNote").modal("show");
	}else{
		alertify.error('Bạn chưa chọn hồ sơ');
	}
}

function displayNoneBrief(){
	var dataItems = [];
	$.ajax({
		url : "/suply-manager/brief/searchNotBorrow",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataItems.push([
						"<input name='selector[]' id='ad_Checkbox"+ value.id +"' class='ic_checkbox' type='checkbox' value='"+value.id+"' />",i,value.content,value.customer.name,value.department.name,value.stock.name,value.briefType.name]);
			});
			$('#tableBrief').dataTable({
				"bDestroy" : true,
				"bSort" : true,
				"bFilter" : true,
				"bLengthChange" : true,
				"bPaginate" : true,
				"sDom" : '<"top">rt<"bottom"flp><"clear">',
				"aaData" : dataItems,
				"aaSorting" : [],
				"aoColumns" : [ {
					"sTitle" : "Lựa"
				},{
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
				}]
			});
		}
	});
}

function displayNote(){
	var dataItems = [];
	$.ajax({
		url : "/suply-manager/note/getNoteNoPaid",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataItems.push([i,value.code,value.department.name,moment(value.borrowDate).format("Do MMMM YYYY ,h:mm:ss A"),"<button class='btn btn-sm btn-primary' onclick=\"paidBrief('"
								+ value.code + "')\" >Trả hồ sơ</button>"]);
			});
			$('#tableNote').dataTable({
				"bDestroy" : true,
				"bSort" : true,
				"bFilter" : true,
				"bLengthChange" : true,
				"bPaginate" : true,
				"sDom" : '<"top">rt<"bottom"flp><"clear">',
				"aaData" : dataItems,
				"aaSorting" : [],
				"aoColumns" : [{
					"sTitle" : "STT"
				},{
					"sTitle" : "Mã phiếu"
				}, {
					"sTitle" : "Phòng ban"
				}, {
					"sTitle" : "Ngày mượn"
				},{
					"sTitle" : "Trả"
				}]
			});
		}
	});
}

function paidBrief(noteId){
	var dataItems = [];
	$.ajax({
		url : "/suply-manager/note/paidBrief",
		type : "POST",
		dataType : "NOTE",
		data : {
			noteId : noteId
		},
		success : function(response) {
			
		},
		complete : function(){
			displayNoneBrief();
			displayNote();
			displayNotePaid();
		}
	});
}

function displayNotePaid(){
	var dataItems = [];
	$.ajax({
		url : "/suply-manager/note/getNotePaid",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataItems.push([i,value.code,value.department.name,moment(value.borrowDate).format("Do MMMM YYYY ,h:mm:ss A"),moment(value.paidDate).format("Do MMMM YYYY ,h:mm:ss A")]);
			});
			$('#tablePaidNote').dataTable({
				"bDestroy" : true,
				"bSort" : true,
				"bFilter" : true,
				"bLengthChange" : true,
				"bPaginate" : true,
				"sDom" : '<"top">rt<"bottom"flp><"clear">',
				"aaData" : dataItems,
				"aaSorting" : [],
				"aoColumns" : [{
					"sTitle" : "STT"
				},{
					"sTitle" : "Mã phiếu"
				}, {
					"sTitle" : "Phòng ban"
				}, {
					"sTitle" : "Ngày mượn"
				},{
					"sTitle" : "Ngày Trả"
				}]
			});
		}
	});
}

function borrowBrief(){
	//alertify.success('Đã thêm hồ sơ');
	var department = $("#departmentBox").val();
	$.ajax({
		url : "/suply-manager/note/saveNote",
		type : "POST",
		dataType : "JSON",
		data : {
			departmentBox : department
		},
		success : function(response) {
			$(':checkbox:checked').each(function(i){
		        $.ajax({
		        	url : "/suply-manager/brief/updateNote",
		    		type : "POST",
		    		dataType : "JSON",
		    		data : {
		    			briefId : $(this).val(),
		    			noteId : response.code
		    		},
		    		success : function(data) {
		    			$("#createNote").modal("hide");
		    			displayNoneBrief();
		    			displayNote();
		    			displayNotePaid();
		    		},
		    		error: function (xhr, ajaxOptions, thrownError) {
		    	        console.log(xhr.status);
		    	        console.log(thrownError);
		    	    }
		        });
		    });
		}
	});
}

