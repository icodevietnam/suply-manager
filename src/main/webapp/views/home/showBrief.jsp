<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="homeTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div class="col-lg-11">
				<c:forEach var="brief" items="${listBrief}">
					<div class='brief' style="border-bottem: 1px dotted black;">
						<h6>Nội dung: ${brief.content}</h6>
						<h6>Phòng: ${brief.department.name}</h6>
						<h6>Tên khách hàng: ${brief.customer.name}</h6>
						<h6>Mã khách hàng: ${brief.customer.code}</h6>
						<h6>Kho: ${brief.stock.name}</h6>
					</div>
					<c:forEach var="file" items="${brief.listImage}" >
						<a href="${file.absolutelyPath}" >
						<img style='width:140px;margin-left:2px;margin-bottom:5px;' src='${file.absolutelyPath}' />
						</a>
					</c:forEach>
				</c:forEach>
			</div>
		</div>
		<script src="<c:url value='/resources/default/js/page/homeIndex.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>