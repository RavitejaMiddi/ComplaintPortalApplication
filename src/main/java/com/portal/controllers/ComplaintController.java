
package com.portal.controllers;

import java.util.List;


import com.portal.services.IComplaintService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.portal.entities.Complaint;
import com.portal.entities.Engineer;
import com.portal.entities.Product;
import com.portal.enums.ComplaintStatus;
import com.portal.exceptions.InValidComplaintIdException;
import com.portal.exceptions.OutOfWarrantyException;
import com.portal.services.IComplaintServiceImpl;
import com.portal.services.IProductService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
//Spring RestController annotation is used to create RESTful web services using Spring MVC
@RequestMapping("/complaint")    //This annotation maps HTTP requests to handler methods of MVC and REST controllers.
public class ComplaintController {

    @Autowired
    IComplaintService complaintService;


    String role = "";

    //@PostMapping annotated methods handle the HTTP POST requests matched with given URI expression
    @PostMapping("/bookComplaint")
    public ResponseEntity<?> bookComplaint(@Valid @RequestBody Complaint complaint) {

        boolean result = complaintService.bookComplaint(complaint);
        if (result)
            return new ResponseEntity<String>("Complaint Booked Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Booking complaint was unsuccessful", HttpStatus.CREATED);
    }

    //@PutMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. PUT)
    @PutMapping("/ChangeComplaintStatus/{complaintId}/{status}")
    public ResponseEntity<?> changeComplaintStatus(@PathVariable("complaintId") int complaintId, @PathVariable("status") ComplaintStatus status) {


        boolean result = complaintService.changeComplaintStatus(complaintId, status) != null;
        if (result)
            return new ResponseEntity<String>("Status changed successfully", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<String>("Status not changed", HttpStatus.BAD_REQUEST);
    }

    //@GetMapping Annotation is used for mapping HTTP GET requests onto specific handler methods
    @GetMapping("/getAllComplaints")
    public ResponseEntity<List<Complaint>> getClientAllComplaints() {
        List<Complaint> list = complaintService.getAllComplaints();
        return new ResponseEntity<List<Complaint>>(list, HttpStatus.OK);
    }

    //@GetMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. GET)
    @GetMapping("/getAllOpenComplaints")
    public ResponseEntity<List<Complaint>> getClientAllOpenComplaints() {

        List<Complaint> list = complaintService.getAllOpenComplaints();
        return new ResponseEntity<List<Complaint>>(list, HttpStatus.OK);
    }

//    //@GetMapping Annotation is used for mapping HTTP GET requests onto specific handler methods
//    @GetMapping("/getEngineerByComplaintId/{complaintId}")
//    public ResponseEntity<Engineer> getEngineerByComplaintId(@PathVariable("complaintId") int complaintId) {
//
//        Engineer engineer = complaintService.getEngineer(complaintId);
//        return new ResponseEntity<Engineer>(engineer, HttpStatus.OK);
//    }

    //@GetMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. GET)
    @GetMapping("/getProductByComplaintId/{complaintId}")
    public ResponseEntity<Product> getProductByComplaintId(@PathVariable("complaintId") int complaintId) {


        Product product = complaintService.getProductByComplaint(complaintId);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

}
		

		
		
		
		
		
		
		
		
		
	
