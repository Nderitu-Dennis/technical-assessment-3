package tech.csm.service;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.model.LeaveApplication;
import tech.csm.repository.LeaveApplicationRepository;

@Service
public class LeaveApplicationService {

	@Autowired
	private LeaveApplicationRepository leaveApplicationRepository;
	
	public LeaveApplication saveLeaveApplication(LeaveApplication application) {
	    // Validate dates before saving
	    if (application.getFromDate().isAfter(application.getToDate())) {
	        throw new IllegalArgumentException("From date cannot be after To date");
	    }

	    // Calculate total days from fromDate and toDate
	    long days = ChronoUnit.DAYS.between(application.getFromDate(), application.getToDate()) + 1;
	    application.setTotalDays((double) days);

	    return leaveApplicationRepository.save(application);
	}


}
