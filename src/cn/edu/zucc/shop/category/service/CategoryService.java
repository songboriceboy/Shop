package cn.edu.zucc.shop.category.service;

import java.util.List;

import cn.edu.zucc.shop.category.dao.CategoryDao;
import cn.edu.zucc.shop.category.vo.Category;

/*
 * һ������ҵ���Ķ���
 */
public class CategoryService {

	// ע��dao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	//ҵ����ѯ����һ������ķ�����ʵ��
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		
		return categoryDao.findAll();
	}

}
