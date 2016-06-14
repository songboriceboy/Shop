package cn.edu.zucc.shop.category.adminaction;

import java.util.List;

import cn.edu.zucc.shop.category.service.CategoryService;
import cn.edu.zucc.shop.category.vo.Category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * 后台一级分类管理的action
 */
public class AdminCategoryAction extends ActionSupport implements
		ModelDriven<Category> {
	// 模型驱动所用的类
	private Category category = new Category();

	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}

	private CategoryService categoryService;


	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// 后台执行查询所有一级分类的方法
	public String findAll() {
		List<Category> cList = categoryService.findAll();
		
		//将集合的值存到值栈里，显示到页面上
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	//向数据库提交一级分类并保存
	public String save(){
		//用模型驱动接收到，然后用Service进行保存
		categoryService.save(category);
		
		return "saveSuccess";
	}
	
	
	//删除一级分类
	public String delete(){
		category = categoryService.findByCid(category.getCid());
		
		//删除
		categoryService.delete(category);
		return "deleteSuccess";
	}

	
}
