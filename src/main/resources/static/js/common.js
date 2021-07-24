$(document).ready(function() {
	$('#loading').fadeOut(1000);
});

Vue.prototype.$text = function(propertyName) {
	alert(propertyName);
}

Vue.prototype.$formatDate = function(value) {
	return moment(value).format('MM/DD/YYYY');
}

Vue.prototype.$displayValue = function(value) {
	if (value == null || value == '') {
		return '-';
	} else {
		return value;
	}
}

Vue.prototype.$displayCurrency = function(value) {
	if (value == null || value == '') {
		return 'Chưa công bố';
	} else {
		return value.toLocaleString() + ' VNĐ';
	}
}

String.prototype.format = function() {
	var formatted = this;
	for ( var i = 0; i < arguments.length; i++) {
		var regexp = new RegExp('\\{' + i + '\\}', 'gi');
		formatted = formatted.replace(regexp, arguments[i]);
	}
	return formatted;
};

Vue.prototype.$messageError = function(object) {
	if (object.required != null && object.required == false) {
		return "Vui lòng nhập vào trường này!";
	} else if (object.maxLength != null && object.maxLength == false) {
		return "Độ dài không vượt quá {0} ký tự!"
				.format(object.$params.maxLength.max);
	} else if (object.email != null && object.email == false) {
		return "Định dạng email không đúng!";
	}
}

Vue.prototype.$hasError = function(object) {
	if (object.$error) {
		return 'is-invalid';
	}
	return '';
}
