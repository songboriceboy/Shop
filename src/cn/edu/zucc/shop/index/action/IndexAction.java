package cn.edu.zucc.shop.index.action;

import java.util.List;

import cn.edu.zucc.shop.category.service.CategoryService;
import cn.edu.zucc.shop.category.vo.Category;
import cn.edu.zucc.shop.product.service.ProductService;
import cn.edu.zucc.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/*
 * 首页访问的action
 */
public class IndexAction extends ActionSupport {

	// 注入一级分类的Service
	private CategoryService categoryService;
	private ProductService productService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}	

	/*
	 * 执行的访问首页的方法
	 */
	public String execute() {
		//查询所有一级分类的集合
		List<Category> cList = categoryService.findAll();
		//将一级分类存入到session的范围
		ActionContext.getContext().getSession().put("cList", cList);
		
		//查询热门商品
		List<Product> hList = productService.findHot();
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//查询最新商品
		List<Product> nList = productService.findNew();
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		return "index";
		
	}

}
