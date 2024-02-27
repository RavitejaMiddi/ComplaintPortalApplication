
package com.portal.services;
import com.portal.entities.Complaint;
import com.portal.entities.Engineer;
import com.portal.entities.Product;
import com.portal.enums.ComplaintStatus;
import com.portal.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service annotation is used with classes that provide some business functionalities
@Service
public interface IComplaintService {

	// Method to book the complaint 
	public boolean bookComplaint(Complaint complaint)throws OutOfWarrantyException,InValidComplaintIdException,InValidEngineerIdException;
	
	// Method to change the complaint Status
	public ComplaintStatus changeComplaintStatus(int complaintId,ComplaintStatus status);

	
	// Method to get all the open complaints of client
	public List<Complaint> getAllComplaints();
	
	// Method to get the engineer based on complaint id
	public Engineer getEngineer(int complaintId)throws InValidComplaintIdException;
	
	// Method to get the product based on complaint
	public Product getProductByComplaint(int complaintId)throws InValidComplaintIdException;

	public List<Complaint> getAllOpenComplaints()throws NoComplaintFoundException;
}

