package cn.edu.zucc.shop.categorysecond.service;

import java.util.List;

import cn.edu.zucc.shop.category.dao.CategoryDao;
import cn.edu.zucc.shop.category.vo.Category;
import cn.edu.zucc.shop.categorysecond.dao.CategorySecondDao;
import cn.edu.zucc.shop.categorysecond.vo.CategorySecond;
import cn.edu.zucc.shop.utils.PageBean;

/*
 * ��������
 */
public class CategorySecondService {

	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	//��ҳ��ѯ��������
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//���õ�ǰ��ҳ��
		pageBean.setPage(page);
		//ÿҳ��ʾ�ļ�¼��
		int limit = 10;
		pageBean.setLimit(limit);
		
		//�ܵļ�¼��
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		//��ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		int begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	//������������ҵ���
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.save(categorySecond);
	}

	//����csid��ѯ��������ķ�����ҵ���
	public CategorySecond findByCsid(Integer csid) {
		
		return categorySecondDao.findByCsid(csid);
	}

	//ɾ����������
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
		
	}

	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.update(categorySecond);
	}

	//��ѯ���ж�������ķ���
	public List<CategorySecond> findAll() {
		// TODO Auto-generated method stub
		return categorySecondDao.findAll();
	}

	
}
