package tech.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.model.LeaveApplication;
import tech.csm.repository.LeaveApplicationRepository;

@Service
public class LeaveApplicationService {
	
	@Autowired
	private LeaveApplicationRepository leaveApplicationRepository;

	public LeaveApplication saveLeaveApplication(LeaveApplication application) {
	return leaveApplicationRepository.save(application);
	}

}
