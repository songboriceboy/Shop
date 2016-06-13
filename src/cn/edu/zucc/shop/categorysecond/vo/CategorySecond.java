package cn.edu.zucc.shop.categorysecond.vo;

import java.util.HashSet;
import java.util.Set;

import cn.edu.zucc.shop.category.vo.Category;
import cn.edu.zucc.shop.product.vo.Product;

/*
 * 二级分类的实体
 */
public class CategorySecond {
	private String csid;
	private String csname;

	// 所属一级分类,存的是一级分类的对象
	private Category category;

	// 配置商品集合
	private Set<Product> products = new HashSet<Product>();

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	// 一级分类中要存放二级分类的集合
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();

	public String getCsid() {
		return csid;
	}

	public void setCsid(String csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}

	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}

}
