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
 * 商品的持久层代码
 */
public class ProductDao extends HibernateDaoSupport {

	// 首页上热门商品的查询
	public List<Product> findHot() {
		// TODO Auto-generated method stub

		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);

		// 查询热门商品，条件是ishot为1
		criteria.add(Restrictions.eq("is_hot", 1));

		// 倒序排序进行输出
		criteria.addOrder(Order.desc("pdate"));

		// 执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 10);
		return list;
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub

		// 首页上最新商品的查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);

		// 查询最新商品，按日期的倒序来排序
		criteria.addOrder(Order.desc("pid"));

		List<Product> list = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 10);

		return list;
	}

	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub

		return this.getHibernateTemplate().get(Product.class, pid);
	}

	// 根据分类的id来查询商品的个数
	public int findCountCid(Integer cid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 根据分类id来查询商品的集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		// 分页
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, new Object[] { cid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 根据二级分类来查询商品的个数
	public int findCountCsid(Integer csid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 根据二级分类查询商品信息
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

	// 统计商品个数
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 带有分页的查询商品的方法
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
