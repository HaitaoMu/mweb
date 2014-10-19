<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="template/header.jsp"></jsp:include>
<jsp:include page="template/notification.jsp"></jsp:include>

<script type="text/javascript">
	$(function() {
	 	$('#sapTable').bootstrapTable(function(event) {
			console.log("event trigger");
		}); 
		$("#imediateImportBtn").click(function(event) {

			$.ajax({
			    type: "post",
				url : event.currentTarget.value,
				success : function(data) {
					if("success" == data.status && null!=data.message)
					{
						$(".modal-body").html(data.message);
						$("#myModal").modal("show");
					}
				}
			});
		});
		$("#submitJobBtn").click(function(event){
			$.ajax({
			    type: "post",
				url : event.currentTarget.value,
				success : function(data) {
					if("success" == data.status)
					{
						$("#myModal").modal("hide");
					}
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
					<h1 class="page-header">Sap Interface Manager</h1>
				</div>
			</div>
			<div class="row">
				<div id="custom-toolbar">
					<div class="form-inline" role="form">
						<button id="imediateImportBtn" type="button"
							value="<c:url value="/sapImportData/lock"/>" class="btn btn-success">Imediate
							Import</button>
						<button type="button" class="btn btn-success">Schedule
							Import</button>
						<button type="button" class="btn btn-success">Export As
							Excel</button>
						<button type="button" class="btn btn-success">Release
							Data</button>
					</div>
				</div>
				<table id="sapTable" data-url="<c:url value="/pageSapEntity"/>"
					data-height="400" data-side-pagination="server"
					data-pagination="true" data-page-list="[5, 10, 20, 50, 100, 200]"
					data-toolbar="#custom-toolbar" data-search="true"
					data-show-refresh="true" data-show-columns="true"
					data-query-params="queryParams">
					<thead>
						<tr>
							<th data-field="userId" data-align="center">USER ID</th>
							<th data-field="propertyCode" data-align="center">PROPERTY
								CODE</th>
							<th data-field="amount" data-align="right" data-sortable="true">AMOUNT</th>
							<th data-field="price" data-align="center" data-sortable="true">PRICE</th>
							<th data-field="volume" data-sortable="true">VOLUME</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<!-- /#page-wrapper -->
		
		      <!-- Modal -->
         <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
             <div class="modal-dialog">
                 <div class="modal-content">
                     <div class="modal-header">
                         <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                         <h4 class="modal-title" id="myModalLabel">Operation Result</h4>
                     </div>
                     <div class="modal-body">
                     </div>
                     <div class="modal-footer">
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button id="submitJobBtn" type="button" class="btn btn-primary" value=<c:url value="/sapImportData/Run"/>>Run Again</button>
                     </div>
                 </div>
                 <!-- /.modal-content -->
             </div>
             <!-- /.modal-dialog -->
         </div>
	</div>
	<!-- /#wrapper -->

</body>

</html>
