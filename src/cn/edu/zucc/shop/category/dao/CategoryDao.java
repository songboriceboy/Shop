package cn.edu.zucc.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.zucc.shop.category.vo.Category;

/*
 * 一级分类持久层的对象
 */
public class CategoryDao extends HibernateDaoSupport{

	
	//dao层查询所有一级分类的方法
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
}
