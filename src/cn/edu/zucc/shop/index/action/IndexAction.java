package cn.edu.zucc.shop.index.action;

import java.util.List;

import cn.edu.zucc.shop.category.service.CategoryService;
import cn.edu.zucc.shop.category.vo.Category;
import cn.edu.zucc.shop.product.service.ProductService;
import cn.edu.zucc.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/*
 * ��ҳ���ʵ�action
 */
public class IndexAction extends ActionSupport {

	// ע��һ�������Service
	private CategoryService categoryService;
	private ProductService productService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}	

	/*
	 * ִ�еķ�����ҳ�ķ���
	 */
	public String execute() {
		//��ѯ����һ������ļ���
		List<Category> cList = categoryService.findAll();
		//��һ��������뵽session�ķ�Χ
		ActionContext.getContext().getSession().put("cList", cList);
		
		//��ѯ������Ʒ
		List<Product> hList = productService.findHot();
		//���浽ֵջ��
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//��ѯ������Ʒ
		List<Product> nList = productService.findNew();
		//���浽ֵջ��
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		return "index";
		
	}

}
