<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="homeTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div class="col-lg-10">
				<form id="searchBrief" class="form-inline">
					<div class="form-group">
						<input type="text" class="input-sm form-control" name="customerName"
							id="customerName" placeholder="Tên Khách hàng">
					</div>
					<div class="form-group">
						<input type="email" class="input-sm form-control" id="customerEmail"
							name="customerEmail" placeholder="Email">
					</div>
					<button type="button" onclick="displayTable();" class="btn btn-sm btn-primary">Tìm
						Hồ Sơ</button>
				</form>
				<div style="margin-top: 20px;" class="table-responsive">
					<table id="tblItems"
						class="table table-bordered table-hover table-striped">
					</table>
				</div>
			</div>
		</div>
		<script
			src="<c:url value='/resources/default/js/page/homeIndex.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>