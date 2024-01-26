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
					<strong>Register on Buy my Car</strong>
				</legend>
				<c:if test="${ not empty hasRegistered}">
					<div class="alert alert-dismissible alert-success">
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
						<strong>You have successfully registered</strong>
					</div>
				</c:if>
				<c:if test="${ not empty err}">
					<div class="alert alert-dismissible alert-danger">
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
						<strong><c:out value="${ err }"></c:out></strong>
					</div>
				</c:if>
				<div class="form-group">
					<c:if test="${isCompany == true}">
						<label class="col-form-label mt-4" for="LastName">Company
							name</label>
						<input type="text" name="companyName" class="form-control"
							placeholder="Enter the name of your company" id="companyName"
							required>
					</c:if>
					<label class="col-form-label mt-4" for="LastName">Last Name</label>
					<input type="text" name="lastName" class="form-control"
						placeholder="Enter your Last name" id="LastName" required>
					<label class="col-form-label mt-4" for="FirstName">First
						Name</label> <input type="text" name="firstName" class="form-control"
						placeholder="Enter your First name" id="FirstName" required>
					<label for="Email" class="form-label mt-4">Email address</label> <input
						name="email" type="email" class="form-control" id="Email"
						aria-describedby="emailHelp" placeholder="Enter email" required>
					<small id="emailHelp" class="form-text text-muted">We'll
						never share your email with anyone else.</small>
				</div>
				<div class="form-group">
					<label for="Password" class="form-label mt-4">Password</label> <input
						type="password" name="password" class="form-control" id="Password"
						placeholder="Enter a password" required>
				</div>
				<button type="submit" class="btn btn-success mt-4">Register</button>
			</fieldset>
		</form>
	</div>
	<div class="container pb-5 mb-5"></div>
	<div class="container pb-5 mb-5"></div>
	<%@ include file="footer.jsp"%>
</body>
</html>