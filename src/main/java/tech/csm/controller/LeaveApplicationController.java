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

	// Get employees by Department
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

		try {
			LeaveApplication saved = leaveApplicationService.saveLeaveApplication(application);

			String msg = "leave application  saved with id : " + saved.getLeaveApplicationId();
			rd.addFlashAttribute("msg", msg);
			return "redirect:./leave-application-form";
		} catch (IllegalArgumentException ex) {
			rd.addFlashAttribute("dateErrorMsg", ex.getMessage());
			return "redirect:./leave-application-form";

		}
	}
}
