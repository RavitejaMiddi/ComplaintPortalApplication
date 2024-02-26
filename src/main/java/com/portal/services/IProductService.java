package com.portal.services;

import com.portal.entities.Complaint;
import com.portal.entities.Engineer;
import com.portal.entities.Product;
import com.portal.enums.ProductCategoryName;
import com.portal.exceptions.InValidModelNumberException;

import java.util.List;

public interface IProductService {

	public void addProduct(Product product);

	public void removeProducts(ProductCategoryName category);

	public List<Product> getProduct(ProductCategoryName categoryName);

    public void updateProductWarranty(String modelNumber, int years)throws InValidModelNumberException;

	public List<Complaint> getProductComplaints(ProductCategoryName productCategoryName);

	public List<Engineer> getEngineersByProduct(ProductCategoryName productCategoryName);
	
	
	
}
