package cn.edu.zucc.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.zucc.shop.order.vo.Order;
import cn.edu.zucc.shop.utils.PageBean;
import cn.edu.zucc.shop.utils.PageHibernateCallback;

/*
 * 订单模块持久层的代码
 */
public class OrderDao extends HibernateDaoSupport{

	//dao层的保存订单的方法
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	//我的订单个数查询
	public Integer findByCountUid(Integer uid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,uid);
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	
	//我的订单查询
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		
		//暂时并不会按照时间来排序，订单生成的时间有问题，改成按照订单号来排序
		String hql = "from Order o where o.user.uid = ? order by oid desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		return list;
	}

	public Order findByOid(Integer oid) {
		
		
		return this.getHibernateTemplate().get(Order.class,oid);
	}

	public void update(Order currOrder) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(currOrder);
	}

	//统计订单个数
	public int findByCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		} 
		return 0;
	}

	//查询当前页的
	public  List<Order> findByPage(int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "from Order o order by o.oid desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		if(list != null && list.size() > 0) {
			return list;
		}
		
		return null;
	}

	
	
}
