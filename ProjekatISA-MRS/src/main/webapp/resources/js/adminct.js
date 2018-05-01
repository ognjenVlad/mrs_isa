
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
