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
		 <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
		    <jsp:include page="template/navbar.jsp"/>
        <!-- Navigation -->
        	<jsp:include page="template/sidebar.jsp"/>
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Dashboard</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
               
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

</body>

</html>
