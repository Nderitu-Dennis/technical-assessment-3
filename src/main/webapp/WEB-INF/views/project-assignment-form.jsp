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

		<!-- FORM -->

		<div class="card">
			<div class="card-header h2 text-primary bg-secondary">Project
				Assignment Form</div>
			<div class="card-body">
				<form id="assignmentForm" action="./save-assignment" method="post"
					novalidate>
					<!-- novalidate is used when server side or JS validation is implemented -->
					<input type="hidden" name="assignmentId"
						value="${assignment.assignmentId}">
					<!-- for update -->

					<div class="row">
						<!-- Department Dropdown -->
						<div class="col-4 mb-3">
							<label for="departmentId" class="font-weight-bold">Department</label>
							<select id="departmentId" name="departmentId"
								class='form-control'>
								<option value='0'>-select department-</option>
								<c:forEach items="${departments}" var="d">
									<option value="${d.departmentId}"
										${d.departmentId == selectedDepartmentId ? "selected" : ""}>
										${d.departmentName}</option>
								</c:forEach>

							</select>
							<div class="invalid-feedback">Please select a department</div>
						</div>

						<!-- Team Dropdown -->
						<div class="col-4 mb-3">
							<label for="teamId" class="font-weight-bold">Team</label> <select
								id="teamId" name="teamId" class='form-control'>
								<option value='0'>-select-</option>
							</select>
							<div class="invalid-feedback">Please select a team</div>
						</div>


						<div class="col-4 mb-3">
							<label for="projectId" class="font-weight-bold">Project</label> <select
								id="projectId" name="project.projectId" class='form-control'>
								<option value='0'>-select-</option>
							</select>
							<div class="invalid-feedback" id="projectFeedback">Please
								select a project</div>
						</div>

						<!-- Employee Dropdown -->
						<div class="col-6 mb-3">
							<label for="employeeId" class="font-weight-bold">Employee</label>
							<select id="employeeId" name="employee.employeeId"
								class='form-control'>
								<option value='0'>-select employee-</option>
								<c:forEach items="${employees}" var="e">
									<option value="${e.employeeId}"
										${e.employeeId == selectedEmployeeId ? "selected" : ""}>
										${e.fullName}</option>
								</c:forEach>

							</select>
						</div>

						<!-- Role on Project -->
						<div class="col-6 mb-3">
							<label for="roleOnProject" class="font-weight-bold">Role
								on Project</label> <input type="text" name="roleOnProject"
								id="roleOnProject" class="form-control"
								value="${assignment.roleOnProject}">
						</div>

						<!-- Allocation % -->
						<div class="col-3 mb-3">
							<label for="allocationPercent" class="font-weight-bold">Allocation
								%</label> <input type="number" name="allocationPercent"
								id="allocationPercent" class="form-control" step="1" min="1"
								max="100" value="${assignment.allocationPercent}">
						</div>

						<!-- Start Date -->
						<div class="col-3 mb-3">
							<label for="startDate" class="font-weight-bold">Start
								Date</label> <input type="date" name="startDate" id="startDate"
								class="form-control" value="${assignment.startDate}">
						</div>

						<!-- End Date -->
						<div class="col-3 mb-3">
							<label for="endDate" class="font-weight-bold">End Date</label> <input
								type="date" name="endDate" id="endDate" class="form-control"
								value="${assignment.endDate}">
						</div>

						<!-- Status -->
						<div class="col-3 mb-3">
							<label for="status" class="font-weight-bold">Status</label> <select
								name="status" id="status" class='form-control'>
								<option value="Active"
									${assignment.status eq 'Active' ? 'selected' : ''}>Active</option>
								<option value="Inactive"
									${assignment.status eq 'Inactive' ? 'selected' : ''}>Inactive</option>
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

	<div class="h2 text m-5">
		View assigned projects <a href="get-assigned-projects-list">here</a>
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

	<script type="text/javascript">
	
	<!--initial real AJAX-->
	<!--

	$("#departmentId").change(function(e){
		
		$.ajax({
			  url: "http://localhost:8089/prjct/teams-by-department-id", //backend endpoint
			  type: "GET",  //GET req-params appended to the url & fetched by #RequestParam
			  data: {
				  departmentId : $(this).val()		     
			  },
			  success: function(response) { //returns JSON response
				  console.log(response)
				  
			      var teamId=$("#teamId");  //selects the teams 
			      $(teamId).find("option").remove(); //clears all existing options
			      $(teamId).append("<option value='0'>-select-</option>") //adds the default select option
				  $(response).each(function(i,e){
					  //loops thru each object in the JSON res, i is index & e is teams object here
			    	  $(teamId).append("<option value="+e.teamId+">"+e.teamName+"</option>");
			      });
			  },
			  error: function(xhr, status, error) {
			      console.error("Error submitting data: ", error);
			  }
			});
	});

	

$("#teamId").change(function(e){
		
		$.ajax({
			  url: "http://localhost:8089/prjct/projects-by-team-id",
			  type: "GET",
			  data: {
			      teamId: $(this).val()		     
			  },
			  success: function(response) {
				  console.log(response)
				  
			      var projectId=$("#projectId");
			      $(projectId).find("option").remove();
			      $(projectId).append("<option value='0'>-select-</option>")
				  $(response).each(function(i,e){
			    	  $(projectId).append("<option value="+e.projectId+">"+e.projectName+"</option>");
			      });
			  },
			  error: function(xhr, status, error) {
			      console.error("Error submitting data: ", error);
			  }
			});
	});
	
	-->
	<!--Refactored AJAX to handle edits/updates	-->
	function loadTeams(departmentId, selectedTeamId, selectedProjectId) {
	    $.ajax({
	        url: "http://localhost:8089/prjct/teams-by-department-id",
	        type: "GET",
	        data: { departmentId: departmentId },
	        success: function(response) {
	            let team = $("#teamId");
	            team.empty().append("<option value='0'>-select-</option>");
	            $(response).each(function(i, t){
	                let selected = t.teamId == selectedTeamId ? "selected" : "";
	                team.append("<option "+selected+" value='"+t.teamId+"'>"+t.teamName+"</option>");
	            });
	            if(selectedTeamId > 0) {
	                loadProjects(selectedTeamId, selectedProjectId);
	            }
	        }
	    });
	}

	function loadProjects(teamId, selectedProjectId) {
	    $.ajax({
	        url: "http://localhost:8089/prjct/projects-by-team-id",
	        type: "GET",
	        data: { teamId: teamId },
	        success: function(response) {
	            let project = $("#projectId");
	            project.empty().append("<option value='0'>-select-</option>");
	            $(response).each(function(i, p){
	                let selected = p.projectId == selectedProjectId ? "selected" : "";
	                project.append("<option "+selected+" value='"+p.projectId+"'>"+p.projectName+"</option>");
	            });
	        }
	    });
	}
	
	<!--Trigger AJAX on page load (for update mode)-->
	$(document).ready(function(){
	    var deptId = $("#departmentId").val();
	    var selectedTeamId = "${selectedTeamId}";
	    var selectedProjectId = "${selectedProjectId}";

	    if(deptId > 0){
	        loadTeams(deptId, selectedTeamId, selectedProjectId);
	    }
	});
	
	<!--Keep change events for dynamic updates-->
	
	$("#departmentId").change(function(){
	    loadTeams($(this).val(), 0, 0); // new selection → no pre-selection
	});

	$("#teamId").change(function(){
	    loadProjects($(this).val(), 0); // new selection → no pre-selection
	});




</script>


</body>
</html>