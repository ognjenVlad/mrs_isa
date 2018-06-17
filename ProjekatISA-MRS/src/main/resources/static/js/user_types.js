function log_out(){
	localStorage.removeItem('user');
	location.reload();
}

function check_user(){
	var user = JSON.parse(localStorage.getItem('user'));
	if (user == null){
		$(".admin_fan").hide();
		$(".admin_sys").hide();
		$(".admin_ct").hide();
		$(".user").hide();
		$(".user_not_logged").hide();
	}else{
		if(user.user_type == "user"){
			$(".user").show();
		}else if(user.user_type == "sys"){
			$(".admin_sys").show();
			/*
			html_left = '<button type="button" class="btn btn-link navbar-btn" data-toggle="modal" data-target="#modal_add_cinema">Add Cinema/Theatre</button>';
			html_left += '<button type="button" class="btn btn-link navbar-btn" data-toggle="modal" data-target="#modal_add_admin">Add admin</button>';
			$("#navbarLeft").append(html_left);
			*/
		}else if(user.user_type == "ct"){
			$(".admin_ct").hide();
		}else if(user.user_type =="fan"){
			$(".admin_fan").show();
		}
	}
}

