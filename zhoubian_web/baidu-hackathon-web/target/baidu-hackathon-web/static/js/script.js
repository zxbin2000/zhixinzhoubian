$(function(){
	
	var note = $('#note');
	var ts = new Date(2013, 11, 20, 19, 00, 00);
	
	if((new Date()) > ts){
		ts = ts - (new Date()).getTime();
	}

	$('#countdown').countdown({
		timestamp	: ts,
		callback	: function(days, hours, minutes, seconds){
			
			var message = "";
			
			message += days + " day" + ( days==1 ? '':'s' ) + ", ";
			message += hours + " hour" + ( hours==1 ? '':'s' ) + ", ";
			message += minutes + " minute" + ( minutes==1 ? '':'s' ) + " and ";
			message += seconds + " second" + ( seconds==1 ? '':'s' ) + " <br />";
			
			message += "Baidu Hackathon 7";
			
			note.html(message);
		}
	});
	
});
