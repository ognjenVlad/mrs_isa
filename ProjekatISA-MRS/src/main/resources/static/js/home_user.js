function log_out(){

		localStorage.removeItem('user');
		location.replace("http://localhost:8080/");
}
$(document).ready(function() {
	
	
	var reservationsTable = $('#tickets').DataTable({
		"paging":   false,

        "info":     false,
      
	});
	
	var historyTable = $('#history_tickets').DataTable({
		"paging":   false,

        "info":     false,
      
	});
	var cinemaTable = $('#cinemaTable').DataTable({
		"paging":   false,

        "info":     false,
      
	});

	var theatersTable = $('#theatersTable').DataTable({
		"paging":   false,

        "info":     false,
      
	});

	readReservations();
	readHistory();
	readCinemas();
	readCinemas();
	function readReservations(){
		var reservations = [];
		//$('#friends-table').empty();
		reservationsTable.clear();
		$.post({
			url : "http://localhost:8080/api/getReservation",
			data : localStorage.getItem('user'),
			contentType : "application/json",
			success : function(data) {
				console.log(data);
				
				for(reservation in data){
					friends = []
					seats = []
					for(item in data[reservation].friends){
						friends.push(" " + data[reservation].friends[item].name + " " + data[reservation].friends[item].surname +" " + data[reservation].friends[item].email)
					}
					for(item in data[reservation].seats){
						seats.push(" " + data[reservation].seats[item] + " ")
					}
					var button = '<input type="button" class="ibtnDel btn btn-md btn-default"  value="Cancel">';
					
					reservationsTable.row.add([data[reservation].show,data[reservation].time,
					               data[reservation].date,
					               data[reservation].place,
					               friends,
					               seats,button
					               ]);
					reservationsTable.draw();
				}
				
			     
		        
			}})
		
	}
	$("#tickets").on("click", ".ibtnDel", function (event) {
		$row = $(this).closest("tr");
		$tds = $row.find("td");          
		var reservation = [];
		$.each($tds, function() {               
			reservation.push($(this).text());        
		});
		var u = JSON.parse(localStorage.getItem('user'));
		delete u.id;
		var friends = reservation[4].split(",");
		var invited = [];
		for(item in friends){
			var res = item.split(" ");
			invited.push({"email":res[2],"name":res[0],"surname":res[1]});
			console.log(res);
		}
		var data =JSON.stringify({"user":u,"place":reservation[3],"time":reservation[1],"date":reservation[2],"show":reservation[0]
			,"friends":invited,"isCinema":true});
		console.log(data);
		
		
		$.post({
			url : "http://localhost:8080/api/cancel_reservation",
			data : data,
			contentType : "application/json",
			success : function(data){
				if(data!=false){
					console.log(data);
					
					$row.remove();
				}
				alert("It is only 30 minutes until projection starts, too late to cancel reservation!");
				
				
			}
        });
        
        
    });
	function readHistory(){
		var reservations = [];
		//$('#friends-table').empty();
		historyTable.clear();
		$.post({
			url : "http://localhost:8080/api/getHistory",
			data : localStorage.getItem('user'),
			contentType : "application/json",
			success : function(data) {
				console.log(data);
				
				for(reservation in data){
					friends = []
					seats = []
					for(item in data[reservation].friends){
						friends.push(" " + data[reservation].friends[item].name + " " + data[reservation].friends[item].surname)
					}
					for(item in data[reservation].seats){
						seats.push(" " + data[reservation].seats[item] + " ")
					}
					historyTable.row.add([data[reservation].show,data[reservation].time,
					               data[reservation].date,
					               data[reservation].place,
					               friends,
					               seats
					               ]);
					historyTable.draw();
				}
				
			     
		        
			}})
		
	}
	var user = JSON.parse(localStorage.getItem('user'));
	function readCinemas(){
		cinemaTable.clear();
		theatersTable.clear();
			$.ajax({
				type : 'GET',
				url : 'http://localhost:8080/load_cinemas',
				dataType : "json", // data type of response
				success : function(data) {
					
					var list = data == null ? []
					: (data instanceof Array ? data : [ data ]);
					cinemas = list;
					$.each(list, function(index, cinema) {
						var ind = index + 1;
						
						if(cinema.isCinema == true){
							var button = '<a href="cinema_profile.html?'+
		                     cinema.id+'">Repertoire</a>';
							
							cinemaTable.row.add([cinema.name,cinema.address,button]);
							cinemaTable.draw();
						}else{
							var button = '<a href="cinema_profile.html?'+
		                     cinema.id+'">Repertoire</a>';
							theatersTable.row.add([cinema.name,cinema.address,button]);
							theatersTable.draw();
						}
				        
					});
					cinemaTable.draw();
					theatersTable.draw();
				}
			})
		};
	
	
	function getCinemas() {
		console.log("ready!");
		$.ajax({
			type : 'GET',
			url : 'http://localhost:8080/load_cinemas',
			dataType : "json", // data type of response
			success : renderCinemaTheaters,
		});
	}
	function getTheaters() {
		console.log("ready!");
		$.ajax({
			type : 'GET',
			url : 'http://localhost:8080/load_cinemas',
			dataType : "json", // data type of response
			success : renderCinemaTheaters,
		});
	}
	function showCinemaList() {
		$('#cinemas').modal('show');
	}

	function showTheatreList() {
		$('#theatres').modal('show');
	}
})