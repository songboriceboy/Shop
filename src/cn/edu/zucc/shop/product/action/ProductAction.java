package cn.edu.zucc.shop.product.action;

import cn.edu.zucc.shop.category.service.CategoryService;
import cn.edu.zucc.shop.product.service.ProductService;
import cn.edu.zucc.shop.product.vo.Product;
import cn.edu.zucc.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
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

	// 接收分类的cid
	private Integer cid;

	// 接收当前的页数
	private int page;

	public void setPage(int page) {
		this.page = page;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	// 注入一级分类的Service
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

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

	// 根据分类的id来查询商品
	public String findByCid() {
		// List<Category> clist = categoryService.findAll();
		PageBean<Product> pageBean = productService.findByPageCid(cid,page);
		//将pagebean存入到值栈中
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "findByCid";
	}
}
