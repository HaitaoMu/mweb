$(function(){
	 var token = $("meta[name='_csrf']").attr("content");
	  var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
	    type: "post",
	    data:{_csrf:token},
	    url:$("#sidemenu").attr("value"), 
	    success: function(result) {
	    	$("#sidemenu").html(result);
	    	$("#sidemenu").metisMenu();
	    },
	    error: function(){
	    }
	});	
});