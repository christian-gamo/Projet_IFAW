<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Author : Christian -->
<html>
<script>
	
</script>
<body>
	<%@ include file="navbar.jsp"%>
	<c:if test="${isAdmin == false  && sessionScope.user != null && lastOrder != null}">
			<div class="container pt-2 pb-5">
				<fieldset>
					<legend class="mt-4">
						<strong>Thank you ! <i class='fas fa-shopping-bag'
							style='font-size: 24px'></i></strong>
					</legend>
					<p>Your order was completed successfully</p>
				</fieldset>
				<div class="container">
				<p><strong>Order n°: </strong>${lastOrder.id_B}</p>
				<c:if test="${lastOrder.delivery_date != null}">
				<p><strong>Delivery date: </strong>${lastOrder.delivery_date}</p>
				</c:if>
				<c:if test="${lastOrder.delivery_date == null}">
				<p><strong>Your order will be delivered two days after the validation of your order by our staff</strong></p>
				</c:if>
				</div>
				<div class="card bg-secondary mb-3" style="max-width: 650rem;">
					<div class="card-header">
						<i class='fas fa-box-open' style='font-size: 24px'></i> <strong>
							Check the order status</strong>
					</div>
					<div class="card-body">
						<p class="card-text">
							To check the status of your order, you can consult the page <a
								href="<%=request.getContextPath() + "/myorders.jsp"%>">"My orders"</a>. For credit card payments, the waiting time before
							delivery is 2 days. For payments with credit request, delivery is
							made two days after the validation of the demand.
						</p>
					</div>
				</div>
				<div class="card bg-secondary mb-3" style="max-width: 650rem;">
					<div class="card-header">
						<i class='fas fa-print' style='font-size:24px'></i> <strong>Print your order receipt, vehicle(s) registration form, vehicle(s) transfer certificate</strong>
					</div>
					<div class="card-body">
						<p class="card-text">
							To print all documents related to your order in PDF or HTML format, you can also consult the page <a
								href="<%=request.getContextPath() + "/myorders.jsp"%>">"My orders"</a>.
						</p>
					</div>
				</div>
				<a href="<%=request.getContextPath() + "/Catalog"%>" class=" btn btn-success">Back to catalog</a>
			</div>
	</c:if>

	<div class="container pb-5 mb-5"></div>
	<div class="container pb-5 mb-5"></div>
	<%@ include file="footer.jsp"%>
</body>
</html>