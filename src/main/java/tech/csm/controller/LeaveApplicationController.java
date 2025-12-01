package tech.csm.controller;

import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tech.csm.model.Employee;
import tech.csm.model.LeaveApplication;
import tech.csm.model.LeaveType;
import tech.csm.service.DepartmentService;
import tech.csm.service.EmployeeService;
import tech.csm.service.LeaveApplicationService;
import tech.csm.service.LeaveTypeService;




@Controller
public class LeaveApplicationController {

@Autowired
private DepartmentService departmentService;

@Autowired
private EmployeeService employeeService;


@Autowired
private LeaveTypeService leaveTypeservice;

@Autowired
private LeaveApplicationService leaveApplicationService;


    // leave application form
    @GetMapping("/leave-application-form")
    public String getLeaveApplicationForm(Model model) {
    	 model.addAttribute("departments", departmentService.getAllDepartments());
    	 model.addAttribute("employees", employeeService.getActiveEmployees());
    	 model.addAttribute("leaveTypes", leaveTypeservice.getActiveLeaves());

       
        return "leave-application-form";
    }

   //  Get employees by Department
   @GetMapping("/employees-by-department-id")
   @ResponseBody
  public List<Employee> getEmployeesByDepartment(@RequestParam("departmentId") Integer departmentId) {
       return employeeService.getEmployeesByDepartment(departmentId);
  }
   

  @GetMapping("/leave-types-by-employee-id")
  @ResponseBody
  public List<LeaveType> getLeaveTypesByEmployee(@RequestParam("employeeId") Integer employeeId) {
      return employeeService.getLeaveTypesByEmployee(employeeId);
  }
  
// save leave application
@PostMapping("/save-application")
public String saveLeaveApplication(@ModelAttribute LeaveApplication application, RedirectAttributes rd) {
	 // 1. Calculate total days from fromDate and toDate
    long days = ChronoUnit.DAYS.between(application.getFromDate(), application.getToDate()) + 1;
    application.setTotalDays((double) days);
	LeaveApplication  saved = leaveApplicationService.saveLeaveApplication(application);
  String msg = "leave application  saved with id : " + saved.getLeaveApplicationId();
   rd.addFlashAttribute("msg", msg);
    return "redirect:./leave-application-form";
}
}
    


       
   
 

//    //  Get Projects by Team only active & bill_able
//    @GetMapping("/projects-by-team-id")
//    @ResponseBody
//    public List<Project> getProjectsByTeam(@RequestParam("teamId") Integer teamId) {
//        return projectService.getBillableProjectsByTeam(teamId);
//    }


    
//    //get all assigned projects
//    @GetMapping("/get-assigned-projects-list")
//    public String getProjectsAssigned(Model model) {
//        List<ProjectAssignment> assignments = projectAssignmentService.getAllAssignedProjects();
//        if (assignments == null) {
//            assignments = Collections.emptyList();
//        }
//        model.addAttribute("projectsAssigned", assignments);  //variable names shld not contain dashes
//        return "assigned-projects-list";
//    }


//    // 5. Delete Project Assignment
//    @GetMapping("/delete-assignment")
//    public String deleteProjectAssignment(@RequestParam("assignmentId") Integer assignmentId, RedirectAttributes rd) {
//        projectAssignmentService.deleteAssignment(assignmentId);
//        rd.addFlashAttribute("msg", "Project deleted successfully!");
//        return "redirect:./get-assigned-projects-list";
//    }
    
//    //updating/edit a project assignment
//    @GetMapping("/update-assignment")
//    public String editAssignment(@RequestParam("assignmentId") Integer assignmentId, Model model) {
//        ProjectAssignment assignment = projectAssignmentService.getAssignmentById(assignmentId);
//        
//        // extract selected IDs  
//        Integer selectedTeamId = assignment.getProject().getTeam().getTeamId();
//        Integer selectedDepartmentId = assignment.getProject().getTeam().getDepartment().getDepartmentId();
//        Integer selectedProjectId = assignment.getProject().getProjectId();
//        Integer selectedEmployeeId = assignment.getEmployee().getEmployeeId();
//
//        model.addAttribute("assignment", assignment); // pre-fill the form
//        model.addAttribute("selectedDepartmentId", selectedDepartmentId);
//        model.addAttribute("selectedTeamId", selectedTeamId);
//        model.addAttribute("selectedProjectId", selectedProjectId);
//        model.addAttribute("selectedEmployeeId", selectedEmployeeId);
//        
//        //  add necessary dropdown list data
//        model.addAttribute("departments", departmentService.getActiveDepartments());
//        model.addAttribute("employees", employeeService.getActiveEmployees());
//        
//        return "project-assignment-form"; 
//    }


