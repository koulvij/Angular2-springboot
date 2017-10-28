import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';

import { Product } from './product';

@Injectable()
export class ProductService {

	// URLs for CRUD operations
	allProductsUrl = 'http://localhost:8080/user/all-products';
	productUrl = 'http://localhost:8080/user/product';

	// Create constructor to get Http instance
	constructor(private http: Http) {
	}
	// Fetch all producs
	getAllProducts(): Observable<Product[]> {
		return this.http.get(this.allProductsUrl)
			.map(this.extractData)
			.catch(this.handleError);

	}
	// Create Product
	createProduct(product: Product): Observable<number> {
		let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
		let options = new RequestOptions({ headers: cpHeaders });
		return this.http.post(this.productUrl, product, options)
			.map(success => success.status)
			.catch(this.handleError);
	}
	//Fetch product by id
	getProductById(productId: string): Observable<Product> {
		let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
		let cpParams = new URLSearchParams();
		cpParams.set('id', productId);
		let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
		return this.http.get(this.productUrl, options)
			.map(this.extractData)
			.catch(this.handleError);
	}
	//Update product
	updateProduct(product: Product): Observable<number> {
		let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
		let options = new RequestOptions({ headers: cpHeaders });
		return this.http.put(this.productUrl, product, options)
			.map(success => success.status)
			.catch(this.handleError);
	}
	// Delete product

	deleteProductById(productId: string): Observable<number> {
		let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
		let cpParams = new URLSearchParams();
		cpParams.set('id', productId);
		let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
		return this.http.delete(this.productUrl, options)
			.map(success => success.status)
			.catch(this.handleError);
	}
	private extractData(res: Response) {
		let body = res.json();
		return body;
	}
	private handleError(error: Response | any) {
		console.error(error.message || error);
		return Observable.throw(error.status);
	}
}