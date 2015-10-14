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
						<a href="<c:url value='/admin/brief/list'/>" class="btn-link">
							<h2>Quản lý Hồ Sơ</h2>
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
		<div class="modal fade" id="newItem" tabindex="-1" stock="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" stock="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Thêm Hồ Sơ</h4>
					</div>
					<form id="newItemForm" class="form-horizontal" action="<c:url value='/admin/stock/new'/>" method="POST">
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Nội dung</label>
							<div class="col-sm-10">
							<textarea class="form-control" id="content" name="content" ></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="briefType" class="col-sm-2 control-label">Loại Hồ Sơ</label>
							<div class="col-sm-10">
								<select id="briefTypeBox" name="briefTypeBox" class="form-control documentTypeBox"  data-live-search="true" data-style="btn-white">
									<c:forEach var="briefType" items="${listBriefTypes}">
										<option value="${briefType.id}">${briefType.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="customer" class="col-sm-2 control-label">Khách hàng</label>
							<div class="col-sm-10">
								<select id="customerBox" name="customerBox" class="form-control combobox" data-live-search="true" data-style="btn-white">
									<c:forEach var="customer" items="${listCustomers}">
										<option value="${customer.id}">${customer.name} - Đ/c: ${customer.address}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="stock" class="col-sm-2 control-label">Kho</label>
							<div class="col-sm-10">
								<select id="stockBox" name="stockBox" class="form-control combobox" data-live-search="true" data-style="btn-white">
									<c:forEach var="stock" items="${listStocks}">
										<option value="${stock.id}">${stock.name}</option>
									</c:forEach>
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
					</div>
					<div class="modal-footer">
						<button type="button" onclick="insertItem();" class="btn btn-primary">Lưu</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
					</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="updateItem" tabindex="-1" stock="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" stock="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Sửa Hồ Sơ</h4>
					</div>
					<form id="updateItemForm" class="form-horizontal" action="<c:url value='/admin/profile/updateProfile'/>" method="POST">
					<div class="modal-body">
								<input type="text" class="briefId form-control hide" id="briefId" name="briefId" >
								<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Nội dung</label>
							<div class="col-sm-10">
							<textarea class="content form-control" id="content" name="content" ></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="briefType" class="col-sm-2 control-label">Loại Hồ Sơ</label>
							<div class="col-sm-10">
								<select id="briefTypeBox" name="briefTypeBox" class="briefTypeBox form-control combobox" data-style="btn-white">
									<c:forEach var="briefType" items="${listBriefTypes}">
										<option value="${briefType.id}">${briefType.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="customer" class="col-sm-2 control-label">Khách hàng</label>
							<div class="col-sm-10">
								<select id="customerBox" name="customerBox" class="customerBox form-control combobox" data-style="btn-white">
									<c:forEach var="customer" items="${listCustomers}">
										<option value="${customer.id}">${customer.name} - Đ/c: ${customer.address}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="stock" class="col-sm-2 control-label">Kho</label>
							<div class="col-sm-10">
								<select id="stockBox" name="stockBox" class="stockBox form-control combobox" data-style="btn-white">
									<c:forEach var="stock" items="${listStocks}">
										<option value="${stock.id}">${stock.name}</option>
									</c:forEach>
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
			src="<c:url value='/resources/default/js/page/brief.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>