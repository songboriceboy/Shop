package cn.edu.zucc.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * ���ﳵ�Ķ���
 */
public class Cart implements Serializable{

	// ������ļ���
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	// ��������ܼ�
	private double total;

	// cart��������һ��cartItems������
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

	// ���ﳵ�Ĺ���
	// ��ӵ����ﳵ
	public void addCart(CartItem cartItem) {
		// �жϹ��ﳵ���Ƿ���ڹ�����

		Integer pid = cartItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		} else {
			map.put(pid, cartItem);
		}

		total += cartItem.getSubtotal();
	}


	// �ӹ��ﳵ���Ƴ�������
	public void removeCart(Integer pid) {

		CartItem cartItem = map.remove(pid);
		total -= cartItem.getSubtotal();

	}

	// ��չ��ﳵ
	public void clearCart() {
		map.clear();
		total = 0;
	}
}
