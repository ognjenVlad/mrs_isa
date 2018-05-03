/**
 * 
 */

	// Korsiti cancel button
	function closePopups(){
		$("#main_page").css('pointer-events','auto');
	    $("#main_page").css('filter', 'none');
		$("#main_page").css('cursor','auto');
		$("#popup_cinema_add").css('display', "none");
		$("#popup_admin_add").css('display', "none");
	};
	
	function displayCinemaAdd(){
		closePopups();
		$("#main_page").css('pointer-events','none');
	    $("#main_page").css('filter', 'opacity(35%)');
		$("#main_page").css('cursor','default');
		$("#popup_cinema_add").css('pointer-events','auto');
		$("#popup_cinema_add").css('cursor','auto');
		$("#popup_cinema_add").css('display', "inline");
		$("#popup_add_hall").css('display', "none");
	};
	
	function displayAdminAdd(){
		closePopups();
		$("#main_page").css('pointer-events','none');
	    $("#main_page").css('filter', 'opacity(35%)');
		$("#main_page").css('cursor','default');
		$("#popup_admin_add").css('display', "inline");
	};

	function displayHallAdd(){
		$("#popup_cinema_add").css('pointer-events','none');
		$("#popup_cinema_add").css('cursor','default');
		$("#popup_add_hall").css('display', "inline");
	};