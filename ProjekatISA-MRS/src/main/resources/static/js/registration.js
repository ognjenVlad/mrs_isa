function log_out(){

		localStorage.removeItem('user');
		location.replace("http://localhost:8080/");
}

$(document).ready(function() {
	$("#form").submit(function(event){
		if ($('#password').val() != $('#r-password').val()){
			return;
		} 
		event.preventDefault();
		var d = JSON.stringify({email:$("#email").val(),password:$("#password").val(),name:$("#name").val(),surname:$("#surname").val(),
			picture:document.getElementById('profile-img').src,phone:$("#phone").val(),city:$("#city").val()})
			console.log(d);
		$.post({
			url:"http://localhost:8080/api/register",
			data: d,
			contentType: "application/json",
            success:function(data){
            	
            	if(data == ""){
            		$('#message-email').html('Email already exists').css('color', 'red');
            	}else{
            		$('#message-email').html('');
            		confirm("Successful registration.Please verify your email!")
            	}
            }
		});
	});
	function readURL(input){
		var reader = new FileReader();

        reader.onload = function (e) {
            document.getElementById('profile-img').src =  reader.result;

        }

        reader.readAsDataURL(input.files[0]);
	};
	$("#file").change(function(){
        readURL(this);
    });

    $('#password, #r-password').on('keyup', function () {
    	  if ($('#password').val() == $('#r-password').val()) {
    	    $('#message').html('Passwords matching').css('color', 'green');
    	  } else 
    	    $('#message').html('Passwords does not match').css('color', 'red');
    	});
});