package com.portal.services;

import com.portal.entities.Complaint;
import com.portal.enums.ComplaintStatus;
import com.portal.exceptions.InValidComplaintIdException;
import com.portal.exceptions.InValidEngineerIdException;
import com.portal.exceptions.InValidStatusException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IEngineerService {

	
	public List<Complaint> getAllOpenComplaints(int engineerId) throws InValidEngineerIdException;
	public List<Complaint> getResolvedComplaints(int engineerId) throws InValidEngineerIdException;

	public List<Complaint> getComplaints(int engineerId,ComplaintStatus status) throws InValidEngineerIdException;
	public void changeComplaintStatus(int complaintId,ComplaintStatus status) throws InValidStatusException,InValidComplaintIdException; 
	
}
