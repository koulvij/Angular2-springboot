package com.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.entity.Product;
import com.demo.service.IProductService;

@Controller
@RequestMapping("user")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ProductController {
	@Autowired
	private IProductService productService;
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("product")
	public ResponseEntity<Product> getProductById(@RequestParam("id") String id) {
		Product product = productService.getProductById(Integer.parseInt(id));
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	/**
	 * @return
	 */
	@GetMapping("all-products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> list = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	/**
	 * @param product
	 * @param builder
	 * @return
	 */
	@PostMapping("product")
	public ResponseEntity<Void> createProduct(@RequestBody Product product, UriComponentsBuilder builder) {
        boolean flag = productService.createProduct(product);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/product?id={id}").buildAndExpand(product.getProductId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	/**
	 * @param product
	 * @return
	 */
	@PutMapping("product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("product")
	public ResponseEntity<Void> deleteProduct(@RequestParam("id") String id) {
		productService.deleteProduct(Integer.parseInt(id));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 