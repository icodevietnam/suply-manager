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
						<div class="row">
							<h3>Danh sách những hồ sơ :</h3>
							<form id="searchBriefForm" class="form-inline">
								<div class="form-group">
									<label for="exampleInputName2">Từ</label> <input type="text"
										class="dateInput form-control" name="fromDate">
								</div>
								<div class="form-group">
									<label for="exampleInputEmail2">Đến</label> <input type="text"
										class="dateInput form-control" name="toDate">
								</div>
								<div class="form-group">
									<label for="exampleInputEmail2">Mã PE</label> <input
										type="text" class="form-control" name="code" placeholder="">
								</div>
								<div class="form-group">
									<label for="exampleInputEmail2">Tên khách hàng</label> <input
										type="text" class="form-control" name="customerName" placeholder="">
								</div>
								<div class="form-group">
									<label for="exampleInputEmail2">Loại hồ sơ</label> <select
										class="combobox form-control" name="briefTypeBox"
										data-style="btn-white">
										<c:forEach var="briefType" items="${listBrieftType}">
											<option value="${briefType.id}">${briefType.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail2">Kho</label> <select
										class="combobox form-control" name="stockBox"
										data-style="btn-white">
										<c:forEach var="stock" items="${listStock}">
											<option value="${stock.id}">${stock.name}</option>
										</c:forEach>
									</select>
								</div>
								<button type="button" onclick="searchBrief();" class="btn btn-primary">Tìm hồ sơ</button>
							</form>
							<table id="tableBrief"
								class="table table-bordered table-hover table-striped">
							</table>
						</div>
						<div class="row">
							<div class="col-lg-6">
								<div class="panel panel-primary">
									<div class="panel-heading">
										Số lượng hồ sơ theo kho
										<button style="margin-left: 10px;"
											class="btn btn-xs btn-danger" type="button"
											onclick="listGraphByStock();">Refresh</button>
									</div>
									<div style="height: 420px;" class="panel-body">
										<div id="chartBriefStock"></div>
									</div>
								</div>
							</div>
							<div class="col-lg-6">
								<div class="panel panel-primary">
									<div class="panel-heading">
										Số lượng hồ sơ phòng ban đã mượn
										<button style="margin-left: 10px;"
											class="btn btn-xs btn-danger" type="button"
											onclick="listBriefByDepartment();">Refresh</button>
									</div>
									<div style="height: 420px;" class="panel-body">
										<div id="chartBriefDepartment"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-8">
								<div class="panel panel-primary">
									<div class="panel-heading">
										Hồ sơ đã trả
										<button style="margin-left: 10px;"
											class="btn btn-xs btn-danger" type="button"
											onclick="listBriefByDepartment();">Refresh</button>
									</div>
									<div class="panel-body">
										<div class="table-responsive">
											<table id="tablePaidNote"
												class="table table-bordered table-hover table-striped">
											</table>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-4">
								<div class="panel panel-primary">
									<div class="panel-heading">
										So sánh đồ thị của hồ sơ
										<button style="margin-left: 10px;"
											class="btn btn-xs btn-danger" type="button"
											onclick="listGraphByStock();">Refresh</button>
									</div>
									<div style="height: 420px;" class="panel-body">
										<div id="chartBrief"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- ibox content -->
				</div>
			</div>
		</div>
		<script src="<c:url value='/resources/default/js/page/report.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>