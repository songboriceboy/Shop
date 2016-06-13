package cn.edu.zucc.shop.product.action;

import cn.edu.zucc.shop.product.service.ProductService;
import cn.edu.zucc.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * ��Ʒ��action����
 */
public class ProductAction extends ActionSupport implements
		ModelDriven<Product> {

	// ���ڽ������ݵ�ģ������
	private Product product = new Product();
	// ע����Ʒ��Service
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}


	// ������Ʒ��id��ѯ��Ʒ��ִ�з���
	public String findByPid() {
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
}
