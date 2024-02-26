package com.portal.services;


import com.portal.entities.Complaint;
import com.portal.entities.Engineer;
import com.portal.entities.Product;
import com.portal.enums.ProductCategoryName;
import com.portal.exceptions.*;
import com.portal.repository.IComplaintRepository;
import com.portal.repository.IEngineerRepository;
import com.portal.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Service annotation is a specialization of @Component annotation. Spring Service annotation can be applied only to classes
@Service
public class IProductServiceImpl implements IProductService{
	
	//Autowired feature of spring framework enables you to inject the object dependency implicitly.
    @Autowired
	IProductRepository productRepository;
    
    @Autowired
    IComplaintRepository complaintRepository;
    @Autowired
    IEngineerRepository engineerRepository;

    
    


	public IProductServiceImpl(IProductRepository productRepository, IComplaintRepository complaintRepository,
			IEngineerRepository engineerRepository ) {
		super();
		this.productRepository = productRepository;
		this.complaintRepository = complaintRepository;
		this.engineerRepository = engineerRepository;

	}

	//The @Override annotation indicates that the child class method is over-writing its base class method.
  //method to save the product 
	@Override
	public void addProduct( Product product) {
			 LocalDate productDate=product.getDateOfPurchase().plusYears(product.getWarrentyYears());
	         product.setWarrantyDate(productDate);

	        if( productRepository.existsById(product.getModelNumber())){
	        	throw new DuplicateProductException("product id already exists");
	        }else {
		    productRepository.save(product);
		}
	}
	


	//method to get list of products based on category name
	@Override
	public List<Product> getProduct(ProductCategoryName categoryName) {
		List<Product> productList=new ArrayList<>();

		List<Product> productListOne=productRepository.findAll();
		for(Product product:productListOne) {
			if(product.getProductCategoryName() == categoryName) {
				 productList.add(product);
			}
			}
		//size() is  gives the number of objects in list 	
		if(productList.size()!=0) {
			return productList;
		}
		else {
			throw new NoProductsException("No Products Found");
		}
	
	}

	
	//method to remove products based on category
	@Override
	public void removeProducts(ProductCategoryName category) {
		List<Product> productList=new ArrayList<>();
		List<Product> productListOne=productRepository.findAll();
		for(Product product:productListOne) {
			if(product.getProductCategoryName() == category) {
				productList.add(product);
			}
			}
		
		if(productList.size()==0) {
			throw new NoProductsException("No products found");
		}
		else {
			for(Product product:productList) {
			productRepository.deleteById(product.getModelNumber());
			}
		}
		
		}
	

	
	//method to updatewarranty based on model number and year
	@Override
	public void updateProductWarranty(String modelNumber, int years)  {
		if(productRepository.existsById(modelNumber)) {
           Product product=productRepository.getById(modelNumber);
			int yearResult=product.getWarrentyYears()+years;
			product.setWarrentyYears(yearResult);
				LocalDate date=product.getDateOfPurchase().plusYears(yearResult);
				product.setWarrantyDate(date);
				productRepository.save(product);
		}
			
		else {
			throw new InValidModelNumberException("Model Number("+ modelNumber+")does not exist");
		}
		
		}
	
	
	//method to get list of complaints based on product category name
	@Override
	public List<Complaint> getProductComplaints(ProductCategoryName productCategoryName) {
		List<Complaint> complaintList = complaintRepository.findAll();
		List<Complaint> complaintByProductsList = new ArrayList<>();//creates an empty list of complaint object
		for(Complaint complaint:complaintList) {
			String productModelNumber = complaint.getProductModelNumber();
			
			if(!productRepository.existsById(productModelNumber))
				throw new NoProductsException("no products found with"+ " "+ productCategoryName);
			
			Product product = productRepository.getById(productModelNumber);
					
			
			
			if(product.getProductCategoryName() == productCategoryName) {
			
				complaintByProductsList.add(complaint);	
			}
		}
		
		  if (complaintByProductsList.size() == 0){
				throw new NoComplaintFoundException("No Complaints found with"+ " "+ productCategoryName);
		  }
		  else {
		
		  return complaintByProductsList;
		  }
}


	//method to get list of engineers based on product category name
	@Override
	public List<Engineer> getEngineersByProduct(ProductCategoryName productCategoryName) {
		List<Engineer> engineerListOne=new ArrayList<>();	
		
     List<Engineer> engineerList=engineerRepository.findAll();
     for(Engineer e: engineerList)
     {
    String engineerDomain = e.getDomain().toString();
    String categoryName = productCategoryName.toString();
    
    if(engineerDomain.equals(new String(categoryName))) {
    		 engineerListOne.add(e);
    	 }
     }
     if(engineerListOne.size()==0) {
        	throw new NoEngineerFoundException("No Engineer found  with"+ " "+ productCategoryName);	
       }
        else {
	      return engineerListOne;
        }
	
}
}






