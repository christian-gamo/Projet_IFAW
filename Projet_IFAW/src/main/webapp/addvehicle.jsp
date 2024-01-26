<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Author : Darlène -->
<!DOCTYPE html>
<html>
<body>
	<%@ include file="navbar.jsp"%>
	<c:if test="${isAdmin == true  && sessionScope.user != null }">
	<div class="container pt-2 pb-5">
		<form action="AddVehicle" method="post">
			<fieldset>
				<legend class="mt-4">
					<strong>Add a vehicle to the inventory</strong>
				</legend>
				<em>Please make sure that the model of the vehicle you want to
					add don't already exists in the inventory</em>
				<c:if test="${hasAdded==true}">
					<div class="alert alert-dismissible alert-success">
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
						<strong>The vehicle has been successfully added to the
							inventory</strong>
					</div>
				</c:if>
				<c:if test="${hasAdded==false}">
					<div class="alert alert-dismissible alert-danger">
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
						<strong>Something wrong happened, please try again</strong>
					</div>
				</c:if>
				<div class="form-group">
					<label class="col-form-label mt-0" for="picture">Picture</label> <input
						name="picture" type="text" class="form-control"
						placeholder="Please, enter the web address of the picture"
						id="picture">
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="type">Type</label> <select
						name="type" class="form-select" id="exampleSelect1">
						<option value="Car">Car</option>
						<option value="Motorcycle">Motorcycle</option>
					</select>
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="brand">Brand</label> <input
						type="text" name="brand" class="form-control" placeholder="Brand"
						id="inputDefault">
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="model">Model</label> <input
						type="text" name="model" class="form-control" placeholder="Model"
						id="inputDefault">
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="gearbox">Gearbox</label> <select
						class="form-select" name="gearbox">
						<option value="Automatic">Automatic</option>
						<option value="Manual">Manual</option>
					</select>
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="category">Category</label>
					<input type="text" class="form-control" placeholder="Category"
						id="inputDefault" name="category">
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="engine">Engine</label> <select
						class="form-select" name="engine">
						<option value="Electric">Electric</option>
						<option value="Gasoline">Gasoline</option>
					</select>
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="nb_seats">No. of
						seats</label> <select class="form-select" name="nb_seats">
						<option value=1>1</option>
						<option value=2>2</option>
						<option value=3>3</option>
						<option value=4>4</option>
						<option value=5>5</option>
						<option value=6>6</option>
						<option value=7>7</option>
					</select>
				</div>
				<div class="form-group">
					<label for="description" class="form-label mt-4">Description</label>
					<textarea class="form-control" id="exampleTextarea" rows="3"
						name="description"></textarea>
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="price">Price</label>
					<div class="input-group mb-3">
						<input type="text" class="form-control" name="price"> <span
							class="input-group-text">€</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-form-label mt-0" for="stock">Stock</label> <select
						class="form-select" name="stock">
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
				<button type="submit" class="btn btn-success mt-4">Add
					vehicle</button>

			</fieldset>
		</form>
	</div>
	</c:if>
	<div class="container pb-5 mb-5"></div>
	<div class="container pb-5 mb-5"></div>
	<%@ include file="footer.jsp"%>
</body>
</html>