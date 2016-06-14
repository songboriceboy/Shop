package cn.edu.zucc.shop.category.adminaction;

import java.util.List;

import cn.edu.zucc.shop.category.service.CategoryService;
import cn.edu.zucc.shop.category.vo.Category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * ��̨һ����������action
 */
public class AdminCategoryAction extends ActionSupport implements
		ModelDriven<Category> {
	// ģ���������õ���
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

	// ��ִ̨�в�ѯ����һ������ķ���
	public String findAll() {
		List<Category> cList = categoryService.findAll();
		
		//�����ϵ�ֵ�浽ֵջ���ʾ��ҳ����
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	//�����ݿ��ύһ�����ಢ����
	public String save(){
		//��ģ���������յ���Ȼ����Service���б���
		categoryService.save(category);
		
		return "saveSuccess";
	}
	
	
	//ɾ��һ������
	public String delete(){
		category = categoryService.findByCid(category.getCid());
		
		//ɾ��
		categoryService.delete(category);
		return "deleteSuccess";
	}

	
}
