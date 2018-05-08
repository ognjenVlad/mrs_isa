function cinemaHallAdd(){
	//var rows = $("#hall_row_num").val();
	//var columns = $("#hall_column_num").val();
	var tr_code = "<tr> <td> Sala1 </td> <td> 5 </td> <td> 5 </td> ";
	tr_code += "<td> <button class=\"btn btn-primary\" onclick=\"deleteHall()\">Delete </button></td></tr>"
	$("#cinema_hall_body").append(tr_code);
}

function deleteHall(){
	var mouse_pos = $(this).parent().offset();
	var pos;
	var height = 0;
	var width = 0;
	$('#cinema_halls tr').each(function(){
        pos = $(this).position();
        height = $(this).height();
        width = $(this).width();
        if ((pos.top + height) => mouse_pos.top &&
        		pos.top  < mouse_pos.top &&
        		(pos.top + height) => mouse_pos.top &&
        		pos.top  < mouse_pos.top){
        	console.log("Brisem");
        }
	});
	
}



function checkCinemaAdd(){
	var name = $("#cinema_name").val();
	var address = $("#cinema_address").val();
	var description = $("#cinema_description").val();
	var isCinema_val = $("input:radio[name ='managerelradio']:checked").val();
	if(isCinema_var == "true"){
		var isCinema = true;
	}else{
		var isCinema = false;
	}
	
	$('#cinema_halls tr td').each(function(){
        $(this).val();
	});
	
	if(name =="" || address =="" ||description =="")
	{
		alert("No empty fileds");
		return;
	}

	var json_data =  JSON.stringify({
		"name": name,
		"address": address,
		"description":description,
		"isCinema":isCinema
	});

	
	console.log(json_data);
	
	$.ajax({
		type : 'POST',
		url : 'http://localhost:8080/adminct/add_cinema',
		contentType : 'application/json',
		dataType : "text",
		data : json_data,
		success: function(data){
			alert("Cinema added");
		},
	});
}

function updateAdminsField(){
	$.ajax({
		type : 'GET',
		url : 'http://localhost:8080/adminct/get_admins',
		dataType : 'json',
		success : function(data){
			var possibilities = $("<select name=\"territory\" class=\"reg_ter\"></select>");
			$.each(data,function(index,value){
				possibilities.append("<option>"+ value.name +"</option>");
			})
			$(".territory_select").empty();
			$(".territory_select").append(possibilities);
			},
		error : displayError
	});	
}