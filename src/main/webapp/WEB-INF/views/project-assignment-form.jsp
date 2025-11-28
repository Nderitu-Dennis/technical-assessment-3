<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Assignment Form</title>
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
			<div class="card-header h2 text-primary bg-secondary">Project Assignment Form</div>
			<div class="card-body">
				<form id="assignmentForm" action="./save-assignment" method="post" novalidate>
					<div class="row">						
						<!-- Department Dropdown -->
						<div class="col-4 mb-3">
							<label for="departmentId" class="font-weight-bold">Department</label>
							<select id="departmentId" name="departmentId" class='form-control'>
								<option value='0'>-select-</option>
								<c:forEach items="${departments}" var="d">
									<option value="${d.departmentId}">${d.departmentName}</option>
								</c:forEach>
							</select>
							<div class="invalid-feedback">Please select a department</div>
						</div>

						<!-- Team Dropdown -->
						<div class="col-4 mb-3">
							<label for="teamId" class="font-weight-bold">Team</label>
							<select id="teamId" name="teamId" class='form-control' disabled>
								<option value='0'>-select-</option>
							</select>
							<div class="invalid-feedback">Please select a team</div>
						</div>

						<!-- Project Dropdown 
						<div class="col-4 mb-3">
							<label for="projectId" class="font-weight-bold">Project</label>
							<select id="projectId" name="projectId" class='form-control' disabled>
								<option value='0' disabled>-select-</option>
							</select>
							<input type="hidden" id="projectIdHidden" name="projectId" value="project.projectId">
							<div class="invalid-feedback" id="projectFeedback">Please select a project</div>
						</div> -->
						
						<!-- Project Dropdown -->
						<div class="col-4 mb-3">
							<label for="projectId" class="font-weight-bold">Project</label>
							<select id="projectId" name="projectId" class='form-control'>
								<option value='0'>-select-</option>
							</select>
							<div class="invalid-feedback">Please select a project</div>
						</div>

						<!-- Employee Dropdown -->
						<div class="col-6 mb-3">
							<label for="employeeId" class="font-weight-bold">Employee</label>
							<select id="employeeId" name="employeeId" class='form-control'>
								<option value='0'>-select-</option>
								<c:forEach items="${employees}" var="e">
									<option value="${e.employeeId}">${e.fullName}</option>
								</c:forEach>
							</select>
							<input type="hidden" id="employeeIdHidden" name="employee.employeeId">
						</div>

						<!-- Role on Project -->
						<div class="col-6 mb-3">
							<label for="roleOnProject" class="font-weight-bold">Role on Project</label>
							<input type="text" name="roleOnProject" id="roleOnProject" class="form-control">
						</div>

						<!-- Allocation % -->
						<div class="col-3 mb-3">
							<label for="allocationPercent" class="font-weight-bold">Allocation %</label>
							<input type="number" name="allocationPercent" id="allocationPercent" class="form-control"
							       step="0.01" min="1" max="100">
						</div>

						<!-- Start Date -->
						<div class="col-3 mb-3">
							<label for="startDate" class="font-weight-bold">Start Date</label>
							<input type="date" name="startDate" id="startDate" class="form-control">
						</div>

						<!-- End Date -->
						<div class="col-3 mb-3">
							<label for="endDate" class="font-weight-bold">End Date</label>
							<input type="date" name="endDate" id="endDate" class="form-control">
						</div>

						<!-- Status -->
						<div class="col-3 mb-3">
							<label for="status" class="font-weight-bold">Status</label>
							<select name="status" id="status" class='form-control'>
								<option value="Active">Active</option>
								<option value="Inactive">Inactive</option>
							</select>
						</div>
					</div>

					<div class="text-center mt-3">
						<input type="submit" class="btn btn-success" value='Save'>
						<input type="reset" class="btn btn-warning" value="Reset">
					</div>
				</form>
			</div>
		</div>
	</div>

<script src="https://code.jquery.com/jquery-2.2.4.js"
        integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" crossorigin="anonymous"></script>

<script>
$(document).ready(function() {
    // When Department changes, fetch Teams
    $("#departmentId").change(function() {
        var deptId = $(this).val();
        $("#teamId").prop('disabled', true).html("<option value='0'>-select-</option>");
        $("#projectId").prop('disabled', true).html("<option value='0'>-select-</option>");
        if(deptId > 0) {
            $.ajax({
                url: "teams-by-department",  // Controller endpoint
                type: "GET",
                data: { departmentId: deptId },
                success: function(response) {
                    if(response.length > 0) {
                        $.each(response, function(i, team) {
                            $("#teamId").append("<option value='"+team.teamId+"' data-status='"+team.status+"'>"+team.teamName+"</option>");
                        });
                        $("#teamId").prop('disabled', false);
                    }
                }
            });
        }
    });

    // When Department changes, fetch Teams
    $("#teamId").change(function() {
        var teamId = $(this).val();
        $("#projectId").prop('disabled', false).html("<option value='0'>-select-</option>");
        $("#employeeId").prop('disabled', true).html("<option value='0'>-select-</option>");
        if(teamId > 0) {
            $.ajax({
                url: "projects-by-team",  // Controller endpoint
                type: "GET",
                data: { teamId: teamId },
                success: function(response) {
                    if(response.length > 0) {
                        $.each(response, function(i, project) {
                        	console.log(project);
                            $("#projectId").append("<option value='"+project.projectId+"' data-status='"+project.status+"'>"+project.projectName+"</option>");
                        });
                        $("#projectId").prop('disabled', false);
                    }
                }
            });
        }
    });

 // When Project changes, fetch Employees of selected Team
    $("#projectId").change(function() {
        var teamId = $("#teamId").val();  // FIX: correctly getting teamId

        $("#employeeId").prop('disabled', false)
                        .html("<option value='0'>-select-</option>");
        
    });

 
    
    // Update hidden fields when dropdowns change
    $("#projectId").change(function() {
        $("#projectIdHidden").val($(this).val());
    });

    $("#employeeId").change(function() {
        $("#employeeIdHidden").val($(this).val());
    });

    // FIX: Sync hidden fields before form submission
    $("#assignmentForm").submit(function() {
        $("#projectIdHidden").val($("#projectId").val());
        $("#employeeIdHidden").val($("#employeeId").val());
        return true;
    });
});
</script>

</body>
</html>