package tech.csm.controller;

import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import tech.csm.model.Employee;
import tech.csm.model.EmployeeLeaveQuota;
import tech.csm.model.LeaveApplication;
import tech.csm.model.LeaveType;
import tech.csm.repository.DepartmentRepository;
import tech.csm.repository.EmployeeLeaveQuotaRepository;
import tech.csm.service.DepartmentService;
import tech.csm.service.EmployeeService;
import tech.csm.service.LeaveApplicationService;
import tech.csm.service.LeaveTypeService;

@Controller
public class LeaveApplicationController {

	private final DepartmentRepository departmentRepository;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private LeaveTypeService leaveTypeservice;

	@Autowired
	private LeaveApplicationService leaveApplicationService;

	@Autowired
	private EmployeeLeaveQuotaRepository employeeLeaveQuotaRepository;

	LeaveApplicationController(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

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
	public String saveLeaveApplication(
			@Valid @ModelAttribute LeaveApplication application,
			BindingResult result,
			RedirectAttributes rd) {
		if (result.hasErrors()) {
			rd.addFlashAttribute("validationErrors", result.getAllErrors());
			return "redirect:./leave-application-form";

		}

		try {
			LeaveApplication saved = leaveApplicationService.saveLeaveApplication(application);

			String msg = "leave application  saved with id : " + saved.getLeaveApplicationId();
			rd.addFlashAttribute("msg", msg);
			return "redirect:./get-applied-leaves";
		} catch (IllegalArgumentException ex) {
			rd.addFlashAttribute("dateErrorMsg", ex.getMessage());
			// System.out.println("error**" + ex.getMessage());
			return "redirect:./leave-application-form";

		}
	}

	// get all applied leaves
	@GetMapping("/get-applied-leaves")
	public String getAppliedLeaves(Model model) {
		List<LeaveApplication> leaves = leaveApplicationService.getAllAppliedLeaves();
		if (leaves == null) {
			leaves = Collections.emptyList();
		}

		// Attach quota + remainder for each leave
		Map<Integer, Double> entitledMap = new HashMap<>();
		Map<Integer, Double> remainderMap = new HashMap<>();

		for (LeaveApplication a : leaves) {
			var quotaOpt = employeeLeaveQuotaRepository.findByEmployeeEmployeeIdAndLeaveTypeLeaveTypeId(
					a.getEmployee().getEmployeeId(),
					a.getLeaveType().getLeaveTypeId());

			if (quotaOpt.isPresent()) {
				EmployeeLeaveQuota q = quotaOpt.get();
				entitledMap.put(a.getLeaveApplicationId(), q.getTotalAllocated());
				remainderMap.put(a.getLeaveApplicationId(), q.getTotalAllocated() - a.getTotalDays());
			} else {
				entitledMap.put(a.getLeaveApplicationId(), 0.0);
				remainderMap.put(a.getLeaveApplicationId(), 0.0);
			}
		}
		// todo-check on this logic fectching entitled leave days via
		// EmployeeLeaveQuotaTable

		model.addAttribute("leaves", leaves); // variable names shld not contain dashes
		model.addAttribute("entitled", entitledMap);
		model.addAttribute("remainder", remainderMap);

		return "applied-leaves-list";
	}

//     Delete a leave
	@GetMapping("/delete-leave")
	public String deleteLeave(@RequestParam("leaveApplicationId") Integer leaveApplicationId, RedirectAttributes rd) {
		leaveApplicationService.deleteLeaveApplication(leaveApplicationId);
		rd.addFlashAttribute("msg", "Leave application deleted successfully!");
		return "redirect:./get-applied-leaves"; // redirect to a url but return a view page
	}
}
