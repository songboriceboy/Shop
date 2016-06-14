package cn.edu.zucc.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import cn.edu.zucc.shop.adminuser.service.AdminUserService;
import cn.edu.zucc.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * ��̨��½��action
 */
public class AdminUserAction extends ActionSupport implements
		ModelDriven<AdminUser> {
	// ģ�������õĶ���
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

	// ��̨��½�ķ���
	public String login() {

		AdminUser existAdminUser = adminUserService.login(adminUser);
		if (existAdminUser == null) {
			this.addActionError("�û��������������");
			return "loginFail";
		} else {
			ServletActionContext.getRequest().getSession()
					.setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";

		}

	}

}
