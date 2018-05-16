function update_logging(){
	var user = localStorage.getItem('user');
	if(user != null){
		var type = user.getUser_type();
	}else{
		$(".admin_fan").hide();
		$(".admin_sys").hide();
		$(".admin_ct").hide();
		$(".user").hide();
		return;
	}
	
	if(type == "user"){
		$(".admin_fan").hide();
		$(".admin_sys").hide();
		$(".admin_ct").hide();
		$(".user").show();
	}else if(type == "sys"){
		$(".admin_fan").hide();
		$(".admin_ct").hide();
		$(".user").hide();
		$(".admin_sys").show();
	}else if(type == "ct"){
		$(".admin_fan").hide();
		$(".admin_sys").hide();
		$(".user").hide();
		$(".admin_ct").show();
	}else if(type =="fan"){
		$(".admin_sys").hide();
		$(".admin_ct").hide();
		$(".user").hide();
		$(".admin_fan").show();
	}	
};