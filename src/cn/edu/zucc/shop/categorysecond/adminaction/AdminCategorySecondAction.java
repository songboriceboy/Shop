package cn.edu.zucc.shop.categorysecond.adminaction;

import java.util.List;

import cn.edu.zucc.shop.category.service.CategoryService;
import cn.edu.zucc.shop.category.vo.Category;
import cn.edu.zucc.shop.categorysecond.service.CategorySecondService;
import cn.edu.zucc.shop.categorysecond.vo.CategorySecond;
import cn.edu.zucc.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * 后台二级分类的action
 */
public class AdminCategorySecondAction extends ActionSupport implements
		ModelDriven<CategorySecond> {

	// 模型驱动使用的对象
	CategorySecond categorySecond = new CategorySecond();

	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}

	// 注入二级分类的service
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	// 接收page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// 查询二级分类的方法
	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService
				.findByPage(page);
		// 将pagebean的数据保存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findAll";
	}

	// 注入一级分类的Service
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// 跳转到添加页面
	public String addPage() {

		// 查询所有的一级分类
		List<Category> cList = categoryService.findAll();

		// 把数据显示到页面上的下拉列表中，用值栈
		ActionContext.getContext().getValueStack().set("cList", cList);

		// 页面跳转
		return "addPageSuccess";
	}

	// 保存二级分类内容到数据库
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	//删除二级分类的方法
	public String delete(){
		
		//如果要级联删除，先查询再删除，配置cascade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
		
	}
	
	//编辑二级分类的方法
	public String edit(){
		
		//根据二级分类id查询二级分类的对象
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		//返回一个一级分类的集合
		List<Category> cList = categoryService.findAll();
		
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	
	//修改二级分类的方法
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
