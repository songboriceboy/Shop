package cn.edu.zucc.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.edu.zucc.shop.cart.vo.Cart;
import cn.edu.zucc.shop.cart.vo.CartItem;
import cn.edu.zucc.shop.product.service.ProductService;
import cn.edu.zucc.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport {

	// ����pid
	private Integer pid;
	// ����count
	private Integer count;
	// ע����Ʒ��Service
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	// ����������ӵ����ﳵ
	public String addCart() {
		// ��װһ��cartItem�Ķ���
		CartItem cartItem = new CartItem();
		// ��������
		cartItem.setCount(count);

		Product product = productService.findByPid(pid);
		// ������Ʒ
		cartItem.setProduct(product);

		// ����������ӵ����ﳵ,���ﳵ��session��
		Cart cart = getCart();
		cart.addCart(cartItem);

		return "addCart";
	}

	// ��session�л�ù��ﳵ�ķ���
	private Cart getCart() {
		// TODO Auto-generated method stub
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession()
					.setAttribute("cart", cart);
		}
		return cart;
	}

	// ��չ��ﳵ��ִ�еķ���:
	public String clearCart() {
		// ��ù��ﳵ����.
		Cart cart = getCart();
		// ���ù��ﳵ����շ���.
		cart.clearCart();
		return "clearCart";
	}

	// �ӹ��ﳵ���Ƴ�������ķ���:
	public String removeCart() {
		// ��ù��ﳵ����
		Cart cart = getCart();
		// ���ù��ﳵ���Ƴ��ķ���:
		cart.removeCart(pid);
		// ����ҳ��:
		return "removeCart";
	}

	// �ҵĹ��ﳵ:ִ�еķ���
	public String myCart() {
		return "myCart";
	}

}
