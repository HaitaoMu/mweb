$(function(){
	$("#side-menu").metisMenu({toggle: false});
	$.ajax({
	    type: "post",
	    url:$("#side-menu").attr("value"), 
	    success: function(result) {
	    	$("#side-menu").html(result);
	    	 $('#side-menu').metisMenu({
	             doubleTapToGo: true
	           });
	    },
	    error: function(){
	    }
	}); 
});