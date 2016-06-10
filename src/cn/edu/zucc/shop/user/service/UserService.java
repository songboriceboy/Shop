package cn.edu.zucc.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.shop.user.dao.UserDao;
import cn.edu.zucc.shop.user.vo.User;

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
}
