$(document).ready(function() {

$("#log").click(
	function(event) {
			event.preventDefault();
			var d = JSON.stringify({
				email : $("#email").val(),
				password : $("#password").val()
			})

			$.post({
				url : "http://localhost:8080/api/login",
				data : d,
				contentType : "application/json",
				success : function(data) {
					if (data == "") {
						$('#message').html('Wrong username/password').css(
										'color',
										'red');
						return;
					} else {
						$('#message').html('');
					}
					localStorage.setItem('user',JSON.stringify(data));
				
					alert(data)
					window.location.replace("http://localhost:8080/userProfile.html");
				}
			});
		});



	
});