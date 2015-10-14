<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div class="col-lg-8">
				<div class="ibox">
					<div class="ibox-content">
						<a href="<c:url value='/admin/documentType/list'/>" class="btn-link">
							<h2>Quản lý Loại Đơn Từ</h2>
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
		<div class="modal fade" id="newItem" tabindex="-1" documentType="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" documentType="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Thêm Loại Đơn Từ</h4>
					</div>
					<form id="newItemForm" class="form-horizontal" action="<c:url value='/admin/documentType/new'/>" method="POST">
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Tên</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="documentTypeName" name="documentTypeName" >
							</div>
						</div>
						<div class="form-group">
							<label for="department" class="col-sm-2 control-label">Phòng</label>
								<div class="col-sm-10">
									<select id="departmentBox" name="departmentBox" class="form-control combobox" data-style="btn-white">
										<c:forEach var="department" items="${listDepartments}">
											<option value="${department.id}">${department.name}</option>
										</c:forEach>
									</select>
								</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" onclick="insertItem();" class="btn btn-primary">Lưu</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
					</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="updateItem" tabindex="-1" documentType="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" documentType="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Sửa Loại Đơn Từ</h4>
					</div>
					<form id="updateItemForm" class="form-horizontal" action="<c:url value='/admin/profile/updateProfile'/>" method="POST">
					<div class="modal-body">
								<input type="text" class="documentTypeId form-control hide" id="documentTypeId" name="documentTypeId" >
								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">Tên</label>
									<div class="col-sm-10">
										<input type="text" class="documentTypeName form-control" id="documentTypeName" name="documentTypeName" >
									</div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">Vị Trí</label>
									<div class="col-sm-10">
										<select id="departmentBox" name="departmentBox" class="departmentBox form-control combobox" data-style="btn-white">
										<c:forEach var="department" items="${listDepartments}">
											<option value="${department.id}">${department.name}</option>
										</c:forEach>
									</select>
									</div>
								</div>
					</div>
					<div class="modal-footer">
						<button type="button" onclick="editedItem();" class="btn btn-primary">Chỉnh sửa</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
					</div>
					</form>
				</div>
			</div>
		</div>
		<script
			src="<c:url value='/resources/default/js/page/documenttype.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>