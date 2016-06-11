package cn.edu.zucc.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.edu.zucc.shop.user.service.UserService;
import cn.edu.zucc.shop.user.vo.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * 用户模块的action的类
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	// 模型驱动要用的对象
	private User user = new User();

	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	// 注入userService
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*
	 * 跳转到注册页面的执行方法
	 */
	public String registPage() {

		return "registPage";
	}

	/*
	 * 跳转到用户登陆页面的方法
	 */

	public String loginPage() {
		
		return "loginPage";
	}
	
	/*
	 * ajax进行异步校验用户名的方法
	 */
	public String findByName() throws IOException {
		// 调用Service进行查询:
		User existUser = userService.findByUsername(user.getUsername());
		// 获得response对象,向页面输出:
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// 判断
		if (existUser != null) {
			// 查询到该用户:用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		} else {
			// 没查询到该用户:用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	/*
	 * 用户注册的方法
	 */
	public String regist() {

		userService.save(user);

		return NONE;
	}

	

}
