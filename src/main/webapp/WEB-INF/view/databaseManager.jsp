<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <jsp:include page="template/header.jsp"></jsp:include>
  <script type="text/javascript">
 	$(function(){
  		$('#databaseTable').bootstrapTable(function(event)
  		{
			console.log("event trigger");
  		});
  	}); 
  	
  	function queryParams(params) {
        return {
            pageSize: params.pageSize,
            pageNum: params.pageNumber,
            search: params.searchText,
            name: params.sortName,
            order: params.sortOrder
        };
    }
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
                    <h1 class="page-header">Sap Interface Manager</h1>
                </div>
            </div>
            <div class="row">
               	<table id="databaseTable" data-url="<c:url value="/pageDbConfig"/>" data-height="400" data-side-pagination="server" data-pagination="true" data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true" data-show-refresh="true" data-show-columns="true" data-query-params="queryParams">
				    <thead>
				    <tr>
				        <th data-field="dbName" data-align="center">Database Name</th>
				        <th data-field="dbType" data-align="right" data-sortable="true">Database Type</th>
				        <th data-field="driver" data-align="right" data-sortable="true">Database Driver</th>
				        <th data-field="url" data-align="center" data-sortable="true">URL</th>
				        <th data-field="username" data-align="center" data-sortable="true">User Name</th>
				    </tr>
				    </thead>
				</table>
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

</body>

</html>
