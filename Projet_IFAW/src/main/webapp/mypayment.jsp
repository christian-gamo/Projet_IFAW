<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Author : Christian -->
<html>
<script>
	
</script>
<body>
	<%@ include file="navbar.jsp"%>
	<c:if test="${isAdmin == false  && sessionScope.user != null }">
		<form action="AddBill" method="post">
			<div class="container pt-2 pb-5">
				<fieldset>
					<legend class="mt-4">
						<strong>Payment and shipping</strong>
					</legend>
					<div class="card text-white bg-dark mb-3">
						<div class="card-header">
							<strong>TOTAL</strong>
						</div>
						<div class="card-body">
							<table class="table table-hover">
								<thead>
									<tr class="table-dark">
										<th scope="row">Brand</th>
										<th scope="row">Model</th>
										<th scope="row">Quantity</th>
										<th scope="row">Unit price</th>
										<th scope="row">Price per quantity</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${cart}" var="item">
										<c:set var="vehicle_quantity" value="${item.value}" />
										<c:set var="vehicle_unit_price" value="${item.key.price}" />
										<tr class="table-active">
											<td style="color: white;">${item.key.brand}</td>
											<td style="color: white;">${item.key.model}</td>
											<td><input type="number" step="1" min=1
												value="${item.value}" name="quantity" id="quantity"
												class="form-control" readonly disabled></td>
											<td><div class="input-group mb-3">
													<input type="number" value="${item.key.price}"
														id="unitPrice" class="form-control" readonly disabled>
													<span class="input-group-text">€</span>
												</div></td>
											<td><div class="input-group mb-3">
													<input type="number" id="pricePerQuantity"
														value="<c:out value="${vehicle_quantity * vehicle_unit_price}" />"
														class="form-control" readonly disabled> <span
														class="input-group-text">€</span>
												</div></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<p class="card-text">
								<strong>Amount incl.TAX</strong>
							</p>
							<div class="form-group">
								<div class="input-group mb-3" style="width: 400px;">
									<input type="number" value="${total_amount}"
										class="form-control" readonly disabled> <span
										class="input-group-text">€</span>
								</div>
								<p class="card-text">Tax amount :</p>
								<div class="input-group mb-3" style="width: 300px;">
									<input type="number" value="${tax_amount}" class="form-control"
										readonly disabled> <span class="input-group-text">€</span>
								</div>
								<p class="card-text">Shipping country :</p>
								<input type="text" name="shipping_country"
									value="${shipping_country}" style="width: 300px;"
									class="form-control" readonly disabled>
									
							</div>
							<p class="card-text pt-2">Payment method :</p>
								<input type="text" name="payment_method"
									value="${payment_method}" style="width: 300px;"
									class="form-control" readonly disabled>
						</div>
					</div>
				</fieldset>
				<button type="submit" class="btn btn-success mt-4">Checkout</button>
			</div>
		</form>
	</c:if>

	<div class="container pb-5 mb-5"></div>
	<div class="container pb-5 mb-5"></div>
	<%@ include file="footer.jsp"%>
</body>
</html>