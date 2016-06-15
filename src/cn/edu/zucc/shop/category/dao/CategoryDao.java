package cn.edu.zucc.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.zucc.shop.category.vo.Category;

/*
 * һ������־ò�Ķ���
 */
public class CategoryDao extends HibernateDaoSupport {

	// dao���ѯ����һ������ķ���
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	// ����һ������
	public void save(Category category) {
		// TODO Auto-generated method stub

		this.getHibernateTemplate().save(category);
	}
	//��ѯһ������
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	//ɾ��һ������
	public void delete(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(category);
	}

	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

	

}
