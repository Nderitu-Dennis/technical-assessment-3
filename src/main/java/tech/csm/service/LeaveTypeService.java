package tech.csm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.model.LeaveType;
import tech.csm.repository.LeaveTypeRepository;

@Service
public class LeaveTypeService {

	@Autowired
	private LeaveTypeRepository leaveTypeRepository;
	public List<LeaveType> getActiveLeaves() {
		return leaveTypeRepository.findByIsActive(true);

	}

}
