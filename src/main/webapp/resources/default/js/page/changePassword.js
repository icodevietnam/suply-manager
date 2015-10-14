$(function(){
	
	var form = $("#changePasswordForm");
	form.validate({
		rules:{
			oldpassword:{
				required:true,
				remote:{
					url:'/suply-manager/user/checkPasswordExist',
					type:'GET',
					data:{
						oldpassword:function(){
							return $('#oldpassword').val();
						}
					},
					dataType:"JSON"
				}
			},
			newpassword:{
				required:true
			},
			confirmPassword:{
				required:true,
				equalTo:"#newpassword"
			}
		},
		messages:{
			oldpassword:{
				required:"Mật khẩu cũ không được trống.",
				remote:"Mật khẩu không giống mật khẩu cũ của bạn."
			},
			newpassword:{
				required:"Mật khẩu mới không được trống."
			},
			confirmPassword:{
				required:"Xác nhận mật khẩu không được trống.",
				equalTo:"Xác nhận mật khẩu phải giống với mật khẩu mới."
			}
		}
	});
});

function displayChangeInfo(){
	$("#changeInfo").toggle();
}