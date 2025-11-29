<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Assigned Projects</title>
<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
      crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">

    <c:if test="${msg ne null}">
        <div id="alertId" class="alert alert-success">
            <span class="font-weight-bold">Message : </span> ${msg}
        </div>
    </c:if>

    <div class="h3 text-primary mt-5">Assigned Projects List</div>
    
    <table class="table table-bordered table-striped mt-3">
        <thead class="thead-dark">
            <tr>
                <th>Sl.#</th>
                <th>Project Name</th>
                <th>Employee</th>
                <th>Team</th>
                <th>Department</th>
                <th>Role</th>
                <th>Allocation %</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${projectsAssigned}" var="a" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${a.project.projectName}</td>
                    <td>${a.employee.fullName}</td>
                    <td>${a.project.team.teamName}</td>
                    <td>${a.project.team.department.departmentName}</td>
                    <td>${a.roleOnProject}</td>
                    <td>${a.allocationPercent}</td>
                    <td>${a.startDate}</td>
                    <td>${a.endDate}</td>
                    <td>${a.status}</td>
                    <td>
												<a href="./delete-assignment?assignmentId=${a.assignmentId}" class="text-danger">Delete</a>  
												<a href="./update?projectId=${a.project.projectId}" class="text-primary">Update</a>
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
