package cn.edu.zucc.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.shop.order.dao.OrderDao;
import cn.edu.zucc.shop.order.vo.Order;
import cn.edu.zucc.shop.utils.PageBean;

/*
 * ҵ���Ķ���ģ��
 */
@Transactional
public class OrderService {

	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// ���涩����ҵ������
	public void save(Order order) {
		orderDao.save(order);
	}

	// �ҵĶ�����ѯ
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();

		pageBean.setPage(page);
		//ÿҳ��ʾ5������
		Integer limit = 5;
		pageBean.setLimit(limit);
		//�ܼ�¼��
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(uid);
		
		pageBean.setTotalCount(totalCount);
		
		//��ҳ��
		Integer totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalPage / limit;
		} else {
			totalPage = totalPage / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		Integer begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	//���ݶ���id��ѯ����
	public Order findByOid(Integer oid) {
		
		return orderDao.findByOid(oid);
	}
	
	// ҵ����޸Ķ����ķ���:
		public void update(Order currOrder) {
			orderDao.update(currOrder);
		}

}
