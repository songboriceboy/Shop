package cn.edu.zucc.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.zucc.shop.product.vo.Product;

/*
 * ��Ʒ�ĳ־ò����
 */
public class ProductDao extends HibernateDaoSupport{

	//��ҳ��������Ʒ�Ĳ�ѯ
	public List<Product> findHot() {
		// TODO Auto-generated method stub
		
		//ʹ������������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		
		//��ѯ������Ʒ��������ishotΪ1
		criteria.add(Restrictions.eq("is_hot", 1));
		
		//��������������
		criteria.addOrder(Order.desc("pdate"));
		
		//ִ�в�ѯ
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		
		//��ҳ��������Ʒ�Ĳ�ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		
		//��ѯ������Ʒ�������ڵĵ���������
		criteria.addOrder(Order.desc("pdate"));
		
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		
		return list;
	}

	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		
		return this.getHibernateTemplate().get(Product.class, pid);
	}
	
}
