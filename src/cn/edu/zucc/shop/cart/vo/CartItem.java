package cn.edu.zucc.shop.cart.vo;

import cn.edu.zucc.shop.product.vo.Product;

/*
 * ������Ķ���
 */
public class CartItem {
	private Product product;			// �������Ʒ����Ϣ
	private int count;				// �������һ����Ʒ������
	private double subtotal;		// �������һ����Ʒ��С��

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public double getSubtotal() {
		return count * product.getShop_price();
	}

	/*public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}*/
}
