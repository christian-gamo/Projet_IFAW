<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Author : Yassine -->
<html>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container pt-2 pb-5">
		<form class="container pt-2 pb-5" action="Register" method="post">
			<fieldset>
				<legend class="mt-4">
					<strong>Hi ! Do you want to register as a company or as a
						simple user ?</strong>
				</legend>
				<div class="container py-5">
					<div class="d-grid gap-2">
						<a href="<%=request.getContextPath() + "/Register?isCompany=0"%>" class="btn btn-lg btn-dark my-3" type="button">Register as a simple user</a>
						<a href="<%=request.getContextPath() + "/Register?isCompany=1"%>" class="btn btn-lg btn-outline-dark my-2" type="button">Register as a company</a>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<div class="container pb-5 mb-5"></div>
	<div class="container pb-5 mb-5"></div>
	<div class="container pb-5 mb-5"></div>
	<div class="container pb-5 mb-5"></div>
	<%@ include file="footer.jsp"%>
</body>
</html>