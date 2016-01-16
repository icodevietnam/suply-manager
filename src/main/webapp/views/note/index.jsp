<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox">
					<div class="ibox-content">
						<a href="<c:url value='/admin/note/list'/>" class="btn-link">
							<h2>Quản lý Phiếu Mượn Trả</h2>
						</a>
						<br/>
						<h3>Danh sách những hồ sơ chưa được mượn</h3>
						<div class="table-responsive">
							<button data-toggle="modal" data-target="#createNote"
							class="btn btn-sm btn-primary">Tạo phiếu mượn mới</button>
							<table id="tableBrief"
								class="table table-bordered table-hover table-striped">
							</table>
						</div>
						<!-- Phieu muon -->
						<br/>
						<h3>Danh sách những phiếu mượn</h3>
						<div class="table-responsive">
							<table id="tableNote"
								class="table table-bordered table-hover table-striped">
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="createNote" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Thêm Quyền</h4>
					</div>
					<form id="newItemForm" class="form-horizontal" method="POST">
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Tên</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="roleName" name="roleName" >
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Chú Thích</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="roleDescription" name="roleDescription" >
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
		<script src="<c:url value='/resources/default/js/page/note.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>