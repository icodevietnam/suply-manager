<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="style.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>${title}</title>
	<link href="<c:url value='/resources/default/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/default/css/bootstrap-theme.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/default/font-awesome/css/font-awesome.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/default/css/plugins/toastr/toastr.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/default/css/animate.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/default/css/dataTables.bootstrap.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/default/css/bootstrap-select.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/default/css/datepicker.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/default/css/notepad.css'/>" rel="stylesheet">
    <script src="<c:url value='/resources/default/js/jquery-2.1.1.js'/>"></script>
    <script src="<c:url value='/resources/default/js/bootstrap-select.js'/>"></script>
    <script src="<c:url value='/resources/default/js/bootstrap-datepicker.js'/>"></script>
    <script src="<c:url value='/resources/default/js/jquery.dataTables.js'/>"></script>
    <script src="<c:url value='/resources/default/js/dataTables.bootstrap.js'/>"></script>
    <script src="<c:url value='/resources/default/js/jquery.validate.js'/>"></script>
</head>
<body>
	<div id="box">

	<div id="header"><h1>${title}</h1></div>
	<!--TOP NAVIGATION-->
	<tiles:insertAttribute name="navigation" />
	<!--MAIN CONTENT-->
	<div id="content">
		<tiles:insertAttribute name="body" />
	</div>
	<!--FOOTER-->
	<tiles:insertAttribute name="footer" />
</div>
<!-- Mainly scripts -->
    <script src="<c:url value='/resources/default/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resources/default/js/plugins/metisMenu/jquery.metisMenu.js'/>"></script>
    <script src="<c:url value='/resources/default/js/plugins/slimscroll/jquery.slimscroll.min.js'/>"></script>
    <!-- Custom and plugin javascript -->
    <script src="<c:url value='/resources/default/js/inspinia.js'/>"></script>
    <script src="<c:url value='/resources/default/js/plugins/pace/pace.min.js'/>"></script>
</body>
</html>