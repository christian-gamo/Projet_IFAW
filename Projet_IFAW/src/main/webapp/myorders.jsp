<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Author : Christian -->
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
			<h2 class="display-3 text-center pt-5 ">
				My
				<c:if test="${isCompany == true}"> fleet </c:if>
				orders
			</h2>
			<hr class="my-5">
			<p class="text-center pb-4">Check all your order history
				information</p>
		</div>
		<div class="container-vehicles py-5 px-3">
			<div class="container-xxl">
				<table class="table table-hover">
					<thead>
						<tr class="table-dark">
							<th scope="row">Order number</th>
							<th scope="row">Order date</th>
							<th scope="row">Shipping country</th>
							<th scope="row">Amount incl. tax</th>
							<th scope="row">Order status</th>
							<th scope="row">Order receipt</th>
							<th scope="row">Vehicle(s) registration form</th>
							<th scope="row">Vehicle(s) transfer certificate</th>
						</tr>
					</thead>
					<c:if test="${ not empty user_bills}">
						<c:forEach items="${user_bills}" var="item">
							<tbody>
								<tr class="table-active">
									<th scope="row">Order n°${item.id_B}</th>
									<td>${item.bill_date}</td>
									<td>${item.country}</td>
									<td><fmt:formatNumber type="number" pattern="##,###.##" value="${item.bill_amount}"/>€</td>
									<c:if test="${item.order_state =='ONGOING'}">
										<td><span class="badge rounded-pill bg-warning">${item.order_state}</span></td>
									</c:if>
									
									<c:if test="${item.order_state == 'VALIDATED'}">
										<td><span class="badge rounded-pill bg-success">${item.order_state}</span></td>
									</c:if>
									<c:if test="${item.order_state == 'SHIPPED'}">
										<td><span class="badge rounded-pill bg-info">${item.order_state}</span></td>
									</c:if>
									<td style="width: 110px;"><a style="color:#212529;" href="<%=request.getContextPath() + "/GenerateHTML?doc=0&id_b="%>${item.id_B}"><i
										class='fas fa-file-download mx-4'></i></a></td>
									<td style="width: 110px;"><a style="color:#212529;" href=<%=request.getContextPath() + "/GenerateHTML?doc=1&id_b="%>${item.id_B}><i
										class='fas fa-file-download mx-4'></i></a></td>
										<td style="width: 110px;"><a style="color:#212529;" href="<%=request.getContextPath() + "/GenerateHTML?doc=2&id_b="%>${item.id_B}"><i
										class='fas fa-file-download mx-4'></i></a></td>
								</tr>

							</tbody>
						</c:forEach>
					</c:if>
				</table>

			</div>
		</div>
	</c:if>
	<div class="container pb-5 mb-5"></div>
	<div class="container pb-5 mb-5"></div>
	<%@ include file="footer.jsp"%>

</body>
</html>