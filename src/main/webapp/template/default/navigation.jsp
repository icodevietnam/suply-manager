<%@page import="com.icoding.service.UserService"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="org.springframework.security.core.userdetails.UserDetails"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@page import="com.icoding.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav metismenu" id="side-menu">
			<li class="nav-header">
				<div class="dropdown profile-element">
					<span>
					<img alt="image" class="img-circle"
						src="<c:url value='/resources/default/img/profile_small.jpg'/>" />
					</span> 
					<a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
					<span class="clear">
						<span class="block m-t-xs"> <strong
								class="font-bold"><%=request.getUserPrincipal().getName()%></strong>
						</span>
						<span class="departmentCur text-muted text-xs block">Phòng: Art Director</span>
					</span>
					</a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li><a href="<c:url value='/admin/profile/'/>">Hồ Sơ Cá Nhân</a></li>
						<li><a href="<c:url value='/admin/profile/change-password'/>">Thay đổi mật khẩu</a></li>
						<li class="divider"></li>
						<li><a href="<c:url value='/j_spring_security_logout'/>">Thoát</a></li>
					</ul>
				</div>
				<div class="logo-element">IN+</div>
			</li>
			<li class="suply"><a href="index.html"><i class="fa fa-th-large"></i> <span
				class="nav-label">Quản lý người dùng</span> <span class="fa arrow"></span></a>
				<ul class="nav nav-second-level collapse">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="<c:url value='/admin/user/list'/>">Quản lý người dùng</a></li>
					<li><a href="<c:url value='/admin/department/list'/>">Phòng ban</a></li>
					<li><a href="<c:url value='/admin/role/list'/>">Quyền</a></li>
				</sec:authorize>
				<li><a href="<c:url value='/admin/customer/list'/>">Quản lý khách hàng</a></li>
				</ul>
			</li>
			<li class="suply"><a href="index.html"><i class="fa fa-th-large"></i> <span
				class="nav-label">Quản lý Hồ Sơ</span> <span class="fa arrow"></span></a>
				<ul class="nav nav-second-level collapse">
					<li><a href="<c:url value='/admin/brief/list'/>">Quản lý Hồ Sơ</a></li>
					<li><a href="<c:url value='/admin/briefType/list'/>">Quản lý Loại Hồ Sơ</a></li>
					<li><a href="<c:url value='/admin/stock/list'/>">Quản lý Kho</a></li>
				</ul>
			</li>
			<li class="suply"><a href="index.html"><i class="fa fa-th-large"></i> <span
				class="nav-label">Quản lý Đơn từ</span> <span class="fa arrow"></span></a>
				<ul class="nav nav-second-level collapse">
					<li><a href="<c:url value='/admin/document/list'/>">Quản lý Đơn từ</a></li>
					<li><a href="<c:url value='/admin/documentType/list'/>">Quản lý Loại Đơn từ</a></li>
				</ul>
			</li>
		</ul>
	</div>
	<script src="<c:url value='/resources/default/js/navigation.js'/>"></script>
</nav>