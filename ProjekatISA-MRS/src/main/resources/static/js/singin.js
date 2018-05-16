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
				
					
					window.location.replace("http://localhost:8080/userProfile.html");
				}
			});
		});

$("#signout").on('click',function()
		{
		localStorage.removeItem('user');
		window.location.replace("https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8080/signin.html");
});

	
});