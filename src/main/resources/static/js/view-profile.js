function changeAvatar() {
	document.getElementById("formUploadAvatar").submit();
}
$(document).ready(function() {
	$("#view-profile-form").validate({
		rules: {
			firstName: {
				required: true,
				maxlength: 36
			},
			lastName: {
				required: true,
				maxlength: 36
			},
			middleName: {
				maxlength: 36,
			},
			email: {
				required: true,
				maxlength: 255,
				email: true
			},
			numberPhone: {
				required: true,
				number: true,
				rangelength: [10, 10]
			},
			birthDay: {
				required: true,
				maxlength: 10,
				required: true
			},
			gender: {
				required: true
			},
			description: {
				maxlength: 4000
			}
		},
		messages: {
			firstName: {
				required: "Vui lòng nhập tên!",
				maxlength: "Tên không được nhập quá 36 ký tự!"
			},
			middleName: {
				maxlength: "Tên đệm không được nhập quá 36 ký tự!"
			},
			lastName: {
				required: "Vui lòng nhập họ!",
				maxlength: "Họ không được nhập quá 36 ký tự!"
			},
			email: {
				required: "Vui lòng nhập Email!",
				maxlength: "Email không được nhập quá 255 ký tự!",
				email: "Email chưa đúng định dạng, vui lòng kiểm tra lại!"
			},
			numberPhone: {
				required: "Vui lòng nhập số điện thoại!",
				rangelength: "Số điện thoại phải đủ 10 số, vui lòng kiểm tra lại!",
				number: "Số điện thoại phải là số, vui lòng kiểm tra lại!",
			},
			birthDay: {
				required: "Vui lòng chọn ngày sinh!",
				maxlength: "Ngày sinh phải đúng 10 ký tự!"
			},
			gender: {
				required: "Vui lòng chọn giới tính!"
			},
			description: {
				maxlength: "Mô tả không quá 4000 ký tự!"
			}
		},
	    errorClass:'text-danger',
	    highlight: function (element) {
	        $(element).addClass('is-invalid');
	    },
	    unhighlight: function (element) {
	        $(element).removeClass('is-invalid');
	    },
		submitHandler: function(form) {
			form.submit();
		}
	});
});
