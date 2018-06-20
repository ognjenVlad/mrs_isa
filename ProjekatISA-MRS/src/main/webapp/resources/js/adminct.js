function cinemaHallAdd(){
	var rows = $("#cinema_rows").val();
	var columns = $("#cinema_columns").val();
	if(rows == "" || columns == "")
		{
		alert("No empty fields");
		return;
		}
	row_counter += 1;
	var tr_code = "<tr id=\"table_row_"+ row_counter +"\"> <td> "+ rows +" </td> <td> "+ columns +" </td> ";
	tr_code += "<td> <button type=\"button\" class=\"btn btn-secondary btn-sm\" onclick=\"deleteHall(\'"+ row_counter +"\')\">Remove</button></td></tr>";
	$("#cinema_hall_body").append(tr_code);
}
var row_counter = 0;

function deleteHall(row_id){
	$('#cinema_halls tr').each(function(){
		if ($(this).attr('id') == "table_row_" + row_id){
			$(this).remove();
		}
	});
}



$(document).ready(function() {
	$("#add_cinema_form").submit(function(event){
		event.preventDefault();

		var name = $("#cinema_name").val();
		var address = $("#cinema_address").val();
		var description = $("#cinema_description").val();
		var isCinema_val = $("input:radio[name ='is_cinema_radio']:checked").val();
	
		if(isCinema_val == "true"){
			var isCinema = true;
		}else{
			var isCinema = false;
		}
			
		var json_data =  JSON.stringify({
			"name": name,
			"address": address,
			"description":description,
			"isCinema":isCinema,
			"halls" : createHallsJson()
		});
	
		
		
		$.post({
			url : 'http://localhost:8080/adminct/add_cinema',
			contentType : 'application/json',
			dataType : "text",
			data : json_data,
			success: function(data){
				alert("Cinema added");
				 $('#modal_add_cinema').modal('hide')
			},
		});
	})

	$("#form_add_admin").submit(function(event){
		if ($('#add_admin_password').val() != $('#add_admin_r-password').val()){
			return;
		} 
		event.preventDefault();
		var user_type_val = $("input:radio[name ='admin_type_radio']:checked").val();
		console.log(user_type_val);
		var d = JSON.stringify(
			{
				email:$("#add_admin_email").val(),
				password:$("#add_admin_password").val(),
				name:$("#add_admin_name").val(),
				surname:$("#add_admin_surname").val(),
				picture: "nothing", //document.getElementById('add_admin_profile-img').src,
				phone:$("#add_admin_phone").val(),
				city:$("#add_admin_city").val(),
				"user_type":user_type_val
			})

		$.post({
			url:"http://localhost:8080/api/add_admin",
			data: d,
			dataType: "json",
			contentType: "application/json",
            success:function(data){
            	if(data == ""){
            		$('#add_admin_message-email').html('Email already exists').css('color', 'red');
            	}else{
            		$('#add_admin_message-email').html('');
            		confirm("Adding done successfuly!")
            	}
            }
		});
	});
	function readURL(input){
		var reader = new FileReader();

        reader.onload = function (e) {
            document.getElementById('add_admin_profile-img').src =  reader.result;
        }

        reader.readAsDataURL(input.files[0]);
	};
	$("#add_admin_file").change(function(){
        readURL(this);
    });
	

    $('#add_admin_password, #add_admin_r-password').on('keyup', function () {
    	  if ($('#add_admin_password').val() == $('#add_admin_r-password').val()) {
    	    $('#add_admin_message').html('Passwords matching').css('color', 'green');
    	  } else 
    	    $('#add_admin_message').html('Passwords do not match').css('color', 'red');
    	});	
});

function createHallsJson(){
	var hall_list = [];
	var i = 0;
	$('#cinema_halls tr').each(function(){
		if(i == 0){
			i++;
			return true;
		}
		
		hall_list.push({
		    "hall_id": "Hall " + i.toString(),
		    "rows":  ($(":nth-child(1)",this).html()).trim(),
		    "columns":  ($(":nth-child(2)",this).html()).trim(),
		});
		
		i++;
	});

	console.log(hall_list);
	return hall_list;	
}
