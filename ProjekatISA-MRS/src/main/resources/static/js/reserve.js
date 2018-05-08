$(document).ready(function() {
	var items;
	$.get({
		url:"http://localhost:8080/adminct/get_cinemas"
		,success:function(data){
			
			items = data;
			console.log(items);
			$.each(items, function (i, item) {
				console.log(items);
			    $('#cinema-select').append($('<option>', { 
			        value: item.name,
			        text : item.name 
			    }));
			});
			$('#cinema-select').selectpicker('refresh');

		}
	})
	
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
		if(cinema == 'Cinaplex'){
			cars = ["LOTR", "Maratonci", "Get hard"];
		}else{
			cars =["limun"];
		}
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
	var noobs = ["Lemur Lemuric a@aaa", "Deisan Sorgic a@aa", "the Dog a@aa"];
	function selectFriends() {
		$('#friends').empty();
		$.each(noobs, function (i, item) {
			
			$('#friends').append($('<option>', { 
		        value: item,
		        text : item 
		    }));
		});
		$('#friends').selectpicker('refresh');
	}
	selectFriends();
	
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
        $(this).closest("tr").remove();       
    });
	console.log(seats);
	$('#form').on('submit',function(){
		
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
		var userShort = {"email":"ogauzz1@gmail.com"};
		var data =JSON.stringify({"user":userShort,"place":cinema,"time":time,"date":date,"show":projection
			,"friends":invited,"isCinema":true});
		console.log(data);
		$.ajax({
			 type:    "POST",
			url:'http://localhost:8080/api/make_reservation',
			contentType: "application/json",
			data: data,
			success:function(d){
				alert(d);
			},
			error:   function(jqXHR, textStatus, errorThrown) {
		        alert("Error, status = " + textStatus + ", " +
		              "error thrown: " + errorThrown
		        );
		  }
		});
	})
	
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