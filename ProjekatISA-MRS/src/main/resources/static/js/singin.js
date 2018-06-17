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
					if(data.user_type != "user" && data.isActive == "false"){
						$("edit_email").val(data.email);
						$("#modal_change_pw").modal("show");
						return;
					}
					localStorage.setItem('user',JSON.stringify(data));
				
					
					window.location.replace("http://localhost:8080/userProfile.html");
				}
			});
		});
	$('#edit_password, #edit_r-password').on('keyup', function () {
	  if ($('#edit_password').val() == $('#edit_r-password').val()) {
	    $('#edit_message').html('Passwords matching').css('color', 'green');
	  } else 
	    $('#edit_message').html('Passwords do not match').css('color', 'red');
		});
	
	$("#signout").on('click',function()
		{
		localStorage.removeItem('user');
		window.location.replace("https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8080/signin.html");
	});

	
});

function activate_admin(){
	var pass = $("#edit_password").val();
	var r_pass = $("#edit_r-password").val();
	
	if(pass != r_pass){
		return;
	}
	var email = $("#edit_email").val();
	
	$.post({
		url : "http://localhost:8080/api/activate_admin/" + email + "/" + pass,
		data : d,
		contentType : "application/json",
		success : function(data) {
			if(data.message == "Success"){
				localStorage.setItem('user',JSON.stringify(data));
				window.location.replace("http://localhost:8080/pocetna.html");
			}else{
				alert(data.message);
			}
			
		}
	});	
}