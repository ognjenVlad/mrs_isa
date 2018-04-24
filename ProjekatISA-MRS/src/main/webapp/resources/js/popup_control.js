/**
 * 
 */

	// Korsiti cancel button
	function closePopups(){
		$("#main_page").css('pointer-events','auto');
	    $("#main_page").css('filter', 'none');
		$("#main_page").css('cursor','auto');
		$("#popup_cinema_add").css('display', "none");
	};
	
	function displayCinemaAdd(){
		closePopups();
		$("#main_page").css('pointer-events','none');
	    $("#main_page").css('filter', 'opacity(35%)');
		$("#main_page").css('cursor','default');
		$("#popup_cinema_add").css('display', "inline");
	};