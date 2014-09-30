<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<jsp:include page="header.jsp"></jsp:include>
</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please Sign In</h3>
					</div>
					<div class="panel-body">
						<form role="form" action="<c:url value="/login" />" method="post">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="username"
										name="username" type="text" autofocus>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password"
										name="password" type="password" value="">
								</div>
								<div class="form-group">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
								</div>
								<div class="checkbox">
									<label> <input name="remember" type="checkbox"
										value="Remember Me">Remember Me
									</label>
								</div>
								<!-- Change this to a button or input when using this as a form -->
								<input type="submit" value="Login"
									class="btn btn-lg btn-success btn-block" />
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>
