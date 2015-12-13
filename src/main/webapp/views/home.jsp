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
							<input type="text" id="searchName" name="searchName" placeholder="Name or Code" >
							</div>
							<button class='btn btn-primary' type="button" onclick='searchData();'>Search</button>
						</div>
						<div class="row">
							<div class="table-responsive">
								<table id="tblBrief"
									class="table table-bordered table-hover table-striped">
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script
			src="<c:url value='/resources/default/js/page/adminHome.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>