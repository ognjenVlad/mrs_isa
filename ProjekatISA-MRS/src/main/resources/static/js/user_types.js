
function check_user(){
	var user = JSON.parse(localStorage.getItem('user'));
	var html_to_add;
	if (user == null){
		html_to_add = '<li><a href="reg.html"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li><li>'+
		'<a href="signin.html"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>';
	}else{
		html_to_add = '<li><a href="userProfile.html"><span class="glyphicon glyphicon-profile"></span> My profile</a></li>'+
		'<li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>';
		
		if(user.user_type == "ct"){
			
		}
		
	}
	$('#navbarRight').append(html_to_add);
}

