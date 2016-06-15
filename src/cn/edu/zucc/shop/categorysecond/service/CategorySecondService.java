package cn.edu.zucc.shop.categorysecond.service;

import java.util.List;

import cn.edu.zucc.shop.category.dao.CategoryDao;
import cn.edu.zucc.shop.category.vo.Category;
import cn.edu.zucc.shop.categorysecond.dao.CategorySecondDao;
import cn.edu.zucc.shop.categorysecond.vo.CategorySecond;
import cn.edu.zucc.shop.utils.PageBean;

/*
 * 二级分类
 */
public class CategorySecondService {

	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	//分页查询二级分类
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//设置当前的页数
		pageBean.setPage(page);
		//每页显示的记录数
		int limit = 10;
		pageBean.setLimit(limit);
		
		//总的记录数
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		//总页数
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
	
	//保存二级分类的业务层
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.save(categorySecond);
	}

	//根据csid查询二级分类的方法，业务层
	public CategorySecond findByCsid(Integer csid) {
		
		return categorySecondDao.findByCsid(csid);
	}

	//删除二级分类
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
		
	}

	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.update(categorySecond);
	}

	//查询所有二级分类的方法
	public List<CategorySecond> findAll() {
		// TODO Auto-generated method stub
		return categorySecondDao.findAll();
	}

	
}
