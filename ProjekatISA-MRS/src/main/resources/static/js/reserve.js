$(document).ready(function() {
	var items;
//	$.get({
//		url:"http://localhost:8080/adminct/get_cinemas"
//		,success:function(data){
//			
//			items = data;
//			console.log(items);
//			$.each(items, function (i, item) {
//				console.log(items);
//			    $('#cinema-select').append($('<option>', { 
//			        value: item.name,
//			        text : item.name 
//			    }));
//			});
//			$('#cinema-select').selectpicker('refresh');
//
//		}
//	})
	$("#signout").on('click',function()
			{
			localStorage.removeItem('user');
			window.location.replace("https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8080/signin.html");
	});
	$('#cinema-select').append($('<option>', { 
        value: "cine",
        text : "cine" 
    }));

	$('#cinema-select').selectpicker('refresh');
	var date_input=$('input[name="date"]'); //our date input has the name "date"
	
	date_input.datepicker({
		format: 'dd/mm/yyyy',
		todayHighlight: true,
		autoclose: true,
	})
	
	
	
	$('#cinema-select').on('change',function(){
		$('#projection-select').empty();
		
		var cinema = $('#cinema-select').find(":selected").text();
//		var projections;
//		$.get({
//			url:"http://localhost:8080/adminct/get_projections"
//			,data: cinema
//			,success:function(data){
//				projections = data;
//			}
//		})
		//
		var cars;
		
		cars =["lemur"];
		$.each(cars, function (i, item) {
			
			$('#projection-select').append($('<option>', { 
		        value: item,
		        text : item 
		    }));
		});
		$('#projection-select').selectpicker('refresh');
	
		
	})
	$('#date').on('change',function(){
		$('#time').empty();
		var day = $('#date');
		console.log(day);
		var projection_starts=['22:40', '21:50'];
//		$.get({
//			url:"http://localhost:8080/adminct/get_starts"
//			,data: day
//			,success:function(data){
//				projection_starts = data;
//			}
//		})
		$.each(projection_starts, function (i, item) {
			
			$('#time').append($('<option>', { 
		        value: item,
		        text : item 
		    }));
		});
		$('#time').selectpicker('refresh');
	});
	
	$('#time').on('change',function(){
		$('#hall').empty();
		var time = $('#time');
		console.log(time);
		var halls=['1', '2'];
//		$.get({
//			url:"http://localhost:8080/adminct/get_halls"
//			,data: time
//			,success:function(data){
//				halls = data;
//			}
//		})
		$.each(halls, function (i, item) {
			
			$('#hall').append($('<option>', { 
		        value: item,
		        text : item 
		    }));
		});
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
		length = seats.getSelected().length
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
			var res = c.split(" ")[2];
			invited.push({"email":res});
			console.log(res);
		});
		var user = JSON.parse(localStorage.getItem('user'));
		delete user.id;
		var data =JSON.stringify({"user":user,"place":cinema,"time":time,"date":date,"show":projection
			,"friends":invited,"isCinema":true});
		console.log(data);
		$.post({
			url:'http://localhost:8080/api/make_reservation',
			contentType: "application/json",
			data: data
			
		})
		
		window.location.replace("http://localhost:8080/userProfile.html");
		return;
	});
	
	$('#hall').on('change',function(){
		var hall_info;
//		$.get({
//		url:"http://localhost:8080/adminct/get_hall_info"
//		,data: time
//		,success:function(data){
//			hall_info = data;
//		}
	//})
		$("#legends").css("visibility", "visible");
		seats = $('#seats').flexiSeats({
		    rows: 8,
		    columns: 10,
		    multiple: false,
		    booked: ['0-0','5-4']
		});

	});
	
	

});