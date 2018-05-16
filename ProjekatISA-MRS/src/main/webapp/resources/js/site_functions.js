function update_logging(){
	var user = localStorage.getItem('user');
	if(user != null){
		var type = user.getUser_type();
	}else{
		$(".admin_fan").css("display","none");
		$(".admin_sys").css("display","none");
		$(".admin_ct").css("display","none");
		$(".user").css("display","none");
	}
	
	
	if(type == "user"){
		$(".admin_fan").css("display","none");
		$(".admin_sys").css("display","none");
		$(".admin_ct").css("display","none");
		$(".user").css("display","inline");
	}else if(type == "sys"){
		$(".admin_fan").css("display","none");
		$(".admin_ct").css("display","none");
		$(".user").css("display","none");
		$(".admin_sys").css("display","inline");
	}else if(type == "ct"){
		$(".admin_fan").css("display","none");
		$(".admin_sys").css("display","none");
		$(".user").css("display","none");
		$(".admin_ct").css("display","inline");
	}else if(type =="fan"){
		$(".admin_sys").css("display","none");
		$(".admin_ct").css("display","none");
		$(".user").css("display","none");
		$(".admin_fan").css("display","inline");
	}	
};