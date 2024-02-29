package com.portal.controllers;

import java.util.List;

import com.portal.entities.Engineer;
import com.portal.entities.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.portal.entities.Complaint;
import com.portal.enums.ComplaintStatus;

import com.portal.services.IEngineerServiceImpl;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping("engineer")
public class EngineerController {

    @Autowired
    IEngineerServiceImpl service;

    @PostMapping("/addEngineer")
    public  ResponseEntity<?> addEngineer(@Valid @RequestBody Engineer engineer) {
        service.addEngineer(engineer);
        return new ResponseEntity<String>("Engineer added successfully",HttpStatus.OK) ;
    }
    @DeleteMapping("/deleteEngineer/{engineerId}")
    public ResponseEntity<?> deleteDeveloper(@PathVariable("engineerId") int engineerId){
        service.deleteEngineer(engineerId);
        return new ResponseEntity<String>("Engineer Deleted Succesfully",HttpStatus.OK);
    }

    @GetMapping("/getAllEngineers")
    public ResponseEntity<List<Engineer>> getAllEngineers() {

        List<Engineer> engineerList = service.getAllEngineers();

        return new ResponseEntity<List<Engineer>>(engineerList, HttpStatus.OK);
    }

    @GetMapping("/getAllOpenComplaints/{engineerId}")
    public ResponseEntity<List<Complaint>> getAllOpenComplaints(@PathVariable("engineerId")int engineerId) {

        List<Complaint> complaintList = service.getAllOpenComplaints(engineerId);

        return new ResponseEntity<List<Complaint>>(complaintList, HttpStatus.OK);
    }

    @GetMapping("/getResolvedComplaints/{engineerId}")
    public ResponseEntity<?> getResolvedComplaints(@PathVariable("engineerId")int engineerId) {

        List<Complaint> complaintList = service.getResolvedComplaints(engineerId);

        return new ResponseEntity<List<Complaint>>(complaintList, HttpStatus.OK);
    }

    @GetMapping("getComplaintsByStatus/{engineerId}/{status}")
    public ResponseEntity<?> getComplaintsByStatus(@PathVariable("engineerId")int engineerId,  @PathVariable("status")ComplaintStatus status) {


        List<Complaint> complaintList = service.getComplaints(engineerId, status);

        return new ResponseEntity<List<Complaint>>(complaintList, HttpStatus.OK);
    }

    @PutMapping("/ChangeComplaintStatus/{complaintId}/{status}")
    public ResponseEntity<?> changeComplaintStatus( @PathVariable("complaintId") int complaintID, @PathVariable("status") ComplaintStatus status){
        service.changeComplaintStatus(complaintID, status);
        return new ResponseEntity<String>("status changed",HttpStatus.OK);

    }


}
