package cn.edu.zucc.shop.user.action;

import cn.edu.zucc.shop.user.service.UserService;
import cn.edu.zucc.shop.user.vo.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/*
 * �û�ģ���action����
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	//ģ������Ҫ�õĶ���
	private User user = new User();
	//ע��userService
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	
	/*
	 * ��ת��ע��ҳ���ִ�з���
	 */
	public String registPage() {
		
		return "registPage";
	}
	
	/*
	 * ajax�����첽У���û����ķ���
	 */
	public String findByName(){
		//����Service���в�ѯ
		User existUser = userService.findByUsername(user.getUsername());
		if(existUser != null) {
			
		} else {
			
		}
		
		return  NONE;
	}

	
}
