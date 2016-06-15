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

	// 接收验证码
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
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

		// 判断验证码是否正确
		// 先要从session中获取验证码的值
		String checkcode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		//如果输入的验证码和session中的不一致，就添加错误信息
		if(!checkcode.equalsIgnoreCase(checkcode1)){
			this.addActionError("验证码输入错误!");
			return "checkcodeFail";
		}
		userService.save(user);
		return "msg";
	}

	/*
	 * 用户登陆的方法
	 */

	public String login() {

		User existUser = userService.login(user);
		// 判断
		if (existUser == null) {
			// 登陆失败
			this.addActionError("登陆失败，用户名或密码错误");
			return "loginagain";
		} else {
			// 登陆成功
			// 将用户的信息存到session中
			ServletActionContext.getRequest().getSession()
					.setAttribute("existUser", existUser);
			// 页面跳转
			return "loginSuccess";
		}

	}

	/*
	 * 用户退出的方法
	 */

	public String quit() {

		// 销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}

}
