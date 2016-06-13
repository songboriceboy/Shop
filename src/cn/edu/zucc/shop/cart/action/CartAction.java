package cn.edu.zucc.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.edu.zucc.shop.cart.vo.Cart;
import cn.edu.zucc.shop.cart.vo.CartItem;
import cn.edu.zucc.shop.product.service.ProductService;
import cn.edu.zucc.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport {

	// 接收pid
	private Integer pid;
	// 接收count
	private Integer count;
	// 注入商品的Service
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

	// 将购物项添加到购物车
	public String addCart() {
		// 封装一个cartItem的对象
		CartItem cartItem = new CartItem();
		// 设置数量
		cartItem.setCount(count);

		Product product = productService.findByPid(pid);
		// 设置商品
		cartItem.setProduct(product);

		// 将购物项添加到购物车,购物车在session中
		Cart cart = getCart();
		cart.addCart(cartItem);

		return "addCart";
	}

	// 从session中获得购物车的方法
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

	// 清空购物车的执行的方法:
	public String clearCart() {
		// 获得购物车对象.
		Cart cart = getCart();
		// 调用购物车中清空方法.
		cart.clearCart();
		return "clearCart";
	}

	// 从购物车中移除购物项的方法:
	public String removeCart() {
		// 获得购物车对象
		Cart cart = getCart();
		// 调用购物车中移除的方法:
		cart.removeCart(pid);
		// 返回页面:
		return "removeCart";
	}

	// 我的购物车:执行的方法
	public String myCart() {
		return "myCart";
	}

}
