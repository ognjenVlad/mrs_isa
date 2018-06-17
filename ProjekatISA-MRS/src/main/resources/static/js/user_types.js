var user;

function log_out(){
	localStorage.removeItem('user');
	location.reload();
}

function check_user(){
	user = JSON.parse(localStorage.getItem('user'));
	var html_to_add = "";
	if (user == null){
		html_to_add = '<li><a href="reg.html"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li><li>'+
		'<a href="signin.html"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>';
	}else{
		html_to_add = '<li><a href="userProfile.html"><span class="glyphicon glyphicon-profile"></span> My profile</a></li>';
		var html_left;
		if(user.user_type == "user"){
			html_left = '<button class="btn btn-link navbar-btn" data-toggle="modal" data-target="#modal_send_ad">Send ad request</button>';
			$("#navbarLeft_fan").append(html_left);
		}else if(user.user_type == "sys"){
			html_left = '<button type="button" class="btn btn-link navbar-btn" data-toggle="modal" data-target="#modal_add_cinema">Add Cinema/Theatre</button>';
			html_left += '<button type="button" class="btn btn-link navbar-btn" data-toggle="modal" data-target="#modal_add_admin">Add admin</button>';
			$("#navbarLeft").append(html_left);
		}else if(user.user_type == "ct"){
			if (window.location.href.substring(0, 36) == "http://localhost:8080/cinema_profile" ){
				var reportButton = '<button type="button" class="btn btn-link navbar-btn" data-toggle="modal" data-target="#modal_report">Business report</button>'
				$("#navbarLeft").append(reportButton);
			}
		}else if(user.user_type =="fan"){
			html_left +='<button class="btn btn-link navbar-btn" data-toggle="modal" data-target="#modal_create_prop">Create prop</button>';
			html_left +='<button class="btn btn-link navbar-btn" data-toggle="modal" data-target="#modal_get_ad" onclick="get_ad_request()">Get pending ad</button>';
			$("#navbarLeft_fan").append(html_left);
		}		
		
		html_to_add += "<li><a href=\"javascript:log_out();\"><span class=\"glyphicon glyphicon-log-out\"></span> Log out</a></li>";
	}
	$('#navbarRight').append(html_to_add);
}

