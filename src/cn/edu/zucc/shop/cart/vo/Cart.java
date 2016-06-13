package cn.edu.zucc.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * 购物车的对象
 */
public class Cart implements Serializable{

	// 购物项的集合
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	// 购物项的总价
	private double total;

	// cart对象中有一个cartItems的属性
	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	/*
	 * public Map<Integer, CartItem> getMap() { return map; }
	 * 
	 * public void setMap(Map<Integer, CartItem> map) { this.map = map; }
	 * 
	 * public void setTotal(double total) { this.total = total; }
	 */
	
	public double getTotal() {
		return total;
	}

	// 购物车的功能
	// 添加到购物车
	public void addCart(CartItem cartItem) {
		// 判断购物车中是否存在购物项

		Integer pid = cartItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		} else {
			map.put(pid, cartItem);
		}

		total += cartItem.getSubtotal();
	}


	// 从购物车中移除购物项
	public void removeCart(Integer pid) {

		CartItem cartItem = map.remove(pid);
		total -= cartItem.getSubtotal();

	}

	// 清空购物车
	public void clearCart() {
		map.clear();
		total = 0;
	}
}
