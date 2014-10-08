$(function() {

	var socket = new SockJS('/mweb/tasknotification');
	var client = Stomp.over(socket);

	client.connect({}, function(frame) {

		client.subscribe("/topic/tasknotification", function(message) {
			 var task = JSON.parse(message.body);
			 $("#taskList").html(task.message);
			 $("#task").dropdown();
		});

	});
});