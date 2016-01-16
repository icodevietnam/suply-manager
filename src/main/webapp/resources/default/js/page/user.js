$(function() {
	$('.combobox').selectpicker();
	$(".dateInput").datepicker();
	//
	displayTable();
	$("#newItemForm").validate({
		rules : {
			userName:{
				required:true,
				remote : {
					url : "/suply-manager/user/check",
					type : "GET",
					data: {
				          check: function() {
				            return $( "#newItemForm input[name='userName']" ).val();
				          }
				    }
				}
			},
			password:{
				required:true,
				minlength:5
			},
			confirmPassword:{
				required:true,
				equalTo:"#password"
			},fullname:{
				required:true
			},confirmPassword:{
				required:true
			},birthDate:{
				required:true
			},
			address:{
				required:true
			},
			phone:{
				required:true
			}
		},
		messages : {
			userName:{
				required:"Tên đăng nhập không được để trống",
				remote : "Tên đăng nhập đã tồn tại"
			},
			password:{
				required:"Mật khẩu không được để trống",
				minlength:"Mật khẩu không được bé hơn 5 ký tự"
			},
			confirmPassword:{
				required:"Xác nhận mật khẩu không được để trống",
				equalTo:"Xác nhận mật khẩu và mật khẩu không giống nhau"
			},fullname:{
				required:"Họ và tên không được để trống"
			},confirmPassword:{
				required:"Xác nhận mật khẩu không được để trống"
			},birthDate:{
				required:"Ngày sinh không được để trống"
			},
			address:{
				required:"Địa chỉ không được để trống"
			},
			phone:{
				required:"Điện Thoại không được để trống"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
	
	$("#changeModalForm").validate({
		rules : {
			password:{
				required:true,
				minlength:5
			},
			confirmPassword:{
				required:true,
				equalTo:"#changeModalForm .password"
			}
		},
		messages : {
			password:{
				required:"Mật khẩu không được để trống",
				minlength:"Mật khẩu không được bé hơn 5 ký tự"
			},
			confirmPassword:{
				required:"Xác nhận mật khẩu không được để trống",
				equalTo:"Xác nhận mật khẩu và mật khẩu không giống nhau"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
	
	$("#updateItemForm").validate({
		rules : {
			userName:{
				required:true,
				remote : {
					url : "/suply-manager/user/check",
					type : "GET",
					data: {
				          check: function() {
				            return $( "#updateItemForm input[name='userName']" ).val();
				          }
				    }
				}
			},fullname:{
				required:true
			},birthDate:{
				required:true
			},
			address:{
				required:true
			},
			phone:{
				required:true
			}
		},
		messages : {
			userName:{
				required:"Tên đăng nhập không được để trống",
				remote : "Tên đăng nhập đã tồn tại"
			},fullname:{
				required:"Họ và tên không được để trống"
			},birthDate:{
				required:"Ngày sinh không được để trống"
			},
			address:{
				required:"Địa chỉ không được để trống"
			},
			phone:{
				required:"Điện Thoại không được để trống"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
});


function insertItem() {
	
	if($("#newItemForm").valid()){
		var userName = $("#userName").val();
		var password = $("#password").val();
		var fullname = $("#fullname").val();
		var birthDate = $("#birthDate").val();
		var address = $("#address").val();
		var roleId = $("#roleBox").val();
		var departmentId = $("#departmentBox").val();
		var gender = $("#genderBox").val();
		var state = $("#stateBox").val();
		var phone = $("#phone").val();
		$.ajax({
			url : "/suply-manager/user/new",
			type : "POST",
			data : {
				userName : userName,
				password : password,
				fullname : fullname,
				birthDate : birthDate,
				address : address,
				roleId : roleId,
				departmentId : departmentId,
				gender : gender, 
				state : state,
				phone : phone
			},
			dataType : "JSON",
			success : function(response) {
				if(response ==="false"){
					alertify.error('Không thể thêm người dùng');
				}else{
					alertify.success('Đã thêm người dùng');
				}
				$("#newItem").modal("hide");
				$("#userName").val("");
				$("#fullname").val("");
				$("#birthDate").val("");
				$("#address").val("");
				$("#phone").val("");
				displayTable();
			}
		});
	}
}

function displayTable() {
	var dataUsers = [];
	$.ajax({
		url : "/suply-manager/user/getAll",
		type : "GET",
		dataType : "JSON",
		success : function(response) {
			var i = 0;
			$.each(response, function(key, value) {
				i++;
				var state = "";
				if(value.state == "active"){
					state ="Đang hoạt động";
				}else if(value.state == "absent"){
					state = "Đang nghỉ phép";
				}else{
					state = "Từ chức";
				}
				dataUsers.push([
						i,
						value.username,value.fullName,value.birthDate,value.address,state,value.role.description,value.department.name,
						"<button class='btn btn-sm btn-primary' onclick='changePass("
						+ value.id + ")' >Đổi Mật Khẩu</button>",
						"<button class='btn btn-sm btn-primary' onclick='editItem("
								+ value.id + ")' >Đổi thông tin</button>",
						"<button class='btn btn-sm btn-danger' onclick='deleteItem("
								+ value.id + ")'>Xoá</button>" ]);
			});
			$('#tblUser').dataTable({
				"bDestroy" : true,
				"bSort" : true,
				"bFilter" : true,
				"bLengthChange" : true,
				"bPaginate" : true,
				"sDom" : '<"top">rt<"bottom"flp><"clear">',
				"aaData" : dataUsers,
				"aaSorting" : [],
				"aoColumns" : [ {
					"sTitle" : "STT"
				}, {
					"sTitle" : "Tên Đăng Nhập"
				}, {
					"sTitle" : "Họ Tên"
				},  {
					"sTitle" : "Ngày sinh"
				},
				{
					"sTitle" : "Địa chỉ"
				},{
					"sTitle" : "Trạng Thái"
				},{
					"sTitle" : "Quyền"
				},{
					"sTitle" : "Phòng"
				},
				{
					"sTitle" : "Đổi mật khẩu"
				}, 
				{
					"sTitle" : "Thay đổi"
				}, {
					"sTitle" : "Xoá"
				} ]
			});
		}
	});
}


function editItem(id) {
	$.ajax({
		url : "/suply-manager/user/get",
		type : "GET",
		data : {
			itemId : id
		},
		dataType : "JSON",
		success : function(response) {
			$("#updateItemForm .userId").val(response.id);
			$("#updateItemForm .userName").val(response.username);
			$("#updateItemForm .fullname").val(response.fullName);
			$("#updateItemForm .birthDate").val(response.birthDate);
			$("#updateItemForm .address").val(response.address);
			$("#updateItemForm .phone").val(response.phone);
			$("#updateItemForm .departmentBox").selectpicker('val',""+response.department.id);
			$("#updateItemForm .roleBox").selectpicker('val',""+response.role.id);
			$("#updateItemForm .genderBox").selectpicker('val',""+response.gender);
			$("#updateItemForm .stateBox").selectpicker('val',""+response.state);
			$("#updateItem").modal("show");
		}
	});
}

function deleteItem(id) {
	if (confirm("Are you sure you want to proceed?") == true) {
		$.ajax({
			url : "/suply-manager/user/delete",
			type : "POST",
			data : {
				itemId : id
			},
			dataType : "JSON",
			success : function(response) {
				if(response ==="false"){
					alertify.error('Không thể xóa người dùng');
				}else{
					alertify.success('Đã xóa người dùng');
				}
				displayTable();
			}
		});
	}
}

function editedItem() {
	if($("#updateItemForm").valid()){
		var userId = $("#updateItemForm .userId").val();
		var userName = $("#updateItemForm  .userName").val();
		var fullname = $("#updateItemForm  .fullname").val();
		var birthDate = $("#updateItemForm .birthDate").val();
		var address = $("#updateItemForm  .address").val();
		var roleId = $("#updateItemForm  .roleBox").val();
		var departmentId = $("#updateItemForm .departmentBox").val();
		var gender = $("#updateItemForm .genderBox").val();
		var state = $("#updateItemForm .stateBox").val();
		var phone = $("#updateItemForm .phone").val();
		$.ajax({
			url : "/suply-manager/user/update",
			type : "POST",
			data : {
				userId : userId,
				userName : userName,
				fullname : fullname,
				birthDate : birthDate,
				address : address,
				roleId : roleId,
				departmentId : departmentId,
				gender : gender, 
				state : state,
				phone : phone
			},
			dataType : "JSON",
			success : function(response) {
				if(response ==="false"){
					alertify.error('Không thể sửa thông tin người dùng');
				}else{
					alertify.success('Đã sửa thông tin người dùng');
				}
				$("#userName").val("");
				$("#fullname").val("");
				$("#birthDate").val("");
				$("#address").val("");
				$("#phone").val("");
				$("#updateItem").modal("hide");
				displayTable();
			}
		});
	}
}

function changePass(id){
	$("#changeModal").modal("show");
	$("#changeModalForm .userId").val(id);
}

function changePassProcess(){
	var userId = $("#changeModalForm .userId").val();
	var password = $("#changeModalForm .password").val();
	$.ajax({
		url : "/suply-manager/user/changePassword",
		type : "POST",
		data : {
			userId : userId,
			password : password,
		},
		dataType : "JSON",
		success : function(response) {
			if(response ==="false"){
				alertify.error('Không thể thay đổi mật khẩu người dùng');
			}else{
				alertify.success('Đã thay đổi mật khẩu người dùng');
			}
		}
	});
	$("changeModalForm .password").val(" ");
	$("changeModalForm .confirmPassword").val(" ");
	$("#changeModal").modal("hide");
	
}
