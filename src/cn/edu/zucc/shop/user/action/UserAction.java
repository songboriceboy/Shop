package cn.edu.zucc.shop.user.action;

import cn.edu.zucc.shop.user.service.UserService;
import cn.edu.zucc.shop.user.vo.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/*
 * 用户模块的action的类
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	//模型驱动要用的对象
	private User user = new User();
	//注入userService
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	
	/*
	 * 跳转到注册页面的执行方法
	 */
	public String registPage() {
		
		return "registPage";
	}
	
	/*
	 * ajax进行异步校验用户名的方法
	 */
	public String findByName(){
		//调用Service进行查询
		User existUser = userService.findByUsername(user.getUsername());
		if(existUser != null) {
			
		} else {
			
		}
		
		return  NONE;
	}

	
}
