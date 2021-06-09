$("#register-form").validate({
	rules: {
		firstName: {
			required: true
		},
		lastName: {
			required: true
		},
		email: {
			required: true,
			email: true
		},
		phoneNumber: {
			required: true,
			number: true,
			rangelength: [10, 10]
		},
		birthday: {
			required: true
		},
		userName: {
			required: true
		},
		password: {
			required: true,
			minlength: 8
		},
		confirmPassword: {
			equalTo: "#password"
		}
	},
	messages: {
		firstName: {
			required: "Vui lòng nhập tên!"
		},
		lastName: {
			required: "Vui lòng nhập họ!"
		},
		email: {
			required: "Vui lòng nhập Email!",
			email: "Email chưa đúng định dạng, vui lòng kiểm tra lại!"
		},
		phoneNumber: {
			required: "Vui lòng nhập số điện thoại!",
			rangelength: "Số điện thoại phải đủ 10 số, vui lòng kiểm tra lại!",
			number: "Số điện thoại phải là số, vui lòng kiểm tra lại!"
		},
		birthday: {
			required: "Vui lòng chọn ngày sinh!"
		},
		userName: {
			required: "Vui lòng điền tên đăng nhập!"
		},
		password: {
			required: "Vui lòng nhập mật khẩu!",
			minlength: "Mật khẩu phải trên 8 kí tự, vui lòng kiểm tra lại!"
		},
		confirmPassword: {
			equalTo: "Mật khẩu không khớp, vui lòng kiểm tra lại!"
		}
	},
    errorClass:'text-danger',
    highlight: function (element) {
        $(element).addClass('is-invalid');
    },
    unhighlight: function (element) {
        $(element).removeClass('is-invalid');
    },
    errorPlacement: function (error, element) {
        switch (element.attr("name")) {
        case 'birthday':
            error.insertAfter($("#birthday-place"));
            break;
        default:
            error.insertAfter(element);
        }
    },
	submitHandler: function(form) {
		form.submit();
	}
});