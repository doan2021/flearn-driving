$(document).ready(function() {
	$('#loading').fadeOut(1000);
});

window.onscroll = function() {
	scrollFunction()
};

function scrollFunction() {
	if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 80) {
		$("#navbar").removeClass("py-3 bd-transparent navbar-dark");
		$("#navbar").addClass("py-2 bg-light navbar-light");
		
	} else {
		$("#navbar").removeClass("py-2 bg-light navbar-light");
		$("#navbar").addClass("py-3 bd-transparent navbar-dark");
	}
}