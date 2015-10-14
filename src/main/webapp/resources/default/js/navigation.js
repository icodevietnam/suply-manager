$(function(){
	loadDepartment();
});

function loadDepartment(){
	$.ajax({
		url : "/suply-manager/deparment/getCurrentUserDepartment",
		type:"GET",
		dataType:"text",
		success:function(data){
			$("span.departmentCur").html(data);
		}
	});
}