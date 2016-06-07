package cn.edu.zucc.shop.index.action;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 首页访问的action
 */
public class IndexAction extends ActionSupport {

	/*
	 * 执行的访问首页的方法
	 */

	public String execute() {

		return "index";
	}
}
