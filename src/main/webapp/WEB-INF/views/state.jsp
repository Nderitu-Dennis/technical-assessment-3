<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<div class="container mt-5">
		<c:if test="${msg ne null}">
			<div id='alertId' class='alert alert-success'>
				<span class='font-weight-bold'>Message : </span> ${msg}
			</div>

		</c:if>


		<div class="card">
			<div class="card-header">
				<div class="h3 text-primary">State Data Form</div>
			</div>
			<div class="card-body">
				<div class="container">
					<form action="./save-state" method="post">
						<%-- <input type="hidden" name="id" id="id" value="${co.id}"> --%>
						<div class="row">
							<div class="col-4">
								<label for="countryId">Country Name</label> <select
									name="country" id="country" class="form-control">
									<option>-select-</option>
									<c:forEach items="${countries}" var="c">
										<option value="${c.id}">${c.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-4">
								<label for="stateId">State Name</label> <input type="text"
									id="name" name="name" class="form-control" value="${co.name}">
							</div>
						</div>
						<div class="text-center mt-3">
							<input type="submit" class="btn btn-success" value="add">
							<input type="reset" class="btn btn-warning">


						</div>





					</form>




				</div>

			</div>

		</div>

		<div class="h3 text-primary mt5">Country List</div>
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Sl.#</th>
					<th>Id</th>
					<th>Name</th>
					<th>Action</th>
				</tr>

			</thead>
			<tbody>
				<c:forEach items="${countries}" var="c" varStatus="counter">
					<tr>
						<td>${counter.count}</td>
						<td>${c.id}</td>
						<td>${c.name}</td>
						<td><a href="./delete-country?cId=${c.id}"
							class="text-danger">Del</a> | <a
							href="./update-country?cId=${c.id}" class="text-warning">Update</a>
						</td>
					</tr>
				</c:forEach>

			</tbody>


		</table>

	</div>



	<script src="https://code.jquery.com/jquery-2.2.4.js"
		integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
		crossorigin="anonymous"></script>
	<script>
		 document.addEventListener("DOMContentLoaded", function(event){
			var al=document.querySelector("#alertId");
			if(al != null){
				setTimeout(() => {
					al.remove();
				}, 3000);
			}			
		});
	</script>
</body>
</html>