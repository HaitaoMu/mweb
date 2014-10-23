<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="template/header.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$('#menuTable').bootstrapTable(function(event) {
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
					//successAlert("Successfully create menu")
			    },
			    error: function(){
			    	$("#createMenuDlg").modal("hide");
				    //errorAlert("Create menu failure");
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
					data-pagination="true" data-page-list="[5, 10, 20, 50, 100, 200]"
					data-search="true" data-show-refresh="true"
					data-toolbar="#custom-toolbar"
					data-show-columns="true" data-query-params="queryParams">
					<thead>
						<tr>
							<th data-field="name" data-align="center">Menu Name</th>
							<th data-field="menuUrl" data-align="right" data-sortable="true">Menu
								URL</th>
							<th data-field="menuIcon" data-align="right" data-sortable="true">Menu
								ICON</th>
							<th data-field="menuDescription" data-align="center"
								data-sortable="true">Menu Description</th>
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
