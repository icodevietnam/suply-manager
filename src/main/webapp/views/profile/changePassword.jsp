<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="row">
			<div id="changePassword" class="col-lg-6">
				<div class="ibox">
					<div class="ibox-content">
						<a class="btn-link">
							<h2>Thay đổi mật khẩu</h2>
						</a>
						<form id="changePasswordForm" class="form-horizontal" action="<c:url value='/admin/profile/change-password'/>" method="POST">
							<input type="text" value="${currentUser.id}" class="form-control hide" id="id" name="id" >
							<div class="form-group">
								<label for="oldpassword" class="col-sm-3 control-label">Mật mã cũ</label>
								<div class="col-sm-9">
									<input type="password" class="form-control" placeholder="Mật mã cũ" id="oldpassword" name="oldpassword" >
									<span class="error" style="color:red;">${message}</span>
								</div>
							</div>
							<div class="form-group">
								<label for="newpassword" class="col-sm-3 control-label">Mật mã mới</label>
								<div class="col-sm-9">
									<input type="password" class="form-control" placeholder="Mật mã mới" id="newpassword" name="newpassword" >
								</div>
							</div>
							<div class="form-group">
								<label for="confirmPassword" class="col-sm-3 control-label">Xác nhận mật mã</label>
								<div class="col-sm-9">
									<input type="password" class="form-control" placeholder="Xác nhận mật mã" id="confirmPassword" name="confirmPassword" >
								</div>
							</div>
							<div class="form-group">
    							<div class="col-sm-5">
      								<button type="submit" class="btn btn-sm btn btn-primary">Thay đổi mật khẩu</button>
      								<button type="reset" class="btn btn-sm btn btn-danger">Xoá</button>
      								<span class="success" style="color:blue;">${success}</span>
    							</div>
  							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script src="<c:url value='/resources/default/js/page/changePassword.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>