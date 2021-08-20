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
		numberPhone: {
			required: true,
			number: true,
			rangelength: [10, 10]
		},
		birthDay: {
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
			equalTo: password
		},
		gender: {
			required: true
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
			email: "Email không đúng định dạng!"
		},
		numberPhone: {
			required: "Vui lòng nhập số điện thoại!",
			rangelength: "Số điện thoại ít nhất 10 ký tự!",
			number: "Số điện thoại phải là số!"
		},
		birthDay: {
			required: "Vui lòng chọn ngày sinh!"
		},
		userName: {
			required: "Vui lòng điền tên đăng nhập!"
		},
		password: {
			required: "Vui lòng nhập mật khẩu!",
			minlength: "Mật khẩu phải trên 8 kí tự!"
		},
		confirmPassword: {
			equalTo: "Mật khẩu không khớp!"
		},
		gender: {
			required: "Vui lòng chọn giới tính!"
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