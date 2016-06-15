package cn.edu.zucc.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.zucc.shop.category.vo.Category;

/*
 * 一级分类持久层的对象
 */
public class CategoryDao extends HibernateDaoSupport {

	// dao层查询所有一级分类的方法
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	// 保存一级分类
	public void save(Category category) {
		// TODO Auto-generated method stub

		this.getHibernateTemplate().save(category);
	}
	//查询一级分类
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	//删除一级分类
	public void delete(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(category);
	}

	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

	

}
