function stopLoading() {
	$("#loading").addClass('display-none');
}

function startLoading() {
	$("#loading").removeClass('display-none');
}

function loadingInitPage() {
	startLoading();
	setTimeout(stopLoading, 500);
}

$(document).ready(function() {
	loadingInitPage();
	var domain = window.location.href;
	if (domain.includes('/dashboard')) {
		$('#item-dashboard').addClass('active');
	}
	if (domain.includes('/user-manage')) {
		$('#item-user-manage').addClass('active');
	}
	if (domain.includes('/customer-manage')) {
		$('#item-customer-manage').addClass('active');
	}
	if (domain.includes('/salary')) {
		$('#item-salary').addClass('active');
	}
});