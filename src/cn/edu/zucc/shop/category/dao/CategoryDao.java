package cn.edu.zucc.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.zucc.shop.category.vo.Category;

/*
 * һ������־ò�Ķ���
 */
public class CategoryDao extends HibernateDaoSupport{

	
	//dao���ѯ����һ������ķ���
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
}
