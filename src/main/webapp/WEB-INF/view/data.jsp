<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <jsp:include page="template/header.jsp"></jsp:include>
<!--   <script type="text/javascript" src="resources/js/jquery.js" /></script> -->
  <script type="text/javascript" src="resources/js/highcharts.js" /></script>
  <script type="text/javascript" src="resources/js/application.js" /></script>
  <script type="text/javascript" src="resources/js/stomp.js" /></script>
  <script type="text/javascript" src="resources/js/sockjs-0.3.4.js" /></script>
  <script type="text/javascript">
     $(function(){
    		$("#sidemenu").metisMenu(); 
     });
  </script>
  
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
                 <div class="col-lg-12">
                    <div id="randomDataChart"></div>
                </div>
            </div>
            <div class="row">
            	<div class="col-lg-12">
            		
					        <ul id="sidemenu">
					           <li> <a class="active" href="/mweb/home"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
						           <li><a href="menu"><i class="fa fa-cogs fa-fw"></i>Menu Manager</a></li>
						           <li><a href="listRole"><i class="fa fa-file fa-fw"></i>Role Manager</a></li>
						           <li><a href="chart"><i class="fa fa-cogs fa-fw"></i>Chart</a></li>
						           <li><a href="dbConfigIndex"><i class="fa fa-files-o fa-fw"></i>Database Manager</a></li>
						           <li><a href="listUser"><i class="fa fa-file fa-fw"></i>User Manager</a></li>
						           <li><a href="#"><i class="fa fa-cogs fa-fw"></i>Interface Manager<span class="fa arrow"></span></a>
							           <ul class="nav nav-second-level">
								           <li><a href="sapIndex"><i class="fa fa-cogs fa-fw"></i>Sap Interface<span class="fa arrow"></span></a>
									           <ul class="nav nav-third-level">
									             <li><a href="/mweb/SAPExpression"><i class="Sap"></i>SAP Expression Interface</a></li>
									          </ul>
								          </li>
							          </ul>
						          </li>
					        </ul>
					  
            	</div>
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

</body>

</html>