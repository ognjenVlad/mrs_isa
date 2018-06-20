function getBase64(file, modal_check) {
   var reader = new FileReader();
   reader.readAsDataURL(file);
   reader.onload = function () {
	   $('#'+modal_check+'poster_src').empty();
	   $('#'+modal_check+'poster_src').val(reader.result);
   };
}



function modify_projection(index){
	var proj = projections[index];
	$('#m_id').html(proj.id);
	$('#m_name').val(proj.name);
	$('#m_actors').empty();
	for (var i = 0; i < proj.actors.length; i++){
		$('#m_actors').append('<tr><td id="m_actor'+i+'">'+proj.actors[i]+'</td><button type="button">Remove</button></tr>')
	}
	actors_count = proj.actors.length;
	$('#m_genre').val(proj.genre);
	$('#m_director').val(proj.director);
	$('#m_length').val(proj.length);
	$('#m_description').val(proj.description);
	$('#m_halls').empty();
	for (var i = 0; i < proj.halls.length; i++){
		$('#m_halls').append('<tr><td id="m_hall'+i+'">'+proj.halls[i].hall_id+'</td></tr>')
		var date_html = '<div class="form-group"><label for="date'+i+'" class="col-form-label">'+
		'Date of projection:</label><input class="form-control" id="m_date'+i+'"placeholder="DD/MM/YYYY" autocomplete="off" '+
		'type="text" required/></div>';
		$('#m_halls').append(date_html);
		$('#m_date'+i).val(proj.date[i]);
		var time_html = '<div class="form-group"><label for="time'+i+'" class="col-form-label">'+
		'Time of projection:</label><input type="time" class="form-control" id="m_time'+i+'"></div>';
		$('#m_halls').append(time_html);
		$('#m_time'+i).val(proj.time[i]);
	}
	halls_count = proj.halls.length;
	$('#m_price').val(proj.price);
	$('#modifyModal').modal('show');
}

function delete_projection(index){
	var proj_id = projections[index].id;
	$('#del_proj_id').html(proj_id);
	$('#deleteModal').modal('show');
}

function load_poster(modal_check){
	var file = $('#'+modal_check+'poster')[0].files[0];
	var poster = getBase64(file, modal_check); // prints the base64 string
}

$(document).ready(function() {
	
	$("#add_projection_form").submit(function(event){
		event.preventDefault();

		var name = $("#name").val();
		$.each(projections, function(index, proj){
			if (name == proj.name && cin_id == proj.cinthe_id){
				alert("Projection like this already exists in place.");
				return;
			}
		});
		
		var actors = []
		for (var i = 0; i < actors_count; i++){
			actors.push($('#actor'+i).html());
		}
		
		var genre = $("#genre").val();
		var director = $("#director").val();
		var length  = $("#length").val();
		
		var poster = $('#poster_src').val(); // prints the base64 string
		
		var description = $("#description").val();
		
		var prj_halls = [];
		var prj_date = [];
		var prj_time = [];
		
		var price = $('#price').val();
		
		var cinthe_id = cin_id;
		
		for (var i = 0; i < halls_count; i++){
			var hall_to_add;
			for (var j = 0; j < halls.length; j++){
				var hall_id = halls[j].hall_id;
				if ($('#hall'+j).html() == hall_id){
					hall_to_add = halls[j];
				}
			}
			prj_halls.push(hall_to_add);
			prj_date.push($('#date'+i).val());
			prj_time.push($('#time'+i).val());
			
		}
			
		actors_count = 0;
		halls_count = 0;
		
		var json_data =  JSON.stringify({
			"name": name,
			"actors": actors,
			"genre": genre,
			"director": director,
			"length": length,
			"poster": poster,
			"description":description,
			"halls":prj_halls,
			"date": prj_date,
			"time" : prj_time,
			"price" : price,
			"cinthe_id" : cinthe_id,
		});
	
		
		
		$.post({
			url : 'http://localhost:8080/adminct/add_projection',
			contentType : 'application/json',
			dataType : "text",
			data : json_data,
			success: function(data){
				alert("Projection added");
				$('#addProjectionModal').modal('hide');
				location.reload();
			},
		});
	})
	
	$("#modify_form").submit(function(event){
		event.preventDefault();

		var id = $('#m_id').html();
		var name = $("#m_name").val();
		$.each(projections, function(index, proj){
			if (name == proj.name && cin_id == proj.cinthe_id){
				alert("Projection like this already exists in place.");
				return;
			}
		});
		
		var actors = []
		for (var i = 0; i < actors_count; i++){
			actors.push($('#m_actor'+i).html());
		}
		
		var genre = $("#m_genre").val();
		var director = $("#m_director").val();
		var length  = $("#m_length").val();
		
		
		var file = $('#m_poster')[0].files[0];
		if (file != null)
			var poster = $('#m_poster_src').val(); // prints the base64 string
		
		var description = $("#m_description").val();
		
		var prj_halls = [];
		var prj_date = [];
		var prj_time = [];
		
		var price = $('#m_price').val();
		
		var cinthe_id = cin_id;
		
		for (var i = 0; i < halls_count; i++){
			var hall_to_add;
			for (var j = 0; j < halls.length; j++){
				var hall_id = halls[j].hall_id;
				if ($('#m_hall'+j).html() == hall_id){
					hall_to_add = halls[j];
				}
			}
			prj_halls.push(hall_to_add);
			prj_date.push($('#m_date'+i).val());
			prj_time.push($('#m_time'+i).val());
		}
			
		actors_count = 0;
		halls_count = 0;
		
		var json_data =  JSON.stringify({
			"id": id,
			"name": name,
			"actors": actors,
			"genre": genre,
			"director": director,
			"length": length,
			"poster": poster,
			"description":description,
			"halls":prj_halls,
			"date": prj_date,
			"time" : prj_time,
			"price" : price,
			"cinthe_id" : cinthe_id
		});
	
		
		
		$.post({
			url : 'http://localhost:8080/adminct/modify_projection',
			contentType : 'application/json',
			dataType : "text",
			data : json_data,
			success: function(data){
				alert("Projection changed");
				$('#modifyModal').modal('hide');
				location.reload();
			},
		});
	})
	
	$('#delete_proj_btn').click(function(event){
		event.preventDefault();
		
		var id = $('#del_proj_id').html();
		
		$.post({
			url : 'http://localhost:8080/adminct/delete_projection/'+id,
			dataType : "text",
			success: function(data){
				alert("Projection has been deleted");
				$('#deleteModal').modal('hide');
				location.reload();
			},
		});
	})

});