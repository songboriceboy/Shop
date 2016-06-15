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
 * ��̨���������action
 */
public class AdminCategorySecondAction extends ActionSupport implements
		ModelDriven<CategorySecond> {

	// ģ������ʹ�õĶ���
	CategorySecond categorySecond = new CategorySecond();

	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}

	// ע����������service
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	// ����page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// ��ѯ��������ķ���
	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService
				.findByPage(page);
		// ��pagebean�����ݱ��浽ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findAll";
	}

	// ע��һ�������Service
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// ��ת�����ҳ��
	public String addPage() {

		// ��ѯ���е�һ������
		List<Category> cList = categoryService.findAll();

		// ��������ʾ��ҳ���ϵ������б��У���ֵջ
		ActionContext.getContext().getValueStack().set("cList", cList);

		// ҳ����ת
		return "addPageSuccess";
	}

	// ��������������ݵ����ݿ�
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	//ɾ����������ķ���
	public String delete(){
		
		//���Ҫ����ɾ�����Ȳ�ѯ��ɾ��������cascade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
		
	}
	
	//�༭��������ķ���
	public String edit(){
		
		//���ݶ�������id��ѯ��������Ķ���
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		//����һ��һ������ļ���
		List<Category> cList = categoryService.findAll();
		
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	
	//�޸Ķ�������ķ���
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
