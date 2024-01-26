<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Author : Christian-->
<html>
<style>
.jumbotron {
	background-color: #b9bec4;
}
</style>

<body>
	<%@ include file="navbar.jsp"%>
	<c:if test="${isAdmin == false  && sessionScope.user != null }">
	<form action="PaymentShipping" method="post">
	<div class="container pt-2 pb-5">
		<div class="mt-5">
				<c:if test="${ not empty cart}">
					<table class="table table-hover">
						<thead>
							<tr class="table-dark">
								<th scope="row">Brand</th>
								<th scope="row">Model</th>
								<th scope="row">Quantity</th>
								<th scope="row">Remove</th>
							</tr>
						</thead>
						<c:forEach items="${cart}" var="item">
							<tbody>
								<tr class="table-active">
									<td>${item.key.brand}</td>
							
									<td>${item.key.model}</td>
									<td><input type="number" step="1" min=1
										value="${item.value}" name="quantity${item.key.ID_V}" class="form-control">
									</td>
									<td><a class="btn btn-danger btn-sm"
										href="<%=request.getContextPath() + "/DeleteCartItem?id_v="%>${item.key.ID_V}">
											<i class="material-icons">delete</i>
									</a></td>
								</tr>

							</tbody>

						</c:forEach>
					</table>
				</c:if>	
		</div>
		<div>
	
				<c:if test="${ not empty cart}">
					<div class="card bg-secondary mb-3">
						<div class="card-header">
							<strong>Payment and shipping</strong>
						</div>
						<div class="card-body">
							<div class="form-group">
								<label class="form-label">Shipping country</label> <select
									class="form-select" name="shipping_country">
									<option value="DE">DE</option>
									<option value="FR">ES</option>
									<option value="FR">FR</option>
									<option value="GB">GB</option>
									<option value="IT">IT</option>
									<option value="US">US</option>
									<option value="JP">JP</option>
								</select>
							</div>
							<div class="form-group">
								<label class="form-label mt-2">Payment method</label> <select
									class="form-select" name="payment_method">
									<option value="Credit Card">Credit Card</option>
									<option value="Credit Request">Credit Request</option>
								</select>
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-success mt-4">Confirm
					order</button>
				</c:if>
		</div>
		
		<c:if test="${empty cart}">
			<div class="container pt-0 pb-5 mb-5">
				<fieldset>
					<legend class="mt-4">
						<strong>My cart</strong>
					</legend>
				</fieldset>
				<div class="jumbotron pt-1 pb-1">
					<h3 class="text-center mt-3">
						<strong> Your cart is empty</strong>
					</h3>
					<p class="text-center">
						You can checkout our <a
							href="<%=request.getContextPath() + "/Catalog"%>"> vehicle
							catalog </a> to buy what you like !
					</p>
				</div>
			</div>
			<div class="container pb-5 mb-5"></div>
			<div class="container pb-5 mb-5"></div>
			<div class="container pb-5 mb-5"></div>
			<div class="container pb-5 mb-5"></div>
			
		</c:if>
		<div class="container pb-5 mb-5"></div>
		<div class="container pb-5 mb-5"></div>
	</div>
	</form>
</c:if>
	<%@ include file="footer.jsp"%>
</body>

</html>
