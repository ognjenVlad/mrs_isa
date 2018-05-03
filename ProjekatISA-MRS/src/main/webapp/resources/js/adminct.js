function cinemaHallAdd(){
	var rows = $("#hall_row_num").val();
	var columns = $("#hall_column_num").val();
	var length = 1;
	$("#cinema_halls").append('<li class = \"cinema_hall\">Hall'+ length + " " + rows +" - " + columns+ '</li>');
	
	displayCinemaAdd();
}


function checkCinemaAdd(){
	var name = $("#cinema_name").val();
	var address = $("#cinema_address").val();
	var description = $("#cinema_description").val();
	var isCinema = $("#cinema_is_cinema").is(":checked");
	
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