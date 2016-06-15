package cn.edu.zucc.shop.order.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cn.edu.zucc.shop.cart.vo.Cart;
import cn.edu.zucc.shop.cart.vo.CartItem;
import cn.edu.zucc.shop.order.service.OrderService;
import cn.edu.zucc.shop.order.vo.Order;
import cn.edu.zucc.shop.order.vo.OrderItem;
import cn.edu.zucc.shop.user.vo.User;
import cn.edu.zucc.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * 订单管理的action
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	// 模型驱动需要使用的对象
	private Order order = new Order();

	private OrderService orderService;
	// 接收page参数
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	// 生成订单的方法
	public String save() {
		// 保存数据到数据库里
		// 订单数据的补全
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");

		if (cart == null) {
			this.addActionError("亲!您还没有购物!先去逛逛吧~~");
			return "msg";
		}

		// 设置订单关联的客户:
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("亲!您还没有登录!");
			return "loginOrder";
		}
		order.setUser(existUser);
		
		order.setTotal(cart.getTotal());
		// 设置订单状态
		order.setState(1); // 1:未付款. 2:已付款
		// 设置订单的时间，这个有问题，插入的时候显示错误，暂时改为null
		// order.setOrdertime(new Date());
		order.setOrdertime(null);
		
		order.setAddr(existUser.getAddr());
		order.setPhone(existUser.getPhone());
		order.setName(existUser.getName());

		// 设置订单项集合:
		for (CartItem cartItem : cart.getCartItems()) {
			// 订单项的信息从购物项获得的.
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		orderService.save(order);
		// 清空购物车:
		cart.clearCart();

		// 将订单的对象显示到页面上
		// 通过值栈的形式

		return "saveSuccess";
	}

	// 我的订单查询
	public String findByUid() {

		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");

		PageBean<Order> pageBean = orderService.findByPageUid(
				existUser.getUid(), page);

		// 将分页的数据显示到页面上，存到值栈里
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}

	// 根据订单号来查询订单
	public String findByOid() {
		order = orderService.findByOid(order.getOid());

		return "findByOidSuccess";
	}

	// 假装完成付款
	public String payOrder() {
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(2);
		orderService.update(currOrder);
		return "payOrderSuccess";
	}
}
