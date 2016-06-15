package cn.edu.zucc.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.zucc.shop.order.vo.Order;
import cn.edu.zucc.shop.utils.PageBean;
import cn.edu.zucc.shop.utils.PageHibernateCallback;

/*
 * ����ģ��־ò�Ĵ���
 */
public class OrderDao extends HibernateDaoSupport{

	//dao��ı��涩���ķ���
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	//�ҵĶ���������ѯ
	public Integer findByCountUid(Integer uid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,uid);
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	
	//�ҵĶ�����ѯ
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		
		//��ʱ�����ᰴ��ʱ�������򣬶������ɵ�ʱ�������⣬�ĳɰ��ն�����������
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

	//ͳ�ƶ�������
	public int findByCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		} 
		return 0;
	}

	//��ѯ��ǰҳ��
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
