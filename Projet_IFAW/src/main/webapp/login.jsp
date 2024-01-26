<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- Author : Yassine -->
<html>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container pt-2 pb-5 mb-5">
		<form class="container pt-2 pb-5 mb-5" action="Login" method="post">
			<fieldset class="container pt-2 pb-5 mb-5">
				<legend class="mt-4">
					<strong>Log into Buy my Car</strong>
				</legend>
				<c:if test="${ not empty error}">
					<div class="alert alert-dismissible alert-danger">
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
						<strong><c:out value="${ error }"></c:out></strong>
					</div>
				</c:if>
				<c:if test="${empty error && not empty user}">
					<div class="alert alert-dismissible alert-success">
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
						<strong>You have successfully logged in</strong>
					</div>
				</c:if>
				<c:if test="${ not empty hasLoggedOut}">
					<div class="alert alert-dismissible alert-success">
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
						<strong>You have successfully logged out</strong>
					</div>
				</c:if>
				<div class="form-group">
					<label for="Email" class="form-label mt-4">Email address</label> <input
						type="email" name="email" class="form-control" id="Email"
						aria-describedby="emailHelp" placeholder="Enter your email"
						required>
				</div>
				<div class="form-group">
					<label for="Password" class="form-label mt-4">Password</label> <input
						type="password" name="password" class="form-control" id="Password"
						placeholder="Enter your password" required>
				</div>
				<button type="submit" class="btn btn-success mt-4">Log in</button>
			</fieldset>
		</form>

	</div>
	<div class="container pb-5 mb-5"></div>
	<div class="container pb-5 mb-5"></div>
	<%@ include file="footer.jsp"%>
</body>