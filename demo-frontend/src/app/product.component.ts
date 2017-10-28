import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { ProductService } from './product.service';
import { Product } from './product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  // Component properties
  allProducts: Product[];
  statusCode: number;
  requestProcessing = false;
  productIdToUpdate = null;
  processValidation = false;
  // Create form
  productForm = new FormGroup({
    title: new FormControl('', Validators.required),
    category: new FormControl('', Validators.required),
    status: new FormControl('', Validators.required)
  });
  // Create constructor to get service instance
  constructor(private productService: ProductService) {
  }
  // Create ngOnInit() and and load products

  ngOnInit(): void {
    this.getAllProducts();
  }
  // Fetch all products

  getAllProducts() {
    this.productService.getAllProducts()
      .subscribe(data => this.allProducts = data,
      errorCode => this.statusCode = errorCode);
  }
  // Handle create and update product
  onProductFormSubmit() {
    this.processValidation = true;
    if (this.productForm.invalid) {
      return; // Validation failed, exit from method.
    }
    // Form is valid, now perform create or update
    this.preProcessConfigurations();
    let title = this.productForm.get('title').value.trim();
    let category = this.productForm.get('category').value.trim();
    let status = this.productForm.get('status').value.trim();
    if (this.productIdToUpdate === null) {
      // Handle create product
      let product = new Product(null, title, category, status);
      this.productService.createProduct(product)
        .subscribe(successCode => {
          this.statusCode = successCode;
          this.getAllProducts();
          this.backTocreateProduct();
        },
        errorCode => this.statusCode = errorCode);
    } else {
      // Handle update product
      let product = new Product(this.productIdToUpdate, title, category, status);
      this.productService.updateProduct(product)
        .subscribe(successCode => {
          this.statusCode = successCode;
          this.getAllProducts();
          this.backTocreateProduct();
        },
        errorCode => this.statusCode = errorCode);
    }
  }
  // Load product by id to edit
  loadProductToEdit(productId: string) {
    this.preProcessConfigurations();
    this.productService.getProductById(productId)
      .subscribe(product => {
        this.productIdToUpdate = product.productId;
        this.productForm.setValue({ title: product.title, category: product.category, status: product.status });
        this.processValidation = true;
        this.requestProcessing = false;
      },
      errorCode => this.statusCode = errorCode);
  }
  // Delete product
  deleteProduct(productId: string) {
    this.preProcessConfigurations();
    this.productService.deleteProductById(productId)
      .subscribe(successCode => {
        this.statusCode = successCode;
        this.getAllProducts();
        this.backTocreateProduct();
      },
      errorCode => this.statusCode = errorCode);
  }
  // Perform preliminary processing configurations
  preProcessConfigurations() {
    this.statusCode = null;
    this.requestProcessing = true;
  }
  // Go back from update to create
  backTocreateProduct() {
    this.productIdToUpdate = null;
    this.productForm.reset();
    this.processValidation = false;
  }
}
