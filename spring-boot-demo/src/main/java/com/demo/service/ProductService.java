package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.IProductDAO;
import com.demo.entity.Product;
/**
 * @author vijaykoul
 *
 */
@Service
public class ProductService implements IProductService {
	@Autowired
	private IProductDAO productDAO;
	
	
	/* (non-Javadoc)
	 * @see com.demo.service.IProductService#getProductById(int)
	 */
	@Override
	public Product getProductById(int productId) {
		Product obj = productDAO.getProductById(productId);
		return obj;
	}	

	/* (non-Javadoc)
	 * @see com.demo.service.IProductService#getAllProducts()
	 */
	@Override
	public List<Product> getAllProducts(){
		return productDAO.getAllProducts();
	}
	

	/* (non-Javadoc)
	 * @see com.demo.service.IProductService#createProduct(com.demo.entity.Product)
	 */
	@Override
	public synchronized boolean createProduct(Product product){
       if (productDAO.productExists(product.getTitle(), product.getCategory())) {
    	   return false;
       } else {
    	   productDAO.createProduct(product);
    	   return true;
       }
	}
	

	/* (non-Javadoc)
	 * @see com.demo.service.IProductService#updateProduct(com.demo.entity.Product)
	 */
	@Override
	public void updateProduct(Product product) {
		productDAO.updateProduct(product);
	}
	

	/* (non-Javadoc)
	 * @see com.demo.service.IProductService#deleteProduct(int)
	 */
	@Override
	public void deleteProduct(int productId) {
		productDAO.deleteProduct(productId);
	}
}
