<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>${title}</title>

    <link href="<c:url value='/resources/default/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/default/css/font-awesome/css/font-awesome.css'/>" rel="stylesheet">

    <link href="<c:url value='/resources/default/css/animate.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/default/css/style.css'/>" rel="stylesheet">

</head>

<body class="gray-bg">


    <div class="middle-box text-center animated fadeInDown">
        <h1>${errorNumber}</h1>
        <h3 class="font-bold">${errorName}</h3>

        <div class="error-desc">
            ${message}
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="<c:url value='/resources/default/js/jquery-2.1.1.js'/>"></script>
    <script src="<c:url value='/resources/default/js/bootstrap.min.js'/>"></script>

</body>

</html>
