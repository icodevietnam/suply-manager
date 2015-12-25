<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox">
					<div class="ibox-content">
						<a href="#" class="btn-link">
							<h2>Tình hình hồ sơ</h2>
						</a>
						<div class="row">
							<input type="text" id="searchName" name="searchName"
								placeholder="Name or Code">
						</div>
						<button class='btn btn-primary' type="button"
							onclick='searchData();'>Search</button>
						<a href="<c:url />" >Export Data</a>
					<div class="table-responsive">
						<table id="tblBrief"
							class="table table-bordered table-hover table-striped">
						</table>
					</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="detailBrief" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Chi tiết</h4>
					</div>
					<div class="modal-body">
						<h4 class="content"></h4>
						<h4 class="department"></h4>
						<h4 class="customerName"></h4>
						<h4 class="customerCode"></h4>
						<h4 class="stock"></h4>
						<div class="image"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
					</div>
				</div>
			</div>
		</div>
		<script src="<c:url value='/resources/default/js/page/adminHome.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>