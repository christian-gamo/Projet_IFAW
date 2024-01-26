<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Author : Darlène -->
<!DOCTYPE html>
<html>
<style>
.jumbotron {
	background-color: #b9bec4;
}
</style>
<body>
	<%@ include file="navbar.jsp"%>
	<c:if test="${isAdmin == true  && sessionScope.user != null }">
		<div class="jumbotron my-0 pb-1">
			<h2 class="display-3 text-center pt-5 ">Bill manager</h2>
			<hr class="my-5">
			<p class="text-center pb-4">View the history of all orders
				submitted on the website</p>
		</div>
		<form action="" method="post">
			<div class="container-vehicles py-5 px-3">
				<div class="container-xxl">
					<table class="table table-hover">
						<thead>
							<tr class="table-dark">
								<th scope="row">Order number</th>
								<th scope="row">Client's lastname</th>
								<th scope="row">Client's firstname</th>
								<th scope="row">Order date</th>
								<th scope="row">Shipping country</th>
								<th scope="row">Amount incl. tax</th>
								<th scope="row">Order status</th>
								<th scope="row">Validate order</th>
							</tr>
						</thead>
						<c:if test="${ not empty bills}">
							<c:forEach items="${bills}" var="item">
								<tbody>
									<tr class="table-active">
										<th style="width: 110px;" scope="row">Order
											N°${item.key.id_B}</th>
										<td style="width: 110px;">${item.value.lastName}</td>
										<td style="width: 110px;">${item.value.firstName}</td>
										<td style="width: 110px;">${item.key.bill_date}</td>
										<td style="width: 110px;">${item.key.country}</td>
										<td style="width: 115px;"><fmt:formatNumber type="number" pattern="##,###.##" value="${item.key.bill_amount}"/>€</td>

										<c:if test="${item.key.order_state =='ONGOING'}">
											<td><span class="badge rounded-pill bg-warning">${item.key.order_state}</span></td>
										</c:if>
										<c:if test="${item.key.order_state == 'VALIDATED'}">
											<td><span class="badge rounded-pill bg-success">${item.key.order_state}</span></td>
										</c:if>
										<c:if test="${item.key.order_state == 'SHIPPED'}">
											<td><span class="badge rounded-pill bg-info">${item.key.order_state}</span></td>
										</c:if>
										<c:if test="${item.key.payment_state == false}">
											<td><a type="submit" href="<%=request.getContextPath() + "/ValidateOrder?id_b="%>${item.key.id_B}" class="btn btn-success btn-sm">Validate</a></td>
										</c:if>
										<c:if test="${item.key.payment_state == true}">
											<td><a type="submit" class="btn btn-success btn-sm disabled">Validate</a></td>
										</c:if>
									</tr>
								</tbody>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</div>
		</form>
	</c:if>
	<div class="container pb-5 mb-5"></div>
	<div class="container pb-5 mb-5"></div>
	<%@ include file="footer.jsp"%>
</body>

</html>