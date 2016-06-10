package cn.edu.zucc.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.shop.user.dao.UserDao;
import cn.edu.zucc.shop.user.vo.User;

/*
 * 用户模块业务层的代码,要加@transaction
 */
@Transactional
public class UserService {
	// 注入userDao

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 按用户名去查询用户的方法
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
}
