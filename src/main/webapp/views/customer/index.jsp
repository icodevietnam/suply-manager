<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div class="col-lg-10">
				<div class="ibox">
					<div class="ibox-content">
						<a href="<c:url value='/admin/customer/list'/>" class="btn-link">
							<h2>Quản lý Khách hàng</h2>
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
		<div class="modal fade" id="newItem" tabindex="-1" customer="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" customer="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Thêm Khách hàng</h4>
					</div>
					<form id="newItemForm" class="form-horizontal" action="<c:url value='/admin/customer/new'/>" method="POST">
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Tên</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="customerName" name="customerName" >
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Ngày sinh</label>
							<div class="col-sm-10">
							<input type="text" class="dateInput form-control" id="customerBirthDay" name="customerBirthDay" data-date-format="dd/mm/yyyy" >
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
							<input type="email" class="form-control" id="customerEmail" name="customerEmail" >
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Địa chỉ</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="customerAddress" name="customerAddress" >
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Điện thoại</label>
							<div class="col-sm-10">
							<input type="number" class="form-control" id="customerPhone" name="customerPhone" >
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
		<div class="modal fade" id="updateItem" tabindex="-1" customer="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" customer="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Sửa Khách hàng</h4>
					</div>
					<form id="updateItemForm" class="form-horizontal" action="<c:url value='/admin/profile/updateProfile'/>" method="POST">
					<div class="modal-body">
								<input type="text" class="customerId form-control hide" id="customerId" name="customerId" >
								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">Tên</label>
									<div class="col-sm-10">
									<input type="text" class="customerName form-control" id="customerName" name="customerName" >
									</div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">Ngày sinh</label>
									<div class="col-sm-10">
									<input type="text" class="dateInput customerBirthDay form-control" id="customerBirthDay" name="customerBirthDay" data-date-format="dd/mm/yyyy">
									</div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">Email</label>
									<div class="col-sm-10">
									<input type="email" class="customerEmail form-control" id="customerEmail" name="customerEmail" >
									</div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">Địa chỉ</label>
									<div class="col-sm-10">
									<input type="text" class="customerAddress form-control" id="customerAddress" name="customerAddress" >
									</div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">Điện thoại</label>
									<div class="col-sm-10">
									<input type="number" class="customerPhone form-control" id="customerPhone" name="customerPhone" >
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
			src="<c:url value='/resources/default/js/page/customer.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>