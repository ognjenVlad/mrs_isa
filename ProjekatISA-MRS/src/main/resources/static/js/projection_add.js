function getBase64(file) {
   var reader = new FileReader();
   reader.readAsDataURL(file);
   reader.onload = function () {
     console.log(reader.result);
   };
   reader.onerror = function (error) {
     console.log('Error: ', error);
   };
   return reader.result;
}



$(document).ready(function() {
	$("#add_projection_form").submit(function(event){
		event.preventDefault();

		var name = $("#name").val();
		
		var actors = []
		for (var i = 0; i < actors_count; i++){
			actors.push($('#actor'+i).html());
		}
		
		var genre = $("#genre").val();
		var director = $("#director").val();
		var length  = $("#length").val();
		
		var file = $('#poster')[0].files[0];
		var poster = getBase64(file); // prints the base64 string
		
		var description = $("#description").val();
		
		var prj_halls = [];
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
			prj_time.push($('#time'+i).val());
		}
			
		var json_data =  JSON.stringify({
			"name": name,
			"actors": actors,
			"genre": genre,
			"director": director,
			"length": length,
			"poster": poster,
			"description":description,
			"halls":prj_halls,
			"time" : prj_time,
			"price" : price,
			"cinthe_id" : cinthe_id
		});
	
		
		
		$.post({
			url : 'http://localhost:8080/adminct/add_projection',
			contentType : 'application/json',
			dataType : "text",
			data : json_data,
			success: function(data){
				alert("Projection added");
				$('#modal_add_cinema').modal('hide');
				location.reload();
			},
		});
	})

	
	function readURL(input){
		var reader = new FileReader();

        reader.onload = function (e) {
            document.getElementById('add_admin_profile-img').src =  reader.result;
        }

        reader.readAsDataURL(input.files[0]);
	};
	$("#add_admin_file").change(function(){
        readURL(this);
    });
	

    $('#add_admin_password, #add_admin_r-password').on('keyup', function () {
    	  if ($('#add_admin_password').val() == $('#add_admin_r-password').val()) {
    	    $('#add_admin_message').html('Passwords matching').css('color', 'green');
    	  } else 
    	    $('#add_admin_message').html('Passwords do not match').css('color', 'red');
    	});	
});