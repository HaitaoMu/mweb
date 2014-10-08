$(function() {

	var socket = new SockJS('/mweb/tasknotification');
	var client = Stomp.over(socket);

	client.connect({}, function(frame) {

		client.subscribe("/topic/tasknotification", function(message) {
			 var task = JSON.parse(message.body);
//			 $("#taskList").html("");
			 $("#taskList").html(task.messsage);
//			 $("#task").dropdown();
			 
//			 $("#taskList").html("<li> <a href='#'><div><p><strong>SAP IMPORT</strong> <span class='pull-right text-muted'>null</span> <div class='progress progress-striped active'> <div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='57' aria-valuemin='0' aria-valuemax='100' style='width:57%'> <span class='sr-only'>null</span> </div> </div> </div> </a> </li> <li class='divider'></li><li><a class='text-center' href='#'> <strong>See All Tasks</strong>  <i class='fa fa-angle-right'></i></a></li>");
			 $("#task").removeClass("dropdown");
			 $("#task").addClass("dropdown open");
		});

	});
});