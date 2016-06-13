package cn.edu.zucc.shop.product.action;

import cn.edu.zucc.shop.product.service.ProductService;
import cn.edu.zucc.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * 商品的action对象
 */
public class ProductAction extends ActionSupport implements
		ModelDriven<Product> {

	// 用于接收数据的模型驱动
	private Product product = new Product();
	// 注入商品的Service
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}


	// 根据商品的id查询商品，执行方法
	public String findByPid() {
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
}
