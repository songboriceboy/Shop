package cn.edu.zucc.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.shop.product.dao.ProductDao;
import cn.edu.zucc.shop.product.vo.Product;
import cn.edu.zucc.shop.utils.PageBean;

/*
 * 商品的业务层代码
 */
@Transactional
public class ProductService {
	// 注入ProductDao
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 首页上热门商品查询
	public List<Product> findHot() {
		return productDao.findHot();
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}

	// 根据商品的id来查询商品
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}

	//根据一级分类的cid带有分页的查询商品
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		//设置每页显示的个数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总的记录数
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		//每页显示数据的集合
		int begin = (page -1 ) * limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}


}
