$(document).ready(function() {

	var user = JSON.parse(localStorage.getItem('user'));
	console.log(user.phone);
	$('#name-surname').html(user.name +" "+ user.surname);
    $('#email').html(user.email);
    $('#profile-pic').attr("src",user.picture);
    $('#phone').html(user.phone);
    $('#city').html(user.city);
    
    $('#modal-name').val(user.name);
    $('#modal-surname').val(user.surname);
    $('#modal-password').val(user.password);
    $('#modal-r-password').val(user.password);
    $('#modal-email').val(user.email);
    $('#profile-img').attr("src",user.picture);
    $('#modal-phone').val(user.phone);
    $('#modal-city').val(user.city);
    
    $('#update').click(function(){
    	if ($('#modal-password').val() != $('#modal-r-password').val()){
			return;
		} 
		event.preventDefault();
		var d = JSON.stringify({email:$("#modal-email").val(),password:$("#modal-password").val(),name:$("#modal-name").val(),surname:$("#modal-surname").val(),
			picture:document.getElementById('profile-img').src,phone:$("#modal-phone").val(),city:$("#modal-city").val()})
		 	var $btn = $(this);
			$btn.button('loading');
		$.post({
			url:"http://localhost:8080/api/changeUser",
			data: d,
			contentType: "application/json",
            success:function(data){
            	localStorage.setItem('user',JSON.stringify(data));
    	    	var user = JSON.parse(localStorage.getItem('user')); 
    	    	$('#name-surname').html(user.name +" "+ user.surname);
    	        $('#email').html(user.email);
    	        $('#profile-pic').attr("src",user.picture);
    	        $('#phone').html(user.phone);
    	        $('#city').html(user.city);
    	        $btn.button('reset');
    	        $('#edit').modal('toggle');
    	        
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
	
	$('#modal-password, #modal-r-password').on('keyup', function () {
		  if ($('#modal-password').val() == $('#modal-r-password').val()) {
		    $('#message').html('Passwords matching').css('color', 'green');
		  } else 
		    $('#message').html('Passwords does not match').css('color', 'red');
		});
});