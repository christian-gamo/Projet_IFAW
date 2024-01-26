<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Author : Yassine -->
<html>
<style>
.jumbotron {
	background-color: #b9bec4;
}
</style>
<body>
	<%@ include file="navbar.jsp"%>
	<c:if test="${isAdmin == false  && sessionScope.user != null }">
	<div class="jumbotron my-0 pb-1">
		<h2 class="display-3 text-center pt-5 ">My<c:if test="${isCompany == true}"> company </c:if> profile</h2>
		<hr class="my-5">
		<p class="text-center pb-4">See all the information about your
			customer profile</p>
	</div>
	<div class="container-vehicles py-5 px-3">
		<div class="container-xxl">
			<div class="card mb-5">
				<h3 class="card-header">
					<i class='far fa-user-circle px-1'></i>
					<c:out value=" ${user.lastName}  ${user.firstName}" />
				</h3>
				<div class="card-body">
					<fieldset>
						<input class="form-control" id="disabledInput" type="text"
							placeholder="Last name : ${user.lastName}"  disabled>
					</fieldset>
					<fieldset class="my-2">
						<input class="form-control" id="disabledInput" type="text"
							placeholder="First name : ${user.firstName}"  disabled>
					</fieldset>
					<fieldset class="my-2">
						<input class="form-control" id="disabledInput" type="text"
							placeholder="Email : ${user.email}"  disabled>
					</fieldset>
					<fieldset class="my-2">
						<input class="form-control" id="disabledInput" type="text"  disabled
							placeholder="<c:if test = "${isAdmin == false && isCompany == false}">Status : Client</c:if> <c:if test = "${isAdmin == false && isCompany == true}">Status : Company</c:if>">
					</fieldset>
				</div>
			</div>
		</div>
	</div>
	</c:if>
	<%@ include file="footer.jsp"%>
</body>
</html>