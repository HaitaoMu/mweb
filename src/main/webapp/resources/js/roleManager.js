$(function() {
			
			function successAlert(information)
			{
				$("#successAlert").show();
				$("#successAlert").html(information);
				$("#successAlert").addClass('animated zoomOutUp');
				$('#successAlert').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
					$("#successAlert").hide();
					$("#successAlert").removeClass('animated zoomOutUp');
				});
				
			}
			function errorAlert(information)
			{
				$("#errorAlert").show();
				$("#errorAlert").html(information);
				$("#errorAlert").addClass('animated bounceOutUp');
				$('#errorAlert').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
					$("#errorAlert").hide();
					$("#errorAlert").removeClass('animated bounceOutUp');
				});
				
			}

			$("#submitUserBtn").click(function(event){
				var data = {
						roleName:$("#roleName").val(),
						roleDescription:$("#roleDescription").val()
				}
			    $.ajax({
				    type: "post",
				    url: event.currentTarget.value, 
				    data:data,
				    success: function(result) {
				    	successAlert("Create Role Success");
						$("#myModal").modal('hide');
				    },
				    error: function(){
				    	errorAlert("Create Role Failure");
						$("#myModal").modal('hide');
				    }
				});  
			});
			
			
			$(".save").click(function(event) {
				console.log("save role");
				updateUser(event);
			});
			
			function updateUser(event){
				var targetId = event.currentTarget.value;
				var data = {
						roleName:$("#"+targetId+" td[name='roleName']").html(),
						roleDescription:$("#"+targetId+" td[name='roleDescription']").html()
				};

				$.ajax({
				    type: "post",
				    url: event.currentTarget.name, 
				    data:data,
				    success: function(result) {
					    console.log("update role success");
					    $('#roleTable tbody tr[id='+targetId+'] td').editable('close');
						$('#roleTable tbody tr[id='+targetId+']').removeClass("success");
						successAlert("Update Success");
				    },
				    error: function(){
				    	console.log("update user failure");
				    	 $('#roleTable tbody tr[id='+targetId+'] td').editable('close');
						 $('#roleTable tbody tr[id='+targetId+']').removeClass("success");
						 errorAlert("Update Failure");
				    }
				}); 
			};

			$(".edit").click(function(event) {
				var targetId = event.currentTarget.value;
				$('#roleTable tbody tr[id='+targetId+'] td[name="roleName"]').editable();
				$('#roleTable tbody tr[id='+targetId+'] td[name="roleDescription"]').editable();
				$('#roleTable tbody tr[id='+targetId+']').addClass("success");
			});
			
			$(".delete").click(function(event) {
				
				$.confirm({
				    text: "Are you sure you want to delete this role?",
				    confirm: function() {
				    	var targetId = event.currentTarget.value;
						$.ajax({
						    type: "post",
						    url: event.currentTarget.name, //your valid url
						    contentType: "application/json", //this is required for spring 3 - ajax to work (at least for me)
						    success: function(result) {
							    $("tr[id="+targetId+"]").remove();
							    successAlert("Delete Success");
						    },
						    error: function(){
						    	errorAlert("Delete Failure");
						    }
						});
				    },
				    cancel: function(button) {
				       console.log("cancel jquery dialog");
				    }
				});
				
			});

		});