package cn.edu.zucc.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import cn.edu.zucc.shop.adminuser.service.AdminUserService;
import cn.edu.zucc.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * 后台登陆的action
 */
public class AdminUserAction extends ActionSupport implements
		ModelDriven<AdminUser> {
	// 模型驱动用的对象
	private AdminUser adminUser = new AdminUser();

	@Override
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}

	private AdminUserService adminUserService;

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	// 后台登陆的方法
	public String login() {

		AdminUser existAdminUser = adminUserService.login(adminUser);
		if (existAdminUser == null) {
			this.addActionError("用户名或者密码错误！");
			return "loginFail";
		} else {
			ServletActionContext.getRequest().getSession()
					.setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";

		}

	}

}
