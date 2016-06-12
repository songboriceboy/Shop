package cn.edu.zucc.shop.index.action;

import java.util.List;

import cn.edu.zucc.shop.category.service.CategoryService;
import cn.edu.zucc.shop.category.vo.Category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/*
 * ��ҳ���ʵ�action
 */
public class IndexAction extends ActionSupport {

	// ע��һ�������Service
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/*
	 * ִ�еķ�����ҳ�ķ���
	 */

	public String execute() {
		//��ѯ����һ������ļ���
		List<Category>cList = categoryService.findAll();
		
		//��һ��������뵽session�ķ�Χ
		ActionContext.getContext().getSession().put("cList", cList);
		return "index";
	}

}
