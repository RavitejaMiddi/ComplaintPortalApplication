package com.portal.controllers;

import com.portal.entities.Complaint;
import com.portal.entities.Engineer;
import com.portal.entities.Product;
import com.portal.enums.ProductCategoryName;

import com.portal.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

// Spring RestController annotation is used to create RESTful web services using Spring MVC.
@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping("products")//This annotation maps HTTP requests to handler methods of MVC and REST controllers.
public class ProductController {
	@Autowired
	IProductService service;
	



	//ResponseEntity represents the whole HTTP response: status code, headers, and body. As a result
    //@GetMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod
	@PostMapping("/addProduct")
	public  ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
		service.addProduct(product);
		return new ResponseEntity<String>("product added successfully",HttpStatus.OK) ;
	}
	
	//@DeleteMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. DELETE) .
	@DeleteMapping("/removeProducts/{category}")
	public ResponseEntity<?> removeProducts(@PathVariable("category") ProductCategoryName category) {
		service.removeProducts(category);
		return new ResponseEntity<String>("product removed successfully",HttpStatus.OK) ;

	}
		
	
	// @GetMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. GET) 
	@GetMapping("/getProduct/{categoryname}")
	public ResponseEntity<List<Product>> getProduct(@PathVariable("categoryname") ProductCategoryName categoryName){
		List<Product> list = service.getProduct(categoryName);
		return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
	}
	
	
	
	//I@PutMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.
		@PutMapping("/updateProductWarranty/{modelnumber}/{years}")	
		public ResponseEntity<?> updateProductWarranty(@PathVariable("modelnumber") String modelNumber,@PathVariable("years")int years) {
			service.updateProductWarranty(modelNumber, years);
			return new ResponseEntity<String>("product updated successfully",HttpStatus.OK) ;

		}

	// @GetMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. GET) 
	 @GetMapping("/getproduct/complaints/{categoryname}")
	  public ResponseEntity<List<Complaint>> getProductComplaints(@PathVariable("categoryname") ProductCategoryName productCategoryName) {
	     List<Complaint> list=service.getProductComplaints(productCategoryName);
		 return new ResponseEntity<List<Complaint>>(list,HttpStatus.OK);

	  }
	 
	// @GetMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. GET) 
	 @GetMapping("/getproduct/engineers/domain/{categoryname}")
	 public  ResponseEntity<List<Engineer>> getEngineersByProduct(@PathVariable("categoryname") ProductCategoryName productCategoryName){
	    List<Engineer> list=service.getEngineersByProduct(productCategoryName);
		return new ResponseEntity<List<Engineer>>(list,HttpStatus.OK);

	  }
}
