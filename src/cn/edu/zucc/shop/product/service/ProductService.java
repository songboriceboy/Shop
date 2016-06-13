package cn.edu.zucc.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.shop.product.dao.ProductDao;
import cn.edu.zucc.shop.product.vo.Product;
import cn.edu.zucc.shop.utils.PageBean;

/*
 * ��Ʒ��ҵ������
 */
@Transactional
public class ProductService {
	// ע��ProductDao
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// ��ҳ��������Ʒ��ѯ
	public List<Product> findHot() {
		return productDao.findHot();
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}

	// ������Ʒ��id����ѯ��Ʒ
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}

	//����һ�������cid���з�ҳ�Ĳ�ѯ��Ʒ
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		//����ÿҳ��ʾ�ĸ���
		int limit = 8;
		pageBean.setLimit(limit);
		//�����ܵļ�¼��
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		//ÿҳ��ʾ���ݵļ���
		int begin = (page -1 ) * limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}


}
