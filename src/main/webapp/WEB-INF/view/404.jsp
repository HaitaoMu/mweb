<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="template/header.jsp"></jsp:include>
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
					<h1 class="page-header">404 Error</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="error-page">
						<h2 class="headline text-info">404</h2>
						<div class="error-content">
							<h3>
								<i class="fa fa-warning text-yellow"></i> Oops! Page not found.
							</h3>
							<p>We could not find the page you were looking for.
								Meanwhile, you may</p>
						</div>
						<!-- /.error-content -->
					</div>
					<!-- /.error-page -->
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

</body>

</html>
