package cn.edu.zucc.shop.product.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.zucc.shop.product.vo.Product;
import cn.edu.zucc.shop.utils.PageHibernateCallback;

/*
 * ��Ʒ�ĳ־ò����
 */
public class ProductDao extends HibernateDaoSupport {

	// ��ҳ��������Ʒ�Ĳ�ѯ
	public List<Product> findHot() {
		// TODO Auto-generated method stub

		// ʹ������������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);

		// ��ѯ������Ʒ��������ishotΪ1
		criteria.add(Restrictions.eq("is_hot", 1));

		// ��������������
		criteria.addOrder(Order.desc("pdate"));

		// ִ�в�ѯ
		List<Product> list = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 10);
		return list;
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub

		// ��ҳ��������Ʒ�Ĳ�ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);

		// ��ѯ������Ʒ�������ڵĵ���������
		criteria.addOrder(Order.desc("pid"));

		List<Product> list = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 10);

		return list;
	}

	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub

		return this.getHibernateTemplate().get(Product.class, pid);
	}

	// ���ݷ����id����ѯ��Ʒ�ĸ���
	public int findCountCid(Integer cid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// ���ݷ���id����ѯ��Ʒ�ļ���
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		// ��ҳ
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, new Object[] { cid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// ���ݶ�����������ѯ��Ʒ�ĸ���
	public int findCountCsid(Integer csid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// ���ݶ��������ѯ��Ʒ��Ϣ
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, new Object[] { csid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}

		return null;
	}

	// ͳ����Ʒ����
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// ���з�ҳ�Ĳ�ѯ��Ʒ�ķ���
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pid desc";
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, null, begin, limit));

		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public void save(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(product);
	}

	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	public void update(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(product);
	}

}
