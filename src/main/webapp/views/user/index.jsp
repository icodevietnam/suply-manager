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
						<a href="<c:url value='/admin/user/list'/>" class="btn-link">
							<h2>Quản lý Người dùng</h2>
						</a>
						<button data-toggle="modal" data-target="#newItem"class="btn btn-sm btn-primary">Tạo
							mới</button>
						<div class="table-responsive">
							<table id="tblUser"
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
						<h4 class="modal-title" id="myModalLabel">Thêm Người dùng</h4>
					</div>
					<form id="newItemForm" class="form-horizontal" action="<c:url value='/admin/user/new'/>" method="POST">
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Tên đăng nhập</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="userName" name="userName" >
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Mật Khẩu</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="password" name="password" >
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Xác nhận mật khẩu</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" >
							</div>
						</div>
						<div class="form-group">
								<label for="gender" class="col-sm-2 control-label">Phái</label>
								<div class="col-sm-10">
									<select id="genderBox" class="combobox form-control" data-style="btn-white" name="genderBox">
										<option value="true">Nam</option>
										<option value="false">Nữ</option>
									</select>
								</div>
							</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Họ và tên</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="fullname" name="fullname" >
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Ngày sinh</label>
							<div class="col-sm-10">
								<input type="text" class="dateInput form-control" id="birthDate" name="birthDate" data-date-format="dd/mm/yyyy" >
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Địa chỉ</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="address" name="address" >
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Điện thoại</label>
							<div class="col-sm-10">
								<input type="number" class="form-control" id="phone" name="phone" >
							</div>
						</div>
						<div class="form-group">
								<label for="state" class="col-sm-2 control-label">Trạng thái</label>
								<div class="col-sm-10">
									<select id="stateBox" class="combobox form-control" name="stateBox" data-style="btn-white">
										<option value="active">Đang hoạt động</option>
										<option value="absent">Nghỉ phép</option>
										<option value="resign">Từ chức</option>
									</select>
								</div>
						</div>
						<div class="form-group">
							<label for="department" class="col-sm-2 control-label">Phòng ban</label>
							<div class="col-sm-10">
								<select id="departmentBox" name="departmentBox" class="form-control combobox" data-style="btn-white">
									<c:forEach var="department" items="${listDepartments}">
										<option value="${department.id}">${department.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="role" class="col-sm-2 control-label">Quyền</label>
							<div class="col-sm-10">
								<select id="roleBox" name="roleBox" class="form-control combobox" data-style="btn-white">
									<c:forEach var="role" items="${listRoles}">
										<option value="${role.id}">${role.name}</option>
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
		<div class="modal fade" id="updateItem" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Sửa Người dùng</h4>
					</div>
					<form id="updateItemForm" class="form-horizontal" action="<c:url value='/admin/profile/updateProfile'/>" method="POST">
					<div class="modal-body">
								<input type="text" class="userId form-control hide" id="userId" name="userId" >
								<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Tên đăng nhập</label>
							<div class="col-sm-10">
							<input type="text" disabled="disabled" class="userName form-control" id="userName" name="userName" >
							</div>
						</div>
						<div class="form-group">
								<label for="gender" class="col-sm-2 control-label">Phái</label>
								<div class="col-sm-10">
									<select id="genderBox" class="genderBox combobox form-control" data-style="btn-white" name="gender">
										<option value="true">Nam</option>
										<option value="false">Nữ</option>
									</select>
								</div>
							</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Họ và tên</label>
							<div class="col-sm-10">
								<input type="text" class="fullname form-control" id="fullname" name="fullname" >
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Ngày sinh</label>
							<div class="col-sm-10">
								<input type="text" class="birthDate dateInput form-control" id="birthDate" name="birthDate" >
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Địa chỉ</label>
							<div class="col-sm-10">
								<input type="text" class="address form-control" id="address" name="address" >
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Điện thoại</label>
							<div class="col-sm-10">
								<input type="number" class="phone form-control" id="phone" name="phone" >
							</div>
						</div>
						<div class="form-group">
								<label for="state" class="col-sm-2 control-label">Trạng thái</label>
								<div class="col-sm-10">
									<select id="stateBox" class="stateBox combobox form-control" name="stateBox" data-style="btn-white">
										<option value="active">Đang hoạt động</option>
										<option value="absent">Nghỉ phép</option>
										<option value="resign">Từ chức</option>
									</select>
								</div>
						</div>
						<div class="form-group">
							<label for="department" class="col-sm-2 control-label">Phòng ban</label>
							<div class="col-sm-10">
								<select id="departmentBox" name="departmentBox" class="departmentBox form-control combobox" data-style="btn-white">
									<c:forEach var="department" items="${listDepartments}">
										<option value="${department.id}">${department.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="department" class="col-sm-2 control-label">Quyền</label>
							<div class="col-sm-10">
								<select id="roleBox" name="roleBox" class="roleBox form-control combobox" data-style="btn-white">
									<c:forEach var="role" items="${listRoles}">
										<option value="${role.id}">${role.name}</option>
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
		<div class="modal fade" id="changeModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Thay đổi mật khẩu</h4>
					</div>
					<form id="changeModalForm" class="form-horizontal" action="<c:url value='/admin/user/new'/>" method="POST">
					<input type="text" class="userId form-control hide" id="userId" name="userId" >
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Mật Khẩu</label>
							<div class="col-sm-10">
								<input type="password" class="password form-control" id="password" name="password" >
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Xác nhận mật khẩu</label>
							<div class="col-sm-10">
								<input type="password" class="confirmPassword form-control" id="confirmPassword" name="confirmPassword" >
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" onclick="changePassProcess();" class="btn btn-primary">Đổi mật khẩu</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
					</div>
					</form>
				</div>
			</div>
		</div>
		<script
			src="<c:url value='/resources/default/js/page/user.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>