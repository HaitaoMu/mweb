<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>
	<c:url value="/hello" var="messageUrl" />
	<c:url value="/fileupload" var="formUrl" />
	<a href="${messageUrl}">Click to enter</a>

	<h1>Please upload a file</h1>
	<form method="post" action="${formUrl}" enctype="multipart/form-data">
		 <input type="text" name="name" />
		 <input type="file" name="file" /> 
		 <input type="submit" />
	</form>
</body>
</html>
