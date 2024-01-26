<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Author : Darlène -->
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://bootswatch.com/5/zephyr/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<title>Wiggler Kart | The first multi-brand car seller in Europe</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
<script src='https://kit.fontawesome.com/cfea7f36cc.js'></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<img src="./img/logowiggler.png" width="90"
				class="d-inline-block align-middle mr-2"></img> <a
				class="navbar-brand"
				href="<%=request.getContextPath() + "/index.jsp"%>">Wiggler Kart</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarColor02"
				aria-controls="navbarColor02" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarColor02">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath() + "/index.jsp"%>">Home </a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath() + "/Catalog"%>">Vehicles
							catalog</a></li>
					<c:if test="${isAdmin == true  && sessionScope.user != null }">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
							href="#" role="button" aria-haspopup="true" aria-expanded="false">Administrator's
								space</a>
							<div class="dropdown-menu">
								<a class="dropdown-item"
									href="<%=request.getContextPath() + "/BillManager"%>">Bill
									manager</a> <a class="dropdown-item"
									href="<%=request.getContextPath() + "/Inventory"%>">Vehicles
									inventory manager</a>
							</div></li>
					</c:if>
					<c:if test="${isAdmin == false && isCompany == false  && sessionScope.user != null }">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
							 role="button" aria-haspopup="true" aria-expanded="false">My
								customer space</a>
							<div class="dropdown-menu">
								<a class="dropdown-item"
									href="<%=request.getContextPath() + "/myprofile.jsp"%>">My
									profile</a> <a class="dropdown-item"
									href="<%=request.getContextPath() + "/MyOrders"%>">My
									orders</a>
							</div></li>
					</c:if>
						<c:if test="${isAdmin == false && isCompany == true  && sessionScope.user != null }">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
							 role="button" aria-haspopup="true" aria-expanded="false">My
								company space</a>
							<div class="dropdown-menu">
								<a class="dropdown-item"
									href="<%=request.getContextPath() + "/myprofile.jsp"%>">My company
									profile</a> <a class="dropdown-item"
									href="<%=request.getContextPath() + "/MyOrders"%>">My fleet
									orders</a>
							</div></li>
					</c:if>

				</ul>
				<c:if test="${empty user}">
					<form class="form-inline my-lg-3">
						<a class="btn btn-secondary my-sm-2 text-dark" type="submit"
							href="<%=request.getContextPath() + "/registerMenu.jsp"%>" role="button">Register</a>
						<a class="btn btn-secondary ml-1 my-sm-4 text-dark" type="submit"
							href="<%=request.getContextPath() + "/Login"%>" role="button">Log
							in</a>
					</form>
				</c:if>
				<c:if test="${!empty user}">
					<form class="form-inline my-lg-3">
						<c:if test="${isAdmin == false}">
							<a class="btn btn-secondary"
								href="<%=request.getContextPath() + "/mycart.jsp"%>"
								role="button"><span class="badge bg-success rounded-pill">${cart_size}</span>
								<i
								class='fas fa-shopping-cart'></i> My cart</a>
						</c:if>
						<c:if test="${isCompany == false}">
						<a class="btn btn-secondary my-sm-2 text-dark"
							role="button"><i class='far fa-user-circle px-1'></i>
						<c:out value="Welcome ${user.firstName}" /></a> 
						</c:if>
						<c:if test="${isCompany == true}">
						<a class="btn btn-secondary my-sm-2 text-dark"
							role="button"><i class='far fa-user-circle px-1'></i>
						<c:out value="Welcome ${user.name}" /></a> 
						</c:if>
						<a class="btn btn-secondary ml-1 my-sm-4 text-dark" type="submit"
							href="<%=request.getContextPath() + "/Logout"%>" role="button">Log
							out</a>
					</form>
				</c:if>
			</div>
		</div>
	</nav>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js"></script>

</body>
</html>