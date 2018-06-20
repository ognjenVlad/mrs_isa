var user;

function log_out(){
	localStorage.removeItem('user');
	location.reload();
}

function check_user(){
	user = JSON.parse(localStorage.getItem('user'));
	if (user == null){
		hide_all();
		$(".user_not_logged").show();
	}else{
		hide_all();
		if(user.user_type == "user"){
			$(".user").show();
		}else if(user.user_type == "sys"){
			$(".admin_sys").show();
		}else if(user.user_type == "ct"){
			$(".admin_ct").show();
			if (window.location.href.substring(0, 36) == "http://localhost:8080/cinema_profile" ){
				var reportButton = '<button type="button" class="btn btn-link navbar-btn" data-toggle="modal" data-target="#modal_report">Business report</button>'
				$("#navbarLeft").append(reportButton);
			}
		}else if(user.user_type =="fan"){
			$(".admin_fan").show();
		}
	}
}

function hide_all(){
	$(".admin_fan").hide();
	$(".admin_sys").hide();
	$(".admin_ct").hide();
	$(".user").hide();
	$(".user_not_logged").hide();
	
}

