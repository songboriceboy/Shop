package cn.edu.zucc.shop.cart.vo;

import cn.edu.zucc.shop.product.vo.Product;

/*
 * 购物项的对象
 */
public class CartItem {
	private Product product;			// 购买的商品的信息
	private int count;				// 购买的这一种商品的数量
	private double subtotal;		// 购买的这一种商品的小计

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
