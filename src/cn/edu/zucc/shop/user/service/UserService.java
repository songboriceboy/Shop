package cn.edu.zucc.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.shop.user.dao.UserDao;
import cn.edu.zucc.shop.user.vo.User;
import cn.edu.zucc.shop.utils.UUIDUtils;

/*
 * �û�ģ��ҵ���Ĵ���,Ҫ��@transaction
 */
@Transactional
public class UserService {
	// ע��userDao

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// ���û���ȥ��ѯ�û��ķ���
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	//ҵ�������û�ע��Ĵ���
	public void save(User user) {
		// TODO Auto-generated method stub
		//�����ݴ浽���ݿ�
		user.setState(0);//0��ʾ�û�δ���1������
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		
		userDao.save(user);
	}

	//��½ʱ
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}
	
	
}
