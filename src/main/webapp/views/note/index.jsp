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
							<button onclick="checkBoxLength();"
							class="btn btn-sm btn-primary">Tạo phiếu mượn mới</button>
							<table id="tableBrief"
								class="table table-bordered table-hover table-striped">
							</table>
						</div>
						<!-- Phieu muon -->
						<br/>
						<h3>Danh sách những phiếu mượn (hồ sơ chưa được trả)</h3>
						<div class="table-responsive">
							<table id="tableNote"
								class="table table-bordered table-hover table-striped">
							</table>
						</div>
						
						<!-- Phieu trả -->
						<br/>
						<h3>Danh sách những phiếu mượn (hồ sơ đã trả)</h3>
						<div class="table-responsive">
							<table id="tablePaidNote"
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
					<form id="createNoteForm" class="form-horizontal" method="POST">
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-3 control-label">Phòng ban</label>
							<div class="col-sm-9">
								<select id="departmentBox" name="departmentBox" class="form-control combobox" data-style="btn-white">
									<c:forEach var="department" items="${listDepartments}">
										<option value="${department.id}">${department.name}</option>
									</c:forEach>
								</select>
							</div>
							<label style="font-weight: lighter;" for="name" class="col-sm-12 control-label">Khi bấm vào nút mượn hồ sơ , các phiếu mượn sẽ xuống danh sách những phiếu mượn</label>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" onclick="borrowBrief();" class="btn btn-primary">Mượn hồ sơ</button>
					</div>
					</form>
				</div>
			</div>
		</div>
		<script src="<c:url value='/resources/default/js/page/note.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>