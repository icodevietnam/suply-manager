$(function(){
	loadDepartment();
});

function loadDepartment(){
	$.ajax({
		url : "/suply-manager/deparment/getCurrentUserDepartment",
		type:"GET",
		dataType:"JSON",
		success:function(data){
			$("span.departmentCur").html(data.department.name);
			$("#roleName").html(data.role.description);
		}
	});
}