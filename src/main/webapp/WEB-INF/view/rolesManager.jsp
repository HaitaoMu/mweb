<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="template/header.jsp"></jsp:include>
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
					<h1 class="page-header">RolesManager</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div id="successAlert" class="alert alert-success" hidden="true"></div>
			<div id="errorAlert" class="alert alert-danger" hidden="true"></div>
			<!-- /.row -->
			<!-- Operation url-->
			<%-- 				<c:url id="createUserUrl" value="createUser"/> --%>
			<%-- 				<c:url id="deleteUserUrl" value="deleteUser"/> --%>
			<!-- Operation url -->
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">
							 <button class="btn btn-success btn-normal" data-toggle="modal" data-target="#myModal">
					             Create Role
					         </button>
					</div>
					<!-- /.panel-heading -->

					<div class="panel-body">
						<div class="table-responsive">
							<table id="roleTable"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<c:forEach var="headValue" items="${tableHead}">
											<td align="center">${ headValue }</td>
										</c:forEach>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="role" items="${roles}">
										<tr id="${role.roleId}">
											<td name="roleName" align="center">${role.roleName}</td>
											<td name="roleDescription" align="center">${role.roleDescription}</td>
											<td align="center">
												<button type="button"
													class="save btn btn-success btn-circle" name='<c:url value="/updateRole/${role.roleId}"/>' value="${role.roleId}">
													<i class="fa fa-save"> </i>
												</button>
												<button type="button"
													class="edit btn btn-success btn-circle" value="${role.roleId}">
													<i class="fa fa-edit"> </i>
												</button>
												<button type="button" class="delete btn btn-warning btn-circle" name='<c:url value="/deleteRole/${role.roleId}"/>' value="${role.roleId}">
													<i class="fa fa-times"> </i>
												</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 		@formatter:on -->
		<!-- /#page-wrapper -->
         <!-- Button trigger modal -->
         
         <!-- Modal -->
         <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
             <div class="modal-dialog">
                 <div class="modal-content">
                     <div class="modal-header">
                         <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                         <h4 class="modal-title" id="myModalLabel">Create Role</h4>
                     </div>
                     <div class="modal-body">
                  		  <form role="form">
                                     <div class="form-group has-warning">
                                         <input type="text" class="form-control" id="roleName" placeholder="Role Name">
                                     </div>
                                     <div class="form-group has-warning">
                                         <input type="text" class="form-control" id="roleDescription" placeholder="Role Description">
                                     </div>
                            </form>
                     </div>
                     <div class="modal-footer">
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button id="submitUserBtn" type="button" class="btn btn-primary" value="<c:url value="/saveRole"/>">Save Role</button>
                     </div>
                 </div>
                 <!-- /.modal-content -->
             </div>
             <!-- /.modal-dialog -->
         </div>

	</div>
	<!-- /#wrapper -->

	<!-- <script type="text/javascript" src="resources/js/userManager.js" /> -->
	<!-- DataTables JavaScript -->
	<script src="resources/js/plugins/confirm/jquery.confirm.min.js"></script>
	<script src="resources/js/plugins/jeditable/jquery.editable.min.js"></script>
	<script src="resources/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="resources/js/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/roleManager.js"></script>
</body>

</html>
