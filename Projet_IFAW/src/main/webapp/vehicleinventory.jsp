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
	<c:if test="${isAdmin == true  && sessionScope.user != null }">
	<div class="jumbotron my-0 pb-1">
		<h2 class="display-3 text-center pt-5 ">Vehicle inventory manager</h2>
		<hr class="my-5">
		<p class="text-center pb-4">Add, delete or modify vehicles in
			stock</p>
	</div>
	<div class="container-vehicles py-5 px-3">
		<div class="container-xxl">
			<div class ="container px-5">
			<div class="d-grid  pb-3">
			 <div class="text-center">
				<a class="btn btn-dark"
					href="<%=request.getContextPath() + "/AddVehicle"%>" type="button">Add
					a new vehicle to the inventory</a>
			</div>
			</div>
			<table class="table table-hover">
				<thead>
					<tr class="table-dark">
						<th scope="row">Picture</th>
						<th scope="row">Type</th>
						<th scope="row">Brand</th>
						<th scope="row">Model</th>
						<th scope="row">Gearbox</th>
						<th scope="row">Category</th>
						<th scope="row">Engine</th>
						<th scope="row">No. of seats</th>
						<th scope="row">Price</th>
						<th scope="row">Stock</th>
						<th scope="row">Available</th>
						<th scope="row">Delete</th>
						<th scope="row">Edit</th>
					</tr>
				</thead>
				<c:forEach items="${inventoryVehicles}" var="item">
					<tbody>
						<tr class="table-active">
							<td><img src="${item.picture}"
								class="d-block user-select-none" width="120%" height="70"
								preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180"
								style="font-size: 1.125rem; text-anchor: middle"></td>
							<td>${item.type}</td>
							<td>${item.brand}</td>
							<td>${item.model}</td>
							<td>${item.gearbox}</td>
							<td>${item.category}</td>
							<td>${item.engine}</td>
							<td>${item.nb_seats}</td>
							<td>${item.price}</td>
							<td>${item.stock}</td>
							<c:if test="${item.available == true}">
								<td><span class="badge rounded-pill bg-success">In
										stock</span></td>
							</c:if>
							<c:if test="${item.available == false}">
								<td><span class="badge rounded-pill bg-danger">Out
										of stock</span></td>
							</c:if>
							<c:set var="id_vehicle" scope="session" value="${item.ID_V}" />
							<td><a
								href="<%=request.getContextPath() + "/DeleteVehicle?id_v="%>${id_vehicle}"
								style='color: #4C4E52'><i class="material-icons">delete</i></a></td>
							<td><a
								href="<%=request.getContextPath() + "/EditVehicle?id_vehicle="%>${id_vehicle}"
								style='color: #4C4E52'><i class="material-icons mx-4">edit</i></a></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
			</div>
		</div>
	</div>
	</c:if>
	<%@ include file="footer.jsp"%>
</body>
</html>