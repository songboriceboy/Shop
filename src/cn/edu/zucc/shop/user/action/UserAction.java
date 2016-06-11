package cn.edu.zucc.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.edu.zucc.shop.user.service.UserService;
import cn.edu.zucc.shop.user.vo.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * �û�ģ���action����
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	// ģ������Ҫ�õĶ���
	private User user = new User();

	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	// ע��userService
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*
	 * ��ת��ע��ҳ���ִ�з���
	 */
	public String registPage() {

		return "registPage";
	}

	/*
	 * ��ת���û���½ҳ��ķ���
	 */

	public String loginPage() {
		
		return "loginPage";
	}
	
	/*
	 * ajax�����첽У���û����ķ���
	 */
	public String findByName() throws IOException {
		// ����Service���в�ѯ:
		User existUser = userService.findByUsername(user.getUsername());
		// ���response����,��ҳ�����:
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// �ж�
		if (existUser != null) {
			// ��ѯ�����û�:�û����Ѿ�����
			response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
		} else {
			// û��ѯ�����û�:�û�������ʹ��
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
		}
		return NONE;
	}

	/*
	 * �û�ע��ķ���
	 */
	public String regist() {

		userService.save(user);

		return NONE;
	}

	

}
