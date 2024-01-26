<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Author : Christian -->
<!DOCTYPE html>
<html>
<style>
.jumbotron {
	background-color: #b9bec4;
}

.container-vehicles {
	background-color: #d0d4d9;
}

#sidebar {
	background-color: #d0d4d9;
}
</style>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="jumbotron my-0 pb-1">
		<h1 class="display-3 text-center pt-5 ">Vehicle Catalog</h1>
		<hr class="my-5">
		<p class="text-center pb-4">Quality vehicles, exclusively on sale
			on our website</p>
	</div>
	<div class="container-vehicles py-5 px-3">
		<c:if test="${ not empty catalog_error}">
			<div class="alert alert-dismissible alert-danger">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong><c:out value="${ catalog_error }"></c:out></strong>
			</div>
		</c:if>
		<c:if test="${ not empty catalog_success}">
			<div class="alert alert-dismissible alert-success">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong><c:out value="${ catalog_success }"></c:out></strong>
			</div>
		</c:if>
		<div class="row" id="sidebar">
			<div class="col-2 px-2 mx-2 pt-5">
				<div class="d-flex-lg-row">
					<form action="Catalog" method="post">
						<div class="accordion" id="accordion">
							<div class="accordion-item">
								<h2 class="accordion-header" id="headingOne">
									<button class="accordion-button collapsed" type="button"
										data-bs-toggle="collapse" data-bs-target="#collapseOne"
										aria-expanded="false" aria-controls="collapseOne">
										Type of vehicle</button>
								</h2>
								<div id="collapseOne" class="accordion-collapse collapse"
									aria-labelledby="headingOne" data-bs-parent="#accordion">
									<div class="accordion-body">
										<fieldset class="form-group">
											<div class="form-check">
												<input class="form-check-input" type="checkbox" value="Car"
													id="checkboxCar" name="Car"> <label
													class="form-check-label" for="flexCheckDefault">
													Car </label>
											</div>
											<div class="form-check">
												<input class="form-check-input" type="checkbox"
													value="Motorcycle" id="checkboxMotorcycle"
													name="Motorcycle"> <label class="form-check-label"
													for="flexCheckDefault"> Motorcycle </label>
											</div>
										</fieldset>
									</div>
								</div>
							</div>
							<div class="accordion-item" id="accordion-item-2">
								<h2 class="accordion-header" id="headingTwo">
									<button class="accordion-button collapsed" type="button"
										data-bs-toggle="collapse" data-bs-target="#collapseTwo"
										aria-expanded="false" aria-controls="collapseTwo">
										Type of fuel</button>
								</h2>
								<div id="collapseTwo" class="accordion-collapse collapse"
									aria-labelledby="headingTwo" data-bs-parent="#accordion">
									<div class="accordion-body">
										<fieldset class="form-group">
											<div class="form-check">
												<input class="form-check-input" type="checkbox"
													value="Electric" id="checkboxElectric" name="Electric">
												<label class="form-check-label" for="flexCheckDefault">
													Electric </label>
											</div>
											<div class="form-check">
												<input class="form-check-input" type="checkbox"
													value="Gasoline" id="checkboxGasoline" name="Gasoline">
												<label class="form-check-label" for="flexCheckDefault">
													Gasoline </label>
											</div>
										</fieldset>
									</div>
								</div>
							</div>
							<div class="accordion-item" id="accordion-item-3">
								<h2 class="accordion-header" id="headingThree">
									<button class="accordion-button collapsed" type="button"
										data-bs-toggle="collapse" data-bs-target="#collapseThree"
										aria-expanded="false" aria-controls="collapseThree">
										Type of gearbox</button>
								</h2>
								<div id="collapseThree" class="accordion-collapse collapse"
									aria-labelledby="headingThree" data-bs-parent="#accordion">
									<div class="accordion-body">
										<fieldset class="form-group">
											<div class="row px-1">
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														value="Manual" id="checkboxManual" name="Manual">
													<label class="form-check-label" for="flexCheckDefault">
														Manual </label>
												</div>
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														value="Automatic" id="checkboxAutomatic" name="Automatic">
													<label class="form-check-label" for="flexCheckDefault">
														Automatic </label>
												</div>
											</div>
										</fieldset>
									</div>
								</div>
							</div>
							<div class="accordion-item" id="accordion-item-4">
								<h2 class="accordion-header" id="headingFour">
									<button class="accordion-button collapsed" type="button"
										data-bs-toggle="collapse" data-bs-target="#collapseFour"
										aria-expanded="false" aria-controls="collapseFour">
										Number of seats</button>
								</h2>
								<div id="collapseFour" class="accordion-collapse collapse"
									aria-labelledby="headingFour" data-bs-parent="#accordion">
									<div class="accordion-body">
										<fieldset class="form-group">
											<div class="row mx-1">
												<input type="text" id="textInput1" name="nb_seats" value=""
													style="width: 140px;" placeholder="No. of seats" readonly>
											</div>
											<div class="row mx-1">
												<input type="range" class="form-range" style="width: 100px;"
													id="seats-range" min="0" max="7"
													onchange="updateTextInput1(this.value);">
											</div>
										</fieldset>
									</div>
								</div>
							</div>
							<div class="accordion-item" id="accordion-item-5">
								<h2 class="accordion-header" id="headingFive">
									<button class="accordion-button collapsed" type="button"
										data-bs-toggle="collapse" data-bs-target="#collapseFive"
										aria-expanded="false" aria-controls="collapseFive">
										Special offers</button>
								</h2>
								<div id="collapseFive" class="accordion-collapse collapse"
									aria-labelledby="headingFive" data-bs-parent="#accordion">
									<div class="accordion-body">
										<fieldset class="form-group">
											<div class="form-check">
												<input class="form-check-input" type="checkbox"
													name="on_sale" value="true"> <label
													class="form-check-label">On sale</label>
											</div>
											<div class="form-check">
												<input class="form-check-input" type="checkbox"
													name="normal_price" value="false"> <label
													class="form-check-label">Normal price</label>
											</div>
										</fieldset>
									</div>
								</div>
							</div>
							<div class="accordion-item" id="accordion-item-6">
								<h2 class="accordion-header" id="headingSix">
									<button class="accordion-button collapsed" type="button"
										data-bs-toggle="collapse" data-bs-target="#collapseSix"
										aria-expanded="false" aria-controls="collapseSix">
										Price</button>
								</h2>
								<div id="collapseSix" class="accordion-collapse collapse"
									aria-labelledby="headingSix" data-bs-parent="#accordion">
									<div class="accordion-body">
										<fieldset class="form-group">
											<div class="row mx-1">
												<input type="text" id="textInput2" style="width: 140px;"
													placeholder="Max price" value="" name="price" readonly>
											</div>
											<div class="row mx-1">
												<input type="range" class="form-range" style="width: 100px;"
													id="price-range" min="1" max="1000000"
													onchange="updateTextInput2(this.value);">
											</div>
										</fieldset>
									</div>
								</div>
							</div>
							<button type="submit" class="btn btn-success mt-3">Search</button>
						</div>
					</form>
				</div>
			</div>

			<div class="col-9 mx-1">
				<div class="row mx-5">
					<c:if test="${ not empty vehicles}">
						<div class="card-group">
							<c:forEach items="${vehicles}" var="item">
								<div class="col-4 pe-2">
									<div class="card mb-3">
										<h4 class="card-header">${item.model}</h4>
										<c:if test="${item.on_sale == true}">
											<span class="badge bg-warning"> On sale (-30%
												discount)</span>
										</c:if>
										<c:if test="${item.on_sale == false}">
											<span class="badge bg-info">Normal price</span>
										</c:if>
										<div class="card-body">
											<h5 class="card-title"><fmt:formatNumber type="number" pattern="##,###.##" value="${item.price}"/>€</h5>
											<h6 class="card-subtitle text-muted">${item.category}</h6>
										</div>
										<img src="${item.picture}" class="d-block user-select-none"
											width="100%" height="200"
											preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180"
											style="font-size: 1.125rem; text-anchor: middle">

										<rect width="100%" height="100%" fill="#868e96"></rect>
										<div class="card-body">
											<p class="card-text">${item.description}</p>
										</div>
										<ul class="list-group list-group-flush">
											<li class="list-group-item">Gearbox : ${item.gearbox}</li>
											<li class="list-group-item">Fuel : ${item.engine}</li>
											<li class="list-group-item">No. of seats :
												${item.nb_seats}</li>
										</ul>
										<div class="card-footer mb-0">Stock : ${item.stock}</div>
										<c:choose>
											<c:when test="${not empty user && isAdmin == false && isCompany == false}">
												<div class="card-body">

													<p>Quantity</p>
													<form action="AddToCart" method="post">
														<div>
															<input name="id_vehicle" type="hidden"
																value="${item.ID_V}"> <input type="number"
																step="1" min="1" max="${item.stock}" value="1"
																name="quantity" class="form-control">
														</div>
														<button class="btn btn-success mt-4 px-4" type="submit">
															Add to cart</button>
													</form>
												</div>
											</c:when>
											<c:when test="${not empty user && isAdmin == false && isCompany == true}">
												<div class="card-body">
													<p>Fleet of vehicles</p>
													<form action="AddToCart" method="post">
														<div>
															<input name="id_vehicle" type="hidden"
																value="${item.ID_V}"> 
																<select
																class="form-select" name="quantity">
																<option value=10>10</option>
																<option value=20>20</option>
																<option value=30>30</option>
																<option value=40>40</option>
																<option value=50>50</option>
																<option value=60>60</option>
																<option value=70>70</option>
																<option value=80>80</option>
																<option value=90>90</option>
																<option value=100>100</option>
																<option value=110>110</option>
																<option value=120>120</option>
																<option value=130>130</option>
																<option value=140>140</option>
																<option value=150>150</option>
																<option value=160>160</option>
																<option value=170>170</option>
																<option value=180>180</option>
																<option value=190>190</option>
																<option value=200>200</option>
															</select>
														</div>
														<button class="btn btn-success mt-4 px-4" type="submit">
															Add to cart</button>
													</form>
												</div>
											</c:when>
											<c:when test="${empty user || isAdmin == true}">
												<div class="card-body">
													<div class="row">
														<button type="button" class="btn btn-dark disabled">Login
															as a customer to add a vehicle to your cart</button>
													</div>
												</div>
											</c:when>
										</c:choose>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:if>
					<c:if test="${empty vehicles}">
						<div class="jumbotron">
							<h3 class="text-center mt-3">
								<strong> We are sorry ! </strong>
							</h3>
							<p class="text-center">We can't seem to find any products
								that match your search</p>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
<script type="text/javascript">
	function updateTextInput1(val) {
		document.getElementById('textInput1').value = val;
	}
	function updateTextInput2(val) {
		document.getElementById('textInput2').value = val;
	}
</script>
</html>
