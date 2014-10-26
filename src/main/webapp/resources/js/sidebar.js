$(function(){
	$.ajax({
	    type: "post",
	    url:$("#sidemenu").attr("value"), 
	    success: function(result) {
	    	$("#sidemenu").html(result);
	    	$("#sidemenu").metisMenu();
	    },
	    error: function(){
	    }
	}); 
});