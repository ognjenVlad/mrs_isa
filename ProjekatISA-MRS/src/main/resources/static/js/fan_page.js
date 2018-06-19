display_ads();
fill_history();
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

		var user = JSON.parse(localStorage.getItem('user'));
		
		var d = JSON.stringify({
			"title" : $("#ad_title").val(),
			"description" : $("#ad_description").val(),
			"exp_date" : date_val.getMonth() + "/" + date_val.getDate() +  "/" + date_val.getFullYear(),
			"user_email" :user.email,
			"picture" : ad_img_src
		})


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
			"amount" : $("#prop_amount").val(),
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

	$("#form_edit_prop").submit(function(event) {
		event.preventDefault();
		var d = JSON.stringify({
			"title" : $("#prop_title_edit").val(),
			"description" : $("#prop_description_edit").val(),
			"price" : $("#prop_price_edit").val(),
			"amount" : $("#prop_amount_edit").val(),
			"id" : $("#prop_id_edit").val(),
			"picture" : $("#prop_picture_edit").val()
		})

		console.log(d);

		$.post({
			url : "http://localhost:8080/admin_fan/add_prop",
			data : d,
			contentType : "application/json",
			success : function(data) {
				$('#modal_edit_prop').modal('hide')
				display_props();
			}
		});
	});

	$("#form_edit_bid").submit(function(event) {
		event.preventDefault();
		var ad_id = $("#bid_ad_id_edit").val();
		var d = JSON.stringify({
			"value" : $("#bid_value_edit").val(),
			"id" : $("#bid_id_edit").val(),
			"user" : $("#bid_user_edit").val(),
		})

		console.log(d);

		$.post({
			url : "http://localhost:8080/admin_fan/add_ad_bid/" + ad_id,
			data : d,
			contentType : "application/json",
			success : function(data) {
				$('#modal_edit_prop').modal('hide')
				display_props();
			}
		});
	});
});

function fillHistory(){
	var user = JSON.parse(localStorage.getItem('user'));
	if(user != null){
		$("#histroy").empty();
		$.each(user.history,function(index,notification){
			$("#history").append("<div class=\"notification\">"+ notification +"</div>");
		})
	}
}


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

function add_bid(ad_id){
	var user = JSON.parse(localStorage.getItem('user'));

	var d = JSON.stringify({
		"value" : $("#ad_amount_" + ad_id).val(),
		"user" : user.email
	})
	
	console.log(d);
	
	$.ajax({
		type : 'POST',
		url : "http://localhost:8080/admin_fan/add_ad_bid/" + ad_id,
		dataType : "json",
		data : d,
		contentType : "application/json",
		success : function(data) {
			alert(data.message);
		}
	});
}

function fill_bid_window(ad_id,id){
	$.ajax({
		type : "GET",
		url : "http://localhost:8080/admin_fan/get_bid/" + id,
		dataType : "json",
		success : function(data) {
			$("#bid_value_edit").val(data.obj.value);
			$("#bid_user_edit").val(data.obj.user);
			$("#bid_id_edit").val(id);
			$("#bid_ad_id_edit").val(ad_id);
		}
	});
}

function remove_bid(ad_id,id){
	$.ajax({
		type : "PUT",
		url : "http://localhost:8080/admin_fan/remove_bid/" + ad_id + "/" + id,
		dataType : "json",
		success : function(data) {
			display_props();
		}
	});
}

function display_ads() {
	$("#ads").empty();
	$("#my_ads").empty();
	var user = JSON.parse(localStorage.getItem('user'));
	var counter = 0;
	var counter_my_ads = 0;
	var have_bid = false;
	$.ajax({
		type : "GET",
		url : "http://localhost:8080/admin_fan/get_ads",
		dataType : "json",
		success : function(data) {
			var html_code;
			$("#post_container").empty();
			$.each(data.obj,function(index, ad) {
				have_bid = false;
				html_code = "<div><article><div class=\"row\"><div class=\"col-sm-6 col-md-3\"><figure>";
				html_code += "<img src=\""+ ad.picture + "\" />";
				html_code += "</figure></div>";
				html_code += "<div class=\"col-md-9 col-sm-6\"><span class=\"label label-default pull-right\"><i class=\"glyphicon glyphicon-inbox\"></i> 0</span>";
				html_code += "<h4>Title: "+ ad.title + "</h4>";
				html_code += "<p>Description: "+ ad.description + "</p><section><i class=\"glyphicon glyphicon-user\"></i>" + ad.user_email + "<i class=\"glyphicon glyphicon-calendar\">";
				html_code += "</i>" + ad.exp_date + "<button class=\"btn pull-right\" data-toggle=\"collapse\" data-target=\"#ad_bids_"+ ad.id + "\" aria-expanded=\"false\"";
				html_code += "aria-controls=\"ad_bids_" + ad.id + "\"> Show/Hide bids </button></section></div></div></article>";
				html_code += "<div class=\"collapse\" id=\"ad_bids_"+ ad.id +"\"><div class=\"card card-body\">";
				console.log(ad);

				$.each(ad.bids,function(index2, bid){
					html_code += "<div><span>User &lt"+ bid.user + "&gt offered:"+ bid.value +" </span>";
					if(user != null){
						if(bid.user == user.email){
							have_bid = true;
							html_code += "<button class=\"btn btn-danger\" onclick=\"remove_bid("+ ad.id + " , " + bid.id +")\">";
							html_code += "Remove <i class=\"glyphicon glyphicon-trash\"></i></button><button class=\"btn btn-dark\" data-toggle=\"modal\" data-target=\"#modal_edit_bid\"";
							html_code += " onclick=\"fill_bid_window("+ ad.id + " , " + bid.id +")\">Edit <i class=\"glyphicon glyphicon-pencil\"></i></button>";
						}else if(user.email == ad.user_email){
							html_code += "<button class=\"btn btn-success\" onclick=\"accept_bid_offer("+ ad.id + " , " + bid.id +")\">";
							html_code += "Accept offer</button>";
						}					
					}
					html_code += "</div>";
				})
				html_code += "</div></div>";
				if(user != null){
					if(have_bid == false && user.user_type == "user" && user.email != ad.user_email){
						html_code += "<div class=\"input-group user\"><input class= \"form-control width100\" id=\"ad_amount_" + ad.id +"\" type=\"number\"/ placeholder=\"Bid amount\">";
						html_code += "<span class=\"input-group-btn\"><button class=\"btn\" onclick=\"add_bid("+ ad.id +")\">Add bid</button></span></div>";					
					}
				}
				html_code += "</div>"
				
				if(user != null){
					if(user.email != ad.user_email){
						$("#ads").append(html_code);
						counter++;
					}else{
						$("#my_ads").append(html_code);
						counter_my_ads++;
					}
				}else{
					$("#ads").append(html_code);
					counter++;
				}
			})

			if(counter ==0){
				$("#ads").append("<h3> No ads to display </h3>");
			}
			if(user == null){
				$("#my_ads").append("<h3> <a href=\"signin.html\"> Log in to see your ads</a> </h3>");
			}else{
				if(counter_my_ads == 0){
					$("#my_ads").append("<h3> You still have any ads <h3>");
				}
			}
		}
	});
}

function accept_bid_offer(ad_id,bid_id){
	$.ajax({
		type : "PUT",
		url : "http://localhost:8080/admin_fan/choose_bid/" + ad_id + "/" + bid_id,
		dataType : "json",
		success : function(data) {
			alert(data.message);
			display_ads();
		}
	});
	
}

function fill_prop_window(id){
	$.ajax({
		type : "GET",
		url : "http://localhost:8080/admin_fan/get_prop/" + id,
		dataType : "json",
		success : function(data) {
			$("#prop_title_edit").val(data.obj.title);
			$("#prop_id_edit").val(id);
			$("#prop_description_edit").val(data.obj.description);
			$("#prop_price_edit").val(data.obj.price);
			$("#prop_amount_edit").val(data.obj.amount);
			$("#prop_picture_edit").val(data.obj.picture);
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
				html_code += "</figure></div><div class=\"col-md-9 col-sm-6\"><button class=\"btn pull-right btn-danger\" onclick=\"remove_prop("+ prop.id +")\">";
				html_code += "Remove <i class=\"glyphicon glyphicon-trash\"></i></button>";
				html_code += "<button class=\"btn pull-right btn-dark\" data-toggle=\"modal\" data-target=\"#modal_edit_prop\" onclick=\"fill_prop_window("+ prop.id +")\">Edit <i class=\"glyphicon glyphicon-pencil\"></i></button>";
				html_code += "<h4>Title: "+ prop.title + "</h4><p>Description: "+ prop.description + "</p><section><i class=\"glyphicon glyphicon-usd\"></i>" + prop.price;
				html_code += "<p>Amount : "+ prop.amount + "</p><button class=\"btn btn-default btn-sm pull-right\" onclick=\"reserve_prop("+ prop.id +")\">Reserve</button>";
				html_code += "<input id=\"prop_amount_"+ prop.id +"\" class=\"pull-right\" type=\"number\"/ placeholder=\"Amount\" min=\"1\" max=\"" + prop.amount + "\"></section></div></div></article>";
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
	var user = JSON.parse(localStorage.getItem('user'));

	$.ajax({
		type : "PUT",
		url : "http://localhost:8080/admin_fan/update_prop/" + id + "/" + $("#prop_amount_" + id).val() + "/" + user.email,
		dataType : "json",
		success : function(data) {
			if(data.message == "Not enough props"){
				alert("Not enough props");
			}
			display_props();
		}
	});	
}
