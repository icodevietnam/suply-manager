$(function(){
	$("#changeInfo").hide();
	$('.combobox').selectpicker();
	$("#birthdate").datepicker();
	
	var form = $("#changeInfoForm");
	form.validate({
		rules:{
			fullname:{
				required:true
			},
			birthdate:{
				required:true
			},
			address:{
				required:true
			},
			phone:{
				required:true
			}
		},
		messages:{
			fullname:{
				required:"Họ và tên không được trống."
			},
			birthdate:{
				required:"Ngày sinh không được trống"
			},
			address:{
				required:"Địa chỉ không được trống"
			},
			phone:{
				required:"Số điện thoại không được trống"
			}
		}
	});
});

function displayChangeInfo(){
	$("#changeInfo").toggle();
}