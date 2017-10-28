package com.demo.dao;
import java.util.List;

import com.demo.entity.Product;
/**
 * @author vijaykoul
 *
 */
public interface IProductDAO {
	

/**
 * @return List<Product>
 */
public  List<Product> getAllProducts();

/**
 * @param productId
 * @return Product
 */
public Product getProductById(int productId);

/**
 * @param product
 */
public void createProduct(Product product);

/**
 * @param product
 */
public void updateProduct(Product product);

/**
 * @param productId
 */
public  void deleteProduct(int productId);

/**
 * @param title
 * @param category
 * @return
 */
public boolean productExists(String title, String category);
}
 