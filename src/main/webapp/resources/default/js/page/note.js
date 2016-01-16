$(function(){
	//Show None Brief
	displayNoneBrief();
});

function displayNoneBrief(){
	var dataItems = [];
	$.ajax({
		url : "/suply-manager/brief/getAll",
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
		url : "/suply-manager/note/getAll",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				dataItems.push([i,value.department.name,value.borrowDate,value.paidDate]);
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
				"aoColumns" : [{
					"sTitle" : "STT"
				}, {
					"sTitle" : "Phòng ban"
				}, {
					"sTitle" : "Ngày mượn"
				},{
					"sTitle" : "Ngày trả"
				}]
			});
		}
	});
}