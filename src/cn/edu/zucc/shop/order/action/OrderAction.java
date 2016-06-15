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
 * ���������action
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	// ģ��������Ҫʹ�õĶ���
	private Order order = new Order();

	private OrderService orderService;
	// ����page����
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

	// ���ɶ����ķ���
	public String save() {
		// �������ݵ����ݿ���
		// �������ݵĲ�ȫ
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");

		if (cart == null) {
			this.addActionError("��!����û�й���!��ȥ����~~");
			return "msg";
		}

		// ���ö��������Ŀͻ�:
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("��!����û�е�¼!");
			return "loginOrder";
		}
		order.setUser(existUser);
		
		order.setTotal(cart.getTotal());
		// ���ö���״̬
		order.setState(1); // 1:δ����. 2:�Ѹ���
		// ���ö�����ʱ�䣬��������⣬�����ʱ����ʾ������ʱ��Ϊnull
		// order.setOrdertime(new Date());
		order.setOrdertime(null);
		
		order.setAddr(existUser.getAddr());
		order.setPhone(existUser.getPhone());
		order.setName(existUser.getName());

		// ���ö������:
		for (CartItem cartItem : cart.getCartItems()) {
			// ���������Ϣ�ӹ������õ�.
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		orderService.save(order);
		// ��չ��ﳵ:
		cart.clearCart();

		// �������Ķ�����ʾ��ҳ����
		// ͨ��ֵջ����ʽ

		return "saveSuccess";
	}

	// �ҵĶ�����ѯ
	public String findByUid() {

		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");

		PageBean<Order> pageBean = orderService.findByPageUid(
				existUser.getUid(), page);

		// ����ҳ��������ʾ��ҳ���ϣ��浽ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}

	// ���ݶ���������ѯ����
	public String findByOid() {
		order = orderService.findByOid(order.getOid());

		return "findByOidSuccess";
	}

	// ��װ��ɸ���
	public String payOrder() {
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(2);
		orderService.update(currOrder);
		return "payOrderSuccess";
	}
}
