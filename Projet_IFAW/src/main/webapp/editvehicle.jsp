<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Author : Darlène -->
<!DOCTYPE html>
<html>
<body>
	<%@ include file="navbar.jsp"%>
	<c:if test="${isAdmin == true && sessionScope.user != null }">
	<div class="container pt-2 pb-5">
		<form action="EditVehicle" method="post">
			<fieldset>
				<legend class="mt-4">
					<strong>Edit a vehicle of the inventory</strong>
				</legend>
				<c:if test="${hasEdited==true}">
					<div class="alert alert-dismissible alert-success">
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
						<strong>The vehicle information has been updated successfully</strong>
					</div>
				</c:if>
				<c:if test="${hasEdited==false}">
					<div class="alert alert-dismissible alert-danger">
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
						<strong>Something wrong happened, please try again</strong>
					</div>
				</c:if>
				<div class="form-group">
					<label class="col-form-label mt-0" for="picture">Picture</label> <input
						name="picture" type="text" class="form-control"
						placeholder="Please, enter the web address of the picture"
						id="picture" value="<c:out value='${existingvehicle.picture}' />">
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="type">Type</label> <select
						name="type" class="form-select" id="exampleSelect1">
						<option value="Car"
							${existingvehicle.type eq 'Car' ? 'selected' : ''}>Car</option>
						<option value="Motorcycle"
							${existingvehicle.type eq 'Motorcycle' ? 'selected' : ''}>Motorcycle</option>
					</select>
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="brand">Brand</label> <input
						type="text" name="brand" class="form-control" placeholder="Brand"
						id="inputDefault"
						value="<c:out value='${existingvehicle.brand}' />">
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="model">Model</label> <input
						type="text" name="model" class="form-control" placeholder="Model"
						id="inputDefault"
						value="<c:out value='${existingvehicle.model}' />">
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="gearbox">Gearbox</label> <select
						class="form-select" name="gearbox">
						<option value="Automatic"
							${existingvehicle.gearbox eq 'Automatic' ? 'selected' : ''}>Automatic</option>
						<option value="Manual"
							${existingvehicle.gearbox eq 'Manual' ? 'selected' : ''}>Manual</option>
					</select>
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="category">Category</label>
					<input type="text" class="form-control" placeholder="Category"
						id="inputDefault" name="category"
						value="<c:out value='${existingvehicle.category}' />">
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="engine">Engine</label> <select
						class="form-select" name="engine">
						<option value="Electric"
							${existingvehicle.engine eq 'Electric' ? 'selected' : ''}>Electric</option>
						<option value="Gasoline"
							${existingvehicle.engine eq 'Gasoline' ? 'selected' : ''}>Gasoline</option>
					</select>
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="nb_seats">No. of
						seats</label> <select class="form-select" name="nb_seats">
						<!-- <option selected="<c:out value='${existingvehicle.nb_seats}'/>">${existingvehicle.nb_seats}</option> -->
						<option value=1 ${existingvehicle.nb_seats eq 1 ? 'selected' : ''}>1</option>
						<option value=2 ${existingvehicle.nb_seats eq 2 ? 'selected' : ''}>2</option>
						<option value=3 ${existingvehicle.nb_seats eq 3 ? 'selected' : ''}>3</option>
						<option value=4 ${existingvehicle.nb_seats eq 4 ? 'selected' : ''}>4</option>
						<option value=5 ${existingvehicle.nb_seats eq 5 ? 'selected' : ''}>5</option>
						<option value=6 ${existingvehicle.nb_seats eq 6 ? 'selected' : ''}>6</option>
						<option value=7 ${existingvehicle.nb_seats eq 7 ? 'selected' : ''}>7</option>
					</select>
				</div>
				<div class="form-group">
					<label for="description" class="form-label mt-4">Description</label>
					<textarea class="form-control" id="TextareaDescription" rows="3"
						name="description"><c:out
							value='${existingvehicle.description}' /></textarea>
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="price">Price</label>
					<div class="input-group mb-3">
						<input type="text" class="form-control" name="price"
							value="<c:out value='${existingvehicle.price}' />"> <span
							class="input-group-text">€</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-form-label mt-0" for="stock">Stock</label> <select
						class="form-select" name="stock">
						<option value=10
							${existingvehicle.stock eq 10 ? 'selected' : ''}>10</option>
						<option value=20
							${existingvehicle.stock eq 20 ? 'selected' : ''}>20</option>
						<option value=30
							${existingvehicle.stock eq 30 ? 'selected' : ''}>30</option>
						<option value=40
							${existingvehicle.stock eq 40 ? 'selected' : ''}>40</option>
						<option value=50
							${existingvehicle.stock eq 50 ? 'selected' : ''}>50</option>
						<option value=60
							${existingvehicle.stock eq 60 ? 'selected' : ''}>60</option>
						<option value=70
							${existingvehicle.stock eq 70 ? 'selected' : ''}>70</option>
						<option value=80
							${existingvehicle.stock eq 80 ? 'selected' : ''}>80</option>
						<option value=90
							${existingvehicle.stock eq 90 ? 'selected' : ''}>90</option>
						<option value=100
							${existingvehicle.stock eq 100 ? 'selected' : ''}>100</option>
						<option value=110
							${existingvehicle.stock eq 110 ? 'selected' : ''}>110</option>
						<option value=120
							${existingvehicle.stock eq 120 ? 'selected' : ''}>120</option>
						<option value=130
							${existingvehicle.stock eq 130 ? 'selected' : ''}>130</option>
						<option value=140
							${existingvehicle.stock eq 140 ? 'selected' : ''}>140</option>
						<option value=150
							${existingvehicle.stock eq 150 ? 'selected' : ''}>150</option>
						<option value=160
							${existingvehicle.stock eq 160 ? 'selected' : ''}>160</option>
						<option value=170
							${existingvehicle.stock eq 170 ? 'selected' : ''}>170</option>
						<option value=180
							${existingvehicle.stock eq 180 ? 'selected' : ''}>180</option>
						<option value=190
							${existingvehicle.stock eq 190 ? 'selected' : ''}>190</option>
						<option value=200
							${existingvehicle.stock eq 200 ? 'selected' : ''}>200</option>
					</select>
				</div>
				<button type="submit" class="btn btn-success mt-4">Edit
					vehicle</button>
			</fieldset>
		</form>
	</div>
	</c:if>
	<%@ include file="footer.jsp"%>
</body>
<script>
function defaultDescription() {
  document.getElementById("TextareaDescription").defaultValue = "<c:out value='${existingvehicle.description}'/>";
}
</script>
</html>