$(document).ready(function() {
	var user = JSON.parse(localStorage.getItem('user'));
	console.log(user);
	function fillTable(){
		
		var friends = [];
		$('#friends-table').empty();
		
		$.post({
			url : "http://localhost:8080/api/getFriends",
			data : localStorage.getItem('user'),
			contentType : "application/json",
			success : function(data) {
				
				for(friend in data){
					var newRow = $("<tr>");
					var cols = "";
					cols += '<td><img src="'+ data[friend].picture+'"/></td><td>'+data[friend].name
					+'</td>' + '<td>'+data[friend].surname+'</td>'+'<td>'+data[friend].email+'</td>'+
					'<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
					
					newRow.append(cols);
					$('#friends-table').append(newRow);
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
				
				for(friend in data){
					var newRow = $("<tr>");
					var cols = "";
					cols += '<td><img src="'+ data[friend].picture+'"/></td><td>'+data[friend].name
					+'</td>' + '<td>'+data[friend].surname+'</td>'+'<td>'+data[friend].email+'</td>'+
					'<td><input type="button" class="accept btn btn-md btn-primary "  value="Accept"><input type="button" class="decline btn btn-md btn-default "  value="Decline"></td>';
					
					newRow.append(cols);
					$('#requests-table').append(newRow);
				}
			     
		        $("#req-number").text(data.length);
			}})
		
	}
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
	var users = []
	$('#find-friends').click(function(){
		$('#friend-select').empty();
		$.get({
			url:"http://localhost:8080/api/getUsers"
			,success:function(data){
				
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
	
	$('#friend-select').on('change',function(){
		var v = $('#friend-select').find(":selected").val();
		var res = v.split(" ");
		console.log(res);
		$('#find-info').html("<tr><td>Name:</td><td>"+res[0]+"</td><tr><td>Surname:</td><td>"+res[1]+"</td>"+
				'<tr><td><input type="button" class="accept btn btn-md btn-primary "  id ="add-user" value="Add user"></td></tr>');
	
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
			picture:document.getElementById('profile-img').src,phone:$("#modal-phone").val(),city:$("#modal-city").val()})
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
	
	$('#modal-password, #modal-r-password').on('keyup', function () {
		  if ($('#modal-password').val() == $('#modal-r-password').val()) {
		    $('#message').html('Passwords matching').css('color', 'green');
		  } else 
		    $('#message').html('Passwords does not match').css('color', 'red');
		});
});