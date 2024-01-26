<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- Author : Yassine -->
<html>
<body>
	<%@ include file="navbar.jsp"%>
	<div id="carousel" class="carousel slide carousel-fade"
		data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="https://wallpapercave.com/wp/wp3743284.png"
					class="d-block w-100" alt="...">
				<div class="container">
					<div class="carousel-caption bg-dark">
						<h1 style='color: white'>Welcome to Wiggler Kart</h1>
						<p>What drives you to us ?</p>
					</div>
				</div>
			</div>
			<div class="carousel-item">
				<img
					src="https://lemagsportauto.ouest-france.fr/wp-content/uploads/2022/07/MK8DBCP_w2_scrn_WaluigiPinball_01.jpg"
					class="d-block w-100" alt="...">
				<div class="container">
					<div class="carousel-caption bg-dark">
						<h1 style='color: white'>About us</h1>
						<p>Our company will lead the way to the future of mobility,
							enriching lives around the world with the safest and most
							responsible ways of moving people</p>
					</div>
				</div>
			</div>
			<div class="carousel-item">
				<img src="https://images7.alphacoders.com/519/thumb-1920-519876.jpg"
					class="d-block w-100" alt="...">
				<div class="container">
					<div class="carousel-caption bg-dark">
						<h1 style='color: white'>Our products</h1>
						<a class="btn btn-light btn-lg"
							href="<%=request.getContextPath() + "/Catalog"%>" role="button">Check
							out the catalog</a>
					</div>
				</div>
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carousel" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carousel" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>
</body>
<%@ include file="footer.jsp"%>
</body>
</html>