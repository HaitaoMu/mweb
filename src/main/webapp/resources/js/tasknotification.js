$(function(){
//	var stompClient = null;
//
//	function connect() {
//		var socket = new SockJS('/mweb/tasknotification');
//		stompClient = Stomp.over(socket);
//		stompClient.connect({}, function(frame) {
//			stompClient.subscribe("/topic/tasknotification", function(message) {
//		
//			});
//		});
//	}
	
//	var tasks="<li> <a href='#'> <div>  <p>  <strong>dataTransferJob-0</strong> <span class='pull-right text-muted'>100% Complete</span> <div class='progress progress-striped active'> <div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100' style='width:100%'> <span class='sr-only'>100% Complete</span> </div> </div> </div> </a> </li> <li class='divider'></li><li><a class='text-center' href='#'> <strong>See All Tasks</strong>  <i class='fa fa-angle-right'></i></a></li>"
//	$("#taskList").html(tasks);
//	$("#task").removeClass("dropdown");
//	$("#task").addClass("dropdown open");
	
	var socket = new SockJS('/mweb/tasknotification');
	var client = Stomp.over(socket);
//
	client.connect({}, function(frame) {

		
		client.subscribe("/topic/tasknotification", function(message) {
			var tasks = message.body;
			$("#taskList").html(tasks);
			$("#task").removeClass("dropdown");
			$("#task").addClass("dropdown open");
			if(null!=$("#sapTable"))
			{
//				$('#sapTable').bootstrapTable("load",{ url:"/mweb/pageSapEntity"});
//				 $table = $('#sapTable').bootstrapTable();
//				 $table.bootstrapTable('refresh', {
//                    url: $("sapTable").attr("data-url")
//                });
			}
		});
	});
	
//	client.disconnect(function() {
//	    console.log("See you next time!");
//	});
});


