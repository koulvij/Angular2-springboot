package com.demo.service;

import java.util.List;

import com.demo.entity.Product;

public interface IProductService {
	
     /**
     * @return
     */
    public List<Product> getAllProducts();
    /**
     * @param articleId
     * @return
     */
    public Product getProductById(int articleId);
    /**
     * @param article
     * @return
     */
    public boolean createProduct(Product article);
    /**
     * @param article
     */
    public void updateProduct(Product article);
    /**
     * @param articleId
     */
    public void deleteProduct(int articleId);
}
