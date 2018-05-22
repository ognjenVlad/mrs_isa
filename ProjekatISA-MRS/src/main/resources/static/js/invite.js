$(document).ready(function() {
	var url = window.location.href;
	var reservation = url.split('?')[1];
	
	var base64Url = reservation.split('.')[1];
	var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
	var obj = JSON.parse(window.atob(base64));
	var res = {};
	console.log(obj);
})
