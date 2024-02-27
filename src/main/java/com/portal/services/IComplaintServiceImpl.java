package com.portal.services;


import com.portal.entities.Complaint;
import com.portal.entities.Engineer;
import com.portal.entities.Product;
import com.portal.enums.ComplaintStatus;
import com.portal.exceptions.*;
import com.portal.repository.IComplaintRepository;
import com.portal.repository.IEngineerRepository;
import com.portal.repository.IProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// @Service annotation is used with classes that provide some business functionalities
@Service
public class IComplaintServiceImpl implements IComplaintService {
	
	// Autowired feature of spring framework enables you to inject the object dependency implicitly
	@Autowired
	IComplaintRepository complaintRepository;

	@Autowired
	IProductRepository productRepository;

	
	@Autowired
	IEngineerRepository engineerRepository;

	Logger logger = LoggerFactory.getLogger(IComplaintServiceImpl.class);
	
	
	// constructor using fields
	public IComplaintServiceImpl(IComplaintRepository complaintRepository, IProductRepository productRepository,
			 IEngineerRepository engineerRepository) {
		super();
		this.complaintRepository = complaintRepository;
		this.productRepository = productRepository;

		this.engineerRepository = engineerRepository;
	}

	// The @Override annotation indicates that the child class method is over-writing its base class method
	// existsById() is default method in repository it gives object by taking Id
	// Method to book the complaint 
	@Override
	public boolean bookComplaint(Complaint complaint,int engineerId){
		
		if(! productRepository.existsById(complaint.getProductModelNumber())) {
			throw new InValidModelNumberException("Product model doesn't exists");
		}	
	
		Product product = productRepository.getById(complaint.getProductModelNumber());
		
		LocalDate warrantyDate = product.getWarrantyDate();
		
		if(warrantyDate.isBefore(LocalDate.now())) {
			throw new OutOfWarrantyException("Warranty is out of date");
		}


		if( engineerRepository.existsById(engineerId)) {
			Engineer engineer = engineerRepository.getById(engineerId);
			complaint.setEngineer(engineer);
		}
		else {
			throw new InValidEngineerIdException("Engineer doesn't exists");
		}
		
		Optional<Complaint> complaintOpt=complaintRepository.findById(complaint.getComplaintId());
		
		if(complaintOpt.isPresent()) {
		throw new InValidComplaintIdException("complaint Id already exists");
	}
     else {
    	 complaintRepository.save(complaint);
    	 return true;
     }
	}
	
	// The @Override annotation indicates that the child class method is over-writing its base class method
	// Method to change the complaint Status
	@Override
	public ComplaintStatus changeComplaintStatus(int complaintId,ComplaintStatus status) {

		Optional<Complaint> complaintOpt= complaintRepository.findById(complaintId);
		
		if(! complaintOpt.isPresent()) {
			throw new InValidComplaintIdException("complaint Id does not exists");
		}
			else {
				Complaint complaint = complaintOpt.get();
				System.out.println(complaint.getComplaintId());
			
			switch (status) {
			
		case OPEN:
			complaint.setStatus(ComplaintStatus.OPEN);
		     break;
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
			complaint.setStatus(ComplaintStatus.CLOSED);
			break;
		
		}
		complaintRepository.save(complaint);
		}
		return status;
	}
	
	// The @Override annotation indicates that the child class method is over-writing its base class method
	// findAll() method gives list of objects.
	// Method to get all the complaints
	@Override
	public List<Complaint> getAllComplaints( ) {
		
		List<Complaint> clientAllComplaints = new ArrayList<>();
		clientAllComplaints=complaintRepository.findAll();

		if(clientAllComplaints.size() == 0) {
			throw new NoComplaintFoundException("No Complaints Found");
		}
		return clientAllComplaints;
	}

	// The @Override annotation indicates that the child class method is over-writing its base class method
	// equals() method compares two objects
	// Method to get all the open complaints of client
	@Override
	public List<Complaint> getAllOpenComplaints(int complaintId) throws NoComplaintFoundException {
		
		List<Complaint> complaintList = complaintRepository.findAll() ;
		
		List<Complaint> openComplaints = new ArrayList<>();
		if(complaintRepository.existsById(complaintId)) {
			for(Complaint c : complaintList) {
				if ( c.getStatus() == ComplaintStatus.OPEN)	{
						openComplaints.add(c);
				}
			}
		}
		else {
	           throw new NoComplaintFoundException ("Complaint Id does not exists");
		}
		
		if(openComplaints.size() == 0) {
			throw new NoComplaintFoundException("No Open Complaints Found");
		}
		return openComplaints;
	}
	
	// The @Override annotation indicates that the child class method is over-writing its base class method
	// Method to get the engineer based on complaint id
	@Override
	public Engineer getEngineer(int complaintId){
		Optional<Complaint> complaintOpt=complaintRepository.findById(complaintId);
		if(complaintOpt.isPresent()) {
		 Complaint complaint = complaintOpt.get();
		 Engineer engineer = complaint.getEngineer();
		 int engineerId = engineer.getEngineerId();
         Optional<Engineer> engineerOpt= engineerRepository.findById(engineerId);
         
         if(! engineerOpt.isPresent()) {
        	 throw new InValidEngineerIdException("Engineer Id doesn't exists");
         }
    	 return engineerOpt.get();	 
		}
		else 
			throw new InValidComplaintIdException("complaint Id does not exists");
         }
		
	// The @Override annotation indicates that the child class method is over-writing its base class method
	// Method to get the product based on complaint
	@Override
	public Product getProductByComplaint(int complaintId)
	{
		Optional<Complaint> complaintOpt = complaintRepository.findById(complaintId);
		
		if (complaintOpt.isPresent()) {
			Complaint complaint =  complaintOpt.get();
			String productModelNumber = complaint.getProductModelNumber();
			Optional<Product> productOpt = productRepository.findById(productModelNumber);
			
			if(! productOpt.isPresent()) {
				throw new InValidModelNumberException("product model doesn't exists");
			}
			
			return productOpt.get();
		}
		else
		{
				throw new InValidComplaintIdException("complaint Id does not exists");
		}
		
	}
	
	
	@Override
	public List<Engineer> getAllEngineers() throws NoEngineerFoundException {
		// TODO Auto-generated method stub
		List<Engineer> engineerList=engineerRepository.findAll();
		if(engineerList.size()==0)
			throw new NoEngineerFoundException("Engineers data is empty");
		else		
		return engineerList;
	}
	}
