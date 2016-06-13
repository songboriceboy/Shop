package cn.edu.zucc.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.shop.product.dao.ProductDao;
import cn.edu.zucc.shop.product.vo.Product;

/*
 * ��Ʒ��ҵ������
 */
@Transactional
public class ProductService {
	// ע��ProductDao
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// ��ҳ��������Ʒ��ѯ
	public List<Product> findHot() {
		return productDao.findHot();
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}
	
	//������Ʒ��id����ѯ��Ʒ
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}

}
