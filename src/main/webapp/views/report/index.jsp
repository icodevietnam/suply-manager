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
								<label for="exampleInputEmail2">Loại hồ sơ</label>
								<select id="stateBox" class="combobox form-control" name="stateBox" data-style="btn-white">
									<option value="active">Đang hoạt động</option>
									<option value="absent">Nghỉ phép</option>
									<option value="resign">Từ chức</option>
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