package com.portal.services;

import com.portal.entities.Complaint;
import com.portal.entities.Engineer;
import com.portal.entities.Product;
import com.portal.enums.ComplaintStatus;
import com.portal.exceptions.InValidComplaintIdException;
import com.portal.exceptions.InValidEngineerIdException;
import com.portal.exceptions.InValidStatusException;
import com.portal.exceptions.NoEngineerFoundException;

import java.util.List;
import java.util.Optional;

public interface IEngineerService {

    public List<Engineer> getAllEngineers() throws NoEngineerFoundException;

    public void addEngineer(Engineer engineer);

    Optional<Engineer> deleteEngineer(int engineerId);

    public List<Complaint> getAllOpenComplaints(int engineerId) throws InValidEngineerIdException;
    public List<Complaint> getResolvedComplaints(int engineerId) throws InValidEngineerIdException;

    public List<Complaint> getComplaints(int engineerId, ComplaintStatus status) throws InValidEngineerIdException;
    public void changeComplaintStatus(int complaintId,ComplaintStatus status) throws InValidStatusException, InValidComplaintIdException;
}
