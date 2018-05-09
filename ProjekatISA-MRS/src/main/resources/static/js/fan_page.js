$(document).ready(function() {
	$("#form_create_ad").submit(function(event){
		if ($('#password').val() != $('#r-password').val()){
			return;
		} 
		event.preventDefault();
		
		date_val = new Date($("#ad_exp_date").val());
		
		var d = JSON.stringify(
		{
			"title":$("#ad_title").val(),
			"description":$("#ad_description").val(),
			"exp_date": date_val,
			"picture":$('#ad_file').val()
		})
		
		console.log(d);
		
		$.post({
			url:"http://localhost:8080/admin_fan/add_ad",
			data: d,
			contentType: "application/json",
            success:function(data){
            	alert("Ad request sended");
				 $('#modal_send_ad').modal('hide')
            }
		});
	});
});