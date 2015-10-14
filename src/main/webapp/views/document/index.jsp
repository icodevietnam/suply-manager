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
						<a href="<c:url value='/admin/document/list'/>" class="btn-link">
							<h2>Quản lý Đơn Từ</h2>
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
		<div class="modal fade" id="newItem" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Thêm Đơn Từ</h4>
					</div>
					<form id="newItemForm" class="form-horizontal" action="<c:url value='/admin/document/new'/>" method="POST" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Nội dung</label>
							<div class="col-sm-10">
							<textarea class="form-control" id="content" name="content" ></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="customer" class="col-sm-2 control-label">Hồ Sơ</label>
							<div class="col-sm-10">
								<select id="briefBox" name="briefBox" class="form-control combobox" data-live-search="true" data-style="btn-white">
									<c:forEach var="brief" items="${listBriefs}">
										<option value="${brief.id}">${brief.content}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="document" class="col-sm-2 control-label">Loại đơn từ</label>
							<div class="col-sm-10">
								<select id="documentTypeBox" name="documentTypeBox" class="form-control combobox" data-live-search="true" data-style="btn-white">
									<c:forEach var="documentType" items="${listDocumentTypes}">
										<option value="${documentType.id}">${documentType.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="document" class="col-sm-2 control-label">Loại đơn từ</label>
							<div class="col-sm-10">
								File to upload: <input id="fileUpload" type="file" name="fileUpload"><br /> 
							</div>
						</div>
					<div class="modal-footer">
						<button type="button" onclick="insertItem();" class="btn btn-primary">Lưu</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
					</div>
					</div>
					</form>
					
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="updateItem" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Sửa Đơn Từ</h4>
					</div>
					<form id="updateItemForm" class="form-horizontal" action="<c:url value='/admin/profile/updateProfile'/>" enctype="multipart/form-data" method="POST">
					<div class="modal-body">
								<input type="text" class="documentId form-control hide" id="documentId" name="documentId" >
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Nội dung</label>
							<div class="col-sm-10">
							<textarea class="content form-control" id="content" name="content" ></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="customer" class="col-sm-2 control-label">Hồ Sơ</label>
							<div class="col-sm-10">
								<select id="briefBox" name="briefBox" class="briefBox form-control combobox" data-live-search="true" data-style="btn-white">
									<c:forEach var="brief" items="${listBriefs}">
										<option value="${brief.id}">${brief.content}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="document" class="col-sm-2 control-label">Loại đơn từ</label>
							<div class="col-sm-10">
								<select id="documentTypeA" name="documentTypeA" class="documentTypeA form-control combobox" data-live-search="true" data-style="btn-white">
									<c:forEach var="documentType" items="${listDocumentTypes}">
										<option value="${documentType.id}">${documentType.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="document" class="col-sm-2 control-label">Loại đơn từ</label>
							<div class="col-sm-10">
								File to upload: <input id="fileUpload" class="fileUpload" type="file" name="fileUpload"><br /> 
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
			src="<c:url value='/resources/default/js/page/document.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>