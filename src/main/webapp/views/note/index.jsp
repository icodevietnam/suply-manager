<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox">
					<div class="ibox-content">
						<a href="<c:url value='/admin/note/list'/>" class="btn-link">
							<h2>Quản lý Phiếu Mượn Trả</h2>
						</a>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<br/>
						<h3>Danh sách những hồ sơ chưa được mượn</h3>
						<div class="table-responsive">
							<button onclick="checkBoxLength();"
							class="btn btn-sm btn-primary">Tạo phiếu mượn mới</button>
							<table id="tableBrief"
								class="table table-bordered table-hover table-striped">
							</table>
						</div>
						</sec:authorize>
						<!-- Danh sách hồ sơ đã được mượn -->
						<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')">
						<br/>
						<h3>Danh sách những hồ sơ đã được mượn</h3>
						<div class="table-responsive">
							<table id="tableBriefBorrow"
								class="table table-bordered table-hover table-striped">
							</table>
						</div>
						</sec:authorize>
						<!-- Phieu muon -->
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<br/>
						<h3>Danh sách những phiếu mượn (hồ sơ chưa được trả)</h3>
						<div class="table-responsive">
							<table id="tableNote"
								class="table table-bordered table-hover table-striped">
							</table>
						</div>
						</sec:authorize>
						<!-- Phieu trả -->
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<br/>
						<h3>Danh sách những phiếu mượn (hồ sơ đã trả)</h3>
						<div class="table-responsive">
							<table id="tablePaidNote"
								class="table table-bordered table-hover table-striped">
							</table>
						</div>
						</sec:authorize>
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
						<h4 class="modal-title" id="myModalLabel">Tạo phiếu mượn</h4>
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
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-3 control-label">Tên người mượn</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="borrowMan" >
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
		<div class="modal fade" id="showTblBrief" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Xem chi tiết hồ sơ theo phiếu mượn</h4>
					</div>
					<div class="modal-body">
						<div class="table-responsive">
							<table id="tblBorrowedBrief"
								class="table table-bordered table-hover table-striped">
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="<c:url value='/resources/default/js/page/note.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>