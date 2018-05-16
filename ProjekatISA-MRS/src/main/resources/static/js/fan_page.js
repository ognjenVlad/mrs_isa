display_ads();
display_props();
var ad_img_src = "https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120";
var prop_img_src = "https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120";
var current_ad;


$(document).ready(function() {
	function readURL(input) {
		var reader = new FileReader();

		reader.onload = function(e) {
			ad_img_src = reader.result;
			prop_img_src = reader.result;
		}

		reader.readAsDataURL(input.files[0]);
	}
	;

	$("#ad_file").change(function() {
		readURL(this);
	});

	$("#prop_file").change(function() {
		readURL(this);
	});

	$("#form_create_ad").submit(function(event) {
		event.preventDefault();

		date_val = new Date($("#ad_exp_date").val());

		var d = JSON.stringify({
			"title" : $("#ad_title").val(),
			"description" : $("#ad_description").val(),
			"exp_date" : date_val.getMonth() + "/" + date_val.getDate() +  "/" + date_val.getFullYear(),
			"picture" : ad_img_src
		})

		console.log(d);

		$.post({
			url : "http://localhost:8080/admin_fan/add_ad",
			data : d,
			contentType : "application/json",
			success : function(data) {
				$('#modal_send_ad').modal('hide')
			}
		});
	});

	$("#form_create_prop").submit(function(event) {
		event.preventDefault();
		var d = JSON.stringify({
			"title" : $("#prop_title").val(),
			"description" : $("#prop_description").val(),
			"price" : $("#prop_price").val(),
			"picture" : prop_img_src
		})

		console.log(d);

		$.post({
			url : "http://localhost:8080/admin_fan/add_prop",
			data : d,
			contentType : "application/json",
			success : function(data) {
				$('#modal_create_prop').modal('hide')
				display_props();
			}
		});
	});
});


function get_ad_request() {
	$("#get_ad_container").empty();
	$
			.ajax({
				type : 'GET',
				url : "http://localhost:8080/admin_fan/get_next_ad",
				dataType : "json",
				success : function(data) {
					if (data.message == "Success") {
						update_ad_container(data.obj);
					} else {
						$("#get_ad_container")
								.append(
										"<h3> No pending ad requests </h3><button class=\"btn\" onclick=\"get_ad_request()\">Try again</button>");
						alert(data.message);
					}
				}
			});
};

function update_ad_container(ad) {
	current_ad = ad;
	var PICTURE_URL = ad.picture;
	var USERNAME = "User1";
	var COMMENT = ad.description;
	var TITLE = ad.title;

	var html_code = "<article class=\"row\"><div class=\"col-md-2 col-sm-2 hidden-xs\"><figure class=\"thumbnail\">";
	html_code += "<img class=\"img-responsive\" src=\"" + PICTURE_URL + "\" />";
	html_code += "<figcaption class=\"text-center\">" + USERNAME
			+ "</figcaption></figure></div>";
	html_code += "<div class=\"col-md-10 col-sm-10\"><div class=\"panel panel-default arrow left\"><div class=\"panel-body\">";
	html_code += "<header class=\"text-left\"><div class=\"comment-user\"><i class=\"fa fa-user\"></i>"
			+ TITLE + "</div>";
	html_code += "</header><div class=\"comment-post\"><p>"
			+ COMMENT
			+ "</p></div></div></div></div></article><button class=\"btn btn-success\" onclick=\"send_ad_response(true)\">Accept</button>";
	html_code += "<button class=\"btn btn-danger\" onclick=\"send_ad_response(false)\">Decline</button><button class=\"btn\" onclick=\"skip_ad()\">Skip this ad</button>";

	$("#get_ad_container").append(html_code);
}

function send_ad_response(do_publish) {
	update_ad(do_publish, true);
	get_ad_request();
}

function skip_ad() {
	update_ad(false, false);
	get_ad_request();
}

function update_ad(isPublished, isTaken) {
	$.ajax({
		type : 'GET',
		url : "http://localhost:8080/admin_fan/update_ad/" + isPublished + "/"
				+ isTaken + "/" + current_ad.id,
		dataType : "json",
		success : function(data) {
			display_ads();
		}
	});
}

function display_ads() {
	$("#ads").empty();
	var counter = 0;
	$.ajax({
		type : "GET",
		url : "http://localhost:8080/admin_fan/get_ads",
		dataType : "json",
		success : function(data) {
			var html_code;
			$("#post_container").empty();
			$.each(data.obj,function(index, ad) {
				html_code = "<article><div class=\"row\"><div class=\"col-sm-6 col-md-3\"><figure>";
				html_code += "<img src=\""+ ad.picture + "\" />";
				html_code += "</figure></div>";
				html_code += "<div class=\"col-md-9 col-sm-6\"><span class=\"label label-default pull-right\"><i class=\"glyphicon glyphicon-inbox\"></i> 0</span>";
				html_code += "<h4>Title: "+ ad.title + "</h4>";
				html_code += "<p>Description: "+ ad.description + "</p><section><i class=\"glyphicon glyphicon-user\"></i> Default User <i class=\"glyphicon glyphicon-calendar\">";
				html_code += "</i>" + ad.exp_date + "</section></div><input type=\"number\"/ placeholder=\"Bid amount\">";
				html_code += "</button><button class=\"btn\" onclick=\"add_bid("+ ad.id +")\">Add bid</button></div></article>";

				$("#ads").append(html_code);
				counter++;
			})

			if(counter ==0){
				$("#ads").append("<h3> No ads to display </h3>");
			}
		}
	});
}

function display_props() {
	$("#props").empty();
	var counter = 0;
	$.ajax({
		type : "GET",
		url : "http://localhost:8080/admin_fan/get_props",
		dataType : "json",
		success : function(data) {
			var html_code;
			$.each(data.obj,function(index, prop) {
				html_code = "<article><div class=\"row\"><div class=\"col-sm-6 col-md-3\"><figure>";
				html_code += "<img src=\""+ prop.picture + "\" />";
				html_code += "</figure></div>";
				html_code += "<div class=\"col-md-9 col-sm-6\"><button class=\"label label-default pull-right remove-button\" onclick=\"remove_prop("+ prop.id +")\">";
				html_code += "Remove<span class=\"glyphicon glyphicon-trash\"></span></button><h4>Title: "+ prop.title + "</h4>";
				html_code += "<p>Description: "+ prop.description + "</p><section><i class=\"glyphicon glyphicon-usd\"></i>" + prop.price;
				html_code += "<button class=\"btn btn-default btn-sm pull-right\" onclick=\"reserve_prop("+ prop.id +")\">Reserve</button></section></div></div></article>";
				html_code += "";
				$("#props").append(html_code);
				counter++;
			})
			if(counter ==0){
				$("#props").append("<h3> No props to display </h3>");
			}
		}
	});
}

function remove_prop(id){
	$.ajax({
		type : "PUT",
		url : "http://localhost:8080/admin_fan/remove_prop/" + id,
		dataType : "json",
		success : function(data) {
			display_props();
		}
	});
}


function reserve_prop(id){
	
}
