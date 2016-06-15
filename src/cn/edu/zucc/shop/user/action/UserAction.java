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

	// ������֤��
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
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

		// �ж���֤���Ƿ���ȷ
		// ��Ҫ��session�л�ȡ��֤���ֵ
		String checkcode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		//����������֤���session�еĲ�һ�£�����Ӵ�����Ϣ
		if(!checkcode.equalsIgnoreCase(checkcode1)){
			this.addActionError("��֤���������!");
			return "checkcodeFail";
		}
		userService.save(user);
		return "msg";
	}

	/*
	 * �û���½�ķ���
	 */

	public String login() {

		User existUser = userService.login(user);
		// �ж�
		if (existUser == null) {
			// ��½ʧ��
			this.addActionError("��½ʧ�ܣ��û������������");
			return "loginagain";
		} else {
			// ��½�ɹ�
			// ���û�����Ϣ�浽session��
			ServletActionContext.getRequest().getSession()
					.setAttribute("existUser", existUser);
			// ҳ����ת
			return "loginSuccess";
		}

	}

	/*
	 * �û��˳��ķ���
	 */

	public String quit() {

		// ����session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}

}
