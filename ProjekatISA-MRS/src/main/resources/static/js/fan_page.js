display_ads();
display_props();
var ad_img_src;
var prop_img_src;
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
			"exp_date" : date_val,
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
			console.log(data);
			if (data.message == "Success") {
				alert("Done!");
			} else {
				alert("Not done!");
			}
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
				html_code = "<article class=\"row\"><div class=\"col-md-2 col-sm-2 hidden-xs\"><figure class=\"thumbnail\">";
				html_code += "<img class=\"img-responsive\" src=\""+ ad.picture + "\" />";
				html_code += "<figcaption class=\"text-center\">" + "Default user"  + "</figcaption></figure></div>";
				html_code += "<div class=\"col-md-10 col-sm-10\"><div class=\"panel panel-default arrow left\"><div class=\"panel-body\">";
				html_code += "<header class=\"text-left\"><div class=\"comment-user\"><i class=\"fa fa-user center\"></i><b> Title: "+ ad.title + "</b></div>";
				html_code += "</header><div class=\"comment-post\"><p>Description: "+ ad.description + "</p></div></div></div></div></article>";
				html_code += "<input class=\"form-control\" type=\"number\"/ placeholder=\"Bid amount\"></button><button class=\"btn\" onclick=\"add_bid("+ ad.id +")\">Add bid</button>";
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
			$("#post_container").empty();
			$.each(data.obj,function(index, prop) {
				html_code = "<article class=\"row\"><div class=\"col-md-2 col-sm-2 hidden-xs\"><figure class=\"thumbnail\">";
				html_code += "<img class=\"img-responsive\" src=\""+ prop.picture + "\" />";
				html_code += "<figcaption class=\"text-center\">" + "Default user"  + "</figcaption></figure></div>";
				html_code += "<div class=\"col-md-10 col-sm-10\"><div class=\"panel panel-default arrow left\"><div class=\"panel-body\">";
				html_code += "<header class=\"text-left\"><div class=\"comment-user\"><i class=\"fa fa-user center\"></i><b> Title: "+ prop.title + "</b></div>";
				html_code += "</header><div class=\"comment-post\"><p>Description: "+ prop.description + "</p></div></div></div></div></article>";
				html_code += "</button><button class=\"btn\" onclick=\"reserve_prop("+ prop.id +")\">Reserve</button>";
				html_code += "</button><button class=\"btn btn-danger\" onclick=\"remove_prop("+ prop.id +")\">Remove</button>";
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
