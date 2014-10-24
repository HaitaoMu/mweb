<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="template/header.jsp"></jsp:include>
<script src="resources/js/plugins/confirm/jquery.confirm.min.js"></script>
<script type="text/javascript">
	$(function() {
		 $table = $('#menuTable').bootstrapTable(function(event) {
			console.log("event trigger");
		});
		$("#createMenuBtn").click(function(){
			$("#createMenuDlg").modal("show");
		});
		$("#submitMenuBtn").click(function(event){
			var targetId = event.currentTarget.value;
			var data = {
					parentId:$("#menuId").find("option:selected").attr("id"),
					menuName:$("#menuName").val(),
					menuUrl:$("#menuUrl").val(),
					menuIcon:$("#menuIcon").val(),
					menuDescription:$("#menuDescription").val()
			};

			$.ajax({
			    type: "post",
			    url: event.currentTarget.value, 
			    data:data,
			    success: function(result) {
			    	$("#createMenuDlg").modal("hide");
			    	console.log($table);
			    	$table.bootstrapTable('refresh');
			    },
			    error: function(){
			    	$("#createMenuDlg").modal("hide");
			    }
			}); 
		});
		
		
		
		
	});
	
	function queryParams(params) {
		return {
			pageSize : params.pageSize,
			pageNum : params.pageNumber,
			search : params.searchText,
			name : params.sortName,
			order : params.sortOrder
		};
	}

	 function operateFormatter(value, row, index) {
	        return [
			'<button type="button" class="edit btn btn-success btn-circle" href="javascript:void(0)" value="<c:url value="/updateMenu"/>" title="Edit">',
				'<i class="fa fa-edit"> </i>',
			'</button>',
			'<button type="button" class="delete btn btn-warning btn-circle" href="javascript:void(0)" value="<c:url value="/deleteMenu"/>" title="Remove">',
				'<i class="fa fa-times"> </i>',
			'</button>'
	        ].join('');
	    }
	 
	 window.operateEvents = {
     'click .edit': function (e, value, row, index) {
         alert('You click edit icon, row: ' + JSON.stringify(row));
         console.log(value, row, index);
     },
     'click .delete': function (e, value, row, index) {
    	 $table =  $('#menuTable').bootstrapTable();
         console.log(value, row, index);
         $.confirm({
			    text: "Are you sure you want to delete this menu?",
			    confirm: function() {
					$.ajax({
					    type: "post",
					    url: e.currentTarget.value, //your valid url
					    data:{menuId:row.id},
					    success: function(result) {
					    	 $table.bootstrapTable('refresh');
					    },
					    error: function(){
					    }
					});
			    },
			    cancel: function(button) {
			       console.log("cancel jquery dialog");
			    }
			});
     }
	};
	
	
</script>
</head>

<body>

	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<jsp:include page="template/navbar.jsp" />
			<!-- Navigation -->
			<jsp:include page="template/sidebar.jsp" />
		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Menu Manager</h1>
				</div>
			</div>
			<div class="row">
				<div id="custom-toolbar">
					<div class="form-inline" role="form">
						<button id="createMenuBtn" type="button" class="btn btn-success">Create Menu</button>
					</div>
				</div>
				<table id="menuTable" data-url="<c:url value="/pageMenuConfig"/>"
					data-height="400" data-side-pagination="server"
					data-pagination="true" data-page-list="[10,20, 50, 100, 200]"
					data-search="true" data-show-refresh="true"
					data-toolbar="#custom-toolbar"
					data-show-columns="true" data-query-params="queryParams">
					<thead>
						<tr>
							<th data-field="name" data-align="center">Menu Name</th>
							<th data-field="menuUrl" data-align="right" data-sortable="true">Menu URL</th>
							<th data-field="menuIcon" data-align="right" data-sortable="true">Menu ICON</th>
							<th data-field="menuDescription" data-align="center" data-sortable="true">Menu Description</th>
							<th data-field="operate" data-formatter="operateFormatter" data-events="operateEvents" >Operation</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<!-- /#page-wrapper -->
		
		<!-- create menu dialog -->
		<div class="modal fade" id="createMenuDlg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
             <div class="modal-dialog">
                 <div class="modal-content">
                     <div class="modal-header">
                         <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                         <h4 class="modal-title" id="myModalLabel">Create Menu</h4>
                     </div>
                     <div class="modal-body">
                  		  <form role="form">
                  		          <div class="form-group has-warning">
	                  		          <select id="menuId" class="form-control">
										  <c:forEach var="menu" items="${menus}">
										      <option id="${menu.id}">${menu.name}</option>
										  </c:forEach>
									  </select>
								  </div>
                                  <div class="form-group has-warning">
                                      <input type="text" class="form-control" id="menuName" placeholder="Menu Name">
                                  </div>
                                  <div class="form-group has-warning">
                                      <input type="text" class="form-control" id="menuUrl" placeholder="Menu Url">
                                  </div>
                                  <div class="form-group has-warning">
                                      <input type="text" class="form-control" id="menuIcon" placeholder="Menu Icon">
                                  </div>
                                  <div class="form-group has-warning">
                                      <input type="text" class="form-control" id="menuDescription" placeholder="Menu Description">
                                  </div>
                            </form>
                     </div>
                     <div class="modal-footer">
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button id="submitMenuBtn" type="button" class="btn btn-primary" value="<c:url value="/saveMenu"/>">Save Menu</button>
                     </div>
                 </div>
                 <!-- /.modal-content -->
             </div>
             <!-- /.modal-dialog -->
         </div>
		<!-- end create menu dialog -->

	</div>
	<!-- /#wrapper -->

</body>

</html>
