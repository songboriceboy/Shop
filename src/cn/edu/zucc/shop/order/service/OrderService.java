package cn.edu.zucc.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.shop.order.dao.OrderDao;
import cn.edu.zucc.shop.order.vo.Order;
import cn.edu.zucc.shop.utils.PageBean;

/*
 * 业务层的订单模块
 */
@Transactional
public class OrderService {

	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// 保存订单的业务层代码
	public void save(Order order) {
		orderDao.save(order);
	}

	// 我的订单查询
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();

		pageBean.setPage(page);
		//每页显示5个订单
		Integer limit = 5;
		pageBean.setLimit(limit);
		//总记录数
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(uid);
		
		pageBean.setTotalCount(totalCount);
		
		//总页数
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

	//根据订单id查询订单
	public Order findByOid(Integer oid) {
		
		return orderDao.findByOid(oid);
	}
	
	// 业务层修改订单的方法:
		public void update(Order currOrder) {
			orderDao.update(currOrder);
		}

}
