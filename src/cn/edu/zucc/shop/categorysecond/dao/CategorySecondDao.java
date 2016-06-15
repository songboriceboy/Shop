package cn.edu.zucc.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.zucc.shop.category.vo.Category;
import cn.edu.zucc.shop.categorysecond.vo.CategorySecond;
import cn.edu.zucc.shop.utils.PageHibernateCallback;

/*
 *�����������ĳ־ò�
 */
public class CategorySecondDao extends HibernateDaoSupport {

	// ��ѯ���Ķ������������
	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// dao��Ĳ�ѯ��������ķ���
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";

		List<CategorySecond> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<CategorySecond>(hql, null, begin,
						limit));

		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// �����������
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	// ɾ�����������dao
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	// ���ݶ��������csid��ѯ��������
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(categorySecond);
	}

	// ��ѯ���еĶ�������
	public List<CategorySecond> findAll() {

		String hql = "from CategorySecond";
		return this.getHibernateTemplate().find(hql);
	}

}
