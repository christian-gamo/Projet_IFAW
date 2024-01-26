<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div id="vehicle_transfer_certificate">
		<div class="container-lg bg-dark mt-5 pt-4">
			<div class="row">
				<div class="col pt-1">
					<img src="./img/logowiggler.png" width="90"
						class="d-inline-block align-middle mr-2"></img>
					<h5 class="text-light d-inline-block align-middle">Wiggler
						Kart</h5>
				</div>
				<div class="col pt-1">
					<h2 class="text-light d-inline-block align-middle py-4">Vehicle
						transfer certificate</h2>
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
				<strong>Former owner information</strong>
			</p>
		</div>

		<div class="container mt-2 pt-3 bg-dark">
			<div class="row">
				<div class="col"
					style="text-align: center; color: white; font-size: 90%;">
					<p>COMPANY NAME</p>
				</div>
				<div class="col"
					style="text-align: center; color: white; font-size: 90%;">
					<p>COMPANY LOCATION</p>
				</div>
				<div class="col"
					style="text-align: center; color: white; font-size: 90%;">
					<p>HEAD OF THE COMPANY</p>
				</div>
			</div>
		</div>
		<div class="container mt-2 pt-3 bg-light">
			<div class="row">
				<div class="col"
					style="text-align: center; color: black; font-size: 85%;">
					<p>Wiggler Kart Co. Ltd</p>
				</div>
				<div class="col"
					style="text-align: center; color: black; font-size: 85%;">
					<p>Tower 42, 25 Old Broad St, London EC2N 1HN, United Kingdom</p>
				</div>
				<div class="col"
					style="text-align: center; color: black; font-size: 85%;">
					<p>Wiggler Gardens (CEO)</p>
				</div>
			</div>
		</div>
		<div class="container mt-2" id="new_owner_info">
			<p class="text-dark d-inline-block align-middle pt-2">
				<strong>New owner information</strong>
			</p>
		</div>
		<div class="container mt-2 pt-3 bg-dark">
			<div class="row">
				<div class="col"
					style="text-align: center; color: white; font-size: 90%;">
					<p>NEW OWNER NAME</p>
				</div>
				<div class="col"
					style="text-align: center; color: white; font-size: 90%;">
					<p>NEW OWNER COUNTRY</p>
				</div>
			</div>
		</div>
		
		<div class="container mt-2 pt-3 bg-light">
			<div class="row">
				<div class="col"
					style="text-align: center; color: black; font-size: 85%;">
					<p>${user.lastName} ${user.firstName}</p>
				</div>
				<div class="col"
					style="text-align: center; color: black; font-size: 85%;">
					<p>${bill.country}</p>
				</div>
			</div>
		</div>
		<div class="container pt-3">
			<p>
				<strong><em>This document is to certificate that the
						vehicle owner has changed.</em></strong>
			</p>
		</div>

		<div class="container text-center py-5 my-5">
			<div class="row">
				<div class="col">
					<strong><em>Former owner signature</em></strong>
				</div>
				<div class="col">
					<strong><em>New owner signature</em></strong>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<img src="./img/backup_signature.png" width="200" height="200">
					<p>
						<em>Date of signature : ${bill.bill_date}</em>
					</p>
				</div>
				<div class="col"></div>
			</div>
		</div>
	</div>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js"></script>
</body>
<script>
	function generatePDF() {
		const element = document.getElementById("vehicle_transfer_certificate");
		var opt = {
			margin : 2,
			filename : 'vehicle_transfer_certificate.pdf',
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
</html>