package cn.edu.zucc.shop.order.adminaction;

import cn.edu.zucc.shop.order.service.OrderService;
import cn.edu.zucc.shop.order.vo.Order;
import cn.edu.zucc.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminOrderAction extends ActionSupport implements
		ModelDriven<Order> {

	//模型驱动要用的对象
	private Order order = new Order();

	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}

	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}


	public String findAll(){
		PageBean<Order> pageBean = orderService.findByPage(page);
		
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "findAll";
	}
}
