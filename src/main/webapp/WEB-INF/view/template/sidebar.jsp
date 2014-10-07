<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
            <li class="sidebar-search">
                <div class="input-group custom-search-form">
                    <input type="text" class="form-control" placeholder="Search...">
                    <span class="input-group-btn">
                    <button class="btn btn-default" type="button">
                        <i class="fa fa-search"></i>
                    </button>
                </span>
                </div>
                <!-- /input-group -->
            </li>
            <li>
                <a class="active" href='<c:url value="/home" />'><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
            </li>
             <li>
                 <a href='<c:url value="/listUser" />'><i class="fa fa-cogs fa-fw"></i> Interface Manager<span class="fa arrow"></span></a>
			     <ul class="nav nav-second-level">
                     <li>
                         <a href="<c:url value="/sapIndex" />">SAP Interface</a>
                     </li>
                 </ul>
            </li>
            <li>
                 <a href='<c:url value="/listUser" />'><i class="fa fa-files-o fa-fw"></i> User Manager</span></a>
            </li>
            <li>
             	 <a  href='<c:url value="/chart" />'><i class="fa fa-star fa-fw"></i> Chart</a>
            </li>
        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->
</nav>
