package cn.edu.zucc.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.zucc.shop.product.vo.Product;

/*
 * 商品的持久层代码
 */
public class ProductDao extends HibernateDaoSupport{

	//首页上热门商品的查询
	public List<Product> findHot() {
		// TODO Auto-generated method stub
		
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		
		//查询热门商品，条件是ishot为1
		criteria.add(Restrictions.eq("is_hot", 1));
		
		//倒序排序进行输出
		criteria.addOrder(Order.desc("pdate"));
		
		//执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		
		//首页上最新商品的查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		
		//查询最新商品，按日期的倒序来排序
		criteria.addOrder(Order.desc("pdate"));
		
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		
		return list;
	}

	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		
		return this.getHibernateTemplate().get(Product.class, pid);
	}
	
}
