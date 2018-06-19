function log_out(){

		localStorage.removeItem('user');
		location.replace("http://localhost:8080/");
}
$(document).ready(function() {
	var items;
	$.ajax({
		type : 'GET',
		url : 'http://localhost:8080/load_cinemas',
		dataType : "json", // data type of response
		success : function(data) {
			console.log("AAA");
			var list = data == null ? []
			: (data instanceof Array ? data : [ data ]);
			cinemas = list;
			$.each(list, function(index, cinema) {
				$('#cinema-select').append($('<option>', { 
			        value: cinema.id,
			        text : cinema.name 
			    }));
			});
			$('#cinema-select').selectpicker('refresh')
		        
	}
	});

	var date_input=$('input[name="date"]'); //our date input has the name "date"
	
	date_input.datepicker({
		format: 'dd/mm/yyyy',
		todayHighlight: true,
		autoclose: true,
	})
	
	
	
	$('#cinema-select').on('change',function(){
		$('#projection-select').empty();
		//resetSeats();
		var cinema = $('#cinema-select').find(":selected").val();
		$.ajax({
			type : 'GET',
			url : 'http://localhost:8080/get_projections',
			dataType : "json",
			success : function(data){
				var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
				$.each(list, function(index, proj){
					if (cinema == proj.cinthe_id){	
						$('#projection-select').append($('<option>', { 
					        value: proj,
					        text : proj.name 
					    }));
					
					
					}
				});
				$('#projection-select').selectpicker('refresh');
			}
		});
			
	})
	$('#projection-select').on('change',function(){
		$('#time').empty();
		var projection = $('#projection-select').find(":selected").val();
		$.each(projection.time, function(index, proj){
				
				$('#time').append($('<option>', { 
			        value: index,
			        text : proj 
			    }));
			
			
			
		})
		$('#time').selectpicker('refresh');
	});
	
//	$('#date').on('change',function(){
//		$('#time').empty();
//		var day = $('#date');
//		
//		var projection_starts=['23:46', '21:50'];
//		$.get({
//			url:"http://localhost:8080/adminct/get_starts"
//			,data: day
//			,success:function(data){
//				projection_starts = data;
//			}
//		})
//		$.each(projection_starts, function (i, item) {
//			
//			$('#time').append($('<option>', { 
//		        value: item,
//		        text : item 
//		    }));
//		});
//		$('#time').selectpicker('refresh');
//	});
	
	$('#time').on('change',function(){
		//resetSeats();
		$('#hall').empty();
		var index = $('#time').find(":selected").val();
		var projection = $('#projection-select').find(":selected").val();
		console.log(projection);

		
		var hall = projection.halls[index];
		$('#hall').append($('<option>', { 
	        value: hall,
	        text : hall.hall_id 
	    }));
	
		$('#hall').selectpicker('refresh');
	});
	//get friends from user
	var noobs = [];
	
	$('#hall').on('change',function(){
		$('#friends').empty();

		$.post({
			url : "http://localhost:8080/api/getFriends",
			data : localStorage.getItem('user'),
			contentType : "application/json",
			success : function(data) {
				
				$.each(data, function (i, item) {
					noobs.push(item.name+" " +item.surname);
					$('#friends').append($('<option>', { 
				        value: item.name +" " + item.surname+" " +item.email,
				        text : item.name +" " + item.surname +" " +item.email
				    }));
				});
				$('#friends').selectpicker('refresh');
			}
		})
	})
	function selectFriends(){
		$('#friends').empty();
		
		$.each(noobs, function (i, item) {
			var res = item.split(" ");
			$('#friends').append($('<option>', { 
		        value: item,
		        text : res[0] +" "+ res[1] +" "+ res[2]
		    }));
		});
		$('#friends').selectpicker('refresh');
	}
	
	var seats;
	var length;
	
	$('#invite-button').on('click',function(){
		
		var rowCount = $('#friends-table tr').length;
		var friend = $('#friends').find(":selected").text();
		if(friend =='Choose one of the following...'){
			return;
		}
		length = seats.getSelected().length;
		
		if(seats.getSelected().length<=rowCount){
			alert("You did not pick enaugh seats!");
			return;
		}
			
		
		
		var index = noobs.indexOf(friend);
		noobs.splice(index,1);
		selectFriends();

		var newRow = $("<tr>");
		var cols = "";

        cols += '<td>'+friend+'</td>';
        cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
        newRow.append(cols);
		$('#friends-table').append(newRow);
		
		
	});	
	$("#friends-table").on("click", ".ibtnDel", function (event) {
		$row = $(this).closest("tr");
		$tds = $row.find("td");          
		var user = [];
		$.each($tds, function() {               
		    noobs.push($(this).text()); 
		    
		});
		noobs.splice(-1,1)
		selectFriends();
		$row.remove();
         
    });
	console.log(seats);
	$('#make-reservation').on('click',function(){
		
	length = seats.getSelected().length
		var rowCount = $('#friends-table tr').length;
	console.log(length);
	if(length <rowCount){
			alert("You invited more friends than you have reserved seats, please reserve more seats or delete invites!");
			return;
		}
	var cinema = $('#cinema-select').find(":selected").text();
		var user = JSON.parse(localStorage.getItem('user'));
		var time = $('#time').find(":selected").text();
		var date = $('#date').val();
		var projection = $('#projection-select').find(":selected").text();
		var hall = $('#hall').find(":selected").text();
		var invited = [];
		$('#friends-table > tbody > tr').each(function() {
			var c = $(this).text();
			var res = c.split(" ");
			invited.push({"email":res[2],"name":res[0],"surname":res[1]});
			console.log(res);
		});
		var user = JSON.parse(localStorage.getItem('user'));
		delete user.id;
		var booked = [];
		var items = seats.getSelected()
		jQuery.each(items, function(index, item) {
			booked.push(item.id);
		});
		var data =JSON.stringify({"user":user,"place":cinema,"time":time,"date":date,"show":projection
			,"friends":invited,"isCinema":true,"seats":booked});
		console.log(data);
		$.post({
			url:'http://localhost:8080/api/make_reservation',
			contentType: "application/json",
			data: data
			
		})
		
		window.location="http://localhost:8080/userProfile.html";
		return;
	});

	$('#hall').on('change',function(){
		
		var hall = $('#hall-select').find(":selected").val();

		var data =JSON.stringify({"time":time,"date":date,"show":projection});
		
		$.post({
			url : "http://localhost:8080/api/get_seats",
			data : data,
			contentType: "application/json",
			success: function(data){
				console.log(data);
				$("#legends").css("visibility", "visible");
				seats = $('#seats').flexiSeats({
				    rows: hall.rows1,
				    columns: hall.columns1,
				    multiple: false,
				    booked: data
				});
				console.log(seats.getAvailable());
				console.log(seats);

			
			}
		
		})
		
	});

	function getCinemas() {
		console.log("ready!");
		$.ajax({
			type : 'GET',
			url : 'http://localhost:8080/load_cinemas',
			dataType : "json", // data type of response
			success : function(data){
				
			}
		});
	}
});