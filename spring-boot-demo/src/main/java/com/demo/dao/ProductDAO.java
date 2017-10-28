package com.demo.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.Product;
/**
 * @author vijaykoul
 *
 */
@Transactional
@Repository
public class ProductDAO implements IProductDAO {
	
	@PersistenceContext	
	private EntityManager entityManager;	
	

	/* (non-Javadoc)
	 * @see com.demo.dao.IProductDAO#getProductById(int)
	 */
	@Override
	public Product getProductById(int articleId) {
		return entityManager.find(Product.class, articleId);
	}
	

	/* (non-Javadoc)
	 * @see com.demo.dao.IProductDAO#getAllProducts()
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		String hql = "FROM Product as atcl ORDER BY atcl.productId DESC";
		return (List<Product>) entityManager.createQuery(hql).getResultList();
	}	
	

	/* (non-Javadoc)
	 * @see com.demo.dao.IProductDAO#createProduct(com.demo.entity.Product)
	 */
	@Override
	public void createProduct(Product article) {
		entityManager.persist(article);
	}
	

	/* (non-Javadoc)
	 * @see com.demo.dao.IProductDAO#updateProduct(com.demo.entity.Product)
	 */
	@Override
	public void updateProduct(Product product) {
		Product artcl = getProductById(product.getProductId());
		artcl.setTitle(product.getTitle());
		artcl.setCategory(product.getCategory());
		artcl.setStatus(product.getStatus());
		entityManager.flush();
	}
	

	/* (non-Javadoc)
	 * @see com.demo.dao.IProductDAO#deleteProduct(int)
	 */
	@Override
	public void deleteProduct(int productId) {
		entityManager.remove(getProductById(productId));
	}
	

	/* (non-Javadoc)
	 * @see com.demo.dao.IProductDAO#productExists(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean productExists(String title, String category) {
		String hql = "FROM Product as atcl WHERE atcl.title = ? and atcl.category = ?";
		int count = entityManager.createQuery(hql).setParameter(1, title)
		              .setParameter(2, category).getResultList().size();
		return count > 0 ? true : false;
	}
}
