function log_out(){
		localStorage.removeItem('user');
		location.replace("http://localhost:8080/");
	}
$(document).ready(function() {
	var user = JSON.parse(localStorage.getItem('user'));
	var table = $('#friends-table').DataTable({
		"paging":   false,

        "info":     false,
        'columnDefs'        : [    
                                        { 
                                            'searchable'    : false, 
                                            'targets'       : [0,3] 
                                        },
                                        { 
                                            'sortable'    : false, 
                                            'targets'       : [0,4] 
                                        }
                                    ],
                                    "aaSorting": []
	});
	var reservationsTable = $('#tickets').DataTable({
		"paging":   false,

        "info":     false,
      
	});
	var find_table = $('#find-table').DataTable({
		"paging":   false,

        "info":     false,
        'columnDefs'        : [    
                                        { 
                                            'searchable'    : false, 
                                            'targets'       : [0,3] 
                                        },
                                        { 
                                            'sortable'    : false, 
                                            'targets'       : [0,4] 
                                        }
                                    ],
                                    "aaSorting": []
	});
	console.log(user);
	function fillTable(){
		
		var friends = [];
		//$('#friends-table').empty();
		table.clear();
		$.post({
			url : "http://localhost:8080/api/getFriends",
			data : localStorage.getItem('user'),
			contentType : "application/json",
			success : function(data) {
				
				for(friend in data){
//					var newRow = $("<tr>");
//					var cols = "";
//					cols += '<td><img src="'+ data[friend].picture+'"/></td><td>'+data[friend].name
//					+'</td>' + '<td>'+data[friend].surname+'</td>'+'<td>'+data[friend].email+'</td>'+
//					'<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
//					
//					newRow.append(cols);
//					$('#friends-table').append(newRow);
					var pic ='<img class="profile-img" src='+data[friend].picture+'>';
					var button = '<input type="button" class="ibtnDel btn btn-md btn-default"  value="Delete">';
					table.row.add([pic,data[friend].name,data[friend].surname,data[friend].email, button]);
					table.draw();
				}
				
			     
		        
			}})
		
	}
	$("#friends-table").on("click", ".ibtnDel", function (event) {
		$row = $(this).closest("tr");
		$tds = $row.find("td");          
		var user = [];
		$.each($tds, function() {               
		    user.push($(this).text());        
		});
		console.log(user);
		var u = JSON.parse(localStorage.getItem('user'));
		delete u.id;
		var friend = {"email":user[3],"name":user[1],"surname":user[2]};
		var friends = JSON.stringify({user:u,friend:friend});
        
		$.post({
			url : "http://localhost:8080/api/deleteFriend",
			data : friends,
			contentType : "application/json",
			success : function(){
				$row.remove();
			}
        });
        
    });
	fillTable();
	
	function fillRequests(){
		
		var requests = [];
		
		
		$.post({
			url : "http://localhost:8080/api/getRequests",
			data : localStorage.getItem('user'),
			contentType : "application/json",
			success : function(data) {
				console.log(data);
				for(friend in data){
					var newRow = $("<tr>");
					var cols = "";
					cols += '<td><img class="profile-img" src="'+ data[friend].picture+'"/></td><td>'+data[friend].name
					+'</td>' + '<td>'+data[friend].surname+'</td>'+'<td>'+data[friend].email+'</td>'+
					'<td><input type="button" class="accept btn btn-md btn-primary "  value="Accept"><input type="button" class="decline btn btn-md btn-default "  value="Decline"></td>';
					
					newRow.append(cols);
					$('#requests-table').append(newRow);
				}
			     
		        $("#req-number").text(data.length);
			}})
		
	}
//	function searchTable() {
//		  // Declare variables 
//		  var input, filter, table, tr, td, i;
//		  input = document.getElementById("search");
//		  filter = input.value.toUpperCase();
//		  table = document.getElementById("friends-table");
//		  tr = table.getElementsByTagName("tr");
//		  
//		  for (i = 0; i < tr.length; i++) {
//		    td = tr[i].getElementsByTagName("td")[0];
//		    if (td) {
//		      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
//		        tr[i].style.display = "";
//		      } else {
//		        tr[i].style.display = "none";
//		      }
//		    } 
//		  }
//		}
	$("#requests-table").on("click", ".decline", function (event) {
		$row = $(this).closest("tr");
		$tds = $row.find("td");          
		var user = [];
		$.each($tds, function() {               
		    user.push($(this).text());        
		});
		var u = JSON.parse(localStorage.getItem('user'));
		delete u.id;
		var friend = {"email":user[3],"name":user[1],"surname":user[2]};
		var friends = JSON.stringify({user:u,friend:friend});
		
		
        $.post({
			url : "http://localhost:8080/api/declineRequest",
			data : friends,
			contentType : "application/json",
			success: function(){
				$row.remove();
				var num = $("#req-number").text() - 1;
				$("#req-number").text(num);
			}
        });
        
    });
	
	$("#requests-table").on("click", ".accept", function (event) {
		$row = $(this).closest("tr");
		$tds = $row.find("td");          
		var user = [];
		$.each($tds, function() {               
		    user.push($(this).text());        
		});
		console.log(user);
		
		var u = JSON.parse(localStorage.getItem('user'));
		delete u.id;
		var friend = {"email":user[3],"name":user[1],"surname":user[2]};
		var friends = JSON.stringify({user:u,friend:friend});
        
		$.post({
			url : "http://localhost:8080/api/acceptRequest",
			data : friends,
			contentType : "application/json",
			success: function(){
				$row.remove();
				var num = $("#req-number").text() - 1;

				$("#req-number").text(num);
				fillTable();
			}
        });
        
    });
	fillRequests();
	
	$("#signout").on('click',function()
			{
			localStorage.removeItem('user');
			window.location.replace("https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8080/signin.html");
	});
	var users = []
	$('#find-friends').click(function(){
		$('#friend-select').empty();
		var u = user;
		delete u.id;

		$.post({
			url:"http://localhost:8080/api/getUsers"
			,data:JSON.stringify(u)
			,contentType : "application/json",
			success:function(data){
				
				users = data;
				console.log(users);
				$.each(users, function (i, item) {
	
				    $('#friend-select').append($('<option>', { 
				        value: item.name+ " " + item.surname + " " + item.email +" " + item.city,
				        text : item.name + " " + item.surname
				    }));
				});
				$('#friend-select').selectpicker('refresh');
				$('#find').modal('toggle');
			}
		})
	});
	$("#find").on("hidden.bs.modal", function () {
		$('#find-info').empty();
	});
	$('#friend-select').on('change',function(){
		var v = $('#friend-select').find(":selected").val();
		var res = v.split(" ");
		var email = res[2];
		var result = $.grep(users, function(e){ return e.email == email; });
		
		console.log(result);
		$('#find-info').html('<img class="profile-img" src="'+result[0].picture+'">'+ "<tr><td>Name:</td><td>"+result[0].name+"</td><tr><td>Surname:</td><td>"+result[0].surname+"</td></tr>"+"<tr><td>Email:</td><td>"+result[0].email+"</td>"+
				"</tr></td>"+"<tr><td>City:</td><td>"+result[0].city+"</td>"+'<tr><td><input type="button" class="accept btn btn-md btn-primary "  id ="add-user" value="Add user"></td></tr>');
	
	})	
	$("#find-info").on("click", "tr", function(event){
		var friend = $('#friend-select').find(":selected").val();
		var res = friend.split(" ");
		var user = JSON.parse(localStorage.getItem('user'));
		delete user.id;
		var friends = JSON.stringify({user:user,friend:{email:res[2],name:res[0],surname:res[1]}});
		console.log(friends);
		$.post({
			url : "http://localhost:8080/api/addFriend",
			data : friends,
			contentType : "application/json",
			success : function(data) {
				alert("Friend request sent!");
			}})
	})
	console.log(user.phone);
	$('#name-surname').html(user.name +" "+ user.surname);
	$('#name').html(user.name);
    $('#surname').html(user.surname);
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
			picture:document.getElementById('profile-img').src,phone:$("#modal-phone").val(),city:$("#modal-city").val(),
			id:user.id, activated:user.activated, user_type:user.user_type})
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
	
	$("#showReservations").on('click',function(){
		readReservations();
	})
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

	$('#modal-password, #modal-r-password').on('keyup', function () {
		  if ($('#modal-password').val() == $('#modal-r-password').val()) {
		    $('#message').html('Passwords matching').css('color', 'green');
		  } else 
		    $('#message').html('Passwords does not match').css('color', 'red');
		});
});