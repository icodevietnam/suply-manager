<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div class="col-lg-6">
				<div class="ibox">
					<div class="ibox-content">
						<a href="<c:url value='/admin/role/list'/>" class="btn-link">
							<h2>Quản lý Báo Cáo</h2>
						</a>
						<button data-toggle="modal" data-target="#newItem"class="btn btn-sm btn-primary">Tạo
							mới</button>
						<div class="table-responsive">
							<table id="tblDepartment"
								class="table table-bordered table-hover table-striped">
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="ibox">
					<div class="ibox-content">
						<a href="<c:url value='/admin/role/list'/>" class="btn-link">
							<h2>Quản lý Báo Cáo</h2>
						</a>
						<button data-toggle="modal" data-target="#newItem"class="btn btn-sm btn-primary">Tạo
							mới</button>
						<div class="table-responsive">
							<table id="tblDepartment"
								class="table table-bordered table-hover table-striped">
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script
			src="<c:url value='/resources/default/js/page/report.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>