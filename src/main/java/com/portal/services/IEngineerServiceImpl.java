package com.portal.services;

import com.portal.entities.Complaint;
import com.portal.entities.Engineer;
import com.portal.enums.ComplaintStatus;
import com.portal.exceptions.InValidComplaintIdException;
import com.portal.exceptions.InValidEngineerIdException;
import com.portal.exceptions.InValidStatusException;
import com.portal.exceptions.NoComplaintFoundException;
import com.portal.repository.IComplaintRepository;
import com.portal.repository.IEngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IEngineerServiceImpl implements IEngineerService {
	@Autowired
	IEngineerRepository engineerRepository;
	
	@Autowired
	IComplaintRepository complaintRepository;
	
	
	
	public IEngineerServiceImpl(IEngineerRepository engineerRepository, IComplaintRepository complaintRepository) {
		super();
		this.engineerRepository = engineerRepository;
		this.complaintRepository = complaintRepository;
	}

	@Override
	public List<Complaint> getAllOpenComplaints(int engineerId) {
		// TODO Auto-generated method stub
		if(! engineerRepository.existsById(engineerId)) {
			throw new InValidEngineerIdException("Engineer ID doesn't exists");
			}
		Engineer engineer = engineerRepository.getById(engineerId);
		List<Complaint> list = new ArrayList<>();
		for(Complaint c: engineer.getComplaints()) {
			if(c.getStatus() == ComplaintStatus.OPEN){
			list.add(c);
			}
		}
		if(list.size() ==0) {
			throw new NoComplaintFoundException("No Complaint Foud");
		}
		
		return list;
	}

	@Override
	public List<Complaint> getResolvedComplaints(int engineerId) {
		// TODO Auto-generated method stub
		if(! engineerRepository.existsById(engineerId))
			throw new InValidEngineerIdException("Engineer ID doesn't exists");
		Engineer engineer = engineerRepository.getById(engineerId);
		List<Complaint> list = new ArrayList<>();
		for(Complaint c: engineer.getComplaints()) {
			if(c.getStatus() == ComplaintStatus.RESOLVED){
			list.add(c);
			}
		}
		if(list.size() ==0) {
			throw new NoComplaintFoundException("No Complaint Foud");
		}
		
		return list;
	}


	@Override
	public List<Complaint> getComplaints(int engineerId, ComplaintStatus status) {
		// TODO Auto-generated method stub
		if(! engineerRepository.existsById(engineerId))
			throw new InValidEngineerIdException("Engineer ID doesn't exists");
		List<Complaint> list = new ArrayList<>();
		Engineer e= engineerRepository.getById(engineerId);
		
		for(Complaint c: e.getComplaints()) {
			if(c.getStatus() == status){
			list.add(c);
			}
		}
		
		if(list.size() ==0) {
			throw new NoComplaintFoundException("No Complaint Foud");
		}
		return list;
	}

	@Override
	public void changeComplaintStatus(int complaintId,ComplaintStatus status) {
		// TODO Auto-generated method stub
		Complaint complaint=complaintRepository.getById(complaintId);
				if(complaintRepository.existsById(complaintId)) {
					
				switch (status) {
				
				case RESOLVE_ONLINE:
					complaint.setStatus(ComplaintStatus.RESOLVE_ONLINE);
					break;
				case RESOLVE_AT_HOME:
					complaint.setStatus(ComplaintStatus.RESOLVE_AT_HOME);
					break;
				case RESOLVED:
					complaint.setStatus(ComplaintStatus.RESOLVED);
					break;
				case CLOSED:
					throw new InValidStatusException("Engineer cannot close the complaint");
				case OPEN:
					complaint.setStatus(status);
				default:
					break;
					}
				complaintRepository.save(complaint);	
				}
				else {
					throw new InValidComplaintIdException("Complaint Not Found");
				}
			

	}

}
