package tech.csm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.model.Employee;
import tech.csm.model.LeaveType;
import tech.csm.repository.EmployeeLeaveQuotaRepository;
import tech.csm.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeLeaveQuotaRepository  employeeLeaveQuotaRepository;

	  public List<Employee> getActiveEmployees() {
	        return employeeRepository.findByIsActive(true);
	    }

	  public List<Employee> getEmployeesByDepartment(Integer departmentId) {
		return employeeRepository.findByDepartment_DepartmentId(departmentId);
		
		//we;re finding employee by departmentId, not employeeId, so findById cant work here
	  }

	  public List<LeaveType> getLeaveTypesByEmployee(Integer employeeId) {
		  // Only return leave types where remaining balance > 0
	        return employeeLeaveQuotaRepository.findActiveLeaveTypesByEmployee(employeeId);	  }
	  


}
