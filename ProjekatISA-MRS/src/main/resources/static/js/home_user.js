function log_out(){

		localStorage.removeItem('user');
		location.replace("http://localhost:8080/");
}
$(document).ready(function() {
	$('#reservations').on('shown.bs.modal', function (e) {
		readReservations();
	})
	$('#history').on('shown.bs.modal', function (e) {
		readHistory();
	})
	$('#cinemas').on('shown.bs.modal', function (e) {
		readCinemas();
	})
	$('#theaters').on('shown.bs.modal', function (e) {
		readCinemas();
	})
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
						friends.push(" " + data[reservation].friends[item].name + " " + data[reservation].friends[item].surname)
					}
					for(item in data[reservation].seats){
						seats.push(" " + data[reservation].seats[item] + " ")
					}
					reservationsTable.row.add([data[reservation].show,data[reservation].time,
					               data[reservation].date,
					               data[reservation].place,
					               friends,
					               seats
					               ]);
					reservationsTable.draw();
				}
				
			     
		        
			}})
		
	}
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
		
		var rad = function(x) {
			  return x * Math.PI / 180;
			};

			var getDistance = function(p1, p2) {
			  var R = 6378137; // Earth’s mean radius in meter
			  var dLat = rad(p2.lat() - p1.lat());
			  var dLong = rad(p2.lng() - p1.lng());
			  var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
			    Math.cos(rad(p1.lat())) * Math.cos(rad(p2.lat())) *
			    Math.sin(dLong / 2) * Math.sin(dLong / 2);
			  var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			  var d = R * c;
			  return d; // returns the distance in meter
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