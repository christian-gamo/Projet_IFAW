<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Author : Darlène -->
<!DOCTYPE html>
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
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js">
	
</script>
</head>

<style>
#client_info {
	background-color: #b9bec4;
}

#former_owner_info {
	background-color: #b9bec4;
}

#new_owner_info {
	background-color: #b9bec4;
}
</style>
<body onload="generatePDF()">
	<div id="order_form">
		<div class="container-lg bg-dark mt-5 pt-4">
			<div class="row">
				<div class="col pt-1">
					<img src="./img/logowiggler.png" width="90"
						class="d-inline-block align-middle mr-2"></img>
					<h5 class="text-light d-inline-block align-middle">Wiggler
						Kart</h5>
				</div>
				<div class="col pt-1">
					<h2 class="text-light d-inline-block align-middle py-4">Order
						receipt</h2>
				</div>
				<div class="col pt-2">
					<p class=" text-light px-2" align="right">
						Wiggler Kart Co. Ltd.<br> Tower 42, 25 Old Broad St, London
						EC2N <br>1HN, United Kingdom <br> ++44 7911 123456
					</p>
				</div>
			</div>
		</div>

		<div class="container mt-2" id="client_info">
			<p class="text-dark d-inline-block align-middle pt-2">
				<strong>Client information </strong>
			</p>
		</div>

		<div class="container mt-2 pt-3 bg-dark">
			<div class="row">
				<div class="col"
					style="text-align: center; color: white; font-size: 90%;">
					<p>CUSTOMER ID</p>
				</div>
				<div class="col"
					style="text-align: center; color: white; font-size: 90%;">
					<p>CUSTOMER NAME</p>
				</div>
				<div class="col"
					style="text-align: center; color: white; font-size: 90%;">
					<p>CUSTOMER EMAIL</p>
				</div>
				<div class="col"
					style="text-align: center; color: white; font-size: 90%;">
					<p>DATE OF PURCHASE</p>
				</div>
			</div>
		</div>
		<div class="container mt-2 pt-3 bg-light">
			<div class="row">
				<div class="col"
					style="text-align: center; color: black; font-size: 85%;">
					<p>${user.ID_U}</p>
				</div>
				<div class="col"
					style="text-align: center; color: black; font-size: 85%;">
					<p>${user.lastName}</p>
				</div>
				<div class="col"
					style="text-align: center; color: black; font-size: 85%;">
					<p>${user.email}</p>
				</div>
				<div class="col"
					style="text-align: center; color: black; font-size: 85%;">
					<p>${bill.bill_date}</p>
				</div>
			</div>
		</div>

		<div class="container mt-2" id="former_owner_info">
			<p class="text-dark d-inline-block align-middle pt-2">
				<strong>Order detail</strong>
			</p>
		</div>
		<div class="container mt-2 pt-3 bg-dark">
			<div class="row">
				<div class="col"
					style="text-align: center; color: white; font-size: 90%;">
					<p>VEHICLE(S)</p>
				</div>
				<div class="col"
					style="text-align: center; color: white; font-size: 90%;">
					<p>UNIT PRICE</p>
				</div>
				<div class="col"
					style="text-align: center; color: white; font-size: 90%;">
					<p>QUANTITY</p>
				</div>
				<div class="col"
					style="text-align: center; color: white; font-size: 90%;">
					<p>PRICE PER QUANTITY</p>
				</div>
			</div>
		</div>
		<c:forEach items="${vehiclesReceipt}" var="item">
			<div class="container mt-2 pt-3 bg-light">
				<div class="row">
					<div class="col"
						style="text-align: center; color: black; font-size: 85%;">
						<p>${item.key.model}</p>
					</div>
					<div class="col"
						style="text-align: center; color: black; font-size: 85%;">
						<p><fmt:formatNumber type="number" pattern="##,###.##" value="${item.key.price}"/>€</p>
					</div>
					<div class="col"
						style="text-align: center; color: black; font-size: 85%;">
						
						<p>${item.value}</p>
						
					</div>
					<div class="col"
						style="text-align: center; color: black; font-size: 85%;">
						<p><fmt:formatNumber type="number" pattern="##,###.##" value="${item.value*item.key.price}"/>€</p>
					</div>
				</div>
			</div>
		</c:forEach>

		<div class="container mt-2" id="former_owner_info">
			<p class="text-dark d-inline-block align-middle pt-2">
				<strong>Order total</strong>
			</p>
		</div>
		<div class="container mt-2 pt-3 bg-dark">
			<div class="row">
				<div class="col"
					style="text-align: left; color: white; font-size: 90%;">
					<p>Amount incl.TAX</p>
				</div>
			</div>
		</div>
		<div class="container mt-2 pt-3 bg-light">
			<div class="row">
				<div class="col"
					style="text-align: left; color: black; font-size: 85%;">
					<p><fmt:formatNumber type="number" pattern="##,###.##" value="${bill.bill_amount}"/>€</p>
				</div>
			</div>
		</div>
		<div class="container mt-2 pt-3 bg-dark">
			<div class="row">
				<div class="col"
					style="text-align: left; color: white; font-size: 90%;">
					<p>Tax amount</p>
				</div>
			</div>
		</div>
		<div class="container mt-2 pt-3 bg-light">
			<div class="row">
				<div class="col"
					style="text-align: left; color: black; font-size: 85%;">
					<p><fmt:formatNumber type="number" pattern="##,###.##" value="${tax_amount_receipt}"/>€</p>
				</div>
			</div>
		</div>
		<div class="container mt-2 pt-3 bg-dark">
			<div class="row">
				<div class="col"
					style="text-align: left; color: white; font-size: 90%;">
					<p>Shipping country</p>
				</div>
			</div>
		</div>
		<div class="container mt-2 pt-3 bg-light">
			<div class="row">
				<div class="col"
					style="text-align: left; color: black; font-size: 85%;">
					<p>${bill.country}</p>
				</div>
			</div>
		</div>

		<div class="container mt-2 pt-3 bg-dark">
			<div class="row">
				<div class="col"
					style="text-align: left; color: white; font-size: 90%;">
					<p>Payment method</p>
				</div>
			</div>
		</div>
		<div class="container mt-2 pt-3 bg-light">
			<div class="row">
				<div class="col"
					style="text-align: left; color: black; font-size: 85%;">
					<p>
						<c:if test="${bill.payment_method =='CREDIT_REQUEST'}">
							<c:out value="Credit request" />
						</c:if>
						<c:if test="${bill.payment_method =='CREDIT_CARD'}">
							<c:out value="Credit card" />
						</c:if>
					</p>
				</div>
			</div>
		</div>
		<div class="container mt-2 pt-3 bg-dark">
			<div class="row">
				<div class="col"
					style="text-align: left; color: white; font-size: 90%;">
					<p>Estimated arrival time</p>
				</div>
			</div>
		</div>
		<div class="container mt-2 pt-3 bg-light">
			<div class="row">
				<div class="col"
					style="text-align: left; color: black; font-size: 85%;">
					<p>
						<c:if test="${bill.delivery_date == null }">
							<c:out
								value="Your order must be paid and validated to be delivered." />
						</c:if>
						<c:if test="${bill.delivery_date != null }">
							<c:out value="${bill.delivery_date}" />
						</c:if>
					</p>
				</div>
			</div>
		</div>
		<div class="container py-5">
			<p>
				<strong><em>Thank you for your purchase! Please keep
						this document carefully.</em></strong>
			</p>
		</div>
	</div>
</body>
<script>
	function generatePDF() {
		const element = document.getElementById("order_form");
		var opt = {
			margin : 2,
			filename : 'order_form.pdf',
			html2canvas : {
				scale : 8
			},
			pagebreak : {
				mode : 'avoid-all'
			}

		};
		html2pdf().from(element).set(opt).save();
	}
</script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js"></script>
</html>