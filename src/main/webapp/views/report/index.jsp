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
						<form class="form-inline">
							<div class="form-group">
								<label for="exampleInputName2">Từ</label> 
								<input type="text" class="form-control" id="fromDate"
									placeholder="">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail2">Đến</label> <input
									type="email" class="form-control" id="toDate"
									placeholder="">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail2">Mã PE</label> <input
									type="email" class="form-control" id="toDate"
									placeholder="">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail2">Tên khách hàng</label> <input
									type="email" class="form-control" id="toDate"
									placeholder="">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail2">Loại hồ sơ</label>
								<select id="stateBox" class="combobox form-control" name="stateBox" data-style="btn-white">
									<c:forEach var="briefType" items="${listBrieftType}">
										<option value="${briefType.id}">${briefType.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="exampleInputEmail2">Kho</label>
								<select id="stateBox" class="combobox form-control" name="stateBox" data-style="btn-white">
									<c:forEach var="briefType" items="${listBrieftType}">
										<option value="${briefType.id}">${briefType.name}</option>
									</c:forEach>
								</select>
							</div>
							<button type="submit" class="btn btn-primary">Tìm hồ sơ</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script src="<c:url value='/resources/default/js/page/report.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>